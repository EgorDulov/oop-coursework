package gui;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

abstract class TaskPanel extends JPanel {

    private final String title;

    protected TaskPanel(String title) {
        super(new BorderLayout(0, 8));
        this.title = title;
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

    protected static JPanel header(String description, JComponent controls) {
        JLabel label = new JLabel("<html><body style='width: 600px'>" + description + "</body></html>");
        JPanel panel = new JPanel(new BorderLayout(0, 6));
        panel.add(label, BorderLayout.NORTH);
        panel.add(controls, BorderLayout.CENTER);
        return panel;
    }
}