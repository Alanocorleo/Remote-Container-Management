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
import controller.DisplayJourneysRegisterController;

/**
 * This class is responsible for displaying a window with a table of journeys 
 * to book containers, and eventually register them the desired journey for the clients. 
 * It extends JFrame to get advantage of the components that can be added to the window.
 */

public class DisplayJourneysRegisterView extends JFrame {

	private static final long serialVersionUID = 4212860552112409994L;
	private DisplayJourneysRegisterController controller;
	private JTable tblInventory;
	private JLabel lblSession;
	
	/**
	 * This constructor sets DisplayJourneysRegisterController, and calls for GUI initialization
	 * and window displaying.
	 * @param controller
	 */
	public DisplayJourneysRegisterView(DisplayJourneysRegisterController controller) {
		this.controller = controller;
		initGUI();
	}
	
	/**
	 * This method initializes a GUI, and displays a corresponding window.
	 */
	private void initGUI() {
		setTitle("Journeys");
		setPreferredSize(new Dimension(800, 600));
		
		// buttons
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.find();
			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.refresh();
			}
		});
		
		JButton btnSelect = new JButton("Select journey");
		btnSelect.setEnabled(false);
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.select(tblInventory.getSelectedRow());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
		// toolbar
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JToolBar toolbar = new JToolBar();
		toolbar.add(btnFind);
		toolbar.add(btnRefresh);
		toolbar.add(btnSelect);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(lblSession);
		add(toolbar, BorderLayout.NORTH);
		
		// table
		tblInventory = new JTable();
		tblInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblInventory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnSelect.setEnabled((tblInventory.getSelectedRow() >= 0));
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
	 * This methods displays a window with a confirmation message.
	 */
	public void showConfirmation(String message) {
		JOptionPane.showMessageDialog(this, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
