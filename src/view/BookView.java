package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BookView extends JFrame implements IBookView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JPanel panelContent, panelInfo;
	public JButton btnChangeText;
	public BookView() {
		setLayout(new BorderLayout());
		
		panelContent = new JPanel(new GridLayout(0, 1));
		add(panelContent, BorderLayout.WEST);
		
		panelInfo = new JPanel(new FlowLayout());
		add(panelInfo, BorderLayout.CENTER);

		btnChangeText = new JButton();
		
		setSize(1000, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		validate();
		repaint();	
		
	}
	@Override
	public void addClickListtenerForButtonChangeText(ActionListener listener) {
		btnChangeText.addActionListener(listener);
		
	}
	
	
	
}