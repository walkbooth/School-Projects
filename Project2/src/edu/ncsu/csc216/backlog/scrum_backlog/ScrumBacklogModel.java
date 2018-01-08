package edu.ncsu.csc216.backlog.scrum_backlog;

import java.util.ArrayList;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.task.TaskItem;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.TaskIOException;
import edu.ncsu.csc216.task.xml.TaskReader;
import edu.ncsu.csc216.task.xml.TaskWriter;

/**
 * The ScrumBacklogModel class maintains the current ScrumBacklog using a
 * TaskItemList. A new TaskItemList can be loaded from files, new TaskItems may be added,
 * and existing TaskItems may be updated. If desired, the TaskItemList can be saved as a file.
 * @author Walker Booth
 * @author Brian Morris
 *
 */
public class ScrumBacklogModel {

	/** The single instance of the ScrumBacklogModel */
	private static ScrumBacklogModel singleton;
	/** The TaskItemList to store TaskItems */
	private TaskItemList tasks;
	
    /**
     * The constructor of ScrumBacklogModel initializes the instance with an
     * empty TaskItemList.
     */
    private ScrumBacklogModel() {
        tasks = new TaskItemList();
    }
    
    /**
     * Returns the only instance of ScrumBacklogModel.
     * @return the only instance of ScrumBacklogModel
     */
    public static ScrumBacklogModel getInstance() {
    	if (singleton == null) {
    		singleton = new ScrumBacklogModel();
    		
    	} 
    	return singleton;
    }
    
    /**
     * Saves a TaskItemList to a file with the specified file name. Throws an IllegalArgumentException
     * if there are no TaskItems in the list, the filename is null, or there was an error when
     * writing to the output file.
     * @param filename the name of the file
     * @throws IllegalArgumentException if the filename is null, the list is empty, or there was an error
     *         when writing to the output file.
     */
    public void saveTasksToFile(String filename) {
    	if (tasks.getTaskItems().size() == 0) {
    		throw new IllegalArgumentException();
    	}
    	
    	if (filename == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	try {
    		TaskWriter output = new TaskWriter(filename);
    		for (int i = 1; i <= tasks.getTaskItems().size(); i++) {  
    			//TaskItem t = getTaskItemById(i);
    			output.addItem(tasks.getTaskItemById(i).getXMLTask());
    		}
    		output.marshal();
    	} catch (TaskIOException e) {
    		throw new IllegalArgumentException();
    	}    	
    }
    
    /**
     * Loads a TaskItemList from a file with the specified file name. Throws an
     * IllegalArgumentException if there is an error when loading the tasks from
     * a file.
     * @param filename the name of the file
     * @throws IllegalArgumentException if there is an error when loading the tasks from a file
     */
    public void loadTasksFromFile(String filename) {
    	try {
    		TaskReader input = new TaskReader(filename);
			tasks.addXMLTasks(input.getTasks());
			
		} catch (TaskIOException e) {
			throw new IllegalArgumentException();
		}
    }
    
    /**
     * Creates a new TaskItemList.
     */
    public void createNewTaskItemList() {
        tasks = new TaskItemList();
    }
    
    /**
     * Returns a 2D array of objects containing information about the TaskItems in the
     * backlog with the ID number in the first slot, the state name in the second
     * slot, and the title in the third slot of the inner-array.
     * @return the 2D array of objects containing information about the TaskItems stored
     *         in the TaskItemList.
     */
    public Object[][] getTaskItemListAsArray() {
        ArrayList<TaskItem> list = (ArrayList<TaskItem>) tasks.getTaskItems();
		Object[][] array = new Object[list.size()][3];
    	for (int i = 0; i < list.size(); i++) {
			array[i][0] = tasks.getTaskItems().get(i).getTaskItemId();
			array[i][1] = tasks.getTaskItems().get(i).getStateName();
			array[i][2] = tasks.getTaskItems().get(i).getTitle();
		}
    	return array;
    }
    
    /**
     * Returns a 2D array of objects containing the TaskItems that have
     * the specified owner with the ID number in the first slot, the state name in the
     * second slot, and the title in the third slot of the inner-array.
     * @param owner the owner of the TaskItems
     * @return the 2D array of objects containing information about the TaskItems
     *         with the specified owner.
     */
    public Object[][] getTaskItemListByOwnerAsArray(String owner) {
    	ArrayList<TaskItem> list = (ArrayList<TaskItem>) tasks.getTaskItemsByOwner(owner);
    	Object[][] o = new Object[list.size()][3];
    	for (int i = 0; i < list.size(); i++) {
    		o[i][0] = list.get(i).getTaskItemId();
    		o[i][1] = list.get(i).getStateName();
    		o[i][2] = list.get(i).getTitle();
    	}
    	return o;
    }
    
    /**
     * Returns a 2D array of objects containing the TaskItems that have
     * the specified creator with the ID number in the first slot, the state name in
     * the second slot, and the title in the third slot of the inner-array.
     * @param creator the creator of the TaskItems
     * @return the 2D array of objects containing information about the TaskItems
     *         with the specified creator.
     */
    public Object[][] getTaskItemListByCreatorAsArray(String creator) {
    	ArrayList<TaskItem> list = (ArrayList<TaskItem>) tasks.getTasksByCreator(creator);
    	Object[][] o = new Object[list.size()][3];
    	for (int i = 0; i < list.size(); i++) {
    		o[i][0] = list.get(i).getTaskItemId();
    		o[i][1] = list.get(i).getStateName();
    		o[i][2] = list.get(i).getTitle();
    	}
    	return o;
    }
    
    /**
     * Returns the TaskItem with associated ID number.
     * @param id the ID number of the TaskItem
     * @return the TaskItem with the associated id
     */
    public TaskItem getTaskItemById(int id) {
        return tasks.getTaskItemById(id);
    }

    /**
     * Executes the given command on the TaskItem with provided ID number.
     * @param id the ID number of the TaskItem
     * @param command the command to execute
     */
    public void executeCommand(int id, Command command) {
       getTaskItemById(id).update(command);
    }

    /**
     * Deletes a TaskItem from the list with the given ID number.
     * @param id the ID number of the TaskItem
     */
    public void deleteTaskItemById(int id) {
        tasks.deleteTaskItemById(id);
    }

    /**
     * Adds a new TaskItem to the TaskItemList.
     * @param title the title of the TaskItem
     * @param type the type of the TaskItem
     * @param creator the creator of the TaskItem
     * @param note the note of the TaskItem
     */
    public void addTaskItemToList(String title, Type type, String creator, String note) {
        TaskItem.setCounter(tasks.addTaskItem(title, type, creator, note) + 1);
    }
}