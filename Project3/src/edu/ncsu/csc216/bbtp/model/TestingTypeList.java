package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.bbtp.util.ArrayList;

/**
 * This class maintains and manipulates an ArrayList of testing types.
 * 
 * @author Noah Trimble
 * @author Walker Booth
 *
 */
public class TestingTypeList extends Observable implements Tabular, Serializable, Observer {

	/** Serial number */
	private static final long serialVersionUID = 984509L;
	/** Name of the testing type list */
	private String name = "Testing Types";
	/** Next testing type number */
	private int nextTestingTypeNum;
	/** List of testing types */
	private ArrayList list;
	
	/**
	 * Constructs a TestingTypeList object by initializing the list ArrayList
	 * and setting nextTestingTypeNum to 1.
	 */
	public TestingTypeList()
	{
		list = new ArrayList();
		nextTestingTypeNum = 1;
		
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * Gets the testing type list name
	 * @return testing type list name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Adds a testing type to the testing type list so that the list is always sorted by TestingTypeID.
	 * Notifies observers and the TestingTypeList is added as an Observer of the added TestingType
	 * @param name Name of the testing type to add
	 * @param desc Description of the testing type to add
	 * @return true if testing type can be added, false if not
	 */
	public boolean addTestingType(String name, String desc)
	{
		boolean canAdd = false;
		TestingType temp = new TestingType("TT" + Integer.toString(getNextTestingTypeNum()), name, desc);
		
		canAdd = list.add(temp);
			
		if(canAdd)
		{
			temp.addObserver(this);
			incNextTestingTypeNum();
			setChanged();
			notifyObservers(this);

		}
		
		return canAdd;
	}

	/**
	 * Gets the testing type at the specified index.
	 * @param index Index to retrieve testing type from
	 * @return testing type at specified index
	 * @throws IndexOutOfBoundsException if the index is out of range (index is less than 0 || index is greater than size())
	 */
	public TestingType getTestingTypeAt(int index)
	{
		//checks for a valid index
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		return (TestingType) list.get(index);
	}
	
	/**
	 * Gets the index of a testing type by ID in the list.
	 * @param id Testing Type ID
	 * @return index of the testing type specified or -1 if the testing type specified is not in the list
	 */
	public int indexOf(String id)
	{
		for(int i = 0; i < size(); i++)
		{
			if(((TestingType) list.get(i)).getTestingTypeID().equals(id))
				return i;
		}
		return -1;
	}
	
	/**
	 * Gets the index of a testing type by name in the list.
	 * @param name Testing Type name
	 * @return index of the testing type specified or -1 if the testing type specified is not in the list
	 */
	public int indexOfName(String name)
	{
		for(int i = 0; i < size(); i++)
		{
			if(((TestingType) list.get(i)).getName().equals(name))
				return i;
		}
		return -1;
	}
	
	/**
	 * Gets the number of elements in the list list.
	 * @return number of elements in list
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * Determines if the list list is empty
	 * @return true if the list list is empty, false if not
	 */
	public boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	/**
	 * Removes a testing type at a specified index.
	 * Notifies observers and the TestingTypeList is removed as an Observer of the removed TestingType
	 * @param index Index to remove testing type at
	 * @return testing type removed
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to size
	 */
	public TestingType removeTestingTypeAt(int index)
	{	
		try
		{
			TestingType temp = (TestingType) list.remove(index);
			temp.deleteObserver(this);
			setChanged();
			notifyObservers(this);
			return temp;
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * Removes a testing type with a specific id.
	 * Notifies observers and the TestingTypeList is removed as an Observer of the removed TestingType
	 * @param id ID of the testing type to remove
	 * @return true if the testing type was removed, false if not
	 */
	public boolean removeTestingType(String id)
	{
		int index = indexOf(id);
		
		if(index != -1)
		{
			TestingType temp = (TestingType) list.remove(index);
			temp.deleteObserver(this);
			setChanged();
			notifyObservers(this);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Gets the next testing type number
	 * @return next testing type number
	 */
	private int getNextTestingTypeNum()
	{
		return nextTestingTypeNum;
	}
	
	/**
	 * Increments the testing type number for the next testing type.
	 */
	private void incNextTestingTypeNum() {
		nextTestingTypeNum++;
	}
	
	/**
	 * Creates and returns an Object double array with each row representing a test type and three columns as
	 * Testing Type id, name, and description.
	 * @return Object array with testing type information
	 */
	@Override
	public Object[][] get2DArray() {
		Object[][] testingTypeArray = new Object[size()][3];
		
		for(int i = 0; i < size(); i++)
		{
			TestingType temp = (TestingType) list.get(i);
			testingTypeArray[i][0] = temp.getTestingTypeID();
			testingTypeArray[i][1] = temp.getName();
			testingTypeArray[i][2] = temp.getDescription();
		}
		
		return testingTypeArray;
	}

	/**
	 * Propagates the notification of change to its Observers if the testing types list contains Object o.
	 * @param o Observable to check for in list
	 * @param arg Argument to pass through as notification
	 */
	@Override
	public void update(Observable o, Object arg) {
		TestingType t = (TestingType) o;
		if(list.contains(t))
		{
			setChanged();
			notifyObservers(arg);
		}
	}
}
