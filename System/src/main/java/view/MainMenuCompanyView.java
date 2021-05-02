package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.MainMenuCompanyController;
import controller.Session;

public class MainMenuCompanyView extends JFrame {

		private static final long serialVersionUID = 989075282041187452L;
		private MainMenuCompanyController MainMenucontroller;
		private JLabel lblSession;
		private JLabel imageLabel;
	    private JPanel box = new JPanel(new GridLayout(7, 1, 0, 10));
		
		public MainMenuCompanyView(MainMenuCompanyController controller) {
			this.MainMenucontroller = controller;
			initGUI();
		}
	
		private void initGUI() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Main Menu");
			setPreferredSize(new Dimension(920, 630));
			setResizable(false);

//			imageLabel = new JLabel();
//			URL url = this.getClass().getResource("/resources/cargo_blue.gif");
//     		ImageIcon imageIcon = new ImageIcon(url);
//            imageLabel.setIcon(imageIcon);
//            add(imageLabel, BorderLayout.CENTER);
			
			// buttons
			
			JButton btnClient = new JButton("Clients");
			box.add(btnClient);
			btnClient.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MainMenucontroller.displayClients(); 
				}
			});
			
			JButton btnContainer = new JButton("Containers");
			box.add(btnContainer);
			btnContainer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MainMenucontroller.displayContainers();
				}
			});
			
			JButton btnJourney = new JButton("Journeys");
			box.add(btnJourney);
			btnJourney.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MainMenucontroller.displayJourneys();
				}
			});
			JButton btnContainerUpdate = new JButton("Update container Status");
			box.add(btnContainerUpdate);
			btnContainerUpdate.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MainMenucontroller.displayAddHistory();
				}
			});
			JButton btnContainerInfo = new JButton("See Container History");
			box.add(btnContainerInfo);
			btnContainerInfo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MainMenucontroller.displaySeeHistory();
				}
			});
			
			// toolbar
			lblSession = new JLabel();
			lblSession.setVerticalAlignment(SwingConstants.BOTTOM);
			JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
			toolbar.addSeparator(new Dimension(10, 10));
			toolbar.add(box);
     		toolbar.add(Box.createVerticalGlue());
			toolbar.add(lblSession);
			add(toolbar, BorderLayout.WEST);
			pack();
			setLocationRelativeTo(null);
		}	

		public void setSession(Session sessionModel) {
			lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
		}
		
	}



