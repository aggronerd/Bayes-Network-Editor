package uk.co.gregorydoran.plotxml.editor.xml_binding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uk.co.gregorydoran.plotxml.editor.Dependency;
import edu.uci.ics.jung.graph.Graph;

/**
 * Schema fragment(s) for this class:
 * 
 * <pre>
 * &lt;xs:complexType xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Decision">
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
public class Decision
{
    protected String english;
    protected DependenciesType dependencies = new DependenciesType();
    protected OptionsType options = new OptionsType(this);
    protected ProbabilitiesType probabilities = new ProbabilitiesType();
    protected String name;
    protected String type = new String("String");

    public Decision()
    {
	OptionType o1 = new OptionType("0");
	o1.setParent(this.getOptions());
	OptionType o2 = new OptionType("1");
	o2.setParent(this.getOptions());

	this.options.getOptions().add(o1);
	this.options.getOptions().add(o2);

	dependencies = new DependenciesType();
	updateProbabilities();
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
     * Get the 'dependencies' element value.
     * 
     * @return value
     */
    public DependenciesType getDependencies()
    {
	return dependencies;
    }

    /**
     * Set the 'dependencies' element value.
     * 
     * @param dependencies
     */
    public void setDependencies(DependenciesType dependencies)
    {
	this.dependencies = dependencies;
    }

    /**
     * Get the 'options' element value.
     * 
     * @return value
     */
    public OptionsType getOptions()
    {
	return options;
    }

    /**
     * Set the 'options' element value.
     * 
     * @param options
     */
    public void setOptions(OptionsType options)
    {
	this.options = options;
    }

    /**
     * Get the 'probabilities' element value.
     * 
     * @return value
     */
    public ProbabilitiesType getProbabilities()
    {
	return probabilities;
    }

    /**
     * Set the 'probabilities' element value.
     * 
     * @param probabilities
     */
    public void setProbabilities(ProbabilitiesType probabilities)
    {
	this.probabilities = probabilities;
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
	// TODO: Check decision name is unique.
	this.name = name;
    }

    /**
     * Get the 'type' attribute value. Indicates to the interpreter the type of
     * the chosen result.
     * 
     * 
     * @return value
     */
    public String getType()
    {
	return type;
    }

    /**
     * Set the 'type' attribute value. Indicates to the interpreter the type of
     * the chosen result.
     * 
     * 
     * @param type
     */
    public void setType(String type)
    {
	this.type = type;
    }

    /**
     * Schema fragment(s) for this class:
     * 
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
    public static enum Type
    {
	INT("int"), STRING("string"), DOUBLE("double");
	private final String value;

	private Type(String value)
	{
	    this.value = value;
	}

	public String toString()
	{
	    return value;
	}

	public static Type convert(String value)
	{
	    for (Type inst : values())
	    {
		if (inst.toString().equals(value))
		{
		    return inst;
		}
	    }
	    return null;
	}
    }

    /**
     * Returns the path to this decision in the plot XML file.
     * 
     * @return
     */
    public String getPath()
    {
	// TODO: Add paths.
	return (name);
    }

    /**
     * Removes all dependencies from the class.
     */
    public void clearDependencies()
    {
	dependencies.getDependencies().clear();
    }

    /**
     * Returns the path to the object.
     */
    public String toString()
    {
	return (this.getPath());
    }

    /**
     * This method updates dependencies and the probability table to reflect
     * those found in the graph.
     * 
     * @param g
     *            The graph object to base the dependencies on.
     */
    public void updateDependencies(Graph<Decision, Dependency> g)
    {
	// Clear existing dependencies
	clearDependencies();

	// Update with ones from the graph.
	Collection<Decision> collection = g.getPredecessors(this);

	setProbabilities(new ProbabilitiesType());

	if (collection.size() > 0)
	{
	    for (Decision d : collection)
	    {
		DependencyType dep = new DependencyType();
		dep.setDecision(d);
		dependencies.getDependencies().add(dep);
	    }

	    // Update probability elements
	    getProbabilities().setGivens(
		    getNewGivens(getDependencies().getDecisions()));
	}
	else
	{
	    updateProbabilities();
	}

    }

    /**
     * Assumes that this Decision is not dependent on any others.
     */
    public void updateProbabilities()
    {
	// There are no dependencies so we just add the probabilities for
	// the options for this decision.
	for (OptionType o : this.getOptions().getOptions())
	{
	    ProbType p = new ProbType();
	    p.setValue(1.0f / this.getOptions().getOptions().size());
	    p.setOption(o);
	    getProbabilities().getProbs().add(p);
	}
    }

    /**
     * Calls update dependencies for all dependent decisions. Will break due to
     * circular references.
     */
    public void updateAllDependant(Graph<Decision, Dependency> g)
    {
	Collection<Decision> collection = g.getSuccessors(this);
	for (Decision d : collection)
	{
	    d.updateDependencies(g);
	}
    }

    /**
     * Returns the structure of objects for storing probabilities for this
     * Decision's Options given the options of the decisions in the
     * decisionsLeft parameter.
     * 
     * Works by recursively calling itself and returning null if decisionsLeft
     * is empty.
     * 
     * The probabilities which are set by default are 1 / number of options in
     * this Decision.
     * 
     * @param decisionsLeft
     * @return
     */
    private List<GivenType> getNewGivens(List<Decision> decisionsLeft)
    {
	// Make a copy of the list because it's being called recursively in a
	// branching structure we don't want to break the list.
	decisionsLeft = new ArrayList<Decision>(decisionsLeft);

	List<GivenType> result = new ArrayList<GivenType>();

	if (decisionsLeft.size() > 0)
	{

	    // Grab the first decision.
	    Decision first = decisionsLeft.get(0);

	    // Remove this one because we don't want to do it again.
	    decisionsLeft.remove(first);

	    // Loop through options for this decision and carry recursive calls.
	    for (OptionType o : first.getOptions().getOptions())
	    {
		// There are still more dependencies to add elements for.
		GivenType g = new GivenType();
		g.setOption(o);

		// May set it to null, but we don't mind, because that means
		// there's no more to add.
		g.setGivens(getNewGivens(decisionsLeft));

		if (g.getGivens().size() == 0)
		{
		    // This was the last call to this function which means we're
		    // at the lowest given tag, thus we add the probabilities
		    // for the options of this Object.
		    g.setProb(new ArrayList<ProbType>());

		    for (OptionType myOption : this.getOptions().getOptions())
		    {
			ProbType p = new ProbType();
			p.setOption(myOption);
			p
				.setValue(1.0f / this.getOptions().getOptions()
					.size());
			g.getProb().add(p);
		    }
		}

		result.add(g);
	    }
	}
	else
	{
	}

	return result;
    }

}
