package edu.ncsu.csc216.backlog.command;

/**
 * The Command class stores information about an update command that will
 * be provided to a TaskItem to update its state. 
 * @author Walker Booth
 * @author Brian Morris
 */
public class Command {

	/** The update details provided in a note */
	private String note;
	/** The author of the note */
	private String noteAuthor;
	/** The value of the command */
	private CommandValue c;
	
	/**
	 * Contains the types of commands a TaskItem may be given.
	 */
	public static enum CommandValue { BACKLOG, CLAIM, PROCESS, VERIFY, COMPLETE, REJECT }
	
	/**
	 * The constructor for the command class initializes values for all fields. If any
	 * of the parameters are null or an empty String, an IllegalArgumentException is thrown.
	 * @param c the current CommandValue for the given command
	 * @param noteAuthor the author of the note
	 * @param noteText the text of the note
	 * @throws IllegalArgumentException if any of the provided parameters are null or an empty String.
	 */
	public Command(CommandValue c, String noteAuthor, String noteText) {
		if (c == null) {
			throw new IllegalArgumentException("Parameters must not be null or empty.");
		}
		
		if (noteAuthor == null || noteAuthor.isEmpty()) {
			throw new IllegalArgumentException("Parameters must not be null or empty.");
		}
		
		if (noteText == null || noteText.isEmpty()) {
			throw new IllegalArgumentException("Parameters must not be null or empty.");
		}
		
		this.c = c;
		this.noteAuthor = noteAuthor;
		this.note = noteText;
	}
	
	/**
	 * Returns the CommandValue of this Command.
	 * @return the CommandValue of this Command
	 */
	public CommandValue getCommand () {
		return c;
	}
	
	/**
	 * Returns the note text of this Command.
	 * @return the note text of this Command
	 */
	public String getNoteText () {
		return note;
	}
	
	/**
	 * Returns the author of the note.
	 * @return the author of the note
	 */
	public String getNoteAuthor () {
		return noteAuthor;
	}

}
