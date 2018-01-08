package edu.ncsu.csc216.bbtp.ui;

import java.io.Serializable;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.bbtp.model.TestingType;

/**
 * Class for the test case table of a single test case list
 * @author Walker Booth
 * @author Noah Trimble 
 */
public class TestCaseTableModel extends AbstractTableModel implements Serializable {

	private static final long serialVersionUID = 5954551753060998701L;
	/** The names of each column */
	private String[] colNames = {"ID", "Description", "Test Type", "Creation Date", "Last Tested Date", "Tested?", 
			                     "Expected Results", "Actual Results", "Pass?"};
	/** The 2D array of table data */
	private Object[][] data;
	
	/** 
	 * Constructs a new TestCaseTableModel 
	 * @param data the data to be sent to the new table
	 */
	public TestCaseTableModel(Object[][] data) {
		super();
		this.data = data;
	}
	
	/**
	 * Gets the number of rows in the table
	 * @return rows the number of rows in the table 
	 */
	public int getRowCount () {
		return data.length;
	}
	
	/**
	 * Gets the number of columns in the table 
	 * @return columns the number of columns in the table 
	 */
	public int getColumnCount () {
		return colNames.length;
	}
	
	/**
	 * Gets the name of one of the columns 
	 * @param idx the index where the column is located 
	 * @return name the name of the column to return 
	 */
	public String getColumnName (int idx) {
		return colNames[idx];
	}
	
	/**
	 * Gets the value at one position in the table 
	 * @param row the row of the value
	 * @param col the column of the value 
	 * @return data the data in the specified location in the table 
	 */
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	/**
	 * Sets the value at one position in the table
	 * @param o the new value of the table location
	 * @param row the row of the value
	 * @param col the column of the value 
	 */
	public void setValueAt(Object o, int row, int col) {
		data[row][col] = o;
		fireTableCellUpdated(row, col);
	}
	
	/**
	 * Gets all of the data in one row of the table 
	 * @param row the row to retrieve 
	 * @return testCaseData the data in the row 
	 */
	public TestCaseData getTestCaseRowData(int row) {
		return new TestCaseData((String) data[row][0], (String) data[row][1], (TestingType) data[row][2], 
				                (Date) data[row][3], (Date) data[row][4], (boolean) data[row][5], 
				                (String) data[row][6], (String) data[row][7], (boolean) data[row][8]);
	}
	
	/**
	 * Sets all of the data in one row of the table 
	 * @param row the row to set 
	 * @param data the new data for that row 
	 */
	public void setTaskRowData (int row, TestCaseData data) {
	      setValueAt(data.getTestCaseID(), row, 0);
	      setValueAt(data.getDescription(), row, 1);
	      setValueAt(data.getTestingType(), row, 2);
	      setValueAt(data.getCreationDateTime(), row, 3);
	      setValueAt(data.getLastTestedDateTime(), row, 4);
	      setValueAt(data.tested(), row, 5);
	      setValueAt(data.getExpectedResults(), row, 6);
	      setValueAt(data.getActualResults(), row, 7);
	      setValueAt(data.pass(), row, 8);
	}
}
