package com.android;


import android.os.Parcel;
import android.os.Parcelable;

public class Slot implements Parcelable {


	private long iD;
	private long slotID;
	private String Name;
	private long dayID;
	private String status;
	
    public Slot (Parcel in)
   {
  //  Log.d (TAG, "parcel in");
        iD = in.readLong();
        slotID = in.readLong ();
        Name = in.readString();
        dayID = in.readLong();
        status = in.readString();
   }
    
    public Slot()
    {}


    public static final Parcelable.Creator<Slot> CREATOR
    = new Parcelable.Creator<Slot>() 
   {
         public Slot createFromParcel(Parcel in) 
         {
           // Log.d (TAG, "createFromParcel()");
             return new Slot(in);
         }

         public Slot[] newArray (int size) 
         {
           // Log.d (TAG, "createFromParcel() newArray ");
             return new Slot[size];
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
       dest.writeLong (iD);
       dest.writeLong (slotID);
       dest.writeString(Name);
       dest.writeLong(dayID);
       dest.writeString(status);
   }

	public long getSlotID() {
		return slotID;
	}
	public void setSlotID(long slotID) {
		this.slotID = slotID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public long getDayID() {
		return dayID;
	}
	public void setDayID(long dayID) {
		this.dayID = dayID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getiD() {
		return iD;
	}
	public void setiD(long iD) {
		this.iD = iD;
	}
	 @Override
	 public String toString() {
	        return this.Name;
	 }
	 
	 
	
	
}
