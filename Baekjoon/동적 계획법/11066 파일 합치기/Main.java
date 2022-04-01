// DP 문제
// 못풀어서 풀이봤다 ㅠㅠ
// Bottom-Up 방식 풀이
// dp[i][j] 는 i 페이지 부터 j 페이지까지의 최소합이다.
// 3개 페이지 이상의 합부터는 i부터 j까지 페이지를 2그룹씩 나누어 계산한 합 중 최소값이 된다. (연속된 2페이지만 더할 수 있기 때문이다.)
// 따라서, dp[i][j] = min(dp[i][mid] + dp[mid + 1][j] + sum(i,j))이다.

// 마지막에는 i부터 j 까지의 범위의 합을 더해야한다.

import java.io.*;
import java.util.*;

public class Main {

    public static void solution(int[] list, int K) {
        int[][] dp = new int[K + 1][K + 1];
        int[] sum = new int[K + 1];

        for (int i = 1; i <= K; i++) sum[i] = sum[i - 1] + list[i];

        for (int gap = 1; gap < K; gap++) {
            for (int s = 1; s <= K - gap; s++) {
                int e = s + gap;
                dp[s][e] = (int)1e9;

                for (int mid = s; mid < e; mid++) {
                    dp[s][e] = Math.min(dp[s][e], dp[s][mid] + dp[mid + 1][e] + sum[e] - sum[s - 1]);
                }
            }
        }

        System.out.println(dp[1][K]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());

            int[] list = new int[K + 1];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for (int i = 1; i <= K; i++) list[i] = Integer.parseInt(st.nextToken());

            solution(list, K);
        }
    }
}
