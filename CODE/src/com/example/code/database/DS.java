package com.example.code.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.code.model.Charity;

public class DS {

	private SQLiteDatabase db;
	private DatabaseHelper dbh;
	private String[] CharityColumns = { DatabaseHelper.COLUMN_BN,
			DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_FROM_DONATION,
			DatabaseHelper.COLUMN_FROM_CHARITIES, DatabaseHelper.COLUMN_FROM_GOV,
			DatabaseHelper.COLUMN_FROM_FOREIGN,
			DatabaseHelper.COLUMN_FROM_INVESTMENT,
			DatabaseHelper.COLUMN_FROM_FUNDRAISING,
			DatabaseHelper.COLUMN_FROM_OTHER, DatabaseHelper.COLUMN_REVENUE_TOTAL,
			DatabaseHelper.COLUMN_TO_CHARITY, DatabaseHelper.COLUMN_TO_ADMIN,
			DatabaseHelper.COLUMN_TO_FUND, DatabaseHelper.COLUMN_TO_POLITIC,
			DatabaseHelper.COLUMN_TO_GIFTS, DatabaseHelper.COLUMN_EXPENSE_TOTAL,
			DatabaseHelper.COLUMN_CHARITY_QUALITY, DatabaseHelper.COLUMN_WEBSITE };
	

	public DS(Context context) {
		dbh = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		db = dbh.getWritableDatabase();
	}

	public void close() {
		dbh.close();
	}

	public void addIdent(String BN, String Name, String website) {
		ContentValues val = new ContentValues();
		val.put(DatabaseHelper.COLUMN_BN, BN);
		val.put(DatabaseHelper.COLUMN_NAME, Name);
		val.put(DatabaseHelper.COLUMN_WEBSITE, website);
		db.insert(DatabaseHelper.TABLE_CHARITY, null, val);
	}

	public void addFin(String BN, int[] Revenue, int[] Expense, float CQ) {
		ContentValues val = new ContentValues();
		val.put(DatabaseHelper.COLUMN_BN, BN);
		
		float revTotal = 0;
		for (int i : Revenue) {
			revTotal+= i;
		}
		float expTotal = 0;
		for (int i : Expense) {
			expTotal+= i;
		}
		
		if (Revenue.length == 7) {
			val.put(DatabaseHelper.COLUMN_FROM_DONATION, Revenue[0]);
			val.put(DatabaseHelper.COLUMN_FROM_CHARITIES, Revenue[1]);
			val.put(DatabaseHelper.COLUMN_FROM_GOV, Revenue[2]);
			val.put(DatabaseHelper.COLUMN_FROM_FOREIGN, Revenue[3]);
			val.put(DatabaseHelper.COLUMN_FROM_INVESTMENT,Revenue[4]);
			val.put(DatabaseHelper.COLUMN_FROM_FUNDRAISING, Revenue[5]);
			val.put(DatabaseHelper.COLUMN_FROM_OTHER, Revenue[6]);
			val.put(DatabaseHelper.COLUMN_REVENUE_TOTAL, revTotal);
		}
		if (Expense.length == 5) {
			val.put(DatabaseHelper.COLUMN_TO_CHARITY, Expense[0]);
			val.put(DatabaseHelper.COLUMN_TO_ADMIN, Expense[1]);
			val.put(DatabaseHelper.COLUMN_TO_FUND, Expense[2]);
			val.put(DatabaseHelper.COLUMN_TO_POLITIC, Expense[3]);
			val.put(DatabaseHelper.COLUMN_TO_GIFTS, Expense[4]);
			val.put(DatabaseHelper.COLUMN_EXPENSE_TOTAL, expTotal);
		}
		val.put(DatabaseHelper.COLUMN_CHARITY_QUALITY, revTotal/expTotal);
		
		db.update(DatabaseHelper.TABLE_CHARITY, val, DatabaseHelper.COLUMN_BN + " = " + BN, null);
		
	}

	public void deleteCharity(Charity charity) {
		String BN = charity.getBusinessNumber();
		System.out.println("Comment deleted with BN: " + BN);
		db.delete(DatabaseHelper.TABLE_CHARITY, DatabaseHelper.COLUMN_BN + " = " + BN,
				null);
	}

	public List<Charity> getAllCharities() {
		List<Charity> charities = new ArrayList<Charity>();

		Cursor cur = db.query(DatabaseHelper.TABLE_CHARITY, CharityColumns, null, null,
				null, null, null);

		cur.moveToFirst();
		while (!cur.isAfterLast()) {
			Charity charity = cursorToCharity(cur);
			charities.add(charity);
			cur.moveToNext();
		}
		cur.close();
		return charities;
	}

	private Charity cursorToCharity(Cursor cur) {
		Charity charity = new Charity();
		charity.setBusinessNumber(cur.getString(0));
		charity.setName(cur.getString(1));
		
		//TODO: Rest of the charity information...
		
		return charity;
	}
	
	public void populateCode(String file) {
		db.execSQL(file);
	}
	public void populateCodeLayman(String file) {
		db.execSQL(file);
	}
	public void populateLayman(String file) {
		db.execSQL(file);
	}

}
