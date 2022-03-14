// DP 문제
// dp[i]는 i원의 경우의 수를 담고 있다.
// dp[i] += dp[i - coin]을 하면 경우의 수를 구할 수 있고, 이를 모든 coin에 대해 반복하면 된다.
// 단, (i - coin)이 0일 때도 고려해야 한다.

import java.io.*;
import java.util.*;

public class Main {
    static int[] coins;
    static int[] dp;

    public static void solution(int K) {
        for (int coin : coins) {
            for (int i = 1; i <= K; i++) {
                if (i - coin < 0) continue;
                else if (i - coin == 0) {
                    dp[i]++;
                    continue;
                }

                dp[i] += dp[i - coin];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        coins = new int[N];
        dp = new int[K + 1];

        for (int i = 0; i < N; i++) coins[i] = Integer.parseInt(br.readLine());

        Arrays.sort(coins);

        solution(K);
        System.out.println(dp[K]);
    }
}
