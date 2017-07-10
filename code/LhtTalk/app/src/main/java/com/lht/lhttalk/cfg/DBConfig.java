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

package com.lht.lhttalk.cfg;

/**
 * <p><b>Package</b> com.lht.vsocyy.interfaces.keys
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> DBConfig
 * <p><b>Description</b>: TODO
 * Created by leobert on 2016/8/16.
 */
public interface DBConfig {
    interface BasicDb {
        String DB_NAME = "basic.db";
    }

    interface AuthenticateDb {
        String DB_NAME = "authenticate.db";
    }

    interface SearchHistroyDb {
        /**
         * 保存搜索相关数据的数据库
         */
        String DB_NAME = "search.db";
    }

    interface BadgeNumberDb {
        /**
         * 保存数字角标的
         */
        String DB_NAME = "badge_number.db";
    }

}
