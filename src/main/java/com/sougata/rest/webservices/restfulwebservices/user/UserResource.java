package com.sougata.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sougata.rest.webservices.restfulwebservices.exception.UserAlreadyExistsException;
import com.sougata.rest.webservices.restfulwebservices.exception.UserNotFoundException;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userDaoService;
	
	@GetMapping(path="/users")
	public List<User> retrieveAllUsers(){
		return userDaoService.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	public User retrieveOneUser(@PathVariable int id){
		User user = userDaoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		return user;
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<Object> createUser(@RequestBody User user){
		
		if(userDaoService.findOne(user.getId()) != null) {
			throw new UserAlreadyExistsException("Already exists, id:"+user.getId());
		}
		
		User savedUser = userDaoService.saveUser(user);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
		// return userDaoService.saveUser(user);
	}
	
	@DeleteMapping(path="/delete/{id}")
	public User deleteUser(@PathVariable int id){
		if(userDaoService.findOne(id) == null) {
			throw new UserNotFoundException("id-" + id);
		}
		return userDaoService.deleteOne(id);
	}

	@GetMapping(path="users/{id}/posts")
	public List<Post> retrieveAllPosts
		(@PathVariable int id){
			return userDaoService.getAllPost(id);
	}

	@PostMapping(path="/users/{id}/posts")
	public ResponseEntity<Object> createPost
		(@PathVariable int id, @RequestBody Post post){
		User user = userDaoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
		Post savedPost = userDaoService.savePost(id, post);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
			
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/users/{id}/posts")
	public ResponseEntity<Object> deletePost
		(@PathVariable int id, @RequestBody Post post){
		User user = userDaoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
		Post deletedPost = userDaoService.removePost(id, post);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(deletedPost.getId())
				.toUri();
			
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping(path="/users/{id}/posts/{postId}")
	public Post retrieveOnePost
		(@PathVariable int id, @PathVariable int postId) {
		User user = userDaoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		Post post = userDaoService.getPost(id, postId);
		if(post == null) {
			throw new UserNotFoundException("id-" + id);
		}
		return post;
	}

}
