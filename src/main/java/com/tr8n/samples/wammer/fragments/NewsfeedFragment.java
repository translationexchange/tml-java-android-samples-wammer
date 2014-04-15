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

package com.tr8n.samples.wammer.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tr8n.android.fragments.Tr8nFragment;
import com.tr8n.samples.wammer.R;
import com.tr8n.samples.wammer.adapters.NewsfeedListAdapter;
import com.tr8n.samples.wammer.models.NewsfeedStory;
import com.tr8n.samples.wammer.models.User;
import com.tr8n.samples.wammer.models.stories.DocumentUploadStory;
import com.tr8n.samples.wammer.models.stories.GroupJoinStory;
import com.tr8n.samples.wammer.models.stories.PhotoCommentStory;
import com.tr8n.samples.wammer.models.stories.PhotoTagStory;
import com.tr8n.samples.wammer.models.stories.PhotoUploadStory;

public class NewsfeedFragment extends Tr8nFragment {

	protected ListView newsfeedList;
	
	public NewsfeedFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        
        newsfeedList = (ListView) rootView.findViewById(R.id.listview_stories);
        newsfeedList.setAdapter(new NewsfeedListAdapter(getActivity().getApplicationContext(), getStories()));
        
        return rootView;
    }
	
	private List<NewsfeedStory> getStories() {
		User michael = new User("Michael");
		User george = new User("George");
		User ian = new User("Ian");
		User margaret = new User("Margaret", "female");
		
		List<NewsfeedStory> stories = new ArrayList<NewsfeedStory>();
		stories.add(new DocumentUploadStory(michael, 1));
		stories.add(new PhotoUploadStory(ian, 3));
		stories.add(new DocumentUploadStory(margaret, 2));
		stories.add(new PhotoUploadStory(george, 1));
		stories.add(new GroupJoinStory(ian, "UX"));
		stories.add(new PhotoCommentStory(george, ian));
		stories.add(new PhotoUploadStory(ian, 2));
		stories.add(new PhotoTagStory(margaret, ian, george));
		stories.add(new GroupJoinStory(michael, "Engineering"));
		stories.add(new GroupJoinStory(margaret, "Engineering"));
		stories.add(new PhotoUploadStory(michael, 1));
		
		return stories;
	}
	
	@Override
	public void onTr8nTranslate() {
		((NewsfeedListAdapter)newsfeedList.getAdapter()).notifyDataSetChanged();
	}
	
	@Override
	public void registerSources() {
		
	}
	
}
