package edu.ncsu.csc216.backlog.command;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.backlog.command.Command.CommandValue;

/** 
 * Tests the Command class for completion. 
 * @author Brian Morris
 * @author Walker Booth
 *
 */
public class CommandTest {

	/** An example of a valid Command Value */
	private static final CommandValue VALID_COMMAND = CommandValue.BACKLOG;
	/** An example of a valid note author */
	private static final String VALID_NOTE_AUTHOR = "wgbooth";
	/** An example of a valid note */
	private static final String VALID_NOTE_TEXT = "This is a note.";
	
	/**
	 * Tests the Command class as a whole, using the constructors and getters. 
	 */
    @SuppressWarnings("unused")
	@Test
    public void testCommand() {
    	try {
    		Command c = new Command(null, VALID_NOTE_AUTHOR, VALID_NOTE_TEXT);
    		fail();
    	}
    	catch (IllegalArgumentException e) {
    		assertEquals("Parameters must not be null or empty.", e.getMessage());
    	}
    	
    	try {
    		Command c = new Command(VALID_COMMAND, null, VALID_NOTE_TEXT);
    		fail();
    	}
    	catch (IllegalArgumentException e) {
    		assertEquals("Parameters must not be null or empty.", e.getMessage());
    	}
    	
    	try {
    		Command c = new Command(VALID_COMMAND, "", VALID_NOTE_TEXT);
    		fail();
    	}
    	catch (IllegalArgumentException e) {
    		assertEquals("Parameters must not be null or empty.", e.getMessage());
    	}
    	
    	try {
    		Command c = new Command(VALID_COMMAND, VALID_NOTE_AUTHOR, null);
    		fail();
    	}
    	catch (IllegalArgumentException e) {
    		assertEquals("Parameters must not be null or empty.", e.getMessage());
    	}
    	
    	try {
    		Command c = new Command(VALID_COMMAND, VALID_NOTE_AUTHOR, "");
    		fail();
    	}
    	catch (IllegalArgumentException e) {
    		assertEquals("Parameters must not be null or empty.", e.getMessage());
    	}
    	
    	Command c = new Command(VALID_COMMAND, VALID_NOTE_AUTHOR, VALID_NOTE_TEXT);
    	assertEquals(VALID_COMMAND, c.getCommand());
    	assertEquals(VALID_NOTE_AUTHOR, c.getNoteAuthor());
    	assertEquals(VALID_NOTE_TEXT, c.getNoteText());
    	c = new Command(Command.CommandValue.CLAIM, VALID_NOTE_AUTHOR, VALID_NOTE_TEXT);
    	assertEquals(Command.CommandValue.CLAIM, c.getCommand());
    	c = new Command(Command.CommandValue.COMPLETE, VALID_NOTE_AUTHOR, VALID_NOTE_TEXT);
        assertEquals(Command.CommandValue.COMPLETE, c.getCommand());
        c = new Command(Command.CommandValue.PROCESS, VALID_NOTE_AUTHOR, VALID_NOTE_TEXT);
        assertEquals(Command.CommandValue.PROCESS, c.getCommand());
        c = new Command(Command.CommandValue.REJECT, VALID_NOTE_AUTHOR, VALID_NOTE_TEXT);
        assertEquals(Command.CommandValue.REJECT, c.getCommand());
        c = new Command(Command.CommandValue.VERIFY, VALID_NOTE_AUTHOR, VALID_NOTE_TEXT);
        assertEquals(Command.CommandValue.VERIFY, c.getCommand());
        Command.CommandValue.valueOf(Command.CommandValue.BACKLOG.toString());
        Command.CommandValue.valueOf(Command.CommandValue.CLAIM.toString());
        Command.CommandValue.valueOf(Command.CommandValue.COMPLETE.toString());
        Command.CommandValue.valueOf(Command.CommandValue.PROCESS.toString());
        Command.CommandValue.valueOf(Command.CommandValue.REJECT.toString());
        Command.CommandValue.valueOf(Command.CommandValue.VERIFY.toString());
    	
    }

}
