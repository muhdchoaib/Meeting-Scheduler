package com.android;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

public class SelectGroupActivity extends ListActivity{
    /** Called when the activity is first created. */
	private GroupDataSource datasource;
	private List<Group> groups;
	private UserDataSource userDatasource;
	
	private Group selectedGroup;
    private AlertDialog.Builder builder ;
	private GroupUserRelationDataSource gurDatasource;
	private List<GroupUserRelation> gurList;
	private List<User> users;
	private long selectedGroupID;
	private ArrayList<Message> msgList;
	private ArrayList<TimeSlot> slotList; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectgroup);

		
		@SuppressWarnings("unchecked")
		ArrayList<Message> messageList = getIntent().getParcelableArrayListExtra("messageList");
		slotList = getIntent().getParcelableArrayListExtra("slotList");

		msgList = messageList;
		
		Toast.makeText(this, "ArrayList size = " + messageList.size(), Toast.LENGTH_SHORT);
	
		
		
		datasource = new GroupDataSource(this);
		datasource.open();
	
        builder = new AlertDialog.Builder(this);
		groups = datasource.getAllGroups();

		datasource.close();
		
//		userDatasource = new UserDataSource(this);
		
//		userDatasource.open();
		
//		users = userDatasource.getAllUsers();
		
//		userDatasource.close();
		gurDatasource = new GroupUserRelationDataSource(this);



		
/*		if(groups.size() < 1 )
			addNewGroups();
*/
		ArrayAdapter<Group> adapter = new ArrayAdapter<Group>(this,
				android.R.layout.simple_list_item_single_choice, groups);

	
		//	this.getListView().setChoiceMode(choiceMode)
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		setListAdapter(adapter);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		  super.onListItemClick(l, v, position, id);
		  
			if( ((CheckedTextView) getListView().getChildAt(position)).isChecked())
			{
				Toast.makeText(this, getListView().getItemAtPosition(position).toString() +  " Selected " , Toast.LENGTH_SHORT).show();
			}

			gurDatasource.open();
			
			selectedGroup = (Group) getListView().getItemAtPosition(position);
			
			selectedGroupID = selectedGroup.getGroupID();
			users =  gurDatasource.getGURList(selectedGroupID);

			List<String> listItems = new ArrayList<String>();
			
            for(User user: users)
            {
            	listItems.add(user.getName());
            }
            
			final CharSequence[] userItems = listItems.toArray(new CharSequence[listItems.size()]);

            builder.setTitle("Users List");
/*            builder.setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                }
            });
*/            
            builder.setItems(userItems,null);
            AlertDialog alert = builder.create();
            alert.show();
			

/*		  
		  if( (getListView().getItemAtPosition(position)).isSelected())
		  if(getListView().getisSelected())
			  Toast.makeText(this, " Selected " , Toast.LENGTH_SHORT).show();
		  else
			  Toast.makeText(this, " NOT Selected " , Toast.LENGTH_SHORT).show();
*/		}
	// Will be called via the onClick attribute
	// of the buttons in main.xml

	
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<Slot> adapter = (ArrayAdapter<Slot>) getListAdapter();

		Slot slot = null;
		switch (view.getId()) 
		{
			case R.id.btnNext:
				
				Message message = msgList.get(0);
				
				message.setRecipientGroupID(selectedGroupID);
				
				ArrayList<String> userIDList = new ArrayList<String>();
				for(User user : users)
				{
					long userID = user.getUserID();
					userIDList.add(String.valueOf(userID));
				}
				
//				message.setUsersIDList(userIDList);
				
				msgList.set(0, message);
				
				Intent intent = new Intent().setClass(getBaseContext (), PrepareMessageActivity.class);
				intent.putParcelableArrayListExtra("msgList", msgList);
				intent.putParcelableArrayListExtra("slotList", slotList);
				intent.putStringArrayListExtra("userList", userIDList);
				
				startActivity(intent);
	
//				Toast.makeText(this, selectedSlots.size() +  " Selected " , Toast.LENGTH_SHORT).show();
				
				break;
			
			case R.id.btnCancelSlotsSelection:
				// Deselect all slots on the list
				Toast.makeText(this, " You just cancelled all selections." , Toast.LENGTH_SHORT).show();
				break;
		}
		adapter.notifyDataSetChanged();
	}

	
	
	public void addNewGroups()
	{
		
		for(int k = 0; k < 6; k++)
		{
			Group tempGroup = new Group();
			tempGroup.setGroupID(k);
			tempGroup.setName("Group " + k);
		
		
			Group resultGroup=  datasource.createGroup(tempGroup);
			groups.add(resultGroup);
			
		}
		
		ArrayAdapter<Group> adapter = new ArrayAdapter<Group>(this,
					android.R.layout.simple_list_item_single_choice, groups);

		
		//	this.getListView().setChoiceMode(choiceMode)
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		setListAdapter(adapter);
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
