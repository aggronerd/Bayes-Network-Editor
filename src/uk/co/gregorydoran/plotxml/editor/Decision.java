package uk.co.gregorydoran.plotxml.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uk.co.gregorydoran.plotxml.editor.xml_binding.DecisionType;
import uk.co.gregorydoran.plotxml.editor.xml_binding.GivenType;
import uk.co.gregorydoran.plotxml.editor.xml_binding.OptionType;
import uk.co.gregorydoran.plotxml.editor.xml_binding.ProbType;
import uk.co.gregorydoran.plotxml.editor.xml_binding.ProbabilitiesType;
import edu.uci.ics.jung.graph.Graph;

public class Decision extends DecisionType
{

    /**
     * Returns the path to the object.
     */
    public String toString()
    {
	return (this.getPath());
    }

    /**
     * This method updates dependencies and the probability table to reflect
     * those found in the graph.
     * 
     * @param g
     *            The graph object to base the dependencies on.
     */
    public void updateDependencies(Graph<Decision, Dependency> g)
    {
	// Clear existing dependencies
	clearDependencies();

	// Update with ones from the graph.
	Collection<Decision> collection = g.getPredecessors(this);

	setProbabilities(new ProbabilitiesType());

	if (collection.size() > 0)
	{
	    for (Decision d : collection)
	    {
		dependencies.getDecisions().add(d);
	    }
	    // Update probability elements

	    getProbabilities().setGivens(
		    getNewGivens(getDependencies().getDecisions()));
	}
	else
	{
	    // There are no dependencies so we just add the probabilities for
	    // the options for this decision.
	    for (OptionType o : this.getOptions().getOptions())
	    {
		ProbType p = new ProbType();
		p.setValue(1.0f / this.getOptions().getOptions().size());
		p.setOptionName(o.getName());
		getProbabilities().getProbs().add(p);
	    }
	}

    }

    /**
     * Returns the structure of objects for storing probabilities for this
     * Decision's Options given the options of the decisions in the
     * decisionsLeft parameter.
     * 
     * Works by recursively calling itself and returning null if decisionsLeft
     * is empty.
     * 
     * The probabilities which are set by default are 1 / number of options in
     * this Decision.
     * 
     * @param decisionsLeft
     * @return
     */
    private List<GivenType> getNewGivens(List<DecisionType> decisionsLeft)
    {
	// Make a copy of the list because it's being called recursively in a
	// branching structure we don't want to break the list.
	decisionsLeft = new ArrayList<DecisionType>(decisionsLeft);

	if (decisionsLeft.size() > 0)
	{
	    List<GivenType> result = new ArrayList<GivenType>();

	    // Grab the first decision.
	    DecisionType first = decisionsLeft.get(0);

	    // Remove this one because we don't want to do it again.
	    decisionsLeft.remove(first);

	    // Loop through options for this decision and carry recursive calls.
	    for (OptionType o : first.getOptions().getOptions())
	    {
		// There are still more dependencies to add elements for.
		GivenType g = new GivenType();
		g.setOptionName(o.getName());

		// May set it to null, but we don't mind, because that means
		// there's no more to add.
		g.setGivens(getNewGivens(decisionsLeft));

		if (g.getGivens() == null)
		{
		    // This was the last call to this function which means we're
		    // at the lowest given tag, thus we add the probabilities
		    // for the options of this Object.
		    g.setProb(new ArrayList<ProbType>());

		    for (OptionType myOption : this.getOptions().getOptions())
		    {
			ProbType p = new ProbType();
			p.setOptionName(myOption.getName());
			p
				.setValue(1.0f / this.getOptions().getOptions()
					.size());
			g.getProb().add(p);
		    }
		}

		result.add(g);
	    }

	    return result;
	}
	else
	{
	    return (null);
	}
    }
}
