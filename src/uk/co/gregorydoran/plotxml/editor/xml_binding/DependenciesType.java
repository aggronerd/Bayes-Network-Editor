package uk.co.gregorydoran.plotxml.editor.xml_binding;

import java.util.ArrayList;
import java.util.List;

/**
 * Schema fragment(s) for this class:
 * 
 * <pre>
 * &lt;xs:complexType xmlns:xs="http://www.w3.org/2001/XMLSchema" name="DependenciesType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="decision" minOccurs="1" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class DependenciesType
{
    private List<DecisionType> decisionList = new ArrayList<DecisionType>();

    /**
     * Get the list of 'decision' element items.
     * 
     * @return list
     */
    public List<DecisionType> getDecisions()
    {
	return decisionList;
    }

    /**
     * Set the list of 'decision' element items.
     * 
     * @param list
     */
    public void setDecisions(List<DecisionType> list)
    {
	decisionList = list;
    }
}
