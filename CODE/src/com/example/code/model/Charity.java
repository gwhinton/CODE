package com.example.code.model;

import java.util.ArrayList;
import java.util.List;

public class Charity {
	private String businessNumber;
	private String name;
	private List<Revenue> revenues;
	private List<Expense> expenses;
	private float cq;
	private String executiveSummary;
	private String contact;
	
	public Charity() {
		revenues = new ArrayList<Revenue>();
		expenses = new ArrayList<Expense>();
	}
	public Charity(String BN, String Name) {
		this();
		this.businessNumber = BN;
		this.name = Name;
	}
	
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	
	public String getBusinessNumber() {
		return businessNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Revenue> getRevenues() {
		return revenues;
	}
	
	public void addRevenue(Revenue revenue) {
		this.revenues.add(revenue);
	}
	
	public void setRevenues(List<Revenue> revenues) {
		this.revenues = revenues;
	}
	
	public List<Expense> getExpenses() {
		return expenses;
	}
	
	public void addExpense(Expense expense) {
		this.expenses.add(expense);
	}
	
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	
	public float getCq() {
		return cq;
	}
	
	public void setCq(float cq) {
		this.cq = cq;
	}
	
	public String getExecutiveSummary() {
		return executiveSummary;
	}

	public void setExecutiveSummary(String executiveSummary) {
		this.executiveSummary = executiveSummary;
	}
	
	public String getContact() {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
