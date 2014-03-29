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
	
	public String getlocation(){
		return this.location;
	}
	
	public void setlocation(String location){
		this.location = location;
	}
	
	public String getclasstime(){
		return this.class_time;
	}
	
	public void setmode(String class_time){
		this.class_time = class_time;
	}
	
	public String getdocuments(){
		return this.documents;
	}
	
	public void setdocuments(String documents){
		this.documents = documents;
	}
}
