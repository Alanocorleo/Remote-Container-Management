package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DateFormatErrorGUI {
	
	/**
	 * this creates the pop up error when the entered date is in the wrong format
	 */
	public DateFormatErrorGUI() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel errlabel = new JLabel("The format for date is incorrect! use this format: XX\\XX\\XXXX");
		panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		panel.setLayout(new GridLayout());
		panel.add(errlabel);
		
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setTitle("Error");
		frame.pack();
		frame.setVisible(true);
	}
}
