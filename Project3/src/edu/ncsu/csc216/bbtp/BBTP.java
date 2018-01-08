package edu.ncsu.csc216.bbtp;

import edu.ncsu.csc216.bbtp.model.TestingTypeList;


import edu.ncsu.csc216.bbtp.util.ArrayList;   

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.bbtp.model.TestCaseList;

/**
 * The main class for the BBTP tools. Holds references to the top-level data
 * structures that contain TestCase and TestingType objects and acts as the
 * controller between the model and the GUI presentation view.
 * 
 * @author Noah Trimble
 * @author Walker Booth
 */
public class BBTP extends Observable implements Serializable, Observer {

    /** Serial version UID. */
    private static final long serialVersionUID = 34992L;

    /** Increment for increasing the capacity of the array of TestCaseLists */
    private static final int RESIZE = 3;
    /** A collection of TestCaseList */
    private TestCaseList[] testCases;
    /** Number of TestCaseList */
    private int numLists;
    /** A collection of TestingTypes */
    private TestingTypeList testingTypes;
    /** Filename for saving the bbtp information */
    private String filename;
    /** True if bbtp has changed since last save */
    private boolean changed;
    /** The next number for a task list id */
    private int nextTestCaseListNum;
    
    /** 
     * Constructor of a Black Box Test Plan
     * Initializes nextTestCaseListNum to 1 and numLists to 1.
     * Constructs the testCases array with at least 3 elements.
     * Constructs a TestCaseList called new list and adds it to the testCases array at element 0 using add method
     * Constructs a new TestingTypeList.
     * Changed is set to false.
     */
    public BBTP () {
    	nextTestCaseListNum = 1;
    	numLists = 0;
    	
    	testCases = new TestCaseList[RESIZE];
    	testCases[numLists] = new TestCaseList("New List", "TCL" + getNextTestCaseListNum());
    	testCases[numLists].addObserver(this);
    	numLists++;
    	incNextTestCaseListNum();
    	
    	testingTypes = new TestingTypeList();
    	testingTypes.addObserver(this);
    	changed = false;
    	setChanged();
    	notifyObservers(this);
    }
    
    /**
     * Returns the value stored in changed
     * @return changed whether or not the file has been modified. 
     */
    public boolean isChanged () {
    	boolean isChanged = false;
    	
    	if (testCases[1] != null) {
    		isChanged = true;
    	}
    	
    	return testingTypes.hasChanged() || this.changed || isChanged;
    }
    
    /**
     * Sets the changed field to the given value
     * @param newValue the value to set changed to 
     */
    public void setChanged (boolean newValue) {
    	changed = newValue;
    }
    
    /**
     * Returns the filename
     * @return filename the name of a selected file
     */
    public String getFilename () {
    	return filename;
    }
    
    /**
     * Sets the filename 
     * @param filename the filename to select
     * @throws IllegalArgumentException if the filename parameter is null or empty 
     */
    public void setFilename(String filename) {
    	if(filename == null || filename.equals(""))
    		throw new IllegalArgumentException("Filename cannot be null or an empty string.");
    	
    	this.filename = filename;
    }
    
    /**
     * Gets the number of the next TestCaseList
     * @return nextTestCaseListNum the number of the next TestCaseList
     */
    private int getNextTestCaseListNum () {
    	return nextTestCaseListNum;
    }
    
    /**
     * Increases the number of the next TestCaseList
     */
    private void incNextTestCaseListNum () {
    	nextTestCaseListNum++;
    }
    
    /**
     * Returns the number of TestCaseLists
     * @return numLists the number of TestCaseLists
     */
    public int getNumTestCaseLists() {
    	return numLists;
    }
    
    /**
     * Gets the TestCaseList at the specified index.    
     * @param idx the index where the desired list is located. 
     * @return list the list to return 
     * @throws IndexOutOfBoundsException if the index is less than zero or greater than or equal to numLists
     */
    public TestCaseList getTestCaseList (int idx) {
    	if (idx < 0 || idx >= numLists) {
			throw new IndexOutOfBoundsException();
		}
    	
    	return testCases[idx];
    }
    
    /**
     * Returns the TestingTypeList. 
     * @return testingTypes the testing type list 
     */
    public TestingTypeList getTestingTypeList () {
    	return testingTypes;
    }
    
