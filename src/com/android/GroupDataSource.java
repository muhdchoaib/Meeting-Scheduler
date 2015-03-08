package com.android;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class GroupDataSource {

	
	/*   This is Groups table handler. */	
	private String[] groupsAllColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_GROUP_ID, MySQLiteHelper.COLUMN_GROUP_NAME};
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	

	public GroupDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}
	
	public GroupDataSource()
	{}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	public Group createExternalGroup(Group group)
	{
		return createGroup(group);
	}

	public Group createGroup(Group group) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_GROUP_ID, group.getGroupID());
		values.put(MySQLiteHelper.COLUMN_GROUP_NAME, group.getName());
		
		long insertId = database.insert(MySQLiteHelper.TABLE_GROUPS, null,values);
		// To show how to query
		Cursor cursor = database.query(MySQLiteHelper.TABLE_GROUPS,
				groupsAllColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Group tempGroup = cursorToGroup(cursor);
		cursor.close();
		return tempGroup;
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

}
