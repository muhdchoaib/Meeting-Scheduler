package com.android;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PrepareMessageActivity extends Activity{

    /** Called when the activity is first created. */
	private GroupUserRelationDataSource datasource;
	private List<GroupUserRelation> gurList;
	private List<User> users;
	private long selectedGroupID;
	private ArrayList<Message> msgList;	
	private ArrayList<TimeSlot> slotList;	
	private ArrayList<String> userList;	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preparemessage);

	//	gurList =  datasource.getGURList(0);
		
		@SuppressWarnings("unchecked")
		ArrayList<Message> messageList = getIntent().getParcelableArrayListExtra("msgList");
		msgList = messageList;
		slotList = getIntent().getParcelableArrayListExtra("slotList");
		userList = getIntent().getStringArrayListExtra("userList");

		
		
//		Toast.makeText(this, "ArrayList size = " + selectedSlots.size(), Toast.LENGTH_SHORT);
		
		
		datasource = new GroupUserRelationDataSource(this);
		datasource.open();

		users =  datasource.getGURList(0);

/*		if(selectedGroupID > -1)
			gurList = datasource.getGroupUserRelation(selectedGroupID);
		else
			Toast.makeText(this, "No selected group received " , Toast.LENGTH_SHORT);

		if(gurList.size() < 1 )
		{
			addNewGURs();
		}
*/
		
	}
	public void onClick(View view) {

		switch (view.getId()) 
		{
			case R.id.btnPrepareMsg:
				
				
				break;
			
			case R.id.btnSendMsg:
				// Deselect all slots on the list
				Toast.makeText(this, " You just cancelled all selections." , Toast.LENGTH_SHORT).show();
				break;
			case R.id.btnBack:
				// Deselect all slots on the list
				Toast.makeText(this, " You just cancelled all selections." , Toast.LENGTH_SHORT).show();
				break;
	
		}
	}

	public void addNewGURs()
	{
		List<Group> groups = datasource.getAllGroups();
		users = datasource.getAllUsers();
		
/*		if(users.size() < 1)
			addNewUsers();
		
		
		for(int k = 0; k < 2; k++)
		{
			for(int m = 0; m < 2; m++)
			{
				GroupUserRelation tempGUR = new GroupUserRelation();
				tempGUR.setGroupID(k);
				tempGUR.setUserID(m);
				
				GroupUserRelation resultGUR = datasource.createGroupUserRelation(tempGUR);
				
//				gurList.add(resultGUR);
				
			}
	
		}
		
		gurList = datasource.getGroupUserRelation(selectedGroupID);
		*/

	//	gurList = datasource.;
		
	}
	
	public void addNewUsers()
	{
		for(int k = 0; k < 6; k++)
		{
			User tempUser = new User();
			tempUser.setUseriD(k);
			tempUser.setName("User " + k);
		
		
			User resultUser=  datasource.createUser(tempUser);
			users.add(resultUser);
			
		}
		
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
