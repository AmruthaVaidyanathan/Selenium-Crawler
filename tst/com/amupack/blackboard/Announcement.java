package com.amupack.blackboard;

public class Announcement {
	String postedOn;
	String postedBy;
	String content;
	
	Announcement(String postedOn, String postedBy, String content) {
		this.postedOn = postedOn;
		this.postedBy = postedBy;
		this.content = content;
	}
	
	public String postedOn() {
		return postedOn;
	}
	
	public String postedBy() {
		return postedBy;
	}
	
	public String content() {
		return content;
	}
	
	public String toString() {
		return content + " was posted on " + postedOn + " by " + postedBy;
	}
	
}
