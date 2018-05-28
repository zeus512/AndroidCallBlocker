package com.example.blockcall;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import com.android.internal.telephony.ITelephony;

import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Blocker extends BroadcastReceiver {
	private static final int MODE_WORLD_READABLE = 1; 
	private ITelephony telephonyService;
	private String incommingNumber;
	private String incommingName=null;
	private SharedPreferences myPrefs; 
	
	Blockers bbm;
	
	
	TextView tt,t2;
	Calendar calander;
	SimpleDateFormat simpleDateFormat;
	String time;
 
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		
 /////////////////////////////////////////////////////////////
	    
	    
	    
	    Calendar calendar_r = Calendar.getInstance();
	    //calendar_r.set(Calendar.HOUR_OF_DAY, 0);
	    //calendar_r.set(Calendar.MINUTE, 0);
	   // calendar_r.set(Calendar.MILLISECOND, 0);      
	   // calendar_r.set(Calendar.SECOND, 0);
	    //System.out.println(new SimpleDateFormat("HH:mm:ss").format(calendar_r.getTime()));
	    
	    String rtimme=new SimpleDateFormat("HH:mm:ss").format(calendar_r.getTime());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		final String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		
		//String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()+28800000);
		
		//SimpleDateFormat simpleDateFormatArrivals = new SimpleDateFormat("HH:mm", Locale.UK);
	
		try {
		    String string1 = "09:00:00";
		    java.util.Date time1 = null;
			try {
				time1 = new SimpleDateFormat("HH:mm:ss").parse(string1);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    Calendar calendar1 = Calendar.getInstance();
		    calendar1.setTime(time1);

		    String string2 = "17:00:00";
		    java.util.Date time2 = null;
			try {
				time2 = new SimpleDateFormat("HH:mm:ss").parse(string2);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    Calendar calendar2 = Calendar.getInstance();
		    calendar2.setTime(time2);
		    calendar2.add(Calendar.DATE, 1);

		    String someRandomTime = rtimme;
		    java.util.Date d = null;
			try {
				d = new SimpleDateFormat("HH:mm:ss").parse(someRandomTime);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    Calendar calendar3 = Calendar.getInstance();
		    calendar3.setTime(d);
		    calendar3.add(Calendar.DATE, 1);

		    java.util.Date x = calendar3.getTime();
		    if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
		       
		        System.out.println(true);
		        //Toast.makeText(Blocker.this, "in time"+rtimme, 30).show();
		        
		        //
		        //
		        //
		        //
		        //
		        //............ Place your code here .......
		        bbm.status=true;
		        bbm.bb();
		   	 
		        myPrefs = context.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
				 String blockingMode=myPrefs.getString("mode", "not retrieved");
				 
				 
				 
		        if(!blockingMode.equals("cancel")) 
		        {
		 		Bundle bb = intent.getExtras();  
		 	  String state = bb.getString(TelephonyManager.EXTRA_STATE);
		 	  if ((state != null)&& (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)))     
		 	  {
		 		  incommingNumber = bb.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
		 		  if(blockingMode.equals("all"))
		 		  {
		 			  blockCall(context, bb);
		 		  }
		 		  else if(blockingMode.equals("unsaved"))
		 		  {
		 			  
		 		        incommingName=getContactDisplayNameByNumber(incommingNumber, context);
		 			   if((incommingName==null)||(incommingName.length()<=1)){
		 				   blockCall(context, bb);
		 			   }
		 		 }
		 		   
		 		  else if(blockingMode.equals("list"))
		 		  {
		 			  RemindersDbAdapter mDbAdapter=new RemindersDbAdapter(context);
		 			  mDbAdapter.open();
		 			  Cursor c= mDbAdapter.fetchAllReminders();  
		 				  if(c.moveToFirst())
		 				  {
		 			   
		 			        while (c.isAfterLast() == false) {  
		 			        String	title= c.getString(c.getColumnIndex(RemindersDbAdapter.KEY_TITLE)); 
		 			        	  if(title.equals(incommingNumber))      
		 			        	 {
		 			        		 
		 								blockCall(context, bb);
		 			        	 }
		 			        	  c.moveToNext();
		 			        } 
		 				  }
		 		     c.close();
		 		     mDbAdapter.close();
		 		  }
		 		  else
		 		  {
		 			  
		 		  }
		              
		  }
		 	  
		 	  
		 	  
		 	  
		 	  
		        }
		        //
		        //
		        //
		        //
		        //
		        //
		        //
		        //
		        //
		        //
		        //
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		    }else {
		    	//Toast.makeText(getApplicationContext(), "out time", 30).show();
		    	
		    	
		    
		    
				 
			}
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		
	///////////////////////////////////////////////////////////////////
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		 
		
	}
	public void blockCall(Context c, Bundle b)
	{
	    
      TelephonyManager telephony = (TelephonyManager) 
      c.getSystemService(Context.TELEPHONY_SERVICE);  
      try {
       Class cls = Class.forName(telephony.getClass().getName());
       Method m = cls.getDeclaredMethod("getITelephony");
       m.setAccessible(true);
       telephonyService = (ITelephony) m.invoke(telephony);
       //telephonyService.silenceRinger();
       telephonyService.endCall();
      } catch (Exception e) {
       e.printStackTrace();
      }
	 
	}
	public String getContactDisplayNameByNumber(String number, Context c) {

        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        String name = "?";
        String data=null;
        ContentResolver contentResolver =c.getContentResolver();
        Cursor contactLookup = contentResolver.query(uri, new String[] {BaseColumns._ID,
                ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null);

        try {
            if (contactLookup != null && contactLookup.getCount() > 0) {
                contactLookup.moveToNext();
                data = contactLookup.getString(contactLookup.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                //String contactId = contactLookup.getString(contactLookup.getColumnIndex(BaseColumns._ID));
            }
        } finally {
            if (contactLookup != null) {
                contactLookup.close();
               
            }
        }
        

        return data;
    }  
}
