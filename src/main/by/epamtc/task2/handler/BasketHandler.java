package by.epamtc.task2.handler;

import by.epamtc.task2.entity.Basket;
import by.epamtc.task2.entity.Color;
import by.epamtc.task2.exception.InvalidArgumentException;

public class BasketHandler {

    public static double calculateTotalWeight(Basket basket) throws InvalidArgumentException {
        if (basket == null) {
            throw new InvalidArgumentException("Basket cannot be null.");
        }
        double totalWeight = 0;
        for (var ball : basket) {
            totalWeight += ball.getWeight();
        }
        return totalWeight;
    }

    public static int countBallsByColor(Basket basket, Color color) throws InvalidArgumentException {
        if (basket == null) {
            throw new InvalidArgumentException("Basket cannot be null.");
        }
        if (color == null) {
            throw new InvalidArgumentException("Color cannot be null.");
        }
        int ballsCounter = 0;
        for (var ball : basket) {
            if (ball.getColor() == color) {
                ballsCounter++;
            }
        }
        return ballsCounter;
    }
}
