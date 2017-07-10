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

package com.lht.lhttalk.util.debug;

import android.util.Log;

import com.lht.lhttalk.BuildConfig;

/**
 * @ClassName: DLog
 * @Description: debug工具类
 * @date 2016年3月24日 上午9:41:23
 * 
 * @author leobert.lan
 * @version 1.0
 */
public class DLog {
	public static class Lmsg{}

	public static class Tmsg{}

	/**
	 * forceDebug:强制打印，设置为true则打包后也打印log
	 */
	public static final boolean forceDebug = false;

	public static void v(Class<?> clazz, String msg) {
		log(clazz, msg, LogLevel.v);
	}

	public static void v(Class<?> clazz, LogLocation locaion, String msg) {
		log(clazz, msg + locaion.toString(), LogLevel.v);
	}

	public static void d(Class<?> clazz, String msg) {
		log(clazz, msg, LogLevel.d);
	}

	public static void d(Class<?> clazz, LogLocation locaion, String msg) {
		log(clazz, msg + locaion.toString(), LogLevel.d);
	}

	public static void i(Class<?> clazz, String msg) {
		log(clazz, msg, LogLevel.i);
	}

	public static void i(Class<?> clazz, LogLocation locaion, String msg) {
		log(clazz, msg + locaion.toString(), LogLevel.i);
	}

	public static void w(Class<?> clazz, String msg) {
		log(clazz, msg, LogLevel.w);
	}

	public static void w(Class<?> clazz, LogLocation locaion, String msg) {
		log(clazz, msg + locaion.toString(), LogLevel.w);
	}

	public static void e(Class<?> clazz, String msg) {
		log(clazz, msg, LogLevel.e);
	}

	public static void e(Class<?> clazz, LogLocation locaion, String msg) {
		log(clazz, msg + locaion.toString(), LogLevel.e);
	}

	public static void wtf(Class<?> clazz, String msg) {
		log(clazz, msg, LogLevel.wtf);
	}

	public static void wtf(Class<?> clazz, LogLocation locaion, String msg) {
		log(clazz, msg + locaion.toString(), LogLevel.wtf);
	}

	private static void log(Class<?> clazz, String msg, LogLevel level) {

		if (BuildConfig.DEBUG | forceDebug) {
			switch (level) {
			case v:
				Log.v(clazz.getSimpleName(), msg);
				break;
			case d:
				Log.d(clazz.getSimpleName(), msg);
				break;
			case i:
				Log.i(clazz.getSimpleName(), msg);
				break;
			case w:
				Log.w(clazz.getSimpleName(), msg);
				break;
			case e:
				Log.e(clazz.getSimpleName(), msg);
				break;
			case wtf:
				Log.wtf(clazz.getSimpleName(), msg);
				break;

			default:
				break;
			}
		}

	}

	enum LogLevel {
		v, d, i, w, e, wtf;
	}

	public static class LogLocation extends Exception {
		/**
		 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
		 */
		private static final long serialVersionUID = 1L;

		public int line() {
			StackTraceElement[] trace = getStackTrace();
			if (trace == null || trace.length == 0) {
				return -1;
			}
			return trace[0].getLineNumber();
		}

		public String fun() {
			StackTraceElement[] trace = getStackTrace();
			if (trace == null || trace.length == 0) {
				return "";
			}
			return trace[0].getMethodName();
		}

		public LogLocation() {
			super();
		}

		@Override
		public String toString() {
			return "(logged at:" + line() + "  function:" + fun()
					+ ")";
		}
	}
}
