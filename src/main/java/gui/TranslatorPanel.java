package gui;

import lab3.exception.FileReadException;
import lab3.exception.InvalidFileFormatException;
import service.TextFileReader;
import service.TranslationService;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Optional;

final class TranslatorPanel extends TaskPanel {

    private static final String DESCRIPTION =
            "Выберите файл словаря (строки вида «слово или выражение | перевод»), введите текст вручную "
                    + "или загрузите его из файла и нажмите «Перевести».";

    private final TranslationService translationService = new TranslationService();
    private final JTextField dictionaryField = new JTextField("dictionary.txt");
    private final JTextArea inputArea = new JTextArea(8, 40);
    private final OutputArea output = new OutputArea();
    private File lastDirectory = new File(System.getProperty("user.dir"));

    TranslatorPanel() {
        super("3. Переводчик");

        JButton chooseDictionaryButton = new JButton("Выбрать…");
        JButton loadTextButton = new JButton("Загрузить текст из файла…");
        JButton translateButton = new JButton("Перевести");

        chooseDictionaryButton.addActionListener(event ->
                chooseFile().ifPresent(path -> dictionaryField.setText(path.toString())));
        loadTextButton.addActionListener(event -> loadTextFromFile());
        translateButton.addActionListener(event -> translate());

        JPanel dictionaryRow = new JPanel(new BorderLayout(6, 0));
        dictionaryRow.add(new JLabel("Файл словаря:"), BorderLayout.WEST);
        dictionaryRow.add(dictionaryField, BorderLayout.CENTER);
        dictionaryRow.add(chooseDictionaryButton, BorderLayout.EAST);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        buttons.add(loadTextButton);
        buttons.add(translateButton);

        JPanel controls = new JPanel(new BorderLayout(0, 6));
        controls.add(dictionaryRow, BorderLayout.NORTH);
        controls.add(buttons, BorderLayout.CENTER);

        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setText("dog look to the window, dog look forward");
        JScrollPane inputScroll = new JScrollPane(inputArea);
        inputScroll.setBorder(BorderFactory.createTitledBorder("Текст для перевода (можно ввести вручную)"));
        output.setBorder(BorderFactory.createTitledBorder("Результат"));

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputScroll, output);
        split.setResizeWeight(0.5);

        add(header(DESCRIPTION, controls), BorderLayout.NORTH);
        add(split, BorderLayout.CENTER);
    }

    private Optional<Path> chooseFile() {
        JFileChooser chooser = new JFileChooser(lastDirectory);
        if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return Optional.empty();
        }
        File file = chooser.getSelectedFile();
        lastDirectory = file.getParentFile();
        return Optional.of(file.toPath());
    }

    private void loadTextFromFile() {
        Optional<Path> chosen = chooseFile();
        if (chosen.isEmpty()) {
            return;
        }
        try {
            inputArea.setText(TextFileReader.read(chosen.get()));
            output.setText("Текст загружен из файла: " + chosen.get());
        } catch (FileReadException e) {
            output.setText("Ошибка: " + e.getMessage());
        }
    }

    private void translate() {
        String dictionaryPath = dictionaryField.getText().strip();
        if (dictionaryPath.isEmpty()) {
            output.setText("Ошибка: укажите файл словаря.");
            return;
        }
        try {
            output.setText(translationService.translate(Path.of(dictionaryPath), inputArea.getText()));
        } catch (FileReadException | InvalidFileFormatException | InvalidPathException e) {
            output.setText("Ошибка: " + e.getMessage());
        }
    }
}