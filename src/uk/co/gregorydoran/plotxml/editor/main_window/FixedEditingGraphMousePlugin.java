package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.event.MouseEvent;

import org.apache.commons.collections15.Factory;

import uk.co.gregorydoran.plotxml.editor.Dependency;
import uk.co.gregorydoran.plotxml.editor.xml_binding.Decision;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.control.EditingGraphMousePlugin;

/**
 * 
 * This class was created to fix an issue with the EditingGraphMousePlugin class
 * where on line 97 it uses instanceof to determine if the graph is directed.
 * Because this application uses DirectedGraph and on top of that
 * ObservableGraph it thinks it is unidirectional and crashes when adding edges.
 * As a work around this class is used to overwrite it and assume that the graph
 * is directional.
 * 
 * @author greg
 * 
 */
public class FixedEditingGraphMousePlugin extends
	EditingGraphMousePlugin<Decision, Dependency>
{

    public FixedEditingGraphMousePlugin(int modifiers,
	    Factory<Decision> vertexFactory, Factory<Dependency> edgeFactory)
    {
	super(modifiers, vertexFactory, edgeFactory);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
	super.mousePressed(e);
	this.edgeIsDirected = EdgeType.DIRECTED;
    }
}
