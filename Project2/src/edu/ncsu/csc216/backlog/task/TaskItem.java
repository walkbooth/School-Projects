package edu.ncsu.csc216.backlog.task;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.task.xml.NoteItem;
import edu.ncsu.csc216.task.xml.NoteList;
import edu.ncsu.csc216.task.xml.Task;

/**
 * Maintains information about a TaskItem including: its ID, completion state, title, creator, owner, type, and notes. 
 * @author Walker Booth
 * @author Brian Morris
 */
public class TaskItem {

	/** ID number of the TaskItem */
	private int taskID;
	
	/** Title of the TaskItem */
    private String title;

    /** Creator of the TaskItem */
    private String creator;

    /** Owner of the TaskItem */
    private String owner;

    /** The ArrayList of notes attached to the TaskItem */
    private ArrayList<Note> notes;

    /** Boolean that is true if the TaskItem has been verified */
    private boolean isVerified;

    /** The completion state of the TaskItem */
	private TaskItemState state;
	
	/** The type of the TaskItem */
    private Type type;

    /** A counter variable used to generate TaskItem ID numbers */
    private static int counter = 1;

    /** The state of a TaskItem in the Backlog state */
	private final TaskItemState backlogState = new BacklogState();
	
	/** The state of a TaskItem in the Owned state */
	private final TaskItemState ownedState = new OwnedState();
	
	/** The state of a TaskItem in the Processing state */
	private final TaskItemState processingState = new ProcessingState();
	
	/** The state of a TaskItem in the Verifying state */
	private final TaskItemState verifyingState = new VerifyingState();
	
	/** The state of a TaskItem in the Done state */
	private final TaskItemState doneState = new DoneState();
	
	/** The state of a TaskItem in the Rejected state */
	private final TaskItemState rejectedState = new RejectedState();
	
	/** The string representation of the Backlog state */
	public static final String BACKLOG_NAME = "Backlog";
	
	/** the string representation of the Owned state */
	public static final String OWNED_NAME = "Owned";
	
	/** The string representation of the Processing state */
	public static final String PROCESSING_NAME = "Processing";
	
	/** The string representation of the Verifying state */
	public static final String VERIFYING_NAME = "Verifying";
	
	/** The string representation of the Done state */
	public static final String DONE_NAME = "Done";
	
	/** The string representation of the Rejected state */
	public static final String REJECTED_NAME = "Rejected";
	
	/** The string representation of the Feature type */
	public static final String T_FEATURE = "F";
	
	/** The string representation of the Bug type */
	public static final String T_BUG = "B";
	
	/** The string representation of the Technical Work type */
	public static final String T_TECHNICAL_WORK = "TW";
	
	/** The string representation of the Knowledge Acquisition type */
	public static final String T_KNOWLEDGE_ACQUISITION = "KA";
	
	/**
	 * The constructor for TaskItem creates a new TaskItem with values for all fields.
	 * If any of the parameters are null or an empty String, an IllegalArgumentException is thrown.
	 * @param title the title of the task  
	 * @param type the type of the task
	 * @param creator the creator of the task
	 * @param note the note attached to the task
	 * @throws IllegalArgumentException if any of the parameters are null or an empty String
	 */
	public TaskItem(String title, Type type, String creator, String note) {
		if (title == null || title.equals("")) {
		    throw new IllegalArgumentException("Invalid task information.");
		} else if (type == null){
		    throw new IllegalArgumentException("Invalid task information.");
		} else if (creator == null || creator.equals("")) {
            throw new IllegalArgumentException("Invalid task information.");
        } else if (note == null || note.equals("")) {
            throw new IllegalArgumentException("Invalid task information.");
        }
	    this.title = title;
		this.type = type;
		this.creator = creator;
		this.notes = new ArrayList<Note>();
		notes.add(new Note(creator, note));
		this.state = backlogState;
		this.taskID = counter;
		this.isVerified = false;
		incrementCounter();
	}
	
