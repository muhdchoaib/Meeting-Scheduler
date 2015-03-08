package com.android;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class GroupUserRelationDataSource {

	
	/*   This is GroupUserRelations table handler. */	
	private String[] gurAllColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_USERID, MySQLiteHelper.COLUMN_GROUP_ID};
	private String[] groupsAllColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_GROUP_ID, MySQLiteHelper.COLUMN_GROUP_NAME};
	private String[] usersAllColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_USERID, MySQLiteHelper.COLUMN_USER_NAME, MySQLiteHelper.COLUMN_USER_ADDRESS };


	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	

	public GroupUserRelationDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public GroupUserRelationDataSource(){}

	public void close() {
		dbHelper.close();
	}
	
	public GroupUserRelation createExternalGUR(GroupUserRelation gur)
	{
		return createGroupUserRelation(gur);
	}

	public GroupUserRelation createGroupUserRelation(GroupUserRelation gur) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_GROUP_ID, gur.getGroupID());
		values.put(MySQLiteHelper.COLUMN_USERID, gur.getUserID());
		
		long insertId = database.insert(MySQLiteHelper.TABLE_GROUP_USER_RELATION, null,values);
		// To show how to query
		Cursor cursor = database.query(MySQLiteHelper.TABLE_GROUP_USER_RELATION,
				gurAllColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		GroupUserRelation tempGroup = cursorToGroupUserRelation(cursor);
		cursor.close();
		return tempGroup;
	}

	public  List<User> getGURList(long groupID) {
		List<User> users = new ArrayList<User>();
		//Cursor cursor = database.rawQuery("Select id, UserId, GroupId from " + MySQLiteHelper.TABLE_GROUP_USER_RELATION + " where GroupId = " + groupID +" ; ", null);
		Cursor cursor = database.rawQuery("Select u.id, u.userID, u.Name, u.Address From "+ MySQLiteHelper.TABLE_USERS +" u where u.userID IN ( Select UserId from "+ MySQLiteHelper.TABLE_GROUP_USER_RELATION +" gur where gur.GroupId = "+ groupID + "); ", null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			User user = cursorToUser(cursor);
			users.add(user);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return users;
	}

	
	public List<GroupUserRelation> getAllGroupUserRelations() {
		List<GroupUserRelation> groups = new ArrayList<GroupUserRelation>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_GROUP_USER_RELATION,
				gurAllColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			GroupUserRelation gur = cursorToGroupUserRelation(cursor);
			groups.add(gur);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return groups;
	}
	
	public  List<GroupUserRelation> getGroupUserRelation(long groupID) {
		List<GroupUserRelation> groups = new ArrayList<GroupUserRelation>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_GROUP_USER_RELATION,
				gurAllColumns, MySQLiteHelper.COLUMN_ID + " = " + groupID, null,
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			GroupUserRelation gur = cursorToGroupUserRelation(cursor);
			groups.add(gur);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return groups;
	}

	private GroupUserRelation cursorToGroupUserRelation(Cursor cursor) {
		GroupUserRelation gur = new GroupUserRelation();
		gur.setiD(cursor.getLong(0));
		gur.setUserID(cursor.getLong(1));
		gur.setGroupID(cursor.getLong(2));

		return gur;
	}
	
	public List<Group> getAllGroups() {
		List<Group> groups = new ArrayList<Group>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_GROUPS,
				groupsAllColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Group group = cursorToGroup(cursor);
			groups.add(group);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return groups;
	}

	private Group cursorToGroup(Cursor cursor) {
		Group group = new Group();
		group.setID(cursor.getLong(0));
		group.setGroupID(cursor.getLong(1));
		group.setName(cursor.getString(2));

		return group;
	}
	
	public User createUser(User user) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_USERID, user.getUserID());
		values.put(MySQLiteHelper.COLUMN_USER_NAME, user.getName());
		values.put(MySQLiteHelper.COLUMN_USER_ADDRESS, user.getAddress());
		
		long insertId = database.insert(MySQLiteHelper.TABLE_USERS, null,values);
		// To show how to query
		Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS,
				usersAllColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		User tempUser = cursorToUser(cursor);
		cursor.close();
		return tempUser;
	}

	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS,
				usersAllColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			User user = cursorToUser(cursor);
			users.add(user);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return users;
	}

	private User cursorToUser(Cursor cursor) {
		User user = new User();
		user.setID(cursor.getLong(0));
		user.setUseriD(cursor.getLong(1));
		user.setName(cursor.getString(2));
		user.setAddress(cursor.getString(3));
		return user;
	}

}
