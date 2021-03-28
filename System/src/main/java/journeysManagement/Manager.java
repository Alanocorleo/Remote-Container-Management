package journeysManagement;

public class Manager {
	
	String manager;
	private ResponseObject response;


	public Manager(String company) {
		this.manager = company;
	}


	public ResponseObject updatePosition(String journeyID, String position, Record record) {
		
			Record companyJourneys = new Record();
			companyJourneys.setRecord(record.filter(record.getRecord(), container -> container.getCompany().contains(this.manager)));
			if (companyJourneys.getRecord().containsKey(journeyID)) {
				
				record.getRecord().get(journeyID).setPosition(position);
				response = new ResponseObject(070, "Position has been updated");
				
			} else {
				
				response = new ResponseObject(700, "Journey was not found");
				
			};
			
		return response;
		
	}
	
	public ResponseObject completeJourney(String journeyID, Record record) {
		
		Record companyJourneys = new Record();
		companyJourneys.setRecord(record.filter(record.getRecord(), container -> container.getCompany().contains(this.manager)));
		if (companyJourneys.getRecord().containsKey(journeyID)) {
			
			record.getRecord().remove(journeyID);
			response = new ResponseObject(050, "Journey has been completed and succesfully removed from the record");
			
		} else {
			
			response = new ResponseObject(700, "Journey was not found");
			
		};
		
	return response;
	
}
	
	
//	List<Employee> list = ...
//			for (Employee e : list)
//			   if (e.getID() == id)
//			      return true;
//			return false;

}
