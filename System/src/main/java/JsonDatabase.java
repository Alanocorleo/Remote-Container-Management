import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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
		
		JSONObject sampleObject = new JSONObject();
		Files.write(Paths.get("example.json"), sampleObject.toJSONString().getBytes());
		
		JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("example.json");
		
		Journey journey1 = new Journey(journeys);
		journey1.setJourneyID("CO23092");
		journey1.setOrigin("Copenhagen");
		journey1.setDestination("Oslo");	
		journeys.create(journey1);
		create("example.json", journey1, jsonObject);
		
		Journey journey2 = new Journey(journeys);
		journey2.setJourneyID("CO23392");
		journey2.setOrigin("Amsterdam");
		journey2.setDestination("Copenhagen");	
		create("example.json", journey2, jsonObject);
		
		Journey journey3 = new Journey(journeys);
		journey3.setJourneyID("CO23392");
		journey3.setOrigin("Amsterdam");
		journey3.setDestination("Copenhagen");	
		create("example.json", journey3, jsonObject);

		for (int i = 1; i <= 5; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(i - 1).setContainerID(i);
			containers.getContainers().get(i - 1).setPosition("Copenhagen");
			containers.getContainers().get(i - 1).setAvailability(true);
		}
		
		Client client = new Client();
		client.registerContainers("Copenhagen", "Fish", "Maersk", 2, containers);
		journeys.registerTo("CO23092", "Copenhagen", "Oslo", client.getMyContainers());
        
   
        
        Files.write(Paths.get("example.json"), jsonObject.toJSONString().getBytes());
        
   
    }
	
	public static void create(String filename, Journey journey, JSONObject jsonObject) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		ArrayList<Container> containerList = new ArrayList<Container>();
		jsonObject.put(mapper.writeValueAsString(journey), containerList);
		
		Files.write(Paths.get(filename), jsonObject.toString().replaceAll("\\\\","").getBytes());
	}
	
	public static Object readJsonSimpleDemo(String filename) throws Exception {
	    FileReader reader = new FileReader(filename);
	    JSONParser jsonParser = new JSONParser();
	    return jsonParser.parse(reader);
	}
	
	
}
