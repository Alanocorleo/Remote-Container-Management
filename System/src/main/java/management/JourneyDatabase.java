package management;

import java.util.Map;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import response.ResponseObject;
import serialization.JourneySerializer;
import serialization.JourneyDeserializer;

// Journey Database is observable
public class JourneyDatabase extends AbstractTableModel {
	
	private static final long serialVersionUID = 2345990592978343022L;
	
	final String database = "journey_database.json";
	ObjectMapper mapper = new ObjectMapper();
	
	@JsonProperty("journeys")
	@JsonSerialize(keyUsing = JourneySerializer.class)
	@JsonDeserialize(keyUsing = JourneyDeserializer.class)
	private Map<Journey, ArrayList<Container>> journeys;
	
	@JsonCreator
	public JourneyDatabase() {
		setJourneys(new HashMap<Journey, ArrayList<Container>>());
	}
	
	public Map<Journey, ArrayList<Container>> getJourneys() {
		return journeys;
	}
	
	public void setJourneys(Map<Journey, ArrayList<Container>> journeys) {
		this.journeys = journeys;
		fireTableDataChanged();
	}
	
	public void produce() throws Exception {
		mapper = new ObjectMapper();
		JourneyDatabase journeys = new JourneyDatabase();
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(journeys);
		Files.write(Paths.get(database), jsonResult.getBytes());
	}
	

	public void pull() throws Exception {
		mapper = new ObjectMapper();
		this.journeys = mapper.readValue(new File(database), JourneyDatabase.class).getJourneys();
	}
	
