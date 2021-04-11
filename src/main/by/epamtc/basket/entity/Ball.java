package by.epamtc.basket.entity;

import by.epamtc.basket.exception.InvalidArgumentException;

import java.io.Serializable;

public class Ball implements Serializable {

    private Color color;
    private double weight;

    public Ball() {
        color = Color.WHITE;
        weight = 1;
    }

    public Ball(Color color, double weight) throws InvalidArgumentException {
        if (color == null) {
            throw new InvalidArgumentException("Ball's color cannot be null.");
        }
        if (weight <= 0) {
            throw new InvalidArgumentException("Ball's weight must be positive.");
        }
        this.color = color;
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        long weightLongBits = Double.doubleToLongBits(weight);
        int hashCode = (int) (weightLongBits ^ (weightLongBits >>> (Long.SIZE / 2)));
        hashCode = (Integer.SIZE - 1) * hashCode + (color.ordinal() ^ (color.ordinal() >>> (Integer.SIZE / 2)));
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
        Ball ball = (Ball) o;
        return Double.compare(ball.weight, weight) == 0 && color == ball.color;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@{" + "color = " + color + ", weight = " + weight + '}';
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) throws InvalidArgumentException {
        if (color == null) {
            throw new InvalidArgumentException("Ball's color cannot be null.");
        }
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) throws InvalidArgumentException {
        if (weight <= 0) {
            throw new InvalidArgumentException("Ball's weight must be positive.");
        }
        this.weight = weight;
    }
}
