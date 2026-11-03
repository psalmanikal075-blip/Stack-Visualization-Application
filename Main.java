

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class StackArray {

    private int[] stack = new int[10];
    private int top = -1;

    public boolean push(int value) {
        if (isFull()) {
            return false;
        }

        stack[++top] = value;
        return true;
    }

    public Integer pop() {
        if (isEmpty()) {
            return null;
        }

        return stack[top--];
    }

    public Integer peek() {
        if (isEmpty()) {
            return null;
        }

        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == 9;
    }

    public int size() {
        return top + 1;
    }

    public void clear() {
        top = -1;
    }

    public ArrayList<Integer> getElements() {
        ArrayList<Integer> elements = new ArrayList<>();

        for (int i = top; i >= 0; i--) {
            elements.add(stack[i]);
        }

        return elements;
    }
}

// ROUNDED PANEL
class RoundedPanel extends JPanel {

    private Color panelColor;

    public RoundedPanel(Color color) {
        panelColor = color;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(panelColor);
        g2.fillRoundRect(
            0, 0, getWidth(), getHeight(), 22, 22
        );

        g2.dispose();

        super.paintComponent(g);
    }
}

// MODERN DARK BLUE BUTTON
class RoundedButton extends JButton {

    private Color buttonColor =
        new Color(15, 40, 85);

    private Color hoverColor =
        new Color(25, 60, 120);

    private boolean hovering = false;

    public RoundedButton(String text) {
        super(text);

        setFont(new Font("Arial", Font.BOLD, 12));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setBorder(
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        );

        addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                hovering = true;
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                hovering = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(hovering ? hoverColor : buttonColor);

        g2.fillRoundRect(
            0, 0, getWidth(), getHeight(), 15, 15
        );

        g2.dispose();

        super.paintComponent(g);
    }
}

// MAIN GUI
public class Main extends JFrame {

    private StackArray stack = new StackArray();

    private JTextField inputField;
    private JPanel stackPanel;

    private JLabel statusLabel;
    private JLabel sizeLabel;

    private final Color BACKGROUND =
        new Color(15, 23, 42);

    private final Color PANEL_COLOR =
        new Color(30, 41, 59);

    public Main() {

        setTitle("Stack Visualization Application");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(BACKGROUND);
        setLayout(new BorderLayout(0, 0));

        // HEADER
        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(BACKGROUND);
        header.setBorder(
            BorderFactory.createEmptyBorder(25, 30, 20, 30)
        );

        JLabel title = new JLabel("STACK VISUALIZER");

        title.setFont(
            new Font("Arial", Font.BOLD, 27)
        );

        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel(
            "Data Structure  /  Last In, First Out"
        );

        subtitle.setFont(
            new Font("Arial", Font.PLAIN, 13)
        );

        subtitle.setForeground(
            new Color(148, 163, 184)
        );

        JPanel titlePanel = new JPanel();

        titlePanel.setLayout(
            new BoxLayout(titlePanel, BoxLayout.Y_AXIS)
        );

        titlePanel.setBackground(BACKGROUND);

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitle);

        sizeLabel = new JLabel("0 / 10 ELEMENTS");

        sizeLabel.setFont(
            new Font("Arial", Font.BOLD, 12)
        );

        sizeLabel.setForeground(
            new Color(96, 165, 250)
        );

