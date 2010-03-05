package uk.co.gregorydoran.plotxml.editor.main_window;

import java.util.Collection;
import java.util.EventListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import edu.uci.ics.jung.graph.Graph;

import uk.co.gregorydoran.plotxml.editor.Decision;
import uk.co.gregorydoran.plotxml.editor.Dependency;


/**
 * 
 * Comprises the panel which takes up the right hand side
 * of the main editor window and is used to update the 
 * details of a decision.
 * 
 * @author Gregory Doran <www.gregorydoran.co.uk>
 *
 */
public class NodePanel extends JPanel implements EventListener {

	private static final long serialVersionUID = -4341231428121402538L;
	
	private JTable probTable;
	private JTextField nameField;
	
	private Decision activeDecision;
	private Graph<Decision, Dependency> activeGraph;

	/**
	 * Sets the panel's active decision and displays the controls for it.
	 * @param activeDecision
	 */
	public void setActiveDecision(Decision activeDecision) 
	{
		this.activeDecision = activeDecision;
		reloadControls();
	}

	/**
	 * Returns the active decision for the panel.
	 * @return
	 */
	public Decision getActiveDecision() 
	{
		return activeDecision;
	}
	
	/**
	 * Displays the panel controls.
	 */
	public NodePanel(Graph<Decision, Dependency> graph) 
	{
		activeGraph = graph;
	
		nameField = new JTextField(30);
		nameField.setVisible(true);
		
		probTable = null;	
		
		this.add(new JLabel("Name:"));
		this.add(nameField);
	}
	
	/**
	 * Reloads the controls for the current decision.
	 */
	public void reloadControls()
	{
		if(activeDecision == null)
		{
			nameField.setVisible(false);
			probTable = null;
		}
		else
		{
			nameField.setText(activeDecision.getName());
			nameField.setVisible(true);
			
			Collection<Decision> dependencies = activeGraph.getPredecessors(activeDecision);
			Collection<String> columnNames;
			for(Decision d : dependencies)
			{
				//TableColumn tc = new TableColumn();
				//tc.setIdentifier(d.toString());
				//probTable.addColumn(tc);
				columnNames.add(d.toString());
				//d.toString());
			}
			probTable = new JTable();
			
			
			header.setVisible(true);
			probTable.setVisible(true);
			
		}
	}
	
}
