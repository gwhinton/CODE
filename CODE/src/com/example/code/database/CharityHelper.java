package com.example.code.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CharityHelper extends SQLiteOpenHelper{

	public static final String TABLE_NAME = "charity";
	public static final String COLUMN_BN = "bn";
	public static final String COLUMN_NAME = "name";
	
	public static final String COLUMN_FROM_DONATION = "from_donations";
	public static final String COLUMN_FROM_CHARITIES = "from_charities";
	public static final String COLUMN_FROM_GOV = "from_gov";
	public static final String COLUMN_FROM_FOREIGN = "from_foreign";
	public static final String COLUMN_FROM_INVESTMENT = "from_investment";
	public static final String COLUMN_FROM_FUNDRAISING = "from_fundrasing";
	public static final String COLUMN_FROM_OTHER = "from_other";
	public static final String COLUMN_REVENUE_TOTAL = "revenue_total";
	
	public static final String COLUMN_TO_CHARITY = "to_charity";
	public static final String COLUMN_TO_ADMIN = "to_admin";
	public static final String COLUMN_TO_FUND = "to_fundraising";
	public static final String COLUMN_TO_POLITIC = "to_politics";
	public static final String COLUMN_TO_GIFTS = "to_gifts";
	public static final String COLUMN_EXPENSE_TOTAL = "expense_total";
	
	public static final String COLUMN_CHARITY_QUALITY = "cq";
	public static final String COLUMN_WEBSITE = "website";
	
	private static final String DATABASE_NAME = "charity.db";
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_NAME + "(" 
			+ COLUMN_BN + " text primary key, " 
			+ COLUMN_NAME + " text not null, "
			//REVENUE
			+ COLUMN_FROM_DONATION + " real, "
			+ COLUMN_FROM_CHARITIES + " real, "
			+ COLUMN_FROM_GOV + " real, " 
			+ COLUMN_FROM_FOREIGN + " real, " 
			+ COLUMN_FROM_INVESTMENT + " real, "
			+ COLUMN_FROM_FUNDRAISING + " real, " 
			+ COLUMN_FROM_OTHER + " real, "
			+ COLUMN_REVENUE_TOTAL + " real, " 
			//EXPENSES
			+ COLUMN_TO_CHARITY + " real, "
			+ COLUMN_TO_ADMIN + " real, "
			+ COLUMN_TO_FUND + " real, " 
			+ COLUMN_TO_POLITIC + " real, " 
			+ COLUMN_TO_GIFTS + " real, " 
			+ COLUMN_EXPENSE_TOTAL + " real, " 
			
			+ COLUMN_CHARITY_QUALITY + " integer, " 
			+ COLUMN_WEBSITE + " text" 
			+");";
	
	public CharityHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(DATABASE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){
		Log.w(CharityHelper.class.getName(),
				"Upgrading database from version " + oldVer + " to "
				+ newVer + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
}