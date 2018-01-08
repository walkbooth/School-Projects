package edu.ncsu.csc216.backlog.task;

/**
 * The Note class contains information about a note that is stored in a TaskItem
 * including the note's author and the text of the note.
 * @author Walker Booth
 * @author Brian Morris
 */
public class Note {

    /** The author of the Note */
    private String noteAuthor;
    /** The text of the Note */
    private String noteText;
    
    /**
     * The constructor of Note initializes values for all fields. If any of the parameters
     * are null or an empty String, an IllegalArgumentException is thrown.
     * @param noteAuthor the author of the Note
     * @param noteText the text of the Note
     * @throws IllegalArgumentException if any of the parameters are null or an empty String
     */
    public Note (String noteAuthor, String noteText) {
        if (noteAuthor == null || noteAuthor.isEmpty()) {
        	throw new IllegalArgumentException("Parameters cannot be null or empty.");
        }
        
        if (noteText == null || noteText.isEmpty()) {
        	throw new IllegalArgumentException("Parameters cannot be null or empty.");
        }
    	
    	this.noteAuthor = noteAuthor;
        this.noteText = noteText;
    }
    
    /**
     * Returns the author of the Note.
     * @return the author of the Note
     */
    public String getNoteAuthor() {
        return noteAuthor;
    }
    
    /**
     * Returns the noteText of the Note.
     * @return the noteText of the Note
     */
    public String getNoteText() {
        return noteText;
    }
    
    /**
     * Returns a String array of size 2 with noteAuthor as the first element and noteText as the second. 
     * @return a String array containing the note author and note text
     */
    public String[] getNoteArray() {
        String[] s = new String[2];
        s[0] = noteAuthor;
        s[1] = noteText;
        return s;
    }
}
