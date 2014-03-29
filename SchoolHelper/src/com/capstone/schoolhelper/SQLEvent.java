package com.capstone.schoolhelper;

public class SQLEvent {
	String event_name;
	String event_date;
	String event_time;
	Long description;
	String event_documents;
	
	public SQLEvent(){
		
	}
	
	public SQLEvent(String event_name, String event_date, String event_time, Long description, String event_documents){
		this.event_name = event_name;
		this.event_date = event_date;
		this.event_time = event_time;
		this.description = description;
		this.event_documents = event_documents;
	}
	
	public String geteventname(){
		return this.event_name;
	}
	
	public void seteventname(String event_name){
		this.event_name = event_name;
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
	
	public Long getdescription(){
		return this.description;
	}
	
	public void setmode(Long description){
		this.description = description;
	}
	
	public String geteventdocuments(){
		return this.event_documents;
	}
	
	public void seteventdocuments(String event_documents){
		this.event_documents = event_documents;
	}
}
