package com.captainkray.krayscandles.util;

import java.util.Random;

public class MathHelper {

    private static Random random = new Random();

    public static boolean roll(double percent) {
        return (random.nextDouble() * 100) <= percent;
    }
}
