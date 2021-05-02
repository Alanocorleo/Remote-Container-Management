package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContainerErrorGUI {
	
	/**
	 * this creates the pop up error when the container is not found
	 */
	public ContainerErrorGUI() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel errlabel = new JLabel("This container does not exist!");
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		panel.setLayout(new GridLayout());
		panel.add(errlabel);
		
		
		frame.add(panel, BorderLayout.CENTER);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Error");
		frame.pack();
		frame.setVisible(true);

	}
	
//	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new ErrorGUI();

//	}
	

}
