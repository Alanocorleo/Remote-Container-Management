package management;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import response.ResponseObject;

public class ContainerDatabase extends AbstractTableModel {
	
	private static final long serialVersionUID = 1003824727758953988L;
	
	final String database = "container_database.json";
	ObjectMapper mapper = new ObjectMapper();
	
	@JsonProperty("containers")
	private ArrayList<Container> containers;
	
	public ContainerDatabase() {
		setContainers(new ArrayList<Container>());
	}

	public ArrayList<Container> getContainers() {
		return containers;
	}

	public void setContainers(ArrayList<Container> containerList) {
		this.containers = containerList;
		fireTableDataChanged(); // notify the views that data changed
	}
	
	public void produce() throws Exception {
		mapper = new ObjectMapper();
		ContainerDatabase containers = new ContainerDatabase();
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(containers);
		Files.write(Paths.get(database), jsonResult.getBytes());
	}
	

	public void pull() throws Exception {
		mapper = new ObjectMapper();
		this.containers = mapper.readValue(new File(database), ContainerDatabase.class).getContainers();
	}
	
	public void push() throws Exception {
		mapper = new ObjectMapper();
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		Files.write(Paths.get(database), jsonResult.getBytes());
	}
	
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
	
	public ArrayList<Container> extract(int clientID) {
		
		ArrayList<Container> myContainers = new ArrayList<Container>();
		
		for(Container container: containers) {
			if (container.getOwner() == clientID) {
				myContainers.add(container);
			}
		}
	
		return myContainers;
		
	}
	
	public ResponseObject book(int id, String location, String contentType, String company, int quantity) {
		
		ResponseObject response;
		
		if (!((location == null) || (contentType == null) || (company == null) || (quantity == 0))){
			response = new ResponseObject(111, "Container not found");
			for (Container container : extract(quantity, location)) {
				container.setOwner(id);
				container.setContentType(contentType);
				container.setCompany(company);
				container.setAvailability(false);
				// Codes from 000 to 099 indicate successful operation
				response = new ResponseObject(010, "Container has been registered");
			}
		}
		
		else {
			// Codes from 100 to 999 indicate otherwise
			response = new ResponseObject(110, "Necessary parameters not entered");
		}
			 
		return response;
		
	}

	public ArrayList<Container> find(String criteria, int entry) {
		
		ArrayList<Container> myContainers = new ArrayList<Container>();
		
		if (criteria == "id") {
			for (Container container : this.containers) {
				 if (container.getContainerID() == entry) {
					  myContainers.add(container);
					  break;
	            } 
	        }
		}
		
		if (criteria == "owner") {
			for (Container container : this.containers) {
				 if (container.getOwner() == entry) {
					  myContainers.add(container);
	            } 
	        }
		}
		
		return myContainers;
		
	}
	
	public ArrayList<Container> find(String criteria, String entry) {
		
		entry = StringUtils.lowerCase(entry);
		
		ArrayList<Container> myContainers = new ArrayList<Container>();
		
		if (criteria == "position") {
			for (Container container : this.containers) {
				if (container.getPosition() != null) {
					if (StringUtils.lowerCase(container.getPosition()).equals(entry)) {
						  myContainers.add(container);
		            } 
				}
	        }
		}
		
		if (criteria == "contentType") {
			for (Container container : this.containers) {
				if (container.getContentType() != null) {
					if (StringUtils.lowerCase(container.getContentType()).equals(entry)) {
						  myContainers.add(container);
		            } 
				}
	        }
		}
		
		if (criteria == "company") {
			for (Container container : this.containers) {
				if (container.getCompany() != null) {
					if (StringUtils.lowerCase(container.getCompany()).equals(entry)) {
						  myContainers.add(container);
		            } 
				}
	        }
		}
		
		if (criteria == "journey") {
			for (Container container : this.containers) {
				if (container.getCurrentJourney() != null) {
					 if (StringUtils.lowerCase(container.getCurrentJourney()).equals(entry)) {
						  myContainers.add(container);
		            } 
				}
	        }
		}
		
		return myContainers;
		
	}
	
	public ArrayList<Container> find(boolean entry) {
		
		ArrayList<Container> myContainers = new ArrayList<Container>();
		
		for (Container container : this.containers) {
			 if (container.isAvailability() == entry) {
				  myContainers.add(container);
            } 
        }
	
		return myContainers;
	}
	
	public int register(String Location) {
		
		int ID = (containers.get(containers.size()-1).getContainerID() + 1);
		
		Container container = new Container();
		container.setAvailability(true);
		container.setCompany("");
		container.setContainerID(ID);
		container.setContentType("");
		container.setOwner(0);
		container.setPosition(Location);
		
		containers.add(container);
		return ID;	
		
	}
	
	public ResponseObject updatePosition(String journeyID, String position) {
		
		ResponseObject response = new ResponseObject(111, "Container not found");;

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
	
	public ResponseObject markArrived(String journeyID) {
		
		ResponseObject response = new ResponseObject(111, "Container not found");;

		 for (Container container : containers) {
			 if (container.getCurrentJourney() != null) {
				 if (container.getCurrentJourney().equals(journeyID)) {
					 container.setCurrentJourney("Just completed journey: " + journeyID);
					 response = new ResponseObject(075, "Journey info has been updated");
				 }  
			 }
		}
		
		return response;
	
	}
	
	public void remove(int row) {
		int containerID = containers.get(row).getContainerID();
		for(Container container: containers) {
			if(container.getContainerID() == containerID) {
				containers.remove(container);
			}
		}
	}
	
	@JsonIgnore
	@Override
	public int getColumnCount() {
		return 7;
	}
	
	@JsonIgnore
	@Override
	public int getRowCount() {
		return containers.size();
	}

	@JsonIgnore
	@Override
	public TableModelListener[] getTableModelListeners() {
        return listenerList.getListeners(TableModelListener.class);
    }
	
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