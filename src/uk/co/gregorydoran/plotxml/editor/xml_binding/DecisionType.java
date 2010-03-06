
package uk.co.gregorydoran.plotxml.editor.xml_binding;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:xs="http://www.w3.org/2001/XMLSchema" name="DecisionType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="english" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="DependenciesType" name="dependencies" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="OptionsType" name="options" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="ProbabilitiesType" name="probabilities" minOccurs="1" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:string" use="required" name="name"/>
 *   &lt;xs:attribute use="required" name="type">
 *     &lt;xs:simpleType>
 *       &lt;!-- Reference to inner class Type -->
 *     &lt;/xs:simpleType>
 *   &lt;/xs:attribute>
 * &lt;/xs:complexType>
 * </pre>
 */
public class DecisionType
{
    protected String english;
    protected DependenciesType dependencies;
    protected OptionsType options;
    protected ProbabilitiesType probabilities;
    protected String name;
    protected Type type;

    public DecisionType() 
    {
		options = new OptionsType();
		this.options.getOptions().add(new OptionType("0"));
		this.options.getOptions().add(new OptionType("1"));
	}
    
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
     * Get the 'dependencies' element value.
     * 
     * @return value
     */
    public DependenciesType getDependencies() {
        return dependencies;
    }

    /** 
     * Set the 'dependencies' element value.
     * 
     * @param dependencies
     */
    public void setDependencies(DependenciesType dependencies) {
        this.dependencies = dependencies;
    }

    /** 
     * Get the 'options' element value.
     * 
     * @return value
     */
    public OptionsType getOptions() {
        return options;
    }

    /** 
     * Set the 'options' element value.
     * 
     * @param options
     */
    public void setOptions(OptionsType options) {
        this.options = options;
    }

    /** 
     * Get the 'probabilities' element value.
     * 
     * @return value
     */
    public ProbabilitiesType getProbabilities() {
        return probabilities;
    }

    /** 
     * Set the 'probabilities' element value.
     * 
     * @param probabilities
     */
    public void setProbabilities(ProbabilitiesType probabilities) {
        this.probabilities = probabilities;
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

    /** 
     * Get the 'type' attribute value. 
     Indicates to the interpreter the type of the chosen
     result.
     
     * 
     * @return value
     */
    public Type getType() {
        return type;
    }

    /** 
     * Set the 'type' attribute value. 
     Indicates to the interpreter the type of the chosen
     result.
     
     * 
     * @param type
     */
    public void setType(Type type) {
        this.type = type;
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:simpleType xmlns:xs="http://www.w3.org/2001/XMLSchema">
     *   &lt;xs:restriction base="xs:string">
     *     &lt;xs:enumeration value="int"/>
     *     &lt;xs:enumeration value="string"/>
     *     &lt;xs:enumeration value="double"/>
     *   &lt;/xs:restriction>
     * &lt;/xs:simpleType>
     * </pre>
     */
    public static enum Type {
        INT("int"), STRING("string"), DOUBLE("double");
        private final String value;

        private Type(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        public static Type convert(String value) {
            for (Type inst : values()) {
                if (inst.toString().equals(value)) {
                    return inst;
                }
            }
            return null;
        }
    }
}
