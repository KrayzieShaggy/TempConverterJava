import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Simple GUI that converts temperatures between
 * Celsius and Fharenheit
*/

public class TempConverter extends JFrame {
    private final JTextField celsius = new JTextField(10);
    private final JTextField farenheit = new JTextField(10);

    // Constructor to build and display the GUI

    public TempConverter () {
        super("Temperature Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Left side form
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(new EmptyBorder(16,16,16, 16));
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6,6,6,6);
        gc.fill = GridBagConstraints.HORIZONTAL;

        // Create labels for the text fields

        JLabel cLabel = new JLabel("Celsius (°C):");
        JLabel fLabel = new JLabel("Fahrenheit (°F):");

        // Create tool tips when hovering over text boxes
        ToolTipManager.sharedInstance().setInitialDelay(200);
        ToolTipManager.sharedInstance().setDismissDelay(5000);
        celsius.setToolTipText("Enter a number to convert Celsius to Fahrenheit.");
        farenheit.setToolTipText("Enter a number to convert from Fahrenheit to Celsius");

        // Converter Buttons
        JButton cToF = new JButton("C → F");
        JButton fToC = new JButton("F → C");

        // Arranging components
        gc.gridx = 0; gc.gridy = 0; form.add(cLabel, gc);
        gc.gridx = 1; gc.gridy = 0; form.add(celsius, gc);
        gc.gridx = 0; gc.gridy = 1; form.add(fLabel, gc);
        gc.gridx = 1; gc.gridy = 1; form.add(farenheit, gc);

        // Creating a row for bottons
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        buttonRow.add(cToF);
        buttonRow.add(fToC);

        // Add to the center of the main frame
        gc.gridx = 0; gc.gridy = 2; gc.gridwidth = 2; form.add(buttonRow, gc);

        // Putting together
        add(form, BorderLayout.CENTER);

        // Image to display on the right side of GUI
        try {
            // Load the Thermometer image from file path
            ImageIcon icon = new ImageIcon("src/Thermometer.png");

            // Scale for better display
            Image scaled = icon.getImage().getScaledInstance(120,-1, Image.SCALE_SMOOTH);

            // JLable to hold the image
            JLabel pic = new JLabel(new ImageIcon(scaled));
            pic.setBorder(new EmptyBorder(10,10,10,10));

            // Place image to the right side of window
            add(pic, BorderLayout.EAST);
        } catch (Exception ex) {
            // Error message if image does not load
            System.err.println("Could not load image: " + ex.getMessage());
        }


        // Button actions when pressed
        cToF.addActionListener(this::converterCtoF);
        fToC.addActionListener(this::convertFtoC);

        // Pressing enter in text box Action
        celsius.addActionListener(this::converterCtoF);
        farenheit.addActionListener(this::convertFtoC);

        //Set up Window
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Format a double value by trimming trailing zeros
     * @param value the number to format
     * @return a clean string version of number
     */

    private static String format (double value) {
        String s = String.format(java.util.Locale.US, "%.10f", value);
        s = s.replaceAll("(\\.\\d*?[1-9])0+$", "$1").replaceAll("\\.0+$", "");
        return  s;
    }

    /**
     * Display a pop-up warning messge
     * @param message the message text to show
     */
    private static void warn(String message) {
        JOptionPane.showMessageDialog(null, message, "Input Required", JOptionPane.WARNING_MESSAGE);
    }

    /**
     *Converts Celsius to Fahrenheit when users clicks "C → F" button
     */
    private void converterCtoF(ActionEvent e){
        String text = celsius.getText().trim();

        // prevents empty input
        if (text.isEmpty()) {
            warn("Please enter a Celsius value");
            return;
        }
        try{
            // Parse the Celsius input
            double c = Double.parseDouble(text);

            // Fahrenheit Conversion
            double f = c * 9.0 / 5.0 + 32.0;

            //Display results in Fahrenheit label
            farenheit.setText(format(f));
        } catch (NumberFormatException ex){

            // Handles invalid input
            warn("Celsius must be a valid number.");
        }
    }

    /**
     *Converts Fahrenheit to Celsius when uses clicks "F → C" button
     */
    private void convertFtoC(ActionEvent e) {
        String text = farenheit.getText().trim();

        // Prevent empty input
        if (text.isEmpty()) {
            warn("Please enter a Fahrenheit value.");
            return;
        }

        try {
            // Parse Fahrenheit input
            double f = Double.parseDouble(text);

            // Convert to Celsius
            double c = (f - 32.0) * 5.0 / 9.0;

            // Display result
            celsius.setText(format(c));

        } catch (NumberFormatException ex) {
            // Handle invalid input
            warn("Fahrenheit must be a valid number");
        }
    }

    /**
     * Main method that launches the program.
     */
    public static void main(String[] args) {
        // Run GUI safely on the Swing event dispatch thread
        SwingUtilities.invokeLater(TempConverter::new);
    }
}
