

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import management.Journey;
import management.JourneyDatabase;

public class JourneyTest {
	
	private Journey journey;
	
	@Before
	public void setUp() {
		journey = new Journey();
	}

	@Test
	public void testGetJourneyID() {
		assertNull(journey.getJourneyID());
		journey.setJourneyID("CO23143");
		assertEquals("CO23143", journey.getJourneyID());
	}

	@Test
	public void testSetJourneyID() {
		journey.setJourneyID("CO22287");
		assertEquals("CO22287", journey.getJourneyID());
	}

	@Test
	public void testGetOrigin() {
		assertNull(journey.getOrigin());
		journey.setOrigin("Copenhagen");
		assertEquals("Copenhagen", journey.getOrigin());
	}

	@Test
	public void testSetOrigin() {
		journey.setOrigin("Nice");
		assertEquals("Nice", journey.getOrigin());
	}

	@Test
	public void testGetDestination() {
		assertNull(journey.getDestination());
		journey.setDestination("Accra");
		assertEquals("Accra", journey.getDestination());
	}

	@Test
	public void testSetDestination() {
		journey.setDestination("Istanbul");
		assertEquals("Istanbul", journey.getDestination());
	}

	@Test
	public void testGetDepartureDate() {
		assertNull(journey.getDepartureDate());
		journey.setDepartureDate("23/07/2030");
		assertEquals("23/07/2030", journey.getDepartureDate());
	}

	@Test
	public void testSetDepartureDate() {
		journey.setDepartureDate("23/07/2044");
		assertEquals("23/07/2044", journey.getDepartureDate());
	}

	@Test
	public void testGetArrivalDate() {
		assertNull(journey.getArrivalDate());
		journey.setArrivalDate("23/07/2030");
		assertEquals("23/07/2030", journey.getArrivalDate());
	}

	@Test
	public void testSetArrivalDate() {
		journey.setArrivalDate("23/07/2044");
		assertEquals("23/07/2044", journey.getArrivalDate());
	}

	@Test
	public void testSetJourneyDatabase() {
//		JourneyDatabase journeyDatabase = new JourneyDatabase();
//		assertNull(journey.getJourneyDatabase());
//		journey.setJourneyDatabase(journeyDatabase);
//		assertEquals(journeyDatabase, journey.getArrivalDate());
	}

	@Test
	public void testCreateJourneyID() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