	/**
	 * The constructor for TaskItem accepts an XML Task as a parameter and creates
	 * a new TaskItem. If the Task has any invalid combination of properties, an IllegalArgumentException
	 * is thrown.
	 * @param task the XML Task
	 * @throws IllegalArgumentException if the Task has any invalid combination of properties
	 */
	public TaskItem(Task task) {
	    if (task == null) {
	        throw new IllegalArgumentException("Invalid task information.");
	    } else if (task.getId() <= 0) {
	        throw new IllegalArgumentException("Invalid task information.");
	    } else if (task.getState() == null || task.getState().equals("")) {   
	        throw new IllegalArgumentException("Invalid task information.");
	    } else if (task.getCreator() == null || task.getCreator().equals("")) {
	        throw new IllegalArgumentException("Invalid task information.");
	    } else if (task.getTitle() == null) {
	        throw new IllegalArgumentException("Invalid task information.");
	    } else if (task.getType() == null || task.getType().equals("")) {
	        throw new IllegalArgumentException("Invalid task information.");
	    } else if (task.getOwner() == null && 
	    		  (task.getState().equals(OWNED_NAME) || task.getState().equals(PROCESSING_NAME) ||
	    		   task.getState().equals(DONE_NAME) || task.getState().equals(VERIFYING_NAME))) {
	    	throw new IllegalArgumentException("Invalid task information.");
	    } else if (task.getOwner() != null &&
	    		  (task.getState().equals(BACKLOG_NAME) || task.getState().equals(REJECTED_NAME))) {
	    	throw new IllegalArgumentException("Invalid task information.");
	    }
	    this.isVerified = task.isVerified();
	    if (task.getState().equals(DONE_NAME) && !isVerified) {
	        throw new IllegalStateException("Invalid task information.");
	    }
	    setState(task.getState());
	    setType(task.getType());
	    this.title = task.getTitle();
	    this.creator = task.getCreator();
	    this.owner = task.getOwner();
	    this.notes = new ArrayList<Note>();
	    List<NoteItem> taskNotes = task.getNoteList().getNotes();
	    for (int i = 0; i < taskNotes.size(); i++) {
	        String noteAuthor = taskNotes.get(i).getNoteAuthor();
	        String noteText = taskNotes.get(i).getNoteText();
	        notes.add(new Note(noteAuthor, noteText));
	    }
	    this.taskID = task.getId();
	    incrementCounter();

	}
	
	/**
	 * Increments the counter variable.
	 */
	public static void incrementCounter() {
		counter++;
	}
	
	/**
	 * Returns the current state of the task item.
	 * @return the current state of the task item
	 */
	public TaskItemState getState() {
		return state;
	}

	/**
	 * Sets the current state of the TaskItem to reflect the provided state String.
	 * If the state String is an invalid state type or null, an IllegalArgumentException is thrown.
	 * @param state the provided state String
	 * @throws IllegalArgumentException if the provided state String is an invalid state type or null
	 */
	protected void setState(String state) {
	    if (state == null) {
	        throw new IllegalArgumentException("Invalid state.");
	    } else if (state.equals(BACKLOG_NAME)) {
	        this.state = backlogState;
	    } else if (state.equals(DONE_NAME)) {
	        this.state = doneState;
	    } else if (state.equals(OWNED_NAME)) {
	        this.state = ownedState;
	    } else if (state.equals(PROCESSING_NAME)) {
	        this.state = processingState;
	    } else if (state.equals(VERIFYING_NAME)) {
	        this.state = verifyingState;
	    } else if (state.equals(REJECTED_NAME)) {
            this.state = rejectedState;
        } else {
            throw new IllegalArgumentException("Invalid state."); //not sure if needed
        }
	}
	
