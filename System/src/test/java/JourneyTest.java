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
		JourneyDatabase journeyDatabase = new JourneyDatabase();
		assertNull(journey.getJourneyDatabase());
		journey.setJourneyDatabase(journeyDatabase);
		
		assertNotNull(journey.getJourneyDatabase());
	}

	@Test
	public void testCreateJourneyID() {
		JourneyDatabase journeyDatabase = new JourneyDatabase();
		journey.setJourneyDatabase(journeyDatabase);
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		assertNull(journey.getJourneyID());
		journey.createJourneyID();
		
		assertNotNull(journey.getJourneyID());
	}
	
	@Test
	public void testCreateJourneyID2() {
		JourneyDatabase journeys = new JourneyDatabase();
		Journey journey1 = new Journey();
		String journeyID1 = "CO23143";
		journey1.setJourneyID(journeyID1);
		journeys.create(journey1);
		
		Journey journey2 = new Journey();
		journey2.setOrigin("Copenhagen");
		journey2.setDestination("Oslo");
		journey2.setJourneyDatabase(journeys);
		assertNull(journey2.getJourneyID());
		journey2.createJourneyID();
		
		assertNotNull(journey2.getJourneyID());
	}

}
