package db;

import java.util.HashMap;

public class Account {
	
	private String code;
	private String name;
	private HashMap<String, Item> relatedItems;
	
	public Account(String code, String name){
		this.code = code;
		this.name = name;
		relatedItems = new HashMap<String, Item>();
	}
	
	/**
	 * Returns true if item with given name exists in the related items list.
	 * If not, returns false;
	 * @param name
	 * @return
	 */
	public boolean hasItem(String name){
		return relatedItems.containsKey(name);
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

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString(){
			return "Account: " + name + "; Code: " + code;
	}
	
}
