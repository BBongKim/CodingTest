// 순열만 알면 쉽다.

import java.util.HashSet;

class Solution {
    static int answer;
    static HashSet<Integer> set = new HashSet<>();

    public static int solution(String numbers) {
        answer = 0;

        int[] list = new int[numbers.length()];

        for (int i = 0; i < numbers.length(); i++) {
            list[i] = numbers.charAt(i) - '0';
        }

        for (int i = 1; i <= numbers.length(); i++) {
            permutation(list, 0, i);
        }

        return answer;
    }

    public static void permutation(int[] list, int depth, int k) {
        if (depth == k) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < k; i++) {
                sb.append(list[i]);
            }
            int n = Integer.parseInt(sb.toString());

            if (!set.contains(n) && isPrime(n)) {
                set.add(n);
                answer++;
            }

        } else {
            for (int i = depth; i < list.length; i++) {
                swap(list, depth, i);
                permutation(list, depth + 1, k);
                swap(list, depth, i);
            }
        }
    }

    public static boolean isPrime(int n) {
        if (n == 1 || n == 0) return false;
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void swap(int[] list, int a, int b) {
        int tmp = list[a];
        list[a] = list[b];
        list[b] = tmp;
    }

    public static void main(String[] args) {
        String numbers = "17";
        System.out.println(solution(numbers));
    }
}