
package uk.co.gregorydoran.plotxml.editor.xml_binding;

/** 
 * Schema fragment(s) for this class:
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
    private DecisionsType decisions;
    private String name;

    /** 
     * Get the 'english' element value.
     * 
     * @return value
     */
    public String getEnglish() {
        return english;
    }

    /** 
     * Set the 'english' element value.
     * 
     * @param english
     */
    public void setEnglish(String english) {
        this.english = english;
    }

    /** 
     * Get the 'decisions' element value.
     * 
     * @return value
     */
    public DecisionsType getDecisions() {
        return decisions;
    }

    /** 
     * Set the 'decisions' element value.
     * 
     * @param decisions
     */
    public void setDecisions(DecisionsType decisions) {
        this.decisions = decisions;
    }

    /** 
     * Get the 'name' attribute value.
     * 
     * @return value
     */
    public String getName() {
        return name;
    }

    /** 
     * Set the 'name' attribute value.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
