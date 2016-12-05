package com.example.tabswithswipe.adapter;

import java.util.ArrayList;

import com.example.tabswithswipe.ParticularDateActivity;
import com.example.tabswithswipe.R;
import com.example.tabswithswipe.modelclass.ModelClass;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterClass extends BaseAdapter
{
	

	static ArrayList<ModelClass> orderArrayList;

	Context myContext;
	LayoutInflater inflator;
	
	
	
	public AdapterClass(ArrayList<ModelClass> orderArrayList2 ,ParticularDateActivity particulardateactivity)
	{
		AdapterClass.orderArrayList = orderArrayList2;
		this.myContext = particulardateactivity;
		inflator = LayoutInflater.from(myContext);
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return orderArrayList.size();
	}

	@Override
	public Object getItem(int arg0) 
	{
		// TODO Auto-generated method stub
		return  orderArrayList.get(arg0);
	}

	@Override
	public long getItemId(int arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) 
	{
		// TODO Auto-generated method stub
		Log.i("dbInsert", "View dispta samko");
		
		View v =convertView;
		final ViewHolder holdsView;
		
		if(v == null)
		{
			v = inflator.inflate(R.layout.each_row_added, null);
			holdsView = new ViewHolder();
			v.setTag(holdsView);
		}
		else
		{
			holdsView = (ViewHolder) v.getTag();
		}
		
		holdsView.tvAmtDisp= setS( v , R.id.tvAmtDispAdded, orderArrayList.get(position).getAmount());
		holdsView.tvTimeDisp= setS( v , R.id.tvTimeDispAdded, orderArrayList.get(position).getTime());
		
		return v;
	}
	
	
	final class ViewHolder
	{
		TextView tvAmtDisp,tvTimeDisp;	
	}
	
	
	
	public TextView setS(View arg1, int text, String i) 
	{
		TextView txt = (TextView) arg1.findViewById(text);
		txt.setText(i);
		return txt;
	}

}
