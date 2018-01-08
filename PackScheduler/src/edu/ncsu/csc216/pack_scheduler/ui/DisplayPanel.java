package edu.ncsu.csc216.pack_scheduler.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Creates a user interface for displaying, revising and exporting a student's schedule
 * 
 * @author Sarah Heckman
 * @author Alex Johnson
 */
public class DisplayPanel extends JPanel implements ActionListener {
	/** ID used for object serialization */
	private static final long serialVersionUID = 1L;
	/** JTable for displaying the directory of Faculty */
	private JTable tableFacultyDirectory;
	/** Scroll pane for table */
	private JScrollPane scrollFacultyDirectory;
	/** TableModel for directory of Faculty */
	private CourseTableModel facultyDirectoryTableModel;
	/** Button for revising the Student's schedule */
	private JButton btnRevise;
	/** Button for exporting the schedule */
	private JButton btnExport;
	/** The current student logged in */
	private Student currentUser;
	/** Reference to student's Schedule */
	private Schedule schedule;
	
	/**
	 * Constructs the and sets up the GUI components. Code from FacultyDirectoryPanel.
	 */
	public DisplayPanel() {
		super(new GridBagLayout());
		
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder boarder = BorderFactory.createTitledBorder(lowerEtched, "Directory Buttons");
		
		facultyDirectoryTableModel = new CourseTableModel();
		tableFacultyDirectory = new JTable(facultyDirectoryTableModel);
		tableFacultyDirectory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableFacultyDirectory.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableFacultyDirectory.setFillsViewportHeight(true);
		
		scrollFacultyDirectory = new JScrollPane(tableFacultyDirectory, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Faculty Directory");
		scrollFacultyDirectory.setBorder(boarder);
		scrollFacultyDirectory.setToolTipText("Faculty Directory");
		
		//Set up Faculty buttons
		btnRevise = new JButton("Add Faculty");
		btnRevise.addActionListener(this);
		btnExport = new JButton("Remove Faculty");
		btnExport.addActionListener(this);
		
		JPanel pnlFacultyButtons = new JPanel();
		pnlFacultyButtons.setLayout(new GridLayout(1, 2));
		pnlFacultyButtons.add(btnRevise);
		pnlFacultyButtons.add(btnExport);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Schedule Controls");
		pnlFacultyButtons.setBorder(boarder);
		pnlFacultyButtons.setToolTipText("Schedule Controls");
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(scrollFacultyDirectory, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = .5;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(pnlFacultyButtons, c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRevise) {
			System.out.println("Revise schedule");
		} else if (e.getSource() == btnExport) {
			System.out.println("export schedule");
		}

		// cardLayout.show(panel, STUDENT_PANEL);
		this.validate();
		this.repaint();
	}
	
	/**
	 * {@link CourseTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of {@link Course}s to the user.
	 * @author Sarah Heckman
	 */
	private class CourseTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Name", "Section", "Title", "Meeting Days", "Open Seats"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link CourseTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public CourseTableModel() {
			updateData();
		}

		/**
		 * Returns the number of columns in the table.
		 * @return the number of columns in the table.
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * @return the number of rows in the table.
		 */
		public int getRowCount() {
			if (data == null) 
				return 0;
			return data.length;
		}
		
		/**
		 * Returns the column name at the given index.
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * @return the data at the given location.
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}
		
		/**
		 * Sets the given value to the given {row, col} location.
		 * @param value Object to modify in the data.
		 * @param row location to modify the data.
		 * @param column location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
		
		/**
		 * Updates the given model with {@link Course} information from the {@link WolfScheduler}.
		 */
		private void updateData() {
			currentUser = (Student)RegistrationManager.getInstance().getCurrentUser();
			if (currentUser != null) {
				schedule = currentUser.getSchedule();
				data = schedule.getScheduledCourses();
					
				DisplayPanel.this.repaint();
				DisplayPanel.this.validate();
			}
			
		}
	}

}
