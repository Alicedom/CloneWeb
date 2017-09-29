package test;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class TreeFolder extends JFrame {

  public static void main(String[] args) {
    new TreeFolder().setVisible(true);
  }

  private JTree tree;

  public TreeFolder() {
    this.tree = new JTree();
    this.add(this.tree);

    File fileRoot = new File("/home/khanhpq/Downloads");

    DefaultMutableTreeNode root = new DefaultMutableTreeNode(fileRoot);
    DefaultTreeModel model = new DefaultTreeModel(root);

    File[] subItems = fileRoot.listFiles();
    for (File file : subItems) {
      root.add(new DefaultMutableTreeNode(file));
    }

    this.tree.setModel(model);
    
    this.pack();
  }
}