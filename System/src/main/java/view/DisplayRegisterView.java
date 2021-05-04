package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.DisplayRegisterController;
import controller.Session;

/**
 * This class is responsible for displaying a window to proceed with filling the
 * required fields in order to book and register containers to journeys for the clients. 
 * It extends JFrame to get advantage of the components that can be added to the window.
 */

public class DisplayRegisterView extends JFrame {

	private static final long serialVersionUID = 4212863552112409964L;
	private DisplayRegisterController controller;
	private JLabel lblSession;
	private JPanel boxFromTo = new JPanel(new GridLayout(2, 2, 2, 0));
	private JPanel boxCompany = new JPanel(new GridLayout(2, 1, 0, 0));
	private JPanel boxContainers = new JPanel(new GridLayout(2, 2, 2, 0));
	private JPanel panel = new JPanel();
	
	/**
	 * This constructor sets DisplayRegisterController, and calls for GUI initialization
	 * and window displaying.
	 * @param controller
	 */
	public DisplayRegisterView(DisplayRegisterController displayRegisterController) {
		this.controller = displayRegisterController;
		initGUI();
	}
	
	/**
	 * This method initializes a GUI, and displays a corresponding window.
	 */
	private void initGUI() {
		setTitle("Register");
		setPreferredSize(new Dimension(800, 600));
		
	   panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	
	   JTextField origin = new JTextField();
	   JTextField destination = new JTextField();
	   JTextField company = new JTextField();
	   JTextField contentType = new JTextField();
	   JTextField numberOfContainers = new JTextField();
	   
       String[] labelStrings = {
               "From: ",
               "To: ",
               "Company: ",
               "Content Type: ",
               "Number of Containers: "
           };
	   JLabel[] labels = new JLabel[labelStrings.length];
	   for (int i = 0; i < labelStrings.length; i++) {
	       labels[i] = new JLabel(labelStrings[i],
	                              JLabel.LEFT);
	       labels[i].setFont(new java.awt.Font("Century Gothic", Font.PLAIN, 16));
	   }
	   
	   Border grayline = BorderFactory.createLineBorder(Color.gray);
	   
	   boxFromTo.add(labels[0]); boxFromTo.add(labels[1]);
	   boxFromTo.add(origin); boxFromTo.add(destination);
	   boxFromTo.setBorder(grayline);
	   
	   boxCompany.add(labels[2]);
	   boxCompany.add(company);
	   boxCompany.setBorder(grayline);
	   
	   boxContainers.add(labels[3]); boxContainers.add(labels[4]);
	   boxContainers.add(contentType); boxContainers.add(numberOfContainers);
	   boxContainers.setBorder(grayline);
	   
	   JLabel title = new JLabel("Booking Information: ", JLabel.LEFT);
       title.setFont(new java.awt.Font("Century Gothic", Font.PLAIN, 16));
       
       panel.add(title);
       
       JPanel[] boxes = {boxFromTo, boxCompany, boxContainers};
       for (JPanel box : boxes) {
    	   panel.add(box);
    	   panel.add(Box.createRigidArea(new Dimension(0, 20)));
       }
       
       JPanel leftHalf = new JPanel() {
           //Don't allow us to stretch vertically.
           public Dimension getMaximumSize() {
        	   Dimension pref = getPreferredSize();
               return new Dimension(Integer.MAX_VALUE,
            		  pref.height);
           }
       };
       leftHalf.add(panel);
	   add(leftHalf, BorderLayout.CENTER);
	   
		// buttons
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.request(origin.getText(), destination.getText(), company.getText(), contentType.getText(), numberOfContainers.getText());
			}
		});

		// toolbar
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JToolBar toolbar = new JToolBar();
		toolbar.add(btnContinue);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(lblSession);
		add(toolbar, BorderLayout.NORTH);
		
		pack();
		setLocationRelativeTo(null);
	}

	/**
	 * This methods sets the session to a JLabel to be displayed on the window.
	 * @param sessionModel
	 */
	public void setSession(Session sessionModel) {
		lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
	}
	
	/**
	 * This methods displays a window with an error message.
	 */
	public void showError(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Input error", JOptionPane.ERROR_MESSAGE);
	}
	
}
