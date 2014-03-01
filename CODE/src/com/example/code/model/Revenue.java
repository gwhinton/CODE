package com.example.code.model;

public class Revenue {
	
	private float amount;
	private RevenueType revenueType;
	
	public Revenue(float amount, RevenueType revenueType) {
		this.amount = amount;
		this.revenueType = revenueType;
	}
	
	public float getAmount() {
		return this.amount;
	}
	public RevenueType getRevenueType() {
		return this.revenueType;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public void setRevenueType(RevenueType revenueType) {
		this.revenueType = revenueType;
	}
	
	public enum RevenueType {
		
		DONATION("Donation", "Receipted donations"),
		NONDONATION("Non-receipted","Non-receipted donations"),
		GIFT("Gifts", "Gifts from other charities"),
		GOVERNMENT("Government", "Government funding"),
		OTHER("Other", "All other revenue");
		
		private String label;
		private String description;
		
		private RevenueType (String label, String description) {
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
