package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;

import java.util.Date;
import java.util.Observable;

/**
 * This class represents a black box test case and maintains data related to a test case.
 * 
 * @author Noah Trimble
 * @author Walker Booth
 *
 */
public class TestCase extends Observable implements Serializable, Comparable<TestCase> {

	/** Serial number */
	private static final long serialVersionUID = 7459L;
	/** Test Case ID */
	private String testCaseID;
	/** Creation date and time of test case */
	private Date creationDateTime;
	/** Description of test case */
	private String description;
	/** Expected results of test case */
	private String expectedResults;
	/** Actual results of test case */
	private String actualResults;
	/** Date and time the test was last tested */
	private Date lastTestedDateTime;
	/** Has the test been run? */
	private boolean testedStatus;
	/** Has the test passed? */
	private boolean pass;
	/** Type of test */
	private TestingType testingType;
	
	/**
	 * Constructs a TestCase object using the appropriate attributes.
	 *  
	 * @param id Test case ID
	 * @param description Description of test case
	 * @param testingType Type of test
	 * @param creationDateTime Creation date and time of test case
	 * @param expectedResults Expected results of test case
	 * @param tested Has the test been run?
	 * @param lastTestedDateTime Date and time the test was last tested
	 * @param actualResults Actual results of test case
	 * @param pass Did the test pass?
	 */
	public TestCase(String id, String description, TestingType testingType, Date creationDateTime, String expectedResults, 
			        boolean tested, Date lastTestedDateTime, String actualResults, boolean pass)
	{
		setTestCaseID(id);
		setDescription(description);
		setTestingType(testingType);
		setCreationDateTime(creationDateTime);
		setExpectedResults(expectedResults);
		setTestedStatus(tested);
		setLastTestedDateTime(lastTestedDateTime);
		setActualResults(actualResults);
		setPass(pass);
		
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the test case ID
	 * @return the testCaseID
	 */
	public String getTestCaseID() {
		return testCaseID;
	}

	/**
	 * Sets the test case ID
	 * Notifies observers of TestCase
	 * @param testCaseID the testCaseID to set
	 * @throws IllegalArgumentException if the testCaseID to set is null or an empty string
	 */
	private void setTestCaseID(String testCaseID) {
		if(testCaseID == null || testCaseID.equals(""))
			throw new IllegalArgumentException("Invalid test case ID.");
		
		this.testCaseID = testCaseID;
		setChanged();
		notifyObservers(this);	
	}

	/**
	 * Gets the creation date and time
	 * @return the creationDateTime
	 */
	public Date getCreationDateTime() {
		return creationDateTime;
	}

	/**
	 * Sets the creation date and time
	 * Notifies observers of TestCase
	 * @param creationDateTime the creationDateTime to set
	 * @throws IllegalArgumentException if the creationDateTime to set is null
	 */
	public void setCreationDateTime(Date creationDateTime) {
		if(creationDateTime == null)
			throw new IllegalArgumentException("Invalid creation date and time.");
		
		this.creationDateTime = creationDateTime;
		setChanged();
		notifyObservers(this);	
	}

	/**
	 * Gets the description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the test case
	 * Notifies observers of TestCase
	 * @param description the description to set
	 * @throws IllegalArgumentException if the description to set is null, an empty string, or a whitespace
	 */
	public void setDescription(String description) {
		if(description == null || description.equals("") || description.equals(" "))
			throw new IllegalArgumentException("Invalid description.");
		
		this.description = description;
		setChanged();
		notifyObservers(this);	
	}

	/**
	 * Gets the expected results
	 * @return the expectedResults
	 */
	public String getExpectedResults() {
		return expectedResults;
	}

	/**
	 * Sets the expected results
	 * Notifies observers of TestCase
	 * @param expectedResults the expectedResults to set
	 * @throws IllegalArgumentException if the expectedResults to set is null, an empty string, or a whitespace
	 */
	public void setExpectedResults(String expectedResults) {
		if(expectedResults == null || expectedResults.equals("") || expectedResults.equals(" "))
			throw new IllegalArgumentException("Invalid expected results.");
		
		this.expectedResults = expectedResults;
		setChanged();
		notifyObservers(this);	
	}

	/**
	 * Gets the actual results
	 * @return the actualResults
	 */
	public String getActualResults() {
		return actualResults;
	}

	/**
	 * Sets the actual results
	 * Notifies observers of TestCase
	 * @param actualResults the actualResults to set
	 * @throws IllegalArgumentException if the actualResults to set is null, an empty string, or a whitespace
	 * and testedStatus is true
	 */
	public void setActualResults(String actualResults) {
		if(testedStatus && (actualResults == null || actualResults.equals("") || actualResults.equals(" ")))
			throw new IllegalArgumentException("Invalid actual results.");
		
		this.actualResults = actualResults;
		setChanged();
		notifyObservers(this);	
	}

	/**
	 * Gets the last tested date and time
	 * @return the lastTestedDateTime
	 */
	public Date getLastTestedDateTime() {
		return lastTestedDateTime;
	}

	/**
	 * Sets the last tested date and time
	 * Notifies observers of TestCase
	 * @param lastTestedDateTime the lastTestedDateTime to set
	 * @throws IllegalArgumentException if the lastTestedDateTime to set is null while the testedStatus is true
	 */
	public void setLastTestedDateTime(Date lastTestedDateTime) {
		if(lastTestedDateTime == null && testedStatus)
			throw new IllegalArgumentException("Invalid last tested date and time.");
		
		this.lastTestedDateTime = lastTestedDateTime;
		setChanged();
		notifyObservers(this);	
	}

	/**
	 * Gets whether the test case has been tested
	 * @return the testedStatus
	 */
	public boolean tested() {
		return testedStatus;
	}

	/**
	 * Sets the tested status of the test
	 * Notifies observers of TestCase
	 * @param testedStatus the testedStatus to set
	 */
	public void setTestedStatus(boolean testedStatus) {
		this.testedStatus = testedStatus;
		setChanged();
		notifyObservers(this);	
	}

	/**
	 * Gets whether the test case has passed
	 * @return whether the test passed
	 */
	public boolean pass() {
		return pass;
	}

	/**
	 * Sets the value of pass
	 * Notifies observers of TestCase
	 * @param pass the pass to set
	 */
	public void setPass(boolean pass) {
		this.pass = pass;
		setChanged();
		notifyObservers(this);	
	}

	/**
	 * Gets the testing type
	 * @return the testingType
	 */
	public TestingType getTestingType() {
		return testingType;
	}

	/**
	 * Sets the testing type
	 * Notifies observers of TestCase
	 * @param testingType the testingType to set
	 * @throws IllegalArgumentException if the testingType to set is null
	 */
	public void setTestingType(TestingType testingType) {
		if(testingType == null)
			throw new IllegalArgumentException("Invalid testing type.");
		
		this.testingType = testingType;
		setChanged();
		notifyObservers(this);	
	}

	/**
	 * Generates a hashcode for TestCase with testCaseID.
	 * 
	 * @return hashcode for TestCase
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testCaseID == null) ? 0 : testCaseID.hashCode());
		return result;
	}

	/**
	 * Compares objects by checking for equality with test case IDs.
	 * 
	 * @param obj
	 *            the Object to compare
	 * @return true if the objects have the same test case IDs.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestCase other = (TestCase) obj;
		if (testCaseID == null) {
			if (other.testCaseID != null)
				return false;
		} else if (!testCaseID.equals(other.testCaseID))
			return false;
		return true;
	}
	
	/**
	 * Compares two test cases based on their last tested dates and times
	 * @param c testing type to compare to
	 * @return a negative integer, zero, or a positive integer 
	 * as this object is less than, equal to, or greater than the specified object respectively.
	 */
	@Override
	public int compareTo(TestCase c) {
		return lastTestedDateTime.compareTo(c.getLastTestedDateTime());
	}
}
