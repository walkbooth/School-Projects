package edu.ncsu.csc316.customer_service.data;

/**
 * This class represents a single Timestamp object
 * @author Walker Booth (wgbooth)
 *
 */
public class Timestamp implements Comparable<Object> {

	/** The month the ticket was submitted */
	private Integer month;
	/** The day the ticket was submitted */
	private Integer day;
	/** The year the ticket was submitted */
	private Integer year;
	/** The hour the ticket was submitted */
	private Integer hour;
	/** The minute the ticket was submitted */
	private Integer minute;
	/** The second the ticket was submitted */	
	private Integer second;
	
	/**
	 * Constructor for a Timestamp object
	 * @param month the month the ticket was submitted
	 * @param day the day the ticket was submitted
	 * @param year the year the ticket was submitted
	 * @param hour the hour the ticket was submitted
	 * @param minute the minute the ticket was submitted
	 * @param second the second the ticket was submitted 
	 */
	public Timestamp (int month, int day, int year, int hour, int minute, int second) {
		this.month = month;
		this.day = day;
		this.year = year;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	/**
	 * Returns the month
	 * @return the month
	 */
	public int getMonth() {
		return month; 
	}

	/**
	 * Returns the day
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Returns the year
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Returns the hour
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Returns the minute
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Returns the second
	 * @return the second
	 */
	public int getSecond() {
		return second;
	}

	/**
	 * Compares two Timestamp objects 
	 * @param o the object to compare this object with 
	 * @return -1 if this object is less than o, 1 if this object is greater than o, 0 if this object is equal to o
	 */
	@Override
	public int compareTo(Object o) {
		Timestamp t = (Timestamp) o;
		if (year > t.getYear()) {
			return 1;
		} else if (year < t.getYear()) {
			return -1;
		} else {
			if (month > t.getMonth()) {
				return 1;
			} else if (month < t.getMonth()) {
				return -1;
			} else {
				if (day > t.getDay()) {
					return 1;
				} else if (day < t.getDay()) {
					return -1;
				} else {
					if (hour > t.getHour()) {
						return 1;
					} else if (hour < t.getHour()) {
						return -1;
					} else {
						if (minute > t.getMinute()) {
							return 1;
						} else if (minute < t.getMinute()) {
							return -1;
						} else {
							if (second > t.getSecond()) {
								return 1;
							} else if (second < t.getSecond()) {
								return -1;
							} else {
								return 0;
							}
						}
					}
				}
			}
			
		}
	}
	
	/**
	 * Returns the date as a string
	 * @return s the string version of the date components of this object
	 */
	public String dateString () {
		String s;
		if (month < 10) {
			s = "0";
		} else {
			s = "";
		}
		
		if (day < 10) {
			s = s.concat(month.toString().concat("/0"));
		} else {
			s = s.concat(month.toString().concat("/"));
		}
		
		s = s.concat(day.toString().concat("/"));
		s = s.concat(year.toString());
		return s;
	}
	
	/**
	 * Returns the time as a string
	 * @return s the string version of the time components of this object
	 */
	public String timeString () {
		String s;
		if (hour < 10) {
			s = "0";
		} else {
			s = "";
		}
		
		if (minute < 10) {
			s = s.concat(hour.toString().concat(":0"));
		} else {
			s = s.concat(hour.toString().concat(":"));
		}
		
		if (second < 10) {
			s = s.concat(minute.toString().concat(":0"));
		} else {
			s = s.concat(minute.toString().concat(":"));
		}
		s = s.concat(second.toString());
		
		return s;

	}
	
}
