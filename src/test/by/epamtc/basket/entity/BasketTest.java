package by.epamtc.basket.entity;

import by.epamtc.basket.exception.IncompatibleStateException;
import by.epamtc.basket.exception.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    private static Basket testBasket;
    private static final int INITIAL_BASKET_CAPACITY = 15;
    private static List<Ball> initialBallList;
    private static int initialBasketSize;

    @BeforeEach
    void initBasket() throws InvalidArgumentException, IncompatibleStateException {
        testBasket = new Basket(INITIAL_BASKET_CAPACITY);
        initialBasketSize = 0;
        initialBallList = List.of(new Ball(Color.RED, 10), new Ball(Color.BLUE, 11), new Ball(Color.GREEN, 12),
                                  new Ball(Color.GREEN, 13));
        for (var ball : initialBallList) {
            testBasket.put(ball);
            initialBasketSize++;
        }
    }

    @Test
    void initBasketInvalid() {
        for (var testCapacity : new int[]{-10, 0}) {
            assertThrows(InvalidArgumentException.class, () -> testBasket = new Basket(testCapacity));
        }
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
    void setCapacityValidMinimal() throws IncompatibleStateException, InvalidArgumentException {
        testBasket.setCapacity(initialBasketSize);
        assertEquals(initialBasketSize, testBasket.getCapacity());
    }

    @Test
    void setCapacityValidAny() throws IncompatibleStateException, InvalidArgumentException {
        testBasket.setCapacity(20);
        assertEquals(20, testBasket.getCapacity());
    }

    @Test
    void setCapacityInvalidNegative() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.setCapacity(-10));
    }

    @Test
    void setCapacityInvalidZero() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.setCapacity(0));
    }

    @Test
    void setCapacityInvalidBelowSize() {
        assertThrows(IncompatibleStateException.class, () -> testBasket.setCapacity(initialBasketSize - 1));
    }

    @Test
    void getBalls() {
        assertEquals(initialBallList, testBasket.getBalls());
    }

    @Test
    void setBallsValid() throws IncompatibleStateException, InvalidArgumentException {
        List<Ball> testBallSet = new ArrayList<>() {{
            add(new Ball(Color.CORAL, 41));
            add(new Ball(Color.YELLOW, 28));
            add(new Ball(Color.BLUE, 1));
        }};
        testBasket.setBalls(testBallSet);
        assertEquals(testBallSet, testBasket.getBalls());
    }

    @Test
    void setBallsInvalidNull() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.setBalls(null));
    }

    @Test
    void setBallsInvalidContainingNull() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.setBalls(new ArrayList<>() {{
            add(null);
        }}));
    }

    @Test
    void setBallsInvalidDuplicates() throws InvalidArgumentException {
        Ball duplicateBall = new Ball(Color.AQUAMARINE, 12);
        assertThrows(IncompatibleStateException.class,
                     () -> testBasket.setBalls(List.of(duplicateBall, duplicateBall)));
    }

    @Test
    void setBallsInvalidOverflow() throws IncompatibleStateException, InvalidArgumentException {
        testBasket.setCapacity(testBasket.getSize());
        assertThrows(IncompatibleStateException.class, () -> testBasket.setBalls(new ArrayList<>() {{
            add(new Ball(Color.AQUAMARINE, 14));
            addAll(initialBallList);
        }}));
    }

    @Test
    void putAllValid() throws InvalidArgumentException, IncompatibleStateException {
        List<Ball> testBallList = List.of(new Ball(Color.AZURE, 1), new Ball(Color.AQUAMARINE, 16));
        List<Ball> initialBallListCopy = new ArrayList<>(initialBallList);
        testBasket.putAll(testBallList);
        initialBallListCopy.addAll(testBallList);
        assertEquals(initialBallListCopy, testBasket.getBalls());
    }

    @Test
    void putAllInvalidNull() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.putAll(null));
    }

    @Test
    void putAllInvalidKnownDuplicates() {
        Ball duplicateBall = List.copyOf(initialBallList).get(1);
        assertThrows(IncompatibleStateException.class, () -> testBasket.putAll(List.of(duplicateBall, duplicateBall)));
    }

    @Test
    void putAllInvalidUnknownDuplicates() throws InvalidArgumentException {
        Ball duplicateBall = new Ball(Color.AQUAMARINE, 12);
        assertThrows(IncompatibleStateException.class, () -> testBasket.putAll(List.of(duplicateBall, duplicateBall)));
    }

    @Test
    void putValid() throws InvalidArgumentException, IncompatibleStateException {
        Ball testBall = new Ball(Color.RED, 10);
        List<Ball> initialBallListCopy = new ArrayList<>(initialBallList);
        testBasket.put(testBall);
        initialBallListCopy.add(testBall);
        assertEquals(initialBallListCopy, testBasket.getBalls());
    }

    @Test
    void putInvalidNull() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.put(null));
    }

    @Test
    void putInvalidDuplicate() {
        Ball duplicateBall = List.copyOf(initialBallList).get(1);
        assertThrows(IncompatibleStateException.class, () -> testBasket.put(duplicateBall));
    }

    @Test
    void isEmptyFalse() {
        assertFalse(testBasket.isEmpty());
    }

    @Test
    void isEmptyTrue() {
        testBasket = new Basket();
        assertTrue(testBasket.isEmpty());
    }

    @Test
    void clear() {
        testBasket.clear();
        assertTrue(testBasket.isEmpty());
    }

    @Test
    void removeAllValidEmpty() throws InvalidArgumentException, IncompatibleStateException {
        testBasket.removeAll(new ArrayList<>());
        assertEquals(initialBallList, testBasket.getBalls());
    }

    @Test
    void removeAllValid() throws InvalidArgumentException, IncompatibleStateException {
        List<Ball> initialBallListCopy = new ArrayList<>(initialBallList);
        List<Ball> testBallList = List.of(initialBallListCopy.get(0), initialBallListCopy.get(1));
        testBasket.removeAll(testBallList);
        initialBallListCopy.removeAll(testBallList);
        assertEquals(initialBallListCopy, testBasket.getBalls());
    }

    @Test
    void removeAllInvalidNull() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.removeAll(null));
    }

    @Test
    void removeAllInvalidDuplicates() {
        Ball duplicateBall = List.copyOf(initialBallList).get(1);
        assertThrows(IncompatibleStateException.class,
                     () -> testBasket.removeAll(List.of(duplicateBall, duplicateBall)));
    }

    @Test
    void removeValid() throws InvalidArgumentException, IncompatibleStateException {
        Ball testBall = List.copyOf(initialBallList).get(1);
        List<Ball> initialBallListCopy = new ArrayList<>(initialBallList);
        testBasket.remove(testBall);
        initialBallListCopy.remove(testBall);
        assertEquals(initialBallListCopy, testBasket.getBalls());
    }

    @Test
    void removeInvalidNull() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.remove(null));
    }

    @Test
    void removeInvalidUnknown() throws InvalidArgumentException {
        Ball testBall = new Ball(Color.AQUAMARINE, 12);
        assertThrows(IncompatibleStateException.class, () -> testBasket.remove(testBall));
    }

    @Test
    void containsAllValidEmpty() throws InvalidArgumentException {
        assertTrue(testBasket.containsAll(new ArrayList<>()));
    }

    @Test
    void containsAllValidItself() throws InvalidArgumentException {
        assertTrue(testBasket.containsAll(initialBallList));
    }

    @Test
    void containsAllValidModified() throws InvalidArgumentException {
        List<Ball> initialBallListCopy = new ArrayList<>(initialBallList);
        initialBallListCopy.add(new Ball(Color.AQUAMARINE, 20));
        assertFalse(testBasket.containsAll(initialBallListCopy));
    }

    @Test
    void containsAllValidKnownDuplicates() throws InvalidArgumentException {
        Ball duplicateBall = List.copyOf(initialBallList).get(1);
        assertTrue(testBasket.containsAll(List.of(duplicateBall, duplicateBall)));
    }

    @Test
    void containsAllValidUnknownDuplicates() throws InvalidArgumentException {
        Ball duplicateBall = new Ball(Color.AQUAMARINE, 12);
        assertFalse(testBasket.containsAll(List.of(duplicateBall, duplicateBall)));
    }

    @Test
    void containsAllInvalidNull() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.containsAll(null));
    }

    @Test
    void containsValidKnown() throws InvalidArgumentException {
        Ball testBall = List.copyOf(initialBallList).get(1);
        assertTrue(testBasket.contains(testBall));
    }

    @Test
    void containsValidUnknown() throws InvalidArgumentException {
        Ball testBall = new Ball(Color.AQUAMARINE, 12);
        assertFalse(testBasket.contains(testBall));
    }

    @Test
    void containsInvalidNull() {
        assertThrows(InvalidArgumentException.class, () -> testBasket.contains(null));
    }
}