        header.add(titlePanel, BorderLayout.WEST);
        header.add(sizeLabel, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // MAIN CONTENT
        JPanel mainContent = new JPanel(
            new GridLayout(1, 2, 18, 0)
        );

        mainContent.setBackground(BACKGROUND);

        mainContent.setBorder(
            BorderFactory.createEmptyBorder(10, 30, 20, 30)
        );

        // STACK CONTENTS PANEL
        RoundedPanel leftPanel =
            new RoundedPanel(PANEL_COLOR);

        leftPanel.setLayout(new BorderLayout(10, 10));

        leftPanel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JLabel stackTitle = new JLabel("STACK CONTENTS");

        stackTitle.setFont(
            new Font("Arial", Font.BOLD, 15)
        );

        stackTitle.setForeground(Color.WHITE);

        JLabel direction = new JLabel("TOP  ↓  BOTTOM");

        direction.setFont(
            new Font("Arial", Font.PLAIN, 11)
        );

        direction.setForeground(
            new Color(148, 163, 184)
        );

        JPanel leftHeader = new JPanel(new BorderLayout());

        leftHeader.setOpaque(false);

        leftHeader.add(stackTitle, BorderLayout.WEST);
        leftHeader.add(direction, BorderLayout.EAST);

        leftPanel.add(leftHeader, BorderLayout.NORTH);

        stackPanel = new JPanel();

        stackPanel.setLayout(
            new BoxLayout(stackPanel, BoxLayout.Y_AXIS)
        );

        stackPanel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(stackPanel);

        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        scrollPane.getVerticalScrollBar().setUnitIncrement(15);

        leftPanel.add(scrollPane, BorderLayout.CENTER);

        // STACK OPERATIONS PANEL
        RoundedPanel rightPanel =
            new RoundedPanel(PANEL_COLOR);

        rightPanel.setLayout(new BorderLayout(15, 15));

        rightPanel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JLabel operationsTitle =
            new JLabel("STACK OPERATIONS", JLabel.CENTER);

        operationsTitle.setFont(
            new Font("Arial", Font.BOLD, 15)
        );

        operationsTitle.setForeground(Color.WHITE);

        rightPanel.add(operationsTitle, BorderLayout.NORTH);

        JPanel controls = new JPanel(
            new BorderLayout(0, 12)
        );

        controls.setOpaque(false);

        // INPUT FIELD
        JPanel inputPanel = new JPanel(
            new BorderLayout(0, 8)
        );

        inputPanel.setOpaque(false);

        JLabel inputTitle = new JLabel("ENTER VALUE");

        inputTitle.setFont(
            new Font("Arial", Font.BOLD, 11)
        );

        inputTitle.setForeground(
            new Color(148, 163, 184)
        );

        inputField = new JTextField();

        inputField.setFont(
            new Font("Arial", Font.PLAIN, 16)
        );

        inputField.setForeground(Color.WHITE);

        inputField.setBackground(
            new Color(15, 23, 42)
        );

        inputField.setCaretColor(Color.WHITE);

        inputField.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(51, 65, 85), 1
                ),
                BorderFactory.createEmptyBorder(
                    10, 12, 10, 12
                )
            )
        );

        inputPanel.add(inputTitle, BorderLayout.NORTH);
        inputPanel.add(inputField, BorderLayout.CENTER);

        controls.add(inputPanel, BorderLayout.NORTH);

        // ALL DARK BLUE BUTTONS
        JPanel buttonPanel = new JPanel(
            new GridLayout(4, 2, 10, 10)
        );

        buttonPanel.setOpaque(false);

        RoundedButton pushButton =
            new RoundedButton("PUSH");

        RoundedButton popButton =
            new RoundedButton("POP");

        RoundedButton peekButton =
            new RoundedButton("PEEK");

        RoundedButton clearButton =
            new RoundedButton("CLEAR");

        RoundedButton checkButton =
            new RoundedButton("CHECK STACK");

        RoundedButton randomButton =
            new RoundedButton("RANDOM");

        RoundedButton sizeButton =
            new RoundedButton("SIZE");

        buttonPanel.add(pushButton);
        buttonPanel.add(popButton);
        buttonPanel.add(peekButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(checkButton);
        buttonPanel.add(randomButton);
        buttonPanel.add(sizeButton);
        buttonPanel.add(new JLabel());

        controls.add(buttonPanel, BorderLayout.CENTER);

        rightPanel.add(controls, BorderLayout.CENTER);

        mainContent.add(leftPanel);
        mainContent.add(rightPanel);

        add(mainContent, BorderLayout.CENTER);

        // FOOTER
        JPanel footer = new JPanel(new BorderLayout());

        footer.setBackground(BACKGROUND);

        footer.setBorder(
            BorderFactory.createEmptyBorder(10, 30, 20, 30)
        );

        statusLabel = new JLabel("Ready to use.");

        statusLabel.setFont(
            new Font("Arial", Font.PLAIN, 12)
        );

        statusLabel.setForeground(
            new Color(148, 163, 184)
        );

        JLabel footerText = new JLabel("STACK SYSTEM");

        footerText.setFont(
            new Font("Arial", Font.BOLD, 10)
        );

        footerText.setForeground(
            new Color(96, 165, 250)
        );

        footer.add(statusLabel, BorderLayout.WEST);
        footer.add(footerText, BorderLayout.EAST);

        add(footer, BorderLayout.SOUTH);

        // BUTTON ACTIONS
        pushButton.addActionListener(e -> pushAction());
        popButton.addActionListener(e -> popAction());
        peekButton.addActionListener(e -> peekAction());
        clearButton.addActionListener(e -> clearAction());
        checkButton.addActionListener(e -> checkAction());
        randomButton.addActionListener(e -> randomAction());
        sizeButton.addActionListener(e -> sizeAction());

        inputField.addActionListener(e -> pushAction());

        updateDisplay();
    }

    // PUSH
    private void pushAction() {

        try {

            int value = Integer.parseInt(
                inputField.getText().trim()
            );

            if (stack.push(value)) {

                statusLabel.setText(
                    "Value pushed successfully!"
                );

            } else {

                statusLabel.setText(
                    "STACK OVERFLOW: Stack is full!"
                );
            }

            inputField.setText("");

            updateDisplay();

        } catch (NumberFormatException e) {

            statusLabel.setText(
                "Please enter a valid integer."
            );
        }
    }

    // POP
    private void popAction() {

        Integer removed = stack.pop();

        if (removed == null) {

            statusLabel.setText(
                "STACK UNDERFLOW: Stack is empty!"
            );

        } else {

            statusLabel.setText(
                "Removed element: " + removed
            );
        }

        updateDisplay();
    }

    // PEEK
    private void peekAction() {

        Integer value = stack.peek();

        if (value == null) {

            statusLabel.setText(
                "Stack is empty. Nothing to peek."
            );

        } else {

            statusLabel.setText(
                "TOP ELEMENT: " + value
            );
        }
    }

    // CLEAR
    private void clearAction() {

        stack.clear();

        statusLabel.setText(
            "All stack elements cleared."
        );

        updateDisplay();
    }

    // CHECK STACK
    private void checkAction() {

        if (stack.isEmpty()) {

            statusLabel.setText(
                "The stack is EMPTY."
            );

        } else {

            statusLabel.setText(
                "The stack is NOT EMPTY."
            );
        }
    }

    // RANDOM
    private void randomAction() {

        stack.clear();

        Random random = new Random();

        int count = random.nextInt(10) + 1;

        for (int i = 0; i < count; i++) {

            stack.push(random.nextInt(100) + 1);
        }

        statusLabel.setText(
            "Random stack generated successfully!"
        );

        updateDisplay();
    }

    // SIZE
    private void sizeAction() {

        statusLabel.setText(
            "Current stack size: " + stack.size()
        );
    }

    // UPDATE DISPLAY
    private void updateDisplay() {

        stackPanel.removeAll();

        ArrayList<Integer> elements =
            stack.getElements();

        if (elements.isEmpty()) {

            JLabel emptyLabel =
                new JLabel("No elements in stack");

            emptyLabel.setFont(
                new Font("Arial", Font.PLAIN, 15)
            );

            emptyLabel.setForeground(
                new Color(148, 163, 184)
            );

            emptyLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
            );

            stackPanel.add(Box.createVerticalGlue());
            stackPanel.add(emptyLabel);
            stackPanel.add(Box.createVerticalGlue());

        } else {

            for (int i = 0; i < elements.size(); i++) {

                String text;

                if (i == 0) {
                    text = "TOP  →  " + elements.get(i);
                } else {
                    text = String.valueOf(elements.get(i));
                }

                RoundedPanel block =
                    new RoundedPanel(
                        new Color(65, 91, 130)
                    );

                block.setLayout(new BorderLayout());

                block.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 48)
                );

                block.setPreferredSize(
                    new Dimension(300, 48)
                );

                JLabel valueLabel =
                    new JLabel(text, JLabel.CENTER);

                valueLabel.setFont(
                    new Font("Arial", Font.BOLD, 16)
                );

                valueLabel.setForeground(Color.WHITE);

                block.add(valueLabel, BorderLayout.CENTER);

                stackPanel.add(block);

                stackPanel.add(
                    Box.createVerticalStrut(8)
                );
            }
        }

        sizeLabel.setText(
            stack.size() + " / 10 ELEMENTS"
        );

        stackPanel.revalidate();
        stackPanel.repaint();
    }

    // MAIN METHOD
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Main program = new Main();

            program.setVisible(true);
        });
    }
}


