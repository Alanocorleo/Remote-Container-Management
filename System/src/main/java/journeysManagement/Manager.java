package journeysManagement;

public class Manager {
	
	private String manager;
	private Record record;

	public Manager(String company, Record record) {
		this.manager = company;
		this.record = record;
	}


	public ResponseObject updatePosition(String journeyID, String position) {
		
			ResponseObject response;
			Record companyJourneys = new Record();
			
			companyJourneys.setRecord(this.record.filter(this.record.getRecord(), container -> container.getCompany().contains(this.manager)));
			if (companyJourneys.getRecord().containsKey(journeyID)) {
				
				this.record.getRecord().get(journeyID).setPosition(position);
				response = new ResponseObject(070, "Position has been updated");
				
			} else {
				
				response = new ResponseObject(700, "Journey was not found");
				
			};
			
		return response;
		
	}
	
	public ResponseObject completeJourney(String journeyID) {
		
		ResponseObject response;
		Record companyJourneys = new Record();
		
		companyJourneys.setRecord(this.record.filter(this.record.getRecord(), container -> container.getCompany().contains(this.manager)));
		if (companyJourneys.getRecord().containsKey(journeyID)) {
			
			this.record.getRecord().remove(journeyID);
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
