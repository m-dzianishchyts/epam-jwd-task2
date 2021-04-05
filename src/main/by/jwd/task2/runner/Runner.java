package by.jwd.task2.runner;

import by.jwd.task2.entity.Ball;
import by.jwd.task2.entity.Basket;
import by.jwd.task2.entity.Color;
import by.jwd.task2.exception.IncompatibleStateException;
import by.jwd.task2.exception.InvalidArgumentException;
import by.jwd.task2.handler.BasketHandler;

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
            System.out.println("\nTotal weight: " + BasketHandler.calculateTotalWeight(basket));
            System.out.println("Blue balls in basket: " + BasketHandler.countBallsByColor(basket, Color.BLUE));
        } catch (InvalidArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
