package gui;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;

final class OutputArea extends JScrollPane {

    private final JTextArea textArea = new JTextArea();

    OutputArea() {
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        setViewportView(textArea);
    }

    void println(String line) {
        textArea.append(line + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    void setText(String text) {
        textArea.setText(text);
        textArea.setCaretPosition(0);
    }

    void clear() {
        textArea.setText("");
    }
}