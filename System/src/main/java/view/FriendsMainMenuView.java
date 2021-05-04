package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import controller.Session;
import controller.FriendsMainMenuController;

/**
 * This class is responsible for displaying a window with a table of friends for the 
 * clients. It extends JFrame to get advantage of the components that can be added 
 * to the window.
 */

public class FriendsMainMenuView extends JFrame {
	
	private static final long serialVersionUID = 989075282041187452L;
	private FriendsMainMenuController controller;
	private JTable tblInventory;
	private JLabel lblSession;
	
	/**
	 * This constructor sets FriendsMainMenuController, and calls for GUI initialization
	 * and window displaying.
	 * @param controller
	 */
	public FriendsMainMenuView(FriendsMainMenuController controller) {
		this.controller = controller;
		initGUI();
	}
	
	/**
	 * This method initializes a GUI, and displays a corresponding window.
	 */
	private void initGUI() {
		setTitle("User Manager");
		setPreferredSize(new Dimension(800, 600));
		
		// buttons
		JButton btnFriend = new JButton("Share my information with a friend");
		btnFriend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.friendAdding();
			}
		});

		JButton btnShowJourneys = new JButton("Show friend's journeys");
		btnShowJourneys.setEnabled(false);
		btnShowJourneys.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.showJourneys(tblInventory.getSelectedRow());
			}
		});
		
		// toolbar
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JToolBar toolbar = new JToolBar();
		toolbar.add(btnFriend);
		toolbar.add(btnShowJourneys);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(lblSession);
		add(toolbar, BorderLayout.NORTH);
		
		// table
		tblInventory = new JTable();
		tblInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblInventory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnShowJourneys.setEnabled((tblInventory.getSelectedRow() >= 0));
 			}
		});
		add(new JScrollPane(tblInventory), BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void setTableModel(TableModel model) {
		tblInventory.setModel(model);
	}

	/**
	 * This methods sets the session to a JLabel to be displayed on the window.
	 * @param sessionModel
	 */
	public void setSession(Session sessionModel) {
		lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
	}

	/**
	 * This methods displays a window with a given error message.
	 * @param errorMessage
	 */
	public void showError(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Adding Friend", JOptionPane.INFORMATION_MESSAGE);
	}
	
}