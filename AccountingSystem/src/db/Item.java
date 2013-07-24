package db;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3627566312336519158L;
	private String vendor;
	private String name;
	private String code;
	private double unitPrice;
	private String account;

	public Item(String code, String name, String vendor, String account, double unitPrice){
		this.vendor = vendor;
		this.name = name;
		this.code = code;
		this.unitPrice = unitPrice;
		this.account = account;
	}

	public String getVendorCode() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getAccountCode() {
		return account;
	}

	public void setRelatedAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
	
	@Override
	public String toString(){
		return "Item: " + name + "; Code: " + code + "; Unit Price: $" + unitPrice + "; Related Account: " + account + "  Vendor: " + vendor;
	}
	
	
	
	
}
