package journeysManagement;

import java.util.LinkedList;

public class Record {
	
		LinkedList<Container> record = new LinkedList<Container>();
		
		
	public void put(Container container) {
		
		record.push(container);
		
		
	}

}
