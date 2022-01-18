class Solution {
    public static String solution(int n) {
        String answer = "";

        answer = getAns(n);

        return answer;
    }

    public static String getAns(int n) {
        String[] num = {"4", "1", "2", "4"};

        StringBuilder answer = new StringBuilder();
        int r;

        while (n > 3) {
            r = n % 3;

            if (r == 0) {
                answer.append(num[3]);
                n--;
            } else answer.append(num[r]);

            n /= 3;
        }

        answer.append(num[n]);
        return answer.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(solution(29));
    }
}