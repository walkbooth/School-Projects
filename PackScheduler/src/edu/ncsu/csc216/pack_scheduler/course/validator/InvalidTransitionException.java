package edu.ncsu.csc216.pack_scheduler.course.validator;
/**
 * Thrown when the FSM reaches an invalid transition
 * @author Alex Johnson
 * @author James Ritchey
 * @author Connor McCarthy 
 *
 */
public class InvalidTransitionException extends Exception {
	/**Default UID*/
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor with the default message
	 */
	public InvalidTransitionException() {
		super("Invalid FSM Transition.");
	}
	/**
	 * Constructor with the custom message
	 * @param message The custom error message to display
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
}
