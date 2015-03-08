package com.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_SLOTS = "slots";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_SLOTID = "slotid";
	public static final String COLUMN_DAYID = "dayid";

	public static final String COLUMN_SLOTNAME = "slotName";
	public static final String COLUMN_STATUS = "status";

	private static final String DATABASE_NAME = "slots13.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String CREATE_SLOTS_TABLE = "create table if not exists "
			+ TABLE_SLOTS + "( " 
			+ COLUMN_ID + " integer primary key autoincrement, " 
			+ COLUMN_SLOTID + " integer , " 
			+ COLUMN_DAYID + " integer , " 
			+ COLUMN_SLOTNAME 	+ " text not null," 
			+ COLUMN_STATUS + " text not null" +
		");";
	
	public static final String TABLE_USERS = "Users";
	public static final String COLUMN_USERID = "userID";
	public static final String COLUMN_USER_NAME = "Name";
	public static final String COLUMN_USER_ADDRESS = "Address";

	// Database creation sql statement
	private static final String CREATE_USERS_TABLE = "create table if not exists "
			+ TABLE_USERS + "( " 
			+ COLUMN_ID + " integer primary key autoincrement, " 
			+ COLUMN_USERID + " integer, " 
			+ COLUMN_USER_NAME + " text , " 
			+ COLUMN_USER_ADDRESS + " text "+ ");";

	public static final String TABLE_GROUPS = "Groups";
	public static final String COLUMN_GROUP_NAME = "Name";
	public static final String COLUMN_GROUP_ID = "GroupID";

	// Database creation sql statement
	private static final String CREATE_GROUPS_TABLE = "create table if not exists "
			+ TABLE_GROUPS + "( " 
			+ COLUMN_ID + " integer primary key autoincrement, " 
			+ COLUMN_GROUP_ID + " integer, " 
			+ COLUMN_GROUP_NAME + " text  "+ ");"; 

	
	/*
	 * CREATE TABLE ORDERS
			(Order_ID integer primary key,
			Order_Date datetime,
			Customer_SID integer references CUSTOMER(SID),
			Amount double);
	 */
	
	public static final String TABLE_GROUP_USER_RELATION = "GroupUserRelation";
	public static final String COLUMN_GURID = "id";
	public static final String COLUMN_GUR_USERID = "UserId";
	public static final String COLUMN_GUR_GROUPID= "GroupId";

	// Database creation sql statement
	private static final String CREATE_GROUP_USER_RELATION_TABLE = "create table if not exists "
			+ TABLE_GROUP_USER_RELATION + "( " 
			+ COLUMN_GURID + " integer primary key autoincrement, " 
			+ COLUMN_GUR_USERID + " integer REFERENCES " + TABLE_USERS + " (" + COLUMN_ID + "), "   
			+ COLUMN_GUR_GROUPID + " integer REFERENCES " + TABLE_GROUPS + " (" + COLUMN_ID + ") " 
			+ ");";

/*	public static final String VIEW_USERS = "ViewUsers";
	public static final String CREATE_VIEW_USERS =  "CREATE VIEW "+ VIEW_USERS +
    " AS SELECT "+ TABLE_USERS +"."+ COLUMN_USERID +" AS userID,"+
    " "+ TABLE_USERS +"."+ COLUMN_USER_NAME +","+
    " "+ TABLE_USERS +"."+ COLUMN_USER_ADDRESS +","+
    " "+ TABLE_USERS +"."+ COLUMN_USER_ADDRESS +","+
    " "+ TABLE_GROUPS +"."+ COLUMN_GROUPNAME +","+
    " FROM "+ TABLE_USERS +" JOIN "+ deptTable+
    " ON "+employeeTable+"."+colDept+" ="+deptTable+"."+colDeptID
    );
*/	
	
	public static final String TABLE_INBOX = "Inbox";
	public static final String COLUMN_INBOX_ID = "ID";
	public static final String COLUMN_MSG_ID = "MessageID";
	public static final String COLUMN_SENDER_ID = "SenderID";
	public static final String COLUMN_COORDINATOR_ID = "CoordinatorID";
	public static final String COLUMN_SLOTS = "Slots";              // Slots can be like this (8-9; 9-10; 10-11)
	public static final String COLUMN_MSG_DESC = "MessagDesc";

	// Database creation sql statement
	private static final String CREATE_INBOX_TABLE = "create table if not exists "
			+ TABLE_INBOX + "( " 
			+ COLUMN_INBOX_ID + " integer primary key autoincrement, " 
			+ COLUMN_MSG_ID + " integer , " 
			+ COLUMN_SENDER_ID + " integer , " 
			+ COLUMN_COORDINATOR_ID + " integer , " 
			+ COLUMN_GROUP_ID + " integer , " 
			+ COLUMN_SLOTS + " text , " 
			+ COLUMN_MSG_DESC + " text  " 
			+ ");";

	public static final String TABLE_OUTBOX = "Outbox";
	public static final String COLUMN_OUTBOX_ID = "ID";

	// Database creation sql statement
	private static final String CREATE_OUTBOX_TABLE = "create table if not exists "
			+ TABLE_OUTBOX + "( " 
			+ COLUMN_OUTBOX_ID + " integer primary key autoincrement, " 
			+ COLUMN_MSG_ID + " integer , " 
			+ COLUMN_SENDER_ID + " integer , " 
			+ COLUMN_COORDINATOR_ID + " integer , " 
			+ COLUMN_GROUP_ID + " integer , " 
			+ COLUMN_SLOTS + " text , " 
			+ COLUMN_MSG_DESC + " text  " 
			+ ");";

	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_SLOTS_TABLE);
		database.execSQL(CREATE_USERS_TABLE);
		database.execSQL(CREATE_GROUPS_TABLE);
		database.execSQL(CREATE_GROUP_USER_RELATION_TABLE);
		database.execSQL(CREATE_INBOX_TABLE);
		database.execSQL(CREATE_OUTBOX_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SLOTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP_USER_RELATION);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_INBOX);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_OUTBOX);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		onCreate(db);
	}

}
