package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import uk.co.gregorydoran.plotxml.editor.Decision;
import uk.co.gregorydoran.plotxml.editor.xml_binding.OptionType;

public class DecisionPanel extends JPanel implements ActionListener
{

    private static final long serialVersionUID = 1L;
    private JLabel jLabelName = null;
    private JTextField jTextFieldName = null;
    private JLabel jLabelQuestion = null;
    private JTextArea jTextAreaQuestion = null;
    private JButton jButtonEditOptions = null;
    private JLabel jLabelProbabilities = null;
    private Decision decision;
    private ProbabilitiesControl probPanel = null;

    /**
     * This is the default constructor
     */
    public DecisionPanel(Decision d)
    {
	super();
	decision = d;
	this.setLayout(new GridBagLayout());
	initialize();
	reloadControlValues();
    }

    /**
     * Reloads the values of the text in the fields to match those in the
     * attached decision object.
     */
    private void reloadControlValues()
    {
	// Load the values from
	jTextFieldName.setText(decision.getName());
	jTextAreaQuestion.setText(decision.getEnglish());
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize()
    {

	// Name label
	jLabelName = new JLabel("Name:");
	GridBagConstraints gbcNameLabel = new GridBagConstraints();
	gbcNameLabel.gridx = 0;
	gbcNameLabel.gridy = 0;
	gbcNameLabel.anchor = GridBagConstraints.EAST;
	this.add(jLabelName, gbcNameLabel);

	// Name field
	GridBagConstraints gbcNameField = new GridBagConstraints();
	gbcNameField.gridx = 1;
	gbcNameField.gridy = 0;
	gbcNameField.anchor = GridBagConstraints.WEST;
	gbcNameField.fill = GridBagConstraints.HORIZONTAL;
	gbcNameField.weightx = 1.0;
	this.add(getJTextFieldName(), gbcNameField);

	// Question label
	jLabelQuestion = new JLabel("Question:");
	GridBagConstraints gbcQuestionLabel = new GridBagConstraints();
	gbcQuestionLabel.gridx = 0;
	gbcQuestionLabel.gridy = 1;
	gbcQuestionLabel.anchor = GridBagConstraints.EAST;
	this.add(jLabelQuestion, gbcQuestionLabel);

	// Question field
	GridBagConstraints gbcQuestionField = new GridBagConstraints();
	gbcQuestionField.gridx = 1;
	gbcQuestionField.gridy = 1;
	gbcQuestionField.anchor = GridBagConstraints.WEST;
	gbcQuestionField.fill = GridBagConstraints.HORIZONTAL;
	this.add(getJTextAreaQuestion(), gbcQuestionField);

	// Edit Options button
	jButtonEditOptions = new JButton("Edit Options");
	GridBagConstraints gbcOptionsField = new GridBagConstraints();
	gbcOptionsField.gridx = 1;
	gbcOptionsField.gridy = 2;
	gbcOptionsField.anchor = GridBagConstraints.WEST;
	jButtonEditOptions.addActionListener(this);
	this.add(jButtonEditOptions, gbcOptionsField);

	// Probabilities label
	jLabelProbabilities = new JLabel("Probabilities:");
	GridBagConstraints gbcProbabilitiesLabel = new GridBagConstraints();
	gbcProbabilitiesLabel.gridx = 0;
	gbcProbabilitiesLabel.gridy = 3;
	gbcProbabilitiesLabel.anchor = GridBagConstraints.WEST;
	gbcProbabilitiesLabel.gridwidth = 2;
	this.add(jLabelProbabilities, gbcProbabilitiesLabel);

	// Probabilities field
	GridBagConstraints gbcProbabilitiesField = new GridBagConstraints();
	gbcProbabilitiesField.gridx = 0;
	gbcProbabilitiesField.gridy = 4;
	gbcProbabilitiesField.gridwidth = 2;
	gbcProbabilitiesField.weightx = 1.0;
	gbcProbabilitiesField.fill = GridBagConstraints.BOTH;
	this.add(getProbPanel(), gbcProbabilitiesField);

    }

    /**
     * This method initializes jTextFieldName
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getJTextFieldName()
    {
	if (jTextFieldName == null)
	{
	    jTextFieldName = new JTextField();
	}
	return jTextFieldName;
    }

    /**
     * This method initializes jTextAreaQuestion
     * 
     * @return javax.swing.JTextArea
     */
    private JTextArea getJTextAreaQuestion()
    {
	if (jTextAreaQuestion == null)
	{
	    jTextAreaQuestion = new JTextArea();
	}
	return jTextAreaQuestion;
    }

    /**
     * This method initializes probPanel
     * 
     * @return java.awt.Panel
     */
    private ProbabilitiesControl getProbPanel()
    {
	if (probPanel == null)
	{
	    probPanel = new ProbabilitiesControl(decision.getProbabilities(),
		    decision.getDependencies().getDecisions());
	}
	return probPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	editOptions();
    }

    /**
     * Returns the parent JFrame which ultimately owns this container.
     * 
     * @return The parent JFrame.
     */
    private JFrame getParentWindow()
    {
	Container p = this.getParent();
	while (p.getParent() != null)
	{
	    p = p.getParent();
	}
	return (JFrame) (p);
    }

    public void editOptions()
    {
	// Bit of a messy cast, but this should work. I'm in a rush and haven't
	// got much time. You'd to the same, rather be writing the game.
	OptionsEditor optionsEditorDialog = new OptionsEditor(
		getParentWindow(), decision);

	List<OptionType> newOptions = optionsEditorDialog.showEditOptions();

	if (newOptions != null)
	{
	    decision.getOptions().setOptions(newOptions);
	}

    }
}
