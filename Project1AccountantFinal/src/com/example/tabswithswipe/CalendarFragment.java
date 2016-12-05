package com.example.tabswithswipe;

import com.example.tabswithswipe.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;
 
public class CalendarFragment extends Fragment 
{
	CalendarView calendarview1;
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
        View rootView = inflater.inflate(R.layout.calender_layout, container, false);
        calendarview1 = (CalendarView) rootView.findViewById(R.id.calendarView1);
        
        
    	calendarview1.setOnDateChangeListener(new OnDateChangeListener()
		{
			
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,int dayOfMonth) 
			{
				month = month+1;
				Intent i = new Intent(getActivity(),ParticularDateActivity.class);
				
				i.putExtra("dayOfMonth", dayOfMonth);
				i.putExtra("month",month);
				i.putExtra("year",year);
				
				startActivity(i);
			}
		});
         
        return rootView;
    }
}