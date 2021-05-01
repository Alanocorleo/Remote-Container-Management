
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import management.Container;
import management.ContainerDatabase;
import response.ResponseObject;

public class ContainerDatabaseTest {
	private ContainerDatabase containerDatabase;
	private ArrayList<Container> containers;
	private Container container1;
	private Container container2;
	private Container container3;
	private Container container4;
	private ResponseObject response;
	
	@Before
	public void setUp() {
		containerDatabase = new ContainerDatabase();
		containers = new ArrayList<>();
		container1 = new Container();
		container2 = new Container();
		container3 = new Container();
		container4 = new Container();
		ResponseObject response = new ResponseObject(0,"New response object");
	}
	
	@Test
	public void testGetContainers() {
		 containers.add(container1);
		 containers.add(container2);
		 containers.add(container3);
		 containers.add(container4);
		 
		 assertEquals(0, containerDatabase.getContainers().size());
		 containerDatabase.setContainers(containers);
		 assertEquals(4, containerDatabase.getContainers().size());
	}
	
	@Test
	public void testSetContainers() {
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containerDatabase.setContainers(containers);
		
		assertEquals(3, containerDatabase.getContainers().size());
	}

	@Test
	public void testProduce() {
		fail("Not yet implemented");
	}

	@Test
	public void testPull() {
		fail("Not yet implemented");
	}

	@Test
	public void testPush() {
		fail("Not yet implemented");
	}

	@Test
	public void testExtractIntString() {
		container1.setAvailability(true);
		container2.setAvailability(true);
		container1.setPosition("Copenhagen");
		container2.setPosition("Copenhagen");
		container3.setPosition("Copenhagen");
		container4.setPosition("Copenhagen");
		containerDatabase.setContainers(containers);
		
		assertEquals(0, containerDatabase.extract(4, "Copenhagen").size());
	}
	
	@Test
	public void testExtractIntString2() {
		container1.setAvailability(true);
		container2.setAvailability(true);
		container1.setPosition("Copenhagen");
		container2.setPosition("Copenhagen");
		container3.setPosition("Copenhagen");
		container4.setPosition("Copenhagen");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		assertEquals(0, containerDatabase.extract(0, "Copenhagen").size());
		assertEquals(1, containerDatabase.extract(1, "Copenhagen").size());
		assertEquals(2, containerDatabase.extract(2, "Copenhagen").size());
	}
	
	@Test
	public void testExtractIntString3() {
		container1.setAvailability(true);
		container2.setAvailability(true);
		container1.setPosition("Copenhagen");
		container2.setPosition("Copenhagen");
		container3.setPosition("Copenhagen");
		container4.setPosition("Copenhagen");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		assertEquals(2, containerDatabase.extract(3, "Copenhagen").size());
	}
	
	@Test
	public void testExtractIntString4() {
		container1.setAvailability(true);
		container2.setAvailability(true);
		container1.setPosition("Copenhagen");
		container2.setPosition("Copenhagen");
		container3.setPosition("Copenhagen");
		container4.setPosition("Copenhagen");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		assertEquals(0, containerDatabase.extract(3, "Oslo").size());
	}

	@Test
	public void testExtractInt() {
		container1.setAvailability(true);
		container2.setAvailability(true);
		container3.setAvailability(true);
		container4.setAvailability(true);
		container1.setOwner(2);
		container2.setOwner(2);
		container3.setOwner(6);
		container4.setOwner(2);
		containerDatabase.setContainers(containers);
		
		assertEquals(0, containerDatabase.extract(2).size());
	}
	
	@Test
	public void testExtractInt2() {
		container1.setAvailability(true);
		container2.setAvailability(true);
		container3.setAvailability(true);
		container4.setAvailability(true);
		container1.setOwner(2);
		container2.setOwner(2);
		container3.setOwner(6);
		container4.setOwner(2);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		assertEquals(0, containerDatabase.extract(30).size());
	}
	
