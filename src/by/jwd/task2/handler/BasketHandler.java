package by.jwd.task2.handler;

import by.jwd.task2.entity.Basket;
import by.jwd.task2.entity.Color;
import by.jwd.task2.entity.InvalidBallPropertyException;

public class BasketHandler {

    public static double calculateTotalWeight(Basket basket) throws InvalidBasketException {
        if (basket == null) {
            throw new InvalidBasketException("Basket cannot be null.");
        }
        double totalWeight = 0;
        for (var ball : basket) {
            totalWeight += ball.getWeight();
        }
        return totalWeight;
    }

    public static int countBallsByColor(Basket basket, Color color)
            throws InvalidBasketException, InvalidBallPropertyException {
        if (basket == null) {
            throw new InvalidBasketException("Basket cannot be null.");
        }
        if (color == null) {
            throw new InvalidBallPropertyException("Color cannot be null.");
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
