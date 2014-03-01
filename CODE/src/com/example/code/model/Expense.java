package com.example.code.model;

public class Expense {
	
	private float amount;
	private ExpenseType expenseType;
	
	public Expense(float amount, ExpenseType expenseType) {
		this.amount = amount;
		this.expenseType = expenseType;
	}
	
	public float getAmount() {
		return this.amount;
	}
	public ExpenseType getExpenseType() {
		return this.expenseType;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}
	
	public enum ExpenseType {
		
		CHARITABLE("Charitable", "Charitable program"),
		BUSINESS("Administration", "Management and administration"),
		FUNDRAISING("Fundraising", "Fundrasing"),
		POLITCAL("Political", "Political activities"),
		GIFT("Given", "Gifts to other registered charities and qualified donees"),
		OTHER("Other", "Other");
		
		private String label;
		private String description;
		
		private ExpenseType (String label, String description) {
			this.label = label;
			this.description = description;
		}
		
		public String getLabel() {
			return this.label;
		}
		public String getDescription() {
			return this.description;
		}
	}
}
