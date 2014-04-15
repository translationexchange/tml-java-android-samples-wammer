package com.tr8n.samples.wammer.models.stories;

import android.text.Spannable;

import com.tr8n.android.Tr8n;
import com.tr8n.core.Utils;
import com.tr8n.samples.wammer.models.NewsfeedStory;
import com.tr8n.samples.wammer.models.User;

public class PhotoCommentStory extends NewsfeedStory {
	
	public PhotoCommentStory(User actor, User target) {
		super(actor, target);
	}
	
	@Override
	public Spannable generateStory() {
		return Tr8n.translateSpannableString("[link: {actor}] left a comment for [link: {target::pos}] photo.", Utils.buildMap(
				"actor", getActor(),
				"target", getTarget(),
				"link", Utils.buildMap("color", "blue")
		));
	}
	
}
