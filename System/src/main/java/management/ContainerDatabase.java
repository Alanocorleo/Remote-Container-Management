package management;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import response.ResponseObject;

/**
 * ContainerDatabase class represent all containers that belong to the logistics
 * company. It extends AbstractTableModel in order to change the format of the 
 * container database, which is a array list composed of containers, into a 
 * table to be used for GUI.
 */

public class ContainerDatabase extends AbstractTableModel {
	
	private static final long serialVersionUID = 1003824727758953988L;
	
	final String database = "container_database.json";
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Json annotations are used for instructing how to both serialize and
	 * deserialize.
	 */
	@JsonProperty("containers")
	private ArrayList<Container> containers;
	
	/**
	 * This constructor creates a an array list of containers.
	 * This is necessary for deserializing.
	 */
	public ContainerDatabase() {
		setContainers(new ArrayList<Container>());
	}

	/**
	 * This method returns the containers.
	 * @return containers
	 */
	public ArrayList<Container> getContainers() {
		return containers;
	}

	/**
	 * This method sets the containers.
	 */
	public void setContainers(ArrayList<Container> containerList) {
		this.containers = containerList;
		// Notify the view that the data has been changed
		fireTableDataChanged();
	}
	
	/**
	 * This method produces a container database as a JSON file with name 
	 * "container_database.json" to program's directory by serialising a newly
	 * created container database object. 
	 */
	public void produce() throws Exception {
		mapper = new ObjectMapper();
		ContainerDatabase containers = new ContainerDatabase();
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(containers);
		Files.write(Paths.get(database), jsonResult.getBytes());
	}
	
	/**
	 * This method pulls the container database from program's directory by 
	 * deserializing "container_database.json" file, and assigning the information
	 * to containers. 
	 */
	public void pull() throws Exception {
		mapper = new ObjectMapper();
		this.containers = mapper.readValue(new File(database), ContainerDatabase.class).getContainers();
	}
	
	/**
	 * This method either produces or overwrites an existing container database in
	 * program's directory with name "container_database.json" by serialising 
	 * containers.
	 */
	public void push() throws Exception {
		mapper = new ObjectMapper();
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		Files.write(Paths.get(database), jsonResult.getBytes());
	}
	
	/**
	 * This method takes a number, and position as parameters, and returns available
	 * containers from location equal to the given position. It searches every container
	 * checking both the position and availability. Once found, it is added to another 
	 * array list of containers. In case if there are no more containers left to 
	 * extract relatively to the number given, then stop extracting, and print out 
	 * a warning message listing the number of how many containers got extracted.
	 * @return containers
	 */
	public ArrayList<Container> extract(int number, String position) {
		
		ArrayList<Container> extract = new ArrayList<Container>();
		int count = number;
		
		for(Container container: containers) {
			if (container.getPosition().equals(position) && container.isAvailability() == true && count > 0) {
				extract.add(container);
				count -= 1;
			}
		}
		
		if (extract.size() < number) {
			System.out.println("WARNING: Only " + extract.size() + " containers are available");
		}
	
		return extract;
		
	}
	
	/**
	 * This method takes a client-ID as a parameter, and returns containers that 
	 * currently belong to the client with the given client-ID. It searches every 
	 * container that have an owner with the same given client-ID. Once found, it
	 * is added to another array list of containers. This is repeated for every 
	 * container.
	 * @return containers
	 */
	public ArrayList<Container> extract(int clientID) {
		
		ArrayList<Container> myContainers = new ArrayList<Container>();
		
		for(Container container: containers) {
			if (container.getOwner() == clientID) {
				myContainers.add(container);
			}
		}
	
		return myContainers;
		
	}
	
	/**
	 * This method takes a client-ID, location, content type, company, and quantity
	 * and extracts containers according the given location and quantity, and assigns
	 * them with an owner, content type, and company. It also returns a response about
	 * the execution of the process. In case if the client-ID is not valid, then 
	 * return a response with a code 132 and a message "Client is not valid". In 
	 * case if the extracted list of containers is empty, then return a response 
	 * with a code 110 and a message "Container is not found". In case if the journey
	 * attributes are empty, then return a response with a code 210 and a message 
	 * "Necessary parameters are not entered". Otherwise, return a response with 
	 * a code 010 and a message "Container has been registered" for every container 
	 * that gets assigned.
	 * @return execution response
	 */
	public ResponseObject book(int clientID, String location, String contentType, String company, int quantity) {
		
		ResponseObject response;
		
		if(clientID == 0) {
			response = new ResponseObject(132, "Client is not valid");
		}
		
		else if (!((location == null) || (contentType == null) || (company == null) || (quantity == 0))){
			response = new ResponseObject(110, "Container is not found");
			for (Container container : extract(quantity, location)) {
				container.setOwner(clientID);
				container.setContentType(contentType);
				container.setCompany(company);
				container.setAvailability(false);
				// Codes from 000 to 099 indicate successful operation
				response = new ResponseObject(010, "Container has been registered");
			}
		}
		
		else {
			// Codes from 100 to 999 indicate otherwise
			response = new ResponseObject(210, "Necessary parameters are not entered");
		}
			 
		return response;
		
	}

