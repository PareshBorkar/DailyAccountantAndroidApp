package com.example.tabswithswipe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

import com.example.tabswithswipe.R;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
public class AccountantAddDeleteFragment extends Fragment implements OnClickListener
{
	TextView tvTotalBalMainLayout,tvDateTodayMainLayout;
	EditText etAddBal,etDeductBal;
	Button btnAddBal,btnDeductBal;
	String strMainBal,strAddBal,strDeductBal,formattedDate,strAddedBaltvDisplay,strDeductedBaltvDisplay,strMainBaltvDisplay,strTotalBaltvDisplay;
	String curTime;
	SqliteClass dbMain;
	PendingIntent pendingIntent;
	Context context;
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.main_layout, container, false);
        
        dbMain = new SqliteClass(getActivity());
        		bridges(rootView);
        
        		
        	btnAddBal.setOnClickListener(this);
        	btnDeductBal.setOnClickListener(this);
        	        
        	       
        	    	Calendar c = Calendar.getInstance();
        	        SimpleDateFormat df = new SimpleDateFormat("d-M-yyyy");
        	        formattedDate = df.format(c.getTime());
        	        // formattedDate have current date/time
        	       tvDateTodayMainLayout.setText("DATE: "+formattedDate);
        	       
        	
        	       
        	       Date dt = new Date();
        	       int hours = dt.getHours();
        	       int minutes = dt.getMinutes();
        	       int seconds = dt.getSeconds();
        	       
        	       curTime = hours + ":" + minutes + ":" + seconds;
        	       
        	    
        	       		dbMain.open();
        	       		strTotalBaltvDisplay = dbMain.showFinalBal2(formattedDate);
        	       		Log.wtf("main bal :", strTotalBaltvDisplay);
        	       		dbMain.close();
        	       		tvTotalBalMainLayout.setText(strTotalBaltvDisplay);
        	       		
         
        		
        		
        return rootView;
    }
    
    
    
	private void bridges(View rootView) 
	{
		// TODO Auto-generated method stub
		tvTotalBalMainLayout = (TextView)  rootView.findViewById(R.id.tvTotalBalMainLayout);
		tvDateTodayMainLayout =(TextView) rootView.findViewById(R.id.tvDateTodayMainLayout);
		
		etAddBal = (EditText) rootView.findViewById(R.id.etAddBal);
		etDeductBal = (EditText) rootView.findViewById(R.id.etDeductBal);
		
		btnAddBal = (Button) rootView.findViewById(R.id.btnAddBal);
		btnDeductBal = (Button) rootView.findViewById(R.id.btnDeductBal);
		
		
		
	}
	
	
	
	



	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		
		switch (v.getId()) 
		{
        case R.id.btnAddBal:
        	
        	strAddBal = etAddBal.getText().toString();
        	
        	if(strAddBal.equals(""))
        	{
        		etAddBal.setError("aare baba enter values re");
        	}
        	else
        	{
        	
        	   Date dt = new Date();
               int hours = dt.getHours();
               int minutes = dt.getMinutes();
               int seconds = dt.getSeconds();
           String curTimeAdd = hours + ":" + minutes + ":" + seconds;
        	
        	
        	dbMain.open();
        	dbMain.insert(formattedDate, strAddBal,curTimeAdd);
        	
        	Toast.makeText(getActivity(), "Balance Added", Toast.LENGTH_LONG).show();
        	
        	
        	strTotalBaltvDisplay = dbMain.showFinalBal(formattedDate);
       		tvTotalBalMainLayout.setText(strTotalBaltvDisplay);
        	
        	dbMain.close();
        	etAddBal.setText("");
        	}
        	
        	
        	
			break;
			
			
        case R.id.btnDeductBal:
        	
        	strDeductBal = etDeductBal.getText().toString();
        	
        	if(strDeductBal.equals(""))
        	{
        		etDeductBal.setError("aare baba enter values re");
        	}
        	else
        	{
				
			
        	
        	Date dtD = new Date();
            int hoursD = dtD.getHours();
            int minutesD = dtD.getMinutes();
            int secondsD = dtD.getSeconds();
        String curTimeDeduct = hoursD + ":" + minutesD + ":" + secondsD;
        	
        	
        	
        	
        	
        	dbMain.open();
        	dbMain.deductBalance(strDeductBal, formattedDate,curTimeDeduct);
        	
        	Toast.makeText(getActivity(), "Balance Deducted", Toast.LENGTH_LONG).show();
        	
        	strTotalBaltvDisplay = dbMain.showFinalBal(formattedDate);
       		tvTotalBalMainLayout.setText(strTotalBaltvDisplay);
        	
        	dbMain.close();
        	
        	etDeductBal.setText("");
        	}
        	
        	break;
        	
		
	}
  

	}
}