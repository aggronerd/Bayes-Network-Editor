package uk.co.gregorydoran.plotxml.editor.xml_binding;

import java.util.ArrayList;
import java.util.List;

/**
 * Schema fragment(s) for this class:
 * 
 * <pre>
 * &lt;xs:complexType xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ProbabilitiesType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="GivenType" name="given" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ProbType" name="prob" minOccurs="0" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class ProbabilitiesType
{
    private List<GivenType> givenList = new ArrayList<GivenType>();
    private List<ProbType> probList = new ArrayList<ProbType>();

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
    public void setProbs(List<ProbType> probs)
    {
	this.probList = probs;
    }
}
