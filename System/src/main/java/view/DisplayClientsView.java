
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
import javax.swing.table.TableModel;

import controller.Session;
import controller.DisplayClientsController;

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
		
		
	
		
		JButton btnAddClient = new JButton("Add Client"); 
		
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