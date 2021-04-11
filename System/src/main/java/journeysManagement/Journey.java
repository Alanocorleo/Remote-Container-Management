package journeysManagement;

import java.security.SecureRandom;

public class Journey {
	
	private JourneyDatabase journeyDatabase;
	private String journeyID;
	private String origin;
	private String destination;
	private int departureDate;
	private int arrivalDate;

	public Journey(JourneyDatabase database) {
		this.journeyDatabase = database;
	}
	
	public String getJourneyID() {
		return journeyID;
	}

	public void setJourneyID(String journeyID) {
		this.journeyID = journeyID;
	}
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(int departureDate) {
		this.departureDate = departureDate;
	}

	public int getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(int arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public String createJourneyID() {
		
		SecureRandom output = new SecureRandom();
		int cipher = output.nextInt(100000);
		String journeyID = (String.format("%c%c", this.origin.charAt(0), this.destination.charAt(0)) + String.valueOf(cipher));
		if (this.journeyDatabase.getJourneys().containsKey(journeyID)) {
			return createJourneyID();
		}
		else 
			return this.journeyID = journeyID;
			       
	}

    public String toString() {
        return "{" + "id=" + journeyID + ", origin='" + origin + '\'' + '}';
    }
}
