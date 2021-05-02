

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import management.Container;

public class HistoryBookTest {

	private Container container;
	@Before
	public void setUp() {
		container = new Container();
		
		container.setDate("12\\12\\1212");
		container.setHumidity(1);
		container.setTemperature(1);
		container.setPressure(1);
		container.setPosition("Oslo");
		container.appendHistory();
	}

	@Test
	public void testShow() {
//		container.setDate("12\\12\\1212");
//		container.setHumidity(1);
//		container.setTemperature(1);
//		container.setPressure(1);
//		container.setPosition("Oslo");
//		container.appendHistory();
		
		assertEquals("[1]", container.getHistory().show()[1][0].toString());
	}
	@Test
	public void testShow1() {
//		container.setDate("12\\12\\1212");
//		container.setHumidity(1);
//		container.setTemperature(1);
//		container.setPressure(1);
//		container.setPosition("Oslo");
//		container.appendHistory();
		
		assertEquals("[12\\12\\1212]", container.getHistory().show()[0][0].toString());
	}
	@Test
	public void testShow2() {
//		container.setDate("12\\12\\1212");
//		container.setHumidity(1);
//		container.setTemperature(1);
//		container.setPressure(1);
//		container.setPosition("Oslo");
//		container.appendHistory();
		
		//System.out.println(container.getHistory().show()[0][0].toString());
		
		assertEquals("[1]", container.getHistory().show()[2][0].toString());
		
	}
	
	@Test
	public void testShow3() {
//		container.setDate("12\\12\\1212");
//		container.setHumidity(1);
//		container.setTemperature(1);
//		container.setPressure(1);
//		container.setPosition("Oslo");
//		container.appendHistory();
		
		//System.out.println(container.getHistory().show()[0][0].toString());
		
		assertEquals("[1]", container.getHistory().show()[3][0].toString());
		
	}
	
	@Test
	public void testShow4() {
//		container.setDate("12\\12\\1212");
//		container.setHumidity(1);
//		container.setTemperature(1);
//		container.setPressure(1);
//		container.setPosition("Oslo");
//		container.appendHistory();
		
		//System.out.println(container.getHistory().show()[0][0].toString());
		
		assertEquals("[Oslo]", container.getHistory().show()[4][0].toString());
		
//		System.out.println(container.getHistory().showTable()[0][1]);
		
	}

	@Test
	public void testShowTable() {
		assertEquals("Oslo", container.getHistory().showTable()[0][4].toString());
	}
	
	@Test
	public void testShowTable2() {
		assertEquals(1, container.getHistory().showTable()[0][3]);
	}
	
	@Test
	public void testShowTable3() {
		assertEquals(1, container.getHistory().showTable()[0][2]);
	}
	
	@Test
	public void testShowTable4() {
		assertEquals(1, container.getHistory().showTable()[0][1]);
	}
	
	@Test
	public void testShowTable5() {
		assertEquals("12\\12\\1212", container.getHistory().showTable()[0][0].toString());
	}

	@Test
	public void testAdd() {
		container.setPosition("Copenhagen");
		container.appendHistory();
		assertEquals("Oslo", container.getHistory().showTable()[0][4].toString());
		assertEquals("Copenhagen", container.getHistory().showTable()[1][4].toString());
		
	}

}
