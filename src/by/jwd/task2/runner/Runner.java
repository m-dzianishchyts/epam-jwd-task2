package by.jwd.task2.runner;

import by.jwd.task2.entity.Ball;
import by.jwd.task2.entity.Basket;
import by.jwd.task2.entity.Color;
import by.jwd.task2.entity.InvalidBallException;
import by.jwd.task2.entity.InvalidBallPropertyException;
import by.jwd.task2.handler.BasketHandler;
import by.jwd.task2.handler.InvalidBasketException;

public class Runner {

    public static void main(String[] args) {
	    Basket basket = new Basket();

        for (int i = 0; i < 10; i++) {
            Ball ball = new Ball();
            try {
                ball.setWeight(Math.random() * 100);
                ball.setColor(Color.values()[(int) (Math.random() * 30)]);
                basket.put(ball);
            } catch (InvalidBallPropertyException | InvalidBallException e) {
                System.err.println(e.getMessage());
                System.exit(-1);
            }
        }

        System.out.println("Balls in basket:");
        basket.forEach(System.out::println);

        try {
            System.out.println("\nTotal weight: " + BasketHandler.calculateTotalWeight(basket));
            System.out.println("Blue balls in basket: " + BasketHandler.countBallsByColor(basket, Color.BLUE));
        } catch (InvalidBasketException | InvalidBallPropertyException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
