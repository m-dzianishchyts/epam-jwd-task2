package by.jwd.task2.handler;

import by.jwd.task2.entity.Ball;
import by.jwd.task2.entity.Basket;
import by.jwd.task2.entity.Color;
import by.jwd.task2.exception.IncompatibleStateException;
import by.jwd.task2.exception.InvalidArgumentException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BasketHandlerTest {

    private static Basket testBasket;
    private static double testTotalWeight = 0;
    private static Map<Color, Integer> colorCounter;

    @BeforeAll
    static void initBasket() throws InvalidArgumentException, IncompatibleStateException {
        initColorsCounter();
        testBasket = new Basket();
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
    void calculateTotalWeightValid() throws InvalidArgumentException {
        assertEquals(testTotalWeight, BasketHandler.calculateTotalWeight(testBasket));
    }

    @Test
    void calculateTotalWeightInvalid() {
        assertThrows(InvalidArgumentException.class, () -> BasketHandler.calculateTotalWeight(null));
    }

    @Test
    void countBallsByColorValid() throws InvalidArgumentException {
        for (var testColor : Color.values()) {
            assertEquals(colorCounter.get(testColor), BasketHandler.countBallsByColor(testBasket, testColor));
        }
    }

    @Test
    void countBallsByColorInvalid() {
        assertThrows(InvalidArgumentException.class, () -> BasketHandler.countBallsByColor(null, Color.BLUE));
        assertThrows(InvalidArgumentException.class, () -> BasketHandler.countBallsByColor(testBasket, null));
    }
}