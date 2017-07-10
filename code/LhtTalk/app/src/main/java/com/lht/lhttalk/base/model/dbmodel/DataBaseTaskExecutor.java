/*
 * MIT License
 *
 * Copyright (c) 2017 leobert-lan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.lht.lhttalk.base.model.dbmodel;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;

import com.lht.lhttalk.util.debug.DLog;

import java.lang.ref.WeakReference;

/**
 * <p><b>Package:</b> com.lht.lhttalk.base.model.dbmodel </p>
 * <p><b>Project:</b> czspace </p>
 * <p><b>Classname:</b> DataBaseModels </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/6/14.
 */

/*public packaged*/ class DataBaseTaskExecutor<Result> implements IDataBaseExecutorHandle {

    private final IModelTask<Result> iModelTask;

    private IModelTask.OnTaskFinishListener<Result> listener;

    private MHandler<Result> handler;

    private DataBaseTaskExecutor(IModelTask<Result> iModelTask) {
        this.iModelTask = iModelTask;
    }

    static <R> DataBaseTaskExecutor<R> task(@NonNull IModelTask<R> iModelTask) {
        return new DataBaseTaskExecutor<>(iModelTask);
    }

    DataBaseTaskExecutor<Result> listen(IModelTask.OnTaskFinishListener<Result> listener) {
        this.listener = listener;
        return this;
    }

    private boolean hasExecuted = false;

    public synchronized IDataBaseExecutorHandle start() {
        if (hasExecuted())
            throw new IllegalStateException("executor can only start once time");
        hasExecuted = true;
        handler = new MHandler<>(listener);
        TaskExecutorThread taskExecutorThread = new TaskExecutorThread(handler);
        taskExecutorThread.start();
        return this;
    }

    @Override
    public boolean hasExecuted() {
        return hasExecuted;
    }

    @Override
    public boolean cancelCallback() {
        if (!hasExecuted())
            return false;
        if (listener == null)
            return false;
        handler.shutDown();
        return true;
    }


    private class TaskExecutorThread extends Thread {

        private MHandler<Result> mHandler;

        TaskExecutorThread(MHandler<Result> mHandler) {
            this.mHandler = mHandler;
        }

        @Override
        public void run() {
            super.run();
            if (iModelTask != null) {
                mHandler.sendComplete(iModelTask.startTask());
            } else {
                DLog.e(getClass(), "task is null");
            }
        }
    }

    private static class MHandler<R> extends Handler {
        private class ResultWrapper<T> {
            private T result;

            ResultWrapper(T result) {
                this.result = result;
            }

            public T getResult() {
                return result;
            }
        }

        private void shutDown() {
            if (listenerRef != null)
                listenerRef.clear();
        }

        private static final int WHAT_COMPLETE = 0;
        private final WeakReference<IModelTask.OnTaskFinishListener<R>> listenerRef;
        private ResultWrapper<R> resultWrapper;

        MHandler(IModelTask.OnTaskFinishListener<R> listener) {
            super(Looper.getMainLooper());
            if (listener != null)
                this.listenerRef = new WeakReference<>(listener);
            else
                this.listenerRef = null;
        }

        void sendComplete(R result) {
            synchronized (this) {
                Message msg = obtainMessage(MHandler.WHAT_COMPLETE);
                resultWrapper = new ResultWrapper<>(result);
                sendMessage(msg);
            }
        }

        private R getResult() {
            synchronized (this) {
                if (resultWrapper == null)
                    return null;
                else
                    return resultWrapper.getResult();
            }
        }


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_COMPLETE:
                    if (null == listenerRef) {
                        return;
                    }
                    IModelTask.OnTaskFinishListener<R> listener = listenerRef.get();
                    if (null == listener) {
                        DLog.d(getClass(), "listener has bean gc");
                        return;
                    }

                    listener.onSuccess(getResult());
                    break;
                default:
                    break;
            }
        }
    }
}
