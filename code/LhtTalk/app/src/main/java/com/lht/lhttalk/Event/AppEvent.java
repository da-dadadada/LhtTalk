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

package com.lht.lhttalk.Event;

import com.lht.lhttalk.base.launcher.ITriggerCompare;
import com.lht.lhttalk.module.ucenter.UserBean;

/**
 * <p><b>Package</b> com.lht.vsocyy.Event
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> AppEvent
 * <p><b>Description</b>: 使用静态内部类管理所有的event事件
 * 本次项目中，eventbus仅用做回调的补充，在不便于使用回调的情况下使用eventbus
 * Created by leobert on 2016/5/5.
 */
public class AppEvent {

    public static class TriggerHolderEvent<T extends ITriggerCompare> {
        protected T trigger;

        public T getTrigger() {
            return trigger;
        }

        public void setTrigger(T trigger) {
            this.trigger = trigger;
        }
    }

    /**
     * 登录成功事件
     */
    public static class LoginSuccessEvent extends TriggerHolderEvent {
        private UserBean userBean;

        public LoginSuccessEvent(UserBean userBean) {
            this.userBean = userBean;
        }

        public UserBean getUserBean() {
            return userBean;
        }
    }



    /**
     * 手动关闭登录页事件
     */
    public static class LoginCancelEvent extends TriggerHolderEvent {
    }

    /**
     * 正常登录事件链被打断事件
     */
    public static class LoginInterruptedEvent {
    }

    public static class LogoutEvent {
    }
}
