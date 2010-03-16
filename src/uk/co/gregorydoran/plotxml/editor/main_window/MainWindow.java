/**
 * 
 */
package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import org.apache.commons.collections15.Factory;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;

import uk.co.gregorydoran.plotxml.editor.Dependency;
import uk.co.gregorydoran.plotxml.editor.xml_binding.Decision;
import uk.co.gregorydoran.plotxml.editor.xml_binding.PlotType;
import edu.uci.ics.jung.algorithms.layout.FRLayout2;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.ObservableGraph;
import edu.uci.ics.jung.graph.event.GraphEvent;
import edu.uci.ics.jung.graph.event.GraphEventListener;
import edu.uci.ics.jung.graph.util.Graphs;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.control.RotatingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ScalingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.TranslatingGraphMousePlugin;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.PickedState;

/**
 * @author Gregory Doran <me@gregorydoran.co.uk>
 * 
 */
public class MainWindow extends JFrame implements ActionListener, ItemListener
{

    private static final long serialVersionUID = 9118006988703782991L;

    private Graph<Decision, Dependency> g = null;
    private VisualizationViewer<Decision, Dependency> vv = null;
    private FRLayout2<Decision, Dependency> layout = null;

    private MainWindowMenuBar menuBar;
    private GraphZoomScrollPane gzsp;
    private JSplitPane splitPane;
    private NodePanel nodePanel;
    private PickedState<Decision> pickedState;

    private Factory<Decision> vertexFactory;
    private Factory<Dependency> edgeFactory;

    /**
     * Creates the window and controls.
     */
    public MainWindow()
    {
	super("Plot XML Editor");

	// Load menu bar.
	menuBar = new MainWindowMenuBar(this);
	this.setJMenuBar(menuBar);

	// Setup DirectedGraph control
	createNewGraph();

	layout = new FRLayout2<Decision, Dependency>(g);

	vv = new VisualizationViewer<Decision, Dependency>(layout);

	vertexFactory = new VertexFactory();
	edgeFactory = new EdgeFactory();

	vv.getRenderContext().setVertexLabelTransformer(
		new ToStringLabeller<Decision>());

	gzsp = new GraphZoomScrollPane(vv);

	// Setup tools panel.
	nodePanel = new NodePanel(g);

	// Setup horizontal split.
	splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gzsp, nodePanel);
	splitPane.setOneTouchExpandable(true);
	splitPane.setDividerLocation(550);

	// Create a graph mouse and add it to the visualization component
	PluggableGraphMouse gm = new PluggableGraphMouse();
	gm.add(new TranslatingGraphMousePlugin(MouseEvent.BUTTON2_MASK));
	gm.add(new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0,
		1.1f, 0.9f));
	gm.add(new RotatingGraphMousePlugin(MouseEvent.BUTTON2_MASK
		+ MouseEvent.SHIFT_MASK));
	gm.add(new FixedEditingGraphMousePlugin(MouseEvent.CTRL_MASK,
		vertexFactory, edgeFactory));
	gm.add(new PickingGraphMousePlugin<Decision, Dependency>());
	vv.setGraphMouse(gm);
	vv.setBackground(Color.white);

	// Setup listener for picking.
	pickedState = vv.getPickedVertexState();
	pickedState.addItemListener(this);

	this.getContentPane().add(splitPane);

	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	this.pack();

    }

    private void createNewGraph()
    {
	Graph<Decision, Dependency> newGraph = Graphs
		.<Decision, Dependency> synchronizedDirectedGraph(new DirectedSparseGraph<Decision, Dependency>());

	ObservableGraph<Decision, Dependency> newObservableGraph = new ObservableGraph<Decision, Dependency>(
		newGraph);

	// Setup the listener
	newObservableGraph
		.addGraphEventListener(new GraphEventListener<Decision, Dependency>()
		{
		    public void handleGraphEvent(
			    GraphEvent<Decision, Dependency> graphEvent)
		    {
			// An edge is added.
			if (graphEvent.getType() == GraphEvent.Type.EDGE_ADDED)
			{

			    // TODO: Check for circular references.

			    // Update target probabilities
			    graphEvent
				    .getSource()
				    .getDest(
					    ((GraphEvent.Edge<Decision, Dependency>) graphEvent)
						    .getEdge())
				    .updateDependencies(graphEvent.getSource());
			}
		    }

		});

	g = newObservableGraph;

	System.out.println(g.getClass());

	/*
	 * PlotType obj = null;
	 * 
	 * IBindingFactory bfact; try {
	 * 
	 * bfact = BindingDirectory.getFactory(PlotType.class); // TODO
	 * Auto-generated catch block
	 * 
	 * //Load the XML file IMarshallingContext mctx = null;
	 * 
	 * mctx = bfact.createMarshallingContext();
	 * 
	 * 
	 * mctx.marshalDocument(obj, "UTF-8", null, new
	 * FileOutputStream("filename.xml"));
	 * 
	 * } catch (FileNotFoundException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } catch (JiBXException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 */

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	if ("exit".equals(e.getActionCommand()))
	{
	    System.exit(0);
	}
	if ("add_decision".equals(e.getActionCommand()))
	{
	    vv.repaint();
	}
	if ("save".equals(e.getActionCommand()))
	{
	    saveNetworkAs("filename.xml");
	}
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
	Object subject = e.getItem();
	if (subject instanceof Decision)
	{
	    Decision vertex = (Decision) subject;
	    if (pickedState.isPicked(vertex))
	    {
		nodePanel.setActiveDecision(vertex);
	    }
	    else
	    {
		nodePanel.setActiveDecision(null);
	    }
	    // Makes sure everything fits.
	    splitPane.getRightComponent().validate();
	}
	if (subject instanceof Dependency)
	{

	}
    }

    public Graph<Decision, Dependency> getGraph()
    {
	return (g);
    }

    public void saveNetworkAs(String filename)
    {
	PlotType plot = new PlotType();

	// Add the dependencies from the graph.
	for (Decision d : g.getVertices())
	{
	    plot.getDecisions().add((Decision) d);
	}

	// Set the plot name.
	plot.setName("plot");

	try
	{
	    IBindingFactory bfact = BindingDirectory.getFactory(PlotType.class);

	    IMarshallingContext mctx = bfact.createMarshallingContext();

	    mctx.setIndent(2);

	    mctx.marshalDocument(plot, "UTF-8", null, new FileOutputStream(
		    filename));
	}
	catch (JiBXException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();

	}
	catch (FileNotFoundException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
