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
import controller.DisplayJourneysCompanyController;

public class DisplayJourneysCompanyView extends JFrame {

	private static final long serialVersionUID = 4212860552112409964L;
	private DisplayJourneysCompanyController controller;
	private JTable tblInventory;
	private JLabel lblSession;
	
	public DisplayJourneysCompanyView(DisplayJourneysCompanyController controller) {
		this.controller = controller;
		initGUI();
	}
	
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
	
		JButton btnShowContainers = new JButton("Show containers");
		btnShowContainers.setEnabled(false);
		btnShowContainers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.showContainers(tblInventory.getSelectedRow());
			}
		});
		
		JButton btnPosition = new JButton("Update position");
		btnPosition.setEnabled(false);
		btnPosition.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.updatePosition(tblInventory.getSelectedRow());
			}
		});
		
		JButton btnDepart = new JButton("Set departure date");
		btnDepart.setEnabled(false);
		btnDepart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setDeparture(tblInventory.getSelectedRow());
			}
		});
		
		JButton btnArrived = new JButton("Set arrival to today");
		btnArrived.setEnabled(false);
		btnArrived.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.arrived(tblInventory.getSelectedRow());
			}
		});
		
		JButton btnAdd = new JButton("Add new journey");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.add();
			}
		});
		
		JButton btnDelete = new JButton("Complete and Remove");
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
		toolbar.add(btnShowContainers);
		toolbar.add(btnPosition);
		toolbar.add(btnDepart);
		toolbar.add(btnArrived);
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
				btnShowContainers.setEnabled((tblInventory.getSelectedRow() >= 0));
				btnPosition.setEnabled((tblInventory.getSelectedRow() >= 0));
				btnDepart.setEnabled((tblInventory.getSelectedRow() >= 0));
				btnArrived.setEnabled((tblInventory.getSelectedRow() >= 0));
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

	public void setSession(Session sessionModel) {
		lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
	}

	public void showError() {
		JOptionPane.showMessageDialog(this, "Both origin and destination are required to create a new journey.", "Neccessary parameters are not entered.", JOptionPane.ERROR_MESSAGE);
	}

	public void showError2() {
		JOptionPane.showMessageDialog(this, "Text field cannot be left empty", "Position unchanged", JOptionPane.ERROR_MESSAGE);
	}
	
	public void showError3() {
		JOptionPane.showMessageDialog(this, "Date should be of the format DD/MM/YYYY", "Incorrect date format", JOptionPane.ERROR_MESSAGE);		
	}
	
}
