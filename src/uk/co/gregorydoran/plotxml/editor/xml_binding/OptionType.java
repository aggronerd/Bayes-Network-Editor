package uk.co.gregorydoran.plotxml.editor.xml_binding;

import org.jibx.runtime.IUnmarshallingContext;

/**
 * Schema fragment(s) for this class:
 * 
 * <pre>
 * &lt;xs:complexType xmlns:xs="http://www.w3.org/2001/XMLSchema" name="OptionType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="english" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="DecisionsType" name="decisions" minOccurs="0" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:string" use="required" name="name"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class OptionType
{
    private String english;
    private String name;
    private OptionsType parent;

    public OptionType(String nameValue, OptionsType parentOptions)
    {
	name = nameValue;
	parent = parentOptions;
    }

    public OptionType(String name_value)
    {
	name = name_value;
    }

    public OptionType()
    {
    }

    public void setPath(String newPath)
    {
	// Do nowt, this is just to trick the JIBX binder.
    }

    public OptionsType getParent()
    {
	return (parent);
    }

    public void setParent(Object parentValue)
    {
	parent = (OptionsType) parentValue;
    }

    public void postset(IUnmarshallingContext ctx)
    {
	parent = (OptionsType) ctx.getStackObject(1);
    }

    public String getPath()
    {
	return (this.getParent().getParent().getName() + "." + this.getName());
    }

    /**
     * Get the 'english' element value.
     * 
     * @return value
     */
    public String getEnglish()
    {
	return english;
    }

    /**
     * Set the 'english' element value.
     * 
     * @param english
     */
    public void setEnglish(String english)
    {
	this.english = english;
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
