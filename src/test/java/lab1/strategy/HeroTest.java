package lab1.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeroTest {

    private static final Point ORIGIN = new Point(0, 0);

    @Test
    void constructorRejectsNullStartPosition() {
        assertThrows(NullPointerException.class, () -> new Hero(null, new WalkingStrategy()));
    }

    @Test
    void constructorRejectsNullStrategy() {
        assertThrows(NullPointerException.class, () -> new Hero(ORIGIN, null));
    }

    @Test
    void setStrategyRejectsNull() {
        Hero hero = new Hero(ORIGIN, new WalkingStrategy());
        assertThrows(NullPointerException.class, () -> hero.setMovementStrategy(null));
    }

    @Test
    void moveRejectsNullDestination() {
        Hero hero = new Hero(ORIGIN, new WalkingStrategy());
        assertThrows(NullPointerException.class, () -> hero.move(null));
    }

    @Test
    void moveUpdatesPosition() {
        Hero hero = new Hero(ORIGIN, new WalkingStrategy());
        hero.move(new Point(3, 4));
        assertEquals(new Point(3, 4), hero.getPosition());
    }

    @Test
    void strategyCanBeChangedAtRuntime() {
        Hero hero = new Hero(ORIGIN, new WalkingStrategy());
        String walk = hero.move(new Point(10, 0));

        hero.setMovementStrategy(new FlyingStrategy());
        String fly = hero.move(new Point(20, 0));

        assertTrue(walk.contains("пешком"));
        assertTrue(fly.contains("летит"));
    }

    @Test
    void strategiesHaveDifferentSpeeds() {
        Point ten = new Point(10, 0);
        assertTrue(new WalkingStrategy().move(ORIGIN, ten).contains("2.0 ч"));
        assertTrue(new HorseRidingStrategy().move(ORIGIN, ten).contains("0.4 ч"));
        assertTrue(new FlyingStrategy().move(ORIGIN, ten).contains("0.2 ч"));
    }
}