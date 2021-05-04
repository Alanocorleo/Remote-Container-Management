package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.HistoryController;

/**
 * This class is responsible for displaying a window with a table of container's 
 * history for the clients. It extends JFrame to get advantage of the components 
 * that can be added to the window.
 */

public class HistoryView extends JFrame {
	
	private static final long serialVersionUID = 989075282041187452L;
	private HistoryController historyController;

	/**
	 * This constructor sets HistoryController, and calls for GUI initialization
	 * and window displaying.
	 * @param controller
	 */
	public HistoryView(HistoryController controller) {
		this.historyController = controller;
		initGUI();
	}

	/**
	 * This method initializes a GUI, and displays a corresponding window.
	 */
	private void initGUI() {
		String[] columns = new String[] {
	            "date", "temperature", "humidity", "pressure", "position"
	        };
		
		Object[][] data = historyController.history();
        
        if (data == null) {
        	new IdFormatErrorGUI();
        	
        } else {
        	// create table with data
            JTable table = new JTable(data, columns);
             
            // add the table to the frame
            add(new JScrollPane(table));
            
            setTitle("History Table");
    		setPreferredSize(new Dimension(800, 600));
    		
			pack();
			setLocationRelativeTo(null);
			
            setVisible(true);
        }  
	}
	
}
