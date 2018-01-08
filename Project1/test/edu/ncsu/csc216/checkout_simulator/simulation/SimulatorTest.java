/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.simulation;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

/**
 * Tests the Simulator class
 * @author Walker Booth
 *
 */
public class SimulatorTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.simulation.Simulator#Simulator(int, int)}.
	 */
	@Test
	public void testSimulator() {

		try {
			@SuppressWarnings("unused")
			Simulator s = new Simulator (8, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("There must be at least one shopping cart in the simulation." , e.getMessage());
		}
		
		try {
			@SuppressWarnings("unused")
			Simulator s = new Simulator (2, 1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Number of registers must be between 3 and 12 inclusive." , e.getMessage());
		}
		
		try {
			@SuppressWarnings("unused")
			Simulator s = new Simulator (13, 1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Number of registers must be between 3 and 12 inclusive." , e.getMessage());
		}
		
		Simulator s = new Simulator (9, 300);
		assertEquals(0, (int)(s.averageProcessTime()));
		assertEquals(0, (int)(s.averageWaitTime()));
		assertEquals(-1, s.getCurrentIndex());

		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.simulation.Simulator#simulationColors()}.
	 */
	@Test
	public void testSimulationColors() {
		assertEquals(Color.GREEN, Simulator.simulationColors()[0]);
		assertEquals(Color.BLUE, Simulator.simulationColors()[1]);
		assertEquals(Color.RED, Simulator.simulationColors()[2]);
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.simulation.Simulator#simulationLabels()}.
	 */
	@Test
	public void testSimulationLabels() {
		assertEquals("Express Cart", Simulator.simulationLabels()[0]);
		assertEquals("Regular Cart", Simulator.simulationLabels()[1]);
		assertEquals("Special Handling Cart", Simulator.simulationLabels()[2]);	
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.simulation.Simulator#step()}.
	 */
	@Test
	public void testStep() {
		Simulator s = new Simulator (8, 100);
		for (int i = 0; i < 200; i++) {
			s.step();
		}
		assertEquals(200, s.getStepsTaken());
		try {
			s.step();
			fail();
		} catch (IllegalStateException e) {
			assertEquals(200, s.getStepsTaken());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.simulation.Simulator#getStepsTaken()}.
	 */
	@Test
	public void testGetStepsTaken() {
		Simulator s = new Simulator (8, 100);
		s.step();
		assertEquals(1, s.getStepsTaken());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.simulation.Simulator#totalNumberOfSteps()}.
	 */
	@Test
	public void testTotalNumberOfSteps() {
		Simulator s = new Simulator (8, 100);
		assertEquals(200, s.totalNumberOfSteps());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.simulation.Simulator#moreSteps()}.
	 */
	@Test
	public void testMoreSteps() {
		Simulator s = new Simulator (8, 100);
		assertTrue(s.moreSteps());
		for (int i = 0; i < 200; i++) {
			s.step();
		}
		assertFalse(s.moreSteps());
	}

}
