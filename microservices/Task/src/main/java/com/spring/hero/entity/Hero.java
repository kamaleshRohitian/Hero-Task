package com.spring.hero.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 3, message = "Should have at least 3 characters!...")
    private String hero;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHero() {
		return hero;
	}
	public void setHero(String hero) {
		this.hero = hero;
	}
	public Hero(int id, @Size(min = 3, message = "Should have at least 3 characters!...") String hero) {
		super();
		this.id = id;
		this.hero = hero;
	}
	public Hero() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Hero [id=" + id + ", hero=" + hero + "]";
	}
    
    
}
