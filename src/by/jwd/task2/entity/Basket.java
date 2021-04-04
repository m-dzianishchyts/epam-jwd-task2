package by.jwd.task2.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Basket implements Serializable, Iterable<Ball> {

    private final Set<Ball> balls;

    public Basket() {
        balls = new HashSet<>();
    }

    // Returns an unmodifiable Set containing the balls.
    public Set<Ball> getBalls() {
        return Set.copyOf(balls);
    }

    public boolean putAll(Collection<Ball> balls) throws InvalidBallException {
        if (balls == null) {
            throw new InvalidBallException("Ball collection cannot be null");
        }
        boolean changed = false;
        for (var ball : balls) {
            if (put(ball)) {
                changed = true;
            }
        }
        return changed;
    }

    public boolean put(Ball ball) throws InvalidBallException {
        if (ball == null) {
            throw new InvalidBallException("Ball cannot be null");
        }
        boolean newBallPut = balls.add(ball);
        return newBallPut;
    }

    public void clear() {
        balls.clear();
    }

    public boolean removeAll(Collection<Ball> balls) throws InvalidBallException {
        if (balls == null) {
            throw new InvalidBallException("Ball collection cannot be null");
        }
        boolean changed = false;
        for (var ball : balls) {
            if (remove(ball)) {
                changed = true;
            }
        }
        return changed;
    }

    public boolean remove(Ball ball) throws InvalidBallException {
        if (ball == null) {
            throw new InvalidBallException("Ball cannot be null");
        }
        boolean containedBall = balls.remove(ball);
        return containedBall;
    }

    @Override
    public int hashCode() {
        return balls.hashCode() >>> 16;
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
        return balls.equals(basket.balls);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@{" + "balls=" + balls + '}';
    }

    @Override
    public Iterator<Ball> iterator() {
        return balls.iterator();
    }
}
