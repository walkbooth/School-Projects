package edu.ncsu.csc216.bbtp.ui;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Date;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;

import edu.ncsu.csc216.bbtp.model.TestingType;
import edu.ncsu.csc216.bbtp.model.TestingTypeList;

/**
 * UI class for the pane with the tabs of TestCaseLists 
 * @author Walker Booth
 * @author Noah Trimble
 */
public class TestCaseEditPane extends JPanel implements Serializable, Observer {

	private static final long serialVersionUID = 5479139338455751629L;
	/** The data of a single test case */
	private TestCaseData data;
	/** The list of Testing Types */
	private TestingTypeList testingTypes;
	/** The component for the test case ID */
	private JTextField testCaseID;
	/** The component for the list of testing types */
	private JComboBox<TestingType> tcTestingType;
	/** The component for the expected results */
	private JTextArea expectedResults;
	/** The component for the actual results */
	private JTextArea actualResults;
	/** The component for the description of the test case */
	private JTextArea testCaseDescription;
	/** The component for the creation date of the test case */
	private JSpinner testCreationDate;
	/** The component for the last tested date of the test case */
	private JSpinner testLastTestedDate;
	/** The component for the boolean value of whether or not the test has been run */
	private JCheckBox tested;
	/** The component for the boolean value of whether or not the test is passing */
	private JCheckBox pass;
	/** Whether the pane is in add mode or not */
	private boolean add;
	/** Whether the pane is in edit mode or not */
	private boolean edit;
	
	/** 
	 * Constructor for a TestCaseEditPane without existing TestCaseData
	 * @param typeList the list of valid test types
	 */
	public TestCaseEditPane (TestingTypeList typeList) {
		this(new TestCaseData(), typeList);
	}
	
	/**
	 * Constructor for a TestCaseEditPane with existing TestCaseData
	 * @param data the current data of the test case
	 * @param typeList the list of valid test types 
	 */
	public TestCaseEditPane(TestCaseData data, TestingTypeList typeList) {
		super();
		add = false;
		edit = false;
		this.data = data;
		testingTypes = typeList;
		testingTypes.addObserver(this);
		init();
	}
	
