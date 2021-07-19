package com.webservice.rest.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.rest.dao.UserDaoService;
import com.webservice.rest.exception.UserNotFoundException;
import com.webservice.rest.model.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService uds;
	
	@GetMapping(value="/users")
	public List<User> findAll()
	{
		return uds.findAll();
	}
	
	@PostMapping(value="/users/add")
	public User save( @Valid @RequestBody User user)
	{
		return uds.save(user);
	}
	
	@GetMapping(value="/users/{id}")
	public User findById(@PathVariable int id)throws UserNotFoundException
	{
		User user=uds.findById(id);
		if(user==null)
		{
			throw new UserNotFoundException(id+" is not available");
		}
		return user;
	}
	
	@DeleteMapping(value="/users/{id}")
	public List<User> deleteById(@PathVariable int id)throws UserNotFoundException
	{
		List<User> user=uds.deleteById(id);
		if(user==null)
		{
			throw new UserNotFoundException(id+" is not available");
		}
		return user;
	}
	
	@PutMapping(value="/users/update")
	public User modifyUser(@Valid @RequestBody User user)throws UserNotFoundException
	{
		User user1=uds.modifyUser(user);
		if(user1==null)
		{
			throw new UserNotFoundException(" Please enter the correct ID");
		}
		return user1;
	}
}