	/**
	 * This method takes a criteria, and entry as parameters, and returns containers
	 * that satisfy the criteria based on the entry of type int. According to the 
	 * given criteria, it searches for every container that has a corresponding attribute equal to 
	 * the entry. Once found, this container is added to another array list of 
	 * containers. This is repeated for every container.
	 * @return containers
	 */
	public ArrayList<Container> find(String criteria, int entry) {
		
		ArrayList<Container> myContainers = new ArrayList<Container>();
		
		if(criteria!=null) {
			if (criteria.equals("containerID")) {
				for (Container container : this.containers) {
					 if (container.getContainerID() == entry) {
						  myContainers.add(container);
						  break;
		            } 
		        }
			}
			
			if (criteria.equals("owner")) {
				for (Container container : this.containers) {
					 if (container.getOwner() == entry) {
						  myContainers.add(container);
		            } 
		        }
			}
		}
		
		return myContainers;
		
	}
	
	/**
	 * This method takes a criteria, and entry as parameters, and returns containers
	 * that satisfy the criteria based on the entry of type String. According to the
	 * given criteria, it searches for every container that has a corresponding 
	 * attribute equal to the entry. Once found, this container is added to another 
	 * array list of containers. This is repeated for every container.
	 * @return containers
	 */
	public ArrayList<Container> find(String criteria, String entry) {
		
		entry = StringUtils.lowerCase(entry);
		
		ArrayList<Container> myContainers = new ArrayList<Container>();
		
		if(criteria != null) {
			if (criteria.equals("position")) {
				for (Container container : this.containers) {
					if (container.getPosition() != null) {
						if (StringUtils.lowerCase(container.getPosition()).equals(entry)) {
							  myContainers.add(container);
			            } 
					}
		        }
			}
			
			if (criteria.equals("contentType")) {
				for (Container container : this.containers) {
					if (container.getContentType() != null) {
						if (StringUtils.lowerCase(container.getContentType()).equals(entry)) {
							  myContainers.add(container);
			            } 
					}
		        }
			}
			
			if (criteria.equals("company")) {
				for (Container container : this.containers) {
					if (container.getCompany() != null) {
						if (StringUtils.lowerCase(container.getCompany()).equals(entry)) {
							  myContainers.add(container);
			            } 
					}
		        }
			}
			
			if (criteria.equals("journeyID")) {
				for (Container container : this.containers) {
					if (container.getCurrentJourney() != null && !container.getCurrentJourney().equals("")) {
						 if (StringUtils.lowerCase(container.getCurrentJourney().substring(0, 7)).equals(entry)) {
							  myContainers.add(container);
			            } 
					}
		        }
			}
		}
		
		return myContainers;
		
	}
	
	/**
	 * This method takes an entry as parameter, and returns containers that satisfy 
	 * the criteria based on the entry of type boolean. According to the given 
	 * criteria, it searches for every container that has a corresponding attribute 
	 * equal to the entry. Once found, this container is added to another array list
	 * of containers. This is repeated for every container.
	 * @return containers
	 */
	public ArrayList<Container> find(boolean entry) {
		
		ArrayList<Container> myContainers = new ArrayList<Container>();
		
		for (Container container : this.containers) {
			 if (container.isAvailability() == entry) {
				  myContainers.add(container);
            } 
        }
	
		return myContainers;
	}
	
	/**
	 * This method takes a location as a parameter, and adds a new container with 
	 * a unique container-ID to the container registry. It also returns a response
	 * about the execution of the process. In case if the location is null, then 
	 * return a response with a code 162 and a message "Location is not valid".
	 * Otherwise, return a response with a code 010 and a message "Container has 
	 * been registered".
	 * @return execution response
	 */
	public ResponseObject register(String location) {
		
		ResponseObject response = new ResponseObject(162, "Location is not valid");
		
		if (location != null) {
			int ID;
			if (containers.size() == 0) {
				ID = 1;
			} else {
				ID = (containers.get(containers.size()-1).getContainerID() + 1);
			}
			
			Container container = new Container();
			container.setAvailability(true);
			container.setCompany("");
			container.setContainerID(ID);
			container.setContentType("");
			container.setOwner(0);
			container.setPosition(location);
			
			containers.add(container);
			response = new ResponseObject(010, "Container has been registered");
		}
		
		return response;
		
	}
	
