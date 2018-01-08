package edu.ncsu.csc216.pack_scheduler.ui;

import java.awt.Dimension;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * The user interface for the faculty schedule panel 
 * @author Walker Booth
 * @author Alex Johnson
 * @author Yuquan Cui
 */
public class FacultySchedulePanel extends JPanel {

	/** The id for serialization  */
	private static final long serialVersionUID = 1L;
	/** JTable for displaying schedule*/
	private JTable tableSchedule;
	/** JTable for displaying course roll*/
	private JTable tableRoll;
	/** faculty course table model*/
	private CourseTableModel scheduleTableModel; 
	/** faculty directory schedule roll model*/
	private CourseRollTableModel rollTableModel;
	/** etched border*/
	private Border lowerEtched;
	/** scroll for schedule*/
	private JScrollPane scrollSchedule;
	/** scroll for faculty roll*/
	private JScrollPane scrollRoll;
	/** course details JPanel*/
	private JPanel pnlCourseDetails;
	/** Course name*/
	private JLabel lblNameTitle = new JLabel("Name: ");
	/** Course section*/
	private JLabel lblSectionTitle = new JLabel("Section: ");
	/** Course title*/
	private JLabel lblTitleTitle = new JLabel("Title: ");
	/** JLable for instructor*/
	private JLabel lblInstructorTitle = new JLabel("Instructor: ");
	/** JLable for course credits*/
	private JLabel lblCreditsTitle = new JLabel("Credits: ");
	/** meeting days*/
	private JLabel lblMeetingTitle = new JLabel("Meeting days: ");
	/** enrollment capacity*/
	private JLabel lblEnrollmentCapTitle = new JLabel("Enrollment cap: ");
	/** open seats*/
	private JLabel lblOpenSeatsTitle = new JLabel("Openseats: ");
	/** course wait list*/
	private JLabel lblWaitlistTitle = new JLabel("Waitlist: ");
	/** course name*/
	private JLabel lblName = new JLabel("");
	/** course section*/
	private JLabel lblSection = new JLabel("");
	/** course title*/
	private JLabel lblTitle = new JLabel("");
	/** course instructor*/
	private JLabel lblInstructor = new JLabel("");
	/** course credits*/
	private JLabel lblCredits = new JLabel("");
	/** course meeting days*/
	private JLabel lblMeeting = new JLabel("");
	/** course enrollment capacity*/
	private JLabel lblEnrollmentCap = new JLabel("");
	/** course open seats*/
	private JLabel lblOpenSeats = new JLabel("");
	/** course wait list*/
	private JLabel lblWaitlist = new JLabel("");
	/** Current user */
	private Faculty currentUser;
	/** faculty schedule*/
	private FacultySchedule schedule;
	/** course catalog*/
	private CourseCatalog catalog;
	/** The course currently being edited */
	private Course course;
	
	/**
	 * Initializes the faculty schedule panel 
	 */
	public FacultySchedulePanel () {
		super(new GridBagLayout());
		
		RegistrationManager manager = RegistrationManager.getInstance();
		
		currentUser = (Faculty) manager.getCurrentUser();
		catalog = manager.getCourseCatalog();
		lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		
		/* Faculty schedule table */
		initFacultySchedule();
		/* Course details */
		initCourseDetails();
		/* Course Roll table */
		initCourseRoll();
		
		this.add(scrollSchedule);
		this.add(pnlCourseDetails);
		this.add(scrollRoll);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollSchedule, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlCourseDetails, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollRoll, c);

