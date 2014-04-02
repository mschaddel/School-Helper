package com.capstone.schoolhelper;

public class SQLClass {
	String class_name;
	String professor;
	String location;
	String class_time;
	String documents;
	
	public SQLClass(){
		
	}
	
	public SQLClass(String class_name, String professor, String location, String class_time, String documents){
		this.class_name = class_name;
		this.professor = professor;
		this.location = location;
		this.class_time = class_time;
		this.documents = documents;
		
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
	
	public String getclassdocuments(){
		return this.documents;
	}
	
	public void setclassdocuments(String documents){
		this.documents = documents;
	}
}
