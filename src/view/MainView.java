package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainView extends JFrame implements IMainView, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JMenuItem itOpen,itSave,itImport,itExport;
	JMenuItem itAdd,itDel;
	private JPanel panelLeft_Result;
	
	JComboBox<String> cbSelection = 
			new JComboBox<String>(new String[]{"Class","Id","TagName"});
	JTextField tfInput = new JTextField("Input",20);
	JCheckBox ckGetFirst = new JCheckBox("Get First", true);
	JButton btnFind = new JButton("Find");
	JButton btnFillAllLink = new JButton("Fill All Link");
	public MainView() {
		//********Add menu************
		setLayout(new BorderLayout());
		JMenuBar mainMennu = new JMenuBar();
		add(mainMennu, BorderLayout.NORTH);
		
		JMenu menuFile= new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_F);
		mainMennu.add(menuFile);
		
		itOpen = new JMenuItem("Open");
		itOpen.setToolTipText("Open file HTML to parse");
		itOpen.setMnemonic(KeyEvent.VK_O);
		menuFile.add(itOpen);
		
		itSave = new JMenuItem("Save");
		itSave.setToolTipText("Save HTML result to file");
		itSave.setMnemonic(KeyEvent.VK_S);
		menuFile.add(itSave);
		
		itImport = new JMenuItem("Import");
		itImport.setToolTipText("Import execute Code");
		itImport.setMnemonic(KeyEvent.VK_I);
		menuFile.add(itImport);
		
		itExport = new JMenuItem("Export");
		itExport.setToolTipText("Save execute Code");
		itExport.setMnemonic(KeyEvent.VK_E);
		menuFile.add(itExport);
		
		JMenu menuEdit= new JMenu("Edit");
		menuEdit.setMnemonic(KeyEvent.VK_T);
		mainMennu.add(menuEdit);
		
		itAdd = new JMenuItem("Add");
		itAdd.setToolTipText("Add attribute to element");
		itAdd.setMnemonic(KeyEvent.VK_A);
		menuEdit.add(itAdd);
		
		itDel = new JMenuItem("Del");
		itDel.setToolTipText("Del attribute to element");
		itDel.setMnemonic(KeyEvent.VK_D);
		menuEdit.add(itDel);
		
		JMenu menuHelp= new JMenu("Help");
		menuHelp.setMnemonic(KeyEvent.VK_H);
		mainMennu.add(menuHelp);
		
		//*******Add panel***********
		JPanel panelRight_Excercute = new JPanel();
		panelRight_Excercute.setLayout(new BorderLayout());
		panelRight_Excercute.setBorder(BorderFactory.createTitledBorder(
                "Excercise View"));
		add(panelRight_Excercute, BorderLayout.EAST);
		
		JPanel panelCode = new JPanel();
		panelCode.setLayout(new GridLayout(0, 1));
		panelRight_Excercute.add(panelCode, BorderLayout.NORTH);
		
		JPanel panel1 = new JPanel();
		panel1.setSize(50,50);
		panel1.setBorder(BorderFactory.createTitledBorder(
                "Choose Attribute"));
		panel1.add(cbSelection);
		panel1.add(ckGetFirst);
		panel1.add(tfInput);
		panel1.add(btnFind);
		panelCode.add(panel1);
				
		JPanel panel3 = new JPanel();
		panel3.setSize(500, 500);
		panelCode.add(panel3, BorderLayout.CENTER);
				
		panelLeft_Result = new JPanel();
		panelLeft_Result.setLayout(new BorderLayout());
		panelLeft_Result.setBorder(BorderFactory.createTitledBorder(
                "Result View"));
		add(new JScrollPane(panelLeft_Result), BorderLayout.CENTER);
		

		JPanel panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createTitledBorder(
                "Fill All Link"));
		panel2.add(btnFillAllLink);
		add(panel2, BorderLayout.SOUTH);
		
		
		setSize(1000, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
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

	@Override
	public String getSelection() {
		return cbSelection.getSelectedItem().toString();
	}

	@Override
	public String getElement() {
		return tfInput.getText();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

	@Override
	public void addClickListtenerForItemMenu(Action action) {
		
	}

	@Override
	public void addFindAllLinkButtonListenner(ActionListener listenner) {
		btnFillAllLink.addActionListener(listenner);
		
	}

	@Override
	public Container getPanelLeft_Result() {
		// TODO Auto-generated method stub
		return this.panelLeft_Result;
	}
	
	
}
