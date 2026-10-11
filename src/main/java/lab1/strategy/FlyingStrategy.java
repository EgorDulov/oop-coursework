package lab1.strategy;

import java.util.Locale;

public final class FlyingStrategy implements MovementStrategy {

    private static final double SPEED_KMH = 60.0;

    @Override
    public String getName() {
        return "Полёт";
    }

    @Override
    public String move(Point from, Point to) {
        double distance = from.distanceTo(to);
        return String.format(Locale.ROOT,
                "Герой летит: %.1f км, в пути %.1f ч", distance, distance / SPEED_KMH);
    }
}