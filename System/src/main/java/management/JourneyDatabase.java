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
import serialization.JourneyDeserializer;
import serialization.JourneySerializer;

/**
 * JourneyDatabase class represent marine journeys that take in containers.
 * It extends AbstractTableModel in order to change the format of the journey 
 * database, which is a hash map composed of journey keys and container array 
 * lists, into a table to be used for GUI.
 */

public class JourneyDatabase extends AbstractTableModel {
	
	private static final long serialVersionUID = 2345990592978343022L;
	
	final String database = "journey_database.json";
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Json annotations are used for instructing how to both serialize and
	 * deserialize.
	 */
	@JsonProperty("journeys")
	@JsonSerialize(keyUsing = JourneySerializer.class)
	@JsonDeserialize(keyUsing = JourneyDeserializer.class)
	private Map<Journey, ArrayList<Container>> journeys;
	
	/**
	 * This constructor creates a hash map composed of journey keys and container 
	 * array lists. This is necessary for deserializing.
	 */
	@JsonCreator
	public JourneyDatabase() {
		setJourneys(new HashMap<Journey, ArrayList<Container>>());
	}
	
	/**
	 * This method returns the journeys.
	 * @return journeys
	 */
	public Map<Journey, ArrayList<Container>> getJourneys() {
		return journeys;
	}
	
	/**
	 * This method sets the journeys.
	 */
	public void setJourneys(Map<Journey, ArrayList<Container>> journeys) {
		this.journeys = journeys;
		// Notify the view that the data has been changed
		fireTableDataChanged();
	}
	
	/**
	 * This method produces a journey database as a JSON file with name 
	 * "journey_database.json" to program's directory by serialising a newly
	 * created journey database object. 
	 */
	public void produce() throws Exception {
		mapper = new ObjectMapper();
		JourneyDatabase journeys = new JourneyDatabase();
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(journeys);
		Files.write(Paths.get(database), jsonResult.getBytes());
	}
	
	/**
	 * This method pulls the journey database from program's directory by 
	 * deserializing "journey_database.json" file, and assigning the information
	 * to journeys. 
	 */
	public void pull() throws Exception {
		mapper = new ObjectMapper();
		this.journeys = mapper.readValue(new File(database), JourneyDatabase.class).getJourneys();
	}
	
	/**
	 * This method either produces or overwrites an existing journey database in
	 * program's directory with name "journey_database.json" by serialising 
	 * journeys.
	 */
	public void push() throws Exception {
		mapper = new ObjectMapper();
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		Files.write(Paths.get(database), jsonResult.getBytes());
	}
	
	/**
	 * This method takes a journey as a parameter, and puts it together with an 
	 * array list of containers to the current journeys. It also returns a response
	 * about the execution of the process. In case if the journey attributes are 
	 * empty, then return a response with a code 210 and a message "Necessary parameters 
	 * are not entered". Otherwise, return a response with a code 020 and a message 
	 * "Journey has been created".
	 * @return execution response
	 */
	public ResponseObject create(Journey journey) {
		
		ResponseObject response;
		ArrayList<Container> containerList;
		
		if(!((journey.getOrigin() == null) || (journey.getDestination() == null) || (journey.getDepartureDate() == null) || (journey.getArrivalDate() == null))){
			containerList = new ArrayList<Container>();
			getJourneys().put(journey, containerList);
			// Codes from 000 to 100 indicate successful operation
			response = new ResponseObject(020, "Journey has been created");
		}
		
		else {
			// Codes from 100 to 999 indicate otherwise
			response = new ResponseObject(210, "Necessary parameters are not entered");
		}
		
		return response;
		
	}

