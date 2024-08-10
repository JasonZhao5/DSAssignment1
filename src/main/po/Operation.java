package main.po;

public enum Operation {
    /**
     * Push the min value of all the popped values
     */
    min("0"),

    /**
     * Push the max value of all the popped values
     */
    max("1"),

    /**
     * Push the least common multiple of all the popped values
     */
    lcm("2"),

    /**
     * Push the greatest common divisor of all the popped values
     */
    gcd("3");

    private final String name;


    Operation(String name) {
        this.name = name;
    }

    public String getChineseName() {
        return name;
    }
}