	@Test
	public void testExtractInt3() {
		container1.setAvailability(true);
		container2.setAvailability(true);
		container3.setAvailability(true);
		container4.setAvailability(true);
		container1.setOwner(2);
		container2.setOwner(2);
		container3.setOwner(6);
		container4.setOwner(2);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		assertEquals(1, containerDatabase.extract(6).size());
		assertEquals(3, containerDatabase.extract(2).size());
	}


	@Test
	public void testBook() {
		container1.setPosition("Copenhagen");
		container2.setPosition("Copenhagen");
		container3.setPosition("Copenhagen");
		container4.setPosition("Copenhagen");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.book(0, "Copenhagen", "Fish", "Captain Fish", 4);
		assertEquals(response.getErrorCode(), 131);
	}
	
	@Test
	public void testBook2() {
		container1.setPosition("Copenhagen");
		container2.setPosition("Copenhagen");
		container3.setPosition("Copenhagen");
		container4.setPosition("Copenhagen");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.book(4, null, "Fish", "Captain Fish", 4);
		assertEquals(response.getErrorCode(), 210);
	}
	
	@Test
	public void testBook3() {
		container1.setPosition("Copenhagen");
		container2.setPosition("Copenhagen");
		container3.setPosition("Copenhagen");
		container4.setPosition("Copenhagen");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.book(4, "Copenhagen", null, "Captain Fish", 4);
		assertEquals(response.getErrorCode(), 210);
	}
	
	@Test
	public void testBook4() {
		container1.setPosition("Copenhagen");
		container2.setPosition("Copenhagen");
		container3.setPosition("Copenhagen");
		container4.setPosition("Copenhagen");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.book(4, "Copenhagen", "Fish", null, 4);
		assertEquals(response.getErrorCode(), 210);
	}
	
	@Test
	public void testBook5() {
		container1.setPosition("Copenhagen");
		container2.setPosition("Copenhagen");
		container3.setPosition("Copenhagen");
		container4.setPosition("Copenhagen");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.book(4, "Copenhagen", "Fish", "Captain Fish", 0);
		assertEquals(response.getErrorCode(), 210);
	}
	
	@Test
	public void testBook6() {
		container1.setAvailability(true);
		container2.setAvailability(true);
		container3.setAvailability(true);
		container4.setAvailability(true);
		container1.setPosition("Copenhagen");
		container2.setPosition("Copenhagen");
		container3.setPosition("Copenhagen");
		container4.setPosition("Copenhagen");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.book(4, "Copenhagen", "Fish", "Captain Fish", 3);
		assertEquals(response.getErrorCode(), 010);
	}
	
	@Test
	public void testBook7() {
		container1.setAvailability(false);
		container2.setAvailability(false);
		container3.setAvailability(false);
		container4.setAvailability(false);
		container1.setPosition("Copenhagen");
		container2.setPosition("Copenhagen");
		container3.setPosition("Copenhagen");
		container4.setPosition("Copenhagen");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.book(4, "Copenhagen", "Fish", "Captain Fish", 3);
		System.out.println(response.getErrorCode());
		assertEquals(response.getErrorCode(), 110);
	}
	
	@Test
	public void testBook8() {
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.book(4, "Copenhagen", "Fish", "Captain Fish", 3);
		assertEquals(response.getErrorCode(), 110);
	}

	@Test
	public void testFindStringInt() {
		container1.setOwner(4);
		container2.setOwner(4);
		container3.setOwner(6);
		container4.setOwner(9);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		assertEquals(containerDatabase.find(null, 4).size(), 0);
	}
	
	@Test
	public void testFindStringInt2() {
		container1.setOwner(4);
		container2.setOwner(4);
		container3.setOwner(6);
		container4.setOwner(9);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		assertEquals(containerDatabase.find("owner", 4).size(), 2);
		assertEquals(containerDatabase.find("owner", 9).size(), 1);
	}
	
