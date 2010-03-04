/**
 * 
 */
package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import org.apache.commons.collections15.Factory;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;

import uk.co.gregorydoran.plotxml.editor.Decision;
import uk.co.gregorydoran.plotxml.editor.Dependency;
import uk.co.gregorydoran.plotxml.editor.xml_binding.PlotType;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractModalGraphMouse;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.EditingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.control.RotatingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ScalingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.TranslatingGraphMousePlugin;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization3d.decorators.PickableVertexPaintTransformer;

/**
 * @author Gregory Doran <me@gregorydoran.co.uk>
 *
 */
public class MainWindow extends JFrame implements ActionListener  {

	private static final long serialVersionUID = 9118006988703782991L;
	
	Graph<Decision, Dependency> g;
	VisualizationViewer<Decision, Dependency> vv;
	MainWindowMenuBar menuBar;
	GraphZoomScrollPane gzsp;
    ToolBox toolBox;

	
	Factory<Decision> vertexFactory;
    Factory<Dependency> edgeFactory;
	
	/**
	 * Creates the window and controls.
	 */
	public MainWindow()
	{
		super("Plot XML Editor");
		
		//Load menubar
		menuBar = new MainWindowMenuBar(this);
		this.setJMenuBar(menuBar);
		
		//Load DirectedGraph control
		g = getGraph();
		
		//setup factories
		vertexFactory = new VertexFactory();
		edgeFactory = new EdgeFactory();
		
		vv = new VisualizationViewer<Decision, Dependency>(new FRLayout<Decision, Dependency>(g));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Decision>());
		gzsp = new GraphZoomScrollPane(vv);
		this.getContentPane().add(gzsp);
		
		// Create a graph mouse and add it to the visualization component
		PluggableGraphMouse gm = new PluggableGraphMouse();
		gm.add(new TranslatingGraphMousePlugin(MouseEvent.BUTTON2_MASK));
		gm.add(new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0, 1.1f, 0.9f));
		gm.add(new RotatingGraphMousePlugin(MouseEvent.BUTTON2_MASK + MouseEvent.SHIFT_MASK));
		gm.add(new EditingGraphMousePlugin<Decision, Dependency>(MouseEvent.CTRL_MASK,vertexFactory, edgeFactory));
		gm.add(new PickingGraphMousePlugin<Decision, Dependency>());
		vv.setGraphMouse(gm);
		
		toolBox = new ToolBox();
		add(toolBox, BorderLayout.PAGE_START);
        
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(800, 600);
	}
	
	public Graph<Decision, Dependency> getGraph()
	{
		Graph<Decision, Dependency> g = new DirectedSparseGraph<Decision, Dependency>();
		
		/*PlotType obj = null;
		
		IBindingFactory bfact;
		try {
			
			bfact = BindingDirectory.getFactory(PlotType.class);
			// TODO Auto-generated catch block
		
			//Load the XML file
			IMarshallingContext mctx = null;
			
			mctx = bfact.createMarshallingContext();
			
			
			mctx.marshalDocument(obj, "UTF-8", null,
			    new FileOutputStream("filename.xml"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JiBXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	    
	    
	    
		return(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        if("exit".equals(e.getActionCommand()))
        {
        	System.exit(0);
        }
        if("add_decision".equals(e.getActionCommand()))
        {
        	vv.repaint();
        }
    }

	
}
