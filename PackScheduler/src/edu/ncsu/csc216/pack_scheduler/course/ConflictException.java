package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Thrown when an Activity is atempted to be planned during the time period of
 * another Activity.
 * @author Alex Johnson
 */
public class ConflictException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;
	/** The default message of a ConflictException */
	private static final String DEFAULT_MESSAGE = "Schedule conflict.";
	
	/**
	 * Constructs a ConflictException with the default message
	 */
	public ConflictException () {
		this(DEFAULT_MESSAGE);
	}
	
	/**
	 * Constructs a ConflictException for a given Message
	 * @param message the message to give the Exception
	 */
	public ConflictException (String message) {
		super(message);
	}
	
	
}
