package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainView extends JFrame implements IMainView, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JMenuItem itOpen,itSave,itImport,itExport;
	
	JTextField tfInput = new JTextField("Input",20);
	JComboBox<String> cbSelection = new JComboBox<String>(new String[]{"Class","Id","TagName"});
	JButton btnFind = new JButton("Find");
	
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
		itSave.setToolTipText("Save result parse to file");
		itSave.setMnemonic(KeyEvent.VK_S);
		menuFile.add(itSave);
		
		itImport = new JMenuItem("Import");
		itImport.setToolTipText("Import Code to execuse");
		itImport.setMnemonic(KeyEvent.VK_I);
		menuFile.add(itImport);
		
		itExport = new JMenuItem("Export");
		itExport.setToolTipText("Save excercise Code");
		itExport.setMnemonic(KeyEvent.VK_E);
		menuFile.add(itExport);
		
		//*******Add panel***********
		JPanel panel1 = new JPanel();
		panel1.add(cbSelection);
		panel1.add(tfInput);
		panel1.add(btnFind);
		
		JPanel panel2 = new JPanel();
		
		JPanel panelCode = new JPanel();
		panelCode.setBorder(BorderFactory.createTitledBorder(
                "Excercise View"));
		panelCode.add(panel1);
		panelCode.add(panel2);
		add(panelCode, BorderLayout.EAST);
		
		JPanel panelResult = new JPanel();
		panelResult.setBorder(BorderFactory.createTitledBorder(
                "Result View"));
		add(panelResult, BorderLayout.CENTER);
		
		
		setSize(1000, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
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
	
}
