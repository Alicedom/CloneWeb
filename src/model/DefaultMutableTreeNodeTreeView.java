package model;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class DefaultMutableTreeNodeTreeView extends JPanel implements TreeSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document doc=null;
	private JTree tree;

	public DefaultMutableTreeNodeTreeView(File dir) {
		setLayout(new BorderLayout());
		tree = new JTree(addNodes(null, dir));
		add(tree);

		tree.getSelectionModel().setSelectionMode
		(TreeSelectionModel.SINGLE_TREE_SELECTION);

		
		//Listen for when the selection changes.
		tree.addTreeSelectionListener(this);


	}

	DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
		Content content = new Content(dir.getName());
		DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(content);
		if (curTop != null) {
			curTop.add(curDir);
		}

		List<File> files = new ArrayList<File>(Arrays.asList(dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {

				String name = pathname.getName().toLowerCase();
				return name.endsWith(".html") || name.endsWith(".txt") || (pathname.isDirectory() && !("System Volume Information".equalsIgnoreCase(name)));

			}
		})));

		Collections.sort(files);

		for (File file : files) {
			if (file.isDirectory()) {
				addNodes(curDir, file);
			}
		}
		for (File file : files) {

			if (file.isFile()) {
				Content contentFile = new Content(file.getName());

				if(file.getName().toLowerCase().endsWith(".html")) {
					contentFile.setElement(Tool.getDocToFile(file));
				}

				curDir.add(new DefaultMutableTreeNode(contentFile));
			}
		}
		return curDir;
	}



	@Override
	public void valueChanged(TreeSelectionEvent e) {

		DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				tree.getLastSelectedPathComponent();
		
		if (node == null) return;

		if (node.isLeaf()) {
			Content leaf = (Content) node.getUserObject();
			System.out.println( leaf.getAttribute());
		}
	}

	
	static DefaultMutableTreeNodeTreeView treeview;
	static Container cp; 
	static JButton btnChoose;
	public static void main(String[] av) {

		JFrame frame = new JFrame();
		cp = frame.getContentPane();
		cp.setLayout(new BorderLayout());
		treeview = new DefaultMutableTreeNodeTreeView(new File("."));
		cp.add(treeview, BorderLayout.CENTER);

		btnChoose = new JButton("choose");
		cp.add(btnChoose, BorderLayout.NORTH);
		btnChoose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File file = openJChooseFile("Open");
				  
				treeview = new DefaultMutableTreeNodeTreeView(file);
				cp.add(treeview, BorderLayout.CENTER);
				System.gc();
				cp.validate();
				cp.repaint();
			}
		});

		frame.pack();
		frame.setVisible(true);
		frame.setSize(300, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	protected static File openJChooseFile(String string) {
		File file = null;

		JFileChooser fc= new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showDialog((Component) cp, string);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			System.out.println("Choose: "+file.getPath());
		}

		return file;

	} 

}
