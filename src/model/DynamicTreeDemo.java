/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package model;

/*
 * This code is based on an example provided by Richard Stanford, 
 * a tutorial reader.
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import control.BookViewController;

public class DynamicTreeDemo extends JPanel 
implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String ADD_COMMAND = "add";
	private static String REMOVE_COMMAND = "remove";
	private static String CLEAR_COMMAND = "clear";
	private static String VIEW_COMMAND = "view";
	private DynamicTree treePanel;
	LinkedList<Content> listContent;

	public DynamicTreeDemo(File dir) {
		super(new BorderLayout());

		//Create the components.
		treePanel = new DynamicTree();
		listContent = new LinkedList<Content>();
		populateTree(treePanel, null,dir);

		JButton addButton = new JButton("Add");
		addButton.setActionCommand(ADD_COMMAND);
		addButton.addActionListener(this);

		JButton removeButton = new JButton("Remove");
		removeButton.setActionCommand(REMOVE_COMMAND);
		removeButton.addActionListener(this);

		JButton clearButton = new JButton("Clear");
		clearButton.setActionCommand(CLEAR_COMMAND);
		clearButton.addActionListener(this);
		
		JButton viewButton = new JButton("View");
		viewButton.setActionCommand(VIEW_COMMAND);
		viewButton.addActionListener(this);
		

		//Lay everything out.
		treePanel.setPreferredSize(new Dimension(500, 500));
		add(treePanel, BorderLayout.CENTER);

		JPanel panel = new JPanel(new GridLayout(0,4));
		panel.add(addButton);
		panel.add(removeButton); 
		panel.add(clearButton);
		panel.add(viewButton);
		add(panel, BorderLayout.NORTH);
		
	}

	public void populateTree(DynamicTree treePanel, DefaultMutableTreeNode parent, File dir) {
		Content content= new Content(dir.getName());
		DefaultMutableTreeNode p
			= treePanel.addObject(parent, content, true);

		List<File> files = new ArrayList<File>(Arrays.asList(dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {

				String name = pathname.getName().toLowerCase();
				return name.endsWith(".html") || (pathname.isDirectory() && !("System Volume Information".equalsIgnoreCase(name)));

			}
		})));

		Collections.sort(files);

		for (File file : files) {
			if (file.isDirectory()) {
				//				treePanel.addObject(p1, c1Name);
				populateTree(treePanel, p, file);
			}
		}
		for (File file : files) {

			if (file.isFile()) {
				Content contentFile = new Content(file.getName());

				if(file.getName().toLowerCase().endsWith(".html")) {
					contentFile.setElement(Tool.getDocToFile(file));
					contentFile.setPath(file);
					treePanel.addObject(p, contentFile);
					listContent.add(contentFile);
				}

				
			}
		}

	}

	

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (ADD_COMMAND.equals(command)) {
			//Add button clicked
			populateTree(treePanel, null,Tool.openJChooseFile(treePanel, "Open"));
			
		} else if (REMOVE_COMMAND.equals(command)) {
			//Remove button clicked
			treePanel.removeCurrentNode();
		} else if (CLEAR_COMMAND.equals(command)) {
			//Clear button clicked.
			treePanel.clear();
		}else if (VIEW_COMMAND.equals(command)) {
			//View content.
			new BookViewController(listContent);
		}
	}

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("DynamicTreeDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create and set up the content pane.
		DynamicTreeDemo newContentPane = new DynamicTreeDemo(new File("."));
		newContentPane.setOpaque(true); //content panes must be opaque
		frame.setContentPane(newContentPane);
 
		//Display the window.
		frame.pack();
		frame.setVisible(true);
		frame.validate();
		frame.repaint();
	}

	
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
