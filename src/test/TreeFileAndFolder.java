package test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeFileAndFolder {
  public static void main(String[] av) {

    JFrame frame = new JFrame();
    Container cp = frame.getContentPane();
    cp.add(new DriveTree(new File(".")));
    frame.pack();
    frame.setVisible(true);
    frame.setSize(300, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
} 

class DriveTree extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DriveTree(File dir) {
        setLayout(new BorderLayout());
        JTree tree = new JTree(addNodes(null, dir));
        add(tree);
    }

    DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
        String curPath = dir.getPath();
        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
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
                curDir.add(new DefaultMutableTreeNode(file));
            }
        }
        return curDir;
    }


}