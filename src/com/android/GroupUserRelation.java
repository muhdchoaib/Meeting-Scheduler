package com.android;

public class GroupUserRelation {

	private long iD;
	private long userID;
	private long groupID;

	public GroupUserRelation(long id, long userid, long groupid)
	{
		this.iD = id;
		this.userID = userid;
		this.groupID = groupid;
	}
	
	public GroupUserRelation()
	{}
	public long getiD() {
		return iD;
	}
	public void setiD(long iD) {
		this.iD = iD;
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public long getGroupID() {
		return groupID;
	}
	public void setGroupID(long groupID) {
		this.groupID = groupID;
	}
	
	
}
