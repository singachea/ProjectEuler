//The prime 41, can be written as the sum of six consecutive primes:
//
//        41 = 2 + 3 + 5 + 7 + 11 + 13
//        This is the longest sum of consecutive primes that adds to a prime below one-hundred.
//
//        The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.
//
//        Which prime, below one-million, can be written as the sum of the most consecutive primes?


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


public class Problem050 {

    public static void main(String[] args) {
        int n = 1000000;
        int[] primes = IntStream.range(2, n).filter(e -> isPrime(e)).toArray();

        Tuple result = Arrays.stream(primes)
                    .mapToObj(p -> findLongestPrimeSequence(n, p, primes))
                    .max((a, b) -> a.size - b.size)
                    .get();

        System.out.println("Solution is " + result.key  );
    }

    static Tuple findLongestPrimeSequence(int target, int start, int[] primes) {

        List<Integer> pl = new ArrayList<>();
        for(int p: primes) pl.add(p);  // this is a fucking piece of shit! What a fucking language that array doesn't have indexOf

        ArrayList<Integer> result = new ArrayList(){{ add(start); }};
        ArrayList<Integer> temp = new ArrayList<>();

        int index = pl.indexOf(start);
        int sum = primes[index];

        for(int i = index + 1 ; i < primes.length; i++) {
            int cur = primes[i];
            if(sum + cur >= target) break;

            sum += cur;
            if(isPrime(sum)) {
                result.addAll(temp);
                temp.clear();
                result.add(cur);
            }
            else {
                temp.add(cur);
            }
        }

        return new Tuple(result.stream().mapToInt(Integer::intValue).sum(), result.size());
    }


    static boolean isPrime(int num) {

        if(num == 2) return true;
        if(num <= 1 || num % 2 == 0) return false;

        int s = (int)Math.sqrt(num);

        for(int i = 3; i <= s + 1; i += 2) {
            if(num % i == 0) return false;
        }

        return true;
    }


}

class Tuple {
    int key;
    int size;

    Tuple(int key, int size) {
        this.key = key;
        this.size = size;
    }
}