	@Test
	public void testFindStringInt3() {
		container1.setOwner(4);
		container2.setOwner(4);
		container3.setOwner(6);
		container4.setOwner(9);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		assertEquals(containerDatabase.find("owner", 7).size(), 0);
	}
	
	@Test
	public void testFindStringInt4() {
		container1.setContainerID(4);
		container2.setContainerID(10);
		container3.setContainerID(6);
		container4.setContainerID(9);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		assertEquals(containerDatabase.find("containerID", 10).size(), 1);
	}
	
	@Test
	public void testFindStringInt5() {
		container1.setContainerID(4);
		container2.setContainerID(10);
		container3.setContainerID(6);
		container4.setContainerID(9);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		assertEquals(containerDatabase.find("containerID", 12).size(), 0);
	}
	
	@Test
	public void testFindStringInt6() {
		containerDatabase.setContainers(containers);
		
		assertEquals(containerDatabase.find("owner", 4).size(), 0);
		assertEquals(containerDatabase.find("containerID", 10).size(), 0);
	}

	@Test
	public void testFindStringString() {
	container1.setPosition("Accra");
	container2.setPosition("Accra");
	container3.setPosition("Accra");
	container4.setPosition("Manchester");
	containers.add(container1);
	containers.add(container2);
	containers.add(container3);
	containers.add(container4);
	containerDatabase.setContainers(containers);
	
	assertEquals(containerDatabase.find(null, "Manchester").size(), 0);
	}
	
	@Test
	public void testFindStringString2() {
	container1.setPosition("Accra");
	container2.setPosition("Accra");
	container3.setPosition("Accra");
	container4.setPosition("Manchester");
	containers.add(container1);
	containers.add(container2);
	containers.add(container3);
	containers.add(container4);
	containerDatabase.setContainers(containers);
	
	assertEquals(containerDatabase.find("position", "mANCheSTer").size(), 1);
	}
	
	@Test
	public void testFindStringString3() {
	container1.setPosition("Accra");
	container2.setPosition("Accra");
	container3.setPosition("Accra");
	container4.setPosition("Manchester");
	containers.add(container1);
	containers.add(container2);
	containers.add(container3);
	containers.add(container4);
	containerDatabase.setContainers(containers);
	
	assertEquals(containerDatabase.find("position", "Oslo").size(), 0);
	}
	
	@Test
	public void testFindStringString4() {
	container1.setPosition("Accra");
	container2.setPosition("Accra");
	container4.setPosition("Manchester");
	containers.add(container1);
	containers.add(container2);
	containers.add(container3);
	containers.add(container4);
	containerDatabase.setContainers(containers);
	
	assertEquals(containerDatabase.find("position", "Accra").size(), 2);
	}
	
	@Test
	public void testFindStringString5() {
	container1.setContentType("Oranges");
	container2.setContentType("Oranges");
	container3.setContentType("Peaches");
	container4.setContentType("Avocado");
	containers.add(container1);
	containers.add(container2);
	containers.add(container3);
	containers.add(container4);
	containerDatabase.setContainers(containers);
	
	assertEquals(containerDatabase.find("contentType", "oranges").size(), 2);
	}
	
	@Test
	public void testFindStringString6() {
	container1.setContentType("Oranges");
	container2.setContentType("Oranges");
	container3.setContentType("Peaches");
	container4.setContentType("Avocado");
	containers.add(container1);
	containers.add(container2);
	containers.add(container3);
	containers.add(container4);
	containerDatabase.setContainers(containers);
	
	assertEquals(containerDatabase.find("contentType", "plums").size(), 0);
	}
	
	@Test
	public void testFindStringString7() {
	container1.setContentType("Oranges");
	container3.setContentType("Peaches");
	container4.setContentType("Avocado");
	containers.add(container1);
	containers.add(container2);
	containers.add(container3);
	containers.add(container4);
	containerDatabase.setContainers(containers);
	
	assertEquals(containerDatabase.find("contentType", "peaches").size(), 1);
	}
	