		updateTables();
	}
	
	/**
	 * Initializes the Faculty schedule
	 */
	private void initFacultySchedule () {
		
		scheduleTableModel = new CourseTableModel();
		tableSchedule = new JTable(scheduleTableModel);
		tableSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableSchedule.setFillsViewportHeight(true);

		scrollSchedule = new JScrollPane(tableSchedule, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollSchedule.setBorder(BorderFactory.createTitledBorder(lowerEtched, "Faculty Schedule"));
		scrollSchedule.setToolTipText("Faculty Schedule");
		tableSchedule.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String name = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 0).toString();
				String section = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 1).toString();
				course = catalog.getCourseFromCatalog(name, section);
				updateCourseDetails(course);
			}
			
		});
	}

	/**
	 * Initializes the Course details
	 */
	private void initCourseDetails () {
		pnlCourseDetails = new JPanel();
		pnlCourseDetails.setLayout(new GridLayout(5, 1));
		JPanel pnlName = new JPanel(new GridLayout(1, 4));
		pnlName.add(lblNameTitle);
		pnlName.add(lblName);
		pnlName.add(lblSectionTitle);
		pnlName.add(lblSection);
		
		JPanel pnlTitle = new JPanel(new GridLayout(1, 1));
		pnlTitle.add(lblTitleTitle);
		pnlTitle.add(lblTitle);
		
		JPanel pnlInstructor = new JPanel(new GridLayout(1, 4));
		pnlInstructor.add(lblInstructorTitle);
		pnlInstructor.add(lblInstructor);
		pnlInstructor.add(lblCreditsTitle);
		pnlInstructor.add(lblCredits);
		
		JPanel pnlMeeting = new JPanel(new GridLayout(1, 1));
		pnlMeeting.add(lblMeetingTitle);
		pnlMeeting.add(lblMeeting);
		
		JPanel pnlEnrollment = new JPanel(new GridLayout(1, 4));
		pnlEnrollment.add(lblEnrollmentCapTitle);
		pnlEnrollment.add(lblEnrollmentCap);
		pnlEnrollment.add(lblOpenSeatsTitle);
		pnlEnrollment.add(lblOpenSeats);
		pnlEnrollment.add(lblWaitlistTitle);
		pnlEnrollment.add(lblWaitlist);
		
		pnlCourseDetails.add(pnlName);
		pnlCourseDetails.add(pnlTitle);
		pnlCourseDetails.add(pnlInstructor);
		pnlCourseDetails.add(pnlMeeting);
		pnlCourseDetails.add(pnlEnrollment);
		
		TitledBorder borderCourseDetails = BorderFactory.createTitledBorder(lowerEtched, "Course Details");
		pnlCourseDetails.setBorder(borderCourseDetails);
		pnlCourseDetails.setToolTipText("Course Details");
	}
	
	/**
	 * Initializes the Course Roll
	 */
	private void initCourseRoll () {
		rollTableModel = new CourseRollTableModel();
		tableRoll = new JTable(rollTableModel);
		tableRoll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableRoll.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableRoll.setFillsViewportHeight(true);
		
		scrollRoll = new JScrollPane(tableRoll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollRoll.setBorder(BorderFactory.createTitledBorder(lowerEtched, "Course Roll"));
		scrollRoll.setToolTipText("Course Roll");
	}
	
	/**
	 * Updates the data within each of the tables
	 */
	public void updateTables () {
		scheduleTableModel.updateData();
		rollTableModel.updateData();
	}
	
	/**
	 * update course details
	 * @param c updated course
	 */
	private void updateCourseDetails (Course c) {
		if (c != null) {
			lblName.setText(c.getName());
			lblSection.setText(c.getSection());
			lblTitle.setText(c.getTitle());
			lblInstructor.setText(c.getInstructorId());
			lblCredits.setText("" + c.getCredits());
			lblMeeting.setText(c.getMeetingString());
			lblEnrollmentCap.setText("" + c.getCourseRoll().getEnrollmentCap());
			lblOpenSeats.setText("" + c.getCourseRoll().getOpenSeats());
		}
		
		rollTableModel.updateData();
	}
	
	/**
	 * {@link CourseTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of {@link Course}s to the user.
	 * @author Sarah Heckman
	 * @author Walker Booth
	 * @see StudentRegistrationPanel.java
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
			currentUser = (Faculty)RegistrationManager.getInstance().getCurrentUser();
			if (currentUser != null) {
				schedule = currentUser.getSchedule();
				data = schedule.getScheduledCourses();

				FacultySchedulePanel.this.repaint();
				FacultySchedulePanel.this.validate();
			}
		}
}
	/**
	 * {@link CourseRollTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Faculty to the user.
	 * @author Sarah Heckman
	 * @author Walker Booth
	 * @see FacultyDirectoryPanel
	 */
	private class CourseRollTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"First Name", "Last Name", "Student ID"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link CourseRollTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public CourseRollTableModel() {
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
		 * Updates the given model with {@link Faculty} information from the {@link FacultyDirectory}.
		 * @param roll the CourseRoll to update to
		 */
		public void updateData() {
			if (course != null) {
				CourseRoll roll = course.getCourseRoll();
				Object[][] display = new Object[roll.getEnrollmentCap() - roll.getOpenSeats()][];
				int fill = 0;
				Object[][] dir = RegistrationManager.getInstance().getStudentDirectory().getStudentDirectory();
				for (int i = 0; i < dir.length; i++) {
					Student cur = RegistrationManager.getInstance().getStudentDirectory().getStudentById((String) dir[i][2]);
					if (!roll.canEnroll(cur)) {
						display[fill++] = new Object[]{cur.getFirstName(), cur.getLastName(), cur.getId()};
					}
				}
				data = display;
				
				FacultySchedulePanel.this.repaint();
				FacultySchedulePanel.this.validate();
			}
		}
	}
	
}
