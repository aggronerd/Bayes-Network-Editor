package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import uk.co.gregorydoran.plotxml.editor.xml_binding.Decision;
import uk.co.gregorydoran.plotxml.editor.xml_binding.GivenType;
import uk.co.gregorydoran.plotxml.editor.xml_binding.ProbType;
import uk.co.gregorydoran.plotxml.editor.xml_binding.ProbabilitiesType;

/**
 * 
 * GUI Panel for displaying the contents of a Probabilities tag for a Decision
 * object.
 * 
 * @author greg
 * 
 */
public class ProbabilitiesControl extends JPanel
{

    private static final long serialVersionUID = 1L;

    private ProbabilitiesType probabilities;

    /**
     * This is the default constructor
     */
    public ProbabilitiesControl(ProbabilitiesType p, List<Decision> dependencies)
    {
	super();

	this.probabilities = p;

	this.setLayout(new GridBagLayout());

	int baseX = 1, baseY = 1; // The current root grid positions
	int n = 0;
	int i = 0;

	// Prepares the header labels
	for (Decision d : dependencies)
	{
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = baseX + i;
	    gbc.gridy = baseY;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.weightx = 1.0;
	    gbc.insets = new Insets(10, 0, 10, 0);
	    this.add(new JLabel("Given " + d.getName(), JLabel.CENTER), gbc);
	    i++;
	}
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.gridx = baseX + i;
	gbc.gridy = baseY;
	gbc.fill = GridBagConstraints.BOTH;
	gbc.anchor = GridBagConstraints.WEST;
	gbc.weightx = 1.0;
	gbc.insets = new Insets(10, 0, 10, 0);
	this.add(new JLabel("Option", JLabel.CENTER), gbc);
	i++;
	gbc = new GridBagConstraints();
	gbc.gridx = baseX + i;
	gbc.gridy = baseY;
	gbc.fill = GridBagConstraints.BOTH;
	gbc.anchor = GridBagConstraints.WEST;
	gbc.weightx = 1.0;
	gbc.insets = new Insets(10, 0, 10, 0);
	this.add(new JLabel("Probability", JLabel.CENTER), gbc);

	baseY++;

	// Prepares the controls
	if (probabilities.getGivens() != null
		&& probabilities.getGivens().size() > 0)
	{
	    for (GivenType given : probabilities.getGivens())
	    {
		n = traverseGiven(given, dependencies, baseX, baseY);
		baseY += n;
	    }
	}
	else if (probabilities.getProbs() != null
		&& probabilities.getProbs().size() > 0)
	{
	    for (ProbType prob : probabilities.getProbs())
	    {
		displayProb(prob, baseX, baseY + n);
		n++;
	    }
	}
    }

    /**
     * 
     * @param given
     * @param dependencies
     * @param baseX
     *            The x value of the gridbag
     * @param baseY
     * @return An integer expressing the vertical cells of the grid box layout
     *         used.
     */
    private int traverseGiven(GivenType given, List<Decision> dependencies,
	    int baseX, int baseY)
    {
	GridBagConstraints gbc = null;

	// Detach the first dependency from the list
	dependencies = new ArrayList<Decision>(dependencies);
	dependencies.remove(0);

	int n = 0; // Rows added by this function in this instance

	// Add child controls
	if (given.getGivens() != null && given.getGivens().size() > 0)
	{
	    for (GivenType g : given.getGivens())
	    {
		int nGiven = 0; // Rows add by this given

		// Recursive call
		nGiven += traverseGiven(g, dependencies, baseX + 1, baseY + n);

		n += nGiven;
	    }
	}
	else if (given.getProbs() != null && given.getProbs().size() > 0)
	{
	    // Insert horizontal separator
	    JSeparator sep = new JSeparator();
	    gbc = new GridBagConstraints();
	    gbc.gridx = baseX;
	    gbc.gridy = baseY + n;
	    gbc.gridwidth = 3;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.weightx = 1.0;
	    this.add(sep, gbc);
	    n++;

	    for (ProbType p : given.getProbs())
	    {
		displayProb(p, baseX + 1, baseY + n);
		n++;
	    }
	}
	// Draw label for this option
	gbc = new GridBagConstraints();
	gbc.gridx = baseX;
	gbc.gridy = baseY;
	gbc.gridheight = n;
	gbc.fill = GridBagConstraints.BOTH;
	gbc.anchor = GridBagConstraints.WEST;
	gbc.weightx = 1.0;
	this.add(new JLabel(given.getOption().getName(), JLabel.CENTER), gbc);

	return (n);

    }

    private void displayProb(ProbType prob, int baseX, int baseY)
    {
	// Add label for this option
	GridBagConstraints labelGBC = new GridBagConstraints();
	labelGBC.gridx = baseX;
	labelGBC.gridy = baseY;
	labelGBC.fill = GridBagConstraints.BOTH;
	labelGBC.anchor = GridBagConstraints.WEST;
	labelGBC.weightx = 1.0;
	this.add(new JLabel(prob.getOption().getName(), JLabel.CENTER),
		labelGBC);

	// Add probability control.
	GridBagConstraints probGBC = new GridBagConstraints();
	probGBC.gridx = baseX + 1;
	probGBC.gridy = baseY;
	probGBC.fill = GridBagConstraints.BOTH;
	probGBC.anchor = GridBagConstraints.WEST;
	probGBC.weightx = 1.0;
	this.add(new ProbabilityControl(prob), probGBC);
    }
}
