package ClassFiles;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class ConsoleInterface {
    public JFrame frame;
    public JTextPane console;
    public JTextField input;
    public JScrollPane scrollPane;
    public StyledDocument document;
    public boolean trace = false;

    ConsoleInterface(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        frame = new JFrame();
        frame.setTitle("Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        console = new JTextPane();
        console.setFont(new Font("Courier New", Font.PLAIN, 12));
        console.setForeground(Color.WHITE);
        console.setCaretColor(Color.WHITE);
        console.setOpaque(false);

        input = new JTextField();

        scrollPane = new JScrollPane(console);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        frame.getContentPane().setBackground(new Color(40, 40, 40));
        frame.add(input, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ConsoleInterface console = new ConsoleInterface();
    }
}
