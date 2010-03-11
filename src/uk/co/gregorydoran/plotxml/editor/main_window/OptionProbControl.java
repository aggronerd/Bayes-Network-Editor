package uk.co.gregorydoran.plotxml.editor.main_window;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import uk.co.gregorydoran.plotxml.editor.xml_binding.ProbType;

public class OptionProbControl extends JPanel
{

    private static final long serialVersionUID = 1L;
    private JLabel jLabel = null;
    private JSlider jSlider = null;
    private JTextField jTextField = null;

    private ProbType probType;

    public static final int SLIDER_MAX = 100000;

    /**
     * This is the default constructor
     */
    public OptionProbControl(ProbType p)
    {
	super();
	probType = p;
	initialize();

	// Sets the initial values of the controls
	jLabel.setText(probType.getOptionName());
	jTextField.setText(String.valueOf(probType.getValue()));
	jSlider
		.setValue(Math
			.round(jSlider.getMaximum() / probType.getValue()));
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize()
    {
	GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
	gridBagConstraints2.gridy = 1;
	gridBagConstraints2.anchor = GridBagConstraints.EAST;
	// gridBagConstraints2.weightx = 0.0;
	gridBagConstraints2.gridwidth = 1;
	// gridBagConstraints2.ipady = 0;
	gridBagConstraints2.ipadx = 50;
	gridBagConstraints2.gridx = 2;
	GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
	gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
	gridBagConstraints1.gridy = 1;
	gridBagConstraints1.weightx = 2.0;
	gridBagConstraints1.gridx = 1;
	GridBagConstraints gridBagConstraints = new GridBagConstraints();
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 1;
	jLabel = new JLabel();
	jLabel.setText("option");
	this.setLayout(new GridBagLayout());
	this.add(jLabel, gridBagConstraints);
	this.add(getJSlider(), gridBagConstraints1);
	this.add(getJTextField(), gridBagConstraints2);
    }

    /**
     * This method initializes jSlider
     * 
     * @return javax.swing.JSlider
     */
    private JSlider getJSlider()
    {
	if (jSlider == null)
	{
	    jSlider = new JSlider();
	    jSlider.setMaximum(SLIDER_MAX);
	    jSlider.addChangeListener(new javax.swing.event.ChangeListener()
	    {
		public void stateChanged(javax.swing.event.ChangeEvent e)
		{

		    // Update the attached probType object value.
		    probType.setValue(new Float(((double) jSlider.getValue())
			    / ((double) jSlider.getMaximum())));

		    // Updates the value in the text box.
		    jTextField.setText(String.valueOf(probType.getValue()));
		}
	    });
	}
	return jSlider;
    }

    /**
     * This method initializes jTextField
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField()
    {
	if (jTextField == null)
	{
	    jTextField = new JTextField();
	    jTextField.addKeyListener(new java.awt.event.KeyAdapter()
	    {
		public void keyTyped(java.awt.event.KeyEvent e)
		{
		    try
		    {
			// Attempt to interpret the string as a float.
			probType.setValue(Float
				.parseFloat(jTextField.getText()));

			// Update the slider bar control.
			jSlider.setValue(Math.round(jSlider.getMaximum()
				/ probType.getValue()));

		    }
		    catch (NumberFormatException err)
		    {
			// The text box doesn't contain a float
			// do nothing - we just ignore the new value.
		    }
		}
	    });
	}
	return jTextField;
    }

}
