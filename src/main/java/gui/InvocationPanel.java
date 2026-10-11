package gui;

import service.InvocationReportService;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

final class InvocationPanel extends TaskPanel {

    private static final String DESCRIPTION =
            "Методы класса SampleService, помеченные аннотацией @Repeat(n), вызываются через рефлексию: "
                    + "только protected и private, каждый n раз. Аргументы создаются автоматически "
                    + "по типам параметров, без null. Входных данных у этого задания нет.";

    private final InvocationReportService reportService = new InvocationReportService();
    private final OutputArea output = new OutputArea();

    InvocationPanel() {
        super("2. Аннотации и рефлексия");

        JButton runButton = new JButton("Выполнить");
        runButton.addActionListener(event -> output.setText(reportService.run()));

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        controls.add(runButton);

        add(header(DESCRIPTION, controls), BorderLayout.NORTH);
        add(output, BorderLayout.CENTER);
    }
}