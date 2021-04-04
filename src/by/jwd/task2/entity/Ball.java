package by.jwd.task2.entity;

import java.io.Serializable;

public class Ball implements Serializable {

    private Color color;
    private double weight;

    public Ball() {
        color = Color.WHITE;
        weight = 1;
    }

    @Override
    public int hashCode() {
        long weightLongBits = Double.doubleToLongBits(weight);
        int hashCode = (int) (weightLongBits ^ (weightLongBits >>> 32));
        hashCode = 31 * hashCode + (color.ordinal() ^ (color.ordinal() >>> 16));
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

    public void setColor(Color color) throws InvalidBallPropertyException {
        if (color == null) {
            throw new InvalidBallPropertyException("Ball's color cannot be null.");
        }
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) throws InvalidBallPropertyException {
        if (weight <= 0) {
            throw new InvalidBallPropertyException("Ball's weight must be positive.");
        }
        this.weight = weight;
    }
}
