package test;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ShowPanel extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	public ShowPanel() {
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

//		Container cp = this.getContentPane();
//		cp.setLayout(new FlowLayout());
//		cp.add(new JLabel("HI"));
		
		panel = new JPanel();
		int width = list.size();
		System.out.println(width);
		panel.setLayout(new GridLayout(0,1));
		showListPanel(list,"");

		JScrollPane scpn = new JScrollPane(panel);
		scpn.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scpn);
		
		setTitle("Show Panel");
		
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
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
		new ShowPanel();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
