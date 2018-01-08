package edu.ncsu.csc216.backlog.task;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.command.Command.CommandValue;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.NoteItem;
import edu.ncsu.csc216.task.xml.NoteList;
import edu.ncsu.csc216.task.xml.Task;

/**
 * Tests the TaskItem class for completion.
 * @author Brian Morris
 * @author Walker Booth
 *
 */
public class TaskItemTest {

    /** An example of a valid task title for testing */
    private static final String VALID_TITLE = "Bug Fix";
    /** An example of a valid task type for testing */
    private static final Type VALID_TYPE = Type.BUG;
    /** An example of a valid creator of a task for testing */
    private static final String VALID_CREATOR = "wgbooth";
    /** An example of a valid note for a task for testing */
    private static final String VALID_NOTE = "You've got a bug!";
    /** An example of a valid state for a task for testing */
    private static final String VALID_STATE = "Owned";

    /**
     * Tests the four parameter constructor of TaskItem.
     */
    @Test
    public void testTaskItemFourParams() {
        TaskItem t = null;
        try {
            t = new TaskItem(null, VALID_TYPE, VALID_CREATOR, VALID_NOTE);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }

        try {
            t = new TaskItem("", VALID_TYPE, VALID_CREATOR, VALID_NOTE);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }

        try {
            t = new TaskItem(VALID_TITLE, null, VALID_CREATOR, VALID_NOTE);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }

        try {
            t = new TaskItem(VALID_TITLE, VALID_TYPE, null, VALID_NOTE);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }

        try {
            t = new TaskItem(VALID_TITLE, VALID_TYPE, "", VALID_NOTE);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }

        try {
            t = new TaskItem(VALID_TITLE, VALID_TYPE, VALID_CREATOR, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }

        try {
            t = new TaskItem(VALID_TITLE, VALID_TYPE, VALID_CREATOR, "");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }
        assertTrue(t == null);
        TaskItem.setCounter(1);
        t = new TaskItem(VALID_TITLE, VALID_TYPE, VALID_CREATOR, VALID_NOTE);
        assertEquals(VALID_TITLE, t.getTitle());
        assertEquals(VALID_TYPE, t.getType());
        assertEquals(VALID_CREATOR, t.getCreator());
        assertEquals(VALID_NOTE, t.getNotes().get(0).getNoteText());
        // assertEquals(VALID_NOTE, t.getNotes().get(1));
        assertEquals(1, t.getTaskItemId());
        assertEquals("Backlog", t.getStateName());
        assertEquals(null, t.getOwner());
    }

    /**
     * Tests the constructor that accepts a Task parameter.
     */
    @Test
    @SuppressWarnings("unused")
    public void testTaskItemOneParam() {
        Task task = new Task();
        task.setTitle(null);
        task.setType(TaskItem.T_BUG);
        task.setState(TaskItem.BACKLOG_NAME);
        NoteItem noteItem = new NoteItem();
        noteItem.setNoteAuthor(VALID_CREATOR);
        noteItem.setNoteText(VALID_NOTE);
        NoteList noteList = new NoteList();
        noteList.getNotes().add(noteItem);
        task.setNoteList(noteList);
        try {
            TaskItem t = new TaskItem(task);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }

        task = new Task();
        task.setTitle(VALID_TITLE);
        task.setType(null);
        noteItem = new NoteItem();
        noteItem.setNoteAuthor(VALID_CREATOR);
        noteItem.setNoteText(VALID_NOTE);
        noteList = new NoteList();
        noteList.getNotes().add(noteItem);
        task.setNoteList(noteList);
        try {
            TaskItem t = new TaskItem(task);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }

        task = new Task();
        task.setTitle(VALID_TITLE);
        task.setType("");
        noteItem = new NoteItem();
        noteItem.setNoteAuthor(VALID_CREATOR);
        noteItem.setNoteText(VALID_NOTE);
        noteList = new NoteList();
        noteList.getNotes().add(noteItem);
        task.setNoteList(noteList);
        try {
            TaskItem t = new TaskItem(task);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }

        task = new Task();
        task.setTitle(VALID_TITLE);
        task.setType("BUG");
        noteItem = new NoteItem();
        noteItem.setNoteAuthor(null);
        noteItem.setNoteText(VALID_NOTE);
        noteList = new NoteList();
        noteList.getNotes().add(noteItem);
        task.setNoteList(noteList);
        try {
            TaskItem t = new TaskItem(task);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }

        task = new Task();
        task.setTitle(VALID_TITLE);
        task.setType("BUG");
        noteItem = new NoteItem();
        noteItem.setNoteAuthor("");
        noteItem.setNoteText(VALID_NOTE);
        noteList = new NoteList();
        noteList.getNotes().add(noteItem);
        task.setNoteList(noteList);
        try {
            TaskItem t = new TaskItem(task);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }

        task = new Task();
        task.setTitle(VALID_TITLE);
        task.setType("BUG");
        noteItem = new NoteItem();
        noteItem.setNoteAuthor(VALID_CREATOR);
        noteItem.setNoteText(null);
        noteList = new NoteList();
        noteList.getNotes().add(noteItem);
        task.setNoteList(noteList);
        try {
            TaskItem t = new TaskItem(task);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }

        task = new Task();
        task.setTitle(VALID_TITLE);
        task.setType("BUG");
        noteItem = new NoteItem();
        noteItem.setNoteAuthor(VALID_CREATOR);
        noteItem.setNoteText("");
        noteList = new NoteList();
        noteList.getNotes().add(noteItem);
        task.setNoteList(noteList);
        try {
            TaskItem t = new TaskItem(task);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }

        task = new Task();
        task.setTitle(VALID_TITLE);
        task.setType("BUG");
        task.setId(-1);
        noteItem = new NoteItem();
        noteItem.setNoteAuthor(VALID_CREATOR);
        noteItem.setNoteText("blah");
        noteList = new NoteList();
        noteList.getNotes().add(noteItem);
        task.setNoteList(noteList);
        try {
            TaskItem t = new TaskItem(task);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task information.", e.getMessage());
        }

        TaskItem.setCounter(1);
        task = new Task();
        task.setTitle(VALID_TITLE);
        task.setType(TaskItem.T_BUG);
        task.setState(TaskItem.BACKLOG_NAME);
        task.setCreator(VALID_CREATOR);
        task.setId(1);
        noteItem = new NoteItem();
        noteItem.setNoteAuthor(VALID_CREATOR);
        noteItem.setNoteText(VALID_NOTE);
        noteList = new NoteList();
        noteList.getNotes().add(noteItem);
        task.setNoteList(noteList);
        TaskItem t = new TaskItem(task);
        assertEquals(VALID_TITLE, t.getTitle());
        assertEquals(VALID_TYPE, t.getType());
        assertEquals(VALID_CREATOR, t.getCreator());
        assertEquals(VALID_NOTE, t.getNotes().get(0).getNoteText());
        assertEquals("Backlog", t.getStateName());
        assertEquals(1, t.getTaskItemId());
        assertEquals(null, t.getOwner());

        NoteList testNotes = new NoteList();
        NoteItem testNote = new NoteItem();
        testNote.setNoteAuthor(VALID_CREATOR);
        testNote.setNoteText(VALID_NOTE);
        testNotes.getNotes().add(testNote);
        Task task1 = new Task();
        task1.setCreator(VALID_CREATOR);
        task1.setId(1);
        task1.setTitle(VALID_TITLE);
        task1.setType("B");
        task1.setVerified(false);
        task1.setOwner(VALID_CREATOR);
        task1.setState(VALID_STATE);
        task1.setNoteList(testNotes);
        TaskItem testItem = null;
        try {
            task1.setState(null);
            testItem = new TaskItem(task1);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(testItem == null);
        }
        try {
            task1.setState("");
            testItem = new TaskItem(task1);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(testItem == null);
        }
        try {
            task1.setCreator(null);
            testItem = new TaskItem(task1);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(testItem == null);
        }
        try {
            task1.setCreator("");
            testItem = new TaskItem(task1);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(testItem == null);
        }
        try {
            task1.setTitle(null);
            testItem = new TaskItem(task1);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(testItem == null);
        }
        try {
            task1.setType(null);
            testItem = new TaskItem(task1);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(testItem == null);
        }
        try {
            task1.setType("");
            testItem = new TaskItem(task1);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(testItem == null);
        }
    }

    /**
     * Tests the incrementCounter method.
     */
    @Test
    public void testIncrementCounter() {
        TaskItem.setCounter(1);
        TaskItem t1 = new TaskItem(VALID_TITLE, VALID_TYPE, VALID_CREATOR, VALID_NOTE);
        assertEquals(1, t1.getTaskItemId());
        TaskItem t2 = new TaskItem(VALID_TITLE, VALID_TYPE, VALID_CREATOR, VALID_NOTE);
        assertEquals(2, t2.getTaskItemId());

    }

    /**
     * Tests the setState method
     */
    @Test
    public void testSetState() {
        TaskItem t1 = new TaskItem(VALID_TITLE, VALID_TYPE, VALID_CREATOR, VALID_NOTE);
        try {
            t1.setState(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid state.", e.getMessage());
        }

        try {
            t1.setState("BACKLOG");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid state.", e.getMessage());
        }

        t1.setState(TaskItem.BACKLOG_NAME);
        assertEquals(TaskItem.BACKLOG_NAME, t1.getStateName());
        t1.setState(TaskItem.DONE_NAME);
        assertEquals(TaskItem.DONE_NAME, t1.getStateName());
        t1.setState(TaskItem.OWNED_NAME);
        assertEquals(TaskItem.OWNED_NAME, t1.getStateName());
        t1.setState(TaskItem.PROCESSING_NAME);
        assertEquals(TaskItem.PROCESSING_NAME, t1.getStateName());
        t1.setState(TaskItem.REJECTED_NAME);
        assertEquals(TaskItem.REJECTED_NAME, t1.getStateName());
        t1.setState(TaskItem.VERIFYING_NAME);
        assertEquals(TaskItem.VERIFYING_NAME, t1.getStateName());

    }

    /**
     * Tests the setType method
     */
    @Test
    public void testSetType() {
        TaskItem t1 = new TaskItem(VALID_TITLE, VALID_TYPE, VALID_CREATOR, VALID_NOTE);
        try {
            t1.setType(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid type.", e.getMessage());
        }

        try {
            t1.setType("Backlog");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid type.", e.getMessage());
        }

        t1.setType("F");
        assertEquals("F", t1.getTypeString());
        t1.setType(TaskItem.T_BUG);
        assertEquals(TaskItem.T_BUG, t1.getTypeString());
        t1.setType(TaskItem.T_KNOWLEDGE_ACQUISITION);
        assertEquals(TaskItem.T_KNOWLEDGE_ACQUISITION, t1.getTypeString());
        t1.setType(TaskItem.T_TECHNICAL_WORK);
        assertEquals(TaskItem.T_TECHNICAL_WORK, t1.getTypeString());
    }

    /**
     * Tests the setCounter method.
     */
    @Test
    public void testSetCounter() {
        try {
            TaskItem.setCounter(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid task item id.", e.getMessage());
        }

        TaskItem.setCounter(9);
        TaskItem t1 = new TaskItem(VALID_TITLE, VALID_TYPE, VALID_CREATOR, VALID_NOTE);
        assertEquals(9, t1.getTaskItemId());
    }

    /**
     * Tests the update method.
     */
    @Test
    public void testUpdate() {
        TaskItem.setCounter(1);
        TaskItem t1 = new TaskItem(VALID_TITLE, VALID_TYPE, VALID_CREATOR, VALID_NOTE);
        Command toB = new Command(CommandValue.BACKLOG, "wgboothB", "sent to backlog");
        Command toO = new Command(CommandValue.CLAIM, "wgboothO", "sent to owned");
        Command toP = new Command(CommandValue.PROCESS, "wgboothP", "sent to processing");
        Command toV = new Command(CommandValue.VERIFY, "wgboothV", "sent to verifying");
        Command toR = new Command(CommandValue.REJECT, "wgboothR", "sent to rejected");
        Command toD = new Command(CommandValue.COMPLETE, "wgboothD", "sent to done");

        // Backlog to null
        try {
            t1.update(null);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(null, e.getMessage());
        }

        // Backlog to Backlog (invalid)
        try {
            t1.update(toB);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(null, e.getMessage());
        }

        // Backlog to Rejected (valid)
        t1.update(toR);
        assertEquals(2, t1.getNotes().size());
        assertEquals("wgboothR", t1.getNotes().get(1).getNoteAuthor());
        assertEquals("sent to rejected", t1.getNotes().get(1).getNoteText());
        assertEquals(TaskItem.REJECTED_NAME, t1.getStateName());
        assertEquals(null, t1.getOwner());

        // Rejected to null
        try {
            t1.update(null);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(null, e.getMessage());
        }

        // Rejected to Backlog (valid)
        t1.update(toB);
        assertEquals(3, t1.getNotes().size());
        assertEquals("wgboothB", t1.getNotes().get(2).getNoteAuthor());
        assertEquals("sent to backlog", t1.getNotes().get(2).getNoteText());
        assertEquals(TaskItem.BACKLOG_NAME, t1.getStateName());
        assertEquals(null, t1.getOwner());

        // Backlog to Owned (valid)
        t1.update(toO);
        assertEquals(4, t1.getNotes().size());
        assertEquals("wgboothO", t1.getNotes().get(3).getNoteAuthor());
        assertEquals("sent to owned", t1.getNotes().get(3).getNoteText());
        assertEquals(TaskItem.OWNED_NAME, t1.getStateName());
        assertEquals("wgboothO", t1.getOwner());

        // Owned to null
        try {
            t1.update(null);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(null, e.getMessage());
        }

        // Owned to Owned (invalid)
        try {
            t1.update(toO);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(null, e.getMessage());
        }

        // Owned to Backlog (valid)
        t1.update(toB);
        assertEquals(5, t1.getNotes().size());
        assertEquals("wgboothO", t1.getNotes().get(4).getNoteAuthor());
        assertEquals("sent to backlog", t1.getNotes().get(4).getNoteText());
        assertEquals(TaskItem.BACKLOG_NAME, t1.getStateName());
        assertEquals(null, t1.getOwner());

        // Owned to Processing (valid)
        t1.update(toO);
        assertEquals(6, t1.getNotes().size());
        t1.update(toP);
        assertEquals(7, t1.getNotes().size());
        assertEquals("wgboothO", t1.getNotes().get(6).getNoteAuthor());
        assertEquals("sent to processing", t1.getNotes().get(6).getNoteText());
        assertEquals(TaskItem.PROCESSING_NAME, t1.getStateName());
        assertEquals("wgboothO", t1.getOwner());

        // Processing to null
        try {
            t1.update(null);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(null, e.getMessage());
        }

        // Processing to Rejected (invalid)
        try {
            t1.update(toR);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(null, e.getMessage());
        }

        // Processing to Backlog (valid)
        t1.update(toB);
        assertEquals(8, t1.getNotes().size());
        assertEquals("wgboothO", t1.getNotes().get(7).getNoteAuthor());
        assertEquals("sent to backlog", t1.getNotes().get(7).getNoteText());
        assertEquals(TaskItem.BACKLOG_NAME, t1.getStateName());
        assertEquals(null, t1.getOwner());

        // Owned to Rejected (Valid)
        t1.update(toO);
        assertEquals(9, t1.getNotes().size());
        t1.update(toR);
        assertEquals(10, t1.getNotes().size());
        assertEquals("wgboothO", t1.getNotes().get(9).getNoteAuthor());
        assertEquals("sent to rejected", t1.getNotes().get(9).getNoteText());
        assertEquals(TaskItem.REJECTED_NAME, t1.getStateName());
        assertEquals(null, t1.getOwner());

        // Processing to Verifying (Valid)
        t1.update(toB);
        assertEquals(11, t1.getNotes().size());
        t1.update(toO);
        assertEquals(12, t1.getNotes().size());
        t1.update(toP);
        assertEquals(13, t1.getNotes().size());
        t1.update(toV);
        assertEquals(14, t1.getNotes().size());
        assertEquals("wgboothO", t1.getNotes().get(13).getNoteAuthor());
        assertEquals("sent to verifying", t1.getNotes().get(13).getNoteText());
        assertEquals(TaskItem.VERIFYING_NAME, t1.getStateName());
        assertEquals("wgboothO", t1.getOwner());

        // Verifying to null
        try {
            t1.update(null);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(null, e.getMessage());
        }

        // Verifying to Rejected (invalid)
        try {
            t1.update(toR);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(null, e.getMessage());
        }

        // Verifying to Processing (valid)
        t1.update(toP);
        assertEquals(15, t1.getNotes().size());
        assertEquals("wgboothP", t1.getNotes().get(14).getNoteAuthor());
        assertEquals("sent to processing", t1.getNotes().get(14).getNoteText());
        assertEquals(TaskItem.PROCESSING_NAME, t1.getStateName());
        assertEquals("wgboothO", t1.getOwner());

        // Processing to Done (valid)
        t1.setType("KA");
        t1.update(toD);
        assertEquals(16, t1.getNotes().size());
        assertEquals("wgboothO", t1.getNotes().get(15).getNoteAuthor());
        assertEquals("sent to done", t1.getNotes().get(15).getNoteText());
        assertEquals(TaskItem.DONE_NAME, t1.getStateName());
        assertEquals("wgboothO", t1.getOwner());
        t1.setType("B");
        // Done to null
        try {
            t1.update(null);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(null, e.getMessage());
        }

        // Done to Rejected (invalid)
        try {
            t1.update(toR);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(null, e.getMessage());
        }

        // Done to Processing (valid)
        t1.update(toP);
        assertEquals(17, t1.getNotes().size());
        assertEquals("wgboothO", t1.getNotes().get(16).getNoteAuthor());
        assertEquals("sent to processing", t1.getNotes().get(16).getNoteText());
        assertEquals(TaskItem.PROCESSING_NAME, t1.getStateName());
        assertEquals("wgboothO", t1.getOwner());

        // Processing to Processing (valid)
        t1.update(toP);
        assertEquals(18, t1.getNotes().size());
        assertEquals("wgboothO", t1.getNotes().get(17).getNoteAuthor());
        assertEquals("sent to processing", t1.getNotes().get(17).getNoteText());
        assertEquals(TaskItem.PROCESSING_NAME, t1.getStateName());
        assertEquals("wgboothO", t1.getOwner());

        // Verifying to Done (valid)
        t1.update(toV);
        assertEquals(19, t1.getNotes().size());
        t1.update(toD);
        assertEquals(20, t1.getNotes().size());
        assertEquals("wgboothD", t1.getNotes().get(19).getNoteAuthor());
        assertEquals("sent to done", t1.getNotes().get(19).getNoteText());
        assertEquals(TaskItem.DONE_NAME, t1.getStateName());
        assertEquals("wgboothO", t1.getOwner());


        t1.update(toB);
        assertEquals(21, t1.getNotes().size());
        assertEquals("wgboothO", t1.getNotes().get(20).getNoteAuthor());
        assertEquals("sent to backlog", t1.getNotes().get(20).getNoteText());
        assertEquals(TaskItem.BACKLOG_NAME, t1.getStateName());
        assertEquals(null, t1.getOwner());

        // Rejected to Rejected (invalid)
        t1.update(toO);
        assertEquals(22, t1.getNotes().size());
        t1.update(toR);
        assertEquals(23, t1.getNotes().size());
        try {
            t1.update(toR);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(null, e.getMessage());
        }
    }
    
    /**
     * Tests the getXMLTask method.
     */
    @Test
    public void testGetXMLTask() {
        TaskItem test = new TaskItem(VALID_TITLE, VALID_TYPE, VALID_CREATOR, VALID_NOTE);
        Task test2 = test.getXMLTask();
        assertTrue(test2.getCreator().equals(VALID_CREATOR));
        assertTrue(test2.getType().equals("B"));
        assertTrue(test2.getTitle().equals(VALID_TITLE));
        assertTrue(test2.getNoteList().getNotes().get(0).getNoteAuthor().equals(VALID_CREATOR));
        assertTrue(test2.getNoteList().getNotes().get(0).getNoteText().equals(VALID_NOTE));
    }
    
    /**
     * Tests the getTypeFullString() method.
     */
    @Test
    public void testGetTypeFullString() {
        TaskItem test = new TaskItem(VALID_TITLE, VALID_TYPE, VALID_CREATOR, VALID_NOTE);
        assertTrue(test.getTypeFullString().equals("Bug"));
        test = new TaskItem(VALID_TITLE, Type.FEATURE, VALID_CREATOR, VALID_NOTE);
        assertTrue(test.getTypeFullString().equals("Feature"));
        test = new TaskItem(VALID_TITLE, Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
        assertTrue(test.getTypeFullString().equals("Knowledge Acquisition"));
        test = new TaskItem(VALID_TITLE, Type.TECHNICAL_WORK, VALID_CREATOR, VALID_NOTE);
        assertTrue(test.getTypeFullString().equals("Technical Work"));
    }
    
    /**
     * Tests the getType() method.
     */
    @Test
    public void testGetType() {
        TaskItem test = new TaskItem(VALID_TITLE, VALID_TYPE, VALID_CREATOR, VALID_NOTE);
        assertTrue(test.getType().equals(TaskItem.Type.BUG));
        //enumeration coverage
        TaskItem.Type.valueOf(TaskItem.Type.BUG.toString());
        TaskItem.Type.valueOf(TaskItem.Type.FEATURE.toString());
        TaskItem.Type.valueOf(TaskItem.Type.KNOWLEDGE_ACQUISITION.toString());
        TaskItem.Type.valueOf(TaskItem.Type.TECHNICAL_WORK.toString());
    }
    
    /**
     * Tests the getNotesArray() method.
     */
    @Test
    public void testGetNotesArray() {
        TaskItem test = new TaskItem(VALID_TITLE, VALID_TYPE, VALID_CREATOR, VALID_NOTE);
        String[][] testArray = test.getNotesArray();
        assertTrue(testArray.length == 1);
        String creator = testArray[0][0];
        String notes = testArray[0][1];
        assertTrue(VALID_CREATOR.equals(creator));
        assertTrue(VALID_NOTE.equals(notes));
        Command c = new Command(Command.CommandValue.CLAIM, VALID_CREATOR, VALID_NOTE);
        test.update(c);
    }
}
