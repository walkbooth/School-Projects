package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Represents a User in the Registration system.
 * @author Alex Johnson
 * @author James Ritchey
 * @author Connor McCarthy 
 */
public abstract class User {
	
	/** Student's first name. */
	private String firstName;
	/** Student's last name */
	private String lastName;
	/** Student's id */
	private String id;
	/** Student's email */
	private String email;
	/** Student's password */
	private String hashPW;

	/**
	 * Constructs a User for a first name, last name, id, email and hashed password
	 * @param firstName the first name of the user
	 * @param lastName the last name of the user
	 * @param id the id of the user
	 * @param email the user's email
	 * @param hashPW the hashed password of the user
	 */
	public User(String firstName, String lastName, String id, String email, String hashPW) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setId(id);
		this.setEmail(email);
		this.setPassword(hashPW);
	}
	


	/**
	 * Gets the student's first name.
	 * @return the student's first name 
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the student's last name.
	 * @return the student's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the student's id
	 * @return the student's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the student's email
	 * @return the student's email 
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the student's hashed password
	 * @return the hashed password 
	 */
	public String getPassword() {
		return hashPW;
	}

	/**
	 * Sets the students first name. Must not be null or empty string.
	 * @param firstName the first name to set 
	 * @throws IllegalArgumentException if firstName is null or empty string
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.length() < 1) {
			throw new IllegalArgumentException("Invalid first name");
		} else {
			this.firstName = firstName;
		}
	
	}

	/**
	 * Sets the students last name. Must not be null or empty string.
	 * @param lastName the first name to set 
	 * @throws IllegalArgumentException if lastName is null or empty string
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.length() < 1) {
			throw new IllegalArgumentException("Invalid last name");
		} else {
			this.lastName = lastName;
		}
	}

	/**
	 * Sets the students last name. Must not be null or empty string.
	 * @param id the id to set 
	 * @throws IllegalArgumentException if lastName is null or empty string
	 */
	protected void setId(String id) {
		if (id == null || id.length() < 1) {
			throw new IllegalArgumentException("Invalid id");
		} else {
			this.id = id;
		}
	}

	/**
	 * Sets the students email. Must not be null or empty string and must be a valid email. 
	 * @param email the email to set 
	 * @throws IllegalArgumentException if email is null or empty string or invalid
	 */
	public void setEmail(String email) {
		if (email == null || email.length() < 1 || !email.contains("@") || !email.contains(".") 
				|| email.lastIndexOf(".") < email.indexOf("@")) {
			throw new IllegalArgumentException("Invalid email");
		} else {
			this.email = email;
		}
	}

	/**
	 * Sets the students passed. Must not be null or empty string.
	 * @param hashPW the hashPW to set 
	 * @throws IllegalArgumentException if hashPW is null or empty string
	 */
	public void setPassword(String hashPW) {
		if (hashPW == null || hashPW.length() < 1) {
			throw new IllegalArgumentException("Invalid password");
		} else {
			this.hashPW = hashPW;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((hashPW == null) ? 0 : hashPW.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (hashPW == null) {
			if (other.hashPW != null)
				return false;
		} else if (!hashPW.equals(other.hashPW))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
}