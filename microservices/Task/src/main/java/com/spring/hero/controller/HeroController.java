package com.spring.hero.controller;

import com.spring.hero.entity.Hero;
import com.spring.hero.entity.User;
import com.spring.hero.entity.UserDetails;
import com.spring.hero.repository.UserRepository;
import com.spring.hero.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.security.Principal;
import java.util.*;

@RestController
//@CrossOrigin(origins="http://localhost:4200")
public class HeroController  {

    @Autowired
    private HeroService service;
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping(value = "/")
    public String login()
    {
    	return "verified";
    }

    @GetMapping(value = "/heros")
    public ResponseEntity<List<Hero>> findAll()
    {

        return service.findAll();
    }

    @GetMapping(value = "/heros/{id}")
    public ResponseEntity<Hero> findById(@PathVariable int id)
    {
        return service.findById(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Object> save(@Valid  @RequestBody Hero hero)
    {
        return service.save(hero);
    }


    @PutMapping(value ="/update")
    public ResponseEntity<Object> update(@Valid @RequestBody Hero hero)
    {
        return service.update(hero);
    }


    @DeleteMapping(value = "/heros/remove/{id}")
    public ResponseEntity<Hero> deleteHero(@PathVariable int id)
    {
        return service.deleteHero(id);
    }

    // login validation

   /* @PostMapping(value = "/login")
    public String success(@RequestBody User user, HttpSession session)
    {
        return service.validate(user,session);
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
    @PostMapping("/adduser")
    public String AddUser(@RequestBody User user,HttpSession session)
    {
        return service.AddUser(user,session);
    }*/
    
    @PostMapping("/adduser")
    public String addUser(@RequestBody UserDetails userDetails)
    {
    	return service.addUser(userDetails);
    }
    
    @GetMapping("/user")
    public String getUserDetailsAfterLogin(Principal UserDetails)
    {
    	List<UserDetails> user = userRepository.findByUsername(UserDetails.getName());
    	if(user.size()>0)
    	{
    		return user.get(0).getUsername();
        }
		return "no datas";
    	
    }

}
