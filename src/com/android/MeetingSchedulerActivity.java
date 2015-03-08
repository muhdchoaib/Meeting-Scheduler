package com.android;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MeetingSchedulerActivity extends Activity implements
AdapterView.OnItemSelectedListener{
	
	private TextView mTimeDisplay;
    private Button btnAdd;
    private View view;
    private LinearLayout ll;
    private AlertDialog.Builder builder ;
    Button imageButton;
	private GroupDataSource datasource;
	private List<Group> groups;

    
    private EditText editTextDate;
    private EditText editTextFromTime;
    private EditText editTextToTime;

    private int fromHour;
    private int fromMinute;

    private int toHour;
    private int toMinute;

    
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 0;


    static final int FROM_TIME_DIALOG_ID = 1;
    static final int TO_TIME_DIALOG_ID = 2;
    public static int SLOT_ID = 1;  

	TextView selection;
	String[] items = { "this", "is", "a", "really", 
			"really2", "really3",
			"really4", "really5", "silly", "list" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


		datasource = new GroupDataSource(this);
		datasource.open();
		
		groups = datasource.getAllGroups();
		
		if(groups.size() < 1)
		{
	        PopulateDatabase popDB = new PopulateDatabase(this);
	        popDB.populateBasicData();
		}

        

        editTextDate = (EditText) findViewById(R.id.editText_Date);
        editTextDate.setOnClickListener(new View.OnClickListener() {
         //   @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }

        });
        
        editTextFromTime = (EditText) findViewById(R.id.editText_FromTime);
        editTextFromTime.setOnClickListener(new View.OnClickListener() {
         //   @Override
            public void onClick(View v) {
                showDialog(FROM_TIME_DIALOG_ID);
            }

        });

        
        editTextToTime = (EditText) findViewById(R.id.editText_ToTime);
        editTextToTime.setOnClickListener(new View.OnClickListener() {
         //   @Override
            public void onClick(View v) {
                showDialog(TO_TIME_DIALOG_ID);
            }

        });


        builder = new AlertDialog.Builder(this);
        // get the current time
        final Calendar c = Calendar.getInstance();
        fromHour = c.get(Calendar.HOUR_OF_DAY);
        fromMinute = c.get(Calendar.MINUTE);
        toHour = fromHour;
        toMinute = fromMinute;

        final Calendar dateCalendar = Calendar.getInstance();
        mYear = dateCalendar.get(Calendar.YEAR);
        mMonth = dateCalendar.get(Calendar.MONTH);
        mDay = dateCalendar.get(Calendar.DAY_OF_MONTH);

        // display the current date
        updateDateDisplay();
        updateFromTimeDisplay();
        updateToTimeDisplay();
        

        
//        LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        
 
        
        
 /*       EditText editText = new EditText(this);
        editText.setLayoutParams(lparams);
        editText.setText("Edit Text");
*/
       // TextView tv=new TextView(this);
       // tv.setLayoutParams(lparams);
        //tv.setText("test");
        
//        LinearLayout linerLayout = (LinearLayout) findViewById(R.id.linLayout);
 //       linerLayout.addView(editText);
        //this.linLayout.addView(tv);
        
        btnAdd = (Button) findViewById(R.id.button_Add);

        // add a click listener to the button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addNewTimeSlot(SLOT_ID);
                SLOT_ID++;
            }
        });
   
        
