package edu.ncsu.csc316.customer_service.data;

/**
 * This class represents a single Customer object
 * @author Walker Booth (wgbooth)
 *
 */
public class Customer implements Comparable<Object> {
	
	/** The first name of the customer */
	private String firstName;
	/** The last name of the customer */
	private String lastName;
	
	
	/**
	 * Constructor for Customer objects.
	 * @param firstName the first name of the customer
	 * @param lastName the last name of the customer 
	 */
	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * Returns the first name of the customer
	 * @return firstName the first name of the customer
	 */
	public String getFirstName () {
		return firstName;
	}
	
	/**
	 * Returns the last name of the customer 
	 * @return lastName the last name of the customer
	 */
	public String getLastName () {
		return lastName;
	}

	/**
	 * Compares two customer objects.
	 * @param o the object to compare to
	 * @return 1 if this customer is greater than o, -1 if this customer is less than o, and 0 if they are equal. 
	 */
	@Override
	public int compareTo(Object o) {
		Customer c = (Customer) o;
		if (lastName.compareTo(c.getLastName()) > 0) {
			return 1;
		} else if (lastName.compareTo(c.getLastName()) < 0) {
			return -1;
		} else {
			if (firstName.compareTo(c.getFirstName()) > 0) {
				return 1;
			} else if (firstName.compareTo(c.getFirstName()) < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
	/**
	 * Returns the customer object as a string
	 * @return s the customer with a string representation
	 */
	public String toString() {
		String s = firstName;
		s = s.concat(" ".concat(lastName));
		return s;
	}
	
	

}
