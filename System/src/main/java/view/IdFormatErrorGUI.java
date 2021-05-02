package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IdFormatErrorGUI {

	/**
	 * this creates the pop up error when wrong formats or types have been entered for either
	 * temperature, humidity, pressure or container ID
	 */
	public IdFormatErrorGUI() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel errlabel = new JLabel("Please enter Integers for Container ID, Temperature, Humidty and Pressure");
		panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		panel.setLayout(new GridLayout());
		panel.add(errlabel);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setTitle("Error");
		frame.pack();
		frame.setVisible(true);
	}

}
