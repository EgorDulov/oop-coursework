package gui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

final class MainFrame extends JFrame {

    MainFrame() {
        super("ООП — курсовая работа: задания 1–4");

        List<TaskPanel> panels = List.of(
                new HeroPanel(), new InvocationPanel(), new TranslatorPanel(), new StreamPanel());

        CardLayout cardLayout = new CardLayout();
        JPanel content = new JPanel(cardLayout);
        panels.forEach(panel -> content.add(panel, panel.getTitle()));

        JList<TaskPanel> taskList = new JList<>(panels.toArray(new TaskPanel[0]));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setFont(taskList.getFont().deriveFont(Font.BOLD, 14f));
        taskList.addListSelectionListener(event -> {
            TaskPanel selected = taskList.getSelectedValue();
            if (!event.getValueIsAdjusting() && selected != null) {
                cardLayout.show(content, selected.getTitle());
            }
        });
        taskList.setSelectedIndex(0);

        JScrollPane listScroll = new JScrollPane(taskList);
        listScroll.setPreferredSize(new Dimension(240, 0));
        listScroll.setBorder(BorderFactory.createTitledBorder("Задания"));

        add(listScroll, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 680);
        setMinimumSize(new Dimension(800, 520));
        setLocationRelativeTo(null);
    }
}