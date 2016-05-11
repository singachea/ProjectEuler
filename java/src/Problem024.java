//A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:
//
//        012   021   102   120   201   210
//
//        What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?


import java.util.ArrayList;

public class Problem024 {
    static int counter = 0;
    static final int target = 1000000;

    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        for(int i = 0; i < 10; i++) a.add(i + "");

        permutate("", a);
    }

    static <T> void permutate(String str, ArrayList<T> arr) {
        if(arr.size() <= 1) {
            counter++;
            if(counter == target) System.out.println(str + arr.get(0).toString());
            return;
        }

        for(int i = 0; i < arr.size(); i++) {
            String head = arr.get(i).toString();
            ArrayList<T> clone = (ArrayList<T>) arr.clone();
            clone.remove(i);
            permutate(str + head, clone);
        }
    }
}
