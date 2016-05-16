//The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.
//
//        There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.
//
//        What 12-digit number do you form by concatenating the three terms in this sequence?


import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;


public class Problem049 {

    public static void main(String[] args) {

        int[] primes = IntStream.range(1000, 9999).filter(e -> isPrime(e)).toArray();
        ArrayList<Integer> result = new ArrayList<>();


        Arrays.stream(primes).forEach(p -> {
            if(result.contains(p)) return;

            int[] terms  = permutate(new ArrayList<String>(Arrays.asList((p + "").split(""))))
                    .stream()
                    .map(e -> Integer.parseInt(String.join("", e)))
                    .distinct()
                    .filter(e -> Arrays.stream(primes).anyMatch(i -> i == e)).mapToInt(e -> e).toArray();

            int[] sortedTerms = Arrays.stream(terms).sorted().toArray();
            if(findEquilibrium(sortedTerms)) {
                Arrays.stream(sortedTerms).forEach(e -> {
                    result.add(e);
                });

            }
        });
    }

    static boolean findEquilibrium(int[] nums) {
        if(nums.length < 3) return false;

        for(int i = 0 ; i < nums.length - 2; i++) {
            for(int j = i + 2; j < nums.length; j++) {
                for(int k = i + 1; k < j; k++) {
                    if(nums[k] - nums[i] == nums[j] - nums[k]) {
                        System.out.println(nums[i] + "" + nums[k] + "" + nums[j]);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static <T> ArrayList<ArrayList<T>> permutate(ArrayList<T> array) {
        int length = array.size();
        ArrayList<T> pm = permute(new ArrayList<T>(), array);
        ArrayList<ArrayList<T>> result= new ArrayList<>();

        for(int i = 0; i < pm.size(); i += length) {
            result.add(new ArrayList<T>((pm.subList(i, i + length))));
        }
        return result;
    }


    static <T> ArrayList<T> permute(ArrayList<T> initial, ArrayList<T> tail) {
        if(tail.size() == 0) return initial;

        ArrayList<T> result = new ArrayList<T>();

        tail.forEach(t -> {
            ArrayList<T> remaining = (ArrayList<T>)tail.clone();
            remaining.remove(t);
            ArrayList<T> initClone = (ArrayList<T>)initial.clone();
            initClone.add(t);
            result.addAll(permute(initClone, remaining));
        });
        return result;
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
