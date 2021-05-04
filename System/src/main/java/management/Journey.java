package management;

import java.security.SecureRandom;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Journey class represent single marine journeys.
 */

public class Journey {
	
	private String journeyID;
	private String origin;
	private String destination;
	private String departureDate;
	private String arrivalDate;
	private JourneyDatabase journeyDatabase;

	/**
	 * This constructor is not used for anything.
	 */
	// Journey Constructor
	public Journey() {	
	}
	
	/**
     * This constructor initialises journey attributes after deserializing 
     * the journey database. It is necessary since jackson cannot deserialize 
     * a hash map composed of journey keys and container array lists, automatically.
     * @param key
     */
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
	
	/**
	 * This method returns the journey-ID
	 * @return journey-ID
	 */
	public String getJourneyID() {
		return journeyID;
	}
	/**
	 * This method sets the journey-ID.
	 * @param journeyID
	 */
	// @JsonProperty() can be used to rename variables instead of overriding toString()
	@JsonProperty("ID")
	public void setJourneyID(String journeyID) {
		this.journeyID = journeyID;
	}
	
	/**
	 * This method returns the origin of the journey.
	 * @return origin
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * This method sets the origin of the journey.
	 * @param origin
	 */
	@JsonProperty("Origin")
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	/**
	 * This method returns the destination of the journey.
	 * @return destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * This method sets the destination of the journey.
	 * @param destination
	 */
	@JsonProperty("Destination")
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * This method returns the departure date of the journey.
	 * @return departure date
	 */
	public String getDepartureDate() {
		return departureDate;
	}
	/**
	 * This method sets the departure date of the journey.
	 * @param departureDate
	 */
	@JsonProperty("Departure")
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	/**
	 * This method returns the arrival date of the journey.
	 * @return arrival date
	 */
	public String getArrivalDate() {
		return arrivalDate;
	}
	/**
	 * This method sets the arrival date of the journey.
	 * @param arrivalDate
	 */
	@JsonProperty("Arrival")
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	/**
	 * This method returns the journey database.
	 * @return journey database
	 */
	public JourneyDatabase getJourneyDatabase() {
		return journeyDatabase;
	}
	/**
	 * This method sets the journey database.
	 * @param database
	 */
	public void setJourneyDatabase(JourneyDatabase database) {
		this.journeyDatabase = database;
	}
	
	/**
	 * This method creates a unique journey-ID of pattern XV00000, where X is the first
	 * letter of the origin site, and V is the first letter of the destination site,
	 * by generating a random code and recursively checking if there are any identical
	 * journey IDs in the journey database. If no identical journey-IDs were found,
	 * then set the generated journey-ID to the journey.
	 * @return journeyID
	 */
	public String createJourneyID() {
		SecureRandom output = new SecureRandom();
		int cipher = output.nextInt(100000);
		String journeyID = (String.format("%c%c", this.origin.charAt(0), this.destination.charAt(0)) + String.valueOf(cipher));
		if (this.journeyDatabase.getJourneys().containsKey(journeyID)) {
			return createJourneyID();
		}
		else {
			return this.journeyID = journeyID;
		}	       
	}
	
	/**
	 * This method returns a stringified format of a journey to be used when 
	 * serializing to the container database.
	 * @return Journey information
	 */
	// @JsonValue is used for serializing
    @JsonValue
    public String toString() {
        return "{" + "ID : " + journeyID + ", Origin : " + origin + ", Destination : " + destination + ", Departure : " + departureDate + ", Arrival : " + arrivalDate + "}";
    }

}
