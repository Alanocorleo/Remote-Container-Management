package management;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.time.Instant;

/**
 * HistoryBook class represents a book that keeps the container history.
 */

public class HistoryBook {
	
	private  ArrayList<String> date;
	private  ArrayList<Integer> temperature;
	private  ArrayList<Integer> humidity;
	private  ArrayList<Integer> pressure;
	private  ArrayList<String> position;
	
	/**
	 *  This constructor creates the array lists that keep track of data history
	 */
	public HistoryBook() {
		super();
		date = new ArrayList<String>();
		temperature = new ArrayList<Integer>();
		humidity = new ArrayList<Integer>();
		pressure = new ArrayList<Integer>();
		position = new ArrayList<String>();
	}
	
	/**
	 * This method and annotation fine tune the deserialization process
	 * it specifies how to get the data from database format.
	 * @param history
	 */
	@JsonCreator
	@SuppressWarnings("unchecked")
	public HistoryBook(Object[][] history) {
		date = (ArrayList<String>) history[0][0];
		temperature = (ArrayList<Integer>) history[1][0];
		humidity = (ArrayList<Integer>) history[2][0];
		pressure = (ArrayList<Integer>) history[3][0];
		position = (ArrayList<String>) history[4][0];
	}
	
	/**
	 * This method and annotation specify how the object is going to be serialized
	 * it also creates a matrix with all the history data.
	 * @return matrix of data history
	 */
	@JsonValue
	public Object[][] show() {
		Object table[][] = {{date},{temperature},{humidity},{pressure},{position}};
		return table;
	}
	
	/**
	 * This method returns history of container. It also creates a matrix with each 
	 * column containing data regarding a certain variable. This transposes the 
	 * matrix that show() creates.
	 * @return matrix of data history
	 */
	public Object[][] showTable() {
		//Object table[][] = {{date},{temperature},{humidity},{pressure},{position}};
		int size = date.size();
		Object[][] table = new Object[size][5];
				
		for (int i = 0; i < size; i++) {
			table[i][0] = date.get(i);
			table[i][1] = temperature.get(i);
			table[i][2] = humidity.get(i);
			table[i][3] = pressure.get(i);
			table[i][4] = position.get(i);
		}
		
		return table;
	}
	
	/**
	 * This method appends the new data to the array lists for each measurement.
	 * @param date
	 * @param temperature
	 * @param humidity
	 * @param pressure
	 * @param position
	 */
	public void add(String date, int temperature, int humidity, int pressure, String position) {
		this.date.add(date);
		this.temperature.add(temperature);
		this.humidity.add(humidity);
		this.pressure.add(pressure);
		this.position.add(position);
	}
	
}
