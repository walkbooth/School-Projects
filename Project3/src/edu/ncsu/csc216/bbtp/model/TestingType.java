package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;

import java.util.Observable;

/**
 * This class represents a testing type and maintains information related to a testing type.
 * 
 * @author Noah Trimble
 * @author Walker Booth
 *
 */
public class TestingType extends Observable implements Serializable, Comparable<TestingType> {

	/** Serial number */
	private static final long serialVersionUID = 459188L;
	/** Name of the testing type */
	private String name;
	/** Description of the testing type */
	private String description;
	/** Testing Type ID */
	private String testingTypeID;
	
	/**
	 * Constructs a testing type object using an ID, name, and description.
	 * 
	 * @param id Testing type's ID
	 * @param name Name of testing type
	 * @param desc Description of testing type
	 */
	public TestingType(String id, String name, String desc)
	{
		setTestingTypeID(id);
		setName(name);
		setDescription(desc);
		
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the name of the testing type
	 * @return the name of the testing type
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the testing type
	 * Notifies observers of TestingType
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name to set is null or an empty string
	 */
	public void setName(String name) {
		if(name == null || name.equals(""))
			throw new IllegalArgumentException("Invalid name.");
		
		this.name = name;
		setChanged();
		notifyObservers(this);	
	}

	/**
	 * Gets the description of the testing type
	 * @return the description of the testing type
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the testing type
	 * Notifies observers of TestingType
	 * @param description the description to set
	 * @throws IllegalArgumentException if the description to set is null or an empty string
	 */
	public void setDescription(String description) {
		if(description == null || description.equals(""))
			throw new IllegalArgumentException("Invalid description.");
		
		this.description = description;
		setChanged();
		notifyObservers(this);	
	}

	/**
	 * Gets the testing type ID
	 * @return the testingTypeID
	 */
	public String getTestingTypeID() {
		return testingTypeID;
	}

	/**
	 * Sets the testing type ID
	 * Notifies observers of TestingType
	 * @param testingTypeID the testingTypeID to set
	 * @throws IllegalArgumentException if the testingTypeID to set is null or an empty string
	 */
	private void setTestingTypeID(String testingTypeID) {
		if(testingTypeID == null || testingTypeID.equals(""))
			throw new IllegalArgumentException("Invalid testing type ID.");
		
		this.testingTypeID = testingTypeID;
		setChanged();
		notifyObservers(this);	
	}

	/**
	 * Returns the testing type name.
	 * 
	 * @return the testing type name
	 */
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Generates a hashcode for TestingType with testing type ID.
	 * 
	 * @return hashcode for TestingType
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testingTypeID == null) ? 0 : testingTypeID.hashCode());
		return result;
	}

	/**
	 * Compares objects by checking for equality with testing type IDs.
	 * 
	 * @param obj
	 *            the Object to compare
	 * @return true if the objects have the same testing type IDs.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestingType other = (TestingType) obj;
		if (testingTypeID == null) {
			if (other.testingTypeID != null)
				return false;
		} else if (!testingTypeID.equals(other.testingTypeID))
			return false;
		return true;
	}

	/**
	 * Compares two testing types based on their testing type ID
	 * @param c testing type to compare to
	 * @return a negative integer, zero, or a positive integer 
	 * as this object is less than, equal to, or greater than the specified object respectively.
	 */
	@Override
	public int compareTo(TestingType c) {
		return testingTypeID.compareTo(c.getTestingTypeID());
	}
}