	/**
	 * This method takes a journey-ID, and position as parameters, and searches for 
	 * a container with the current journey equal to the given journey-ID. Once 
	 * found, it updates the position of that container to the given position. 
	 * It also returns a response about the execution of the process. In case if 
	 * the container is not found, then return a response with a code 110 and 
	 * a message "Container is not found". Otherwise, return a response with a 
	 * code 070 and a message "Position has been updated" for every container 
	 * that gets updated.
	 * @return execution response
	 */
	public ResponseObject updatePosition(String journeyID, String position) {
		
		ResponseObject response = new ResponseObject(110, "Container is not found");

		 for (Container container : containers) {
			 if (container.getCurrentJourney() != null) {
				 if (container.getCurrentJourney().equals(journeyID)) {
					 container.setPosition(position);
					 response = new ResponseObject(070, "Position has been updated");
				 }  
			 }
		}
		
		return response;
	
	}
	
	/**
	 * This method takes a journey-ID as a parameter, and searches for a container 
	 * with the current journey equal to the given journey-ID. Once found, it sets 
	 * the current journey of that particular container to "ARRIVED". It also 
	 * returns a response about the execution of the process. In case if the container 
	 * is not found, then return a response with a code 110 and a message "Container 
	 * is not found". Otherwise, return a response with a code 022 and a message 
	 * "Journey label has been updated" for every container that gets updated.
	 * @return execution response
	 */
	public ResponseObject markArrived(String journeyID) {
		
		ResponseObject response = new ResponseObject(110, "Container is not found");;

		 for (Container container : containers) {
			 if (container.getCurrentJourney() != null) {
				 if (container.getCurrentJourney().equals(journeyID)) {
					 container.setCurrentJourney(journeyID + ": ARRIVED");
					 response = new ResponseObject(022, "Journey label has been updated");
				 }  
			 }
		}
		
		return response;
	
	}
	
	/**
	 * This method takes a row index as a parameter, and searches for a container 
	 * with a container-ID equal to the extracted from the indexed container. Once 
	 * found, it removes that container from container registry. It also returns a 
	 * response about the execution of the process. In case if the container is 
	 * not found, then return a response with a code 110 and a message "Container 
	 * not found". Otherwise, return a response with a code 074 and a message 
	 * "Container has been successfully removed".
	 * @return execution response
	 */
	public ResponseObject remove(int row) {
		
		int containerID = -1;
		ResponseObject response = new ResponseObject(110, "Container not found");
		if(row < containers.size()) {
			containerID = containers.get(row).getContainerID();
			for (Container container: containers) {
				if (container.getContainerID() == containerID) {
					containers.remove(container);
					response = new ResponseObject(074, "Container has been successfully removed");
					break;
				}
			}
		}
		
		return response;
		
	}
	
	/**
	 * This method returns the number of columns needed to construct a table of 
	 * containers for GUI. It overrides a method from AbstractTableModel.
	 * @return number of columns
	 */
	@JsonIgnore
	@Override
	public int getColumnCount() {
		return 7;
	}
	
	/**
	 * This method returns the number rows needed to construct a table of 
	 * containers for GUI by finding the size of containers array list. 
	 * It overrides a method from AbstractTableModel.
	 * @return number of rows
	 */
	@JsonIgnore
	@Override
	public int getRowCount() {
		return containers.size();
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
	 * the value of a container attribute from containers.
	 * @return journey attribute values
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Container particularContainer = containers.get(rowIndex);
		
		if (columnIndex == 0) {
			return particularContainer.getContainerID();
			
		} else if (columnIndex == 1) {
			return particularContainer.getOwner();
			
		} else if (columnIndex == 2) {
			return particularContainer.getPosition();
			
		} else if (columnIndex == 3) {
			return particularContainer.getContentType();
			
		} else if (columnIndex == 4) {
			return particularContainer.getCompany();
			
		} else if (columnIndex == 5) {
			return particularContainer.isAvailability();
			
		} else if (columnIndex == 6) {
			return particularContainer.getCurrentJourney();
			
		} else {
			return null;
		}
		
	}
	
	/**
	 * This method takes a column index as a parameter, and returns 
	 * the name of a container attribute.
	 * @return container attribute names
	 */
	public String getColumnName(int columnIndex) {
		
		if (columnIndex == 0) {
			return "Container ID";
			
		} else if (columnIndex == 1) {
			return "Current User";
			
		} else if (columnIndex == 2) {
			return "Position";
			
		} else if (columnIndex == 3) {
			return "Content Type";
			
		} else if (columnIndex == 4) {
			return "Company";
			
		} else if (columnIndex == 5) {
			return "Availability";
		
		} else if (columnIndex == 6) {
			return "Current Journey";
			
		} else {
			return null;
		}
		
	}

}
