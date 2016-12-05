package com.example.tabswithswipe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.tabswithswipe.adapter.AdapterClass;
import com.example.tabswithswipe.adapter.AdapterClass1;
import com.example.tabswithswipe.modelclass.ModelClass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class ParticularDateActivity extends Activity
{
   TextView tvClosingBalPartDate,tvDatePartDate;
   ListView lvPartDispAdd,lvPartDispDeduct;
   
   AdapterClass adapterClassAdd;
   AdapterClass1 adapterClassDeduct;
   
   Intent intentGet;
   int dayOfMonth,month,year;
   SqliteClass dbPartDate;
   String strOpeningAmtPartDate,strClosingBalPartDate,strAddedMoneyPartDAte,strSpentMoneyPartDAte;
   
	ArrayList<ModelClass> orderArrayListadd = new ArrayList<ModelClass>();
	ArrayList<ModelClass> orderArrayListdeduct = new ArrayList<ModelClass>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.particular_date_layout);
		
		dbPartDate = new SqliteClass(getApplicationContext());
		bridges();
	
		
		intentGet = getIntent();
		dayOfMonth = intentGet.getIntExtra("dayOfMonth",0);
		month = intentGet.getIntExtra("month", 0);
	    year = intentGet.getIntExtra("year", 0);
	    
	 //   String daytest = 
	    
	    String strParticularDatetesst = dayOfMonth+"-"+month+"-"+year;
	    
	    Log.e("testInDate", "testIntent :" +strParticularDatetesst);
		
		Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("d-M-yyyy");
      String strParticularDate = df.format(c.getTime());
        
        Log.e("testInDate", "testInbuilt :"+strParticularDate);
        
	    tvDatePartDate.setText(strParticularDatetesst);
	    
	    
	    dbPartDate.open();
		orderArrayListadd =dbPartDate.getdetailsAdded(strParticularDatetesst);
		dbPartDate.close();
		
		dbPartDate.open();
		orderArrayListdeduct =dbPartDate.getdetailsDeduct(strParticularDatetesst);
		dbPartDate.close();
		
		int k =orderArrayListadd.size();
		Log.e("dbInsert","add"+k);
		
		int l =orderArrayListdeduct.size();
		Log.e("dbInsert","deduct"+l);
		
		
		adapterClassAdd = new AdapterClass(orderArrayListadd, ParticularDateActivity.this);
		lvPartDispAdd.setAdapter(adapterClassAdd);
		
		
		
		adapterClassDeduct = new AdapterClass1(orderArrayListdeduct, ParticularDateActivity.this);
		lvPartDispDeduct.setAdapter(adapterClassDeduct);
		
		
		
	    
	    
	    dbPartDate.open();
	    strClosingBalPartDate = dbPartDate.showFinalBal2(strParticularDatetesst);
	    dbPartDate.close();
	    
	   
	    tvClosingBalPartDate.setText(strClosingBalPartDate);
	    
	}
	

	private void bridges() 
	{
		// TODO Auto-generated method stub
		tvClosingBalPartDate = (TextView) findViewById(R.id.tvClosingBalPartDate);
		tvDatePartDate = (TextView) findViewById(R.id.tvDatePartDate);
		
		lvPartDispAdd = (ListView) findViewById(R.id.lvPartDispAdd);
		lvPartDispDeduct = (ListView) findViewById(R.id.lvPartDispDeduct);
		
	}
}
