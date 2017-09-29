package test;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*from ww w .  jav  a2s .co  m*/
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JTreeParseDom {

  public static void main(String[] args) {
    JFrame f = new JFrame();

    f.setSize(300, 500);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel pan = new JPanel(new GridLayout(1, 1));
    final XmlJTree myTree = new XmlJTree(null);
    f.add(new JScrollPane(myTree));
    JButton button = new JButton("Choose file");
    button.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		  JFileChooser chooser = new JFileChooser();
		  FileNameExtensionFilter filter = new FileNameExtensionFilter("XML file",
		      "xml");
		  chooser.setFileFilter(filter);
		  int returnVal = chooser.showOpenDialog(null);
		  if (returnVal == JFileChooser.APPROVE_OPTION) {
		    myTree.setPath(chooser.getSelectedFile().getAbsolutePath());
		  }
		}
	});
    pan.add(button);
    f.add(pan, BorderLayout.SOUTH);
    f.setVisible(true);
  }
}

class XmlJTree extends JTree {

  DefaultTreeModel dtModel = null;

  public XmlJTree(String filePath) {
    if (filePath != null)
      setPath(filePath);
  }

  public void setPath(String filePath) {
    Node root = null;
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(filePath);
      root = (Node) doc.getDocumentElement();
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "Can't parse file", "Error",
          JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (root != null) {
      dtModel = new DefaultTreeModel(builtTreeNode(root));
      this.setModel(dtModel);
    }
  }

  private DefaultMutableTreeNode builtTreeNode(Node root) {
    DefaultMutableTreeNode dmtNode;

    dmtNode = new DefaultMutableTreeNode(root.getNodeName());
    NodeList nodeList = root.getChildNodes();
    for (int count = 0; count < nodeList.getLength(); count++) {
      Node tempNode = nodeList.item(count);

      if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
        if (tempNode.hasChildNodes()) {
          dmtNode.add(builtTreeNode(tempNode));
        }
      }
    }
    return dmtNode;
  }

}