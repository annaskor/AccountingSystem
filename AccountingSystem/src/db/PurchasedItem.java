package db;

import java.io.Serializable;

public class PurchasedItem
{
	private String code;
	private double unitPrice;
	private String date;
	private int quantity;
	
	public PurchasedItem(String code, double unitPrice, String date, int quantity) {
		this.quantity = quantity;
		this.code = code;
		this.unitPrice = unitPrice;
		this.date = date;
	}

	@Override 
	public String toString(){
		return "Quantity: " + quantity;
	}
	
	

}
