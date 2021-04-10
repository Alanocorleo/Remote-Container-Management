package journeysManagement;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import clientsManagement.Client;

public class JourneyDatabase {
	
	private Map<Journey, ArrayList<Container>> journeys;
	
	public JourneyDatabase() {
		this.journeys = new HashMap<Journey, ArrayList<Container>>();
	}
	
	public Map<Journey, ArrayList<Container>> getJourneys() {
		return journeys;
	}
	
	public void setJourneys(Map<Journey, ArrayList<Container>> journeys) {
		this.journeys = journeys;
	}
	
    // Generic Map filter, with predicate
    public <K, V> Map<K, V> filter(Map<K, V> map, Predicate<V> predicate) {
        return map.entrySet()
                  .stream()
                  .filter(x -> predicate.test(x.getValue()))
                  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    
	public Map<Container, Journey> find(String criteria, String entry, int clientID) {
		
		Map<Container, Journey> myContainers = new HashMap<Container, Journey>();
		
		for (Journey key : journeys.keySet()) {
			 for (Container container : journeys.get(key)) {
				 if (container.getOwner() == clientID) {
					 myContainers.put(container, key); 
	             }
             } 
        }
				 
		if (criteria.equals("origin")) {
			myContainers = filter(myContainers, journey -> journey.getOrigin().equals(entry));
		} 
		
		else if (criteria.equals("destination")) {
			myContainers = filter(myContainers, journey -> journey.getDestination().equals(entry));
		}
		
		else if (criteria.equals("content-type")) {
			for(Container key : myContainers.keySet()) {
				if (!(key.getContentType().equals(entry))) {
					myContainers.remove(key);
				}
			}
		}
		
		else if (criteria.equals("company")) {
			for(Container key : myContainers.keySet()) {
				if (!(key.getCompany().equals(entry))) {
					myContainers.remove(key);
				}
			}
		}
		
		return myContainers; 
		
	}
//	
//	public ResponseObject complete(String journeyID) {
//		
//		ResponseObject response;
//		JourneyDatabase companyJourneys = new JourneyDatabase();
//		
//		companyJourneys.setRecord(this.record.filter(this.record.getRecord(), container -> container.getCompany().contains(this.manager)));
//		if (companyJourneys.getRecord().containsKey(journeyID)) {
//			
//			this.record.getRecord().remove(journeyID);
//			response = new ResponseObject(050, "Journey has been completed and succesfully removed from the record");
//			
//		} else {
//			
//			response = new ResponseObject(700, "Journey was not found");
//			
//		};
//		
//	return response;
//	
//}
	
	public ResponseObject create(Journey journey) {
		
		ResponseObject response;
		ArrayList<Container> containerList;
		
		if(!((journey.getOrigin() == null) || (journey.getOrigin() == null))){
			
			containerList = new ArrayList<Container>();
			getJourneys().put(journey, containerList);
			// Codes from 000 to 100 indicate successful operation
			response = new ResponseObject(012, "Journey has been created");
			
		}
		
		else {
			
			// Codes from 100 to 999 indicate otherwise
			response = new ResponseObject(120, "Necessary parameters not entered");
			
		}
		
		return response;
		
	}

	public ResponseObject registerTo(String journeyID, String origin, String destination, ArrayList<Container> containerList) {
		
		ResponseObject response = new ResponseObject(140, "Journey not found");
		
		if (containerList.size() == 0) {
			
			response = new ResponseObject(111, "Container not found"); 
			
		} else {
			
			for (Journey key : journeys.keySet()) {
			       if (key.getJourneyID().equals(journeyID) && key.getOrigin().equals(origin) && key.getDestination().equals(destination)) {
			    	   for (Container container : containerList) {
				    	   journeys.get(key).add(container);
				           response = new ResponseObject(014, "Container has been assigned to a journey");
			    	   }
			       }	
			   }

		   }
		
		return response;
		
	}
	
}