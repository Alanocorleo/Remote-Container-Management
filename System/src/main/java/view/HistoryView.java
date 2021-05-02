package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.HistoryController;

public class HistoryView extends JFrame {
	
	private static final long serialVersionUID = 989075282041187452L;
	private HistoryController historyController;
	//private JLabel lblSession;
	
	

	public HistoryView(HistoryController controller) {
		
		this.historyController = controller;
		initGUI();
	}



	/**
	 * this is the GUI window that shows the table of container history
	 * uses the matrix that its controller generates
	 * 
	 */
	private void initGUI() {
		
		
		String[] columns = new String[] {
	            "date", "temperature", "humidity", "pressure", "position"
	        };
		
		Object[][] data = historyController.history();
        
        
        if (data == null) {
        	//System.out.print("sdgiuoanbh");
        	new IdFormatErrorGUI();
        } else {
        	//System.out.print("WOOOORKS");
        	//create table with data
            JTable table = new JTable(data, columns);
             
            //add the table to the frame
            add(new JScrollPane(table));
             
            //this.setTitle("Table");
            //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
            setVisible(true);
            
            
            setTitle("History Table");
    		setPreferredSize(new Dimension(800, 600));
    		
    		
    		//JPanel panel = new JPanel();
            
            //panel.add(table);
            //add(panel);
			
			pack();
        	
        }
        
        
		
		
		
		
	}
	
	
	//public void setSession(Session sessionModel) {
		//lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
	//}
	
	
	
	
	
}
