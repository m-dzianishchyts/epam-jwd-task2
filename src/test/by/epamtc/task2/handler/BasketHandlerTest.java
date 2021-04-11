package by.epamtc.task2.handler;

import by.epamtc.task2.entity.Ball;
import by.epamtc.task2.entity.Basket;
import by.epamtc.task2.entity.Color;
import by.epamtc.task2.exception.IncompatibleStateException;
import by.epamtc.task2.exception.InvalidArgumentException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BasketHandlerTest {

    private static BasketHandler basketHandler;
    private static double testTotalWeight = 0;
    private static Map<Color, Integer> colorCounter;

    @BeforeAll
    static void init() throws InvalidArgumentException, IncompatibleStateException {
        initColorsCounter();
        Basket testBasket = new Basket();
        basketHandler = new BasketHandler(testBasket);
        Set<Ball> initBallSet = Set.of(new Ball(Color.RED, 10), new Ball(Color.BLUE, 11),
                                       new Ball(Color.GREEN, 12), new Ball(Color.GREEN, 13));
        for (var ball : initBallSet) {
            testBasket.put(ball);
            testTotalWeight += ball.getWeight();
            colorCounter.computeIfPresent(ball.getColor(), (color, amount) -> amount + 1);
        }
    }

    static void initColorsCounter() {
        colorCounter = new HashMap<>();
        for (var color : Color.values()) {
            colorCounter.put(color, 0);
        }
    }

    @Test
    void calculateTotalWeight() throws InvalidArgumentException {
        assertEquals(testTotalWeight, basketHandler.calculateTotalWeight());
    }

    @Test
    void createBasketHandlerInvalid() {
        assertThrows(InvalidArgumentException.class, () -> new BasketHandler(null));
    }

    @Test
    void countBallsByColorValid() throws InvalidArgumentException {
        for (var testColor : Color.values()) {
            assertEquals(colorCounter.get(testColor), basketHandler.countBallsByColor(testColor));
        }
    }

    @Test
    void countBallsByColorInvalid() {
        assertThrows(InvalidArgumentException.class, () -> basketHandler.countBallsByColor(null));
    }
}