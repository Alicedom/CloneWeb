package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainView extends JFrame implements IMainView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JMenuItem itOpen,itSave,itImport,itExport,itAdd,itDel;

	JButton btnFind;
	
	public static JTextField tfInput;
	public static JCheckBox ckGetFirst;
	public static JComboBox<String> cbSelection;
	public static JPanel panelTreeView;
	public static JPanel panelCode;
	
	public MainView() {
		//********Add menu************
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());

		JMenuBar mainMennu = new JMenuBar();
		cp.add(mainMennu, BorderLayout.NORTH);
		
		JMenu menu= new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		mainMennu.add(menu);
		
		itOpen = new JMenuItem("Open");
		itOpen.setToolTipText("Open file HTML to parse");
		itOpen.setMnemonic(KeyEvent.VK_O);
		menu.add(itOpen);
		
		itSave = new JMenuItem("Save");
		itSave.setToolTipText("Save HTML result to file");
		itSave.setMnemonic(KeyEvent.VK_S);
		menu.add(itSave);
		
		itImport = new JMenuItem("Import");
		itImport.setToolTipText("Import execute Code");
		itImport.setMnemonic(KeyEvent.VK_I);
		menu.add(itImport);
		
		itExport = new JMenuItem("Export");
		itExport.setToolTipText("Save execute Code");
		itExport.setMnemonic(KeyEvent.VK_E);
		menu.add(itExport);
		
		menu= new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_T);
		mainMennu.add(menu);
		
		itAdd = new JMenuItem("Add");
		itAdd.setToolTipText("Add attribute to element");
		itAdd.setMnemonic(KeyEvent.VK_A);
		menu.add(itAdd);
		
		itDel = new JMenuItem("Del");
		itDel.setToolTipText("Del attribute to element");
		itDel.setMnemonic(KeyEvent.VK_D);
		menu.add(itDel);
		
		menu= new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		mainMennu.add(menu);
		
		//*******Add panel***********
		cbSelection = new JComboBox<String>(new String[]{"Class","Id","TagName"});
		ckGetFirst = new JCheckBox("Get First", true);
		tfInput = new JTextField("Input",20);
		btnFind = new JButton("Find");
		
		JPanel panel = new JPanel();
		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(
                "Choose Attribute"));
		panel.add(cbSelection);
		panel.add(ckGetFirst);
		panel.add(tfInput);
		panel.add(btnFind);
		cp.add(panel,BorderLayout.SOUTH);
				
		panelTreeView = new JPanel();
		panelTreeView.setLayout(new BorderLayout());
		panelTreeView.setSize(500,500);
		panelTreeView.setBorder(BorderFactory.createTitledBorder(
                "Result View"));
		cp.add(panelTreeView, BorderLayout.CENTER);
		
		panelCode = new JPanel();
		panelCode.setLayout(new FlowLayout());
		panelCode.setSize(500,500);
		panelCode.setBorder(BorderFactory.createTitledBorder(
                "Excercise View"));
		cp.add(panelCode, BorderLayout.EAST);
		
		
		setSize(1000, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		validate();
		repaint();
		
		cbSelection.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbSelection.getSelectedIndex() == 1) {					
					ckGetFirst.setSelected(true);
					ckGetFirst.setEnabled(false);
				}else {
					ckGetFirst.setSelected(true);
					ckGetFirst.setEnabled(true);
				}
				
			}
		});
	}

	@Override
	public void addClickListtenerForSaveItemMenu(ActionListener listener) {
		itSave.addActionListener(listener);
		
	}

	@Override
	public void addClickListtenerForOpenItemMenu(ActionListener listener) {
		itOpen.addActionListener(listener);
	}

	@Override
	public void addClickListtenerForImportItemMenu(ActionListener listener) {
		itImport.addActionListener(listener);
		
	}

	@Override
	public void addClickListtenerForExportItemMenu(ActionListener listener) {
		itExport.addActionListener(listener);
		
	}

	@Override
	public void addClickListtenerForFindButtun(ActionListener listener) {
		btnFind.addActionListener(listener);
		
	}

}