    /**
     * Adds a new TestCaseList and returns the index of the added TestCaseList.
     * Updates observers. 
     * If the numLists of testCases equals capacity the list numLists is doubled.
     * @return index the index of the TestCaseList added  
     */
    public int addTestCaseList() {
    	//doubles the numLists of array if the numLists is equal to the capacity
		if (numLists == testCases.length) {
			growArray();
		}
    	
		//Adds a new element to the end of the list and increments numLists and nextTestCaseListNum
    	TestCaseList temp = new TestCaseList("New List", "TCL" + getNextTestCaseListNum());
    	testCases[numLists] = temp;
    	numLists++;
    	incNextTestCaseListNum();
		
    	//Notifies observers
    	temp.addObserver(this);
    	setChanged();
    	setChanged(true);					//TODO may not need 
		notifyObservers(this);
		
    	return numLists - 1;
    }
    
    /**
	 * Doubles the numLists of the array, preserving the order and contents of the array
	 */
	private void growArray() {
		TestCaseList[] tempList = new TestCaseList[numLists * 2];
		
		for (int i = 0; i < numLists; i++) {
			tempList[i] = testCases[i];
		}
		testCases = tempList;
	}
    
    /**
     * Removes the TestCaseList at the given index and updates observers
     * @param idx the index where the test should be removed
     * @throws IndexOutOfBoundsException if the index is less than zero or greater than or equal to numLists. 
     */
    public void removeTestCaseList(int idx) {
    	if (idx < 0 || idx >= numLists) {
			throw new IndexOutOfBoundsException();
		}
    	
    	TestCaseList temp = testCases[idx];
		
    	//shifts all elements in the array to left 
		for (int i = idx + 1; i < numLists; i++) {
			testCases[i - 1] = testCases[i];
		}
		
		//removes the reference to the duplicate last object in the array (after shifting to the left)
		testCases[numLists - 1] = null;
		
		numLists--;
		setChanged(true);
		
		//Update observers
		temp.deleteObserver(this);
		setChanged();
		notifyObservers(this);
    }
    
    /**
     * Saves the TestingTypeList and the array of TestCaseLists to the given
     * file using object serialization.
     * 
     * @param fname filename to save BBTP information to.
     * @return true is saved successfully
     */
    public boolean saveDataFile(String fname) {
        if (fname == null || fname.trim().equals("")) {
            System.err.println("Invalid filename" + fname);
            return false;
        } else {
            try {
                FileOutputStream fileOut = new FileOutputStream(fname);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                for (int i = 0; i < numLists; i++) {
                    out.writeObject(testCases[i]);
                }
                out.writeObject(testingTypes);
                out.writeObject(filename);
                out.writeInt(nextTestCaseListNum);
                changed = false;
                out.close();
                fileOut.close();
                return true;
            } catch (IOException e) {
                System.err.println("An error occurred while saving file " + fname);
                e.printStackTrace(System.err);
                return false;
            }
        }
    }

    /**
     * Opens a data file with the given name and creates the data structures
     * from the serialized objects in the file.
     * 
     * @param fname filename to create BBTP information from.
     * @return true is opened successfully
     */
    public boolean openDataFile(String fname) {
        if (changed) {
            saveDataFile(filename);
        }
        try {
            FileInputStream fileIn = new FileInputStream(fname);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList temp = new ArrayList();
            Object tl = in.readObject();
            while (tl instanceof TestCaseList) {
                TestCaseList l = (TestCaseList) tl;
                l.addObserver(this);
                temp.add(l);
                tl = in.readObject();
            }
            testCases = new TestCaseList[temp.size()];
            for (int i = 0; i < temp.size(); i++) {
                testCases[i] = (TestCaseList) temp.get(i);
            }
            numLists = temp.size();
            testingTypes = (TestingTypeList) tl;
            testingTypes.addObserver(this);
            filename = (String) in.readObject();
            nextTestCaseListNum = (int) in.readInt();
            for (int i = 0; i < numLists; i++) {
                TestCaseList list = testCases[i];
                for (int j = 0; j < list.size(); j++) {
                    list.getTestCaseAt(j).addObserver(list);
                }
            }
            for (int i = 0; i < testingTypes.size(); i++) {
                testingTypes.getTestingTypeAt(i).addObserver(testingTypes);
            }
            changed = false;
            in.close();
            fileIn.close();
            return true;
        } catch (IOException e) {
            System.err.println("An error occurred while reading file " + fname);
            e.printStackTrace(System.err);
            return false; 
        } catch (ClassNotFoundException c) {
            System.err.println("Error reconstructing BBTP from file " + fname);
            c.printStackTrace(System.err);
            return false;
        }
    }

    /**
     * Notifies observers of any change and sets changed to true.
     * @param o Observable object
	 * @param arg Argument to pass through as notification
     */
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		setChanged(true);
		notifyObservers(arg);
	}

}