	@Test
	public void testFindStringString8() {
	container1.setCompany("Captain Orange");
	container2.setCompany("Captain Orange");
	container3.setCompany("Captain Peach");
	container4.setCompany("Captain Avocado");
	containers.add(container1);
	containers.add(container2);
	containers.add(container3);
	containers.add(container4);
	containerDatabase.setContainers(containers);
	
	assertEquals(containerDatabase.find("company", "captain orange").size(), 2);
	}
	
	@Test
	public void testFindStringString9() {
	container1.setCompany("Captain Orange");
	container2.setCompany("Captain Orange");
	container3.setCompany("Captain Peach");
	container4.setCompany("Captain Avocado");
	containers.add(container1);
	containers.add(container2);
	containers.add(container3);
	containers.add(container4);
	containerDatabase.setContainers(containers);
	
	assertEquals(containerDatabase.find("company", "Captain Plum").size(), 0);
	}
	
	@Test
	public void testFindStringString10() {
	container1.setCompany("Captain Orange");
	container2.setCompany("Captain Orange");
	container3.setCompany("Captain Peach");
	containers.add(container1);
	containers.add(container2);
	containers.add(container3);
	containers.add(container4);
	containerDatabase.setContainers(containers);
	
	assertEquals(containerDatabase.find("company", "Captain orange").size(), 2);
	}
	
	@Test
	public void testFindStringString11() {
	container1.setCurrentJourney("CO1111");
	container2.setCurrentJourney("CO1111");
	container3.setCurrentJourney("CO1111");
	container4.setCurrentJourney("CO1111");
	containers.add(container1);
	containers.add(container2);
	containers.add(container3);
	containers.add(container4);
	containerDatabase.setContainers(containers);
	
	assertEquals(containerDatabase.find("journeyID", "co1111").size(), 4);
	}
	
	@Test
	public void testFindStringString12() {
	container1.setCurrentJourney("CO1111");
	container2.setCurrentJourney("CO1111");
	container3.setCurrentJourney("CO1111");
	container4.setCurrentJourney("CO1111");
	containers.add(container1);
	containers.add(container2);
	containers.add(container3);
	containers.add(container4);
	containerDatabase.setContainers(containers);
	
	assertEquals(containerDatabase.find("journeyID", "MS8273").size(), 0);
	}
	
	@Test
	public void testFindStringString13() {
	container3.setCurrentJourney("CO1111");
	container4.setCurrentJourney("CO1111");
	containers.add(container2);
	containers.add(container3);
	containers.add(container4);
	containerDatabase.setContainers(containers);
	
	assertEquals(containerDatabase.find("journeyID", "CO1111").size(), 2);
	}
	
	@Test
	public void testFindStringString14() {
	containerDatabase.setContainers(containers);
	
	assertEquals(containerDatabase.find("journeyID", "CO1111").size(), 0);
	}


	@Test
	public void testFindBoolean() {
		container1.setAvailability(true);
		container2.setAvailability(true);
		container3.setAvailability(true);
		container4.setAvailability(false);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		assertEquals(containerDatabase.find(false).size(), 1);
	}
	
	@Test
	public void testFindBoolean2() {
		containerDatabase.setContainers(containers);
		
		assertEquals(containerDatabase.find(false).size(), 0);
	}

	@Test
	public void testRegister() {
		container1.setContainerID(4);
		container2.setContainerID(6);
		container3.setContainerID(9);
		container4.setContainerID(10);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.register("Oslo");
		assertEquals(response.getErrorCode(),010);
		assertEquals(containerDatabase.getContainers().get(4).getPosition(),"Oslo");
		assertEquals(containerDatabase.getContainers().get(4).getContainerID(), 11);
	}
	
	@Test
	public void testRegister2() {
		container1.setContainerID(4);
		container2.setContainerID(6);
		container3.setContainerID(9);
		container4.setContainerID(10);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.register(null);
		assertEquals(response.getErrorCode(),162);
	}
	
	@Test
	public void testRegister3() {
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.register("Oslo");
		assertEquals(containerDatabase.getContainers().get(0).getContainerID(), 1);
	}

