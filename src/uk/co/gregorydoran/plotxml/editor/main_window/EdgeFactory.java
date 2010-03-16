package uk.co.gregorydoran.plotxml.editor.main_window;

import org.apache.commons.collections15.Factory;

import uk.co.gregorydoran.plotxml.editor.Dependency;

class EdgeFactory implements Factory<Dependency>
{

    Integer i = 0;

    EdgeFactory()
    {
    }

    public Dependency create()
    {
	Dependency d = new Dependency();

	i++;

	return d;
    }
}