	/**
	 * Initializes the GUI 
	 */
	private void init () {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black));
        initView();
        fillFields();
	}
	
	/**
	 * Initializes the view 
	 */
	private void initView () {
		// First row of test case edit pane
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel1.add(new JLabel("Test Case ID:"));
		panel1.add(getTestCaseID());
		panel1.add(new JLabel("Testing Type:"));
		panel1.add(getTestingType());
		panel1.add(new JLabel("Test Creation Date & Time"));
		panel1.add(getTestCreationDateSpinner());
		this.add(panel1);
		
		//Description row
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel2.add(new JLabel("Description:"));
		this.add(panel2);
		panel2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel2.add(getTestCaseDescription());
		this.add(panel2);
		
		//Tested row 
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel3.add(new JLabel("Tested?"));
		panel3.add(tested());
		panel3.add(new JLabel("Last Tested Date & Time:"));
		panel3.add(getLastTestedDateSpinner());
		this.add(panel3);
		
		//Expected results row
		panel2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel2.add(new JLabel("Expected Results:"));
		this.add(panel2);
		panel2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel2.add(getExpectedResults());
		this.add(panel2);
		
		//Actual Results row
		panel2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel2.add(new JLabel("Actual Results:"));
		this.add(panel2);
		panel2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel2.add(getActualResults());
		this.add(panel2);
		
		//Pass row 
		panel3 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel3.add(new JLabel("Pass?"));
		panel3.add(pass());
		this.add(panel3);

	}
	
	/**
	 * Returns the test creation date spinner 
	 * @return testCreationDate the spinner component for the testCreationDate
	 */
	protected JSpinner getTestCreationDateSpinner() {
		if(testCreationDate ==  null)
		{
			testCreationDate = new JSpinner(new SpinnerDateModel());
			testCreationDate.setEnabled(false);
			testCreationDate.setVisible(true);
		}
		return testCreationDate;
	}
	
	/**
	 * Returns the last tested date spinner
	 * @return testLastTestedDate the spinner component for the testLastTestedDate
	 */
	protected JSpinner getLastTestedDateSpinner() {
		if(testLastTestedDate ==  null)
		{
			testLastTestedDate = new JSpinner(new SpinnerDateModel());
			testLastTestedDate.setEnabled(false);
			testLastTestedDate.setVisible(true);
		}
		return testLastTestedDate;
	}
	
	/**
	 * Returns the creation date
	 * @return date the creation date of the test
	 */
	protected Date getTestCreationDate () {
		return data.getCreationDateTime();
	}
	
	/**
	 * Returns the lastTested date 
	 * @return date the last tested date of the test 
	 */
	protected Date getLastTestedDate () {
		return data.getLastTestedDateTime();
	}
	
	/**
	 * Returns the testCaseId text field 
	 * @return testCaseID the text field component for the testCaseID
	 */
	protected JTextField getTestCaseID () {
		if(testCaseID ==  null)
		{
			testCaseID = new JTextField(7);
			testCaseID.setEditable(false);
			testCaseID.setVisible(true);
			testCaseID.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return testCaseID;
	}
	
	/**
	 * Returns the test case description text area
	 * @return testCase the text area component for the testCaseDescription 
	 */
	protected JTextArea getTestCaseDescription () {
		if(testCaseDescription ==  null)
		{
			testCaseDescription = new JTextArea(10, 80);
			testCaseDescription.setEditable(false);
			testCaseDescription.setVisible(true);
			testCaseDescription.setLineWrap(true);
			testCaseDescription.setAutoscrolls(true);
		}
		return testCaseDescription;
	}
	
	/**
	 * Returns the Testing Types combo box 
	 * @return tcTestingType the combo box component for the testingTypes 
	 */
	protected JComboBox<TestingType> getTestingType() {
		if(tcTestingType ==  null)
		{
			tcTestingType = new JComboBox<TestingType>();
			for(int i = 0; i < testingTypes.size(); i++)
			{
				tcTestingType.addItem(testingTypes.getTestingTypeAt(i));
			}
			tcTestingType.setEnabled(false);
			tcTestingType.setVisible(true);
		}
		return tcTestingType;
	}
	
	/**
	 * Returns the expected results text area
	 * @return expectedResults the text area component for the expected results 
	 */
	protected JTextArea getExpectedResults () {
		if(expectedResults ==  null)
		{
			expectedResults = new JTextArea(10, 80);
			expectedResults.setEditable(false);
			expectedResults.setVisible(true);
			expectedResults.setLineWrap(true);
			expectedResults.setAutoscrolls(true);
		}
		return expectedResults;
	}
	
	/**
	 * Returns the actual results text area
	 * @return act the text area component for the actual results 
	 */
	protected JTextArea getActualResults () {
		if(actualResults ==  null)
		{
			actualResults = new JTextArea(10, 80);
			actualResults.setEditable(false);
			actualResults.setVisible(true);
			actualResults.setLineWrap(true);
            actualResults.setAutoscrolls(true);
		}
		return actualResults;
	}
	
	/**
	 * Returns the passing check box 
	 * @return pass the check box component for the passing field 
	 */
	protected JCheckBox pass () {
		if(pass ==  null)
		{
			pass = new JCheckBox();
			pass.setEnabled(false);
			pass.setVisible(true);
		}
		return pass;
	}
	
	/**
	 * Returns the tested check box 
	 * @return tested the check box component for the testing field 
	 */
	protected JCheckBox tested () {
		if(tested ==  null)
		{
			tested = new JCheckBox();
			tested.setEnabled(false);
			tested.setVisible(true);
		}
		return tested;
	}
	
	/**
	 * Sets the creation date 
	 * @param date the date to set as the creation date time 
	 */
	protected void setCreationDateTime(Date date) {
		data = new TestCaseData(data.getTestCaseID(), data.getDescription(), data.getTestingType(), 
				date, data.getLastTestedDateTime(), data.tested(), data.getExpectedResults(), 
				data.getActualResults(), data.pass());
	}
	
	/**
	 * Sets the last tested date
	 * @param date the date to set as the last tested date time 
	 */
	protected void setLastTestedDateTime(Date date) {
		data = new TestCaseData(data.getTestCaseID(), data.getDescription(), data.getTestingType(), 
				data.getCreationDateTime(), date, data.tested(), data.getExpectedResults(), 
				data.getActualResults(), data.pass());
	}
	
	/**
	 * Returns the add field
	 * @return add the boolean variable for add mode 
	 */
	protected boolean isAddMode () {
		return add;
	}
	
	/**
	 * Returns the edit field 
	 * @return edit the boolean variable for edit mode 
	 */
	protected boolean isEditMode () {
		return edit;
	}
	
	/**
	 * Sets add to true
	 */
	protected void enableAdd() {
		if (!isAddMode()) {
			add = true;
			edit = false;
			clearFields();
		}
	}
	
	/**
	 * Sets add to false
	 */
	protected void disableAdd() {
		add = false;
		clearFields();
	}
	
	/**
	 * Sets edit to true
	 * @param data the data of the current test case 
	 */
	protected void enableEdit(TestCaseData data) {
		if (!isEditMode()) {
			edit = true;
			add = false;
			this.data = data;
			fillFields();
		}
	}
	
	/**
	 * Sets edit to false
	 */
	protected void disableEdit() {
		edit = false;
		clearFields();
	}
	
	/**
	 * Returns the state of the fields as a boolean
	 * @return true if all fields are empty, false if not 
	 */
	protected boolean fieldsNotEmpty() {
		return getTestCaseDescription().getDocument().getLength() != 0 &&
				getTestingType().getSelectedItem() != null &&
				getTestCreationDateSpinner().getValue() != null &&
				getExpectedResults().getDocument().getLength() != 0;
	}
	
	/**
	 * Updates the test case data field 
	 * @param data the new data to set the data field to 
	 */
	protected void setTestCaseData (TestCaseData data) {
		this.data = data;
		fillFields();
	}
	
	/**
	 * Attaches a new field listener 
	 * @param l the event listener to attach 
	 */
	protected void addFieldListener (EventListener l) {
		getTestingType().addActionListener((ActionListener) l);
		getExpectedResults().getDocument().addDocumentListener((DocumentListener) l);
		getActualResults().getDocument().addDocumentListener((DocumentListener) l);
		getTestCaseDescription().getDocument().addDocumentListener((DocumentListener) l);
		getTestCreationDateSpinner().addChangeListener((ChangeListener) l);
		getLastTestedDateSpinner().addChangeListener((ChangeListener) l);
		tested().addActionListener((ActionListener) l);
		pass().addActionListener((ActionListener) l);
	}
	
	/**
	 * Fills all fields 
	 */
	protected void fillFields () {
		if (null == data) {
			//Sets the values of the fields to default
			testCaseID.setText("");
			tcTestingType.setSelectedItem(null);
			expectedResults.setText("");
			actualResults.setText("");
			testCaseDescription.setText("");
			testCreationDate.setValue(new Date(System.currentTimeMillis()));
			testLastTestedDate.setValue(new Date(System.currentTimeMillis()));
			tested.setSelected(false);
			pass.setSelected(false);
			
			//Set editability to false for all fields  
			tcTestingType.setEnabled(false);
			expectedResults.setEditable(false);
			actualResults.setEditable(false);
			testCaseDescription.setEditable(false);
			testCreationDate.setEnabled(false);
			testLastTestedDate.setEnabled(false);
			tested.setEnabled(false);
			pass.setEnabled(false);
			
		} else {
			//Sets the values of the fields to values of data
			testCaseID.setText(data.getTestCaseID());
			tcTestingType.setSelectedItem(data.getTestingType());
			expectedResults.setText(data.getExpectedResults());
			actualResults.setText(data.getActualResults());
			testCaseDescription.setText(data.getDescription());
			if(data.getCreationDateTime() != null)
				testCreationDate.setValue(data.getCreationDateTime());
			if(data.getLastTestedDateTime() != null)
				testLastTestedDate.setValue(data.getLastTestedDateTime());
			tested.setSelected(data.tested());
			pass.setSelected(data.pass());
			
		}
		
		if (isAddMode() || isEditMode()) {
			tcTestingType.setEnabled(true);
			expectedResults.setEditable(true);
			actualResults.setEditable(true);
			testCaseDescription.setEditable(true);
			testCreationDate.setEnabled(true);
			testLastTestedDate.setEnabled(true);
			tested.setEnabled(true);
			pass.setEnabled(true);
		}
	}
	
	/**
	 * Clears all fields 
	 */
	protected void clearFields () {
		data = null;
		fillFields();
	}
	
	/**
	 * Gets the data in all fields 
	 * @return data the test case data collected from the fields 
	 */
	protected TestCaseData getFields () {
		return new TestCaseData(getTestCaseID().getText(), getTestCaseDescription().getText(), (TestingType) getTestingType().getSelectedItem(), 
								(Date) getTestCreationDateSpinner().getValue(), (Date) getLastTestedDateSpinner().getValue(), tested().isSelected(), 
								getExpectedResults().getText(), getActualResults().getText(), pass().isSelected());
	}
	
	/**
	 * Updates the observers by updating the testingTypes list
	 * @param obs the updated testingTypes list
	 * @param o optional parameter passed as part of the update method
	 */
	public void update (Observable obs, Object o) {
		testingTypes = (TestingTypeList) obs;
		tcTestingType.removeAllItems();
		for(int i = 0; i < testingTypes.size(); i++)
		{
			tcTestingType.addItem(testingTypes.getTestingTypeAt(i));
		}
	}		
}