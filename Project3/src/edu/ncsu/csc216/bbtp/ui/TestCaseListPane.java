package edu.ncsu.csc216.bbtp.ui;

import java.awt.Color;


import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import edu.ncsu.csc216.bbtp.model.TestCaseList;

/**
 * The GUI pane for test case lists
 * @author Walker Booth
 * @author Noah Trimble 
 */
public class TestCaseListPane extends JScrollPane implements Serializable, Observer {
	
	private static final long serialVersionUID = -2210716111020406799L
;
	/** The list of test cases */
	private TestCaseList testCases;
	 /** TestCaseTableModel which displays the list of TestCases */
    private TestCaseTableModel testCaseTableModel;
	/** The JTable component */
	private JTable table;
	/** The widths of each column */
	private int[] colWidths = {50, 325, 75, 200, 200, 50, 75, 75, 50};
	
	/**
	 * Constructor for a TestCaseListPane
	 * @param testLists all of the test lists 
	 */
	public TestCaseListPane(TestCaseList testLists) {
		super();
		testCases = testLists;
		// Register as an observer of TestingTypeList so that the pane is
        // updated of
        // any changes to the TestingTypeList.
        this.testCases.addObserver(this);
        testCaseTableModel = new TestCaseTableModel(testLists.get2DArray());
        initView();
	}
	
	/**
	 * Returns the testCaseTableModel
	 * @return testCaseTableModel the testCaseTableModel in use 
	 */
	public TestCaseTableModel getTestCaseTableModel () {
		return testCaseTableModel;
	}
	
	/**
	 * Returns the JTable with TestCaseLists
	 * @return table the JTable component
	 */
	public JTable getTable () {
		return table;
	}
	
	/**
	 * Initializes the view 
	 */
	private void initView () {
		// Associates the TestCaseTableModel with the JTable.
        // The TestCaseTableModel contains the data that the JTable will
        // display.
        table = new JTable(testCaseTableModel);
        // Set up the column widths so the table will look nice.
        for (int i = 0; i < colWidths.length; i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setPreferredWidth(colWidths[i]);
        }
        // Set the table so that only one row can be selected at a time.
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(false);
        setViewportView(table);
        setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Clears the current selector 
	 */
	public void clearSelection () {
		table.clearSelection();
	}
	
	/**
     * This method is called by the observed object, whenever the observed
     * object is changed. In this case, the observed object is the TestCaseList.
     * Any changes to the TestCaseList will lead to an update of the
     * TestCaseTableModel.
     * 
     * @param o the observable object
     * @param arg any additional information needed about the change.
     */
    public void update(Observable o, Object arg) {
        if (o instanceof TestCaseList) {
            TestCaseList ttl = (TestCaseList) o;
            // If there is a different number of rows, create a show new
            // TestCaseTableModel.
            if (ttl.size() != testCaseTableModel.getRowCount()) {
                testCaseTableModel = new TestCaseTableModel(ttl.get2DArray());
                table.setModel(testCaseTableModel);
            } else {
                // Otherwise, just update the values directly.
                Object[][] arr = ttl.get2DArray();
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < testCaseTableModel.getColumnCount(); j++) {
                        testCaseTableModel.setValueAt(arr[i][j], i, j);
                    }
                }
            }
        }
    }
}
