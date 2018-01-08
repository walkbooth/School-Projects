package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.bbtp.util.LinkedList;

/**
 * This class maintains and manipulates a LinkedList of test cases.
 * 
 * @author Noah Trimble
 * @author Walker Booth
 *
 */
public class TestCaseList extends Observable implements Tabular, Serializable, Observer {

	/** Serial number */
	private static final long serialVersionUID = 98734509L;
	/** Name of the TestCaseList */
	private String name = "Testing Types"; 
	
	private int nextTestCaseNum;
	private String testCaseListID;
	/** ArrayList of test cases */
	private LinkedList list;

	/**
	 * Constructs the linked list of Test Cases. 
	 * @param name the name of the TestCaseList
	 * @param id the ID of the TestCaseList
	 * @throws IllegalArgumentException if either parameter is null or empty 
	 */
	public TestCaseList (String name, String id) {
		list = new LinkedList ();
		setName(name);
		setTestCaseListID(id);
		nextTestCaseNum = 1;
		
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * Returns the TestCaseList name 
	 * @return name the name of the TestCaseList
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * Sets the name of the TestCaseList
	 * @param name the name of the TestCaseList
	 * @throws IllegalArgumentException if the name is null or an empty string
	 */
	public void setName (String name) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException();
		}
		
		this.name = name;
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * Returns the testCaseListID
	 * @return testCaseListID the id of the testCaseList
	 */
	public String getTestCaseListID () {
		return testCaseListID;
	}
	
	/**
	 * Sets the TestCaseListID
	 * @throws IllegalArgumentException if the id to set is null or an empty string.
	 */
	private void setTestCaseListID (String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException();
		}
		this.testCaseListID = id;
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * Gets the TestCaseNumber of the next test case
	 * @return nextTestCaseNum the TestCaseNumber of the next test case
	 */
	private int getNextTestCaseNum () {
		return nextTestCaseNum;
	}
	
	/**
	 * Increments the value of the nextTestCaseNum
	 */
	private void incNextTestCaseNum () {
		nextTestCaseNum++;
	}
	
	/**
	 * Adds a new test case to the list.
	 * @param desc the description of the test
	 * @param type the type of the test
	 * @param creation the date of creation of the test
	 * @param exp the expected results of the test
	 * @param tested whether or not the test has been tested
	 * @param lastTestDate the last date that the test was tested
	 * @param act the actual results of the test
	 * @param pass whether or not the test is passing
	 * @return true if the test case could be added to the list, false if not
	 */
	public boolean addTestCase(String desc, TestingType type, Date creation, String exp, boolean tested, Date lastTestDate, String act, boolean pass) {
		TestCase test1;
		try {
			test1 = new TestCase(testCaseListID + "-" + "TC" + getNextTestCaseNum(), desc, type, creation, exp, tested, lastTestDate, act, pass);
		} catch (IllegalArgumentException e) {
			return false;
		}
		
		for (int i = 0; i < list.size(); i++) {
			TestCase test2 = (TestCase) list.get(i);
			if(test1.getLastTestedDateTime() == null)
				break;
				
			if (test2.getLastTestedDateTime() == null || test1.getLastTestedDateTime().compareTo(test2.getLastTestedDateTime()) < 0) {
				list.add(i, test1);
				incNextTestCaseNum();
				test1.addObserver(this);
				setChanged();
				notifyObservers(this);
				return true;
			}
		}
		list.add(list.size(), test1);
		incNextTestCaseNum();
		setChanged();
		notifyObservers(this);
		test1.addObserver(this);
		return true;
	}
	
	/**
	 * Returns the TestCase at the given index in the list.
	 * @param idx the index to retrieve the test case from 
	 * @return testCase the TestCase to return 
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the size of the list
	 */
	public TestCase getTestCaseAt(int idx) {
		if (idx < 0 || idx >= list.size()) {
			throw new IndexOutOfBoundsException ();
		}
		
		return (TestCase) list.get(idx);
	}
	
	/**
	 * Returns the index of the first occurrence of a TestCase that has an exact match to the provided id. 
	 * @param id the id to search for 
	 * @return the index where the TestCase was found, -1 if not found 
	 */
	public int indexOf (String id) {
		for (int i = 0; i < list.size(); i++) {
			TestCase t = (TestCase) list.get(i);
			if (id.equals(t.getTestCaseID())) {
				return i;
			}
		}
		
		return -1;

	}
	
	/**
	 * Returns the number of TestCases in the list. 
	 * @return size the number of items in the list
	 */
	public int size () {
		return list.size();
	}
	
	/**
	 * Is the list empty?
	 * @return true if the list is empty, false if not
	 */
	public boolean isEmpty () {
		return list.isEmpty();
	}
	
	/**
	 * Removes a TestCase at the given index
	 * @param idx the index to remove a test case from 
	 * @return the TestCase which was removed 
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater size
	 */
	public TestCase removeTestCaseAt (int idx) {
		if (idx < 0 || idx > list.size()) {
			throw new IndexOutOfBoundsException ();
		} 
		
		TestCase test = (TestCase) list.remove(idx);
		test.deleteObserver(this);
		setChanged();
		notifyObservers(this);
		return test;
	}
	
	/**
	 * Removes a TestCase with the given ID
	 * @param testCaseID the id of the test to remove
	 * @return true if the test was removed, false if not 
	 */
	public boolean removeTestCase (String testCaseID) {
		int i = indexOf(testCaseID);
		if (i == -1) {
			return false;
		}
		
		TestCase test = (TestCase) list.get(i);
		list.remove(i);
		test.deleteObserver(this);
		setChanged();
		notifyObservers(this);
		
		return true;
		
	}
	
	@Override
	public Object[][] get2DArray() {
		Object[][] o = new Object[list.size()][9];
		for (int i = 0; i < list.size(); i++) {
			TestCase test = (TestCase) list.get(i);
			o[i][0] = test.getTestCaseID();
			o[i][1] = test.getDescription();
			o[i][2] = test.getTestingType();
			o[i][3] = test.getCreationDateTime();
			o[i][4] = test.getLastTestedDateTime();
			o[i][5] = test.tested();
			o[i][6] = test.getExpectedResults();
			o[i][7] = test.getActualResults();
			o[i][8] = test.pass();
				
		}
		
		return o;
	}

	@Override
	public void update(Observable o, Object arg) {
		TestCase t = (TestCase) o;
		if (list.contains(t)) {
			setChanged();
			notifyObservers(arg);
		}
	}
}
