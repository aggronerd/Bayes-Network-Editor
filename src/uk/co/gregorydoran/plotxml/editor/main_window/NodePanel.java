package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.BorderLayout;
import java.util.EventListener;

import javax.swing.JPanel;

import uk.co.gregorydoran.plotxml.editor.Decision;
import uk.co.gregorydoran.plotxml.editor.Dependency;
import edu.uci.ics.jung.graph.Graph;

/**
 * 
 * Comprises the panel which takes up the right hand side of the main editor
 * window and is used to update the details of a decision.
 * 
 * @author Gregory Doran <www.gregorydoran.co.uk>
 * 
 */
public class NodePanel extends JPanel implements EventListener
{

    private static final long serialVersionUID = -4341231428121402538L;

    DecisionPanel decisionPanel;
    Decision activeDecision;
    private Graph<Decision, Dependency> activeGraph;

    /**
     * Sets the panel's active decision and displays the controls for it.
     * 
     * @param activeDecision
     */
    public void setActiveDecision(Decision activeDecision)
    {
	this.activeDecision = activeDecision;

	if (activeDecision != null)
	{
	    // TODO: change this so it's only called when changes have been made
	    // to the dependencies or options of dependent decisions.
	    activeDecision.updateDependencies(activeGraph);

	    reloadControls();
	}
	else
	{
	    clearControls();
	}

	this.repaint();
    }

    /**
     * Returns the active decision for the panel.
     * 
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

	this.setLayout(new BorderLayout());

	decisionPanel = null;
    }

    /**
     * Reloads the controls for the current decision.
     */
    public void reloadControls()
    {
	clearControls();

	decisionPanel = new DecisionPanel(activeDecision);

	// GridBagConstraints gbc = new GridBagConstraints();
	// gbc.gridx = 0;
	// gbc.gridy = 0;
	// gbc.fill = GridBagConstraints.HORIZONTAL;
	// gbc.anchor = GridBagConstraints.NORTH;

	this.add(decisionPanel, BorderLayout.PAGE_START);
    }

    public void clearControls()
    {
	if (decisionPanel != null)
	{
	    this.remove(decisionPanel);
	}
	decisionPanel = null;
    }

}
