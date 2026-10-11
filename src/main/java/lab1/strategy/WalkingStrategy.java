package lab1.strategy;

import java.util.Locale;

public final class WalkingStrategy implements MovementStrategy {

    private static final double SPEED_KMH = 5.0;

    @Override
    public String getName() {
        return "Пешком";
    }

    @Override
    public String move(Point from, Point to) {
        double distance = from.distanceTo(to);
        return String.format(Locale.ROOT,
                "Герой идёт пешком: %.1f км, в пути %.1f ч", distance, distance / SPEED_KMH);
    }
}