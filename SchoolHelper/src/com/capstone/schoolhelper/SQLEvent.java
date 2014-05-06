package com.capstone.schoolhelper;

public class SQLEvent {
	int event_id;
	String event_name;
	String class_name;
	String event_location;
	String event_date;
	String event_time;
	String description;
	
	public SQLEvent(){
		
	}
	
	public SQLEvent(String event_name, String class_name, String event_location, String event_date,String event_time, String description){
		this.event_name = event_name;
		this.class_name = class_name;
		this.event_location = event_location;
		this.event_date = event_date;
		this.event_time = event_time;
		this.description = description;
	}
	
	public SQLEvent(int event_id, String event_name, String class_name, String event_location, String event_date,String event_time, String description){
		this.event_id = event_id;
		this.event_name = event_name;
		this.class_name = class_name;
		this.event_location = event_location;
		this.event_date = event_date;
		this.event_time = event_time;
		this.description = description;
	}
	public long geteventid(){
		return this.event_id;
	}
	
	public void seteventid(int event_id){
		this.event_id = event_id;
	}
	
	public String geteventname(){
		return this.event_name;
	}
	
	public void seteventname(String event_name){
		this.event_name = event_name;
	}
	
	public String getclassname(){
		return this.class_name;
	}
	
	public void setclassname(String class_name){
		this.class_name = class_name;
	}
	
	public String geteventlocation(){
		return this.event_location;
	}
	
	public void seteventlocation(String event_location){
		this.event_location = event_location;
	}
	
	public String geteventdate(){
		return this.event_date;
	}
	
	public void seteventdate(String event_date){
		this.event_date = event_date;
	}
	
	public String geteventtime(){
		return this.event_time;
	}
	
	public void seteventtime(String event_time){
		this.event_time = event_time;
	}
	
	public String getdescription(){
		return this.description;
	}
	
	public void setdescription(String description){
		this.description = description;
	}
	
}
