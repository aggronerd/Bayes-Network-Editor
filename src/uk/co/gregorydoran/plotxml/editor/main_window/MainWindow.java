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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import org.apache.commons.collections15.Factory;
import org.apache.log4j.Logger;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
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
public class MainWindow extends JFrame implements ActionListener, ItemListener,
	GraphEventListener<Decision, Dependency>
{

    private static final Logger log = Logger.getLogger(MainWindow.class);

    private static final long serialVersionUID = 9118006988703782991L;

    // Jung objects
    private Graph<Decision, Dependency> g = null;
    private FRLayout2<Decision, Dependency> layout = null;

    // GUI Controls
    private VisualizationViewer<Decision, Dependency> vv = null;
    private MainWindowMenuBar menuBar = null;
    private GraphZoomScrollPane gzsp = null;
    private JSplitPane splitPane = null;
    private NodePanel nodePanel = null;
    private PickedState<Decision> pickedState = null;

    // Factories
    private Factory<Decision> vertexFactory = null;
    private Factory<Dependency> edgeFactory = null;

    // Plot
    private PlotType currentPlot = null;
    private boolean plotSaved = false;
    private File plotFile = null;

    private boolean isOpeningFile = false;

    /**
     * Creates the window and controls.
     */
    public MainWindow()
    {
	super("Belief Network Editor");

	log.debug("Contructor called");

	// Load menu bar.
	menuBar = new MainWindowMenuBar(this);
	this.setJMenuBar(menuBar);

	// Create a new plot
	createNewPlot();

	splitPane.setDividerLocation(550);

	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	this.pack();

    }

    /**
     * Creates a new plot to replace the current one and updates all the
     * controls.
     */
    public void createNewPlot()
    {
	currentPlot = new PlotType();
	currentPlot.setName("New Network");
	refreshTitle();

	// Factories used for creation of graph objects
	vertexFactory = new VertexFactory();
	edgeFactory = new EdgeFactory();

	// Setup new graph and controls
	resetControls();

	plotSaved = false;
    }

    /**
     * Resets the title to include the plot object's name.
     */
    private void refreshTitle()
    {
	this.setTitle("Plot Belief Network Editor - " + currentPlot.getName());
    }

    /**
     * Creates new controls for the current plot.
     * 
     */
    private void resetControls()
    {
	this.setVisible(false);

	// Removes all existing.
	log.debug("Removing all existing components");
	if (splitPane != null)
	{
	    this.remove(splitPane);
	}
	if (splitPane != null)
	{
	    splitPane.removeAll();
	}
	if (gzsp != null)
	{
	    gzsp.removeAll();
	}
	if (vv != null)
	{
	    vv.removeAll();
	}
	if (nodePanel != null)
	{
	    nodePanel.removeAll();
	}

	// Create the directed graph and wrap in the observable graph class.
	Graph<Decision, Dependency> newGraph = Graphs
		.<Decision, Dependency> synchronizedDirectedGraph(new DirectedSparseGraph<Decision, Dependency>());
	ObservableGraph<Decision, Dependency> newObservableGraph = new ObservableGraph<Decision, Dependency>(
		newGraph);

	// Setup this class as the listener.
	newObservableGraph.addGraphEventListener(this);

	g = newObservableGraph;

	// Create the actual control
	layout = new FRLayout2<Decision, Dependency>(g);
	vv = new VisualizationViewer<Decision, Dependency>(layout);
	vv.getRenderContext().setVertexLabelTransformer(
		new ToStringLabeller<Decision>());
	gzsp = new GraphZoomScrollPane(vv);

	log.debug("New graph and visualisation created.");

	// Setup tools panel.
	nodePanel = new NodePanel();

	// Setup horizontal split.
	splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gzsp, nodePanel);
	splitPane.setOneTouchExpandable(true);

	// Create a graph mouse and add it to the visualisation component
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

	// Setup listener for picking
	pickedState = vv.getPickedVertexState();
	pickedState.addItemListener(this);

	this.getContentPane().add(splitPane);

	this.setVisible(true);
    }

    /**
     * Captures when an action is performed in the menu bar or buttons in the
     * window.
     * 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {

	if ("exit".equals(e.getActionCommand()))
	{
	    System.exit(0);
	}

	else if ("rename_plot".equals(e.getActionCommand()))
	{
	    String newName = JOptionPane.showInputDialog(this, "Plot name:",
		    this.getCurrentPlot().getName());

	    // Test the new plot title.
	    if (newName != null && newName.trim() != "")
	    {
		this.getCurrentPlot().setName(newName);

		// Title displays name of the plot.
		refreshTitle();
	    }
	}

	else if ("save".equals(e.getActionCommand()))
	{
	    if (plotFile != null)
	    {
		log.debug("Saving to old file '" + plotFile.getAbsolutePath()
			+ "'");
		saveNetworkAs(plotFile.getAbsolutePath());
	    }
	    else
	    {
		showSaveAs();
	    }

	}

	else if ("save_as".equals(e.getActionCommand()))
	{
	    showSaveAs();
	}

	else if ("new".equals(e.getActionCommand()))
	{
	    if ((plotSaved != true)
		    && (JOptionPane
			    .showConfirmDialog(
				    this,
				    "Changes have not been saved. Are you sure you want to do this?",
				    "New Plot", JOptionPane.YES_NO_OPTION) != 0))
	    {
		return;
	    }
	    createNewPlot();
	    plotFile = null;
	}

	else if ("delete_decision".equals(e.getActionCommand()))
	{
	    /**
	     * Deletes the current decision
	     */
	    log.debug("'delete_decision' action command called");

	    if (nodePanel.getActiveDecision() != null)
	    {
		log.debug("Proceeding to delete "
			+ nodePanel.getActiveDecision());
		nodePanel.getActiveDecision().delete(g);
		nodePanel.setActiveDecision(null);
	    }
	    else
	    {
		log.debug("No node selected - cannot delete.");
	    }
	}

	else if ("open".equals(e.getActionCommand()))
	{
	    JFileChooser fc = new JFileChooser();
	    fc.setFileFilter(new XMLFileFilter());

	    int returnVal = fc.showOpenDialog(this);

	    if (returnVal == JFileChooser.APPROVE_OPTION)
	    {
		File file = fc.getSelectedFile();
		log.debug("Opening: " + file.getName());
		this.openNetwork(file.getAbsolutePath());
		plotFile = file;
	    }
	    else
	    {
		log.debug("Open command cancelled by user");
	    }
	}

    }

    private void showSaveAs()
    {
	JFileChooser fc = new JFileChooser();
	fc.setFileFilter(new XMLFileFilter());

	int returnVal = fc.showSaveDialog(this);

	if (returnVal == JFileChooser.APPROVE_OPTION)
	{
	    plotFile = fc.getSelectedFile();
	    log.debug("Saving plot: " + plotFile.getAbsolutePath());
	    this.saveNetworkAs(plotFile.getAbsolutePath());
	}
	else
	{
	    log.debug("Save as command cancelled by user");
	}
    }

    /**
     * Captures item state changes from the graph's vertices and edges.
     * 
     */
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
	else if (subject instanceof Dependency)
	{

	}
	else
	{

	}
    }

    /**
     * Returns the current graph.
     * 
     * @return
     */
    public Graph<Decision, Dependency> getGraph()
    {
	return (g);
    }

    /**
     * Saves the XML file for the current graph in the filename specified.
     * 
     * @param filename
     */
    public void saveNetworkAs(String filename)
    {

	// Add the dependencies from the graph.
	currentPlot.getDecisions().clear();
	for (Decision d : g.getVertices())
	{
	    d.setyPosition(layout.getY(d));
	    d.setxPosition(layout.getX(d));
	    currentPlot.getDecisions().add((Decision) d);
	}

	try
	{
	    IBindingFactory bfact = BindingDirectory.getFactory(PlotType.class);

	    IMarshallingContext mctx = bfact.createMarshallingContext();

	    mctx.setIndent(2);

	    mctx.marshalDocument(currentPlot, "UTF-8", null,
		    new FileOutputStream(filename));
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

	plotSaved = true;

    }

    /**
     * Opens the file specified in the filename string.
     * 
     * @param filename
     */
    public void openNetwork(String filename)
    {
	isOpeningFile = true;

	// Prepare binding objects.
	IBindingFactory bfact;

	try
	{
	    bfact = BindingDirectory.getFactory(PlotType.class);
	    IUnmarshallingContext umctx;
	    umctx = bfact.createUnmarshallingContext();

	    // Load the objects from the xml file
	    currentPlot = (PlotType) umctx.unmarshalDocument(new FileReader(
		    new File(filename)));

	    // Reset the graph and objects.
	    resetControls();
	    refreshTitle();

	    // Add decisions found in the file to the graph.
	    for (Decision d : currentPlot.getDecisions())
	    {
		g.addVertex(d);
		layout.setLocation(d, d.getxPosition(), d.getyPosition());
	    }

	    // Link the decisions.
	    for (Decision d : currentPlot.getDecisions())
	    {
		for (Decision dependency : d.getDependencies().getDecisions())
		{
		    g.addEdge(new Dependency(), dependency, d);
		}
	    }

	    this.repaint();

	    isOpeningFile = false;

	}
	catch (FileNotFoundException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	catch (JiBXException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Handles events from the graph.
     */
    @Override
    public void handleGraphEvent(GraphEvent<Decision, Dependency> graphEvent)
    {
	// An edge is added.
	if (graphEvent.getType() == GraphEvent.Type.EDGE_ADDED)
	{
	    if (!isOpeningFile)
	    {

		// TODO: Check for circular references.

		// Update target dependencies
		graphEvent.getSource().getDest(
			((GraphEvent.Edge<Decision, Dependency>) graphEvent)
				.getEdge()).updateDependencies(
			graphEvent.getSource());

		// Update target probabilities
		graphEvent.getSource().getDest(
			((GraphEvent.Edge<Decision, Dependency>) graphEvent)
				.getEdge()).updateProbabilities();

	    }
	}
    }

    public PlotType getCurrentPlot()
    {
	return (currentPlot);
    }
}
