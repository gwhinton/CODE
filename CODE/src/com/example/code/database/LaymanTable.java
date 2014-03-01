package com.example.code.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LaymanTable extends SQLiteOpenHelper{

	public static final String TABLE_NAME = "program_layman";
	
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TERM_EN = "layman_term_EN";
	public static final String COLUMN_TERM_FR = "layman_term_FR";
	
	private static final String DATABASE_NAME = "charity.db";
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_NAME + "(" 
			+ COLUMN_ID + "integer primary key autoincrement, "
			+ COLUMN_TERM_EN + " text, "
			+ COLUMN_TERM_FR + " text"
			+");";
	
	public LaymanTable(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(DATABASE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){
		Log.w(LaymanTable.class.getName(),
				"Upgrading database from version " + oldVer + " to "
				+ newVer + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
}
