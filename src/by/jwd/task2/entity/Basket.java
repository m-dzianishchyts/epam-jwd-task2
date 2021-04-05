package by.jwd.task2.entity;

import by.jwd.task2.exception.IncompatibleStateException;
import by.jwd.task2.exception.InvalidArgumentException;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Basket implements Serializable, Iterable<Ball> {

    private final Set<Ball> balls;
    private int capacity;
    private int size;

    public Basket() {
        balls = new HashSet<>();
        capacity = 10;
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

    // Returns an unmodifiable Set containing the balls.
    public Set<Ball> getBalls() {
        return Set.copyOf(balls);
    }

    public void putAll(Collection<Ball> balls) throws InvalidArgumentException, IncompatibleStateException {
        if (balls == null) {
            throw new InvalidArgumentException("Ball collection cannot be null");
        }
        for (var ball : balls) {
            put(ball);
        }
    }

    public void put(Ball ball) throws InvalidArgumentException, IncompatibleStateException {
        if (ball == null) {
            throw new InvalidArgumentException("Ball cannot be null");
        }
        if (size == capacity) {
            throw new IncompatibleStateException("Basket is full.");
        }
        boolean newBallProvided = balls.add(ball);
        if (newBallProvided) {
            size++;
        } else {
            throw new IncompatibleStateException("Basket already contains this ball.");
        }
    }

    public void clear() {
        balls.clear();
        size = 0;
    }

    public void removeAll(Set<Ball> balls) throws InvalidArgumentException, IncompatibleStateException {
        if (balls == null) {
            throw new InvalidArgumentException("Ball set cannot be null");
        }
        for (var ball : balls) {
            remove(ball);
        }
    }

    public void remove(Ball ball) throws InvalidArgumentException, IncompatibleStateException {
        if (ball == null) {
            throw new InvalidArgumentException("Ball cannot be null");
        }
        boolean containedThisBall = balls.remove(ball);
        if (containedThisBall) {
            size--;
        } else {
            throw new IncompatibleStateException("Basket does not contain this ball.");
        }
    }

    public boolean containsAll(Set<Ball> balls) throws InvalidArgumentException {
        if (balls == null) {
            throw new InvalidArgumentException("Ball set cannot be null");
        }
        for (var ball : balls) {
            if (!contains(ball)) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(Ball ball) throws InvalidArgumentException {
        if (ball == null) {
            throw new InvalidArgumentException("Ball cannot be null");
        }
        boolean containsThisBall = balls.contains(ball);
        return containsThisBall;
    }

    @Override
    public int hashCode() {
        int hashCode = balls.hashCode() >>> 16;
        hashCode = 31 * hashCode + (capacity ^ (capacity >>> 16));
        hashCode = 31 * hashCode + (size ^ (size >>> 16));
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
