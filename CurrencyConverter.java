
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends JFrame {
    private JTextField amountField;
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JLabel resultLabel;
    
    private Map<String, Double> exchangeRates;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Initialize exchange rates
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.75);
        exchangeRates.put("INR", 74.57);
        exchangeRates.put("AUD", 1.34);

        // Create UI components
        amountField = new JTextField();
        fromCurrency = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR", "AUD"});
        toCurrency = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR", "AUD"});
        resultLabel = new JLabel("Converted Amount: ");
        JButton convertButton = new JButton("Convert");

        // Add action listener to convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        // Add components to frame
        add(new JLabel("Amount: "));
        add(amountField);
        add(new JLabel("From: "));
        add(fromCurrency);
        add(new JLabel("To: "));
        add(toCurrency);
        add(new JLabel(""));
        add(convertButton);
        add(resultLabel);

        setVisible(true);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            double convertedAmount = amount * exchangeRates.get(to) / exchangeRates.get(from);
            resultLabel.setText(String.format("Converted Amount: %.2f %s", convertedAmount, to));
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid amount.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverter();
            }
        });
    }
}
