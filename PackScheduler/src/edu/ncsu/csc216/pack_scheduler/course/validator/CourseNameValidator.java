package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Validates a Course Name using an object orientated finite state machine.
 * @author Alex Johnson
 * @author James Ritchey
 * @author Connor McCarthy 
 */
public class CourseNameValidator {
	/** The number of letters */
	private int letterCount;
	/** The number of digits */
	private int digitCount;
	/** If the current state is an acceptance */
	private boolean validEndState;
	/** The current State of the FSM */
	private State state;

	/**
	 * Creates a new CourseNameValidator
	 */
	public CourseNameValidator () {
		letterCount = 0;
		digitCount = 0;
		validEndState = false;
		state = new InitialState();
	}

	/**
	 * Determines if a course name is valid
	 * @param name the course name to check
	 * @return if the name is valid
	 * @throws InvalidTransitionException if there is an invalid transition
	 */
	public boolean isValid(String name) throws InvalidTransitionException {
		letterCount = 0;
		digitCount = 0;
		validEndState = false;
		state = new InitialState();

		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("Invalid name");
		}
		for (char c : name.toCharArray()) {

			if (Character.isAlphabetic(c)) {
				state.onLetter();
			} else if (Character.isDigit(c)) {
				state.onDigit();
			} else {
				state.onOther();
			}
		}
		return validEndState; 
	}
	/**
	 * Represents a current state in the CourseNameValidator FSM.
	 * @author Alex Johnson
	 * @author James Ritchey
	 * @author Connor McCarthy 
	 */
	public abstract class State {		
		/**
		 * Constructs a new state
		 */
		public State () {
			super();
		}

		/**
		 * How the state changes when a letter is next. If the transition is invalid it 
		 * will throw an exception.
		 * @throws InvalidTransitionException Thrown if there is an invalid transition in onLetter
		 */
		public abstract void onLetter () throws InvalidTransitionException;

		/**
		 * How the state changes when a digit is next.  If the transition is invalid it 
		 * will throw an exception.
		 * @throws InvalidTransitionException Thrown if there is an invalid transition in onDigit
		 */
		public abstract void onDigit () throws InvalidTransitionException;

		/**
		 * If a character other than a letter or a digit is part of the input.
		 * @throws InvalidTransitionException if Course Name contains anything but letters or digits
		 */
		public void onOther () throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	/**
	 * The InitalState class for the FSM. Represents when the FSM is waiting for 
	 * the first input.
	 * @author James Ritchey
	 * @author Alex Johnson
	 * @author Connor McCarthy 
	 */
	public class InitialState extends State {

		/**
		 * Constructs an Initial State. Private because no other class should
		 * be able to construct an InitialState
		 */
		private InitialState() {
			letterCount = 0;
			digitCount = 0;
			validEndState = false;
		}

		/**
		 * The actions that are performed when the InitialState is passed a letter.
		 * Since the initial state is expecting a letter, onLetter will change the
		 * state to LetterState.
		 */
		@Override
		public void onLetter() {
			state = new LetterState();
			letterCount++;
		}

		/**
		 * The actions that are performed when the InitialState is passed a digit.
		 * Since the initial state is expecting a letter, onDigit will always throw
		 * an InvalidTransitionException.
		 * @throws InvalidTransitionException always because Couse name cannot start 
		 * with a digit
		 */
		@Override
		public void onDigit() throws InvalidTransitionException{
			throw new InvalidTransitionException("Course name must start with a letter.");
		}

	}

	/**
	 * The LetterState for the FSM. Represents when the FSM has 1 to 4 of the 
	 * letter inputs.
	 * @author James Ritchey
	 * @author Alex Johnson
	 * @author Connor McCarthy 
	 */
	public class LetterState extends State {
		/** The maximum number of prefix letter in a course name */
		private static final int MAX_PREFIX_LETTERS = 4;

		/**
		 * Constructs a new LetterState. 
		 */
		private LetterState() {
		}

		/**
		 * The actions that are performed when the LetterState is passed a letter.
		 * Since the initial state is expecting a letter, onDigit will always throw
		 * an InvalidTransitionException.
		 * @throws InvalidTransitionException if there are more than 4 letters in the 
		 * beginning of the course name
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if(letterCount >= MAX_PREFIX_LETTERS){
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
			else{
				letterCount++;
			}
		}

		/**
		 * OnLetter always accepts a digit because a course can have 1 to 4 letters.
		 * Move the FSM on to the digit state.
		 */
		@Override
		public void onDigit() {
			if(letterCount > 0){
				state = new NumberState();
				digitCount++;
			}
		}
	}
	/**
	 * The NumberState for the FSM. Requires 3 digits from the input and throws
	 * an exception if is it given anything other than a digit.
	 * @author James Ritchey
	 * @author Alex Johnson
	 * @author Connor McCarthy 
	 */
	public class NumberState extends State {
		/** The required number of names */
		private static final int COURSE_NUMBER_LENGTH = 3;

		/**
		 * Constructs a new NumberState.
		 */
		private NumberState() {
		}

		/**
		 * The Number State required that number count to be 3, so if it is under 3 
		 * when this method is called it will throw an exception. If it is at 3 then it will
		 * move the FSM on to the suffix state
		 * @throws InvalidTransitionException if the method is called before the count is at 3
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if(digitCount == COURSE_NUMBER_LENGTH){
				state = new SuffixState();
				validEndState = true;
			}
			else{
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}

		/**
		 * Increases the number count and throws an error if the number of digits is  
		 * greater than 3.
		 * @throws InvalidTransitionException if the number of digits is greater than 3.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			if(digitCount >= COURSE_NUMBER_LENGTH){
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
			else{
				digitCount++;
				if(digitCount == COURSE_NUMBER_LENGTH){
					validEndState = true;
				}
			}
		}

	}
	/**
	 * Represents the SuffixState in the FSM. The Suffix state is an acceptance state 
	 * and required no characters.
	 * @author James Ritchey
	 * @author Alex Johnson
	 * @author Connor McCarthy 
	 */
	public class SuffixState extends State {

		/**
		 * Creates a new SuffixState
		 */
		private SuffixState() {
		}

		/**
		 * The SuffixState required that no additional characters exist past the suffix,
		 * so it will always throw an exception.
		 * @throws InvalidTransitionException if the method is called
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		/**
		 * The SuffixState required that no additional characters exist past the suffix,
		 * so it will always throw an exception.
		 * @throws InvalidTransitionException if the method is called
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}

	}
}


