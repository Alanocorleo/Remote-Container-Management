import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;

import clientsManagement.Client;
import journeysManagement.Container;
import journeysManagement.ContainerDatabase;
import journeysManagement.Journey;
import journeysManagement.JourneyDatabase;

public class JsonDatabase {

	public static void main(String[] args) throws Exception {
		
		JourneyDatabase journeys = new JourneyDatabase();
		ContainerDatabase containers = new ContainerDatabase();
		ObjectMapper mapper = new ObjectMapper();
		
		JSONObject sampleObject = new JSONObject();
		Files.write(Paths.get("example.json"), sampleObject.toJSONString().getBytes());
		
		Journey journey1 = new Journey();
		journey1.setJourneyID("CO23092");
		journey1.setOrigin("Copenhagen");
		journey1.setDestination("Oslo");	
		journeys.create(journey1);
		create("example.json", journey1, journeys);
		
		Journey journey2 = new Journey();
		journey2.setJourneyID("CO23392");
		journey2.setOrigin("Amsterdam");
		journey2.setDestination("Copenhagen");	
		journeys.create(journey2);
		create("example.json", journey2, journeys);
		
		Journey journey3 = new Journey();
		journey3.setJourneyID("CO23192");
		journey3.setOrigin("Amsterdam");
		journey3.setDestination("Copenhagen");	
		journeys.create(journey3);
		create("example.json", journey3, journeys);

//		for (int i = 1; i <= 5; i++) {
//			containers.getContainers().add(new Container());
//			containers.getContainers().get(i - 1).setContainerID(i);
//			containers.getContainers().get(i - 1).setPosition("Copenhagen");
//			containers.getContainers().get(i - 1).setAvailability(true);
//		}
//		
//		Client client = new Client();
//		client.registerContainers("Copenhagen", "Fish", "Maersk", 2, containers);
//		journeys.registerTo("CO23092", "Copenhagen", "Oslo", client.getMyContainers());
//		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(journeys);
//		journeys.push();
//		//Files.write(Paths.get("example.json"), jsonResult.getBytes());
//		
//		journeys.pull();
//		//journeys = mapper.readValue(new File("example.json"), JourneyDatabase.class);
//		
//		for(Journey key : journeys.getJourneys().keySet()) {
//			
//			for (Container container : journeys.getJourneys().get(key)) {
//				
//				System.out.println(container.getPosition());
//				
//			}
//			
//		}
//		

        
        //Files.write(Paths.get("example.json"), jsonObject.toJSONString().getBytes());
        
    }
	
	public static void create(String filename, Journey journey, JourneyDatabase journeys) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		ArrayList<Container> containerList = new ArrayList<Container>();
		journeys.getJourneys().put(journey, containerList);
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(journeys);
		Files.write(Paths.get(filename), jsonResult.getBytes());
	}
	
	// replaceAll("\\\\","")
	public static Object readJsonSimpleDemo(String filename) throws Exception {
	    FileReader reader = new FileReader(filename);
	    JSONParser jsonParser = new JSONParser();
	    return jsonParser.parse(reader);
	}
	
	
}
