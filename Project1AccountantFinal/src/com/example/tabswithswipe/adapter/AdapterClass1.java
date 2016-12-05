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

public class AdapterClass1 extends BaseAdapter
{
	


	static ArrayList<ModelClass> orderArrayList1;

	Context myContext;
	LayoutInflater inflator;
	
	
	
	public AdapterClass1(ArrayList<ModelClass> orderArrayList2 ,ParticularDateActivity particulardateactivity)
	{
		AdapterClass1.orderArrayList1 = orderArrayList2;
		this.myContext = particulardateactivity;
		inflator = LayoutInflater.from(myContext);
	}

	
	
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return orderArrayList1.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return  orderArrayList1.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
				Log.i("dbInsert", "View dispta samko");
				
				View v =convertView;
				final ViewHolder holdsView;
				
				if(v == null)
				{
					v = inflator.inflate(R.layout.each_row_deduct, null);
					holdsView = new ViewHolder();
					v.setTag(holdsView);
				}
				else
				{
					holdsView = (ViewHolder) v.getTag();
				}
				
				holdsView.tvAmtDispD = setS( v , R.id.tvAmtDispD, orderArrayList1.get(position).getAmount());
				holdsView.tvTimeDispD = setS( v , R.id.tvTimeDispD, orderArrayList1.get(position).getTime());
				
				return v;
	}
	
	
	final class ViewHolder
	{
		TextView tvAmtDispD,tvTimeDispD;	
	}
	
	
	
	public TextView setS(View arg1, int text, String i) 
	{
		TextView txt = (TextView) arg1.findViewById(text);
		txt.setText(i);
		return txt;
	}

}
