package edu.ncsu.csc216.backlog.scrum_backlog;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.task.TaskItem;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.NoteItem;
import edu.ncsu.csc216.task.xml.NoteList;
import edu.ncsu.csc216.task.xml.Task;

/**
 * Tests the TaskItemList class for completion. 
 * @author Brian Morris
 * @author Walker Booth
 *
 */
public class TaskItemListTest {

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
     * Tests the constructor of TaskItemList.
     */
	@Test
    public void testTaskItemList() {
        TaskItemList testTaskItemList = new TaskItemList();
        assertEquals(0, testTaskItemList.getTaskItems().size());
        TaskItem testTaskItem = new TaskItem(TITLE, TYPE, CREATOR, NOTE_TEXT);
        assertEquals(1, testTaskItem.getTaskItemId());
    }
	
	/**
	 * Tests the testAddTaskItem method.
	 */
	@Test
	public void testAddTaskItem() {
	    TaskItemList testTaskItemList = new TaskItemList();
	    assertEquals(0, testTaskItemList.getTaskItems().size());
	    TaskItem testTaskItem = new TaskItem(TITLE, TYPE, CREATOR, NOTE_TEXT);
	    assertEquals(1, testTaskItem.getTaskItemId());
	    assertEquals(2, testTaskItemList.addTaskItem(TITLE, TYPE, CREATOR, NOTE_TEXT));
	}

	/**
	 * Tests the addXMLTasks method.
	 */
	@Test
	public void testAddXMLTasks() {
	    TaskItemList testTaskItemList = new TaskItemList();
	    assertEquals(0, testTaskItemList.getTaskItems().size());
	    List<Task> testTaskList = new ArrayList<Task>();
	    
	    NoteList testNotes = new NoteList();
	    NoteItem testNote = new NoteItem();
	    testNote.setNoteAuthor(CREATOR);
	    testNote.setNoteText(NOTE_TEXT);
	    testNotes.getNotes().add(testNote);
	    
	    Task task1 = new Task();
	    task1.setCreator(CREATOR);
	    task1.setId(1);
	    task1.setTitle(TITLE);
	    task1.setType("B");
	    task1.setVerified(false);
	    task1.setOwner(OWNER);
	    task1.setState("Owned");
	    task1.setNoteList(testNotes);
	    
	    Task task2 = new Task();
	    task2.setCreator("wgbooth");
        task2.setId(2);
        task2.setTitle(TITLE);
        task2.setType("B");
        task2.setVerified(false);
        task2.setOwner(OWNER);
        task2.setState("Owned");
        task2.setNoteList(testNotes);
	    
        Task task3 = new Task();
	    task3.setCreator("spbalik");
        task3.setId(3);
        task3.setTitle(TITLE);
        task3.setType("B");
        task3.setVerified(false);
        task3.setOwner(OWNER);
        task3.setState("Owned");
        task3.setNoteList(testNotes);
	    
	    testTaskList.add(task1);
	    testTaskList.add(task2);
	    testTaskList.add(task3);
        
	    //Check that the tasks are in proper order.
	    testTaskItemList.addXMLTasks(testTaskList);
	    assertTrue(testTaskItemList.getTaskItemById(1).getCreator().equals(task1.getCreator()));
	    assertTrue(testTaskItemList.getTaskItemById(2).getCreator().equals(task2.getCreator()));
	    assertTrue(testTaskItemList.getTaskItemById(3).getCreator().equals(task3.getCreator()));
	    
	    //Check that counter updated properly.
        TaskItem task4 = new TaskItem(TITLE, TYPE, CREATOR, NOTE_TEXT);
        assertEquals(4, task4.getTaskItemId());
	}
	
