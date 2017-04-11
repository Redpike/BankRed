package pl.com.redpike.bankred.control.klient;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by Redpike
 */
public class ModuloGenerator {

    private static final int BOUND = 1000000;

    public static BigDecimal generateModulo() {
        Random random = new Random();
        int randomValue = random.nextInt(BOUND) + 1;
        return new BigDecimal(randomValue);
    }
}
