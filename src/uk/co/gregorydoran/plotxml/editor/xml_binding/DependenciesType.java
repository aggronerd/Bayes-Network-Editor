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
    private List<DependencyType> dependencyList = new ArrayList<DependencyType>();

    /**
     * Get the list of 'decision' element items.
     * 
     * @return list
     */
    public List<DependencyType> getDependencies()
    {
	return dependencyList;
    }

    /**
     * Set the list of 'decision' element items.
     * 
     * @param list
     */
    public void setDependencies(List<DependencyType> list)
    {
	dependencyList = list;
    }

    /**
     * Returns a list of all the decisions included in the dependency.
     * 
     * @return
     */
    public List<Decision> getDecisions()
    {
	List<Decision> result = new ArrayList<Decision>();
	for (DependencyType d : dependencyList)
	{
	    result.add(d.getDecision());
	}
	return (result);
    }
}
