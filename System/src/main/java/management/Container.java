package management;

/**
 * Container class represent single containers used for shipping.
 */

public class Container {
	
	private int containerID;
	private int owner;
	private String position;
	private String contentType;
	private String company;
	private boolean availability;
	private String currentJourney;
	private int temperature;
	private int humidity;
	private int pressure;
	private String date;
	private HistoryBook history;
	
	/**
	 * This constructor creates a new history book when introducing a container.
	 */
	// Container constructor
	public Container() {
		super();
		history = new HistoryBook();
	}

	/**
	 * This method returns the container-ID.
	 * @return container-ID
	 */
	public int getContainerID() {
		return containerID;
	}
	/**
	 * This method sets the container-ID.
	 * @param containerID
	 */
	public void setContainerID(int containerID) {
		this.containerID = containerID;
	}
	
	/**
	 * This method returns the current owner of the container.
	 * @return owner
	 */
	public int getOwner() {
		return owner;
	}
	/**
	 * This method sets the current owner of the container.
	 * @param owner
	 */
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	/**
	 * This method returns the current position of the container.
	 * @return position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * This method sets the current position of the container.
	 * @param position
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * This method returns the content type of the container.
	 * @return content type
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * This method sets the content type of the container.
	 * @param contentType
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	/**
	 * This method returns the company using the container.
	 * @return company
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * This method returns sets company using the container.
	 * @param company
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * This method returns the availability status of the container.
	 * @return availability
	 */
	public boolean isAvailability() {
		return availability;
	}
	/**
	 * This method returns the availability status of the container.
	 * @param availability
	 */
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	/**
	 * This method returns the current journey of the container.
	 * @return current journey
	 */
	public String getCurrentJourney() {
		return currentJourney;
	}
	/**
	 * This method sets the current journey of the container.
	 * @param currentJourney
	 */
	public void setCurrentJourney(String currentJourney) {
		this.currentJourney = currentJourney;
	}

	/**
	 * This method returns the current humidity of the container.
	 * @return humidity
	 */
	public int getHumidity() {
		return this.humidity;
	}
	/**
	 * This method sets the current humidity of the container.
	 * @param humidity
	 */
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	/**
	 * This method returns the current temperature of the container.
	 * @return temperature
	 */
	public int getTemperature() {
		return this.temperature;
	}
	/**
	 * This method sets the current temperature of the container.
	 * @param temperature
	 */
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
	/**
	 * This method returns the current pressure of the container.
	 * @return pressure
	 */
	public int getPressure() {
		return this.pressure;
	}
	/**
	 * This method sets the current pressure of the container.
	 * @param pressure
	 */
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	
	/**
	 * This method returns the date of container updates.
	 * @return date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * This method sets the date of container updates.
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * This method returns the history of the container.
	 * @return history
	 */
	public HistoryBook getHistory() {
		return history;
	}
	/**
	 * This method adds the current values to the history of the container.
	 * @return history
	 */
	public void appendHistory() {
		this.history.add(date, temperature, humidity, pressure, position);
	}
	
}
