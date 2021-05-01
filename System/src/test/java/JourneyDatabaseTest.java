import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import management.Container;
import management.Journey;
import management.JourneyDatabase;
import response.ResponseObject;

public class JourneyDatabaseTest {
	
	private JourneyDatabase journeys;
	private ArrayList<Container> containers;
	private Journey journey;
	private Map<Journey, ArrayList<Container>> map;
	private ResponseObject response;
	
	@Before
	public void setUp() {
		journeys = new JourneyDatabase();
		containers = new ArrayList<Container>();
		journey = new Journey();
		map = new HashMap<Journey, ArrayList<Container>>();
		response = new ResponseObject(0, "New response object");
	}

	@Test
	public void testGetJourneys() {
		assertTrue(journeys.getJourneys().isEmpty());
		map.put(journey, containers);
		journeys.setJourneys(map);
		assertFalse(journeys.getJourneys().isEmpty());
	}

	@Test
	public void testSetJourneys() {
		map.put(journey, containers);
		journeys.setJourneys(map);
		assertEquals(journeys.getJourneys().size(),1);
	}

	@Test
	public void testCreate() {
		journey.setJourneyID("CO759342");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		response = journeys.create(journey);
		assertEquals(response.getErrorMessage(), "Journey has been created");
		assertEquals(response.getErrorCode(), 020);
	}
	
	@Test
	public void testCreate2() {
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		response = journeys.create(journey);
		assertEquals(response.getErrorMessage(), "Journey has been created");
		assertEquals(response.getErrorCode(), 020);
	}
	
	@Test
	public void testCreate3() {
		journey.setJourneyID("CO759342");
		journey.setOrigin("Copenhagen");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		response = journeys.create(journey);
		assertEquals(response.getErrorMessage(), "Necessary parameters are not entered");
		assertEquals(response.getErrorCode(), 210);
	}
	
	@Test
	public void testCreate4() {
		journey.setJourneyID("CO759342");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		response = journeys.create(journey);
		assertEquals(response.getErrorMessage(), "Necessary parameters are not entered");
		assertEquals(response.getErrorCode(), 210);
	}
	
	@Test
	public void testCreate5() {
		journey.setJourneyID("CO759342");
		journey.setDestination("Oslo");
		journey.setOrigin("Copenhagen");
		journey.setArrivalDate("");
		response = journeys.create(journey);
		assertEquals(response.getErrorMessage(), "Necessary parameters are not entered");
		assertEquals(response.getErrorCode(), 210);
	}
	
	@Test
	public void testCreate6() {
		journey.setJourneyID("CO759342");
		journey.setDestination("Oslo");
		journey.setOrigin("Copenhagen");
		journey.setDepartureDate("23/05/2020");
		response = journeys.create(journey);
		assertEquals(response.getErrorMessage(), "Necessary parameters are not entered");
		assertEquals(response.getErrorCode(), 210);
	}

	@Test
	public void testRegisterTo() {
		response = journeys.registerTo("CO83746", "Copenhagen", "Oslo", containers);
		assertEquals(response.getErrorMessage(), "Container is not found");
		assertEquals(response.getErrorCode(), 110);
	}
	
	@Test
	public void testRegisterTo2() {		
		Container container = new Container();
		container.setPosition("Copenhagen");
		containers.add(container);
		journey.setJourneyID("CO759342");
		journey.setDestination("Oslo");
		journey.setOrigin("Copenhagen");
		map.put(journey, containers);
		journeys.setJourneys(map);
		response = journeys.registerTo("CO85763", "Copenhagen", "Oslo", containers);
//		System.out.println(response.getErrorMessage());
		assertEquals(response.getErrorMessage(), "Container has been assigned to a journey");
		assertEquals(response.getErrorCode(), 014);
	}
	
