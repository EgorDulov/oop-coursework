package lab1.strategy;

import java.util.Objects;

/** Точка на плоскости (координаты в километрах). */
public record Point(double x, double y) {

    public double distanceTo(Point other) {
        Objects.requireNonNull(other, "Точка не может быть null");
        return Math.hypot(x - other.x, y - other.y);
    }
}