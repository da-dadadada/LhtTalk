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

package com.lht.lhttalk.base.model;

import android.content.SharedPreferences;
import android.os.CountDownTimer;

import com.lht.lhttalk.cfg.SPConstants;

/**
 * <p><b>Package</b> com.lht.vsocyy.mvp.model
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> TimerClockModel
 * <p><b>Description</b>: 时钟任务业务类,获取timerclock开始倒计时，自己计算剩余时间，注意使用时先更新起始点时间戳
 * Created by leobert on 2016/5/5.
 */
public class TimerClockModel implements SPConstants.Timer {

    private SharedPreferences sharedPreferences;
    private final int DEFAULT_PERIOD = 60000;

    private long period;

    private final String tag;

    private long startTimeStamp = 0;

    public TimerClockModel(ISharedPreferenceProvider iSharedPreferenceProvider, OnTimeLapseListener onTimeLapseListener) {
        this.tag = iSharedPreferenceProvider.getRecordTag();
        sharedPreferences = iSharedPreferenceProvider.getSharedPreferences();
        period = DEFAULT_PERIOD;
        this.timeLapseListener = onTimeLapseListener;
//        this.timeLapseListenerWeakReference = new WeakReference<OnTimeLapseListener>(onTimeLapseListener);
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    /**
     * 更新计时起始点
     */
    public void updateTimeStamp() {
        this.updateTimeStamp(getCurrentTimeStamp());
    }

    /**
     * 更新计时起始点
     *
     * @param timeStamp 当前的时间戳
     */
    public void updateTimeStamp(long timeStamp) {
        startTimeStamp = timeStamp;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(KEY_VERIFICATE + tag, timeStamp);
        editor.commit();
    }

    /**
     * desc: 获取倒计时对象，注意获取之后立即start，中间的时间不会被考虑
     *
     * @param countDownInterval
     * @return
     */
    public TimeCount getTimeClock(long countDownInterval) {
        startTimeStamp = sharedPreferences.getLong(KEY_VERIFICATE + tag, 0);
        Long remainTime = period - (getCurrentTimeStamp() - startTimeStamp);
        remainTime = remainTime > 0 ? remainTime : 0;
        if (timeCount != null) {
            timeCount.cancel();
            timeCount = null;
        }
        timeCount = new TimeCount(remainTime, countDownInterval);
        return timeCount;
    }

    /**
     * @param countDownInterval 每次触发的时间间隔
     */
    public void resume(long countDownInterval) {
        startTimeStamp = sharedPreferences.getLong(KEY_VERIFICATE + tag, 0);
        Long remainTime = period - (getCurrentTimeStamp() - startTimeStamp);
        if (remainTime <= 0) {
            return;
        }
        if (timeCount != null) {
            timeCount.cancel();
            timeCount = null;
        }
        timeCount = new TimeCount(remainTime, countDownInterval);
        timeCount.start();
    }

    private TimeCount timeCount;

    public class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
//            updateTimeStamp();
            if (getTimeLapseListener() != null) {
                getTimeLapseListener().onFinish();
            }
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (getTimeLapseListener() != null) {
                getTimeLapseListener().onTick(millisUntilFinished);
            }
        }
    }

//    private WeakReference<OnTimeLapseListener> timeLapseListenerWeakReference;

    private OnTimeLapseListener timeLapseListener;

    private OnTimeLapseListener getTimeLapseListener() {
//        return timeLapseListenerWeakReference.get();
        return timeLapseListener;
    }

    /**
     * 计时器回调接口
     */
    public interface OnTimeLapseListener {
        /**
         * 计时完成
         */
        void onFinish();

        /**
         * 每一个触发
         *
         * @param millisUntilFinished 距离结束的时间
         */
        void onTick(long millisUntilFinished);
    }

    /**
     * 获取当前系统时间
     *
     * @return 当前系统时间戳
     */
    private Long getCurrentTimeStamp() {
        return System.currentTimeMillis();
    }

    public interface ISharedPreferenceProvider {
        /**
         * @return 记录文件
         */
        SharedPreferences getSharedPreferences();

        /**
         * @return 记录的tag
         */
        String getRecordTag();
    }

    public void cancel() {
        if (timeCount != null) {
            timeCount.cancel();
            timeCount = null;
        }
//        timeLapseListener = null;
    }
}
