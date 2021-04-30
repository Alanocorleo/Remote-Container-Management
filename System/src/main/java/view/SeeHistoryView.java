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

import controller.SeeHistoryController;
import controller.Session;

public class SeeHistoryView extends JFrame {
	
	private static final long serialVersionUID = 989075282041187452L;
	private SeeHistoryController seeHistoryController;
	private JLabel lblSession;
	
	
	
	public SeeHistoryView(SeeHistoryController controller) {
		this.seeHistoryController = controller;
		initGUI();
	}



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
		
		
	}
	
	
	public void setSession(Session sessionModel) {
		lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
	}
	

}
