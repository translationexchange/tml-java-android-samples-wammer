package com.tr8n.samples.wammer.models.stories;

import android.text.Spannable;

import com.tr8n.android.Tr8n;
import com.tr8n.core.Utils;
import com.tr8n.samples.wammer.models.NewsfeedStory;
import com.tr8n.samples.wammer.models.User;

public class PhotoTagStory extends NewsfeedStory {
	
	User owner; 
	
	public PhotoTagStory(User actor, User target, User owner) {
		super(actor, target);
		setOwner(owner);
	}
	
	@Override
	public Spannable generateStory() {
		return Tr8n.translateSpannableString("[link: {actor}] tagged [link: {target}] in [link: {owner::pos}] photo.", Utils.buildMap(
				"actor", getActor(),
				"target", getTarget(),
				"owner", getOwner(),
				"link", Utils.buildMap("color", "blue")
		));
	}

	protected User getOwner() {
		return owner;
	}

	protected void setOwner(User owner) {
		this.owner = owner;
	}
	
	
	
	
}