	/**
	 * Sets the type of the TaskItem to the provided task type String. If the provided String is null
	 * or the task type is invalid, an IllegalArgumentException is thrown.
	 * @param type the provided task type String
	 * @throws IllegalArgumentException if the type String is invalid or null
	 */
	protected void setType(String type) {
	    if (type == null) {
	        throw new IllegalArgumentException("Invalid type.");
	    } else if (type.equals(T_FEATURE)) {
		    this.type = Type.FEATURE;
		} else if (type.equals(T_BUG)) {
		    this.type = Type.BUG;
		} else if (type.equals(T_TECHNICAL_WORK)) {
		    this.type = Type.TECHNICAL_WORK;
		} else if (type.equals(T_KNOWLEDGE_ACQUISITION)) {
		    this.type = Type.KNOWLEDGE_ACQUISITION;
		} else {
		    throw new IllegalArgumentException("Invalid type."); //not sure if needed
		}
	}

	/**
	 * Returns the TaskItem's ID number.
	 * @return the TaskItem's ID number
	 */
	public int getTaskItemId() {
		return taskID;
	}

	/**
	 * Returns the title of the TaskItem.
	 * @return the title of the TaskItem
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the creator of the TaskItem.
	 * @return the creator of the TaskItem
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * Returns the owner of the TaskItem.
	 * @return the owner of the TaskItem
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Sets the counter variable to the parameter value. Throws an IllegalArgumentException
	 * if the counter value is less than 1.
	 * @param counter the new counter value
	 * @throws IllegalArgumentException if the counter value is less than 1
	 */
	public static void setCounter(int counter) {
		if (counter < 1) {
			throw new IllegalArgumentException("Invalid task item id.");
		}
		TaskItem.counter = counter;
	}
	
	/**
	 * Returns the name of the TaskItem's state.
	 * @return the name of the TaskItem's state
	 */
	public String getStateName() {
		return state.getStateName();
	}
	
	/** 
	 * Returns the type of the TaskItem.
	 * @return the type of the TaskItem
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * Returns the shortened name of the type of the TaskItem.
	 * @return the shortened name of the type of the TaskItem
	 */
	public String getTypeString() {
		if (type.equals(Type.BUG)) {
		    return T_BUG;
		} else if (type.equals(Type.FEATURE)) {
		    return T_FEATURE;
		} else if (type.equals(Type.KNOWLEDGE_ACQUISITION)) {
		    return T_KNOWLEDGE_ACQUISITION;
		} else if (type.equals(Type.TECHNICAL_WORK)) {
		    return T_TECHNICAL_WORK;
		}
		return "";
	}
	
	/**
	 * Returns the full name of the type of the TaskItem.
	 * @return the full name of the type of the TaskItem
	 */
	public String getTypeFullString() {
		if (type.equals(Type.BUG)) {
		    return "Bug";
		} else if (type.equals(Type.FEATURE)) {
		    return "Feature";
		} else if (type.equals(Type.KNOWLEDGE_ACQUISITION)) {
		    return "Knowledge Acquisition";
		} else if (type.equals(Type.TECHNICAL_WORK)) {
		    return "Technical Work";
		}
		return "";
	}

	
	/** 
	 * Returns the ArrayList of notes of the TaskItem.
	 * @return the ArrayList of notes of the TaskItem
	 */
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	/**
	 * Executes the provided update command on this TaskItem.
	 * @param command the update command to execute
	 */
	public void update (Command command) {
		getState().updateState(command);
	}
	
	/**
	 * Returns the TaskItem as an XML Task
	 * @return the TaskItem an an XML Task
	 */
	public Task getXMLTask() {
		Task task = new Task();
    	task.setTitle(title);
    	task.setType(getTypeString());
    	task.setState(state.getStateName());
    	task.setCreator(creator);
    	task.setId(taskID);
    	if (owner != null) {
    	    task.setOwner(owner);
    	}
    	task.setVerified(isVerified);
    	
    	NoteList noteList = new NoteList();
		task.setNoteList(noteList);
		for (int i = 0; i < notes.size(); i++) {
			NoteItem noteItem = new NoteItem();
	    	noteItem.setNoteAuthor(notes.get(i).getNoteAuthor());
	    	noteItem.setNoteText(notes.get(i).getNoteText());
			noteList.getNotes().add(noteItem);
		}
		task.setNoteList(noteList);
		
		return task;
	}
	
