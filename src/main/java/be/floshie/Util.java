package be.floshie;

import java.math.BigInteger;

public final class Util {
    public static String toPaddedBinary(BigInteger n, int stringSize) {
        return String.format("%" + stringSize + "s", abd(n))
                .replace(" ", "0");
    }

    public static String abd(BigInteger n) {
        BigInteger tmp = n;
        var sb = new StringBuilder();

        while (tmp.intValue() != 0) {
            var lowestSetBit = tmp.getLowestSetBit();
            sb.append(Integer.toBinaryString(lowestSetBit));
            tmp = tmp.shiftRight(Integer.SIZE);
        }

        return sb.toString();
    }
}
