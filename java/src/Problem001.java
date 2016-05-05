

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Created by singachea on 5/5/16.
 */

// If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
// Find the sum of all the multiples of 3 or 5 below 1000.

public class Problem001 {
    public static void main(String[] args) {
        List<Integer> multipliers = Arrays.asList(3, 5);

        int sum = IntStream.range(1, 1000)
                        .filter(e -> multipliers.stream().anyMatch(x -> e % x == 0))
                        .sum();

        System.out.println(sum);
    }
}