	@Test
	public void testRegisterTo3() {
		Container container = new Container();
		container.setPosition("Accra");
		containers.add(container);
		journey.setJourneyID("CO759342");
		journey.setDestination("Oslo");
		journey.setOrigin("Copenhagen");
		map.put(journey, containers);
		journeys.setJourneys(map);
		response = journeys.registerTo("CO759342", "Copenhagen", "Oslo", containers);
		assertEquals(response.getErrorMessage(), "Journey is not found");
		assertEquals(response.getErrorCode(), 120);
	}
	
	@Test
	public void testRegisterTo4() {
		Container container = new Container();
		container.setPosition("Copenhagen");
		containers.add(container);
		journey.setJourneyID("CO759342");
		journey.setDestination("Oslo");
		journey.setOrigin("Copenhagen");
		map.put(journey, containers);
		journeys.setJourneys(map);
		response = journeys.registerTo("CO759342", "Accra", "Oslo", containers);
		assertEquals(response.getErrorMessage(), "Journey is not found");
		assertEquals(response.getErrorCode(), 120);
	}
	
	@Test
	public void testRegisterTo5() {
		Container container = new Container();
		container.setPosition("Copenhagen");
		containers.add(container);
		journey.setJourneyID("CO759342");
		journey.setDestination("Oslo");
		journey.setOrigin("Copenhagen");
		map.put(journey, containers);
		journeys.setJourneys(map);
		response = journeys.registerTo("CO759342", "Copenhagen", "Accra", containers);
		assertEquals(response.getErrorMessage(), "Journey is not found");
		assertEquals(response.getErrorCode(), 120);
	}
	
	@Test
	public void testRegisterTo6() {
		Container container = new Container();
		container.setPosition("Copenhagen");
		containers.add(container);
		journey.setJourneyID("CO759342");
		journey.setDestination("Oslo");
		journey.setOrigin("Copenhagen");
		journeys.create(journey);
		response = journeys.registerTo("CO759942", "Copenhagen", "Oslo", containers);
		assertEquals(response.getErrorMessage(), "Journey is not found");
		assertEquals(response.getErrorCode(), 120);
	}
	
	@Test
	public void testRegisterTo7() {
		Container container = new Container();
		containers.add(container);
		container.setPosition("Accra");
		response = journeys.registerTo("CO759342", "Copenhagen", "Oslo", containers);
		assertEquals(response.getErrorMessage(), "Journey is not found");
		assertEquals(response.getErrorCode(), 120);
	}

	@Test
	public void testUpdatePosition() {
		journey.setJourneyID("CO85763");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		map.put(journey, containers);
		journeys.setJourneys(map);
		Container container1 = new Container();
		container1.setCurrentJourney("CO85763");
		containers.add(container1);
		response = journeys.updatePosition("CO85763", "Accra");
		assertEquals(response.getErrorMessage(), "Position has been updated");
		assertEquals(response.getErrorCode(), 070);
	}

