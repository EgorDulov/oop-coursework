package gui;

import lab1.strategy.FlyingStrategy;
import lab1.strategy.Hero;
import lab1.strategy.HorseRidingStrategy;
import lab1.strategy.MovementStrategy;
import lab1.strategy.Point;
import lab1.strategy.WalkingStrategy;
import service.InputParser;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Locale;

final class HeroPanel extends TaskPanel {

    private static final String DESCRIPTION =
            "Выберите способ перемещения героя, введите координаты цели (в километрах) "
                    + "и нажмите «Переместить». Способ можно менять в любой момент.";

    private static final Point START = new Point(0, 0);

    private record Choice(MovementStrategy strategy) {
        @Override
        public String toString() {
            return strategy.getName();
        }
    }

    private final JComboBox<Choice> strategyBox = new JComboBox<>(new Choice[]{
            new Choice(new WalkingStrategy()),
            new Choice(new HorseRidingStrategy()),
            new Choice(new FlyingStrategy())});
    private final JTextField xField = new JTextField("10", 6);
    private final JTextField yField = new JTextField("0", 6);
    private final OutputArea output = new OutputArea();
    private Hero hero = new Hero(START, selectedStrategy());

    HeroPanel() {
        super("1. Стратегия: герой");

        JButton moveButton = new JButton("Переместить");
        JButton resetButton = new JButton("Сбросить героя");
        JButton clearButton = new JButton("Очистить вывод");

        strategyBox.addActionListener(event -> changeStrategy());
        moveButton.addActionListener(event -> moveHero());
        resetButton.addActionListener(event -> resetHero());
        clearButton.addActionListener(event -> output.clear());

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        controls.add(new JLabel("Способ:"));
        controls.add(strategyBox);
        controls.add(new JLabel("x:"));
        controls.add(xField);
        controls.add(new JLabel("y:"));
        controls.add(yField);
        controls.add(moveButton);
        controls.add(resetButton);
        controls.add(clearButton);

        add(header(DESCRIPTION, controls), BorderLayout.NORTH);
        add(output, BorderLayout.CENTER);
        output.println(positionLine());
    }

    private MovementStrategy selectedStrategy() {
        return strategyBox.getItemAt(strategyBox.getSelectedIndex()).strategy();
    }

    private void changeStrategy() {
        MovementStrategy strategy = selectedStrategy();
        hero.setMovementStrategy(strategy);
        output.println("Способ перемещения изменён: " + strategy.getName());
    }

    private void moveHero() {
        try {
            Point destination = new Point(
                    InputParser.parseCoordinate(xField.getText()),
                    InputParser.parseCoordinate(yField.getText()));
            output.println(hero.move(destination));
            output.println(positionLine());
        } catch (IllegalArgumentException e) {
            output.println("Ошибка: " + e.getMessage());
        }
    }

    private void resetHero() {
        hero = new Hero(START, selectedStrategy());
        output.println("Герой возвращён в начальную точку.");
        output.println(positionLine());
    }

    private String positionLine() {
        Point position = hero.getPosition();
        return String.format(Locale.ROOT, "Позиция героя: (%.1f; %.1f), способ: %s",
                position.x(), position.y(), hero.getMovementStrategy().getName());
    }
}