	@Test
	public void testUpdatePosition() {
		container1.setCurrentJourney("CO1111");
		container2.setCurrentJourney("CO1111");
		container3.setCurrentJourney("CO1111");
		container4.setCurrentJourney("CO1111");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.updatePosition("CO1111", "Accra");
		assertEquals(response.getErrorCode(), 070);
	}
	
	@Test
	public void testUpdatePosition2() {
		container1.setCurrentJourney("CO1111");
		container2.setCurrentJourney("CO1111");
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.updatePosition("CO1111", "Accra");
		assertEquals(response.getErrorCode(), 070);
		assertNull(container3.getPosition());
	}
	
	@Test
	public void testUpdatePosition3() {
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.updatePosition("CO1111", "Accra");
		assertEquals(response.getErrorCode(), 110);
		assertNull(container3.getPosition());
	}
	
	@Test
	public void testUpdatePosition4() {
		container1.setCurrentJourney("CO1111");
		container2.setCurrentJourney("CO1111");
		container3.setCurrentJourney("MS8475");
		container4.setCurrentJourney("CO1111");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		
		response = containerDatabase.updatePosition("CO1111", "Accra");
		assertEquals(response.getErrorCode(), 070);
		assertNull(container3.getPosition());
	}
	
	@Test
	public void testUpdatePosition5() {
		container1.setCurrentJourney("CO1111");
		container2.setCurrentJourney("CO1111");
		container3.setCurrentJourney("MS8475");
		container4.setCurrentJourney("CO1111");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		
		response = containerDatabase.updatePosition("CH4511", "Accra");
		assertEquals(response.getErrorCode(), 110);
	}

	@Test
	public void testMarkArrived() {
		container1.setCurrentJourney("CO1111");
		container2.setCurrentJourney("CO1111");
		container3.setCurrentJourney("CO1111");
		container4.setCurrentJourney("CO1111");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.markArrived("CO1111");
		assertEquals(response.getErrorCode(), 022);
	}
	
	@Test
	public void testMarkArrived2() {
		container1.setCurrentJourney("CO1111");
		container2.setCurrentJourney("CO1111");
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.markArrived("CO1111");
		assertEquals(response.getErrorCode(), 022);
		assertNull(container3.getPosition());
	}
	
	@Test
	public void testMarkArrived3() {
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.markArrived("CO1111");
		assertEquals(response.getErrorCode(), 110);
	}
	
	@Test
	public void testMarkArrived4() {
		container1.setCurrentJourney("CO1111");
		container2.setCurrentJourney("CO1111");
		container3.setCurrentJourney("MS8475");
		container4.setCurrentJourney("CO1111");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		
		response = containerDatabase.markArrived("CH4511");
		assertEquals(response.getErrorCode(), 110);
	}
	
	@Test
	public void testMarkArrived5() {
		container1.setCurrentJourney("CO1111");
		container2.setCurrentJourney("CO1111");
		container3.setCurrentJourney("MS8475");
		container4.setCurrentJourney("CO1111");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		
		response = containerDatabase.updatePosition("CO1111", "Accra");
		assertEquals(response.getErrorCode(), 070);
		assertNull(container3.getPosition());
	}


	@Test
	public void testRemove() {
		container1.setContainerID(4);
		container2.setContainerID(6);
		container3.setContainerID(9);
		container4.setContainerID(10);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.remove(5);
		assertEquals(response.getErrorCode(), 110);
	}
	
	@Test
	public void testRemove2() {
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.remove(1);
		assertEquals(response.getErrorCode(), 110);
	}
	
	@Test
	public void testRemove3() {
		container1.setContainerID(4);
		container2.setContainerID(6);
		container3.setContainerID(9);
		container4.setContainerID(10);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		response = containerDatabase.remove(3);
		assertEquals(response.getErrorCode(), 074);
	}

	@Test
	public void testGetColumnCount() {
		assertEquals(containerDatabase.getColumnCount(),7);
	}

