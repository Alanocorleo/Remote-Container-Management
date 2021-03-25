package journeysManagement;

import java.util.LinkedList;

public class Record {
	
		LinkedList<Container> record = new LinkedList<Container>();
		
	public LinkedList<Container> getRecord() {
			return record;
		}

	public void put(Container container) {
		record.push(container);
		}

}
