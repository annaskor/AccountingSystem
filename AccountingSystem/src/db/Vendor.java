package db;

import java.util.TreeMap;


public class Vendor {

	private String code;
	private String name;
	private String address;
	private TreeMap<String, Item> relatedItems;
	
	/**
	 * The creation of a Vendor object require the code and name of the vendor, though it is recommended to add
	 * an address at the same time
	 * @param code
	 * @param name
	 */
	public Vendor(String code, String name){
		this.code = code;
		this.name = name;
		this.address = null;
		relatedItems = new TreeMap<String, Item>();
	}
	
	/**
	 * Sets the address
	 * @param address
	 */
	public void setAdresss(String address){
		this.address = address;
	}
	
	/**
	 * returns the address. 
	 * Possibility of returning a null - to be handled.
	 * @return
	 */
	public String getAddress(){
		return this.address;
	}
	
	/**
	 * Returns name of vendor.
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Returns code of vendor
	 * @return
	 */
	public String getCode(){
		return this.code;
	}
	
	/**
	 * Returns true if item with given name exists in the related items list.
	 * If not, returns false;
	 * @param name
	 * @return
	 */
	public boolean hasItem(String itemCode){
		return relatedItems.containsKey(itemCode);
	}
	
	/**
	 * returns item with name as given if it has been added to the related items list.
	 * else throws an exception with tailored message
	 * @param name
	 * @return
	 * @throws InvalidArguementException
	 */
	public Item getItemByCode(String code) throws InvalidArguementException{
		if (hasItem(code))
			return relatedItems.get(code);
		else throw new InvalidArguementException("This item does not exist for this vendor.");		
	}
	
	/**
	 * adds item to related items list.
	 * @param item
	 */
	public void addItem(Item item){
		relatedItems.put(item.getCode(), item);
	}
	
	@Override
	public String toString(){
		if (address != null){
			return "Vendor: " + name + "; Code: " + code + "; Address " + address;
		}
		else return "Vendor: " + name + "; Code: " + code;
	}

}
