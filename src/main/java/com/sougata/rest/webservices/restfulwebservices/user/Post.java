package com.sougata.rest.webservices.restfulwebservices.user;

public class Post {
	int id;
	String content;
	public Post(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + "]";
	}
	
}
