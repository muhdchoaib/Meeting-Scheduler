package com.android;

public class User {
	
	private long ID;
	private long userID;
	private String name;
	private String address;

	public User()
	{
		
		
	}
	public User(long id, String name, String address)
	{
		this.userID = id;
		this.address = address;
		this.name = name;
	}
	
	public long getUserID() {
		return userID;
	}
	public void setUseriD(long iD) {
		this.userID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	 @Override
	 public String toString() {
	        return this.name;
	 }
	 
	 
	

}
