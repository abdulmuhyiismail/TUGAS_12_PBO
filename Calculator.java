import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private JPanel panel;
    private String[] buttonLabels = {
        "1", "2", "3", "4", "5", "6",
        "7", "8", "9", "0", "+", "-",
        "*", "/", "=", "%", "Mod", "Exit"
    };
    private JButton[] buttons = new JButton[buttonLabels.length];

    public Calculator() {
        // Set up the frame
        setTitle("Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set up the display field
        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Set up the button panel
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 6, 5, 5)); // 3 rows, 6 columns, 5px padding

        // Create and add buttons to the panel
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Exit")) {
            System.exit(0);
        } else if (command.equals("=")) {
            // Calculate the result
            try {
                String result = eval(display.getText());
                display.setText(result);
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else {
            // Append the button text to the display
            display.setText(display.getText() + command);
        }
    }

    private String eval(String expression) throws Exception {
        // A simple evaluation function (could be improved)
        // This example uses the built-in JavaScript engine
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return engine.eval(expression).toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calc = new Calculator();
            calc.setVisible(true);
        });
    }
}
