import management.Client;
import management.Container;
import management.ContainerDatabase;
import management.Journey;
import management.JourneyDatabase;

//import java.time.Instant;

public class Testing {
	
	public static void main(String[] args) {
		
		JourneyDatabase journeys = new JourneyDatabase();
		
		try {
			journeys.produce();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		Journey journey = new Journey();
		String journeyID = "CO23143";
		String origin = "Copenhagen";
		String destination = "Oslo";
		journey.setJourneyID(journeyID);
		journey.setOrigin(origin);
		journey.setDestination(destination);	
		
		try {
			journeys.pull();
			journeys.create(journey);
			journeys.push();
		} catch (Exception e) {

			e.printStackTrace();
		}
		ContainerDatabase containers = new ContainerDatabase();
		int quantity = 11;
		String location = "Copenhagen";
		String contentType = "fish";
		String company = "Banana Company";
		int temperature = 20;
		int humidity = 21;
		int pressure = 22;
		
		for (int i = 1 ; i <= quantity; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(i - 1).setContainerID(i);
			containers.getContainers().get(i - 1).setPosition(location);
			containers.getContainers().get(i - 1).setAvailability(true);
			containers.getContainers().get(i - 1).setTemperature(temperature);
			containers.getContainers().get(i - 1).setHumidity(humidity);
			containers.getContainers().get(i - 1).setPressure(pressure);
			containers.getContainers().get(i - 1).appendHistory();
			containers.getContainers().get(i - 1).appendHistory();
			containers.getContainers().get(i - 1).appendHistory();
		}
		
		Client client = new Client();
		client.setId(100);
		containers.book(client.getId(), origin, contentType, company, quantity);
		journeys.registerTo(journeyID, origin, destination, client.getMyContainers());
		
		try {
			journeys.push();
			journeys.pull();
			journeys.push();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
