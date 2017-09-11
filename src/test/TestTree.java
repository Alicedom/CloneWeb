package test;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;

public class TestTree  extends JPanel
implements TreeSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTree tree=null;
	public TestTree() {
		LinkedList top = new LinkedList();
		top = createNode();
		System.out.println(top.toString());
		tree = new JTree(top.toArray());
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);
        
        JScrollPane treeView = new JScrollPane(tree);
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);

        Dimension minimumSize = new Dimension(100, 50);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100); 
        splitPane.setPreferredSize(new Dimension(500, 300));

        //Add the split pane to this panel.
        add(splitPane);


	}
	private LinkedList createNode() {
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
		return list;
//		System.out.println(list.toString());

	}
	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		
	}
	  private static void createAndShowGUI() {
	        if (false) {
	            try {
	                UIManager.setLookAndFeel(
	                    UIManager.getSystemLookAndFeelClassName());
	            } catch (Exception e) {
	                System.err.println("Couldn't use system look and feel.");
	            }
	        }

	        //Create and set up the window.
	        JFrame frame = new JFrame("TreeDemo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Add content to the window.
	        frame.add(new TestTree());

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        //Schedule a job for the event dispatch thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
	    }


}
