// 방 번호
// https://www.acmicpc.net/problem/1082

// DP

// i = 0 to N이고, dp[m] = max(dp[m - price] + i) 로 설정했다.
// (Knapsack처럼 현재 숫자를 뽑는 경우의 dp를 구함)

// 각 숫자를 뽑고 나서, 숫자를 문자열 배열로 변환 후에 내림차순으로 정렬해준다.
// 그리고, 가장 i 순회 후 가장 큰 수를 dp 값으로 설정해준다.

// long 범위를 넘어가서 BigInteger를 사용했다.

// package Study18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] prices;
    static int M;

    static BigInteger[] dp;

    public static BigInteger solution(int N, int[] prices, int M) {

        // dp[0] = -1
        // dp[1] = -1
        // ...
        // dp[6] = 0
        // dp[7] = dp[1] + 0 / dp[0] + 1 / -> 1
        // dp[8] = dp[2] + 0 / dp[1] + 1 / dp[0] + 2 -> 2
        // ...
        // dp[12] = dp[6] + 0 / dp[5] + 1 / dp[4] + 2 -> 2
        // dp[13] = dp[7] + 0 / dp[6] + 1 -> 10
        // dp[14] = dp[8] + 0 / dp[7] + 1 / dp[6] + 2 -> 20
        // dp[15] = dp[9] + 0 / dp[8] + 1 / dp[7] + 2 -> 21

        // dp[21] = dp[15] + 0 / dp[14] + 1 / dp[13] + 2 -> 210

        for (int m = 0; m <= M; m++) {
            BigInteger max = new BigInteger("-1");

            for (int i = 0; i < prices.length; i++) {
                if (m < prices[i]) continue;

                if (dp[m - prices[i]].equals(new BigInteger("-1"))) {
                    BigInteger t = new BigInteger(String.valueOf(i));
                    if (max.compareTo(t) < 0) {
                        max = t;
                    }
                } else {
                    String tmp = "";
                    tmp = dp[m - prices[i]] + String.valueOf(i);

                    Character[] chars = new Character[tmp.length()];
                    char[] arr = tmp.toCharArray();

                    for (int j = 0; j < arr.length; j++) {
                        chars[j] = arr[j];
                    }

                    Arrays.sort(chars, Collections.reverseOrder());

                    for (int j = 0; j < arr.length; j++) {
                        arr[j] = chars[j];
                    }

                    BigInteger t2 = new BigInteger(new String(arr));

                    if (max.compareTo(t2) < 0) {
                        max = t2;
                    }
                }
            }

            dp[m] = max;
        }

        return dp[M];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        prices = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        dp = new BigInteger[M + 1];
        Arrays.fill(dp, new BigInteger("-1"));

        System.out.println(solution(N, prices, M));
    }
}
