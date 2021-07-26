package com.spring.hero.entity;

import lombok.*;


public class User {
    private String userid;
    private String upassword;
    private String uname;
    private String authToken;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public User(String userid, String upassword, String uname, String authToken) {
		super();
		this.userid = userid;
		this.upassword = upassword;
		this.uname = uname;
		this.authToken = authToken;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", upassword=" + upassword + ", uname=" + uname + ", authToken=" + authToken
				+ "]";
	}
    
    
}
