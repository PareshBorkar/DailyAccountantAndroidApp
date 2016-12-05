package com.example.tabswithswipe.adapter;

import com.example.tabswithswipe.CalendarFragment;
import com.example.tabswithswipe.AccountantAddDeleteFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter 
{

	public TabsPagerAdapter(FragmentManager fm)
	{
		super(fm);

	}

	@Override
	public Fragment getItem(int index) 
	{

		switch (index) 
		{
		case 0:

			return new AccountantAddDeleteFragment();
		case 1:

			return new CalendarFragment();
		
		}

		return null;
	}

	@Override
	public int getCount()
	{

		return 2;
	}

}