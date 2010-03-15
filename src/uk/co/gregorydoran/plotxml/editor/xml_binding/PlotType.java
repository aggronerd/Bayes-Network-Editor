package uk.co.gregorydoran.plotxml.editor.xml_binding;

import java.util.ArrayList;
import java.util.List;

/**
 * Schema fragment(s) for this class:
 * 
 * <pre>
 * &lt;xs:complexType xmlns:xs="http://www.w3.org/2001/XMLSchema" name="PlotType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="DecisionsType" name="decisions" minOccurs="1" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:string" name="name"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class PlotType
{
    private List<DecisionType> decisions = new ArrayList<DecisionType>();
    private String name;

    /**
     * Get the list of 'decisions' element items.
     * 
     * @return list
     */
    public List<DecisionType> getDecisions()
    {
	return decisions;
    }

    /**
     * Set the list of 'decisions' element items.
     * 
     * @param list
     */
    public void setDecisions(List<DecisionType> d)
    {
	decisions = d;
    }

    /**
     * Get the 'name' attribute value.
     * 
     * @return value
     */
    public String getName()
    {
	return name;
    }

    /**
     * Set the 'name' attribute value.
     * 
     * @param name
     */
    public void setName(String name)
    {
	this.name = name;
    }
}
