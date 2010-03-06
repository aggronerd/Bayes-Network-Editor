package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.text.html.Option;

import edu.uci.ics.jung.graph.Graph;

import uk.co.gregorydoran.plotxml.editor.Decision;
import uk.co.gregorydoran.plotxml.editor.Dependency;
import uk.co.gregorydoran.plotxml.editor.xml_binding.OptionType;


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
	
	private JPanel probPanel;
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
		
		probPanel = new JPanel();
		
		this.add(new JLabel("Name:"));
		this.add(nameField);
		this.add(probPanel);
	}
	
	/**
	 * Reloads the controls for the current decision.
	 */
	public void reloadControls()
	{
		if(activeDecision == null)
		{
			nameField.setVisible(false);
			probPanel.removeAll();
		}
		else
		{
			//Setup the name field
			nameField.setText(activeDecision.getName());
			nameField.setVisible(true);
		
			//Setup the probability controls
			List<Decision> dependencies = new ArrayList<Decision>();
			Collection<Decision> collection = activeGraph.getPredecessors(activeDecision);
			for (Decision d : collection)
				dependencies.add(d);
			dependencies.add(activeDecision);
			probPanel.add(getProbPanel(dependencies));
			
		}
	}
	
	JPanel getProbPanel(List<Decision> toAdd)
	{
		//Pop the decision form the collection.
		Decision decision = toAdd.get(0);
		toAdd.remove(decision);
		
		JPanel panel = new JPanel();
		
		//Retrieve options to make up left hand column.
		List<OptionType> options = decision.getOptions().getOptions();
	
		//Setup layout to the correct size.
		panel.setLayout(new GridLayout(options.size(),2));
		
		//Itterate through the options
		for (OptionType o : options)
		{
			panel.add(new JLabel(o.getName()));
			
			if(toAdd.size() == 0)
			{
				//Display the probability control.
				panel.add(new JSlider(0,100,50));
			}
			else
			{
				//Recursive call will provide the same for remaining
				// values in toAdd.
				panel.add(getProbPanel(toAdd));
			}
		}
		
		return(panel);
		
	}
	
	
}
