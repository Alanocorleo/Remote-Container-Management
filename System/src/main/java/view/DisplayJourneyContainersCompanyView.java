package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import controller.Session;
import controller.DisplayJourneyContainersCompanyController;

/**
 * This class is responsible for displaying a window with table of journey containers
 * for the logistics company. It extends JFrame to get advantage of the components 
 * that can be added to the window.
 */

public class DisplayJourneyContainersCompanyView extends JFrame {

	private static final long serialVersionUID = -5189631801554166475L;
	private DisplayJourneyContainersCompanyController controller;
	private JTable tblInventory;
	private JLabel lblSession;
	
	/**
	 * This constructor sets DisplayJourneyContainersCompanyController, and calls for GUI initialization
	 * and window displaying.
	 * @param controller
	 */
	public DisplayJourneyContainersCompanyView(DisplayJourneyContainersCompanyController controller) {
		this.controller = controller;
		initGUI();
	}
	
	/**
	 * This method initializes a GUI, and displays a corresponding window.
	 */
	private void initGUI() {
		setTitle("Containers");
		setPreferredSize(new Dimension(800, 600));
		
		// buttons
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.find();
			}
		});	
		
		JButton btnRemove = new JButton("Remove from journey");
		btnRemove.setEnabled(false);
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.remove(tblInventory.getSelectedRow());
			}
		});	
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.refresh();
			}
		});
	
		// toolbar
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JToolBar toolbar = new JToolBar();
		toolbar.add(btnFind);
		toolbar.add(btnRemove);
		toolbar.add(btnRefresh);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(lblSession);
		add(toolbar, BorderLayout.NORTH);
		
		// table
		tblInventory = new JTable();
		tblInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblInventory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnRemove.setEnabled((tblInventory.getSelectedRow() >= 0));
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
	
}
