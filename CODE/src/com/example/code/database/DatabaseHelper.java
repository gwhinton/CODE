package com.example.code.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
	//Charity Table
	public static final String TABLE_CHARITY = "charity";
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
	
	//Code Table
	public static final String TABLE_CODE = "program_code";
	
	public static final String COLUMN_CODE = "prog_code";
	public static final String COLUMN_DESC_EN = "prog_term_EN";
	public static final String COLUMN_DESC_FR = "prog_term_FR";
	//Layman Table
	public static final String TABLE_LAYMAN = "program_layman";
	
	public static final String COLUMN_LAYMAN = "prog_layman";
	public static final String COLUMN_TERM_EN = "layman_term_EN";
	public static final String COLUMN_TERM_FR = "layman_term_FR";
	
	//Code/Layman Table
	public static final String TABLE_CODE_LAYMAN = "program_code_layman";
	
	//Database information
	private static final String DATABASE_NAME = "charity.db";
	private static final int DATABASE_VERSION = 1;
	
	//Charity
	private static final String CHARITY_CREATE = "create table "
			+ TABLE_CHARITY + "(" 
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
	//Code
	private static final String CODE_CREATE = "create table "
			+ TABLE_CODE + "(" 
			+ COLUMN_CODE + "text primary key, "
			+ COLUMN_DESC_EN + " text, "
			+ COLUMN_DESC_FR + " text"
			+");";
	//Layman
	private static final String LAYMAN_CREATE = "create table "
			+ TABLE_LAYMAN + "(" 
			+ COLUMN_LAYMAN + " integer primary key autoincrement, "
			+ COLUMN_TERM_EN + " text, "
			+ COLUMN_TERM_FR + " text"
			+");";
	//Layman/Code
	private static final String CODELAYMAN_CREATE = "create table "
			+ TABLE_CODE_LAYMAN + "(" 
			+ COLUMN_CODE + " text, "
			+ COLUMN_LAYMAN + " integer, "
			+ "foreign key(" + COLUMN_CODE + ") references " + DatabaseHelper.TABLE_CODE + "(" + DatabaseHelper.COLUMN_CODE + "), "
			+ "foreign key(" + COLUMN_LAYMAN + ") references " + DatabaseHelper.TABLE_LAYMAN + "(" + DatabaseHelper.COLUMN_LAYMAN + "), " 
			+ "PRIMARY KEY (" + COLUMN_CODE + "," + COLUMN_LAYMAN + ")"
			+");";
	
	
	public DatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(CHARITY_CREATE);
		db.execSQL(CODE_CREATE);
		db.execSQL(LAYMAN_CREATE);
		db.execSQL(CODELAYMAN_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){
		Log.w(DatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVer + " to "
				+ newVer + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARITY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CODE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LAYMAN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CODE_LAYMAN);
		onCreate(db);
	}
	
}