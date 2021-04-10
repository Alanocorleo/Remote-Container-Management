package journeysManagement;

public class Company {
	
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ResponseObject updatePosition(String journeyID, String position, JourneyDatabase journeys) {
		
		ResponseObject response = new ResponseObject(140, "Journey not found");;
		
		for(Journey key : journeys.getJourneys().keySet()) {
			
			 if (key.getJourneyID().equals(journeyID)) {
				 
				 response = new ResponseObject(111, "Container not found");;
				 
				 for (Container container : journeys.getJourneys().get(key)) {
					 container.setPosition(position);
					 response = new ResponseObject(070, "Position has been updated");
				 } 
				 
			 }
	
		}
		
	return response;
	
}

//List<Employee> list = ...
//		for (Employee e : list)
//		   if (e.getID() == id)
//		      return true;
//		return false;

}
