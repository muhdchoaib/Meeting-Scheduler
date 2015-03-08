package com.android;

public class Group {

	private long ID;
	private long groupID;
	private String name;

	
	public Group(long id, String name)
	{
		this.groupID = id;
		this.name = name;
	}
	
	public Group()
	{}
	
	public long getGroupID() {
		return groupID;
	}
	public void setGroupID(long iD) {
		this.groupID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
