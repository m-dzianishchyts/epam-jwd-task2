package by.epamtc.basket.handler;

import by.epamtc.basket.entity.Basket;
import by.epamtc.basket.entity.Color;
import by.epamtc.basket.exception.InvalidArgumentException;

public class BasketHandler {

    private Basket basket;

    public BasketHandler(Basket basket) throws InvalidArgumentException {
        if (basket == null) {
            throw new InvalidArgumentException("Basket cannot be null.");
        }
        this.basket = basket;
    }

    public double calculateTotalWeight() throws InvalidArgumentException {
        double totalWeight = 0;
        for (var ball : basket) {
            totalWeight += ball.getWeight();
        }
        return totalWeight;
    }

    public int countBallsByColor(Color color) throws InvalidArgumentException {
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

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
