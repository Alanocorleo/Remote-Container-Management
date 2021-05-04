package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.SeeHistoryController;
import controller.Session;

/**
 * This class is responsible for displaying a window with a table of container's 
 * history for the logistics company. It extends JFrame to get advantage of the 
 * components that can be added to the window.
 */

public class SeeHistoryView extends JFrame {
	
	private static final long serialVersionUID = 989075282041187452L;
	private SeeHistoryController seeHistoryController;
	private JLabel lblSession;

	 /**
   	 * This constructor sets SeeHistoryController, and calls for GUI initialization
   	 * and window displaying.
   	 * @param controller
   	 */
	public SeeHistoryView(SeeHistoryController controller) {
		this.seeHistoryController = controller;
		initGUI();
	}

	/**
	 * This method initializes a GUI, and displays a corresponding window.
	 */
	private void initGUI() {
		setTitle("Choosing Container");
		setPreferredSize(new Dimension(800, 600));
		
		JPanel panel = new JPanel();
		JTextField container = new JTextField();
		JLabel label = new JLabel("Container ID");
		
		JButton button = new JButton("See info");
		
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean idFormateChecker = container.getText().matches("-?\\d+");
				if (!idFormateChecker) {
					new IdFormatErrorGUI();
				} else {
					seeHistoryController.showHistory(Integer.parseInt(container.getText()));
				}
			}
		});
		
		panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		panel.setLayout(new GridLayout(7,2));
		panel.add(label);
		panel.add(container);
		panel.add(button);
		add(panel);
		
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
	
}
