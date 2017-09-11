package control;

import javax.swing.SwingUtilities;

public class RunProgram {
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            //Turn off metal's use of bold fonts
//	            UIManager.put("swing.boldMetal", Boolean.FALSE); 
	            new CloneWebProgram();
	        }
	    });

	}
}
