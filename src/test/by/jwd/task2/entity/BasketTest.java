package by.jwd.task2.entity;

import by.jwd.task2.exception.IncompatibleStateException;
import by.jwd.task2.exception.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    private static Basket testBasket;
    private static final int INITIAL_BASKET_CAPACITY = 15;
    private static Set<Ball> initialBallSet;
    private static int initialBasketSize;

    @BeforeEach
    void initBasket() throws InvalidArgumentException, IncompatibleStateException {
        testBasket = new Basket(INITIAL_BASKET_CAPACITY);
        initialBasketSize = 0;
        initialBallSet = Set.of(new Ball(Color.RED, 10), new Ball(Color.BLUE, 11),
                                       new Ball(Color.GREEN, 12), new Ball(Color.GREEN, 13));
        for (var ball : initialBallSet) {
            testBasket.put(ball);
            initialBasketSize++;
        }
    }

    @Test
    void initBasketInvalid() {
        assertThrows(InvalidArgumentException.class, () -> testBasket = new Basket(-10));
    }

    @Test
    void getSize() {
        assertEquals(initialBasketSize, testBasket.getSize());
    }

    @Test
    void getCapacity() {
        assertEquals(INITIAL_BASKET_CAPACITY, testBasket.getCapacity());
    }

    @Test
    void setCapacityValid() throws IncompatibleStateException, InvalidArgumentException {
        for (var testCapacity : new int[] {20, 4}) {
            testBasket.setCapacity(testCapacity);
            assertEquals(testCapacity, testBasket.getCapacity());
        }
    }

    @Test
    void setCapacityInvalid() {
        for (var testCapacity : new int[] {-10, 0}) {
            assertThrows(InvalidArgumentException.class, () -> testBasket.setCapacity(testCapacity));
        }
        assertThrows(IncompatibleStateException.class, () -> testBasket.setCapacity(initialBasketSize - 1));
    }

    @Test
    void getBalls() {
        assertEquals(initialBallSet, testBasket.getBalls());
    }

    @Test
    void putAllValid() throws InvalidArgumentException, IncompatibleStateException {
        Set<Ball> testBallSet = Set.of(new Ball(Color.AZURE, 1), new Ball(Color.AQUAMARINE, 16));
        Set<Ball> initialBallSetCopy = new HashSet<>(initialBallSet);
        testBasket.putAll(testBallSet);
        initialBallSetCopy.addAll(testBallSet);
        assertEquals(initialBallSetCopy, testBasket.getBalls());
    }

    @Test
    void putAllInvalid() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.putAll(null));
    }

    @Test
    void putValid() throws InvalidArgumentException, IncompatibleStateException {
        Ball testBall = new Ball(Color.AZURE, 1);
        Set<Ball> initialBallSetCopy = new HashSet<>(initialBallSet);
        testBasket.put(testBall);
        initialBallSetCopy.add(testBall);
        assertEquals(initialBallSetCopy, testBasket.getBalls());
    }

    @Test
    void putInvalid() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.put(null));
    }

    @Test
    void isEmpty() {
        assertFalse(testBasket.isEmpty());
        testBasket = new Basket();
        assertTrue(testBasket.isEmpty());
    }

    @Test
    void clear() {
        testBasket.clear();
        assertTrue(testBasket.isEmpty());
    }

    @Test
    void removeAllValid() throws InvalidArgumentException, IncompatibleStateException {
        testBasket.removeAll(new HashSet<>());
        assertEquals(initialBallSet, testBasket.getBalls());

        List<Ball> initialBallListCopy = List.copyOf(initialBallSet);
        Set<Ball> testBallSet = Set.of(initialBallListCopy.get(0), initialBallListCopy.get(1));
        Set<Ball> initialBallSetCopy = new HashSet<>(initialBallSet);
        testBasket.removeAll(testBallSet);
        initialBallSetCopy.removeAll(testBallSet);
        assertEquals(initialBallSetCopy, testBasket.getBalls());
    }

    @Test
    void removeAllInvalid() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.removeAll(null));
    }

    @Test
    void removeValid() throws InvalidArgumentException, IncompatibleStateException {
        Ball testBall = List.copyOf(initialBallSet).get(1);
        Set<Ball> initialBallSetCopy = new HashSet<>(initialBallSet);
        testBasket.remove(testBall);
        initialBallSetCopy.remove(testBall);
        assertEquals(initialBallSetCopy, testBasket.getBalls());
    }

    @Test
    void removeInvalid() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.remove(null));
    }

    @Test
    void containsAllValid() throws InvalidArgumentException {
        assertTrue(testBasket.containsAll(new HashSet<>()));

        assertTrue(testBasket.containsAll(initialBallSet));
        Set<Ball> initialBallSetCopy = new HashSet<>(initialBallSet);
        initialBallSetCopy.add(new Ball(Color.AQUAMARINE, 20));
        assertFalse(testBasket.containsAll(initialBallSetCopy));
    }

    @Test
    void containsAllInvalid() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.containsAll(null));
    }

    @Test
    void containsValid() throws InvalidArgumentException {
        Ball testBall = List.copyOf(initialBallSet).get(1);
        assertTrue(testBasket.contains(testBall));
    }

    @Test
    void containsInvalid() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.contains(null));
    }
}