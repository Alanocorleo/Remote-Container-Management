package view;


	import java.awt.Dimension;
	import java.awt.GridBagLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JTextField;
	import javax.swing.SwingConstants;

	import controller.CompanyPersonalInfoController;
	import controller.Session;
	import utils.GridBagLayoutUtils;

	public class CompanyPersonalInfoView extends JFrame {

		private static final long serialVersionUID = 989075282041187452L;
		private CompanyPersonalInfoController controller;
		
		private JLabel lblSession;



		
		
		public CompanyPersonalInfoView(CompanyPersonalInfoController controller) {
			this.controller = controller;
			initGUI();
		}
		
		private void initGUI() {
			
			
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Account Details");
			setPreferredSize(new Dimension(800, 600));
			
			// buttons
	
			
			// toolbar
			lblSession = new JLabel();
		
		
			lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
			
			setLayout(new GridBagLayout());
			
			JTextField textFieldName = new JTextField(20);
			JTextField textFieldNameL = new JTextField( 20);
			JTextField textFieldDate = new JTextField(20);
			JTextField textFieldEmail = new JTextField( 20);
			JTextField textFieldPhone = new JTextField(20);
			
		
			JTextField txtPass = new JTextField(20);
			JButton updateBtn = new JButton("Save Changes");
			
			updateBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						controller.createInfo(textFieldName.getText(),textFieldNameL.getText(), textFieldDate.getText(), textFieldEmail.getText(), textFieldPhone.getText(), txtPass.getText());
						
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
			
		}


		public void setSession(Session sessionModel) {
			lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
		}
		
		
		
		public void showError(String errorMessage) {
			JOptionPane.showMessageDialog(this, errorMessage, "Adding client", JOptionPane.INFORMATION_MESSAGE);
		}
	}




