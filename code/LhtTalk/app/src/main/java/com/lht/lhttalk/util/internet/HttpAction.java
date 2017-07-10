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

package com.lht.lhttalk.util.internet;

import java.io.Serializable;

/**
 * <p><b>Package</b> com.lht.vsocyy.util.internet
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> HttpAction
 * <p><b>Description</b>: TODO
 * <p>Created by leobert on 2016/11/23.
 */

public enum HttpAction implements IHttpActionDebugger {
    UNSET("unset for debugger"),

    POST("post"),

    GET("get"),

    HEAD("head"),

    DELETE("delete"),

    PUT("put"),

    PATCH("patch");

    private final String action;

    HttpAction(String action) {
        this.action = action;
    }

    @Override
    public String getAction() {
        return "action is:"+action;
    }

    @Override
    public boolean equals(IHttpActionDebugger compare) {
        if (compare == null) {
            return false;
        }
        boolean b1 = compare.getClass().getName().equals(getClass().getName());
        boolean b2 = compare.getAction().equals(getAction());
        return b1 & b2;
    }

    @Override
    public Serializable getSerializable() {
        return this;
    }
}

interface IHttpActionDebugger {
    String getAction();

    /**
     * 对比是否是同一个,利用序列化
     * @param compare
     * @return
     */
    boolean equals(IHttpActionDebugger compare);

    Serializable getSerializable();
}
