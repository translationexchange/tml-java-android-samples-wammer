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

package com.tr8n.samples.wammer.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tr8n.samples.wammer.R;
import com.tr8n.samples.wammer.models.NewsfeedStory;
import com.tr8n.samples.wammer.models.ResourceCache;

public class NewsfeedListAdapter extends BaseAdapter {
	
	private Context context;
	private List<NewsfeedStory> stories;
	
	public NewsfeedListAdapter(Context context, List<NewsfeedStory> stories){
		this.context = context;
		this.stories = stories;
	}

	public int getCount() {
		return stories.size();
	}

	public Object getItem(int position) {		
		return stories.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.newsfeed_list_item, null);
        }
         
		convertView.setMinimumHeight(150);
		NewsfeedStory story = stories.get(position);
		
        TextView txtTitle = (TextView) convertView.findViewById(R.id.textview_story_title);
        txtTitle.setText(story.getStory());
        
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageview_story_photo);
        imageView.setImageBitmap(ResourceCache.getImage(context, story.getActorImageName() + ".png"));
        return convertView;
	}

}
