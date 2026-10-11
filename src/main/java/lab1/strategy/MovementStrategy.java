package lab1.strategy;

/** Способ перемещения героя (паттерн «Стратегия»). */
public interface MovementStrategy {

    /** Название способа перемещения. */
    String getName();

    /** Выполняет перемещение между точками и возвращает описание результата. */
    String move(Point from, Point to);
}