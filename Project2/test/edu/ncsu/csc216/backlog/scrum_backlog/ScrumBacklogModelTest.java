package edu.ncsu.csc216.backlog.scrum_backlog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.command.Command.CommandValue;
import edu.ncsu.csc216.backlog.task.TaskItem;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.NoteItem;
import edu.ncsu.csc216.task.xml.NoteList;

/**
 * Tests the ScrumBacklogModel class for completion.
 * @author Brian Morris
 * @author Walker Booth
 */
public class ScrumBacklogModelTest {

	/** Generic title for testing */
    private static final String TITLE = "Bug.java";
    /** Type for testing */
    private static final Type TYPE = Type.BUG;
    /** Generic creator for testing */
    private static final String CREATOR = "bcmorri3";
    /** Generic note text for testing */
    private static final String NOTE_TEXT = "This is a bug!";
    /** Generic owner for testing */
    private static final String OWNER = "wgbooth";
	
	/**
	 * Tests that only one instance of a ScrumBacklogModel can be created. 
	 */
    @Test
    public void testSingletonPattern() {
        ScrumBacklogModel model1 = ScrumBacklogModel.getInstance();
        assertEquals(model1, ScrumBacklogModel.getInstance());
        ScrumBacklogModel model2 = ScrumBacklogModel.getInstance();
        assertEquals(model2, ScrumBacklogModel.getInstance());

    }
    
    /**
     * Tests the loadTasksFromFile() method.
     */
    @Test
    public void testLoadTasksFromFile () {
        ScrumBacklogModel model1 = ScrumBacklogModel.getInstance();
        model1.createNewTaskItemList();
        model1.loadTasksFromFile("test_files/exp_task_backlog.xml");
        assertEquals(1, model1.getTaskItemListAsArray().length);
        assertEquals("jep", model1.getTaskItemById(1).getCreator());
        model1.loadTasksFromFile("test_files/exp_task_owned.xml");
        assertEquals(2, model1.getTaskItemListAsArray().length);
        assertEquals("sesmith5", model1.getTaskItemById(2).getOwner());
        
        try {
        	model1.loadTasksFromFile("blahahahahfdas");
        	fail();
        } catch (IllegalArgumentException e) {
        	assertEquals(null, e.getMessage());
        }
        
        try {
        	model1.loadTasksFromFile(null);
        	fail();
        } catch (IllegalArgumentException e) {
        	assertEquals(null, e.getMessage());
        }

    }
    
    /**
     * Tests the saveTasksToFile() method.
     */
    @Test
    public void testSaveTasksToFile () {
        ScrumBacklogModel model1 = ScrumBacklogModel.getInstance();   
        model1.createNewTaskItemList();

        try {
            model1.saveTasksToFile("test_files/actual_task_backlog.xml");
            fail();
        } catch (IllegalArgumentException e) {
        	assertEquals(null, e.getMessage());
        }
        
        model1.loadTasksFromFile("test_files/exp_task_backlog.xml");
        model1.saveTasksToFile("test_files/actual_task_backlog.xml");
        checkFiles("test_files/exp_task_backlog.xml", "test_files/actual_task_backlog.xml");  
        
        /*try {
        	model1.saveTasksToFile("test_files/blahahahah");
        } catch (IllegalArgumentException e) {
        	assertEquals(null, e.getMessage());
        }*/
        
        try {
        	model1.saveTasksToFile(null);
        } catch (IllegalArgumentException e) {
        	assertEquals(null, e.getMessage());
        }
    }
    
    /**
     * Tests the getTaskItemListAsArray() method.
     */
    @Test
    public void testGetTaskItemListAsArray () {
        ScrumBacklogModel model1 = ScrumBacklogModel.getInstance();
        model1.createNewTaskItemList();
        
        NoteList testNotes = new NoteList();
	    NoteItem testNote = new NoteItem();
	    testNote.setNoteAuthor(CREATOR);
	    testNote.setNoteText(NOTE_TEXT);
	    testNotes.getNotes().add(testNote);
        
        model1.addTaskItemToList(TITLE, TYPE, CREATOR, "notes");
        model1.addTaskItemToList("ANOTHER_TITLE", TYPE, CREATOR, NOTE_TEXT);
        
        assertEquals(1, model1.getTaskItemListAsArray()[0][0]);
        assertEquals(TaskItem.BACKLOG_NAME, model1.getTaskItemListAsArray()[0][1]);
        assertEquals(TITLE, model1.getTaskItemListAsArray()[0][2]);
        assertEquals(2, model1.getTaskItemListAsArray()[1][0]);
        assertEquals(TaskItem.BACKLOG_NAME, model1.getTaskItemListAsArray()[1][1]);
        assertEquals("ANOTHER_TITLE", model1.getTaskItemListAsArray()[1][2]);
    	
    	
    }
    
