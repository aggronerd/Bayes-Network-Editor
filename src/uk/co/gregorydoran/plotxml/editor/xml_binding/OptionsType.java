package uk.co.gregorydoran.plotxml.editor.xml_binding;

import java.util.ArrayList;
import java.util.List;

/**
 * Schema fragment(s) for this class:
 * 
 * <pre>
 * &lt;xs:complexType xmlns:xs="http://www.w3.org/2001/XMLSchema" name="OptionsType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="OptionType" name="option" minOccurs="1" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class OptionsType
{
    private List<OptionType> optionList = new ArrayList<OptionType>();
    private Decision parent = null;

    public OptionsType()
    {
    }

    public OptionsType(Decision parentDecision)
    {
	parent = parentDecision;
    }

    /**
     * Get the list of 'option' element items.
     * 
     * @return list
     */
    public List<OptionType> getOptions()
    {
	return optionList;
    }

    /**
     * Set the list of 'option' element items.
     * 
     * @param list
     */
    public void setOptions(List<OptionType> list)
    {
	optionList = list;
    }

    /**
     * 
     * @param d
     *            Decision object as parent.
     */
    private void setParent(Object decision)
    {
	parent = (Decision) decision;
    }

    public Decision getParent()
    {
	return (parent);
    }
}
