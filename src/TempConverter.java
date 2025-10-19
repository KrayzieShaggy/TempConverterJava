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
        JButton cToF = new JButton("C -> F");
        JButton fToC = new JButton("F -> C");

        // Arranging components
        gc.gridx = 0; gc.gridy = 0; form.add(cLabel, gc);
        gc.gridx = 1; gc.gridy = 0; form.add(celsius, gc);
        gc.gridx = 0; gc.gridy = 1; form.add(fLabel, gc);
        gc.gridx = 1; gc.gridy = 1; form.add(farenheit, gc);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TempConverter::new);
    }
}
