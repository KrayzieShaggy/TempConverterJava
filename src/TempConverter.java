import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;


public class TempConverter extends JFrame {
    private final JTextField celsius = new JTextField(10);
    private final JTextField farenheit = new JTextField(10);

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

        // Labels
        JLabel cLabel = new JLabel("Celsius (°C):");
        JLabel fLabel = new JLabel("Fahrenheit (°F):");

        // Tooltips
        celsius.setToolTipText("Enter a number to convert Celsius to Fahrenheit.");
        farenheit.setToolTipText("Enter a number to convert from Fahrenheit to Celsius");

        // Buttons
        JButton cToF = new JButton("C → F");
        JButton fToC = new JButton("F → C");

        // Arranging components
        gc.gridx = 0; gc.gridy = 0; form.add(cLabel, gc);
        gc.gridx = 1; gc.gridy = 0; form.add(celsius, gc);
        gc.gridx = 0; gc.gridy = 1; form.add(fLabel, gc);
        gc.gridx = 1; gc.gridy = 1; form.add(farenheit, gc);

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        buttonRow.add(cToF);
        buttonRow.add(fToC);
        gc.gridx = 0; gc.gridy = 2; gc.gridwidth = 2; form.add(buttonRow, gc);

        // Putting together
        add(form, BorderLayout.CENTER);

        // Actions
        cToF.addActionListener(this::converterCtoF);
        fToC.addActionListener(this::convertFtoC);

        // Pressing enter in text box Action
        celsius.addActionListener(this::converterCtoF);
        farenheit.addActionListener(this::convertFtoC);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static String format (double value) {
        String s = String.format(java.util.Locale.US, "%.10f", value);
        s = s.replaceAll("(\\.\\d*?[1-9])0+$", "$1").replaceAll("\\.0+$", "");
        return  s;
    }

    private static void warn(String message) {
        JOptionPane.showMessageDialog(null, message, "Input Required", JOptionPane.WARNING_MESSAGE);
    }

    private void converterCtoF(ActionEvent e){
        String text = celsius.getText().trim();
        if (text.isEmpty()) {
            warn("Please enter a Celsius value");
            return;
        }
        try{
            double c = Double.parseDouble(text);
            double f = c * 9.0 / 5.0 + 32.0;
            farenheit.setText(format(f));
        } catch (NumberFormatException ex){
            warn("Celsius must be a valid number.");
        }
    }

    private void convertFtoC(ActionEvent e){
        String text = farenheit.getText().trim();
        if(text.isEmpty()) {
            warn("Please enter a Farenheit value.");
            return;
        }
        try {
            double f = Double.parseDouble(text);
            double c = (f - 32.0) * 5.0 / 9.0;
            celsius.setText(format(c));
        } catch (NumberFormatException ex) {
            warn("Fahrenheit must be a vlaid number");
        }

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TempConverter::new);
    }
}
