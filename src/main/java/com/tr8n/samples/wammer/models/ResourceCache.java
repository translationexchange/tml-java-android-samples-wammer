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

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ResourceCache {

	private static ResourceCache instance;
	
	private Map<String, Object> cache;
	
	public ResourceCache() {
		cache = new HashMap<String, Object>();
	}
	
	public static ResourceCache getInstance() {
		if (instance == null) 
			instance = new ResourceCache();
		return instance;
	}
	
	public Map<String, Object> getCache() {
		return cache;
	}

	public Object fetch(String key) {
		return getCache().get(key);
	}

	public void store(String key, Object value) {
		getCache().put(key, value);
	}

	public void delete(String key) {
		getCache().remove(key);
	}
	
	public static void reset() {
		instance = null;
	}
	
	public static Bitmap getImage(Context context, String name) {
		if (getInstance().fetch(name) == null) {
		    try {
		        InputStream path = context.getAssets().open("images/" + name);
		        getInstance().store(name, BitmapFactory.decodeStream(path));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }        
		}
		return (Bitmap) getInstance().fetch(name);
	}
	
}
