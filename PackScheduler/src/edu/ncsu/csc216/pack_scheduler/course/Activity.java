package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Represents a scheduled Activity on a calendar and has a title, 
 * meeting times and meeting days.
 * @author Alex Johnson
 */
public abstract class Activity implements Conflict {

	/** Military time for the largest time possible */
	private static final int UPPER_TIME = 2359;
	/** Minutes in an hour */
	private static final int MINUTES_IN_HOUR = 60;
	/** Hours in a day */
	private static final int UPPER_HOUR = 24;
	/** Activity title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Activity starting time */
	private int startTime;
	/** Activity ending time */
	private int endTime;

	/** 
	 * Constructs an Activity 
	 * @param title title of the activity
	 * @param meetingDays the meeting days of the activity
	 * @param startTime the start time of the activity
	 * @param endTime the end time of the activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super(); 
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime); 
	}

	/**
	 * Gets the title of the Activity.
	 * @return the title of the Activity
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the Activity. Title must be a string with at least 1 character.
	 * @param title the title to set the Activity title to
	 * @throws IllegalArgumentException if title is null or an empty string
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Invalid title");
		}
		if (title.equals("")) {
			throw new IllegalArgumentException("Invalid title");
		}
		this.title = title;
	}

	/**
	 * Gets the meeting days of the Activity.
	 * @return the meetingDays of the Activity
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the meeting days of the Activity. Whether the String is valid is up to the implementation 
	 * of the Activity.
	 * @param meetingDays the String to set meeting days to
	 * @throws IllegalArgumentException if the meeting days string consists is null or the empty string. 
	 */
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		this.meetingDays = meetingDays; 
	}

	/** 
	 * Generates a formatted string with the meeting days and the time. Or that meeting times are arranged.
	 * @return a formatted string with the days of the week and time of class, or 'Arranged' if the meeting days
	 * are arranged.
	 */
	public String getMeetingString() {
		String md = getMeetingDays();
		if (md.equals("A")) {
			return "Arranged";
		}
		return  md + " " + militaryToStandard(getStartTime()) + "-" + militaryToStandard(getEndTime());
	}

	/**
	 * Sets the start and end time of the Activity. Times must be military and between 0 and 2400. Start 
	 * must be before end time. No Activities that start before midnight and end after.
	 * @param startTime the start time of the Activity
	 * @param endTime the end time of the Activity
	 * @throws IllegalArgumentException if a time isn't a valid military time or the start time is after the 
	 * end time
	 */
	public void setActivityTime(int startTime, int endTime) {
		// if they can be converted to military time then they are valid
		try {
			militaryToStandard(startTime);
			militaryToStandard(endTime);
		} catch (IllegalArgumentException e) {
			// try-catch so it's thrown from this method
			throw new IllegalArgumentException(e.getMessage());
		}
		
		if (startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Converts military time to a formatted standard time String. First two digits cannot be less than
	 * 00xx or greater than 23xx. The last two digits must be between xx00 and xx59. Private utility 
	 * function for Course.
	 * @param time the military time to format into a readable time
	 * @returns military standard time in a string
	 * @throws IllegalArgumentException if the time isn't a valid military time
	 */
	private String militaryToStandard(int time) {
		
		int hour = 0;
		int min = 0;
		boolean morning = true;
		
		// time must be between 0-2359
		if (time < 0 || time > UPPER_TIME) {
			throw new IllegalArgumentException("Invalid Time: " + time);
		}
	
		min = time % 100; 
		
		/*
		 * Last 2 digits of time must be 0-60, we already know times greater than 0 so no need to
		 * check lesser than 0 
		 */
		if (min > MINUTES_IN_HOUR - 1) {
			throw new IllegalArgumentException("Invalid minutes: " + min);
		}
		
		hour = (time - min) / 100 % (UPPER_HOUR / 2);
		
		// if 12:00 AM or 12:00 PM
		if (hour == 0) {
			hour = 12;
		}
		
		morning = time < 1200;
		
		// if min is just one digit print an extra 0 in front of it
		if (min < 10) {
			return hour + ":0" + min + (morning ? "AM" : "PM");
		} else {
			return hour + ":" + min + (morning ? "AM" : "PM");
		}
	}

	/**
	 * Gets the start time of the Activity in military time.
	 * @return the start time of the Activity
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Gets the end time of the course in military time.
	 * @return the end time of the course
	 */
	public int getEndTime() {
		return endTime;
	}
	
	/**
	 * Generates and returns a short array of Strings that is used for displaying 
	 * the Activity.
	 * @return the short Array of Activity display data
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Generates and returns a long array of Strings that is used for displaying 
	 * the Activity.
	 * @return the long Array of Activity display data
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Checks to see if an Activity is a duplicate.
	 * @param activity the Activity to test 
	 * @return whether the given Activity is a duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Checks if a separate Activity has a time conflict with this Activity.
	 * @param possibleConflictingActivity a Activity that could have a time conflict with the Activity
	 * @throws ConflictException if there is a time conflict between Activities
	 * @throws IllegalArgumentException if possibleConflictingActivity is null
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		// Test for null possibleConflictingActivity
		if (possibleConflictingActivity == null) {
			throw new IllegalArgumentException("possibleConflictingActivity is null.");
		}
		// Arranged cannot conflict
		if (possibleConflictingActivity.getMeetingDays().equals("A") ||
				getMeetingDays().equals("A")) {
			return;  
		}
		
		// Test for each day of the week
		for (Character a : this.getMeetingDays().toCharArray()) {
			if (possibleConflictingActivity.getMeetingDays().contains(a.toString())) {
				if (possibleConflictingActivity.startTime >= startTime // if starts during
						&& possibleConflictingActivity.startTime <= endTime) {
					// "Schedule conflict: Activity starts during " + title
					throw new ConflictException();
				} else if (possibleConflictingActivity.endTime >= startTime // if ends during
						&& possibleConflictingActivity.endTime <= endTime) {
					// "Schedule conflict: Activity ends during " + title
					throw new ConflictException();
				} else if (possibleConflictingActivity.startTime <= startTime // if encapsulates event
						&& possibleConflictingActivity.endTime >= endTime) {
					// "Schedule conflict: Activity is during " + title
					throw new ConflictException();
				}
			}
		}
	}

	/**
	 * Generates a unique int for the object based on Activity's state.
	 * @return a unique int based on the objects state.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + meetingDays.hashCode();
		result = prime * result + startTime;
		result = prime * result + title.hashCode();
		return result;
	}

	/**
	 * Compares two Activities to see if they are the same one. Compares type of object, title,
	 * meeting days, start time and end time.
	 * @return whether the Activities are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Activity))
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (!title.equals(other.title))
			return false;
		return true;
	}

}