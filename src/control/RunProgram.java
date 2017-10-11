package control;

import java.awt.EventQueue;

public class RunProgram {
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        	new MainViewController();
	        }
	    });

	}
}
