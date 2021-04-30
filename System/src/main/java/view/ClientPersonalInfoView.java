package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import controller.ClientPersonalInfoController;
import controller.Session;
import utils.GridBagLayoutUtils;

public class ClientPersonalInfoView extends JFrame {

	private static final long serialVersionUID = 989075282041187452L;
	private ClientPersonalInfoController controller;
	private JTable tblInventory;
	private JLabel lblSession;



	
	
	public ClientPersonalInfoView(ClientPersonalInfoController controller) {
		this.controller = controller;
		initGUI();
	}
	
	private void initGUI() {
		
		
		setTitle("Account Details");
		setPreferredSize(new Dimension(800, 600));
		
		// buttons


		
		// toolbar
		lblSession = new JLabel();
	
	
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		
		setLayout(new GridBagLayout());
		
		JTextField textFieldName = new JTextField(controller.getClient().getfirstName(), 20);
		JTextField textFieldNameL = new JTextField(controller.getClient().getlastName(), 20);
		JTextField textFieldDate = new JTextField(controller.getClient().getBirthDate(), 20);
		JTextField textFieldEmail = new JTextField(controller.getClient().getEmail(), 20);
		JTextField textFieldPhone = new JTextField(controller.getClient().getPhoneNumber(), 20);
		
	
		JTextField txtPass = new JTextField(controller.getClient().getPassword(), 20);
		JButton updateBtn = new JButton("Save Changes");
		
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.updateInfo(textFieldName.getText(),textFieldNameL.getText(), textFieldDate.getText(), textFieldEmail.getText(), textFieldPhone.getText(), txtPass.getText());
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		add(new JLabel("Account details are listed below:"), GridBagLayoutUtils.constraint(0, 0, 5));
		
		add(new JLabel("First Name:"), GridBagLayoutUtils.constraint(0, 1, 5));
		add(textFieldName, GridBagLayoutUtils.constraint(1, 1, 5));
		
		add(new JLabel("Last Name:"), GridBagLayoutUtils.constraint(0, 2, 5));
		add(textFieldNameL, GridBagLayoutUtils.constraint(1, 2, 5));
		
		add(new JLabel("Birth Date:"), GridBagLayoutUtils.constraint(0, 3, 5));
		add(textFieldDate, GridBagLayoutUtils.constraint(1, 3, 5));
		
		add(new JLabel("Email:"), GridBagLayoutUtils.constraint(0, 4, 5));
		add(textFieldEmail, GridBagLayoutUtils.constraint(1, 4, 5));
		
		add(new JLabel("Phone Number:"), GridBagLayoutUtils.constraint(0, 5, 5));
		add(textFieldPhone, GridBagLayoutUtils.constraint(1, 5, 5));
		
		add(new JLabel("Password:"), GridBagLayoutUtils.constraint(0, 6, 5));
		add(txtPass, GridBagLayoutUtils.constraint(1, 6, 5));
		

		
		add(updateBtn, GridBagLayoutUtils.constraint(1, 7, 5));
		
		pack();
		setLocationRelativeTo(null);
		/*
		panel.add(btnNew);
		//panel.add(btnDelete);
	   	//panel.add(updateBtn);
		panel.add(Box.createHorizontalGlue());
		
		add(panel, BorderLayout.CENTER);
		setVisible(true);
		pack();
		*/
	}
	
	
	
		/*
		// table
		tblInventory = new JTable();
		JTextField textField = new JTextField(20);
		tblInventory.add(textField);
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
	
	*/

	public void setSession(Session sessionModel) {
		lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
	}
	
	
	
	public void showError(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Updating information", JOptionPane.INFORMATION_MESSAGE);
	}
}

