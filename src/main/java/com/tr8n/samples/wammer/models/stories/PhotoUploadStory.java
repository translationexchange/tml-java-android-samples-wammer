package com.tr8n.samples.wammer.models.stories;

import android.text.Spannable;

import com.tr8n.android.Tr8n;
import com.tr8n.core.Utils;
import com.tr8n.samples.wammer.models.NewsfeedStory;
import com.tr8n.samples.wammer.models.User;

public class PhotoUploadStory extends NewsfeedStory {

	Integer count;
	
	public PhotoUploadStory(User actor, Integer count) {
		setActor(actor);
		setCount(count);
	}
	
	@Override
	public Spannable generateStory() {
		return Tr8n.translateSpannableString("[link: {actor}] uploaded [bold: {count||photo}] to {actor|his,her} photo album.", Utils.buildMap(
				"actor", getActor(),
				"count", getCount(),
				"link", Utils.buildMap("color", "blue"),
				"bold", Utils.buildMap("style", "bold")
		));
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	
}
