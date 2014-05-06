package com.capstone.schoolhelper;

public class SQLClass {
	int class_id;
	String class_name;
	String professor;
	String location;
	String class_time;
	String days;
	
	public SQLClass(){
		
	}
	
	public SQLClass(String class_name, String professor, String location, String class_time, String days){
		this.class_name = class_name;
		this.professor = professor;
		this.location = location;
		this.class_time = class_time;
		this.days = days;
	}
	
	public SQLClass(int class_id, String class_name, String professor, String location, String class_time, String days){
		this.class_id = class_id;
		this.class_name = class_name;
		this.professor = professor;
		this.location = location;
		this.class_time = class_time;
		this.days = days;
	}
	
	public long getclassid(){
		return this.class_id;
	}
	
	public void setclassid(int class_id){
		this.class_id = class_id;
	}
	
	public String getclassname(){
		return this.class_name;
	}
	
	public void setclassname(String class_name){
		this.class_name = class_name;
	}
	
	public String getprofessor(){
		return this.professor;
	}
	
	public void setprofessor(String professor){
		this.professor = professor;
	}
	
	public String getclasslocation(){
		return this.location;
	}
	
	public void setclasslocation(String location){
		this.location = location;
	}
	
	public String getclasstime(){
		return this.class_time;
	}
	
	public void setclasstime(String class_time){
		this.class_time = class_time;
	}
	
	public String getclassdays(){
		return this.days;
	}
	
	public void setclassdays(String days){
		this.days = days;
	}
	
	
	
}
