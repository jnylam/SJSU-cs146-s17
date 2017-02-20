package cc.jennylam.cs146;

/**
 * This is a wrapper class for a Comparable type E, which includes two additional values for convenience,
 * negative infinity, which is strictly less than any other value of type E, and
 * positive infinity, which is strictly greater than any other value of type E.
 * @param <E>
 */
public class Extended<E extends Comparable<E>> implements Comparable<Extended<E>> {
    private E value;
    private int inf;

    Extended(E value) {
        this(value, 0);
    }

    public static <E extends Comparable<E>> Extended<E> negativeInfinity() {
        return new Extended<>(null, -1);
    }

    public static <E extends Comparable<E>> Extended<E> positiveInfinity() {
        return new Extended<>(null, 1);
    }

    private Extended(E value, int inf) {
        this.value = value;
        this.inf = inf;
    }

    public E value() {
        return value;
    }

    @Override
    public int compareTo(Extended<E> that) {
        if (this.inf == that.inf)
            return this.inf == 0 ? this.value.compareTo(that.value) : 0;
        return (this.inf == -1 || that.inf == 1) ? -1 : 1;
    }

    @Override
    public String toString() {
        String infStr = "\u221E";
        if (inf == -1)
            return "-" + infStr;
        if (inf == 1)
            return "+" + infStr;
        return value.toString();
    }

    public int compareTo(E that) {
        if (inf != 0)
            return inf;
        return this.value.compareTo(that);
    }
}
