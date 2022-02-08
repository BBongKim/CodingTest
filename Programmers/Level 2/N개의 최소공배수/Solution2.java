// gcd 함수를 이용한 풀이 훨씬 빠르다.

class Solution {
    public static int solution(int[] arr) {
        int answer = 0;

        int lcm = lcm(arr[0], arr[1]);
        for (int i = 2; i < arr.length; i++) {
            lcm = lcm(lcm, arr[i]);
        }

        answer = lcm;

        return answer;
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static void main(String[] args) {
        int[] arr = {2, 6, 8, 14};
        System.out.println(solution(arr));
    }
}