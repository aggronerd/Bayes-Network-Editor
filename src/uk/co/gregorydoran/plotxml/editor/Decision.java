package uk.co.gregorydoran.plotxml.editor;

import uk.co.gregorydoran.plotxml.editor.xml_binding.DecisionType;

public class Decision extends DecisionType{

	public String toString()
	{
		return(name);
	}

	public void setName(String string) {
		name = string;
	}
	
	public String getName(){
		return(name);
	}
	
}
