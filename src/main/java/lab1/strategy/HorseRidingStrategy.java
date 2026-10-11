package lab1.strategy;

import java.util.Locale;

public final class HorseRidingStrategy implements MovementStrategy {

    private static final double SPEED_KMH = 25.0;

    @Override
    public String getName() {
        return "На лошади";
    }

    @Override
    public String move(Point from, Point to) {
        double distance = from.distanceTo(to);
        return String.format(Locale.ROOT,
                "Герой скачет на лошади: %.1f км, в пути %.1f ч", distance, distance / SPEED_KMH);
    }
}