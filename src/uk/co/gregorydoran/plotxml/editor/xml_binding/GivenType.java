package uk.co.gregorydoran.plotxml.editor.xml_binding;

import java.util.ArrayList;
import java.util.List;

/**
 * Schema fragment(s) for this class:
 * 
 * <pre>
 * &lt;xs:complexType xmlns:xs="http://www.w3.org/2001/XMLSchema" name="GivenType">
 *   &lt;!-- Reference to class dflt.GivenType -->
 * &lt;/xs:complexType>
 * </pre>
 */
public class GivenType
{
    private List<GivenType> givenList = new ArrayList<GivenType>();
    private List<ProbType> probList = new ArrayList<ProbType>();
    private OptionType option;

    /**
     * Get the list of 'given' element items.
     * 
     * @return list
     */
    public List<GivenType> getGivens()
    {
	return givenList;
    }

    /**
     * Set the list of 'given' element items.
     * 
     * @param list
     */
    public void setGivens(List<GivenType> list)
    {
	givenList = list;
    }

    /**
     * Get the 'prob' element value.
     * 
     * @return value
     */
    public List<ProbType> getProbs()
    {
	return probList;
    }

    /**
     * Set the 'prob' element value.
     * 
     * @param prob
     */
    public void setProb(List<ProbType> prob)
    {
	this.probList = prob;
    }

    /**
     * Get the 'option' attribute value.
     * 
     * @return value
     */
    public OptionType getOption()
    {
	return option;
    }

    /**
     * Set the 'option_name' attribute value.
     * 
     * @param o
     *            The option.
     */
    public void setOption(OptionType o)
    {
	this.option = o;
    }

    /**
     * Traverses further givens and balances the probabilities to equal 1.
     */
    public void balance()
    {
	if (probList != null && probList.size() > 0)
	{
	    float totalProb = 0;
	    for (ProbType prob : probList)
	    {
		totalProb += prob.getValue();
	    }
	    if (totalProb > 0)
	    {
		for (ProbType prob : probList)
		{
		    prob.setValue(prob.getValue() / totalProb);
		}
	    }
	}
	else if (givenList != null && givenList.size() > 0)
	{
	    for (GivenType given : givenList)
	    {
		given.balance();
	    }
	}

    }
}
