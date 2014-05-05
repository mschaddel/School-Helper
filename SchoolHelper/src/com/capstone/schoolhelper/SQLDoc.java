package com.capstone.schoolhelper;

public class SQLDoc {
	int doc_id;
	long class_id;
	String doc_name;

	public SQLDoc() {

	}

	public SQLDoc(long class_id, String doc_name) {
		this.class_id = class_id;
		this.doc_name = doc_name;
	}

	public SQLDoc(int doc_id, long class_id, String doc_name) {
		this.doc_id = doc_id;
		this.class_id = class_id;
		this.doc_name = doc_name;
	}

	public long getdocid() {
		return this.doc_id;
	}

	public void setdocid(int doc_id) {
		this.doc_id = doc_id;
	}

	public long getclassid() {
		return this.class_id;
	}

	public void setclassid(long class_id) {
		this.class_id = class_id;
	}

	public String getdocname() {
		return this.doc_name;
	}

	public void setdocname(String doc_name) {
		this.doc_name = doc_name;
	}
}
