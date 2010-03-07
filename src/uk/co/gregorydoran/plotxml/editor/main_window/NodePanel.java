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
		
		probTable = new JTable();
		
		this.add(new JLabel("Name:"));
		this.add(nameField);
		this.add(probTable);
	}
	
	/**
	 * Reloads the controls for the current decision.
	 */
	public void reloadControls()
	{
		if(activeDecision == null)
		{
			nameField.setVisible(false);
			if (probTable != null)
			{
				this.remove(probTable);
				probTable = null;
			}
		}
		else
		{
			//Setup the name field
			nameField.setText(activeDecision.getName());
			nameField.setVisible(true);
		
			//Setup the probability controls
			if (probTable != null)
			{
				this.remove(probTable);
				probTable = null;
			}
			
			//Order is important so we convert the collection to a list.
			List<Decision> dependencies = new ArrayList<Decision>();
			Collection<Decision> collection = activeGraph.getPredecessors(activeDecision);
			String[] headers = new String[collection.size()+2];
			int i = 0;
			for (Decision d : collection)
			{
				dependencies.add(d);
				headers[i] = d.getName();
				i++;
			}
			dependencies.add(activeDecision);
			headers[i] = activeDecision.getName();
			headers[i+1] = "Value";
			
			Object[][] vals = getProbTableData(dependencies);
			vals.toString();
			
		}
	}
	
	Object[][] getProbTableData(List<Decision> toAdd)
	{
		//Pop the decision form the collection.
		Decision decision = toAdd.get(0);
		toAdd.remove(decision);
		
		//Retrieve options to make up left hand column.
		List<OptionType> options = decision.getOptions().getOptions();
			
		Object[][] tableData;
		int i = 0;
		
		if(toAdd.size() == 0)
		{
			//This is the last therefore we display the controls.
			tableData = new Object[2][options.size()];
			for (OptionType o : options)
			{
				tableData[i][0] = o.getName(); 
				tableData[i][1] = new JSlider(0,100,50);
				i++;
			}
		}
		else
		{
			//Still more decisions to add so we call recursively.
			Object[][] subTableData = getProbTableData(toAdd);
			
			//Create an array big enough to fit both.
			tableData = new Object[subTableData.length * options.size()][1+subTableData[0].length];
			
			//Itterate through the options
			int k = 0;
			for (OptionType o : options)
			{
				for(i=0; i < subTableData.length; i++)
				{
					//Add the current option's name downwards.
					tableData[(k*subTableData.length)+i][0] = o.getName();
					
					//Add the values from the subtable.
					for(int j=0; j < subTableData[i].length; j++)
					{
						tableData[(k*subTableData.length)+i][j+1] = subTableData[j][i];
					}
				}
				k++;
			}
		}
		return(tableData);
	}
	
	
}
