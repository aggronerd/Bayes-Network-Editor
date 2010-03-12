package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import uk.co.gregorydoran.plotxml.editor.Decision;
import uk.co.gregorydoran.plotxml.editor.xml_binding.OptionType;

public class OptionsEditor extends JDialog implements ActionListener
{

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JScrollPane jScrollPane = null;
    private JTable jTableOptions = null;
    private JButton jButtonAdd = null;
    private JButton jButtonRemove = null;
    private JPanel jPanelOptionsControls = null;
    private JPanel jPanelButtons = null;
    private JButton jButtonOk = null;
    private JButton jButtonCancel = null;

    private Decision decision = null;
    private List<OptionType> options;

    private boolean cancelled = false;

    /**
     * @param owner
     */
    public OptionsEditor(JFrame owner, Decision d)
    {
	super(owner, true);
	decision = d;
	options = new ArrayList<OptionType>(decision.getOptions().getOptions());

	initialize();
    }

    /**
     * Displays the dialog and returns the new OptionsType object or null if the
     * user decides to cancel.
     * 
     * @return
     */
    public List<OptionType> showEditOptions()
    {
	this.setVisible(true);
	if (cancelled)
	{
	    return (null);
	}
	else
	{
	    return (options);
	}
    }

    /**
     * This method initialises this
     * 
     * @return void
     */
    private void initialize()
    {
	this.setSize(729, 295);
	this.setContentPane(getJContentPane());
    }

    /**
     * This method initialises jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane()
    {
	if (jContentPane == null)
	{
	    BorderLayout borderLayout = new BorderLayout();
	    borderLayout.setHgap(5);
	    borderLayout.setVgap(5);
	    jContentPane = new JPanel();
	    jContentPane.setLayout(borderLayout);
	    jContentPane.add(getJScrollPane(), BorderLayout.CENTER);
	    jContentPane.add(getJPanelOptionsControls(), BorderLayout.EAST);
	    jContentPane.add(getJPanelButtons(), BorderLayout.SOUTH);
	}
	return jContentPane;
    }

    /**
     * This method initialises jScrollPane
     * 
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane()
    {
	if (jScrollPane == null)
	{
	    jScrollPane = new JScrollPane();
	    jScrollPane.setViewportView(getJTableOptions());
	}
	return jScrollPane;
    }

    /**
     * This method initialises jTableOptions
     * 
     * @return javax.swing.JTable
     */
    private JTable getJTableOptions()
    {
	if (jTableOptions == null)
	{
	    String[][] content = { { "murder", "Murder" },
		    { "assassination", "Assassination" } };
	    String[] headers = { "Name", "Answer" };
	    jTableOptions = new JTable(content, headers);
	}
	return jTableOptions;
    }

    /**
     * This method initialises jButtonAdd
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonAdd()
    {
	if (jButtonAdd == null)
	{
	    jButtonAdd = new JButton();
	    jButtonAdd.setText("Add");
	}
	return jButtonAdd;
    }

    /**
     * This method initialises jButtonRemove
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonRemove()
    {
	if (jButtonRemove == null)
	{
	    jButtonRemove = new JButton();
	    jButtonRemove.setText("Remove");
	}
	return jButtonRemove;
    }

    /**
     * This method initialises jPanelOptionsControls
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJPanelOptionsControls()
    {
	if (jPanelOptionsControls == null)
	{
	    GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
	    gridBagConstraints1.gridx = 0;
	    gridBagConstraints1.gridy = 1;
	    gridBagConstraints1.weightx = 0.0;
	    gridBagConstraints1.anchor = GridBagConstraints.WEST;
	    GridBagConstraints gridBagConstraints = new GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.anchor = GridBagConstraints.CENTER;
	    gridBagConstraints.weightx = 0.0;
	    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
	    gridBagConstraints.insets = new Insets(0, 0, 0, 0);
	    gridBagConstraints.gridy = 0;
	    jPanelOptionsControls = new JPanel();
	    jPanelOptionsControls.setLayout(new GridBagLayout());
	    jPanelOptionsControls.add(getJButtonRemove(), gridBagConstraints1);
	    jPanelOptionsControls.add(getJButtonAdd(), gridBagConstraints);
	}
	return jPanelOptionsControls;
    }

    /**
     * This method initialises jPanelButtons
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJPanelButtons()
    {
	if (jPanelButtons == null)
	{
	    GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
	    gridBagConstraints3.ipadx = 30;
	    gridBagConstraints3.insets = new Insets(0, 0, 0, 0);
	    gridBagConstraints3.anchor = GridBagConstraints.EAST;
	    gridBagConstraints3.weightx = 0.0;
	    GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
	    gridBagConstraints2.ipadx = 40;
	    gridBagConstraints2.anchor = GridBagConstraints.EAST;
	    jPanelButtons = new JPanel();
	    jPanelButtons.setLayout(new GridBagLayout());
	    jPanelButtons.add(getJButtonOk(), gridBagConstraints2);
	    jPanelButtons.add(getJButtonCancel(), gridBagConstraints3);
	}
	return jPanelButtons;
    }

    /**
     * This method initialises jButtonOk
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonOk()
    {
	if (jButtonOk == null)
	{
	    jButtonOk = new JButton();
	    jButtonOk.setText("Ok");
	}
	return jButtonOk;
    }

    /**
     * This method initialises jButtonCancel
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonCancel()
    {
	if (jButtonCancel == null)
	{
	    jButtonCancel = new JButton();
	    jButtonCancel.setText("Cancel");
	    jButtonCancel.addActionListener(new java.awt.event.ActionListener()
	    {
		public void actionPerformed(java.awt.event.ActionEvent e)
		{
		    System.out.println("actionPerformed()"); // TODO
							     // Auto-generated
							     // Event stub
							     // actionPerformed()
		}
	    });
	}
	return jButtonCancel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	// TODO Auto-generated method stub

    }

} // @jve:decl-index=0:visual-constraint="10,10"
