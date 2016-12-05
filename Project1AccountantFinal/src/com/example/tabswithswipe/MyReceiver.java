package com.example.tabswithswipe;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver
{

	NotificationManager nm;

	 @Override
	 public void onReceive(Context context, Intent intent) 
	 {
	  nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	  
	  String from = "ACCOUNTANT";
	  CharSequence message = "Daily Accountant Update";  
	  
	  Calendar c = Calendar.getInstance();
	  
      SimpleDateFormat df = new SimpleDateFormat("dd-M-yyyy");
      String formattedDate = df.format(c.getTime());
      
      String[] parts = formattedDate.split("-");
      String part1 = parts[0]; // 004
      String part2 = parts[1];
      String part3 = parts[2];
	  
      int dayOfMonth = Integer.parseInt(part1);
      int month = Integer.parseInt(part2);
      int year = Integer.parseInt(part3);
      
      	Intent i = new Intent(context,ParticularDateActivity.class);
		i.putExtra("dayOfMonth", dayOfMonth);
		i.putExtra("month",month);
		i.putExtra("year",year);
		
		i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		
		
	/*	Intent notificationIntent = new Intent(getApplicationContext(), viewmessage.class);
		notificationIntent.putExtra("NotificationMessage", notificationMessage);
		
		notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		PendingIntent pendingNotificationIntent = PendingIntent.getActivity(getApplicationContext(),notificationIndex,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(getApplicationContext(), notificationTitle, notificationMessage, pendingNotificationIntent);*/
		
		
		
		
		
	  
	  PendingIntent contentIntent = PendingIntent.getActivity(context, 0,i, PendingIntent.FLAG_UPDATE_CURRENT);
	  
	  Notification notif = new Notification(R.drawable.ic_launcher,"Daily Accountant Update", System.currentTimeMillis());
	  
	  notif.flags |= Notification.FLAG_AUTO_CANCEL;
	  notif.setLatestEventInfo(context, from, message, contentIntent);
	  
	  nm.notify(1, notif);
	 }

}
