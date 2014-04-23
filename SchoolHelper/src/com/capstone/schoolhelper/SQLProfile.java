package com.capstone.schoolhelper;

public class SQLProfile {

	int profile_id;
	String name;
	String email;
	String school;
	int notifications;
	String mode;
	
	public SQLProfile() {

	}

	public SQLProfile(String name, String email, String school,int notifications, String mode) {
		this.name = name;
		this.email = email;
		this.school = school;
		this.notifications = notifications;
		this.mode = mode;
	}
	
	public SQLProfile(int profileid, String name, String email, String school, int notifications, String mode){
		this.profile_id = profileid;
		this.name = name;
		this.email = email;
		this.school = school;
		this.notifications = notifications;
		this.mode = mode;
	}
	
	public long getprofileid(){
		return this.profile_id;
	}
	
	public void setprofileid(int profile_id){
		this.profile_id = profile_id;
	}
	
	public String getname(){
		return this.name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getemail() {
		return this.email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getschool() {
		return this.school;
	}

	public void setschool(String school) {
		this.school = school;
	}

	public String getmode() {
		return this.mode;
	}

	public void setmode(String mode) {
		this.mode = mode;
	}

	public int getnotifications() {
		return this.notifications;
	}

	public void setnotifications(int notifications) {
		this.notifications = notifications;
	}
}
