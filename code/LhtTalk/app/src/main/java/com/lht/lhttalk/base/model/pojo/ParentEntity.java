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

package com.lht.lhttalk.base.model.pojo;

import java.util.ArrayList;

public class ParentEntity {

	private String groupName;

	private ArrayList<ChildEntity> childs;


	public String getGroupName() {
		return groupName;
	}

	public ArrayList<ChildEntity> getChilds() {
		return childs;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setChilds(ArrayList<ChildEntity> childs) {
		this.childs = childs;
	}

	public static class ChildEntity {

        private String groupName;

        private ArrayList<String> childNames;

        public String getGroupName() {
            return groupName;
        }

        public ArrayList<String> getChildNames() {
            return childNames;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public void setChildNames(ArrayList<String> childNames) {
            this.childNames = childNames;
        }

    }
}
