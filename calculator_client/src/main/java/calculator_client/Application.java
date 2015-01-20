package calculator_client;

import calculator.EvaluationException;
import calculator.impl.MathFunctionFactory;
import calculator.impl.MathOperationFactory;
import calculator.impl.StateMachineCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import static java.awt.GridBagConstraints.*;

/**
 * @author Bogdan Kovalev.
 */
public class Application extends JFrame {

    private final JMenuBar menuBar;
    private final JDialog helpDialog;
    private final JLabel label;
    private final JTextField expressionField;
    private final JTextArea answerArea;
    private final JScrollPane scrollPane;
    private final JButton clearButton;

    private final GridBagConstraints labelConstraints = new GBC().x_y(0, 0).weight(0, 0).anchor(NORTHWEST).insets(new Insets(5, 5, 5, 5));
    private final GridBagConstraints expressionFieldConstraints = new GBC().x_y(0, 1).anchor(NORTH).fill(HORIZONTAL).insets(new Insets(5, 5, 5, 5));
    private final GridBagConstraints scrollPaneConstraints = new GBC().x_y(0, 2).weight(1.0, 1.0).anchor(NORTH).fill(BOTH).insets(new Insets(5, 5, 5, 5));
    private final GridBagConstraints clearButtonConstraints = new GBC().x_y(0, 3).weight(0, 0).anchor(SOUTH).fill(HORIZONTAL).insets(new Insets(5, 5, 5, 5));

    public Application() {
        super("Calculator client");
        setSize(new Dimension(400, 300));
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menuBar = createMenuBar();
        helpDialog = createHelpDialog();
        expressionField = createExpressionField();

        label = new JLabel("Write a math expression and press 'Enter' to evaluate:");
        answerArea = new JTextArea();
        answerArea.setEditable(false);
        scrollPane = new JScrollPane(answerArea);

        clearButton = createClearButton();

        gui();
    }

    private JMenuBar createMenuBar() {
        final JMenuBar jMenuBar = new JMenuBar();
        final JMenu file = new JMenu("File");
        final JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        file.add(exit);

        final JMenu help = new JMenu("Help");
        final JMenuItem available = new JMenuItem("Available operations...");
        available.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpDialog.setVisible(true);
            }
        });
        help.add(available);

        jMenuBar.add(file);
        jMenuBar.add(help);
        return jMenuBar;
    }

    private JDialog createHelpDialog() {
        final JDialog jDialog = new JDialog(Application.this);
        jDialog.setLayout(new GridBagLayout());
        jDialog.setLocationByPlatform(true);
        jDialog.setSize(200, 100);
        final JTextArea textArea = new JTextArea(createHelpText());
        textArea.setEditable(false);
        jDialog.add(textArea, new GBC().weight(1.0, 1.0).fill(BOTH).insets(new Insets(5, 5, 5, 5)));
        return jDialog;
    }

    private JTextField createExpressionField() {
        final JTextField jTextField = new JTextField();
        jTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jTextField.addActionListener(new EnterListener());
        return jTextField;
    }

    private JButton createClearButton() {
        final JButton button = new JButton("Clear");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expressionField.setText("");
                answerArea.setText("");
            }
        });
        button.setFocusable(false);
        return button;
    }

    private void gui() {
        setLayout(new GridBagLayout());

        setJMenuBar(menuBar);

        add(label, labelConstraints);

        add(expressionField, expressionFieldConstraints);

        add(scrollPane, scrollPaneConstraints);

        add(clearButton, clearButtonConstraints);
    }

    private String createHelpText() {
        final Set<String> availableFunctionPresentations = new MathFunctionFactory().getAvailableFunctionPresentations();
        final Set<String> availableOperationPresentations = new MathOperationFactory().getAvailableOperationPresentations();
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Available operations: ");
        for (Iterator it = availableOperationPresentations.iterator(); it.hasNext(); ) {
            final Object next = it.next();
            stringBuilder.append(it.hasNext() ? next + ", " : next);
        }
        stringBuilder.append("\nAvailable functions: ");
        for (Iterator it = availableFunctionPresentations.iterator(); it.hasNext(); ) {
            final Object next = it.next();
            stringBuilder.append(it.hasNext() ? next + ", " : next);
        }
        return stringBuilder.toString();
    }

    private class EnterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            final StateMachineCalculator calculator = new StateMachineCalculator();
            final String expression = expressionField.getText();
            try {
                answerArea.setForeground(new Color(4, 128, 17));
                final double answer = calculator.evaluate(expression);
                answerArea.append("Expression: " + expression + "\n" + "Answer: " + answer + "\n\n");
            } catch (EvaluationException ee) {
                answerArea.setForeground(Color.RED);
                answerArea.append("Input: " + expression + "\n" + ee.getMessage() + "\n\n");
                expressionField.setCaretPosition(ee.getErrorIndex());
            }
        }
    }

    public static void main(String[] args) {
        new Application().setVisible(true);
    }
}
