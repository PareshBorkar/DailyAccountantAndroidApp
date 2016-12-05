package com.example.tabswithswipe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.example.tabswithswipe.modelclass.ModelClass;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class SqliteClass 
{
	
	static String DB_NAME = "dummy";
	static int DB_VER = 1;
	static String DB_PATH;

	DBhelper helper;
	SQLiteDatabase database;
	Context context;
	ArrayList<ModelClass> getDetailArrayListAdd = new ArrayList<ModelClass>();
	ArrayList<ModelClass> getDetailArrayListDeduct = new ArrayList<ModelClass>();

	
	class DBhelper extends SQLiteOpenHelper 
	{

		public DBhelper(Context context)
		{
			super(context, DB_NAME, null, DB_VER);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase arg0) 
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
		{
			// TODO Auto-generated method stub

		}

		
		public void createDatabase() 
		{
			// TODO Auto-generated method stub
			boolean pathExist = checkDB();
			
			if (pathExist) 
			{// do nothing
			
				Log.i("TAG", "database Exists");
			}
			else
			{
				this.getReadableDatabase();

				try 
				{
					CopyDataBase();
					
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		private void CopyDataBase() throws IOException
		{

			Log.i("TAG", "Copying Database.");
			
			InputStream myInput = context.getAssets().open(DB_NAME + ".sqlite");
			String outFileName = DB_PATH + "";
			
			Log.i("TAG", "output file path:" + DB_PATH);
			
			OutputStream myOutput = new FileOutputStream(outFileName);

			byte[] buffer = new byte[1024];
			int length;
			
			while ((length = myInput.read(buffer)) > 0) 
			{
				myOutput.write(buffer, 0, length);
			}

			// Close the streams
			myOutput.flush();
			myOutput.close();
			myInput.close();
			Log.i("TAG", "Finished copying.");
		}

		
		private boolean checkDB()
		{
			// TODO Auto-generated method stub
			boolean dbBool = false;
			String path = context.getFilesDir().getAbsolutePath().replace("files", "databases")+ File.separator + DB_NAME;
			File Fpath = new File(path);
			dbBool = Fpath.exists();

			return dbBool;
		}

	}
	
	
	
	
	
	
	
	

	public SqliteClass(Context c) 
	{

		context = c;
		DB_PATH = context.getDatabasePath(DB_NAME).getAbsolutePath();

		DBhelper helper = new DBhelper(context);
		helper.createDatabase();
	}

	
	
	public void open() 
	{
		helper = new DBhelper(context);
		database = helper.getWritableDatabase();

	}
	
	

	public void close() 
	{

		helper.close();

	}
	
	

	public void insert(String date, String addedbal,String curtime)
	{
		
		String queryFinalBalance = "SELECT final_balance FROM daily_accountant"; 
		Cursor cursor = database.rawQuery(queryFinalBalance, null);
		
		cursor.moveToLast();
		int n=cursor.getPosition();
		String added_data = "0";
		int finalBal = 0;
		
		if(n == -1)
		{
			String query = "insert into daily_accountant(date,final_balance,added_balance,time) values('" + date + "','"+ addedbal + "','"+ addedbal + "','"+ curtime + "')";
			database.execSQL(query);
			Log.i("dataInsert", "data inserted into database");
		}
		else
		{
			added_data =cursor.getString(0);
			
			finalBal = Integer.parseInt(added_data);
			finalBal = finalBal+Integer.parseInt(addedbal);
			
			
			String query = "insert into daily_accountant(date,final_balance,added_balance,time) values('" + date + "','"+ finalBal + "','"+ addedbal + "','"+ curtime + "')";
			
			database.execSQL(query);
			Log.i("dataUpdated", "data updated into database");
			
		}
		
		
	}
	
	
	
	
	
	
	public ArrayList<ModelClass> getdetailsAdded(String date)
	{

		String query = "SELECT date, main_balance , added_balance,deducted_balance,time FROM daily_accountant   WHERE date= '"+date+"'"; 
		
		Cursor cursor = database.rawQuery(query, null);
		
		Log.d("dbInsert", "select query is fired from cursor");
		
		cursor.moveToLast();
		Log.d("dbInsert", "cursor moved to last");
		
		cursor.moveToFirst();
		
		 if (cursor.moveToFirst())
		 {
			  do 
		        {
		      	
			//Log.d("dbInsert", "inside the cursors do while loop "+ i);
			
			
			
			
			String added_data =cursor.getString(2);
			String time = cursor.getString(4);
				
			if(!added_data.equals("0"))
			{
			ModelClass data = new ModelClass();
			data.setAmount(added_data);
			data.setTime(time);	
			getDetailArrayListAdd.add(data);

			Log.i("dbInsert", "got from database added data "+data.getAmount()+"time :"+data.getTime());
			
		//	Toast.makeText(context,"added data : "+data.getAmount()+"/n time:"+data.getTime()  , Toast.LENGTH_LONG).show();
			}
			
			
			
			
		        } while (cursor.moveToNext());
		    }
		
		Log.d("dbInsert", "out of the cursors for loop");
		
		cursor.close();
		
		
		
		
		return getDetailArrayListAdd;
	}
	
	
	
	
	
	
	public ArrayList<ModelClass> getdetailsDeduct(String date)
	{

		String query = "SELECT date, main_balance , added_balance,deducted_balance,time FROM daily_accountant   WHERE date= '"+date+"'"; 
		
		Cursor cursor = database.rawQuery(query, null);
		
		Log.d("dbInsert", "select query is fired from cursor");
		
		cursor.moveToLast();
		Log.d("dbInsert", "cursor moved to last");
		
		cursor.moveToFirst();
		
		 if (cursor.moveToFirst())
		 {
			  do 
		        {
			
			String deducted_data =cursor.getString(3);
			String time = cursor.getString(4);
				
			if(!deducted_data.equals("0"))
			{
			ModelClass dataD = new ModelClass();
			dataD.setAmount(deducted_data);
			dataD.setTime(time);	
			getDetailArrayListDeduct.add(dataD);

			Log.i("dbInsert", "got from database added data "+dataD.getAmount()+"time :"+dataD.getTime());
			
			}
			
			
			
			
		        } while (cursor.moveToNext());
		    }
		
		Log.d("dbInsert", "out of the cursors for loop");
		
		cursor.close();
		
		
		
		
		return getDetailArrayListDeduct;
	}
	
	
	
	
	
	
	
	
	
	
	public void deductBalance(String deductedBalance,String formatedDate,String curtime)
	{
		String queryDeductFinalBalance = "SELECT final_balance FROM daily_accountant  WHERE date= '"+formatedDate+"' "; 
		Cursor cursor = database.rawQuery(queryDeductFinalBalance, null);
		
		cursor.moveToLast();
		int n=cursor.getPosition();
		String added_data = "0";
		int finalBal = 0;
		
		if(n == -1)
		{
			Toast.makeText(context, "ADD DATA FIRST", Toast.LENGTH_LONG).show();
		}
		else
		{
			added_data =cursor.getString(0);
			
			finalBal = Integer.parseInt(added_data);
			finalBal = finalBal-Integer.parseInt(deductedBalance);
			
			if(finalBal < 0)
			{
				Toast.makeText(context, "CANNOT DEDUCT,BALANCE IS ZERO!!!", Toast.LENGTH_SHORT).show();
			}
			else
			{
			String query = "insert into daily_accountant(date,final_balance,deducted_balance,time) values('" + formatedDate + "','"+ finalBal + "','"+deductedBalance+"','"+ curtime + "')";
			database.execSQL(query);
			Log.i("dataUpdated", "data updated into database");
			
			Toast.makeText(context, "Balance Deducted", Toast.LENGTH_LONG).show();
			}
		}
		
	}
	

	
	public String showAddedBal(String formatedDate)
	{
		String query = "SELECT added_balance FROM daily_accountant  WHERE date= '"+formatedDate+"' "; 
		Cursor cursor = database.rawQuery(query, null);
		
		
		cursor.moveToLast();
		int n=cursor.getPosition();
		String added_data = null;
		
		if(n != -1)
		{
		added_data =cursor.getString(0);
		}
		
		if(added_data == null)
		{
			return null;
		}
		else
		{
		return added_data;
		}
	}
	
	
	
	public String showSpent(String formatedDate)
	{
		String query = "SELECT deducted_balance FROM daily_accountant  WHERE date= '"+formatedDate+"' "; 
		Cursor cursor = database.rawQuery(query, null);
		
		cursor.moveToLast();
		int n=cursor.getPosition();
		String deducted_data = null;
		
		if(n != -1)
		{
		deducted_data =cursor.getString(0);
		}
		
		if(deducted_data == null)
		{
			return null;
		}
		else
		{
		return deducted_data;
		}
	}
	
	
	public String showMain(String formatedDate)
	{
		String query = "SELECT main_balance FROM daily_accountant  WHERE date= '"+formatedDate+"' "; 
		Cursor cursor = database.rawQuery(query, null);
		
		cursor.moveToLast();
		int n=cursor.getPosition();
		String mainBal = null;
		
		if(n != -1)
		{
		 mainBal =cursor.getString(0);
		}
		
		if(mainBal == null)
		{
			return null;
		}
		else
		{
		return mainBal;
		}
	}
	
	
	
	
	public String showFinalBal(String formatedDate)
	{
		String query = "SELECT final_balance FROM daily_accountant  WHERE date= '"+formatedDate+"' "; 
		Cursor cursor = database.rawQuery(query, null);
		
		cursor.moveToLast();
		int n=cursor.getPosition();
		String finalBal = null;
		
		if(n != -1)
		{
			finalBal =cursor.getString(0);
		}
		
		if(finalBal == null)
		{
			return null;
		}
		else
		{
		return finalBal;
		}
	}
	
	
	
	
	public String showFinalBal2(String formatedDate)
	{
		String query = "SELECT final_balance FROM daily_accountant"; 
		Cursor cursor = database.rawQuery(query, null);
		
		cursor.moveToLast();
		int n=cursor.getPosition();
		String finalBal = null;
		
		if(n != -1)
		{
			finalBal =cursor.getString(0);
		}
		
		if(finalBal == null)
		{
			return null;
		}
		else
		{
		return finalBal;
		}
	}
	
	
}
