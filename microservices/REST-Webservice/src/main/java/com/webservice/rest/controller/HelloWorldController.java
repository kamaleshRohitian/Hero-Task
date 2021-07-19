package com.webservice.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.rest.model.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	
	@GetMapping(value = "/hello")
	public String helloWorld()
	{
		return "Hello World!...";
	}
	
	@GetMapping(value = "/hello-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("HelloWorldBean!...");
	}
	
	@GetMapping(value = "/hello-bean/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name)
	{
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
	

}
