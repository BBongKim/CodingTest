// 제곱수의 합
// https://www.acmicpc.net/problem/1699

// DP

// 제곱근이 있으면 1을 넣어주고,
// 없으면 dp[i] = Math.min(dp[i], dp[j] + dp[i - j]) 을 통해 최소 항 개수를 찾았다.

// 맞긴했는데, 속도가 매우 느리다.

// 1부터 제곱 수를 빼서 계산하는게 더 빠르다.

package Study18;

// dp[1] = 1
// dp[2] = 2
// dp[3] = 3
// dp[4] = 1
// dp[5] = 2
// dp[6] = 3
// dp[7] = 4

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {
    public static int solution(int N) {
        if (N == 1) return 1;
        if (N == 2) return 2;

        int[] dp = new int[N + 1];

        Arrays.fill(dp, (int) 1e9);

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {

            if (Math.sqrt(i) % 1 == 0.0) {
                dp[i] = 1;
            }

            /*for (int j = i - 1; j > i / 2; j--) {
                dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
            }*/

            for (int j = 1; j * j < i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(solution(N));
    }
}
