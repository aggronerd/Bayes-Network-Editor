package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;

import uk.co.gregorydoran.plotxml.editor.Decision;
import uk.co.gregorydoran.plotxml.editor.Dependency;

import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractPopupGraphMousePlugin;

/**
 * a GraphMousePlugin that offers popup
 * menu support
 */
class PopupGraphMousePlugin extends AbstractPopupGraphMousePlugin implements MouseListener {
    
    public PopupGraphMousePlugin() {
        this(MouseEvent.BUTTON3_MASK);
    }
    public PopupGraphMousePlugin(int modifiers) {
        super(modifiers);
    }
    
    /**
     * If this event is over a Vertex, pop up a menu to
     * allow the user to increase/decrease the voltage
     * attribute of this Vertex
     * @param e
     */
    protected void handlePopup(MouseEvent e) {
        
    	final VisualizationViewer<Decision,Dependency> vv = (VisualizationViewer<Decision,Dependency>)e.getSource();
    	
        Point2D p = e.getPoint();//vv.getRenderContext().getBasicTransformer().inverseViewTransform(e.getPoint());
    }
}
