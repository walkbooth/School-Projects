package edu.ncsu.csc316.customer_service.data;

/**
 * The HelpTicket class represents a HelpTicket object
 * @author Walker Booth (wgbooth)
 */
public class HelpTicket {

	/** The customer who submitted the help ticket */
	private Customer customer;
	/** The time that the help ticket was submitted */
	private Timestamp submitTime;
	/** The priority of the help ticket */
	private int priority;
	/** The question asked by the help ticket */
	private String question;
	/** The position of the help ticket */
	private int position;
	
	/**
	 * The constructor for HelpTickets
	 * @param customer the customer who submitted the help ticket
	 * @param submitTime the time that the help ticket was submitted 
	 * @param priority the priority that a help ticket has
	 * @param question the question asked in the help ticket 
	 */
	public HelpTicket (Customer customer, Timestamp submitTime, int priority, String question) {
		this.customer = customer;
		this.submitTime = submitTime;
		this.priority = priority;
		this.question = question;
		this.position = -1;
	}
	
	/**
	 * Returns the customer who submitted the help ticket
	 * @return customer the customer who submitted the help ticket
	 */
	public Customer getCustomer () {
		return customer;
	}
	
	/**
	 * Returns the time that the ticket was submitted
	 * @return submitTime the time that the ticket was submitted
	 */
	public Timestamp getSubmitTime () {
		return submitTime;
	}
	
	/**
	 * Returns the priority of the help ticket
	 * @return priority the priority of the help ticket
	 */
	public int getPriority () {
		return priority;
	}
	
	/**
	 * Returns the question asked on the help ticket
	 * @return question the question asked by the help ticket 
	 */
	public String getQuestion () {
		return question;
	}
	
	/**
	 * Returns the position of the ticket in the help ticket queue
	 * @return position the ticket's position in the help ticket queue
	 */
	public int getPosition () {
		return position;
	}
	
	/**
	 * Sets the position of the ticket in the help ticket queue
	 * @param position the ticket's new position in the help ticket queue
	 */
	public void setPosition (int position) {
		this.position = position;
	}

	/**
	 * Compares two HelpTicket objects by priority
	 * @param t the ticket to compare to 
	 * @return 1 if this ticket is greater than t, -1 if this ticket is less than t, and 0 if they are equal
	 */
	public int compareByPriority(HelpTicket t) {		
		if (priority < t.getPriority()) {
			return 1;
		} else if (priority > t.getPriority()) {
			return -1;
		} else {
			if (submitTime.compareTo(t.submitTime) < 0) {
				return -1;
			} else if (submitTime.compareTo(t.submitTime) > 0) {
				return 1;
			} else {
				if (customer.compareTo(t.getCustomer()) > 0) {
					return 1;
				} else if (customer.compareTo(t.getCustomer()) < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}
	
	/**
	 * Compares two HelpTicket objects by customer name 
	 * @param t the ticket to compare to
	 * @return intVal what customer.compareTo returns 
	 */
	public int compareByCustomer(HelpTicket t) {
		return customer.compareTo(t.getCustomer());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s = "Priority " + priority;
		s = s.concat(": submitted at ");
		s = s.concat(submitTime.dateString().concat(" "));
		s = s.concat(submitTime.timeString());
		s = s.concat(" by ");
		s = s.concat(customer.toString());
		s = s.concat(", Question: ");
		s = s.concat(question);
		return s;
	}
	
}
