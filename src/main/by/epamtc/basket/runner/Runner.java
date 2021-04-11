package by.epamtc.basket.runner;

import by.epamtc.basket.entity.Ball;
import by.epamtc.basket.entity.Basket;
import by.epamtc.basket.entity.Color;
import by.epamtc.basket.exception.IncompatibleStateException;
import by.epamtc.basket.exception.InvalidArgumentException;
import by.epamtc.basket.handler.BasketHandler;

public class Runner {

    public static void main(String[] args) {
	    Basket basket = new Basket();

        for (int i = 0; i < 10; i++) {
            try {
                Color color = Color.values()[(int) (Math.random() * 3)];
                double weight = Math.random() * 100;
                basket.put(new Ball(color, weight));
            } catch (InvalidArgumentException | IncompatibleStateException e) {
                System.err.println(e.getMessage());
                System.exit(-1);
            }
        }

        System.out.println("Balls in basket:");
        basket.forEach(System.out::println);

        try {
            BasketHandler basketHandler = new BasketHandler(basket);
            System.out.println("\nTotal weight: " + basketHandler.calculateTotalWeight());
            System.out.println("Blue balls in basket: " + basketHandler.countBallsByColor(Color.BLUE));
        } catch (InvalidArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
