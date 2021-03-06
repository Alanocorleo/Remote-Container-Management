package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DateFormatErrorGUI {
	
	/**
	 * This method creates a pop up error when the entered date is in the wrong format.
	 */
	public DateFormatErrorGUI() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel errlabel = new JLabel("The format for date is incorrect! Use this format: DD/MM/YYYY");
		panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		panel.setLayout(new GridLayout());
		panel.add(errlabel);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setTitle("Error");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}
