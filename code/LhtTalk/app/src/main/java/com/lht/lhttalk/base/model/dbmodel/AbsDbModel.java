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

import com.lht.lhttalk.base.MainApplication;

import java.util.Collection;
import java.util.List;

/**
 * <p><b>Package:</b> com.lht.lhttalk.base.model.dbmodel </p>
 * <p><b>Project:</b> czspace </p>
 * <p><b>Classname:</b> AbsDbModel </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/6/14.
 */

public abstract class AbsDbModel<Bean> {
    protected abstract String getDatabaseName();

    private final Class<Bean> beanClass;

    public AbsDbModel(Class<Bean> beanClass) {
        this.beanClass = beanClass;
    }

    private LiteOrmContext liteOrmContext;

    protected final void connect() {
//        liteOrmContext =  LiteOrm.newSingleInstance(MainApplication.getOurInstance(),
//                getDatabaseName());
        liteOrmContext = LiteOrmContext.getLiteOrmContext(MainApplication.getOurInstance(),
                getDatabaseName(),LiteOrmContext.SINGLE);
    }

    protected final LiteOrmContext getConnectedInstance() {
        if (liteOrmContext == null) {
            connect();
        }
        return liteOrmContext;

    }



    public final IDataBaseExecutorHandle saveOrUpdate(final Bean bean) {
        return startTask(new ISimpleTask() {
            @Override
            public Void startTask() {
                getConnectedInstance().save(bean);
                //getConnectedInstance().close();
                return voidInstance;
            }
        });
    }

    public final IDataBaseExecutorHandle saveOrUpdate(final Bean bean,
                                                      SimpleOnTaskFinishListener listener) {
        return startTask(new ISimpleTask() {
            @Override
            public Void startTask() {
                getConnectedInstance().save(bean);
                getConnectedInstance().close();
                return voidInstance;
            }
        }, listener);
    }

    public final IDataBaseExecutorHandle queryAll(OnQueryTaskFinishListener<List<Bean>> listener) {
        return startTask(new IModelTask<List<Bean>>() {
            @Override
            public List<Bean> startTask() {
                List<Bean> result =  getConnectedInstance().query(beanClass);
                getConnectedInstance().close();
                return result;
            }
        }, listener);
    }

    public final IDataBaseExecutorHandle deleteAll(SimpleOnTaskFinishListener listener) {
        return startTask(new ISimpleTask() {
            @Override
            public Void startTask() {
                getConnectedInstance().delete(beanClass);
                getConnectedInstance().close();
                return voidInstance;
            }
        }, listener);
    }


    public final IDataBaseExecutorHandle delete(final Bean bean) {
        return startTask(new ISimpleTask() {

            @Override
            public Void startTask() {
                getConnectedInstance().delete(bean);
                getConnectedInstance().close();
                return voidInstance;
            }
        });
    }

    public final IDataBaseExecutorHandle delete(final Bean bean,
                                                SimpleOnTaskFinishListener listener) {
        return startTask(new ISimpleTask() {
            @Override
            public Void startTask() {
                getConnectedInstance().delete(bean);
                getConnectedInstance().close();
                return voidInstance;
            }
        }, listener);
    }

    protected final <T> IDataBaseExecutorHandle startTask(IModelTask<T> task) {
        return DataBaseTaskExecutor.task(task)
                .start();
    }

    protected final <T> IDataBaseExecutorHandle startTask(IModelTask<T> task,
                                                          IModelTask.OnTaskFinishListener<T> listener) {
        return DataBaseTaskExecutor.task(task)
                .listen(listener)
                .start();
    }

    protected static Void voidInstance;

    static {
        try {
            voidInstance = Void.TYPE.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            voidInstance = null;
        }
    }

    public abstract static class SimpleOnTaskFinishListener
            implements IModelTask.OnTaskFinishListener<Void> {

        @Override
        public final void onSuccess(Void result) {
            onSuccess();
        }

        public abstract void onSuccess();
    }

    public abstract static class OnQueryTaskFinishListener<R>
            implements IModelTask.OnTaskFinishListener<R> {

        @Override
        public void onSuccess(R result) {
            if (result == null) {
                onNotExist();
                return;
            }

            if (result instanceof Collection) {
                if (((Collection) result).isEmpty()) {
                    onNotExist();
                    return;
                }
            }

            onExist(result);
        }

        public abstract void onNotExist();

        public abstract void onExist(R result);
    }

    public interface StringKeyDbModel {
        //       void deleteById(String key);
        IDataBaseExecutorHandle queryById(String key, OnQueryTaskFinishListener listener);
    }

    public interface NumKeyDbModel {
        //        void deleteById(long id);
        IDataBaseExecutorHandle queryById(long key, OnQueryTaskFinishListener listener);
    }

    protected interface ISimpleTask extends IModelTask<Void> {

    }

}