	/**
	 * Returns the list of notes as a 2D String array. The note's author
	 * is the first element of the inner-array and the note text is the
	 * second element of the inner-array.
	 * @return the list of notes as a 2D String array
	 */
	public String[][] getNotesArray () {
		String[][] array = new String[notes.size()][2];
		for (int i = 0; i < notes.size(); i++) {
			array[i][0] = notes.get(i).getNoteAuthor();
			array[i][1] = notes.get(i).getNoteText();
		}
		return array;
	}

	/**
	 * Interface for states in the Task State Pattern.  All 
	 * concrete task states must implement the TaskState interface.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface TaskItemState {
		
		/**
		 * Update the {@link TaskItem} based on the given {@link Command}.
		 * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
		 * is not a valid action for the given state.  
		 * @param c {@link Command} describing the action that will update the {@link TaskItem}'s
		 * state.
		 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
		 * for the given state.
		 */
		void updateState(Command c);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();
	
	}
	
	/**
	 * The BacklogState class represents the state of a TaskItem when it is in the
	 * backlog.Throws an UnsupportedOperationException if the provided update command is
     * invalid for this state type.
	 * @author Walker Booth
	 * @author Brian Morris
	 * @throws UnsupportedOperationException if the provided update command is invalid for this state type.
	 */
	private class BacklogState implements TaskItemState {

		@Override
		public void updateState(Command c) {
		    if(c == null) {
		        throw new UnsupportedOperationException();
		    } else if (c.getCommand() == Command.CommandValue.CLAIM) {
		        owner = c.getNoteAuthor();
		        notes.add(new Note(owner, c.getNoteText()));
		        state = ownedState;
		    } else if (c.getCommand() == Command.CommandValue.REJECT) {
		        notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
		        owner = null;
		        state = rejectedState;
		    } else {
		        throw new UnsupportedOperationException();
		    }
			
		}

		@Override
		public String getStateName() {
			return TaskItem.BACKLOG_NAME;
		}
		
	}
	
	/**
	 * The OwnedState class represents the state of a TaskItem when it has been claimed
	 * by an owner. Throws an UnsupportedOperationException if the provided update command is
     * invalid for this state type.
	 * @author Walker Booth
	 * @author Brian Morris
	 * @throws UnsupportedOperationException if the provided update command is invalid for this state type.
	 */
	private class OwnedState implements TaskItemState {

		@Override
		public void updateState(Command c) {
		    if (c == null) {
		        throw new UnsupportedOperationException();
		    } else if (c.getCommand() == Command.CommandValue.PROCESS) {
		        notes.add(new Note(owner, c.getNoteText()));
		        state = processingState;
		    } else if (c.getCommand() == Command.CommandValue.REJECT) {
		        notes.add(new Note(owner, c.getNoteText()));
		        owner = null;
	            state = rejectedState;
		    } else if (c.getCommand() == Command.CommandValue.BACKLOG) {
		        notes.add(new Note(owner, c.getNoteText()));
		        owner = null; 
		        state = backlogState;
		    } else {
		        throw new UnsupportedOperationException();
		    }
			
		}

		@Override
		public String getStateName() {
			return TaskItem.OWNED_NAME;
		}
		
	}
	
	/**
	 * The ProcessingState class represents the state of a TaskItem while its undergoing
	 * implementation. Throws an UnsupportedOperationException if the provided update command is
	 * invalid for this state type.
	 * @author Walker Booth
	 * @author Brian Morris
	 * @throws UnsupportedOperationException if the provided update command is invalid for this state type.
	 *
	 */
	private class ProcessingState implements TaskItemState {

		@Override
		public void updateState(Command c) {
			if (c == null) {
			    throw new UnsupportedOperationException();
			} else if (c.getCommand() == Command.CommandValue.PROCESS) {
			    notes.add(new Note(owner, c.getNoteText()));
			} else if (c.getCommand() == Command.CommandValue.VERIFY) {
			    if (type == Type.KNOWLEDGE_ACQUISITION) {
			    	throw new UnsupportedOperationException ();
			    }
				notes.add(new Note(owner, c.getNoteText()));
	            state = verifyingState;
			} else if (c.getCommand() == Command.CommandValue.COMPLETE && type.equals(Type.KNOWLEDGE_ACQUISITION)) {
			    notes.add(new Note(owner, c.getNoteText()));
			    state = doneState;
			} else if (c.getCommand() == Command.CommandValue.BACKLOG) {
			    notes.add(new Note(owner, c.getNoteText()));
			    owner = null;
			    state = backlogState;
			} else {
			    throw new UnsupportedOperationException();
			}
			
		}

		@Override
		public String getStateName() {
			return TaskItem.PROCESSING_NAME;
		}
		
	}
	
	/**
	 * The VerifyingState class represents the state of a TaskItem that is undergoing
	 * verification. Throws an UnsupportedOperationException if the provided update command is
     * invalid for this state type.
	 * @author Walker Booth
	 * @author Brian Morris
	 * @throws UnsupportedOperationException if the provided update command is invalid for this state type.
	 */
	private class VerifyingState implements TaskItemState {

		@Override
		public void updateState(Command c) {
		    if (c == null) {
		        throw new UnsupportedOperationException();
		    } else if (c.getCommand() == Command.CommandValue.COMPLETE) {
		        isVerified = true;
		        notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
		        state = doneState;
		    } else if (c.getCommand() == Command.CommandValue.PROCESS) {
		        notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
		        state = processingState;
		    } else {
		        throw new UnsupportedOperationException();
		    }
			
		}

		@Override
		public String getStateName() {
			return TaskItem.VERIFYING_NAME;
		}
		
	}
	
	/**
	 * The DoneState class represents a TaskItem when it has been completed.
	 * Throws an UnsupportedOperationException if the provided update command is
     * invalid for this state type.
	 * @author Walker Booth
	 * @author Brian Morris
	 * @throws UnsupportedOperationException if the provided update command is invalid for this state type.
	 */
	private class DoneState implements TaskItemState {

		@Override
		public void updateState(Command c) {
		    if (c == null) {
                throw new UnsupportedOperationException();
            } else if (c.getCommand() == Command.CommandValue.PROCESS) {
                isVerified = false;
                notes.add(new Note(owner, c.getNoteText()));
                state = processingState;
            } else if (c.getCommand() == Command.CommandValue.BACKLOG) {
                isVerified = false;
                notes.add(new Note(owner, c.getNoteText()));
                owner = null;
                state = backlogState;
            } else {
                throw new UnsupportedOperationException();
            }
		}

		@Override
		public String getStateName() {
			return TaskItem.DONE_NAME;
		}
		
	}
	
	/**
	 * The RejectedState class represents a TaskItem when it has been rejected.
	 * Throws an UnsupportedOperationException if the provided update command is
     * invalid for this state type.
	 * @author Walker Booth
	 * @author Brian Morris
	 * @throws UnsupportedOperationException if the provided update command is invalid for this state type.
	 */
	private class RejectedState implements TaskItemState {

		@Override
		public void updateState(Command c) {
		    if (c == null) {
                throw new UnsupportedOperationException();
            } else if (c.getCommand() == Command.CommandValue.BACKLOG) {
                notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
                state = backlogState;
            } else {
                throw new UnsupportedOperationException();
            }
		}

		@Override
		public String getStateName() {
			return TaskItem.REJECTED_NAME;
		}
		
	}
	
	/**
	 * An enumeration that contains the various types that a TaskItem may be.
	 * @author Walker Booth
	 * @author Brian Morris
	 *
	 */
	public static enum Type { FEATURE, BUG, TECHNICAL_WORK, KNOWLEDGE_ACQUISITION }	

}
