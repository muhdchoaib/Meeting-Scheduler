package com.android;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SlotsDataSource {

	// Database fields
	private static SQLiteDatabase database;
	private MySQLiteHelper dbHelper;


	public SlotsDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}


/*   This is Slots table handler. */	
	private String[] slotsAllColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_SLOTID, MySQLiteHelper.COLUMN_DAYID, 
			MySQLiteHelper.COLUMN_SLOTNAME , MySQLiteHelper.COLUMN_STATUS };

	public Slot createSlot(Slot slot) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_SLOTID, slot.getSlotID());
		//values.put("_slotid", slot.getSlotID());
		values.put(MySQLiteHelper.COLUMN_DAYID, slot.getDayID());
		values.put(MySQLiteHelper.COLUMN_SLOTNAME, slot.getName());
		values.put(MySQLiteHelper.COLUMN_STATUS, slot.getStatus());
		
		long insertId = database.insert(MySQLiteHelper.TABLE_SLOTS, null,values);
		// To show how to query
		Cursor cursor = database.query(MySQLiteHelper.TABLE_SLOTS,
				slotsAllColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Slot tempSlot = cursorToSlot(cursor);
		cursor.close();
		return tempSlot;
	}

/*	public void deleteComment(Comment comment) {
		long id = comment.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_SLOTS, MySQLiteHelper.COLUMN_SLOTID
				+ " = " + id, null);
	}*/

	public List<Slot> getAllSlots() {
		List<Slot> slots = new ArrayList<Slot>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_SLOTS,
				slotsAllColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Slot slot = cursorToSlot(cursor);
			slots.add(slot);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return slots;
	}

	private Slot cursorToSlot(Cursor cursor) {
		Slot slot = new Slot();
		slot.setiD(cursor.getLong(0));
		slot.setSlotID(cursor.getLong(1));
		slot.setDayID(cursor.getLong(2));
		slot.setName(cursor.getString(3));
		slot.setStatus(cursor.getString(4));
		return slot;
	}
	
	

/*	public void deleteComment(Comment comment) {
		long id = comment.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_SLOTS, MySQLiteHelper.COLUMN_SLOTID
				+ " = " + id, null);
	}*/

	
	
	
}