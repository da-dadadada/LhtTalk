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

package com.lht.lhttalk.base.presenter;

import com.lht.lhttalk.Event.AppEvent;
import com.lht.lhttalk.base.BasePresenter;
import com.lht.lhttalk.base.IVerifyHolder;
import com.lht.lhttalk.base.launcher.ITriggerCompare;
import com.lht.lhttalk.base.model.pojo.LoginInfo;

/**
 * <p><b>Package</b> com.lht.vsocyy.mvp.presenter
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> ABSVerifyNeedPresenter
 * <p><b>Description</b>: 该抽象类定义了登录相关的内容，需要区分登录未登录的页面的presenter需要继承之，
 * 登录状态更新部分在Activity、Fragment中实现，若确实出现了共同的处理，修改模板方法并在此处实现共同部分
 * <p/>
 * 注意，只有会触发登录的页面的P才有必要继承
 * <p/>
 * Created by leobert on 2016/5/5.
 */
public abstract class ABSVerifyNeedPresenter implements IVerifyHolder,BasePresenter{

    /**
     * desc: 页面接收到订阅事件后，调用presenter#identifyTrigger，执行逻辑，需要区分触发事件是不是登录事件
     *
     * @param trigger an interface to identify trigger,use equal(ITriggerCompare compare)
     */
    public abstract void identifyTrigger(ITriggerCompare trigger);

    /**
     * desc: 登录可能被用户手动取消，此时发出LoginCancelEvent，订阅的页面需要特殊处理<p>
     * 并不是所有的登录相关页面都需要处理该逻辑，所以#ABSVerifyNeedPresenter中进行空实现
     *
     * @param trigger an interface to identify trigger,use equal(ITriggerCompare compare)
     */
    public void identifyCanceledTrigger(ITriggerCompare trigger) {

    }

    @Override
    public void onEventMainThread(AppEvent.LoginSuccessEvent event) {
        //ignore
    }

    @Override
    public void onEventMainThread(AppEvent.LoginCancelEvent event) {
        //ignore
    }

    /**
     * desc: check if login
     *
     * @return true while login,false otherwise
     */
    protected final boolean isLogin() {
        return getLoginInfo().isLogin();
    }

    @Override
    public final LoginInfo getLoginInfo() {
        return mLoginInfo;
    }
}
