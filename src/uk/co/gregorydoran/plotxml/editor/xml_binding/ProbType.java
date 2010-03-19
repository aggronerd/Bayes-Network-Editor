package uk.co.gregorydoran.plotxml.editor.xml_binding;

/**
 * Schema fragment(s) for this class:
 * 
 * <pre>
 * &lt;xs:complexType xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ProbType">
 *   &lt;xs:attribute type="xs:string" use="required" name="option_name"/>
 *   &lt;xs:attribute type="xs:float" name="value"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class ProbType
{
    private OptionType option;
    private Float value;

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
     * Set the 'option' attribute value.
     * 
     * @param optionName
     */
    public void setOption(OptionType o)
    {
	this.option = o;
    }

    /**
     * Get the 'value' attribute value.
     * 
     * @return value
     */
    public Float getValue()
    {
	return value;
    }

    /**
     * Set the 'value' attribute value.
     * 
     * @param value
     */
    public void setValue(Float value)
    {
	this.value = value;
    }
}