	@Test
	public void testGetRowCount() {
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		assertEquals(containerDatabase.getRowCount(),4);
	}

	@Test
	public void testGetTableModelListeners() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetValueAt() {
		container1.setContainerID(4);
		container2.setContainerID(6);
		container3.setContainerID(9);
		container4.setContainerID(10);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		Object ID = containerDatabase.getValueAt(0, 0);
		assertEquals(ID, 4);
	}
	
	@Test
	public void testGetValueAt2() {
		container1.setOwner(4);
		container2.setOwner(6);
		container3.setOwner(9);
		container4.setOwner(10);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		Object owner = containerDatabase.getValueAt(0, 1);
		assertEquals(owner, 4);
	}
	
	@Test
	public void testGetValueAt3() {
		container1.setPosition("Accra");
		container2.setPosition("Accra");
		container3.setPosition("Accra");
		container4.setPosition("Manchester");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		Object position = containerDatabase.getValueAt(3, 2);
		assertEquals(position, "Manchester");
	}
	
	@Test
	public void testGetValueAt4() {
		container1.setContentType("Oranges");
		container2.setContentType("Oranges");
		container3.setContentType("Peaches");
		container4.setContentType("Avocado");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		Object position = containerDatabase.getValueAt(2, 3);
		assertEquals(position, "Peaches");
	}
	
	@Test
	public void testGetValueAt5() {
		container1.setCompany("Captain Orange");
		container2.setCompany("Captain Orange");
		container3.setCompany("Captain Peach");
		container4.setCompany("Captain Avocado");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		Object company = containerDatabase.getValueAt(2, 4);
		assertEquals(company, "Captain Peach");
	}
	
	@Test
	public void testGetValueAt6() {
		container1.setAvailability(false);
		container2.setAvailability(false);
		container3.setAvailability(true);
		container4.setAvailability(false);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		Object availability = containerDatabase.getValueAt(2, 5);
		assertEquals(availability, true);
	}
	
	@Test
	public void testGetValueAt7() {
		container1.setCurrentJourney("CO1111");
		container2.setCurrentJourney("CO1111");
		container3.setCurrentJourney("MS8475");
		container4.setCurrentJourney("CO1111");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		Object journey = containerDatabase.getValueAt(2, 6);
		assertEquals(journey, "MS8475");
	}

	@Test
	public void testGetValueAt8() {
		container1.setCurrentJourney("CO1111");
		container2.setCurrentJourney("CO1111");
		container3.setCurrentJourney("MS8475");
		container4.setCurrentJourney("CO1111");
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		containers.add(container4);
		containerDatabase.setContainers(containers);
		
		Object journey = containerDatabase.getValueAt(2, 10);
		assertNull(journey);
	}

	@Test
	public void testGetColumnNameInt() {
		String name = containerDatabase.getColumnName(0);
		assertEquals(name, "Container ID");
	}
	
	@Test
	public void testGetColumnNameInt2() {
		String name = containerDatabase.getColumnName(1);
		assertEquals(name, "Current User");
	}
	
	@Test
	public void testGetColumnNameInt3() {
		String name = containerDatabase.getColumnName(2);
		assertEquals(name, "Position");
	}
	
	@Test
	public void testGetColumnNameInt4() {
		String name = containerDatabase.getColumnName(3);
		assertEquals(name, "Content Type");
	}
	
	@Test
	public void testGetColumnNameInt5() {
		String name = containerDatabase.getColumnName(4);
		assertEquals(name, "Company");
	}
	
	@Test
	public void testGetColumnNameInt6() {
		String name = containerDatabase.getColumnName(5);
		assertEquals(name, "Availability");
	}
	
	@Test
	public void testGetColumnNameInt7() {
		String name = containerDatabase.getColumnName(6);
		assertEquals(name, "Current Journey");
	}
	
	@Test
	public void testGetColumnNameInt8() {
		String name = containerDatabase.getColumnName(25);
		assertNull(name);
	}

}
