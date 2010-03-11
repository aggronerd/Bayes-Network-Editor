package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import uk.co.gregorydoran.plotxml.editor.Decision;

public class DecisionPanel extends JPanel
{

    private static final long serialVersionUID = 1L;
    private JLabel jLabelName = null;
    private JTextField jTextFieldName = null;
    private JLabel jLabelQuestion = null;
    private JTextArea jTextAreaQuestion = null;
    private JLabel jLabelOptions = null;
    private JTextField jTextFieldOptions = null;
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
	jTextFieldOptions.setText(String.valueOf(decision.getOptions()
		.getOptions().size()));
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

	// Options label
	jLabelOptions = new JLabel("Number of Options:");
	GridBagConstraints gbcOptionsLabel = new GridBagConstraints();
	gbcOptionsLabel.gridx = 0;
	gbcOptionsLabel.gridy = 2;
	gbcOptionsLabel.anchor = GridBagConstraints.EAST;
	this.add(jLabelOptions, gbcOptionsLabel);

	// Options field
	GridBagConstraints gbcOptionsField = new GridBagConstraints();
	gbcOptionsField.gridx = 1;
	gbcOptionsField.gridy = 2;
	gbcOptionsField.anchor = GridBagConstraints.WEST;
	gbcOptionsField.fill = GridBagConstraints.HORIZONTAL;
	this.add(getJTextFieldOptions(), gbcOptionsField);

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
     * This method initializes jTextFieldOptions
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getJTextFieldOptions()
    {
	if (jTextFieldOptions == null)
	{
	    jTextFieldOptions = new JTextField();
	}
	return jTextFieldOptions;
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

}
