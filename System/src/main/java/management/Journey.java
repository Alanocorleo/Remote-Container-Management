package management;

import java.security.SecureRandom;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class Journey {
	
	private JourneyDatabase journeyDatabase;
	private String journeyID;
	private String origin;
	private String destination;
	private String departureDate;
	private String arrivalDate;

	public Journey() {	
	}
	
	// @JsonCreator is used for deserializing
	@JsonCreator
	public Journey(String key) {
		String[] info = key.split("[\",\\:\\{\\}\\s]+");
		this.journeyID = info[2];
		this.origin = info[4];
		this.destination = info[6];
		this.departureDate = info[8];
		this.arrivalDate = info[10];
	}
	
	public String getJourneyID() {
		return journeyID;
	}
	// @JsonProperty() can be used to rename variables instead of overriding toString()
	@JsonProperty("ID")
	public void setJourneyID(String journeyID) {
		this.journeyID = journeyID;
	}
	
	public String getOrigin() {
		return origin;
	}
	@JsonProperty("Origin")
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getDestination() {
		return destination;
	}
	@JsonProperty("Destination")
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDepartureDate() {
		return departureDate;
	}
	@JsonProperty("Departure")
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}
	@JsonProperty("Arrival")
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public void setJourneyDatabase(JourneyDatabase database) {
		this.journeyDatabase = database;
	}
	
	public JourneyDatabase getJourneyDatabase() {
		return journeyDatabase;
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
	
	// @JsonValue is used for serializing
    @JsonValue
    public String toString() {
        return "{" + "ID : " + journeyID + ", Origin : " + origin + ", Destination : " + destination + ", Departure : " + departureDate + ", Arrival : " + arrivalDate + "}";
    }

}
