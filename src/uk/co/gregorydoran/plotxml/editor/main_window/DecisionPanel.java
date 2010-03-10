package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import uk.co.gregorydoran.plotxml.editor.Decision;

public class DecisionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel = null;
	private JTextField jTextFieldName = null;
	private JLabel jLabel1 = null;
	private JTextArea jTextAreaQuestion = null;
	private JLabel jLabel2 = null;
	private JTextField jTextFieldOptions = null;
	private JLabel jLabel3 = null;
	private JPanel jPanel = null;
	
	private Decision decision;
	
	/**
	 * This is the default constructor
	 */
	public DecisionPanel(Decision d) {
		super();
		decision = d;
		initialize();
		reloadControlValues();
	}

	/**
	 * Reloads the values of the text in the fields to match those in the attached decision object.
	 */
	private void reloadControlValues()
	{
		//Load the values from
		jTextFieldName.setText(decision.getName());
		jTextAreaQuestion.setText(decision.getEnglish());
		jTextFieldOptions.setText(String.valueOf(decision.getOptions().getOptions().size()));
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 1;
		gridBagConstraints7.gridy = 6;
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.gridx = 0;
		gridBagConstraints6.gridwidth = 2;
		gridBagConstraints6.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints6.gridy = 4;
		jLabel3 = new JLabel();
		jLabel3.setText("Probabilities:");
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.fill = GridBagConstraints.BOTH;
		gridBagConstraints5.gridy = 1;
		gridBagConstraints5.weightx = 1.0;
		gridBagConstraints5.ipadx = 0;
		gridBagConstraints5.gridx = 1;
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 0;
		gridBagConstraints4.anchor = GridBagConstraints.NORTHEAST;
		gridBagConstraints4.gridy = 1;
		jLabel2 = new JLabel();
		jLabel2.setText("Number of Options:");
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.fill = GridBagConstraints.BOTH;
		gridBagConstraints3.gridy = 2;
		gridBagConstraints3.weightx = 1.0;
		gridBagConstraints3.weighty = 1.0;
		gridBagConstraints3.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints3.ipady = 30;
		gridBagConstraints3.gridheight = 1;
		gridBagConstraints3.gridx = 1;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.anchor = GridBagConstraints.NORTHEAST;
		gridBagConstraints2.gridy = 2;
		jLabel1 = new JLabel();
		jLabel1.setText("Question:");
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.BOTH;
		gridBagConstraints1.gridy = 0;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.gridx = 1;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;
		gridBagConstraints.gridy = 0;
		jLabel = new JLabel();
		jLabel.setText("Name:");
		this.setSize(728, 191);
		this.setLayout(new GridBagLayout());
		this.add(jLabel, gridBagConstraints);
		this.add(getJTextFieldName(), gridBagConstraints1);
		this.add(jLabel1, gridBagConstraints2);
		this.add(getJTextAreaQuestion(), gridBagConstraints3);
		this.add(jLabel2, gridBagConstraints4);
		this.add(getJTextFieldOptions(), gridBagConstraints5);
		this.add(jLabel3, gridBagConstraints6);
		this.add(getJPanel(), gridBagConstraints7);
	}

	/**
	 * This method initializes jTextFieldName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldName() {
		if (jTextFieldName == null) {
			jTextFieldName = new JTextField();
		}
		return jTextFieldName;
	}

	/**
	 * This method initializes jTextAreaQuestion	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextAreaQuestion() {
		if (jTextAreaQuestion == null) {
			jTextAreaQuestion = new JTextArea();
		}
		return jTextAreaQuestion;
	}

	/**
	 * This method initializes jTextFieldOptions	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldOptions() {
		if (jTextFieldOptions == null) {
			jTextFieldOptions = new JTextField();
		}
		return jTextFieldOptions;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
		}
		return jPanel;
	}

}  //  @jve:decl-index=0:visual-constraint="41,7"
