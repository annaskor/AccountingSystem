package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;

import db.*;

public class AzianMike{

	private HashMap<String, Vendor> vendors;
	private HashMap<String, Account> accounts;
	private HashMap<String, Building> buildings;
	private HashMap<String, PurchasedItem> purchases;
	private HashMap<String, Item> items;
	private String dbLocation;

	public AzianMike(String inputValue){
		
		this.dbLocation = inputValue;
		
		this.vendors = new HashMap<String, Vendor>();
		this.accounts = new HashMap<String, Account>();
		this.buildings = new HashMap<String, Building>();
		this.purchases = new HashMap<String, PurchasedItem>();
		this.items = new HashMap<String, Item>();
		
		getVendorsFromFile();
		getAccountsFromFile();
		getBuildingsFromFile();
		getPurchasesFromFile();
		getItemsFromFile();

	}

	private void getItemsFromFile() {
		File file = new File(dbLocation + "items.txt");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] itemCSV = line.split(", ");
				Item item = new Item(itemCSV[0], itemCSV[1], itemCSV[2], itemCSV[3], Double.valueOf(itemCSV[4].substring(1)));
				this.items.put(itemCSV[0], item);
			}
			reader.close();	
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
	}
	

	private void writeItemsToFile() {
		File file = new File(dbLocation + "items.txt");
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.print("");
			String item = "";
			for (Item i: items.values()) {
				item = i.getCode() + ", " + i.getName() + ", " +i.getVendorCode() + ", " + i.getAccountCode() + ", $" + i.getUnitPrice(); 
				writer.append(item + '\n');
			}
			writer.close();
		} catch (FileNotFoundException e) { }
	}


	private void getPurchasesFromFile() {
		File file = new File(dbLocation + "purchases.txt");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] purchasesCSV = line.split(", ");
				PurchasedItem pItem = new PurchasedItem(purchasesCSV[0], Double.valueOf(purchasesCSV[1].substring(1)), purchasesCSV[2], Integer.valueOf(purchasesCSV[3]));
				this.purchases.put(purchasesCSV[0], pItem);
			}
			reader.close();	
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
	}
	

	private void writePurchasesToFile() {

	}


	private void getBuildingsFromFile() {
		File file = new File(dbLocation + "buildings.txt");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while (reader.ready()) {
				line = reader.readLine();
				String[] buildingCSV = line.split(", ");
				Building building = new Building(buildingCSV[0], buildingCSV[1]);
				if (!buildingCSV[2].equals("null")) building.setAddress(buildingCSV[2]);
				if (!buildingCSV[3].equals("null")) building.setSupervisor(buildingCSV[3]);
				if (!buildingCSV[4].equals("null")) building.setdateOfCreation(buildingCSV[4]);
				this.buildings.put(buildingCSV[0], building);
			}
			reader.close();	
		} catch ( IOException e) {
			try {
				file.createNewFile();
			} catch (IOException e1) { }
		}			
	}
	
	private void writeBuildingsToFile() {
		File file = new File(dbLocation + "buildings.txt");
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.print("");
			String building = "";
			for (Building b: buildings.values()) {
				building = b.getCode() + ", " + b.getName() + b.getAddress() + ", " + b.getSupervisor() + ", " + b.getDateOfCreation(); 
				writer.append(building + '\n');
			}
			writer.close();
		} catch (FileNotFoundException e) { }
	}

	private void getAccountsFromFile() {
		File file = new File(dbLocation + "accounts.txt");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while (reader.ready()) {
				line = reader.readLine();
				String[] accountCSV = line.split(", ");
				Account account = new Account(accountCSV[0], accountCSV[1]);
				this.accounts.put(accountCSV[0], account);
			}
			reader.close();	
		} catch ( IOException e) {
			try {
				file.createNewFile();
			} catch (IOException e1) { }
		}	
	}

	
	private void writeAccountsToFile() {
		File file = new File(dbLocation + "accounts.txt");
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.print("");
			String account = "";
			for (Account a: accounts.values()) {
				account = a.getCode() + ", " + a.getName(); 
				writer.append(account + '\n');
			}
			writer.close();
		} catch (FileNotFoundException e) { }
	}

	private void getVendorsFromFile() {
		File file = new File(dbLocation + "vendors.txt");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while (reader.ready()) {
				line = reader.readLine();
				String[] vendorCSV = line.split(", ");
				Vendor vendor = new Vendor(vendorCSV[0], vendorCSV[1]);
				if (!vendorCSV[2].equals("null"))
					vendor.setAdresss(vendorCSV[2]);
				this.vendors.put(vendorCSV[0], vendor);
			}
			reader.close();	
		} catch ( IOException e) {
			try {
				file.createNewFile();
			} catch (IOException e1) { }
		}	
	}

	private void writeVendorsToFile() {
		File file = new File(dbLocation + "vendors.txt");
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.print("");
			String vendor = "";
			for (Vendor v: vendors.values()) {
				vendor = v.getCode() + ", " + v.getName() + ", " + v.getAddress(); 
				writer.append(vendor + '\n');
			}
			writer.close();
		} catch (FileNotFoundException e) { }
	}

	/**
	 * Adds new vendor to database. Replaces any vendor with the given code, if it exists
	 * @param code
	 * @param name
	 */
	public void newVendor(String code, String name ){ vendors.put(code,  new Vendor(code, name)); }

	/**
	 * Adds new vendor with recorded address to database. Replaces any vendor with the given code, if it exists.
	 * @param code
	 * @param name
	 * @param address
	 */
	public void newVendor(String code, String name, String address) {
		vendors.put(code,  new Vendor(code, name));
		vendors.get(code).setAdresss(address);
	}

	/**
	 * Adds new account to database. Replaces any account with the given code, if it exists
	 * @param code
	 * @param name
	 */
	public void newAccount(String code, String name) { accounts.put(code, new Account(code, name)); }

	/**
	 * Adds new building to database. Replaces any building with the given code, if it exists
	 * @param code
	 * @param name
	 */
	public void newBuilding(String code, String name) {
		buildings.put(code, new Building(code, name));
	}


	public void newItem(String code, String name, String vendor,
			String account, double unitPrice) {
		items.put(code, new Item(code, name, vendor, account, unitPrice));
	}

	public void newPurchase(String code, double valueOf, String date, int quantity ) {
		purchases.put(code+date+quantity, new PurchasedItem(code, valueOf, date, quantity));
	}

	/**
	 * saves current state of db
	 */
	public void save() {
		writeVendorsToFile();
		writeAccountsToFile();
		writeBuildingsToFile();
		writeItemsToFile();
		writePurchasesToFile();

	}

	/**
	 * Checks that the account given exists
	 * @param accountCode
	 * @return
	 */
	public boolean hasAccount(String accountCode) { return accounts.containsKey(accountCode); }

	/**
	 * checks that the given vendor exists
	 * @param vendorCode
	 * @return 
	 */
	public boolean hasVendor(String vendorCode) { return vendors.containsKey(vendorCode); }

	/**
	 * checks that the given item exists
	 * @param itemCode
	 * @return
	 */
	public boolean itemExists(String itemCode) { return items.containsKey(itemCode); }

	public void generateInvoice() {
		// TODO Auto-generated method stub
	}

	/**
	 * @return invoice numbers
	 */
	public String[] getInvoiceList() {
		// TODO Auto-generated method stub
		String [] invoices = {"09546", "45274", "25572", "52474", "25672", "563855"};
		return invoices;
	}

	/**
	 * removes vendor with given code from db
	 * @param code
	 */
	public void removeVendor(String code) { vendors.remove(code); }

	/**
	 * @return list of vendors in format code name at address
	 */
	public String[] getVendorList() {
		Collection<Vendor> vList = vendors.values();
		String [] vendorArray = new String[vList.size()];
		String temp;
		int i = 0;

		for ( Vendor v : vList){
			temp = v.getCode() + " " + v.getName();
			if (v.getAddress() != null) temp += " at " + v.getAddress();
			vendorArray[i] = temp;
			i++;
		}
		return vendorArray;
	}

	/**
	 * @param code
	 * @return a line separated string of items related to a given vendor
	 */
	public String getItemsRelatedToVendor(String code) {
		Collection<Item> iList = items.values();
		String itemsRelatedToVendor = "";
		for (Item it: iList){
			if (it.getVendorCode().equals(code)) 
				itemsRelatedToVendor += it.getCode() + " " + it.getName() + System.getProperty("line.separator");
		}
		return itemsRelatedToVendor;
	}

	/**
	 * removes account from db
	 * @param code
	 */
	public void removeAccount(String code) { accounts.remove(code); }

	/**
	 * @return list of vendors in format code name at address
	 */
	public String[] getAccountList() {
		Collection<Account> aList = accounts.values();
		String [] accountArray = new String[aList.size()];
		String temp;
		int i = 0;

		for ( Account a : aList){
			temp = a.getCode() + " " + a.getName();
			accountArray[i] = temp;
			i++;
		}
		return accountArray;
	}

	/**
	 * @param code
	 * @return line separated list of item codes and names related to account
	 */
	public String getItemsRelatedToAccount(String code) {
		Collection<Item> iList = items.values();
		String itemsRelatedToAccount = "";
		for (Item it: iList){
			if (it.getAccountCode().equals(code)) 
				itemsRelatedToAccount += it.getCode() + " " + it.getName() + System.getProperty("line.separator");
		}
		return itemsRelatedToAccount;
	}

	/**
	 * checks if item exists
	 * @param code
	 * @return
	 */
	public boolean hasItem(String code) { return items.containsKey(code); }

	/**
	 * removes item from db
	 * @param code
	 */
	public void removeItem(String code) { items.remove(code); }

	/**
	 * @return list of all items in db
	 */
	public String[] getItemList() {
		Collection<Item> iList = items.values();
		String [] itemArray = new String[iList.size()];
		String temp;
		int i = 0;

		for ( Item item : iList){
			temp = item.getCode() + " " + item.getName() + ", Vendor: " + item.getVendorCode() + ", Account: "
					+ item.getAccountCode() + ", Unit Price: $" + String.format("%10.2f", item.getUnitPrice());
			itemArray[i] = temp;
			i++;
		}
		return itemArray;
	}

	/**
	 * adds address to the records of the building with given code
	 * @param code
	 * @param address
	 */
	public void addBuildingAddress(String code, String address) { buildings.get(code).setAddress(address); }

	/**
	 * adds supervisor to the records of the building with given code
	 * @param code
	 * @param supervisor
	 */
	public void addBuildingSupervisor(String code, String supervisor) { buildings.get(code).setSupervisor(supervisor); }

	/**
	 * adds date to the records of the building with given code
	 * @param code
	 * @param date
	 */
	public void addBuildingDate(String code, String date) { buildings.get(code).setdateOfCreation(date); }

	/**
	 * checks if building is in database
	 * @param code
	 * @return
	 */
	public boolean hasBuilding(String code) { return buildings.containsKey(code); }

	/**
	 * removes building with given code from database
	 * @param code
	 */
	public void removeBuilding(String code) { buildings.remove(code); }

	/**
	 * @return array of all buildings
	 */
	public String[] getBuildingList() {
		Collection<Building> bList = buildings.values();
		String [] buildingArray = new String[bList.size()];
		int i = 0;		
		for ( Building b : bList){
			buildingArray[i] = b.toString();
			i++;
		}
		return buildingArray;
	}

	public boolean hasInvoice(String code) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getInvoice(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public void generateReport() {
		// TODO Auto-generated method stub

	}
}
