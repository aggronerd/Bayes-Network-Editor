package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.co.gregorydoran.plotxml.editor.xml_binding.DecisionType;
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
    public ProbabilitiesControl(ProbabilitiesType p,
	    List<DecisionType> dependencies)
    {
	super();

	this.probabilities = p;

	this.setLayout(new GridBagLayout());

	// Prepares the controls
	int n = 0;
	if (probabilities.getGivens() != null
		&& probabilities.getGivens().size() > 0)
	{
	    for (GivenType given : probabilities.getGivens())
	    {
		// Set constraints
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = n;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(10, 0, 10, 0);

		this.add(getGivenPanel(given, dependencies), gbc);

		n++;
	    }
	}
	else if (probabilities.getProbs() != null
		&& probabilities.getProbs().size() > 0)
	{
	    for (ProbType prob : probabilities.getProbs())
	    {
		// Set constraints
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = n;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = 1.0;

		this.add(new OptionProbControl(prob), gbc);

		n++;
	    }
	}
    }

    /**
     * 
     * Recursive function which returns a JPanel control with probabilities for
     * the supplied GivenType.
     * 
     * @param given
     * @param dependencies
     * @return
     */
    private JPanel getGivenPanel(GivenType given,
	    List<DecisionType> dependencies)
    {
	JPanel panel = new JPanel();
	panel.setLayout(new GridBagLayout());

	// Detach the first dependency from the list
	DecisionType dependency = dependencies.get(0);
	dependencies = new ArrayList<DecisionType>(dependencies);
	dependencies.remove(0);

	int n = 0;

	// Add child controls
	if (given.getGivens() != null && given.getGivens().size() > 0)
	{

	    for (GivenType g : given.getGivens())
	    {
		// Setup constraints for child control.
		GridBagConstraints childGbc = new GridBagConstraints();
		childGbc.gridx = 1;
		childGbc.gridy = n;
		childGbc.fill = GridBagConstraints.BOTH;
		childGbc.anchor = GridBagConstraints.WEST;
		childGbc.weightx = 1.0;

		// Adds vertical padding between prob. groups:
		if (g.getProb().size() > 0)
		{
		    childGbc.insets = new Insets(10, 0, 10, 0);
		}

		// Add new panel.
		panel.add(getGivenPanel(g, dependencies), childGbc);

		n++;
	    }
	}
	else if (given.getProb() != null && given.getProb().size() > 0)
	{
	    for (ProbType p : given.getProb())
	    {
		// Setup constraints for child control.
		GridBagConstraints childGbc = new GridBagConstraints();
		childGbc.gridx = 1;
		childGbc.gridy = n;
		childGbc.fill = GridBagConstraints.BOTH;
		childGbc.anchor = GridBagConstraints.WEST;
		childGbc.weightx = 1.0;

		// Add prob control.
		panel.add(new OptionProbControl(p), childGbc);

		n++;
	    }

	}

	// Create a label for the option.
	JLabel optionLabel = new JLabel(dependency.getName() + " = "
		+ given.getOptionName());

	// Set Constraints.
	GridBagConstraints gbc1 = new GridBagConstraints();
	gbc1.gridx = 0;
	gbc1.gridy = 0;
	gbc1.gridheight = n + 1;
	gbc1.fill = GridBagConstraints.HORIZONTAL;
	gbc1.anchor = GridBagConstraints.WEST;
	gbc1.weightx = 0.0;

	panel.add(optionLabel, gbc1);

	return (panel);

    }
}
