package test;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ShowTree extends JFrame implements MouseListener{
	
	private JPanel panel;
	public ShowTree() {
		LinkedList list = new LinkedList();
		
		//input test
		for(int i=0;i<10;i++){
			if(i%3==0){
				list.add(""+i);
				continue;
			}else if(i%2==0){
				list.add("*"+i);
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
				showListPanel((LinkedList)i," | "+deep);
			}
			else{
				System.out.println(i.getClass().getName());
				JLabel lb = new JLabel(deep+" |_" + (String)i);
				lb.addMouseListener(this);
				panel.add(lb);
			}
		}
	}


	private Object getParent(int i){
		
		return null;
	}

	public static void main(String[] args) {
		new ShowTree();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		String str;
		
		str= ((JLabel) e.getComponent()).getText();
		System.out.println("2"+str);
		str=((JLabel) e.getSource()).getText();
		System.out.println("3"+str);
				
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
