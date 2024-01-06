package club.someoneice.makpiraaqarvik.util.math;

import net.minecraft.core.BlockPos;

public class MathUtil {
    public static double posEuclideanDistance(BlockPos posX, BlockPos posY) {
        double x, y, z;

        if (posY.getX() > posX.getX())
            x = posY.getX() - posX.getX();
        else x = posX.getX() - posY.getX();

        if (posY.getY() > posX.getY())
            y = posY.getY() - posX.getY();
        else y = posX.getY() - posY.getY();

        if (posY.getZ() > posX.getZ())
            z = posY.getZ() - posX.getZ();
        else z = posX.getZ() - posY.getZ();

        x = Math.pow(x, 2);
        y = Math.pow(y, 2);
        z = Math.pow(z, 2);

        return Math.sqrt(x + y + z);
    }

    public static int add(final int inputA, final int inputB) {
        int s, c, a = inputA, b = inputB;

        do {
            s = a ^ b;
            c = (a & b) << 1;

            a = s;
            b = c;
        } while (c !=0);

        return s;
    }

    public static int sub(final int inputA, final int inputB) {
        return add(inputA, add(~inputB,1));
    }
    public static int mul(final int inputA, final int inputB) {
        int res = 0, a = inputA, b = inputB;

        while (b != 0) {
            if ((b & 1) == 1) res = add(res, a);

            a <<= 1;
            b >>>= 1;
        }

        return res;
    }

    public static int div(final int inputA, final int inputB) {
        int count = 0, t, a = inputA;

        while (a >= inputB) {
            t = inputB;

            while (a >= (t << 1)) {
                t <<= 1;
                count = add(count, 1);
            }

            a = sub(a, t);
            count = add(count, 1);
        }

        return count;
    }
}
