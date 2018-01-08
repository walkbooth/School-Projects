package edu.ncsu.csc216.pack_scheduler.course;

/**
 * A Conflict is an Object that can check if an Activity has a time conflict
 * with the object.
 * @author Alex Johnson
 */
public interface Conflict {
	
	/**
	 * Checks if an Activity has a time conflict with a Conflict Object.
	 * @param possibleConflictingActivity the Activity that is being check
	 * @throws ConflictException if the Activity has a time conflict
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
