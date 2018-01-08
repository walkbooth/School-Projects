package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests class CourseRoll.
 * @author Alex Johnson
 */
public class CourseRollTest {

	/** The course to test against */
	private static Course course;
	
	/**
	 * Sets up for the tests
	 */
	@Before 
	public void setup () {
		course = new Course("CSC226", "Intro to school", "004", 4, "jenkins", 10, "A", 1200, 1300);
	}
	
	/**
	 * Tests construction a CourseRoll
	 */
	@Test
	public void testCourseRoll() {
		
		// Tests invalid cap
		CourseRoll cr0 = null;
		try {
			cr0 = new CourseRoll(course, -1);
			fail("CourseRoll was constructed with capactiy -1");
		} catch (IllegalArgumentException e) {
			assertNull(cr0);
		}
		
		// Tests invalid cap
		CourseRoll cr1 = null;
		try {
			cr1 = new CourseRoll(course, 0);
			fail("CourseRoll was constructed with capactiy 0");
		} catch (IllegalArgumentException e) {
			assertNull(cr1);
		}
		
		// Tests invalid cap
		CourseRoll cr2 = null;
		try {
			cr2 = new CourseRoll(course, 9);
			fail("CourseRoll was constructed with capactiy 9");
		} catch (IllegalArgumentException e) {
			assertNull(cr2);
		}
		
		// Tests invalid cap
		CourseRoll cr3 = null;
		try {
			cr3 = new CourseRoll(course, 251);
			fail("CourseRoll was constructed with capactiy 251");
		} catch (IllegalArgumentException e) {
			assertNull(cr3);
		}
		
		// test boundaries and middle rolls
		CourseRoll cr4 = new CourseRoll(course, 10);
		assertEquals(10, cr4.getEnrollmentCap());
		assertEquals(10, cr4.getOpenSeats());
		CourseRoll cr5 = new CourseRoll(course, 11);
		assertEquals(11, cr5.getEnrollmentCap());
		CourseRoll cr6 = new CourseRoll(course, 168);
		assertEquals(168, cr6.getEnrollmentCap());
		CourseRoll cr7 = new CourseRoll(course, 249);
		assertEquals(249, cr7.getEnrollmentCap());
		assertEquals(249, cr7.getOpenSeats());
		CourseRoll cr8 = new CourseRoll(course, 250);
		assertEquals(250, cr8.getEnrollmentCap());
		
	}
	
	/**
	 * Tests method setEnrollmentCap of class CourseRoll
	 */
	@Test
	public void testSetEnrollmentCap () {
		Course ccc = new Course("CSC226", "Intro to school", "004", 4, "jenkins", 15, "A", 1200, 1300);
		CourseRoll cr1 = ccc.getCourseRoll();
		assertEquals(15, cr1.getEnrollmentCap());
		cr1.setEnrollmentCap(20);
		assertEquals(20, cr1.getEnrollmentCap());
		
		try {
			cr1.setEnrollmentCap(9);
			fail("Enrollment cap was set to 9");
		} catch (IllegalArgumentException e) {
			assertEquals(20, cr1.getEnrollmentCap());
		}
		
		try {
			cr1.setEnrollmentCap(251);
			fail("Enrollment cap was set to 251");
		} catch (IllegalArgumentException e) {
			assertEquals(20, cr1.getEnrollmentCap());
		}
		
		cr1.setEnrollmentCap(10);
		assertEquals(10, cr1.getEnrollmentCap());

		cr1.setEnrollmentCap(250);
		assertEquals(250, cr1.getEnrollmentCap());
		
		// TODO test line
		
	}
	
