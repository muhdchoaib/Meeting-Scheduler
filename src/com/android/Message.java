package com.android;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Message implements Parcelable 
{
	private long messageID;
	private long coordinatorID;
	private long senderID;
	private long recipientGroupID;
	private ArrayList<String> usersIDList ;
	private ArrayList<TimeSlot> slotList;
	
	public Message()
	{
		
	}

	
	   public Message (Parcel in)
	   {
	  //  Log.d (TAG, "parcel in");
	        messageID = in.readLong();
	        coordinatorID = in.readLong ();
	        senderID = in.readLong ();
	        recipientGroupID = in.readLong ();

	        in.readList(usersIDList, null);
	        in.readList(slotList, null);

	   }
	    

	    public static final Parcelable.Creator<Message> CREATOR
	    = new Parcelable.Creator<Message>() 
	   {
	         public Message createFromParcel(Parcel in) 
	         {
	           // Log.d (TAG, "createFromParcel()");
	             return new Message(in);
	         }

	         public Message[] newArray (int size) 
	         {
	           // Log.d (TAG, "createFromParcel() newArray ");
	             return new Message[size];
	         }
	    };

	    public int describeContents ()
	   {
	        //Log.d (TAG, "describe()");
	       return 0;
	   }

	    public void writeToParcel (Parcel dest, int flags)
	   {
	     //   Log.d (TAG, "writeToParcel");
	       dest.writeLong (messageID);
	       dest.writeLong (coordinatorID);
	       dest.writeLong(senderID);
	       dest.writeLong(recipientGroupID);
	       dest.writeList(usersIDList);
	       dest.writeList(slotList);
	   }

	public long getMessageID() {
		return messageID;
	}

	public void setMessageID(long messageID) {
		this.messageID = messageID;
	}

	public long getRecipientGroupID() {
		return recipientGroupID;
	}

	public void setRecipientGroupID(long recipientGroupID) {
		this.recipientGroupID = recipientGroupID;
	}

	public long getCoordinatorID() {
		return coordinatorID;
	}

	public void setCoordinatorID(long coordinatorID) {
		this.coordinatorID = coordinatorID;
	}

	public long getSenderID() {
		return senderID;
	}

	public void setSenderID(long senderID) {
		this.senderID = senderID;
	}

	public ArrayList<String> getUsersIDList() {
		return usersIDList;
	}

	public void setUsersIDList(ArrayList<String> usersIDList) {
		this.usersIDList = usersIDList;
	}

	public ArrayList<TimeSlot> getSlotList() {
		return slotList;
	}

	public void setSlotList(ArrayList<TimeSlot> slotList) {
		this.slotList = slotList;
	}
	
	
}
