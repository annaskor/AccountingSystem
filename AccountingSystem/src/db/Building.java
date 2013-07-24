package db;

import java.io.Serializable;

public class Building implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4582883034478609653L;
	private String code;
	private String name;
	private String address;
	private String supervisor;
	private String dateOfCreation;
	
	public Building(String code, String name){
		this.code = code;
		this.name = name;
		this.address = null;
		this.supervisor = null;
		this.dateOfCreation = null;
	}
	
	public void setSupervisor(String supervisor){
		this.supervisor = supervisor;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public void setdateOfCreation(String dateOfCreation){
		this.dateOfCreation = dateOfCreation;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public String getDateOfCreation() {
		return dateOfCreation;
	}
	
	@Override
	public String toString(){
		String temp;
		temp = code + " " + name;
		if (address != null) temp+= " at " + address;
		if (supervisor != null) temp += " supervised by " + supervisor;
		if (dateOfCreation != null) temp += " added on " + dateOfCreation;
		return temp;
	}
}
