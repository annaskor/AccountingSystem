package db;

public class InvalidArguementException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4342102343470305400L;
	private String message;
	
	public InvalidArguementException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage(){
		return message;
	}

}
