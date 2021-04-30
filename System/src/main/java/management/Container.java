package management;
	// The instances of this class represent single containers.
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
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	// every container contains its own history
	private HistoryBook history;

	public Container() {
		super();
		history = new HistoryBook();
	}

	public int getContainerID() {
		return containerID;
	}
	public void setContainerID(int containerID) {
		this.containerID = containerID;
	}
	
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	public String getCurrentJourney() {
		return currentJourney;
	}
	public void setCurrentJourney(String currentJourney) {
		this.currentJourney = currentJourney;
	}

	public int getPressure() {
		return this.pressure;
	}
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getHumidity() {
		return this.humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getTemperature() {
		return this.temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public HistoryBook getHistory() {
		return history;
	}
	public void setHistory(HistoryBook history) {
		this.history = history;
	}
	// this method adds the current values to the history of the container
	public void appendHistory() {
		this.history.add(date, temperature, humidity, pressure, position);
	}
	
}