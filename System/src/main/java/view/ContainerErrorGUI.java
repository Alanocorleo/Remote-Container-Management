package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContainerErrorGUI {
	
	/**
	 * This method creates a pop up error when the container is not found.
	 */
	public ContainerErrorGUI() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel errlabel = new JLabel("This container does not exist!");
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		panel.setLayout(new GridLayout());
		panel.add(errlabel);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setTitle("Error");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
