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
    private String optionName;

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
    public List<ProbType> getProb()
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
     * Get the 'option_name' attribute value.
     * 
     * @return value
     */
    public String getOptionName()
    {
	return optionName;
    }

    /**
     * Set the 'option_name' attribute value.
     * 
     * @param optionName
     */
    public void setOptionName(String optionName)
    {
	this.optionName = optionName;
    }
}
