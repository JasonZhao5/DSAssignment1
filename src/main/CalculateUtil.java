package main;

public class CalculateUtil {
    /**
     * Calculate the least common multiple of two integers
     *
     * @param a int 1
     * @param b int 2
     * @return The least common multiple of two integers
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Calculate the least common multiple of two integers
     *
     * @param a int 1
     * @param b int 2
     * @return The least common multiple of two integers
     */
    public static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }
}
