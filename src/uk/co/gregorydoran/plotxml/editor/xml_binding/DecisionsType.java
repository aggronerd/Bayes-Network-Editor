
package uk.co.gregorydoran.plotxml.editor.xml_binding;

import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:xs="http://www.w3.org/2001/XMLSchema" name="DecisionsType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="DecisionType" name="decision" minOccurs="1" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class DecisionsType
{
    private List<DecisionType> decisionList = new ArrayList<DecisionType>();

    /** 
     * Get the list of 'decision' element items.
     * 
     * @return list
     */
    public List<DecisionType> getDecisions() {
        return decisionList;
    }

    /** 
     * Set the list of 'decision' element items.
     * 
     * @param list
     */
    public void setDecisions(List<DecisionType> list) {
        decisionList = list;
    }
}
