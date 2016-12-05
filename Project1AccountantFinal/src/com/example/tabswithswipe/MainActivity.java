package com.example.tabswithswipe;

import java.util.Calendar;

import com.example.tabswithswipe.adapter.TabsPagerAdapter;
import com.example.tabswithswipe.R;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
 
public class MainActivity extends FragmentActivity implements ActionBar.TabListener
{
 
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    AlarmManager am;
    
    // Tab titles
    private String[] tabs = {"ADD AND DEDUCT BALANCE", "DATA PICKER"};
 
  
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
 
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);    
        
        
        
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		setRepeatingAlarm();
 
        // Adding Tabs
        for (String tab_name : tabs) 
        {
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
        }
 
        /**
         * on swiping the viewpager make respective tab selected
         * */
       
        
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() 
        {
 
            @Override
            public void onPageSelected(int position)
            {
                // on changing the page
                // make respected tab selected
              actionBar.setSelectedNavigationItem(position);
            }
 
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) 
            {
            }
 
            @Override
            public void onPageScrollStateChanged(int arg0) 
            {
            }
        });
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft)
    {
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft)
    {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
     
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) 
    {
    }
    
    
    public void setRepeatingAlarm()
  	 {
  		    Calendar timefornotification = Calendar.getInstance();
  		    timefornotification.set(Calendar.HOUR_OF_DAY,16);
  		    timefornotification.set(Calendar.MINUTE, 36);
  		    timefornotification.set(Calendar.SECOND, 00);
  		 
  		 
  	  Intent intent = new Intent(this, MyReceiver.class);
  	  
  	  PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,intent, PendingIntent.FLAG_CANCEL_CURRENT);
  	  
  	  am.setRepeating(AlarmManager.RTC_WAKEUP, timefornotification.getTimeInMillis(),AlarmManager.INTERVAL_DAY,  pendingIntent);
  	 }
 
}