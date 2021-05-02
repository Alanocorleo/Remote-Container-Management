package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.AddHistorycontroller;
import controller.Session;

public class AddHistoryView extends JFrame {

		private static final long serialVersionUID = 989075282041187452L;
		private AddHistorycontroller addHistorycontroller;
		private JLabel lblSession;
		
		public AddHistoryView(AddHistorycontroller controller) {
			this.addHistorycontroller = controller;
			initGUI();
		}
	
		/**
		 * this creates the GUI window that asks the company for container ID
		 * and the new measurement values to be updated for that container
		 * 
		 */
		private void initGUI() {
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Updating container information");
			setPreferredSize(new Dimension(800, 600));
			
			// buttons( can add as many as you want :) )
			JPanel panel = new JPanel();
			JTextField date = new JTextField();
			JLabel dlabel = new JLabel("Date");
			
			JTextField container = new JTextField();
			JLabel clabel = new JLabel("Container ID");

			JTextField temperature = new JTextField();
			JLabel tlabel = new JLabel("Temperature");

			JTextField humidity = new JTextField();
			JLabel hlabel = new JLabel("Humidity");

			JTextField pressure = new JTextField();
			JLabel plabel = new JLabel("Pressure");

//			JTextField position = new JTextField();
//			JLabel poslabel = new JLabel("Position");

			JButton button = new JButton("Update info");
			
			lblSession = new JLabel();
			lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean dateFormatChecker = Pattern.matches("([0-9]{2})\\\\([0-9]{2})\\\\([0-9]{4})", date.getText());
					boolean idFormateChecker = container.getText().matches("-?\\d+");
					boolean TempFormatChecker = temperature.getText().matches("-?\\d+");
					boolean HumidFormatChecker = humidity.getText().matches("-?\\d+");
					boolean PressFormatChecker = pressure.getText().matches("-?\\d+");
					if (!dateFormatChecker) {
						new DateFormatErrorGUI();
					} else if (!idFormateChecker || !TempFormatChecker || !HumidFormatChecker || !PressFormatChecker) {
						new IdFormatErrorGUI();
					} else {
					addHistorycontroller.submitChanges(date.getText(),Integer.parseInt(container.getText()),Integer.parseInt(temperature.getText()),Integer.parseInt(humidity.getText()),Integer.parseInt(pressure.getText()));
					}
				}
			});
			
			panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
			panel.setLayout(new GridLayout(7,2));
			panel.add(dlabel);
			panel.add(date);
			panel.add(clabel);
			panel.add(container);
			panel.add(tlabel);
			panel.add(temperature);
			panel.add(hlabel);
			panel.add(humidity);
			panel.add(plabel);
			panel.add(pressure);
//			panel.add(poslabel);
//			panel.add(position);
			panel.add(button);
			add(panel);
			
			pack();
			
		}	

		public void setSession(Session sessionModel) {
			lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
		}
	}