/*    	imageButton = (Button) findViewById(R.layout.row);
    	 
		imageButton.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
 
				Toast.makeText(TestProjectActivity.this,
					"ImageButton (selector) is clicked!",
					Toast.LENGTH_SHORT).show();
 
			}
 
		});
*/ 
        
        //ViewGroup parent = (ViewGroup) findViewById(R.id.layout_timing);

        //view =  LayoutInflater.from(getBaseContext()).inflate(R.layout.row, parent, false);
        //parent.addView(view);
    }
    
    public class MyOnItemSelectedListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
            View view, int pos, long id) {
         // Toast.makeText(parent.getContext(), "The planet is " +
          //    parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
        	
            final CharSequence[] items = {"Red", "Green", "Blue"};
            builder.setTitle("Pick a color");
/*            builder.setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                }
            });
*/            
            builder.setItems(items,null);
            AlertDialog alert = builder.create();
            alert.show();
        }

        public void onNothingSelected(AdapterView parent) {
          // Do nothing.
        }
    }
    
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		
//		ArrayAdapter<Slot> adapter = (ArrayAdapter<Slot>) getListAdapter();

		Slot slot = null;
		switch (view.getId()) 
		{
		
			case R.id.btnSelectSlots:
	
/*				ListView tempListView = getListView();
				int childCount =   getListView().getChildCount();
				int k=0;
				ArrayList<Slot> selectedSlots = new ArrayList<Slot>();
				for(k=0; k < childCount; k++)
				{
					if( ((CheckedTextView) getListView().getChildAt(k)).isChecked())
					{
						//Toast.makeText(this, getListView().getItemAtPosition(k).toString() +  " Selected " , Toast.LENGTH_SHORT).show();
						selectedSlots.add((Slot) getListView().getItemAtPosition(k));
					}
				}
				
				Toast.makeText(this, selectedSlots.size() +  " Selected " , Toast.LENGTH_SHORT).show();
*/	
//				Intent intent = new Intent(MeetingSchedulerActivity.this ,SelectGroupActivity.class);

	            // fill parceable and launch activity
	            Intent intent = new Intent().setClass(getBaseContext (), SelectGroupActivity.class);
	            
	            Message message = new Message();
	            
	            message.setCoordinatorID(0);
	            message.setMessageID(0);
	            message.setRecipientGroupID(0);
	            message.setSenderID(0);
	            
	            TimeSlot timeSlot = new TimeSlot();
	            
	            timeSlot.setFromHour(fromHour);
	            timeSlot.setFromMinute(fromMinute);
	            timeSlot.setToHour(toHour);
	            timeSlot.setToMinute(toMinute);
	            timeSlot.setmDay(mDay);
	            timeSlot.setmMonth(mMonth);
	            timeSlot.setmYear(mYear);
	            
	            
	            ArrayList<Message> messageList = new ArrayList<Message>();
	            
	            messageList.add(message);

				intent.putParcelableArrayListExtra("messageList", messageList);
				
	            ArrayList<TimeSlot> timeSlotList = new ArrayList<TimeSlot>();
	            timeSlotList.add(timeSlot);
//	            message.setSlotList(timeSlotList);

				intent.putParcelableArrayListExtra("slotList", timeSlotList);

				startActivity(intent);
	
//				Toast.makeText(this, selectedSlots.size() +  " Selected " , Toast.LENGTH_SHORT).show();
				break;
			
			case R.id.btnCancelSlotsSelection:
				// Deselect all slots on the list
				Toast.makeText(this, " You just cancelled all selections." , Toast.LENGTH_SHORT).show();
				break;
		}
//		adapter.notifyDataSetChanged();
	}

    public void addNewTimeSlot(int slotID)
    {
    	
        ll = (LinearLayout) findViewById(R.id.layout_timings);
//        view = LayoutInflater.from(getBaseContext()).inflate(R.layout.row,null);
//        imageButton = (Button) findViewById(R.id.button_Delete);
 //       imageButton.setOnClickListener(OnClickDoSomething(imageButton));
        
//        view.setId(slotID);
//        ll.addView(view);
    }
    
    View.OnClickListener OnClickDoSomething(final Button button)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                button.setText("text now set.. ");    
            }
        };
    }

	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		//selection.setText(items[position]);
	}

	public void onNothingSelected(AdapterView<?> parent) {
		//selection.setText("");
	}
    
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATE_DIALOG_ID:
            return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        case FROM_TIME_DIALOG_ID:
            return new TimePickerDialog(this,fromTimeSetListener, fromHour, fromMinute, false);

        case TO_TIME_DIALOG_ID:
            return new TimePickerDialog(this,toTimeSetListener, fromHour, fromMinute, false);


        }
        return null;
    }

    
 // updates the time we display in the TextView
    private void updateFromTimeDisplay() {
    	editTextFromTime.setText(
            new StringBuilder()
                    .append(padTime(fromHour)).append(":")
                    .append(padTime(fromMinute)));
    }
    
    // updates the time we display in the TextView
    private void updateToTimeDisplay() {
    	editTextToTime.setText(
            new StringBuilder()
                    .append(padTime(toHour)).append(":")
                    .append(padTime(toMinute)));
    }


    private static String padTime(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
    
 // the callback received when the user "sets" the time in the dialog
    private TimePickerDialog.OnTimeSetListener fromTimeSetListener =
        new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                fromHour = hourOfDay;
                fromMinute = minute;
                updateFromTimeDisplay();
            }
        };

        // the callback received when the user "sets" the time in the dialog
        private TimePickerDialog.OnTimeSetListener toTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    toHour = hourOfDay;
                    toMinute = minute;
                    updateToTimeDisplay();
                }
            };
     
         
        // updates the date in the TextView
        private void updateDateDisplay() {
        	editTextDate.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mMonth + 1).append("-")
                        .append(mDay).append("-")
                        .append(mYear).append(" "));
        }
        
        // the callback received when the user "sets" the date in the dialog
        private DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, 
                                          int monthOfYear, int dayOfMonth) {
                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;
                        updateDateDisplay();
                    }
                };
                
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
//            		intent.putExtra("selectedGroup", selectedGroup.getGroupID());
            		startActivity(intent);
            	 return true;

            	 case R.id.viewPendingRequests:
            			Intent intent2 = new Intent().setClass(getBaseContext (), MeetingSchedulerActivity.class);
//            			intent.putExtra("selectedGroup", selectedGroup.getGroupID());
            			startActivity(intent2);
            	 case R.id.viewConfirmedMeetings:
            			Intent intent3 = new Intent().setClass(getBaseContext (), MeetingSchedulerActivity.class);
//            			intent.putExtra("selectedGroup", selectedGroup.getGroupID());
            			startActivity(intent3);
            		 
            		 return true;
            		 
            	 }
            	 return false;
            	 }

}