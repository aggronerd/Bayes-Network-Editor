package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import uk.co.gregorydoran.plotxml.editor.xml_binding.Decision;
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

    private Decision decision = null;
    DefaultTableModel tableData;

    /**
     * @param owner
     */
    public OptionsEditor(JFrame owner, Decision d)
    {
	super(owner, true);
	decision = d;
	initialize();
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
	if (tableData == null)
	{
	    tableData = new DefaultTableModel();
	}

	if (jTableOptions == null)
	{
	    // Prepare the table.
	    String[] headers = { "Name", "English" };
	    tableData.setColumnIdentifiers(headers);
	    jTableOptions = new JTable(tableData);

	    refreshTable();

	}
	return jTableOptions;
    }

    /**
     * Recreates the table based on the options in the options List.
     */
    public void refreshTable()
    {
	// Load the data into the table.
	String[] content = new String[2];
	tableData.setRowCount(0);
	for (OptionType o : decision.getOptions().getOptions())
	{
	    content[0] = o.getName();
	    content[1] = o.getEnglish();
	    tableData.addRow(content);
	}
    }

    /**
     * Updates the options List based on the contents of the table.
     */
    public void updateOptions()
    {
	for (int i = 0; i < tableData.getRowCount(); i++)
	{
	    decision.getOptions().getOptions().get(i).setName(
		    (String) tableData.getValueAt(i, 0));
	    decision.getOptions().getOptions().get(i).setEnglish(
		    (String) tableData.getValueAt(i, 1));
	}
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
	    jButtonAdd.addActionListener(new java.awt.event.ActionListener()
	    {
		public void actionPerformed(java.awt.event.ActionEvent e)
		{
		    OptionType newOption = new OptionType();
		    newOption.setName("change_me");
		    newOption.setEnglish("");
		    decision.getOptions().getOptions().add(newOption);
		    refreshTable();

		    // TODO: Add call to update recidual givens.
		}
	    });
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
	    jButtonRemove.addActionListener(new java.awt.event.ActionListener()
	    {
		public void actionPerformed(java.awt.event.ActionEvent e)
		{
		    if (jTableOptions.getSelectedRow() >= 0)
		    {
			// Removes the selected row.
			decision.getOptions().getOptions().remove(
				jTableOptions.getSelectedRow());
			refreshTable();

			decision.updateDependencies(((MainWindow) getParent())
				.getGraph());
		    }
		}
	    });
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
	    GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
	    gridBagConstraints2.ipadx = 40;
	    gridBagConstraints2.anchor = GridBagConstraints.EAST;
	    jPanelButtons = new JPanel();
	    jPanelButtons.setLayout(new GridBagLayout());
	    jPanelButtons.add(getJButtonOk(), gridBagConstraints2);
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
	    jButtonOk.addActionListener(new java.awt.event.ActionListener()
	    {
		public void actionPerformed(java.awt.event.ActionEvent e)
		{
		    // Replace the options data with the latest values in the
		    // database.
		    updateOptions();
		    setVisible(false);
		}
	    });
	}
	return jButtonOk;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	// TODO Auto-generated method stub

    }

} // @jve:decl-index=0:visual-constraint="10,10"
