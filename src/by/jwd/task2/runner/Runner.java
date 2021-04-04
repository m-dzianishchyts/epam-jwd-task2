package by.jwd.task2.runner;

import by.jwd.task2.entity.Ball;
import by.jwd.task2.entity.Bucket;
import by.jwd.task2.entity.Color;
import by.jwd.task2.entity.InvalidBallException;
import by.jwd.task2.entity.InvalidBallPropertyException;
import by.jwd.task2.handler.BucketHandler;
import by.jwd.task2.handler.InvalidBucketException;

public class Runner {

    public static void main(String[] args) {
	    Bucket bucket = new Bucket();

        for (int i = 0; i < 10; i++) {
            Ball ball = new Ball();
            try {
                ball.setWeight(Math.random() * 100);
                ball.setColor(Color.values()[(int) (Math.random() * 30)]);
                bucket.put(ball);
            } catch (InvalidBallPropertyException | InvalidBallException e) {
                System.err.println(e.getMessage());
                System.exit(-1);
            }
        }

        System.out.println("Balls in bucket:");
        bucket.forEach(System.out::println);

        try {
            System.out.println("\nTotal weight: " + BucketHandler.calculateTotalWeight(bucket));
            System.out.println("Blue balls in bucket: " + BucketHandler.countBallsByColor(bucket, Color.BLUE));
        } catch (InvalidBucketException | InvalidBallPropertyException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
