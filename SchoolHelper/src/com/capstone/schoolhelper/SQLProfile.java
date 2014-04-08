package com.capstone.schoolhelper;

public class SQLProfile {

	String name;
	String email;
	String school;
	String mode;
	int notifications;
	
	public SQLProfile(){
		
	}
	
	public SQLProfile(String name, String email, String school, String mode, int notifications){
		this.name = name;
		this.email = email;
		this.school = school;
		this.mode = mode;
		this.notifications = notifications;
	}
	
	public String getname(){
		return name;
	}
	
	public void setname(String name){
		this.name = name;
	}
	
	public String getemail(){
		return this.email;
	}
	
	public void setemail(String email){
		this.email = email;
	}
	
	public String getschool(){
		return this.school;
	}
	
	public void setschool(String school){
		this.school = school;
	}
	
	public String getmode(){
		return this.mode;
	}
	
	public void setmode(String mode){
		this.mode = mode;
	}
	
	public int getnotifications(){
		return this.notifications;
	}
	
	public void setnotifications(int notifications){
		this.notifications = notifications;
	}
}
