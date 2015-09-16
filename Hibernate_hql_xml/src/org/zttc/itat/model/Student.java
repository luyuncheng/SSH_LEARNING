package org.zttc.itat.model;

public class Student {
	private int id;
	private String name;
	private String sex;
	private int version;
	private Classroom classroom;
	public Student() {
	}
	public Student(String name, String sex, Classroom classroom) {
		this.name = name;
		this.sex = sex;
		this.classroom = classroom;
	}
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	
}
