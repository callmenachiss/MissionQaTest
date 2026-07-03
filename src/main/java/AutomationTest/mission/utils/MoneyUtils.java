package AutomationTest.mission.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public final class MoneyUtils {

    private MoneyUtils() {
    }

    public static BigDecimal parseMoney(String text) {
        String amount = text.replaceAll("[^0-9.]", "");
        return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal sum(List<BigDecimal> values) {
        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal value : values) {
            total = total.add(value);
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal percentageOf(BigDecimal amount, int percentage) {
        return amount
                .multiply(new BigDecimal(percentage))
                .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
    }
}
