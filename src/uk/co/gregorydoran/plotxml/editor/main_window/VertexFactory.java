package uk.co.gregorydoran.plotxml.editor.main_window;

import org.apache.commons.collections15.Factory;

import uk.co.gregorydoran.plotxml.editor.xml_binding.Decision;
import uk.co.gregorydoran.plotxml.editor.xml_binding.OptionType;

class VertexFactory implements Factory<Decision>
{

    Integer i = 0;

    public Decision create()
    {
	Decision d = new Decision();
	d.setName("decision" + i.toString());
	d.setEnglish("");
	d.setType("String");

	// Add some options
	OptionType o1 = new OptionType("0");
	o1.setParent(d.getOptions());
	OptionType o2 = new OptionType("1");
	o2.setParent(d.getOptions());

	d.getOptions().getOptions().add(o1);
	d.getOptions().getOptions().add(o2);

	d.updateProbabilities();

	i++;
	return d;
    }
}
