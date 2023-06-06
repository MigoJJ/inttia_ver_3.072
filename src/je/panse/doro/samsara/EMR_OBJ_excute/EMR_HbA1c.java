package je.panse.doro.samsara.EMR_OBJ_excute;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import je.panse.doro.GDSEMR_frame;

public class EMR_HbA1c extends JFrame implements ActionListener {
    private JTextField[] inputFields;
    private JTextArea outputArea;
    private JButton clearButton, saveButton, quitButton;

    public EMR_HbA1c() {

        setTitle("EMR Interface for HbA1c Input");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocation(1460, 450);
        setSize(new Dimension(1000, 600));

        inputFields = new JTextField[3];
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        String[] labels = { "FBS/PP2  ", "Glucose [mg/dL]  ", "HbA1c [ % ]  " };
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setHorizontalAlignment(SwingConstants.RIGHT);
            inputPanel.add(label);

            inputFields[i] = new JTextField();
            inputFields[i].setHorizontalAlignment(JTextField.CENTER);
            inputFields[i].addActionListener(this);
            inputPanel.add(inputFields[i]);
        }

        add(inputPanel, BorderLayout.CENTER);
        outputArea = new JTextArea();
        outputArea = new JTextArea(4, 30);
        add(outputArea, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);
        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add key listener to inputFields[2] to handle Enter key press
        inputFields[2].addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    actionPerformed(new ActionEvent(inputFields[2], ActionEvent.ACTION_PERFORMED, null));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public static void main(String[] args) {
        EMR_HbA1c emr = new EMR_HbA1c();
        emr.setSize(250, 250);
        emr.setVisible(true);
    }

    private void clearInputFields() {
        for (JTextField textField : inputFields) {
            textField.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == clearButton) {
            clearInputFields();
            outputArea.setText("");
        } else if (source == saveButton) {
        	  String input = Arrays.toString(inputFields);
              // Save input to file or database
              outputArea.setText("Input saved: " + input);
        } else if (source == quitButton) {
            dispose();
        } else {
            JTextField textField = (JTextField) source;
            int index = Arrays.asList(inputFields).indexOf(textField);
            if (index < inputFields.length - 1) {
                inputFields[index + 1].requestFocus();
            } else {
                double fbs_pp2 = Double.parseDouble(inputFields[0].getText());
                double glucose_mgdl = Double.parseDouble(inputFields[1].getText());
                String hba1cText = inputFields[2].getText();
                String returnFBS = myString(fbs_pp2);

                if (!hba1cText.isEmpty()) {
                    try {
                        double hba1c_perc = Double.parseDouble(hba1cText);
                        double ifcc_hba1c_mmolmol = (hba1c_perc - 2.15) * 10.929;
                        double eag_mgdl = (28.7 * hba1c_perc) - 46.7;
                        double eag_mmoll = eag_mgdl / 18.01559;

                        String outputText = String.format("\n" + returnFBS + " [ %.0f ] mg/dL   " + "HbA1c [ %.1f ]%%\n"
                                + "\tIFCC HbA1c: %.0f mmol/mol\n" + "\teAG: %.0f mg/dL\n" + "\teAG: %.1f mmol/l\n",
                                glucose_mgdl, hba1c_perc, ifcc_hba1c_mmolmol, eag_mgdl, eag_mmoll);

                        outputArea.setText(outputText);
                        GDSEMR_frame.setTextAreaText(5, outputText);
                        getGlucoseControlStatus(hba1c_perc);
                    } catch (NumberFormatException ex) {
                        System.err.println("Invalid HbA1c value: " + hba1cText);
                        // Additional error handling if needed
                    }
                } else {
                    String outputText = String.format("\n" + returnFBS + " [ %.0f ] mg/dL   ", glucose_mgdl);
                    outputArea.setText(outputText);
                    GDSEMR_frame.setTextAreaText(5, outputText);
                }
            }
        }
    }

    public String myString(double fbspp2) {
        if (fbspp2 == 0) {
            return "FBS";
        }
        return "PP" + fbspp2;
    }

    public static void getGlucoseControlStatus(double HbA1c) {
        String status;
        if (HbA1c > 9.0) {
            status = "Poor";
        } else if (HbA1c >= 7.0 && HbA1c <= 9.0) {
            status = "Fair";
        } else if (HbA1c >= 6.0 && HbA1c < 7.0) {
            status = "Good";
        } else {
            status = "Excellent";
        }

        String message = String.format("\n...now [ %s ] treated DM with current medication", status);
        GDSEMR_frame.setTextAreaText(8, message);
        System.out.println(message);
    }
}
