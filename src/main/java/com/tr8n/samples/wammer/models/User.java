package com.tr8n.samples.wammer.models;

import java.util.Date;

public class User {
	public static final String GENDER_MALE = "male";
	public static final String GENDER_FEMALE = "female";
	
	String firstName;
	String lastName;
	Integer age;
	String gender;
	Date birthday;

	public User() {
		this("No Name");
	}
	
	public User(String firstName) {
		this(firstName, "male");
	}

	public User(String firstName, String gender) {
		setFirstName(firstName);
		setGender(gender);
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public boolean isMale() {
		return getGender().equals(GENDER_MALE);
	}
	public boolean isFemale() {
		return getGender().equals(GENDER_FEMALE);
	}
	public String getImageName() {
		return getFirstName().toLowerCase();
	}
	public String toString() {
		return getFirstName();
	}
	
}