	/**
	 * This method takes a journey-ID, origin, destination, and array list of 
	 * containers as parameters, and searches for a key with identical journey
	 * information. Once found, it adds those containers with position identical 
	 * to the origin of the journey. It also returns a response about the execution 
	 * of the process. In case if the array list of containers is empty, then
	 * return a response with a code 110 and a message "Container is not found".
	 * In case if the journey is not found, then return a response with a code 
	 * 120 and a message "Journey is not found". Otherwise, return a response with
	 * a code 014 and a message "Container has been assigned to a journey" for every
	 * container that gets assigned.
	 * @return execution response
	 */
	public ResponseObject registerTo(String journeyID, String origin, String destination, ArrayList<Container> containerList) {
		
		ResponseObject response = new ResponseObject(120, "Journey is not found");
		
		if (containerList.size() == 0) {
			response = new ResponseObject(110, "Container is not found"); 
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
	
	/**
	 * This method takes a client-ID as a parameter, and returns journeys with
	 * containers that currently belong to the client with the given client-ID.
	 * It searches every journey for containers that have an owner with the same 
	 * given client-ID. Once found, these containers are then added to another array
	 * list of containers, which are then put to a hash map with the journey as the 
	 * key and the containers as the value. This is repeated for every journey.
	 * @return journeys
	 */
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
	
// Unimplemented filter method, which was previously used, but currently relinquished.
	
//	  // Generic Map filter, with predicate
//  public <K, V> Map<K, V> filter(Map<K, V> map, Predicate<V> predicate) {
//      return map.entrySet()
//                .stream()
//                .filter(x -> predicate.test(x.getValue()))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//  }
	
	/**
	 * This method takes a criteria, and entry as parameters, and returns journeys
	 * with containers, that satisfy the criteria based on the entry. According to 
	 * the given criteria, it searches for every journey that has a corresponding 
	 * attribute equal to the entry. Once found, this journey together with their 
	 * containers are then put to a hash map with the journey as the key and the 
	 * containers as the value. This is repeated for every journey.
	 * @return journeys
	 */
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
	
	/**
	 * This method takes a journey-ID, and position as parameters, and searches for 
	 * a key with identical journey information. Once found, it updates the position 
	 * of all journey containers to the given position. It also returns a response 
	 * about the execution of the process. In case if the journey is not found, 
	 * then return a response with a code 120 and a message "Journey is not found".
	 * In case if there were no containers for that journey, then return a response
	 * with a code 110 and a message "Container is not found". Otherwise, return a 
	 * response with a code 070 and a message "Position has been updated" for every
	 * container that gets updated.
	 * @return execution response
	 */
	public ResponseObject updatePosition(String journeyID, String position) {
		
		ResponseObject response = new ResponseObject(120, "Journey is not found");
		
		for (Journey key : journeys.keySet()) {
			 if (key.getJourneyID().equals(journeyID)) {
				 response = new ResponseObject(110, "Container is not found");
				 for (Container container : journeys.get(key)) {
					 container.setPosition(position);
					 response = new ResponseObject(070, "Position has been updated");
				 }  
			 }
		}
		
		return response;
	
	}
	
	/**
	 * This method takes a journey-ID and date as parameters, and searches for a key 
	 * with identical journey information. Once found, it updates the departure date
	 * of that particular journey to the given date. It also returns a response 
	 * about the execution of the process. In case if the journey is not found, 
	 * then return a response with a code 120 and a message "Journey is not found".
	 * Otherwise, return a response with a code 071 and a message "Departure date 
	 * has been set".
	 * @return execution response
	 */
	public ResponseObject setDeparture(String journeyID, String date) {
		
		ResponseObject response = new ResponseObject(120, "Journey is not found");
		
		for (Journey key : journeys.keySet()) {
			 if (key.getJourneyID().equals(journeyID)) {
				 key.setDepartureDate(date);
				 response = new ResponseObject(071, "Departure date has been set");
				 break;
			 }
		}
		
		return response;
	
	}

	/**
	 * This method takes a journey-ID as a parameter, and searches for a 
	 * key with identical journey information. Once found, it updates the arrival
	 * date of that particular journey to today's date, and sets the current journey
	 * of journey containers to "ARRIVED". It also returns a response about the 
	 * execution of the process. In case if the journey is not found, then return 
	 * a response with a code 120 and a message "Journey is not found". In case if 
	 * there were no containers for that journey, then return a response with a code
	 * 110 and a message "Container is not found". Otherwise, return a response with
	 * a code 072 and a message "Arrival date has been set" for every container that
	 * gets updated.
	 * @return execution response
	 */
	public ResponseObject markArrived(String journeyID) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		LocalDate localDate = LocalDate.now();
		String date = dtf.format(localDate).toString();
		
		ResponseObject response = new ResponseObject(120, "Journey is not found");
		
		for(Journey key : journeys.keySet()) {
			 if (key.getJourneyID().equals(journeyID)) {
				 key.setArrivalDate(date);
				 response = new ResponseObject(110, "Container is not found");
				 for (Container container : journeys.get(key)) {
					 container.setCurrentJourney("ARRIVED");
					 response = new ResponseObject(072, "Arrival date has been set");
				 } 
			 }
		}
		
		return response;
	
	}
	
	/**
	 * This method takes a journey-ID, and container-ID as parameters, and searches 
	 * for a key with identical journey information. Once found, it searches for a
	 * container with the given container-ID, and removes it from that journey. It 
	 * also returns a response about the execution of the process. In case if the 
	 * journey is not found, then return a response with a code 120 and a message 
	 * "Journey is not found". In case if the container is not found, then return 
	 * a response with a code 110 and a message "Container is not found". Otherwise, 
	 * return a response with a code 011 and a message "Container has been successfully 
	 * removed".
	 * @return execution response
	 */
	public ResponseObject removeContainer(String journeyID, int containerID) {
		
		ResponseObject response = new ResponseObject(120, "Journey is not found");
		
		outerloop:
		for (Journey journey : journeys.keySet()) {
			if (journey.getJourneyID().equals(journeyID)) {
				response = new ResponseObject(110, "Container is not found");
				for (Container container : journeys.get(journey)) {
					if (container.getContainerID() == containerID) {
				    	journeys.get(journey).remove(container);
				        response = new ResponseObject(011, "Container has been successfully removed");
				        break outerloop;
					}
				}
			}
		}
		
		return response;
		
	}
	
	/**
	 * This method takes a journey-ID as a parameter, and searches for a key 
	 * with identical journey information. Once found, it removes that particular
	 * journey from journeys. It also returns a response about the execution of 
	 * the process. In case if the journey is not found, then return a response 
	 * with a code 120 and a message "Journey is not found". Otherwise, return a 
	 * response with a code 021 and a message "Journey has been completed and 
	 * successfully removed".
	 * @return execution response
	 */
	public ResponseObject complete(String journeyID) {
			
			ResponseObject response = new ResponseObject(120, "Journey is not found");
			Iterator<Journey> iterator = journeys.keySet().iterator();
		    
		    while(iterator.hasNext()) {
		    	Journey journey = iterator.next();
			    if (journey.getJourneyID().equals(journeyID)) {
			      iterator.remove();
			      response = new ResponseObject(021, "Journey has been completed and successfully removed");
			      break;
			    }
		    }
			
			return response;
		
	}
	
	/**
	 * This method returns the number of columns needed to construct a table of 
	 * journeys for GUI. It overrides a method from AbstractTableModel.
	 * @return number of columns
	 */
	@JsonIgnore
	@Override
	public int getColumnCount() {
		return 5;
	}
	
	/**
	 * This method returns the number rows needed to construct a table of 
	 * journeys for GUI by finding the size of journeys map. It overrides 
	 * a method from AbstractTableModel.
	 * @return number of rows
	 */
	@JsonIgnore
	@Override
	public int getRowCount() {
		return journeys.size();
	}
	
	/**
	 * This method returns a listener list. This list is not required for anything,
	 * yet the method overrides a method from AbstractTableModel, which has to be
	 * ignored by JSON serialization process. Otherwise, the process will fail.
	 * @return listener list
	 */
	@JsonIgnore
	@Override
	public TableModelListener[] getTableModelListeners() {
        return listenerList.getListeners(TableModelListener.class);
    }

	/**
	 * This method takes a row index, and column index as parameters, and returns 
	 * the value of a journey attribute from journeys.
	 * @return journey attribute values
	 */
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
	
	/**
	 * This method takes a column index as a parameter, and returns 
	 * the name of a journey attribute.
	 * @return journey attribute names
	 */
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