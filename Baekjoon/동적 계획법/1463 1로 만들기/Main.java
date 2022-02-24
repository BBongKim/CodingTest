// 동전 DP 문제랑 비슷하다. Bottom-up 방식으로 풀었다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int solution(int N) {
        int[] dp = new int[N + 1];

        if (N == 1) return 0;
        if (N == 2 || N == 3) return 1;

        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i < N + 1; i++) {
            int tmp = Math.min(dp[i - 1] + 1, i % 2 == 0 ? dp[i / 2] + 1 : Integer.MAX_VALUE);
            dp[i] = Math.min(tmp, i % 3 == 0 ? dp[i / 3] + 1 : Integer.MAX_VALUE);
        }

        return dp[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println(solution(N));
    }
}
