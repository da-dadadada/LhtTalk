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

package com.lht.lhttalk.base.keyback;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * <p><b>Package</b> com.lht.vsocyy.clazz.keyback
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> IKeyBackHandlerChain
 * <p><b>Description</b>: TODO
 * <p>Created by leobert on 2017/1/20.
 */

public interface IKeyBackHandlerChain {
    IKeyBackHandlerChain next(IKeyBackHandler nextHandler);

    boolean onBackPressed();

    class KeyBackHandlerChainImpl implements IKeyBackHandlerChain{
        ArrayList<IKeyBackHandler> keyBackHandlers;

        private KeyBackHandlerChainImpl() {
            keyBackHandlers = new ArrayList<>();
        }

        public static IKeyBackHandlerChain newInstance() {
            return new KeyBackHandlerChainImpl();
        }


        @Override
        public IKeyBackHandlerChain next(@NonNull IKeyBackHandler nextHandler) {
            keyBackHandlers.add(nextHandler);
            return this;
        }


        @Override
        public boolean onBackPressed() {
            for (int i = 0;i<keyBackHandlers.size();i++) {
                boolean hasHandled = keyBackHandlers.get(i).handle();
                if (hasHandled)
                    return true;
            }
            return false;
        }
    }
}