    /**
     * Tests the getTaskItemListByOwnerAsArray() method.
     */
    @Test
    public void testGetTaskItemListByOwnerAsArray () {
        ScrumBacklogModel model1 = ScrumBacklogModel.getInstance();
        model1.createNewTaskItemList();

    	Command c = new Command(CommandValue.CLAIM, OWNER, "now owned");
    	NoteList testNotes = new NoteList();
	    NoteItem testNote = new NoteItem();
	    testNote.setNoteAuthor(CREATOR);
	    testNote.setNoteText(NOTE_TEXT);
	    testNotes.getNotes().add(testNote);
        
        model1.addTaskItemToList(TITLE, TYPE, CREATOR, "notes");
        model1.addTaskItemToList("ANOTHER_TITLE", TYPE, CREATOR, NOTE_TEXT);
        model1.getTaskItemById(2).update(c);
               
        assertEquals(2, model1.getTaskItemListByOwnerAsArray(OWNER)[0][0]);
        assertEquals(TaskItem.OWNED_NAME, model1.getTaskItemListByOwnerAsArray(OWNER)[0][1]);
        assertEquals("ANOTHER_TITLE", model1.getTaskItemListByOwnerAsArray(OWNER)[0][2]);
        
    }
    
    /**
     * Tests the getTaskItemListByCreatorAsArray() method.
     */
    @Test
    public void testGetTaskItemListByCreatorAsArray() {
    	ScrumBacklogModel model1 = ScrumBacklogModel.getInstance();
        model1.createNewTaskItemList();
        
        NoteList testNotes = new NoteList();
	    NoteItem testNote = new NoteItem();
	    testNote.setNoteAuthor(CREATOR);
	    testNote.setNoteText(NOTE_TEXT);
	    testNotes.getNotes().add(testNote);
        
        model1.addTaskItemToList(TITLE, TYPE, "The creator", "notes");
        model1.addTaskItemToList("ANOTHER_TITLE", TYPE, CREATOR, NOTE_TEXT);
        
        assertEquals(1, model1.getTaskItemListByCreatorAsArray("The creator")[0][0]);
        assertEquals(TaskItem.BACKLOG_NAME, model1.getTaskItemListByCreatorAsArray("The creator")[0][1]);
        assertEquals(TITLE, model1.getTaskItemListByCreatorAsArray("The creator")[0][2]);
  
    }
    
    /**
     * Tests the deleteTaskItemById() method.
     */
    @Test
    public void testDeleteTaskItemById() {
        ScrumBacklogModel model1 = ScrumBacklogModel.getInstance();
        model1.createNewTaskItemList();
        model1.addTaskItemToList(TITLE, TYPE, CREATOR, NOTE_TEXT);
        model1.addTaskItemToList(TITLE, TYPE, CREATOR, NOTE_TEXT);
        model1.addTaskItemToList(TITLE, TYPE, CREATOR, NOTE_TEXT);
        model1.deleteTaskItemById(2);
        assertTrue(model1.getTaskItemById(2) == null);
        assertEquals(2, model1.getTaskItemListAsArray().length);
    }
    
    /**
     * Tests the executeCommand() method.
     */
    @Test
    public void testExecuteCommand() {
        ScrumBacklogModel model1 = ScrumBacklogModel.getInstance();
        model1.createNewTaskItemList();
        model1.addTaskItemToList(TITLE, TYPE, CREATOR, NOTE_TEXT);
        assertTrue(model1.getTaskItemById(1).getStateName().equals("Backlog"));
        Command c = new Command(Command.CommandValue.CLAIM, "bcmorri3", "test");
        model1.executeCommand(1, c);
        assertTrue(model1.getTaskItemById(1).getStateName().equals("Owned"));
        c = new Command(Command.CommandValue.REJECT, "bcmorri3", "test");
        model1.executeCommand(1, c);
        assertTrue(model1.getTaskItemById(1).getStateName().equals("Rejected"));
        c = new Command(Command.CommandValue.BACKLOG, "bcmorri3", "test");
        model1.executeCommand(1, c);
        assertTrue(model1.getTaskItemById(1).getStateName().equals("Backlog"));
        c = new Command(Command.CommandValue.CLAIM, "bcmorri3", "test");
        model1.executeCommand(1, c);
        assertTrue(model1.getTaskItemById(1).getStateName().equals("Owned"));
        c = new Command(Command.CommandValue.PROCESS, "bcmorri3", "test");
        model1.executeCommand(1, c);
        assertTrue(model1.getTaskItemById(1).getStateName().equals("Processing"));
        c = new Command(Command.CommandValue.VERIFY, "bcmorri3", "test");
        model1.executeCommand(1, c);
        assertTrue(model1.getTaskItemById(1).getStateName().equals("Verifying"));
        c = new Command(Command.CommandValue.COMPLETE, "bcmorri3", "test");
        model1.executeCommand(1, c);
        assertTrue(model1.getTaskItemById(1).getStateName().equals("Done"));
    }
    
    /**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output filename
	 * @param actFile actual output filename
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File (expFile));
			Scanner actScanner = new Scanner(new File(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
