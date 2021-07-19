package com.webservice.rest.dao;

import java.util.*;

import org.springframework.stereotype.Component;

import com.webservice.rest.model.User;

@Component
public class UserDaoService {
	
	private static List<User> users=new ArrayList<User>();
	
	static{
		users.add(new User(1,"kamal",new Date()));
		users.add(new User(2,"tamil",new Date()));
		users.add(new User(3,"arul",new Date()));
		
	}
	
	private static int count=3;
	
	//Find all users
	public List<User> findAll()
	{
		return users;
	}
	
	//save user
	public User save(User user)
	{
         if(user.getId() == 0)
		  {
        	 user.setId(++count);
          }
         users.add(user);
         return user;
	}
	
	//find one
	public User findById(int id)
	{
		for(User u:users)
		{
			if(u.getId()==id)
			{
				return u;
			}
		}
		return null;
	}
	
	//delete  a user
	public List<User> deleteById(int id)
	{
	   Iterator<User> it=users.listIterator();
	   while(it.hasNext())
	   {
		   User user=it.next();
		   if(user.getId()==id)
		   {
			   it.remove();
			   count--;
			   return users;
		   }
	   }
	   return null;
	}
	
	// modify a user
	public User modifyUser(User user)
	{
		int id=user.getId();
		for(User u1:users)
		{
			if(u1.getId()==user.getId())
			{
				User u=findById(id);
				int index=users.indexOf(u);
				users.set(index, user);
				return user;
			}
		}
		return null;
		
	}

}
