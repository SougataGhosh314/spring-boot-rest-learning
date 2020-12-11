package com.sougata.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<User>();
	
	private static int usersCount = 3;
	
	static {
		users.add(new User(101, "Deadpool", new Date(), new ArrayList<Post>()));
		users.add(new User(102, "Batman", new Date(), new ArrayList<Post>()));
		users.add(new User(103, "HarleyQuin", new Date(), new ArrayList<Post>()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User saveUser(User user) {
		if(user.getId() == null) {
			user.setId(++usersCount+100);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user: users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteOne(int id) {
		for(User user: users) {
			if(user.getId() == id) {
				users.remove(user);
				return user;
			}
		}
		return null;
	}
	
	public Post savePost(int id, Post post) {
		int index = -1;
		
		for(int i=0; i<users.size(); i++) {
			if(id == users.get(i).getId()) {
				users.get(i).posts.add(post);
				index++;
			}
		}
		
		if(index == -1) {
			return null;
		}
		
		return post;
	}
	
	public Post removePost(int id, Post post) {
		int index = -1;
		
		for(int i=0; i<users.size(); i++) {
			if(id == users.get(i).getId()) {
				users.get(i).posts.remove(post);
				System.out.println("Removed post!!!");
				index++;
			}
		}
		
		if(index == -1) {
			return null;
		}
		
		return post;
	}
	
	public Post getPost(int id, int postId) {
		User ourUser = null;
		for(User user: users) {
			if(user.getId() == id) {
				ourUser = user;
			}
		}
		if(ourUser == null) {
			return null;
		}
		
		for(Post post: ourUser.posts) {
			if(postId == post.getId()) {
				return post;
			}
		}
		return null;
	}
	
	public List<Post> getAllPost(int id) {
		User ourUser = null;
		for(User user: users) {
			if(user.getId() == id) {
				ourUser = user;
			}
		}
		if(ourUser == null) {
			return null;
		}
		return ourUser.getPosts();
	}
	
}
