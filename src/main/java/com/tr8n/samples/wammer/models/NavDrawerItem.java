/*
 *  Copyright (c) 2014 Michael Berkovich, http://tr8nhub.com All rights reserved.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package com.tr8n.samples.wammer.models;

import com.tr8n.android.Tr8n;

import android.app.Fragment;

public class NavDrawerItem {
	
	private String title;
	private String fragmentClass;
	
	public NavDrawerItem(){}

	public NavDrawerItem(String title) {
		this.title = title;
	}

	public NavDrawerItem(String title, String fragmentClass) {
		this.title = title;
		this.fragmentClass = fragmentClass;
	}

	public NavDrawerItem(String title, int icon){
		this.title = title;
	}
		
	public String getTitle(){
		return this.title;
	}

	public String getTranslatedTitle(){
		return Tr8n.translate(getTitle());
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public Fragment getFragment() {
		if (fragmentClass == null)
			return null;
		
		try {
			return (Fragment) Class.forName(fragmentClass).getConstructor().newInstance();
		} catch (Exception ex) {
			return null;
		}
	}
}
