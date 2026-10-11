package gui;

import service.InputKind;
import service.StreamTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

final class StreamPanel extends TaskPanel {

    private static final String DESCRIPTION =
            "Выберите метод, введите входные данные и нажмите «Выполнить». "
                    + "Кнопка «Подставить пример» заполняет поле ввода подходящими данными.";

    private final JComboBox<StreamTask> taskBox = new JComboBox<>(StreamTask.values());
    private final JLabel hintLabel = new JLabel();
    private final JTextArea inputArea = new JTextArea(6, 40);
    private final OutputArea output = new OutputArea();
    private InputKind currentKind;

    StreamPanel() {
        super("4. Stream API");

        JButton exampleButton = new JButton("Подставить пример");
        JButton runButton = new JButton("Выполнить");

        taskBox.addActionListener(event -> onTaskChanged());
        exampleButton.addActionListener(event -> inputArea.setText(selectedTask().getExample()));
        runButton.addActionListener(event -> output.setText(selectedTask().run(inputArea.getText())));

        JPanel selectRow = new JPanel(new BorderLayout(6, 0));
        selectRow.add(new JLabel("Метод:"), BorderLayout.WEST);
        selectRow.add(taskBox, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        buttons.add(exampleButton);
        buttons.add(runButton);

        JPanel lower = new JPanel(new BorderLayout(0, 4));
        lower.add(hintLabel, BorderLayout.NORTH);
        lower.add(buttons, BorderLayout.CENTER);

        JPanel controls = new JPanel(new BorderLayout(0, 6));
        controls.add(selectRow, BorderLayout.NORTH);
        controls.add(lower, BorderLayout.CENTER);

        inputArea.setLineWrap(true);
        JScrollPane inputScroll = new JScrollPane(inputArea);
        inputScroll.setBorder(BorderFactory.createTitledBorder("Входные данные"));
        output.setBorder(BorderFactory.createTitledBorder("Результат"));

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputScroll, output);
        split.setResizeWeight(0.4);

        add(header(DESCRIPTION, controls), BorderLayout.NORTH);
        add(split, BorderLayout.CENTER);

        currentKind = selectedTask().getInputKind();
        hintLabel.setText(currentKind.getHint());
        inputArea.setText(selectedTask().getExample());
    }

    private StreamTask selectedTask() {
        return taskBox.getItemAt(taskBox.getSelectedIndex());
    }

    private void onTaskChanged() {
        StreamTask task = selectedTask();
        hintLabel.setText(task.getInputKind().getHint());
        if (task.getInputKind() != currentKind || inputArea.getText().isBlank()) {
            inputArea.setText(task.getExample());
        }
        currentKind = task.getInputKind();
    }
}