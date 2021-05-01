
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import management.Container;
import management.HistoryBook;

public class ContainerTest {
	
	private Container container;
	
	@Before
	public void setUp() {
		container = new Container();
	}

	@Test
	public void testGetContainerID() {
		assertEquals(0, container.getContainerID());
		container.setContainerID(25);
		assertEquals(25, container.getContainerID());
	}

	@Test
	public void testSetContainerID() {
		container.setContainerID(30);
		assertEquals(30, container.getContainerID());
	}

	@Test
	public void testGetOwner() {
		assertEquals(0, container.getOwner());
		container.setOwner(1);
		assertEquals(1, container.getOwner());
	}

	@Test
	public void testSetOwner() {
		container.setOwner(4);
		assertEquals(4, container.getOwner());
	}

	@Test
	public void testGetPosition() {
		assertNull(container.getPosition());
		container.setPosition("Lagos");
		assertEquals("Lagos", container.getPosition());
	}

	@Test
	public void testSetPosition() {
		container.setPosition("Cyprus");
		assertEquals("Cyprus", container.getPosition());
	}

	@Test
	public void testGetContentType() {
		assertNull(container.getContentType());
		container.setContentType("Paper");
		assertEquals("Paper", container.getContentType());
	}

	@Test
	public void testSetContentType() {
		container.setContentType("Bamboo");
		assertEquals("Bamboo", container.getContentType());
	}

	@Test
	public void testGetCompany() {
		assertNull(container.getCompany());
		container.setCompany("Captain Metal");
		assertEquals("Captain Metal", container.getCompany());
	}

	@Test
	public void testSetCompany() {
		container.setCompany("Captain Plastic");
		assertEquals("Captain Plastic", container.getCompany());
	}

	@Test
	public void testIsAvailability() {
		assertFalse(container.isAvailability());
		container.setAvailability(true);
		assertTrue(container.isAvailability());
	}

	@Test
	public void testSetAvailability() {
		container.setAvailability(true);
		assertTrue(container.isAvailability());
	}

	@Test
	public void testGetCurrentJourney() {
		assertNull(container.getCurrentJourney());
		container.setCurrentJourney("C056842");
		assertEquals("C056842", container.getCurrentJourney());
	}

	@Test
	public void testSetCurrentJourney() {
		container.setCurrentJourney("OM83743");
		assertEquals("OM83743", container.getCurrentJourney());
	}

	@Test
	public void testGetPressure() {
		assertEquals(0, container.getPressure());
		container.setPressure(90);
		assertEquals(90, container.getPressure());
	}

	@Test
	public void testSetPressure() {
		container.setPressure(110);
		assertEquals(110, container.getPressure());
	}

	@Test
	public void testGetHumidity() {
		assertEquals(0, container.getHumidity());
		container.setHumidity(50);
		assertEquals(50, container.getHumidity());
	}

	@Test
	public void testSetHumidity() {
		container.setHumidity(34);
		assertEquals(34, container.getHumidity());
	}

	@Test
	public void testGetTemperature() {
		assertEquals(0, container.getTemperature());
		container.setTemperature(298);
		assertEquals(298, container.getTemperature());
	}

	@Test
	public void testSetTemperature() {
		container.setTemperature(300);
		assertEquals(300, container.getTemperature());
	}

	@Test
	public void testGetHistory() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHistory() {
		fail("Not yet implemented");
	}

	@Test
	public void testAppendHistory() {
		fail("Not yet implemented");
	}

}
