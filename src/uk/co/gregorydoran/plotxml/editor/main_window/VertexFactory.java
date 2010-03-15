package uk.co.gregorydoran.plotxml.editor.main_window;

import org.apache.commons.collections15.Factory;

import uk.co.gregorydoran.plotxml.editor.Decision;

class VertexFactory implements Factory<Decision>
{

    Integer i = 0;

    public Decision create()
    {
	Decision d = new Decision();
	d.setName("decision" + i.toString());
	d.setEnglish("Decision " + i.toString());
	d.setType("String");
	i++;
	return d;
    }
}
