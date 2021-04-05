package by.epamtc.task2.entity;

import by.epamtc.task2.exception.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {

    private static final double INIT_WEIGHT = 10;
    private static final Color INIT_COLOR = Color.BLUE;
    private static Ball testBall;

    @BeforeEach
    void initBall() {
        testBall = new Ball();
        try {
            testBall.setColor(INIT_COLOR);
            testBall.setWeight(INIT_WEIGHT);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getWeight() {
        assertEquals(INIT_WEIGHT, testBall.getWeight());
    }

    @org.junit.jupiter.api.Test
    void getColor() {
        assertEquals(INIT_COLOR, testBall.getColor());
    }

    @org.junit.jupiter.api.Test
    void setInvalidWeight() {
        assertThrows(InvalidArgumentException.class, () -> testBall.setWeight(0));
        assertThrows(InvalidArgumentException.class, () -> testBall.setWeight(-3));
    }

    @org.junit.jupiter.api.Test
    void setValidWeight() throws InvalidArgumentException {
        double testWeight = 16;
        testBall.setWeight(testWeight);
        assertEquals(testWeight, testBall.getWeight());
    }

    @org.junit.jupiter.api.Test
    void setInvalidColor() {
        assertThrows(InvalidArgumentException.class, () -> testBall.setColor(null));
    }

    @org.junit.jupiter.api.Test
    void setValidColor() throws InvalidArgumentException {
        Color testColor = Color.RED;
        testBall.setColor(testColor);
        assertEquals(testColor, testBall.getColor());
    }
}