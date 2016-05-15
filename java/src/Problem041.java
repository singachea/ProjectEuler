//We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is also prime.
//
//        What is the largest n-digit pandigital prime that exists?


import java.util.ArrayList;
import java.util.stream.IntStream;

public class Problem041 {

    public static void main(String[] args) {

        int solution = IntStream.range(1, 10).filter(i -> {
            int sum = IntStream.range(1, i + 1).sum();

            return sum % 3 != 0; // otherwise it's divisible by 3 which is not prime
        }).mapToObj(i -> {
            ArrayList<String> terms = new ArrayList<String>();
            IntStream.range(1, i + 1).forEach(e -> terms.add(e + "")); // feed this as pandigital digit list
            ArrayList<ArrayList<String>> aa  = permutate(terms); // permutate it

            return aa.stream()
                    .map(e -> Integer.parseInt(String.join("", e)))
                    .filter(e -> isPrime(e)); // check prime
        }).flatMap(e -> e).max((a,b) -> a - b).get();


        System.out.println("Solution is " + solution);
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