	@Test
	public void testUpdatePosition2() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		map.put(journey, containers);
		journeys.setJourneys(map);
		Container container1 = new Container();
		container1.setCurrentJourney("CO85763");
		containers.add(container1);
		response = journeys.updatePosition("CO85763", "Accra");
		assertEquals(response.getErrorMessage(), "Journey is not found");
		assertEquals(response.getErrorCode(), 120);
	}
	
	@Test
	public void testSetDeparture() {
		journey.setJourneyID("CO85763");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		map.put(journey, containers);
		journeys.setJourneys(map);
		response = journeys.setDeparture("CO85763","12/05/3032");
		assertEquals(response.getErrorMessage(), "Departure date has been set");
		assertEquals(response.getErrorCode(), 071);
	}
	
	@Test
	public void testSetDeparture2() {
		journey.setJourneyID("CO85763");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		map.put(journey, containers);
		journeys.setJourneys(map);
		response = journeys.setDeparture("CO99999","12/05/3032");
		assertEquals(response.getErrorMessage(), "Journey is not found");
		assertEquals(response.getErrorCode(), 120);
	}
	
	@Test
	public void testSetDeparture3() {
		response = journeys.setDeparture("CO99999","12/05/3032");
		assertEquals(response.getErrorMessage(), "Journey is not found");
		assertEquals(response.getErrorCode(), 120);
	}

	@Test
	public void testMarkArrived() {
		journey.setJourneyID("CO85763");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		map.put(journey, containers);
		journeys.setJourneys(map);
		Container container1 = new Container();
		container1.setCurrentJourney("CO85763");
		containers.add(container1);
		response = journeys.markArrived("CO85763");
		assertEquals(response.getErrorMessage(), "Arrival date has been set");
		assertEquals(response.getErrorCode(), 072);
	}
	
	@Test
	public void testMarkArrived2() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		map.put(journey, containers);
		journeys.setJourneys(map);
		Container container1 = new Container();
		container1.setCurrentJourney("CO85763");
		containers.add(container1);
		response = journeys.markArrived("CO85763");
		assertEquals(response.getErrorMessage(), "Journey is not found");
		assertEquals(response.getErrorCode(), 120);
	}

	@Test
	public void testComplete() {
		journey.setJourneyID("CO85763");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		map.put(journey, containers);
		journeys.setJourneys(map);
		Container container1 = new Container();
		container1.setCurrentJourney("CO85763");
		containers.add(container1);
		response = journeys.complete("CO85763");
		assertEquals(response.getErrorMessage(), "Journey has been completed and succesfully removed");
		assertEquals(response.getErrorCode(), 021);

	}
	
	@Test
	public void testComplete2() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		map.put(journey, containers);
		journeys.setJourneys(map);
		Container container1 = new Container();
		container1.setCurrentJourney("CO85763");
		containers.add(container1);
		response = journeys.markArrived("CO85763");
		assertEquals(response.getErrorMessage(), "Journey is not found");
		assertEquals(response.getErrorCode(), 120);
	}
	
	@Test
	public void testComplete3() {
		response = journeys.markArrived("CO85763");
		assertEquals(response.getErrorMessage(), "Journey is not found");
		assertEquals(response.getErrorCode(), 120);
	}

	@Test
	public void testRemoveContainer() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		map.put(journey, containers);
		journeys.setJourneys(map);
		Container container1 = new Container();
		container1.setContainerID(12);
		containers.add(container1);
		response = journeys.removeContainer("CO89999", 12);
		assertEquals(response.getErrorMessage(), "Container has been succesfully removed");
		assertEquals(response.getErrorCode(), 011);
	}
	
	@Test
	public void testRemoveContainer2() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		map.put(journey, containers);
		journeys.setJourneys(map);
		Container container1 = new Container();
		container1.setContainerID(12);
		containers.add(container1);
		response = journeys.removeContainer("CO89769", 12);
		assertEquals(response.getErrorMessage(), "Container is not found");
		assertEquals(response.getErrorCode(), 110);
	}
	
	@Test
	public void testRemoveContainer3() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		map.put(journey, containers);
		journeys.setJourneys(map);
		Container container1 = new Container();
		container1.setContainerID(12);
		containers.add(container1);
		response = journeys.removeContainer("CO89999", 15);
		assertEquals(response.getErrorMessage(), "Container is not found");
		assertEquals(response.getErrorCode(), 110);
	}

	@Test
	public void testExtract() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		map.put(journey, containers);
		journeys.setJourneys(map);
		Container container1 = new Container();
		container1.setOwner(12);
		containers.add(container1);
		assertEquals(journeys.extract(12).size(), 1);
	}
	
	@Test
	public void testExtract2() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		map.put(journey, containers);
		journeys.setJourneys(map);
		Container container1 = new Container();
		container1.setOwner(12);
		containers.add(container1);
		assertEquals(journeys.extract(17).size(), 0);
	}

	@Test
	public void testFind() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		map.put(journey, containers);
		Journey journey2 = new Journey();
		journey2.setJourneyID("CO80000");
		journey2.setOrigin("Accra");
		journey2.setDestination("Oslo");
		journey2.setDepartureDate("23/05/2020");
		journey2.setArrivalDate("23/05/2020");
		map.put(journey2, containers);
		journeys.setJourneys(map);
		assertEquals(journeys.find("journeyID", "CO89999").size(), 1);
	}
	
	@Test
	public void testFind2() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		map.put(journey, containers);
		Journey journey2 = new Journey();
		journey2.setJourneyID("CO80000");
		journey2.setOrigin("Accra");
		journey2.setDestination("Oslo");
		journey2.setDepartureDate("23/05/2020");
		journey2.setArrivalDate("23/05/2020");
		map.put(journey2, containers);
		journeys.setJourneys(map);
		assertEquals(journeys.find("origin", "Copenhagen").size(), 1);
	}
	
	@Test
	public void testFind3() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		map.put(journey, containers);
		Journey journey2 = new Journey();
		journey2.setJourneyID("CO80000");
		journey2.setOrigin("Accra");
		journey2.setDestination("Oslo");
		journey2.setDepartureDate("23/05/2020");
		journey2.setArrivalDate("23/05/2020");
		map.put(journey2, containers);
		Journey journey3 = new Journey();
		journey3.setJourneyID("CO80000");
		journey3.setOrigin("Accra");
		journey3.setDestination("Manchester");
		journey3.setDepartureDate("23/05/2020");
		journey3.setArrivalDate("23/05/2020");
		map.put(journey3, containers);
		journeys.setJourneys(map);
		assertEquals(journeys.find("destination", "oslo").size(), 2);
	}
	
	@Test
	public void testFind4() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		map.put(journey, containers);
		Journey journey2 = new Journey();
		journey2.setJourneyID("CO80000");
		journey2.setOrigin("Accra");
		journey2.setDestination("Oslo");
		journey2.setDepartureDate("23/05/2020");
		journey2.setArrivalDate("23/05/2020");
		map.put(journey2, containers);
		Journey journey3 = new Journey();
		journey3.setJourneyID("CO80000");
		journey3.setOrigin("Accra");
		journey3.setDestination("Oslo");
		journey3.setDepartureDate("29/05/2020");
		journey3.setArrivalDate("23/05/2020");
		map.put(journey3, containers);
		journeys.setJourneys(map);
		assertEquals(journeys.find("departureDate", "23/05/2020").size(), 2);
	}
	
	@Test
	public void testFind5() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		map.put(journey, containers);
		Journey journey2 = new Journey();
		journey2.setJourneyID("CO80000");
		journey2.setOrigin("Accra");
		journey2.setDestination("Oslo");
		journey2.setDepartureDate("23/05/2020");
		journey2.setArrivalDate("23/05/2020");
		map.put(journey2, containers);
		journeys.setJourneys(map);
		assertEquals(journeys.find("arrivalDate", "23/05/2020").size(), 1);
	}
	
	@Test
	public void testFind6() {
		assertEquals(journeys.find("somethingElse", "23/05/2020").size(), 0);
	}
		

	@Test
	public void testGetColumnCount() {
		assertEquals(journeys.getColumnCount(), 5);
	}

	@Test
	public void testGetRowCount() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		map.put(journey, containers);
		Journey journey2 = new Journey();
		journey2.setJourneyID("CO80000");
		journey2.setOrigin("Accra");
		journey2.setDestination("Oslo");
		journey2.setDepartureDate("23/05/2020");
		journey2.setArrivalDate("23/05/2020");
		map.put(journey2, containers);
		journeys.setJourneys(map);
		assertEquals(journeys.getRowCount(), 2);
	}

	@Test
	public void testGetColumnNameInt() {
		String name = journeys.getColumnName(0);
		assertEquals(name, "Journey ID");
	}
	
	@Test
	public void testGetColumnNameInt2() {
		String name = journeys.getColumnName(1);
		assertEquals(name, "Origin");
	}
	
	@Test
	public void testGetColumnNameInt3() {
		String name = journeys.getColumnName(2);
		assertEquals(name, "Destination");
	}
	
	@Test
	public void testGetColumnNameInt4() {
		String name = journeys.getColumnName(3);
		assertEquals(name, "Departure");
	}
	
	@Test
	public void testGetColumnNameInt5() {
		String name = journeys.getColumnName(4);
		assertEquals(name, "Arrival");
	}
	
	@Test
	public void testGetColumnNameInt6() {
		String name = journeys.getColumnName(9);
		assertNull(name);
	}
	
	@Test
	public void testGetValueAt() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		map.put(journey, containers);
		Journey journey2 = new Journey();
		journey2.setJourneyID("CO80000");
		journey2.setOrigin("Accra");
		journey2.setDestination("Oslo");
		journey2.setDepartureDate("23/05/2020");
		journey2.setArrivalDate("23/05/2020");
		map.put(journey2, containers);
		journeys.setJourneys(map);
		
		Object ID = journeys.getValueAt(0, 0);
		assertEquals(ID, "CO89999");
	}
	
	@Test
	public void testGetValueAt2() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		map.put(journey, containers);
		Journey journey2 = new Journey();
		journey2.setJourneyID("CO80000");
		journey2.setOrigin("Accra");
		journey2.setDestination("Oslo");
		journey2.setDepartureDate("23/05/2020");
		journey2.setArrivalDate("23/05/2020");
		map.put(journey2, containers);
		journeys.setJourneys(map);
		
		Object origin = journeys.getValueAt(1, 1);
		assertEquals(origin, "Accra");
	}
	
	@Test
	public void testGetValueAt3() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		map.put(journey, containers);
		Journey journey2 = new Journey();
		journey2.setJourneyID("CO80000");
		journey2.setOrigin("Accra");
		journey2.setDestination("Oslo");
		journey2.setDepartureDate("23/05/2020");
		journey2.setArrivalDate("23/05/2020");
		map.put(journey2, containers);
		journeys.setJourneys(map);
		
		Object destination = journeys.getValueAt(0, 2);
		assertEquals(destination, "Oslo");
	}
	
	@Test
	public void testGetValueAt4() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		map.put(journey, containers);
		Journey journey2 = new Journey();
		journey2.setJourneyID("CO80000");
		journey2.setOrigin("Accra");
		journey2.setDestination("Oslo");
		journey2.setDepartureDate("23/05/2020");
		journey2.setArrivalDate("23/05/2020");
		map.put(journey2, containers);
		journeys.setJourneys(map);
		
		Object departure = journeys.getValueAt(1, 3);
		assertEquals(departure, "23/05/2020");
	}
	
	@Test
	public void testGetValueAt5() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		map.put(journey, containers);
		Journey journey2 = new Journey();
		journey2.setJourneyID("CO80000");
		journey2.setOrigin("Accra");
		journey2.setDestination("Oslo");
		journey2.setDepartureDate("23/05/2020");
		journey2.setArrivalDate("23/05/2020");
		map.put(journey2, containers);
		journeys.setJourneys(map);
		
		Object arrival = journeys.getValueAt(0, 4);
		assertEquals(arrival, "");
	}
	
	@Test
	public void testGetValueAt6() {
		journey.setJourneyID("CO89999");
		journey.setOrigin("Copenhagen");
		journey.setDestination("Oslo");
		journey.setDepartureDate("23/05/2020");
		journey.setArrivalDate("");
		map.put(journey, containers);
		Journey journey2 = new Journey();
		journey2.setJourneyID("CO80000");
		journey2.setOrigin("Accra");
		journey2.setDestination("Oslo");
		journey2.setDepartureDate("23/05/2020");
		journey2.setArrivalDate("23/05/2020");
		map.put(journey2, containers);
		journeys.setJourneys(map);
		
		Object object = journeys.getValueAt(0, 7);
		assertNull(object);
	}

}
