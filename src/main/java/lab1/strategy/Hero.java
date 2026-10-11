package lab1.strategy;

import java.util.Objects;

/** Герой, способ перемещения которого можно менять во время работы программы. */
public final class Hero {

    private Point position;
    private MovementStrategy movementStrategy;

    public Hero(Point startPosition, MovementStrategy movementStrategy) {
        this.position = Objects.requireNonNull(startPosition, "Начальная позиция не может быть null");
        setMovementStrategy(movementStrategy);
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = Objects.requireNonNull(movementStrategy, "Стратегия не может быть null");
    }

    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    public Point getPosition() {
        return position;
    }

    /** Перемещает героя в указанную точку текущим способом и возвращает отчёт. */
    public String move(Point destination) {
        Objects.requireNonNull(destination, "Точка назначения не может быть null");
        String report = movementStrategy.move(position, destination);
        position = destination;
        return report;
    }
}