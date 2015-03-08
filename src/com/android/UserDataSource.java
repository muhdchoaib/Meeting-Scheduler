package com.android;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UserDataSource {

	/*   This is Users table handler. */	
	private String[] usersAllColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_USERID, MySQLiteHelper.COLUMN_USER_NAME, MySQLiteHelper.COLUMN_USER_ADDRESS };
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	
	public UserDataSource(SQLiteDatabase db)
	{
		database = db;
	}
	
	public UserDataSource ()
	{}
	
	public User createExternalUser(User user)
	{
		return createUser(user);
		
	}

	public UserDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
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
