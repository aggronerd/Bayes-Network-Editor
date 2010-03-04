
package uk.co.gregorydoran.plotxml.editor.xml_binding;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ProbType">
 *   &lt;xs:attribute type="xs:string" use="required" name="option_name"/>
 *   &lt;xs:attribute type="xs:float" name="value"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class ProbType
{
    private String optionName;
    private Float value;

    /** 
     * Get the 'option_name' attribute value.
     * 
     * @return value
     */
    public String getOptionName() {
        return optionName;
    }

    /** 
     * Set the 'option_name' attribute value.
     * 
     * @param optionName
     */
    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    /** 
     * Get the 'value' attribute value.
     * 
     * @return value
     */
    public Float getValue() {
        return value;
    }

    /** 
     * Set the 'value' attribute value.
     * 
     * @param value
     */
    public void setValue(Float value) {
        this.value = value;
    }
}
