package by.jwd.task2.handler;

import by.jwd.task2.entity.Bucket;
import by.jwd.task2.entity.Color;
import by.jwd.task2.entity.InvalidBallPropertyException;

public class BucketHandler {

    public static double calculateTotalWeight(Bucket bucket) throws InvalidBucketException {
        if (bucket == null) {
            throw new InvalidBucketException("Bucket cannot be null.");
        }
        double totalWeight = 0;
        for (var ball : bucket) {
            totalWeight += ball.getWeight();
        }
        return totalWeight;
    }

    public static int countBallsByColor(Bucket bucket, Color color)
            throws InvalidBucketException, InvalidBallPropertyException {
        if (bucket == null) {
            throw new InvalidBucketException("Bucket cannot be null.");
        }
        if (color == null) {
            throw new InvalidBallPropertyException("Color cannot be null.");
        }
        int ballsCounter = 0;
        for (var ball : bucket) {
            if (ball.getColor() == color) {
                ballsCounter++;
            }
        }
        return ballsCounter;
    }
}
