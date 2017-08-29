package test;

import java.awt.GridLayout;
import java.awt.Label;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TestJScrollPanel extends JFrame {
	
	private JPanel panel;
	public TestJScrollPanel() {
		LinkedList list = new LinkedList();
		
		//input test
		for(int i=0;i<10;i++){
			if(i%3==0){
				list.add(""+i);
				continue;
			}else if(i%2==0){
				list.add("|"+i);
				continue;
			}
				
			LinkedList childlist = new LinkedList();
			for(int j=0;j<10;j++){
				if(j%3==1)
					continue;
				for(int k=0;k<3;k++){
					if(k%2==0)
						continue;
					LinkedList childchildlist = new LinkedList();
					childchildlist.add(String.valueOf(i)+String.valueOf(j)+String.valueOf(k));
					childlist.add(childchildlist);
				}
				childlist.add(String.valueOf(i)+String.valueOf(j));
			}
			
			list.add(childlist);
		}
		System.out.println(list.toString());

		
		
		
		panel = new JPanel();
		
		panel.setLayout(new GridLayout(0,1));
		showListPanel(list,"");

		JScrollPane scroll = new JScrollPane(panel);
		add(scroll);
		
		setVisible(true);
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}
	
	private void showListPanel(LinkedList list, String deep){
		for (Object i: list){
			if ( i instanceof LinkedList){
				showListPanel((LinkedList)i,deep+"__");
			}
			else{
			panel.add(new Label(deep + (String)i));
			}
		}
	}

	public static void main(String[] args) {
		new TestJScrollPanel();
	}
}
