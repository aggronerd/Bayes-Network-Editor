package uk.co.gregorydoran.plotxml.editor.main_window;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class XMLFileFilter extends FileFilter
{

    @Override
    public boolean accept(File f)
    {
	if (f.getName().endsWith(".xml"))
	{
	    return (true);
	}
	else if (f.isDirectory())
	{
	    return (true);
	}
	else
	{
	    return (false);
	}
    }

    @Override
    public String getDescription()
    {
	return "XML Files";
    }

}
