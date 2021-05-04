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

import controller.MainMenuCompanyController;
import controller.Session;

/**
 * This class is responsible for displaying a window with logistics company's main
 * menu for the logistics company. It extends JFrame to get advantage of the 
 * components that can be added to the window.
 */

public class MainMenuCompanyView extends JFrame {

	private static final long serialVersionUID = 989075282041187452L;
	private MainMenuCompanyController MainMenucontroller;
	private JLabel lblSession;
	private JLabel imageLabel;
    private JPanel box = new JPanel(new GridLayout(7, 1, 0, 10));
	
    /**
   	 * This constructor sets MainMenuCompanyController, and calls for GUI initialization
   	 * and window displaying.
   	 * @param controller
   	 */
	public MainMenuCompanyView(MainMenuCompanyController controller) {
		this.MainMenucontroller = controller;
		initGUI();
	}

	/**
	 * This method initializes a GUI, and displays a corresponding window.
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Main Menu");
		setPreferredSize(new Dimension(920, 630));
		setResizable(false);

		try {
		imageLabel = new JLabel();
//		URL url = this.getClass().getResource("/resources/cargo_blue.gif");
		URL url = Paths.get("cargo_blue.gif").toUri().toURL();
		ImageIcon imageIcon = new ImageIcon(url);
        imageLabel.setIcon(imageIcon);
        add(imageLabel, BorderLayout.CENTER);
		} 
		catch (NullPointerException | MalformedURLException e) {
				
		}
		
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

	/**
	 * This methods sets the session to a JLabel to be displayed on the window.
	 * @param sessionModel
	 */
	public void setSession(Session sessionModel) {
		lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
	}
	
}



