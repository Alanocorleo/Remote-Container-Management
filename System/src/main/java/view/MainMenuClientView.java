package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.MainMenuClientController;
import controller.Session;


public class MainMenuClientView extends JFrame {

		private static final long serialVersionUID = 989075282041187452L;
		private MainMenuClientController MainMenucontroller;
		private JLabel lblSession;
		private JLabel imageLabel;
	    private JPanel box = new JPanel(new GridLayout(4, 1, 0, 10));
		
		public MainMenuClientView(MainMenuClientController controller) {
			this.MainMenucontroller = controller;
			initGUI();
		}
	
		
		private void initGUI() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Main Menu");
			setPreferredSize(new Dimension(920, 630));
			setResizable(false);

			try {
			imageLabel = new JLabel();
//			URL url = this.getClass().getResource("/resources/cargo_blue.gif");
			URL url = Paths.get("cargo_blue.gif").toUri().toURL();
			ImageIcon imageIcon = new ImageIcon(url);
            imageLabel.setIcon(imageIcon);
            add(imageLabel, BorderLayout.CENTER);
			} 
			catch (NullPointerException | MalformedURLException e) {
				
			}
			
			// buttons( can add as many as you want :) )
			
			JButton btnClient = new JButton("Info");
			box.add(btnClient);
			btnClient.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MainMenucontroller.updateInfo();
				}
			});

			JButton btnFriend = new JButton("See/Add Friends");
			box.add(btnFriend);
			btnFriend.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				MainMenucontroller.friend();
				}
			});
			
			JButton btnJourney = new JButton("My journeys");
			box.add(btnJourney);
			btnJourney.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MainMenucontroller.displayJourneys();
				}
			});
			
			JButton btnRegister = new JButton("Register container(s) for journeys");
			box.add(btnRegister);
			btnRegister.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MainMenucontroller.register();
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



