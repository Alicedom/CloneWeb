package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class BookView extends JFrame implements IBookView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelContent;
	private JTextArea info;
	private JCheckBox cbChangeHTML; 
	private JButton btnShowFull, btnPrintPDF;
	public BookView() {
		setLayout(new BorderLayout(5,5));
		
		cbChangeHTML = new JCheckBox("Change view to HTML");
		btnShowFull = new JButton("Show Full");
		btnPrintPDF = new JButton("Print PDF");
		info = new JTextArea();

		JPanel panel = new JPanel(new GridLayout(1,0));
		panel.add(cbChangeHTML);
		panel.add(btnShowFull);
		panel.add(btnPrintPDF);
		
		add(panel, BorderLayout.NORTH);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        	 
		panelContent = new JPanel(new GridLayout(0, 1));
		JScrollPane scroll = new JScrollPane(panelContent);
		splitPane.setTopComponent(scroll);
	 	
		info = new JTextArea();
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		scroll = new JScrollPane(info);
        splitPane.setBottomComponent(scroll);

        
        splitPane.setDividerLocation(100); 
        splitPane.setPreferredSize(new Dimension(500, 300));
        add(splitPane, BorderLayout.CENTER);
        
		
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		validate();
		repaint();	
		
	}
	@Override
	public void addClickListtenerForButtonChoice(ActionListener listener) {
		btnShowFull.addActionListener(listener);	
	}
	@Override
	public void addClickListtenerForButtonPrintToPDF(ActionListener listener) {
		btnPrintPDF.addActionListener(listener);	
	}
	
	public boolean getCheckBoxChangeToHTML() {
		return this.cbChangeHTML.isSelected();
	}
	 
	public JPanel getPanelContent() {
		return this.panelContent;
	}
	
	public void setTextArea(String str) {
		this.info.setText(str);
	}
}