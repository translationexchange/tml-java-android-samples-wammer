package com.tr8n.samples.wammer.models.stories;

import android.text.Spannable;

import com.tr8n.android.Tr8n;
import com.tr8n.core.Utils;
import com.tr8n.samples.wammer.models.NewsfeedStory;
import com.tr8n.samples.wammer.models.User;

public class GroupJoinStory extends NewsfeedStory {
	
	String group;
	
	public GroupJoinStory(User actor, String group) {
		super(actor);
		setGroup(group);
	}
	
	@Override
	public Spannable generateStory() {
		return Tr8n.translateSpannableString("[link: {actor}] joined [bold: {group}] group.", Utils.buildMap(
				"actor", getActor(),
				"group", getGroup(),
				"link", Utils.buildMap("color", "blue"),
				"bold", Utils.buildMap("style", "bold")
		));
	}

	protected String getGroup() {
		return group;
	}

	protected void setGroup(String group) {
		this.group = group;
	}

	
}
