package by.epamtc.task2.entity;

import by.epamtc.task2.exception.IncompatibleStateException;
import by.epamtc.task2.exception.InvalidArgumentException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Basket implements Serializable, Iterable<Ball> {

    private static final int DEFAULT_CAPACITY = 10;

    private List<Ball> balls;
    private int capacity;
    private int size;

    public Basket(int capacity) throws InvalidArgumentException {
        this();
        if (capacity <= 0) {
            throw new InvalidArgumentException("Capacity must be positive.");
        }
        this.capacity = capacity;
    }

    public Basket() {
        balls = new ArrayList<>();
        capacity = DEFAULT_CAPACITY;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) throws InvalidArgumentException, IncompatibleStateException {
        if (capacity <= 0) {
            throw new InvalidArgumentException("Capacity must be positive.");
        }
        if (capacity < size) {
            throw new IncompatibleStateException("Cannot shrink basket. Capacity is less than current size.");
        }
        this.capacity = capacity;
    }

    // Returns an unmodifiable List containing the balls.
    public List<Ball> getBalls() {
        return List.copyOf(balls);
    }

    public void setBalls(Collection<Ball> balls) throws InvalidArgumentException, IncompatibleStateException {
        checkBallCollection(balls);
        try {
            if (balls.contains(null)) {
                throw new InvalidArgumentException("Ball collection must not contain null.");
            }
        } catch (NullPointerException ignored) {}
        if (balls.size() > capacity) {
            throw new IncompatibleStateException("Basket cannot fit this ball collection. Capacity is too low.");
        }
        this.balls = new ArrayList<>();
        putAll(balls);
        size = this.balls.size();
    }

    public void putAll(Collection<Ball> balls) throws InvalidArgumentException, IncompatibleStateException {
        checkBallCollection(balls);
        for (var ball : balls) {
            put(ball);
        }
    }

    public void put(Ball ball) throws InvalidArgumentException, IncompatibleStateException {
        checkBall(ball);
        if (size == capacity) {
            throw new IncompatibleStateException("Basket is full.");
        }
        boolean isDuplicate = contains(ball);
        if (isDuplicate) {
            throw new IncompatibleStateException("Basket already contains this ball.");
        } else {
            balls.add(ball);
            size++;
        }
    }

    public boolean contains(Ball ball) throws InvalidArgumentException {
        checkBall(ball);
        for (var ballInBasket : balls) {
            if (ballInBasket == ball) {
                return true;
            }
        }
        return false;
    }

    private void checkBall(Ball ball) throws InvalidArgumentException {
        if (ball == null) {
            throw new InvalidArgumentException("Ball cannot be null");
        }
    }

    private void checkBallCollection(Collection<Ball> balls) throws InvalidArgumentException {
        if (balls == null) {
            throw new InvalidArgumentException("Ball collection cannot be null");
        }
    }

    public void clear() {
        balls.clear();
        size = 0;
    }

    public void removeAll(Collection<Ball> balls) throws InvalidArgumentException, IncompatibleStateException {
        checkBallCollection(balls);
        for (var ball : balls) {
            remove(ball);
        }
    }

    public void remove(Ball ball) throws InvalidArgumentException, IncompatibleStateException {
        checkBall(ball);
        boolean containsThisBall = contains(ball);
        if (containsThisBall) {
            balls.remove(ball);
            size--;
        } else {
            throw new IncompatibleStateException("Basket does not contain this ball.");
        }
    }

    public boolean containsAll(Collection<Ball> balls) throws InvalidArgumentException {
        checkBallCollection(balls);
        for (var ball : balls) {
            if (!contains(ball)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        boolean isEmpty = size == 0;
        return isEmpty;
    }

    @Override
    public int hashCode() {
        int hashCode = balls.hashCode() >>> (Integer.SIZE / 2);
        hashCode = (Integer.SIZE - 1) * hashCode + (capacity ^ (capacity >>> (Integer.SIZE / 2)));
        hashCode = (Integer.SIZE - 1) * hashCode + (size ^ (size >>> (Integer.SIZE / 2)));
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Basket basket = (Basket) o;
        return capacity == basket.capacity && size == basket.size && balls.equals(basket.balls);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@{" + "balls=" + balls + ", capacity=" + capacity + ", size=" + size + '}';
    }

    @Override
    public Iterator<Ball> iterator() {
        return balls.iterator();
    }
}
