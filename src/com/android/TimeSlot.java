package com.android;

import android.os.Parcel;
import android.os.Parcelable;

public class TimeSlot implements Parcelable{
	
    private long fromHour;
    private long fromMinute;

    private long toHour;
    private long toMinute;

    
    private long mYear;
    private long mMonth;
    private long mDay;
    
    public TimeSlot()
    {
    	
    }
    
    public TimeSlot (Parcel in)
   {
  //  Log.d (TAG, "parcel in");
        fromHour = in.readLong();
        fromMinute = in.readLong ();
        toHour = in.readLong();
        toMinute = in.readLong();
        mYear = in.readLong();
        mMonth = in.readLong();
        mDay = in.readLong();

   }
    
    
    public static final Parcelable.Creator<TimeSlot> CREATOR
    = new Parcelable.Creator<TimeSlot>() 
   {
         public TimeSlot createFromParcel(Parcel in) 
         {
           // Log.d (TAG, "createFromParcel()");
             return new TimeSlot(in);
         }

         public TimeSlot[] newArray (int size) 
         {
           // Log.d (TAG, "createFromParcel() newArray ");
             return new TimeSlot[size];
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
       dest.writeLong (fromHour);
       dest.writeLong (fromMinute);
       dest.writeLong (toHour);
       dest.writeLong (toMinute);
       dest.writeLong (mYear);
       dest.writeLong (mMonth);
       dest.writeLong (mDay);
       
   }


    
	public long getFromHour() {
		return fromHour;
	}

	public void setFromHour(long fromHour) {
		this.fromHour = fromHour;
	}

	public long getFromMinute() {
		return fromMinute;
	}

	public void setFromMinute(long fromMinute) {
		this.fromMinute = fromMinute;
	}

	public long getToHour() {
		return toHour;
	}

	public void setToHour(long toHour) {
		this.toHour = toHour;
	}

	public long getToMinute() {
		return toMinute;
	}

	public void setToMinute(long toMinute) {
		this.toMinute = toMinute;
	}

	public long getmYear() {
		return mYear;
	}

	public void setmYear(long mYear) {
		this.mYear = mYear;
	}

	public long getmMonth() {
		return mMonth;
	}

	public void setmMonth(long mMonth) {
		this.mMonth = mMonth;
	}

	public long getmDay() {
		return mDay;
	}

	public void setmDay(long mDay) {
		this.mDay = mDay;
	}
    
    


}
