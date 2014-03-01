package com.example.code.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.code.model.Charity;

public class CharityDS {

	private SQLiteDatabase db;
	private CharityHelper dbh;
	private String[] allCol = { CharityHelper.COLUMN_BN,
			CharityHelper.COLUMN_NAME, CharityHelper.COLUMN_FROM_DONATION,
			CharityHelper.COLUMN_FROM_CHARITIES, CharityHelper.COLUMN_FROM_GOV,
			CharityHelper.COLUMN_FROM_FOREIGN,
			CharityHelper.COLUMN_FROM_INVESTMENT,
			CharityHelper.COLUMN_FROM_FUNDRAISING,
			CharityHelper.COLUMN_FROM_OTHER, CharityHelper.COLUMN_REVENUE_TOTAL,
			CharityHelper.COLUMN_TO_CHARITY, CharityHelper.COLUMN_TO_ADMIN,
			CharityHelper.COLUMN_TO_FUND, CharityHelper.COLUMN_TO_POLITIC,
			CharityHelper.COLUMN_TO_GIFTS, CharityHelper.COLUMN_EXPENSE_TOTAL,
			CharityHelper.COLUMN_CHARITY_QUALITY, CharityHelper.COLUMN_WEBSITE };

	public CharityDS(Context context) {
		dbh = new CharityHelper(context);
	}

	public void open() throws SQLException {
		db = dbh.getWritableDatabase();
	}

	public void close() {
		dbh.close();
	}

	public void addIdent(String BN, String Name, String website) {
		ContentValues val = new ContentValues();
		val.put(CharityHelper.COLUMN_BN, BN);
		val.put(CharityHelper.COLUMN_NAME, Name);
		val.put(CharityHelper.COLUMN_WEBSITE, website);
		db.insert(CharityHelper.TABLE_NAME, null, val);
	}

	public void addFin(String BN, int[] Revenue, int[] Expense, float CQ) {
		ContentValues val = new ContentValues();
		val.put(CharityHelper.COLUMN_BN, BN);
		
		float revTotal = 0;
		for (int i : Revenue) {
			revTotal+= i;
		}
		float expTotal = 0;
		for (int i : Expense) {
			expTotal+= i;
		}
		
		if (Revenue.length == 7) {
			val.put(CharityHelper.COLUMN_FROM_DONATION, Revenue[0]);
			val.put(CharityHelper.COLUMN_FROM_CHARITIES, Revenue[1]);
			val.put(CharityHelper.COLUMN_FROM_GOV, Revenue[2]);
			val.put(CharityHelper.COLUMN_FROM_FOREIGN, Revenue[3]);
			val.put(CharityHelper.COLUMN_FROM_INVESTMENT,Revenue[4]);
			val.put(CharityHelper.COLUMN_FROM_FUNDRAISING, Revenue[5]);
			val.put(CharityHelper.COLUMN_FROM_OTHER, Revenue[6]);
			val.put(CharityHelper.COLUMN_REVENUE_TOTAL, revTotal);
		}
		if (Expense.length == 5) {
			val.put(CharityHelper.COLUMN_TO_CHARITY, Expense[0]);
			val.put(CharityHelper.COLUMN_TO_ADMIN, Expense[1]);
			val.put(CharityHelper.COLUMN_TO_FUND, Expense[2]);
			val.put(CharityHelper.COLUMN_TO_POLITIC, Expense[3]);
			val.put(CharityHelper.COLUMN_TO_GIFTS, Expense[4]);
			val.put(CharityHelper.COLUMN_EXPENSE_TOTAL, expTotal);
		}
		val.put(CharityHelper.COLUMN_CHARITY_QUALITY, revTotal/expTotal);
		
		db.update(CharityHelper.TABLE_NAME, val, CharityHelper.COLUMN_BN + " = " + BN, null);
		
	}

	public void deleteCharity(Charity charity) {
		String BN = charity.getBusinessNumber();
		System.out.println("Comment deleted with BN: " + BN);
		db.delete(CharityHelper.TABLE_NAME, CharityHelper.COLUMN_BN + " = " + BN,
				null);
	}

	public List<Charity> getAllCharities() {
		List<Charity> charities = new ArrayList<Charity>();

		Cursor cur = db.query(CharityHelper.TABLE_NAME, allCol, null, null,
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

}
