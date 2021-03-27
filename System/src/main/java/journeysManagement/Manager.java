package journeysManagement;

import java.util.Map;

public class Manager {
	
	String manager;
	private ResponseObject response;


	public Manager(String company) {
		this.manager = company;
	}


	public ResponseObject update(String journeyID, String position, Record record) {
			Map<String, Container> filteredRecord = record.filter(record.get(), x -> x.getCompany().contains(this.manager));
			if (filteredRecord.containsKey(journeyID)) {
				
				record.get().get(journeyID).setPosition(position);
				response = new ResponseObject(070, "Position has been updated");
				
			} else {
				
				response = new ResponseObject(700, "The journey was not found");
				
			};
			
		return response;
	}
	
	
//	List<Employee> list = ...
//			for (Employee e : list)
//			   if (e.getID() == id)
//			      return true;
//			return false;

}
