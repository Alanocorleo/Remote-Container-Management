package journeysManagement;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.time.Instant;

public class HistoryBook {
	
	private  ArrayList<Instant> date;
	private  ArrayList<Integer> temperature;
	private  ArrayList<Integer> humidity;
	private  ArrayList<Integer> pressure;
	private  ArrayList<String> position;
	
	
	public HistoryBook() {
		super();
		date = new ArrayList<Instant>();
		temperature = new ArrayList<Integer>();
		humidity = new ArrayList<Integer>();
		pressure = new ArrayList<Integer>();
		position = new ArrayList<String>();
	}
	
	@JsonCreator
	@SuppressWarnings("unchecked")
	public HistoryBook(Object[][] history) {
		date = (ArrayList<Instant>) history[0][0];
		temperature = (ArrayList<Integer>) history[1][0];
		humidity = (ArrayList<Integer>) history[2][0];
		pressure = (ArrayList<Integer>) history[3][0];
		position = (ArrayList<String>) history[4][0];
	}
	
	@JsonValue
	public Object[][] show() {
		Object table[][] = {{date},{temperature},{humidity},{pressure},{position}};
		return table;
	}

	public void add(int temperature, int humidity, int pressure, String position) {
		// this.date.add(Instant.now());
		this.temperature.add(temperature);
		this.humidity.add(humidity);
		this.pressure.add(pressure);
		this.position.add(position);
	}
	
}