	public void push() throws Exception {
		mapper = new ObjectMapper();
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		Files.write(Paths.get(database), jsonResult.getBytes());
	}
	
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
		} 
		
		else {
			for (Journey key : journeys.keySet()) {
			       if (key.getJourneyID().equals(journeyID) && key.getOrigin().equals(origin) && key.getDestination().equals(destination)) {
			    	   for (Container container : containerList) {
			    		   if (container.getPosition().equals(key.getOrigin())) {
			    			   container.setCurrentJourney(journeyID);
					    	   journeys.get(key).add(container);
					           response = new ResponseObject(014, "Container has been assigned to a journey");   
			    		   }
			    	   }
			       }	
			   }
		   }
		
		return response;
		
	}
	
	public ResponseObject updatePosition(String journeyID, String position) {
		
		ResponseObject response = new ResponseObject(140, "Journey not found");
		
		for(Journey key : journeys.keySet()) {
			 if (key.getJourneyID().equals(journeyID)) {
				 response = new ResponseObject(111, "Container not found");
				 for (Container container : journeys.get(key)) {
					 container.setPosition(position);
					 response = new ResponseObject(070, "Position has been updated");
				 }  
			 }
		}
		
		return response;
	
	}
	
	public ResponseObject setDeparture(String journeyID, String date) {
		
		ResponseObject response = new ResponseObject(140, "Journey not found");
		
		for(Journey key : journeys.keySet()) {
			 if (key.getJourneyID().equals(journeyID)) {
				 key.setDepartureDate(date);
			 }
		}
		response = new ResponseObject(076, "Departure date has been set");
		
		return response;
	
	}

	public ResponseObject markArrived(String journeyID) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		LocalDate localDate = LocalDate.now();
		String date = dtf.format(localDate).toString();
		
		ResponseObject response = new ResponseObject(140, "Journey not found");
		
		for(Journey key : journeys.keySet()) {
			 if (key.getJourneyID().equals(journeyID)) {
				 key.setArrivalDate(date);
				 response = new ResponseObject(111, "Container not found");
				 for (Container container : journeys.get(key)) {
					 container.setCurrentJourney("Just completed journey: " + journeyID);
					 response = new ResponseObject(077, "Arrival date has been set");
				 } 
			 }
		}
		
		return response;
	
	}
	
	public ResponseObject complete(String journeyID) {
			
			ResponseObject response = new ResponseObject(700, "Journey not found");
			Iterator<Journey> iterator = journeys.keySet().iterator();
		    
		    while(iterator.hasNext()) {
		    	Journey journey = iterator.next();
			    if(journey.getJourneyID().equals(journeyID)) {
			      iterator.remove();
			      response = new ResponseObject(070, "Journey has been completed and succesfully removed");
			    }
		    }
			
			return response;
		
	}
	
	public ResponseObject removeContainer(Journey journey, int containerID) {
		
		ResponseObject response = new ResponseObject(140, "Container not found");
		
		Iterator<Container> iterator = journeys.get(journey).iterator();
	    
	    while(iterator.hasNext()){
	      Container container = iterator.next();
	      if(container.getContainerID() == containerID){
	        iterator.remove();
	        response = new ResponseObject(072, "Container has been succesfully removed");
	      }
	    }
		
		return response;
	
    }
	
	  // Generic Map filter, with predicate
    public <K, V> Map<K, V> filter(Map<K, V> map, Predicate<V> predicate) {
        return map.entrySet()
                  .stream()
                  .filter(x -> predicate.test(x.getValue()))
                  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
	
	public Map<Journey, ArrayList<Container>> extract(int clientID) {
		
		Map<Journey, ArrayList<Container>> myJourneys = new HashMap<Journey,  ArrayList<Container>>();
		
		for (Journey key : journeys.keySet()) {
			ArrayList<Container> myContainers = new ArrayList<Container>();
			for (Container container : journeys.get(key)) {
				 if (container.getOwner() == clientID) {
					 myContainers.add(container);
					 myJourneys.put(key, myContainers);
	            }
	        }
		}
		
		return myJourneys;
		
	}
	
	public Map<Journey,  ArrayList<Container>> find(String criteria, String entry) {
		
		entry = StringUtils.lowerCase(entry);
		
		Map<Journey, ArrayList<Container>> myJourneys = new HashMap<Journey,  ArrayList<Container>>();
				 
		if (criteria.equals("journeyID")) {
			for (Journey key: this.journeys.keySet()) {
				if (StringUtils.lowerCase(key.getJourneyID()).equals(entry)) {
					myJourneys.put(key, this.journeys.get(key));
				}
			}
		} 
		
		else if (criteria.equals("origin")) {
			for (Journey key: this.journeys.keySet()) {
				if (StringUtils.lowerCase(key.getOrigin()).equals(entry)) {
					myJourneys.put(key, this.journeys.get(key));
				}
			}
		} 
		
		else if (criteria.equals("destination")) {
			for (Journey key: this.journeys.keySet()) {
				if (StringUtils.lowerCase(key.getDestination()).equals(entry)) {
					myJourneys.put(key, this.journeys.get(key));
				}
			}
		}
		
		else if (criteria.equals("departureDate")) {
			for (Journey key: this.journeys.keySet()) {
				if (StringUtils.lowerCase(key.getDepartureDate()).equals(entry)) {
					myJourneys.put(key, this.journeys.get(key));
				}
			}
		}
		
		else if (criteria.equals("arrivalDate")) {
			for (Journey key: this.journeys.keySet()) {
				if (StringUtils.lowerCase(key.getArrivalDate()).equals(entry)) {
					myJourneys.put(key, this.journeys.get(key));
				}
			}
		}
		
		return myJourneys; 
		
	}
	
	@JsonIgnore
	@Override
	public int getColumnCount() {
		return 5;
	}
	
	@JsonIgnore
	@Override
	public int getRowCount() {
		return journeys.size();
	}
	
	@JsonIgnore
	@Override
	public TableModelListener[] getTableModelListeners() {
        return listenerList.getListeners(TableModelListener.class);
    }

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		
		ArrayList<Journey> journeyKeyList = new ArrayList<Journey>(journeys.keySet());
		
		Journey perticularJourney = journeyKeyList.get(rowIndex);
		
		if (columnIndex == 0) {
			return perticularJourney.getJourneyID();
			
		} else if (columnIndex == 1) {
			return perticularJourney.getOrigin();
			
		} else if (columnIndex == 2) {
			return perticularJourney.getDestination();
			
		} else if (columnIndex == 3) {
			return perticularJourney.getDepartureDate();
			
		} else if (columnIndex == 4) {
			return perticularJourney.getArrivalDate();
			
		} else {
			return null;
		}
		
	}
	
	public String getColumnName(int columnIndex) {
		
		if (columnIndex == 0) {
			return "Journey ID";
			
		} else if (columnIndex == 1) {
			return "Origin";
			
		} else if (columnIndex == 2) {
			return "Destination";
			
		} else if (columnIndex == 3) {
			return "Departure";
			
		} else if (columnIndex == 4) {
			return "Arrival";
			
		} else {
			return null;
		}
		
	}
	
}