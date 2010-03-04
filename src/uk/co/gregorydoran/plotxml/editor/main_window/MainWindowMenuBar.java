package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindowMenuBar extends JMenuBar {

	private static final long serialVersionUID = -7568078151785118206L;
		
	private JMenu menuFile;
	private JMenuItem menuItemExit;
	private JMenuItem menuItemNew;
	private JMenuItem menuItemSave;
	private JMenuItem menuItemSaveAs;
	private JMenuItem menuItemOpen;
	
	private JMenu menuEdit;
	private JMenuItem menuAddDecision;
	private JMenuItem menuItemCut;
	private JMenuItem menuItemCopy;
	private JMenuItem menuItemPaste;
	
	private JMenu menuView;
	private JMenuItem menuItemShowToolbox;

	public MainWindowMenuBar(MainWindow mainWindow) {
		
		//Constructs the 'File' menu.		
		menuFile = new JMenu("File");
		this.add(menuFile);
		
		menuItemNew = new JMenuItem("New");
		menuItemNew.addActionListener((ActionListener) mainWindow);
		menuFile.add(menuItemNew);
		
		menuFile.addSeparator();
		
		menuItemSave = new JMenuItem("Save");
		menuItemSave.addActionListener((ActionListener) mainWindow);
		menuItemSave.setEnabled(false);
		menuFile.add(menuItemSave);
		
		menuItemSaveAs = new JMenuItem("Save As...");
		menuItemSaveAs.addActionListener((ActionListener) mainWindow);
		menuItemSaveAs.setEnabled(false);
		menuFile.add(menuItemSaveAs);
		
		menuItemOpen = new JMenuItem("Open...");
		menuItemOpen.addActionListener((ActionListener) mainWindow);
		menuFile.add(menuItemOpen);
		
		menuFile.addSeparator();
		
		menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener((ActionListener) mainWindow);
		menuItemExit.setActionCommand("exit");
		menuFile.add(menuItemExit);
		
		//Constructs the 'Edit' menu
		menuEdit = new JMenu("Edit");
		this.add(menuEdit);
		
		menuAddDecision = new JMenuItem("Add Decision");
		menuAddDecision.addActionListener((ActionListener) mainWindow);
		menuAddDecision.setActionCommand("add_decision");
		menuEdit.add(menuAddDecision);
		
		menuEdit.addSeparator();
		
		menuItemCut = new JMenuItem("Cut");
		menuItemCut.addActionListener((ActionListener) mainWindow);
		menuEdit.add(menuItemCut);
		
		menuItemCopy = new JMenuItem("Copy");
		menuItemCopy.addActionListener((ActionListener) mainWindow);
		menuEdit.add(menuItemCopy);
		
		menuItemPaste = new JMenuItem("Paste");
		menuItemPaste.addActionListener((ActionListener) mainWindow);
		menuEdit.add(menuItemPaste);
		
		//Constructs the 'View' menu
		menuView = new JMenu("View");
		this.add(menuView);
		
		menuItemShowToolbox = new JMenuItem("Show Toolbox");
		menuItemShowToolbox.addActionListener((ActionListener) mainWindow);
		menuView.add(menuItemShowToolbox);
		
	}

}