	/**
	 * Tests the getTaskItems method.
	 */
	@Test
	public void testGetTaskItems() {
	    TaskItemList testTaskItemList = new TaskItemList();
	    assertEquals(0, testTaskItemList.getTaskItems().size());
        testTaskItemList.addTaskItem(TITLE, TYPE, CREATOR, NOTE_TEXT);
        assertEquals(1, testTaskItemList.getTaskItems().size());
	}
	
	
	/**
	 * Tests the getTaskItemsByOwner method.
	 */
	@Test
	public void testGetTaskItemsByOwner() {
	    TaskItemList testTaskItemList = new TaskItemList();
        assertEquals(0, testTaskItemList.getTaskItems().size());
        List<Task> testTaskList = new ArrayList<Task>();
        
        NoteList testNotes = new NoteList();
        NoteItem testNote = new NoteItem();
        testNote.setNoteAuthor(CREATOR);
        testNote.setNoteText(NOTE_TEXT);
        testNotes.getNotes().add(testNote);
        
        Task task1 = new Task();
        task1.setCreator(CREATOR);
        task1.setId(1);
        task1.setTitle(TITLE);
        task1.setType("B");
        task1.setVerified(false);
        task1.setOwner(OWNER);
        task1.setState("Owned");
        task1.setNoteList(testNotes);
        
        Task task2 = new Task();
        task2.setCreator("wgbooth");
        task2.setId(2);
        task2.setTitle(TITLE);
        task2.setType("B");
        task2.setVerified(false);
        task2.setOwner("bcmorri3");
        task2.setState("Owned");
        task2.setNoteList(testNotes);
        
        Task task3 = new Task();
        task3.setCreator("spbalik");
        task3.setId(3);
        task3.setTitle(TITLE);
        task3.setType("B");
        task3.setVerified(false);
        task3.setOwner("spbalik");
        task3.setState("Owned");
        task3.setNoteList(testNotes);
        
        testTaskList.add(task1);
        testTaskList.add(task2);
        testTaskList.add(task3);
        testTaskItemList.addXMLTasks(testTaskList);
        
        List<TaskItem> testList = testTaskItemList.getTaskItemsByOwner(OWNER);
        assertEquals(1, testList.size());
        assertEquals(1, testList.get(0).getTaskItemId());
        testList = testTaskItemList.getTaskItemsByOwner("bcmorri3");
        assertEquals(1, testList.size());
        assertEquals(2, testList.get(0).getTaskItemId());
        testList = testTaskItemList.getTaskItemsByOwner("spbalik");
        assertEquals(1, testList.size());
        assertEquals(3, testList.get(0).getTaskItemId());
	}
	
	/**
	 * Tests the getTaskItemsByCreator method.
	 */
	@Test
	public void testGetTaskItemsByCreator() {
	    TaskItemList test = new TaskItemList();
	    test.addTaskItem(TITLE, TYPE, CREATOR, NOTE_TEXT);
	    test.addTaskItem(TITLE, TYPE, CREATOR, NOTE_TEXT);
	    test.addTaskItem(TITLE, TYPE, "wgbooth", NOTE_TEXT);
	    List<TaskItem> testList = test.getTasksByCreator(CREATOR);
	    assertEquals(2, testList.size());
	    assertEquals(1, testList.get(0).getTaskItemId());
	    assertEquals(2, testList.get(1).getTaskItemId());
	    testList = test.getTasksByCreator("wgbooth");
	    assertEquals(1, testList.size());
	    assertEquals(3, testList.get(0).getTaskItemId());
	}
	
	/**
	 * Tests the getTaskItemById method.
	 */
	@Test
	public void testGetTaskItemById() {
	    TaskItemList test = new TaskItemList();
        test.addTaskItem(TITLE, TYPE, CREATOR, NOTE_TEXT);
        test.addTaskItem(TITLE, TYPE, CREATOR, NOTE_TEXT);
        test.addTaskItem(TITLE, TYPE, "wgbooth", NOTE_TEXT);
        TaskItem testTask = test.getTaskItemById(2);
        assertTrue(testTask.getCreator().equals(CREATOR));
        testTask = test.getTaskItemById(3);
        assertTrue(testTask.getCreator().equals("wgbooth"));
        assertTrue(test.getTaskItemById(5) == null);
	}
	
	/**
	 * Tests the executeCommand method.
	 */
	@Test
	public void testExecuteCommand() {
	       TaskItemList test = new TaskItemList();
	       test.addTaskItem(TITLE, TYPE, CREATOR, NOTE_TEXT);
	       Command testCommand = new Command(Command.CommandValue.CLAIM, "bcmorri3", "test");
	       try {
	           test.executeCommand(1, testCommand);
	           assertTrue(test.getTaskItemById(1).getStateName().equals("Owned"));
	           test.executeCommand(5, testCommand);
	       } catch (UnsupportedOperationException e) {
	           fail();
	       }
	}
	
	/**
	 * Tests the deleteTaskItemById method.
	 */
	@Test
	public void testDeleteTaskItemById() {
	    TaskItemList test = new TaskItemList();
        test.addTaskItem(TITLE, TYPE, CREATOR, NOTE_TEXT);
        assertEquals(1, test.getTaskItems().size());
        test.deleteTaskItemById(5);
        assertEquals(1, test.getTaskItems().size());
        test.deleteTaskItemById(1);
        assertEquals(0, test.getTaskItems().size());
        
	}
}
