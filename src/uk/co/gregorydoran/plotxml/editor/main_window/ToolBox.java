package uk.co.gregorydoran.plotxml.editor.main_window;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBox extends JToolBar {
	
	private static final long serialVersionUID = 8051383957844794264L;

	public ToolBox() {
		super("Toolbox");
		
	    add(new JButton("Button"));
	    
	    setVisible(true);

	}
	
}
