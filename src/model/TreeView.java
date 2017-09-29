package model;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class TreeView extends JPanel implements TreeSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document doc=null;
	private JTree tree;

	public TreeView(File dir) {
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
					contentFile.setElement(getDocToFile(file));
				}

				curDir.add(new DefaultMutableTreeNode(contentFile));
			}
		}
		return curDir;
	}

	private Document getDocToFile(File dir){


		//***************Chuyen thread de load********************//
		try {
			System.out.println(dir.getName());
			doc = Jsoup.parse(dir, "UTF-8");
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return doc;
	}


	@Override
	public void valueChanged(TreeSelectionEvent e) {

		DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				tree.getLastSelectedPathComponent();
//		TreePath[] jtree= tree.getSelectionPaths();
//		System.out.println(jtree.length + jtree.toString());

		if (node == null) return;

		if (node.isLeaf()) {
			Content leaf = (Content) node.getUserObject();
			System.out.println( leaf.getAttribute());
		}
	}


	public static void main(String[] av) {

		JFrame frame = new JFrame();
		Container cp = frame.getContentPane();
		cp.add(new TreeView(new File(".")));
		frame.pack();
		frame.setVisible(true);
		frame.setSize(300, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 

}
