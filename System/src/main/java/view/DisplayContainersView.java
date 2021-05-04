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
import controller.DisplayContainersController;

/**
 * This class is responsible for displaying a window with table of containers for 
 * the logistics company. It extends JFrame to get advantage of the components that
 * can be added to the window.
 */

public class DisplayContainersView extends JFrame {

	private static final long serialVersionUID = 4212860552112409964L;
	private DisplayContainersController controller;
	private JTable tblInventory;
	private JLabel lblSession;
	
	/**
	 * This constructor sets DisplayContainersController, and calls for GUI initialization
	 * and window displaying.
	 * @param controller
	 */
	public DisplayContainersView(DisplayContainersController controller) {
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
		
		JButton btnFindAvailable = new JButton("Show available containers");
		btnFindAvailable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.findAvailable();;
			}
		});
		
		JButton btnAdd = new JButton("Register new");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.register();
			}
		});
		
		JButton btnDelete = new JButton("Remove");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.delete(tblInventory.getSelectedRow());
			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.refresh();
			}
		});
		
		JButton btnSave = new JButton("Permanently save changes");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.save();
					System.out.print("Saved");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	
		// toolbar
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JToolBar toolbar = new JToolBar();
		toolbar.add(btnFind);
		toolbar.add(btnFindAvailable);
		toolbar.add(btnAdd);
		toolbar.add(btnDelete);
		toolbar.add(btnRefresh);
		toolbar.add(btnSave);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(lblSession);
		add(toolbar, BorderLayout.NORTH);
		
		// table
		tblInventory = new JTable();
		tblInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblInventory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnDelete.setEnabled((tblInventory.getSelectedRow() >= 0));
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
