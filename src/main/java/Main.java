import java.util.HashMap;
import java.util.HashSet;


public class Main {
    public static void duplicate(int[] arr) {
        HashSet<Integer> intlist = new HashSet<>();
        for (int i = 0; i < arr.length - 1; i++) {
            if (!intlist.contains(arr[i])) {
                intlist.add(arr[i]);
            } else System.out.println(arr[i]);
        }

    }

    public static void main(String[] args) {
        int[] str1 = new int[100];
        for (int i = 0; i < str1.length - 1; i++) {
            str1[i] = i + 1;
        }
        str1[80] = 5;
        duplicate(str1);
    }

    public void charsCounter(String str) {
        HashMap<Character, Integer> userList = new HashMap<>();
        int charsCount;


        for (int i = 0; i < str.length(); i++) {
            if (userList.containsKey(str.charAt(i))) {
                charsCount = userList.get(str.charAt(i)) + 1;
                userList.put(str.charAt(i), charsCount);
            } else {
                userList.put(str.charAt(i), 1);
            }
        }
        System.out.println(userList);
    }
}


