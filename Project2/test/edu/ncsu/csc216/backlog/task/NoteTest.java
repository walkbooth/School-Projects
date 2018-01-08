package edu.ncsu.csc216.backlog.task;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Note class for completion.
 * @author Brian Morris
 * @author Walker Booth
 *
 */
public class NoteTest {

	/** Example of a valid note author used for testing */
    private static final String VALID_NOTE_AUTHOR = "wgbooth";
    /** Example of a valid note used for testing*/
	private static final String VALID_NOTE_TEXT = "This is an example note.";

	/**
     * Tests the Note constructor. 
     */
	@SuppressWarnings("unused")
	@Test
    public void testNote() {
		try {
			Note n = new Note(null, VALID_NOTE_TEXT);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Parameters cannot be null or empty.", e.getMessage());
		}
		
		try {
			Note n = new Note("", VALID_NOTE_TEXT);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Parameters cannot be null or empty.", e.getMessage());
		}
		
		try {
			Note n = new Note(VALID_NOTE_AUTHOR, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Parameters cannot be null or empty.", e.getMessage());
		}
		
		try {
			Note n = new Note(VALID_NOTE_AUTHOR, "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Parameters cannot be null or empty.", e.getMessage());
		}
		
		Note n = new Note (VALID_NOTE_AUTHOR, VALID_NOTE_TEXT);
		assertEquals(VALID_NOTE_AUTHOR, n.getNoteAuthor());
		assertEquals(VALID_NOTE_TEXT, n.getNoteText());
	}
	
	/**
	 * Tests the getNoteArray method.
	 */
	@Test
	public void testGetNoteArray () {
		Note n = new Note (VALID_NOTE_AUTHOR, VALID_NOTE_TEXT);
		assertEquals(VALID_NOTE_AUTHOR, n.getNoteArray()[0]);
		assertEquals(VALID_NOTE_TEXT, n.getNoteArray()[1]);
	}

}
