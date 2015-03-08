package com.android;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BaseActivity extends ListActivity {


	public BaseActivity()
	{
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	 switch (item.getItemId()) {
	 case R.id.initiateMeeting:
		Intent intent = new Intent().setClass(getBaseContext (), MeetingSchedulerActivity.class);
//		intent.putExtra("selectedGroup", selectedGroup.getGroupID());
		startActivity(intent);
	 return true;

	 case R.id.viewPendingRequests:
			Intent intent2 = new Intent().setClass(getBaseContext (), MeetingSchedulerActivity.class);
//			intent.putExtra("selectedGroup", selectedGroup.getGroupID());
			startActivity(intent2);
	 case R.id.viewConfirmedMeetings:
			Intent intent3 = new Intent().setClass(getBaseContext (), MeetingSchedulerActivity.class);
//			intent.putExtra("selectedGroup", selectedGroup.getGroupID());
			startActivity(intent3);
		 
		 return true;
		 
	 }
	 return false;
	 }

}
