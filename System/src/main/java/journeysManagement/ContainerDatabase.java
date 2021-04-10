package journeysManagement;

import java.util.ArrayList;

public class ContainerDatabase {
	
	private ArrayList<Container> containers;
	
	public ContainerDatabase() {
		this.setContainers(new ArrayList<Container>());
	}

	public ArrayList<Container> getContainers() {
		return containers;
	}

	public void setContainers(ArrayList<Container> containerList) {
		this.containers = containerList;
	}
	
	public ArrayList<Container> extract(int number, String position) {
		
		ArrayList<Container> extract = new ArrayList<Container>();
		
		int count = number;
		for(Container container: containers) {
			
			if (container.getPosition().equals(position) && container.isAvailability() == true && count > 0) {
				container.setAvailability(false);
				extract.add(container);
				count -= 1;
			}
			
			
		}
		
		if (extract.size() < number) {
			System.out.println("WARNING: " + extract.size() + " containers are available");
		}
	
		return extract;
		
	}

}
