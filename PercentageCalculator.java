import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PercentageCalculator {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Percentage Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);

        // Create the panel for components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));

        // Create labels and input fields
        JLabel instructionLabel = new JLabel("Enter the values and select the calculation type.");
        JLabel valueLabel1 = new JLabel("Value 1:");
        JTextField valueField1 = new JTextField(10);
        
        JLabel valueLabel2 = new JLabel("Value 2 (optional):");
        JTextField valueField2 = new JTextField(10);
        
        JLabel resultLabel = new JLabel("Result:");
        JLabel resultValueLabel = new JLabel("-");
        
        String[] operations = { "Calculate Percentage", "Increase/Decrease Percentage", "Find Whole" };
        JComboBox<String> operationComboBox = new JComboBox<>(operations);

        JButton calculateButton = new JButton("Calculate");

        // Add components to panel
        panel.add(instructionLabel);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(valueLabel1);
        panel.add(valueField1);
        panel.add(valueLabel2);
        panel.add(valueField2);
        panel.add(operationComboBox);
        panel.add(calculateButton);
        panel.add(resultLabel);
        panel.add(resultValueLabel);

        // Add the panel to the frame
        frame.add(panel);

        // Display the frame
        frame.setVisible(true);

        // Add action listener to calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value1Text = valueField1.getText().trim();
                String value2Text = valueField2.getText().trim();
                String selectedOperation = (String) operationComboBox.getSelectedItem();
                double value1, value2;

                // Error handling for invalid input
                try {
                    value1 = Double.parseDouble(value1Text);
                    if (selectedOperation.equals("Increase/Decrease Percentage") || selectedOperation.equals("Find Whole")) {
                        if (value2Text.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Please enter Value 2.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        value2 = Double.parseDouble(value2Text);
                    } else {
                        value2 = 0;
                    }

                    // Perform the selected calculation
                    double result = 0;
                    switch (selectedOperation) {
                        case "Calculate Percentage":
                            result = calculatePercentage(value1, value2);
                            break;
                        case "Increase/Decrease Percentage":
                            result = calculatePercentageChange(value1, value2);
                            break;
                        case "Find Whole":
                            result = findWhole(value1, value2);
                            break;
                    }

                    // Display result
                    resultValueLabel.setText(String.format("%.2f", result));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numerical values.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Method to calculate percentage (value1 is the total, value2 is the percentage)
    public static double calculatePercentage(double total, double percentage) {
        return (total * percentage) / 100;
    }

    // Method to calculate percentage increase or decrease (value1 is the initial value, value2 is the change in percentage)
    public static double calculatePercentageChange(double initialValue, double percentageChange) {
        return initialValue * (1 + percentageChange / 100);
    }

    // Method to find the whole (value1 is the part, value2 is the percentage)
    public static double findWhole(double part, double percentage) {
        return (part * 100) / percentage;
    }
}
