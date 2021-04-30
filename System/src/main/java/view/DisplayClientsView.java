//
//public class DisplayClientsView extends JFrame {
//
//	private static final long serialVersionUID = 989075282041187452L;
//	private DisplayClientsController controller;
//	private JTable tblInventory;
//	private JLabel lblSession;
//	
//	public DisplayClientsView(DisplayClientsController controller) {
//		this.controller = controller;
//		initGUI();
//	}
//	
//	private void initGUI() {
//		setTitle("User Manager");
//		setPreferredSize(new Dimension(800, 600));
//		
//		// buttons
//		JButton btnNew = new JButton("Find");
//		btnNew.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				controller.filter();
//			}
//		});
//		
//		JButton btnadd = new JButton("Add new client");
//		btnadd.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				controller.addClient();
//			}
//		});
//		
//		
//		JButton btnRefresh = new JButton("Refresh");
//		btnRefresh.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				controller.refresh();
//			}
//		});
//		// toolbar
//		lblSession = new JLabel();
//		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
//		
//		JToolBar toolbar = new JToolBar();
//		toolbar.add(btnNew);
//		toolbar.add(btnadd);
//		toolbar.add(btnRefresh);
//		toolbar.add(Box.createHorizontalGlue());
//		toolbar.add(lblSession);
//		add(toolbar, BorderLayout.NORTH);
//		
//		// table
//		tblInventory = new JTable();
//		tblInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		tblInventory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				if ((tblInventory.getSelectedRow() >= 0)) {};
// 			}
//		});
//		add(new JScrollPane(tblInventory), BorderLayout.CENTER);
//		
//		pack();
//		
//	}
//	
//	public void setTableModel(TableModel model) {
//		tblInventory.setModel(model);
//	}
//
//	public void setSession(Session sessionModel) {
//		lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
//	}
//}
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
import controller.DisplayClientsController;
import controller.DisplayContainersController;

public class DisplayClientsView extends JFrame {

	private static final long serialVersionUID = 2888791217183873215L;
	private DisplayClientsController controller;
	private JTable tblInventory;
	private JLabel lblSession;
	
	public DisplayClientsView(DisplayClientsController controller) {
		this.controller = controller;
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Containers");
		setPreferredSize(new Dimension(800, 600));
		
		// buttons
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.filter();
			}
		});
		
		
	
		
		JButton btnAddClient = new JButton("Add Client"); // add code to delete from database so changes are not lost upon exit
		
		btnAddClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addClient();
				
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
		toolbar.add(btnAddClient);
		toolbar.add(btnRefresh);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(lblSession);
		add(toolbar, BorderLayout.NORTH);
		
		// table
		tblInventory = new JTable();
		tblInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		add(new JScrollPane(tblInventory), BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void setTableModel(TableModel model) {
		tblInventory.setModel(model);
	}

	public void setSession(Session sessionModel) {
		lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
	}
	
}