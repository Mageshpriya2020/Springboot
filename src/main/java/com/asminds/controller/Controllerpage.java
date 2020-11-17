package com.asminds.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asminds.entity.User;
import com.asminds.exception.UserNotFoundException;
import com.asminds.repository.UserRepository;

@Controller
@RestController
@RequestMapping("/userdata")
public class Controllerpage {

	@Autowired
	private UserRepository repository;
	
	Logger logger = LoggerFactory.getLogger(Controllerpage.class);//pls check it
	
	@RequestMapping("/home")
	public String home() {
		logger.info("welcome all");
		return "hello";
	}
	
	@PostMapping("/create")
	public User createuser(@Valid @RequestBody User u) {
		//logger.info("user created");
		logger.debug("inside controllerpage.createuser() method");
		return repository.save(u);
	}
	
	@GetMapping("/allusers")
	public List<User> getalluser(){
		return repository.findAll();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserbyId(@PathVariable(value = "id")int id)throws UserNotFoundException{
		
		User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found for this id :: "+ id));
		return ResponseEntity.ok().body(user);
		
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id")int id,@Valid @RequestBody User userdetail)throws UserNotFoundException {
	
		User u =repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found for this id ::" +id));
		u.setName(userdetail.getName());
		u.setEmail(userdetail.getEmail());
		final User updatedUser =repository.save(u);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/user/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int id)throws UserNotFoundException{
		
		User u = repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found for this id :: " +id));
		repository.delete(u);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
