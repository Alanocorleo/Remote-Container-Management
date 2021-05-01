import management.Client;
import management.ClientDatabase;
import management.Container;
import management.ContainerDatabase;
import management.Journey;
import management.JourneyDatabase;

public class Testing2 {
	
	public static void main(String[] args) {
		
		JourneyDatabase journeys = new JourneyDatabase();
		
		try {
			journeys.produce();
			journeys.pull();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Journey journey1 = new Journey();
		String journeyID1 = "CO23143";
		String origin1 = "Copenhagen";
		String destination1 = "Oslo";
		journey1.setJourneyID(journeyID1);
		journey1.setOrigin(origin1);
		journey1.setDestination(destination1);
		journey1.setDepartureDate("21/04/2021");
		journey1.setArrivalDate("22/04/2021");
		journeys.create(journey1);
		
		Journey journey2 = new Journey();
		String journeyID2 = "AC21003";
		String origin2 = "Amsterdam";
		String destination2 = "Copenhagen";
		journey2.setJourneyID(journeyID2);
		journey2.setOrigin(origin2);
		journey2.setDestination(destination2);	
		journey2.setDepartureDate("12/06/2021");
		journey2.setArrivalDate("15/06/2021");
		journeys.create(journey2);
		
		Journey journey3 = new Journey();
		String journeyID3 = "CO22211";
		String origin3 = "Copenhagen";
		String destination3 = "Oslo";
		journey3.setJourneyID(journeyID3);
		journey3.setOrigin(origin3);
		journey3.setDestination(destination3);
		journey3.setDepartureDate("30/04/2021");
		journey3.setArrivalDate("31/04/2021");
		journeys.create(journey3);
		
		Journey journey4 = new Journey();
		String journeyID4 = "OC32413";
		String origin4 = "Oslo";
		String destination4 = "Copenhagen";
		journey4.setJourneyID(journeyID4);
		journey4.setOrigin(origin4);
		journey4.setDestination(destination4);
		journey4.setDepartureDate("29/05/2021");
		journey4.setArrivalDate("30/05/2021");
		journeys.create(journey4);
		
		Journey journey5 = new Journey();
		String journeyID5 = "GA12147";
		String origin5 = "Gothenburg";
		String destination5 = "Amsterdam";
		journey5.setJourneyID(journeyID5);
		journey5.setOrigin(origin5);
		journey5.setDestination(destination5);
		journey5.setDepartureDate("17/05/2021");
		journey5.setArrivalDate("19/05/2021");
		journeys.create(journey5);
		
		try {
			journeys.push();
			journeys.pull();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ContainerDatabase containers = new ContainerDatabase();
		
		try {
			containers.produce();
			containers.pull();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int counter = 1;
		for (int i = 1; i <= 10; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(counter - 1).setContainerID(counter);
			containers.getContainers().get(counter - 1).setPosition("Copenhagen");
			containers.getContainers().get(counter - 1).setAvailability(true);
			counter += 1;
		}
		
		for (int i = 1; i <= 10; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(counter - 1).setContainerID(counter);
			containers.getContainers().get(counter - 1).setPosition("Oslo");
			containers.getContainers().get(counter - 1).setAvailability(true);
			counter += 1;
		}
		
		for (int i = 1; i <= 10; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(counter - 1).setContainerID(counter);
			containers.getContainers().get(counter - 1).setPosition("Gothenburg");
			containers.getContainers().get(counter - 1).setAvailability(true);
			counter += 1;
		}
		
		for (int i = 1; i <= 10; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(counter - 1).setContainerID(counter);
			containers.getContainers().get(counter - 1).setPosition("Amsterdam");
			containers.getContainers().get(counter - 1).setAvailability(true);
			counter += 1;
		}

		try {
			containers.push();
			containers.pull();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ClientDatabase clients = new ClientDatabase();
		
		try {
			clients.produce();
			clients.pull();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	Client client1 = new Client("Alan", "Mansour", "21/04/2021", "Alan.Mansour@mail.com", "+999-999-99-999");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.getClients().add(client1);
		Client client2 = new Client("Javier" ,"Almendra", "21/04/2021", "Javier.Almendra@mail.com", "+999-999-99-999");
		client2.setId(100001);
		client2.setPassword("0000");
		clients.getClients().add(client2);
		Client client3 = new Client("Kristyn" ,"Korboe", "21/04/2021", "Kristyn.Korboe@mail.com", "+999-999-99-999");
		client3.setId(100002);
		client3.setPassword("0000");
		clients.getClients().add(client3);
		Client client4 = new Client("Ousama","Mhadden", "21/04/2021", "Ousama.Mhadden@mail.com", "+999-999-99-999");
		client4.setId(100003);
		client4.setPassword("0000");
		clients.getClients().add(client4);
		Client client5 = new Client("Parsa","Mehrizi", "21/04/2021", "Parsa.Mehrizi@mail.com", "+999-999-99-999");
		client5.setId(100004);
		client5.setPassword("0000");
		clients.getClients().add(client5);
		Client client6 = new Client("Sarthak","Trehan", "21/04/2021", "Sarthak.Trehan@mail.com", "+999-999-99-999");
		client6.setId(100005);
		client6.setPassword("0000");
		clients.getClients().add(client6);
		
		containers.book(client1.getId(), "Copenhagen", "Fish", "Captain Fish", 2);
		containers.book(client2.getId(), "Amsterdam", "Flowers", "Captain Flower", 7);
		containers.book(client3.getId(), "Oslo", "Textile", "Captain Textile", 3);
		containers.book(client3.getId(), "Amsterdam", "Flowers", "Captain Textile", 2);
		containers.book(client4.getId(), "Copenhagen", "Banana", "Captain Banana", 5);
		containers.book(client5.getId(), "Gothenburg", "Plastic", "Captain Plastic", 4);
		containers.book(client6.getId(), "Amsterdam", "Tobacco", "Captain Tobacco", 1);
		
		journeys.registerTo("CO23143", "Copenhagen", "Oslo", containers.extract(client1.getId()));
		journeys.registerTo("AC21003", "Amsterdam", "Copenhagen", containers.extract(client2.getId()));
		journeys.registerTo("AC21003", "Amsterdam", "Copenhagen", containers.extract(client3.getId()));
		journeys.registerTo("OC32413", "Oslo", "Copenhagen", containers.extract(client3.getId()));
		journeys.registerTo("CO23143", "Copenhagen", "Oslo", containers.extract(client4.getId()));
		journeys.registerTo("GA12147", "Gothenburg", "Amsterdam", containers.extract(client5.getId()));
		journeys.registerTo("AC21003", "Amsterdam", "Copenhagen", containers.extract(client6.getId()));
		
		try {
			clients.push();
			containers.push();
			journeys.push();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
