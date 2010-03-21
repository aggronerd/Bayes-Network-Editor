package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.BorderLayout;
import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uk.co.gregorydoran.plotxml.editor.xml_binding.Decision;

/**
 * 
 * Comprises the panel which takes up the right hand side of the main editor
 * window and is used to update the details of a decision.
 * 
 * @author Gregory Doran <www.gregorydoran.co.uk>
 * 
 */
public class NodePanel extends JScrollPane implements EventListener
{

    private static final long serialVersionUID = -4341231428121402538L;

    JPanel decisionPanel = null;
    Decision activeDecision = null;

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
     * Reloads the controls for the current decision.
     */
    public void reloadControls()
    {
	clearControls();

	decisionPanel = new JPanel(new BorderLayout());
	decisionPanel.add(new DecisionPanel(activeDecision),
		BorderLayout.PAGE_START);

	// GridBagConstraints gbc = new GridBagConstraints();
	// gbc.gridx = 0;
	// gbc.gridy = 0;
	// gbc.fill = GridBagConstraints.HORIZONTAL;
	// gbc.anchor = GridBagConstraints.NORTH;

	this.setViewportView(decisionPanel);
    }

    public void clearControls()
    {
	if (decisionPanel != null)
	{
	    this.setViewportView(null);
	}
	decisionPanel = null;
    }

}
