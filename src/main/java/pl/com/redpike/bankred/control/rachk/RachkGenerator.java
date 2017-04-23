package pl.com.redpike.bankred.control.rachk;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by Redpike
 */
public class RachkGenerator {

    private static final int BOUND = 100000000;

    public static String generateRachk() {
        Random random = new Random();
        int randomValue = random.nextInt(BOUND) + 1;
        return String.valueOf(new BigDecimal(randomValue));
    }
}
