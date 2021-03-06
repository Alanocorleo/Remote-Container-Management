package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.AddHistorycontroller;
import controller.Session;

/**
 * This class is responsible for displaying a window to update the history book
 * of a container for the logistics company. It extends JFrame to get advantage 
 * of the components that can be added to the window.
 */

public class AddHistoryView extends JFrame {

	private static final long serialVersionUID = 989075282041187452L;
	private AddHistorycontroller addHistorycontroller;
	private JLabel lblSession;
	
	/**
	 * This constructor sets AddHistorycontroller, and calls for GUI initialization
	 * and window displaying.
	 * @param controller
	 */
	public AddHistoryView(AddHistorycontroller controller) {
		this.addHistorycontroller = controller;
		initGUI();
	}

	/**
	 * This method initializes a GUI, and displays a corresponding window.
	 */
	private void initGUI() {
		setTitle("Updating container's information");
		setPreferredSize(new Dimension(800, 600));
		
		// buttons( can add as many as you want :) )
		JPanel panel = new JPanel();
		
		JTextField container = new JTextField();
		JLabel clabel = new JLabel("Container ID");

		JTextField temperature = new JTextField();
		JLabel tlabel = new JLabel("Temperature");

		JTextField humidity = new JTextField();
		JLabel hlabel = new JLabel("Humidity");

		JTextField pressure = new JTextField();
		JLabel plabel = new JLabel("Pressure");

		JButton button = new JButton("Update info");
		
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
				LocalDate localDate = LocalDate.now();
				String date = dtf.format(localDate).toString();
				boolean idFormateChecker = container.getText().matches("-?\\d+");
				boolean TempFormatChecker = temperature.getText().matches("-?\\d+");
				boolean HumidFormatChecker = humidity.getText().matches("-?\\d+");
				boolean PressFormatChecker = pressure.getText().matches("-?\\d+");
				if (!idFormateChecker || !TempFormatChecker || !HumidFormatChecker || !PressFormatChecker) {
					new IdFormatErrorGUI();
				} else {
				addHistorycontroller.submitChanges(date,Integer.parseInt(container.getText()),Integer.parseInt(temperature.getText()),Integer.parseInt(humidity.getText()),Integer.parseInt(pressure.getText()));
				}
			}
		});
		
		panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		panel.setLayout(new GridLayout(7,2));
		panel.add(clabel);
		panel.add(container);
		panel.add(tlabel);
		panel.add(temperature);
		panel.add(hlabel);
		panel.add(humidity);
		panel.add(plabel);
		panel.add(pressure);
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