	/**
	 * Tests method enroll, of class CourseRoll
	 */
	@Test
	public void testEnroll () {
		
		CourseRoll cr1 = course.getCourseRoll();
		try {
			cr1.enroll(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(10, cr1.getOpenSeats());
		}

		cr1.enroll(new Student("Alex", "Johnson", "alex", "alex@ncsu.edu", "pw"));
		assertEquals(9, cr1.getOpenSeats());
		cr1.enroll(new Student("James", "Johnson", "james", "james@ncsu.edu", "pw"));
		assertEquals(8, cr1.getOpenSeats());
		
		try {
			cr1.enroll(new Student("Alex", "Johnson", "alex", "alex@ncsu.edu", "pw"));
			fail("duplicate student added");
		} catch (IllegalArgumentException e) {
			assertEquals(8, cr1.getOpenSeats());
		}
		
		
	}
	
	/**
	 * Tests method drop, of class CourseRoll
	 */
	@Test
	public void testDrop () {

		CourseRoll cr1 = course.getCourseRoll();
		assertEquals( 10, cr1.getOpenSeats());
		Student s1 = new Student("Alex", "Johnson", "alex", "alex@ncsu.edu", "pw");
		Student s2 = new Student("Carl", "Johnson", "carl", "carl@ncsu.edu", "pw");
		
		cr1.enroll(s1);
		assertEquals( 9, cr1.getOpenSeats());
		assertFalse(cr1.canEnroll(s1));
		
		cr1.drop(s1);
		assertEquals(10, cr1.getOpenSeats());
		assertTrue(cr1.canEnroll(s1));
		
		cr1.enroll(s1);
		cr1.enroll(s2);
		assertEquals(8, cr1.getOpenSeats());
		assertFalse(cr1.canEnroll(s1));
		assertFalse(cr1.canEnroll(s2));
		
		cr1.drop(s2);
		assertEquals(9, cr1.getOpenSeats());
		assertTrue(cr1.canEnroll(s2));
		assertFalse(cr1.canEnroll(s1));
		
		cr1.drop(s1);
		assertEquals(10, cr1.getOpenSeats());
		assertTrue(cr1.canEnroll(s1));
		assertTrue(cr1.canEnroll(s2));
		
	}
	
	/**
	 * Tests method getOpenSeats, of class CourseRoll
	 */
	@Test
	public void testGetOpenSeats () {
		CourseRoll cr1 = course.getCourseRoll();
		assertEquals( 10, cr1.getOpenSeats());
		assertEquals( 10, cr1.getOpenSeats()); // twice doesn't return different
		Student s1 = new Student("Alex", "Johnson", "Alex", "alex@ncsu.edu", "pw");
		Student s2 = new Student("Carl", "Johnson", "Carl", "carl@ncsu.edu", "pw");
		Student s3 = new Student("Ryan", "Johnson", "Ryan", "ryan@ncsu.edu", "pw");
		Student s4 = new Student("Fred", "Johnson", "Fred", "fred@ncsu.edu", "pw");
		
		cr1.enroll(s1);
		assertEquals( 9, cr1.getOpenSeats());
		cr1.enroll(s2);
		assertEquals( 8, cr1.getOpenSeats());
		cr1.enroll(s3); // adding 2
		cr1.enroll(s4);
		assertEquals( 6, cr1.getOpenSeats());
		
		cr1.drop(s1);
		assertEquals( 7, cr1.getOpenSeats());
		cr1.drop(s2);
		assertEquals( 8, cr1.getOpenSeats());
		cr1.drop(s3); // dropping 2
		cr1.drop(s4);
		assertEquals( 10, cr1.getOpenSeats());
		
	}
	
	/**
	 * Tests method canEnroll, of class CourseRoll
	 */
	@Test
	public void testCanEnroll () {
		
		CourseRoll cr1 = course.getCourseRoll();
		Student s1 = new Student("Alex", "Johnson", "Alex", "alex@ncsu.edu", "pw");
		Student s2 = new Student("Carl", "Johnson", "Carl", "carl@ncsu.edu", "pw");
		Student s3 = new Student("Ryan", "Johnson", "Ryan", "ryan@ncsu.edu", "pw");
		Student s4 = new Student("Fred", "Johnson", "Fred", "fred@ncsu.edu", "pw");

		assertTrue(cr1.canEnroll(s1));
		assertTrue(cr1.canEnroll(s2));
		assertTrue(cr1.canEnroll(s3));
		assertTrue(cr1.canEnroll(s4));
		
		cr1.enroll(s1);
		assertFalse(cr1.canEnroll(s1)); // doesn't affect others
		assertTrue(cr1.canEnroll(s2));
		assertTrue(cr1.canEnroll(s3));
		assertTrue(cr1.canEnroll(s4));
		cr1.enroll(s2);
		assertFalse(cr1.canEnroll(s2)); //twice works
		cr1.enroll(s3); // adding 2
		cr1.enroll(s4);
		assertFalse(cr1.canEnroll(s3));
		assertFalse(cr1.canEnroll(s4));

		assertFalse(cr1.canEnroll(s1));
		cr1.drop(s1);
		assertTrue(cr1.canEnroll(s1));
		assertFalse(cr1.canEnroll(s2));
		cr1.drop(s2);
		assertTrue(cr1.canEnroll(s2));
		assertFalse(cr1.canEnroll(s3));
		assertFalse(cr1.canEnroll(s4));
		cr1.drop(s3); // dropping 2
		cr1.drop(s4);
		assertTrue(cr1.canEnroll(s3));
		assertTrue(cr1.canEnroll(s4));
		
		try {
			cr1.canEnroll(null);
			fail("null didn't throw error");
		} catch (IllegalArgumentException e) {
			assertEquals(0, cr1.getNumberOnWaitlist()); 
			assertEquals(10, cr1.getOpenSeats()); 
		}
		
	}
	
	/**
	 * Tests the waitlist functionality of CourseRoll
	 */
	@Test
	public void testWaitList () {

		CourseRoll cr = new CourseRoll(course, 15);
		Student s0 = new Student("Steve", "Johnson", "Alex", "alex@ncsu.edu", "pw");
		Student s1 = new Student("Alex", "Johnson", "Alex", "alex@ncsu.edu", "pw");
		Student s2 = new Student("Carl", "Johnson", "Carl", "carl@ncsu.edu", "pw");
		Student s3 = new Student("Ryan", "Johnson", "Ryan", "ryan@ncsu.edu", "pw");
		Student s4 = new Student("Fred", "Johnson", "Fred", "fred@ncsu.edu", "pw");
		Student s5 = new Student("Bob", "Johnson", "Alex", "alex@ncsu.edu", "pw");
		Student s6 = new Student("Dave", "Johnson", "Carl", "carl@ncsu.edu", "pw");
		Student s7 = new Student("Andi", "Johnson", "Ryan", "ryan@ncsu.edu", "pw");
		Student s8 = new Student("Dennis", "Johnson", "Fred", "fred@ncsu.edu", "pw");
		Student s9 = new Student("Cole", "Johnson", "Alex", "alex@ncsu.edu", "pw");
		Student s10 = new Student("Austin", "Johnson", "Carl", "carl@ncsu.edu", "pw");
		Student s11 = new Student("Reed", "Johnson", "Ryan", "ryan@ncsu.edu", "pw");
		Student s12 = new Student("Pedro", "Johnson", "Fred", "fred@ncsu.edu", "pw");
		Student s13 = new Student("Sally", "Johnson", "Alex", "alex@ncsu.edu", "pw");
		Student s14 = new Student("Jessica", "Johnson", "Carl", "carl@ncsu.edu", "pw");
		Student s15 = new Student("Lindsey", "Johnson", "Ryan", "ryan@ncsu.edu", "pw");
		Student s16 = new Student("Elliot", "Johnson", "Fred", "fred@ncsu.edu", "pw");
		Student s17 = new Student("Christian", "Johnson", "Alex", "alex@ncsu.edu", "pw");
		Student s18 = new Student("Richard", "Johnson", "Carl", "carl@ncsu.edu", "pw");
		Student s19 = new Student("Matt", "Johnson", "Ryan", "ryan@ncsu.edu", "pw");
		Student s20 = new Student("SwaggyP", "Johnson", "Fred", "fred@ncsu.edu", "pw");
		Student s21 = new Student("Gavin", "Johnson", "Ryan", "ryan@ncsu.edu", "pw");
		Student s22 = new Student("Jacob", "Johnson", "Fred", "fred@ncsu.edu", "pw");
		Student s23 = new Student("Sharlot", "Johnson", "Alex", "alex@ncsu.edu", "pw");
		Student s24 = new Student("Mary", "Johnson", "Carl", "carl@ncsu.edu", "pw");
		Student s25 = new Student("Catherine", "Johnson", "Ryan", "ryan@ncsu.edu", "pw");
		
		cr.enroll(s0);
		assertEquals(0, cr.getNumberOnWaitlist());
		assertEquals(14, cr.getOpenSeats());
		cr.enroll(s1);
		cr.enroll(s2);
		cr.enroll(s3);
		cr.enroll(s4);
		cr.enroll(s5);
		cr.enroll(s6);
		cr.enroll(s7);
		cr.enroll(s8);
		cr.enroll(s21);
		cr.enroll(s22);
		cr.enroll(s23);
		// try setting capacity lower than count
		try {
			cr.setEnrollmentCap(10);
			fail("No exception thrown for new capacity less than enrollment");
		} catch (IllegalArgumentException e) {
			assertEquals(0, cr.getNumberOnWaitlist());
			assertEquals(3, cr.getOpenSeats());
		}
		// try dropping null student
		try {
			cr.drop(null);
			fail("Null student didn't throw error");
		} catch (IllegalArgumentException e) {
			assertEquals(0, cr.getNumberOnWaitlist());
			assertEquals(3, cr.getOpenSeats());
		}
		
		cr.enroll(s24);
		cr.enroll(s25);
		assertEquals(0, cr.getNumberOnWaitlist());
		assertEquals(1, cr.getOpenSeats());
		cr.enroll(s9);
		assertEquals(0, cr.getNumberOnWaitlist());
		assertEquals(0, cr.getOpenSeats());
		cr.enroll(s10);
		assertEquals(1, cr.getNumberOnWaitlist());
		assertEquals(0, cr.getOpenSeats());
		cr.enroll(s11);
		cr.enroll(s12);
		cr.enroll(s13);
		cr.enroll(s14);
		cr.enroll(s15);
		cr.enroll(s16);
		cr.enroll(s17);
		cr.enroll(s18);
		assertEquals(9, cr.getNumberOnWaitlist());
		assertEquals(0, cr.getOpenSeats());
		cr.enroll(s19);
		assertEquals(10, cr.getNumberOnWaitlist());
		assertEquals(0, cr.getOpenSeats());
		
		try {
			cr.enroll(s20);
			fail("Waitlist over capacity");
		} catch (IllegalArgumentException e) {
			assertEquals(10, cr.getNumberOnWaitlist()); 
			assertEquals(0, cr.getOpenSeats()); // nothing is changed
		}

		assertFalse(cr.canEnroll(s5));
		try {
			cr.enroll(s5);
			fail("Duplicate added");
		} catch (IllegalArgumentException e) {
			assertEquals(10, cr.getNumberOnWaitlist()); 
			assertEquals(0, cr.getOpenSeats()); // nothing is changed
		}

		assertFalse(cr.canEnroll(s15));
		try {
			cr.enroll(s15);
			fail("Duplicate added");
		} catch (IllegalArgumentException e) {
			assertEquals(10, cr.getNumberOnWaitlist()); 
			assertEquals(0, cr.getOpenSeats()); // nothing is changed
		}
		
		// If a student drops a class they'll be removed from the waitlist
		cr.drop(s0);
		assertEquals(9, cr.getNumberOnWaitlist());
		assertEquals(0, cr.getOpenSeats());
		cr.drop(s1);
		cr.drop(s2);
		assertEquals(7, cr.getNumberOnWaitlist());
		assertEquals(0, cr.getOpenSeats());
		
		// Dropping on waitlist
		cr.drop(s15);
		assertEquals(6, cr.getNumberOnWaitlist()); 
		assertEquals(0, cr.getOpenSeats()); // nothing is changed
		cr.drop(s16);
		assertEquals(5, cr.getNumberOnWaitlist()); 
		assertEquals(0, cr.getOpenSeats()); // nothing is changed
		
	}

}
