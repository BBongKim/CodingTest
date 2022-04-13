// DP 풀이
// 양 끝을 제외한 안쪽이 팰린드롬이고, 양 끝이 같으면 팰린드롬이다.
// 따라서, dp[s+1][e-1] == 1 && list[s]==list[e]일 때 팰린드롬이다.

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] list;
    static int[][] dp;

    public static void solution() {
        for (int i = 1; i < N + 1; i++) {

            dp[i][i] = 1;
            if (i < N && list[i] == list[i + 1]) dp[i][i + 1] = 1;
        }

        for (int e = 3; e < N + 1; e++) {
            for (int s = 1; s < e; s++) {
                if (dp[s + 1][e - 1] == 1 && list[s] == list[e]) dp[s][e] = 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        list = new int[N + 1];
        dp = new int[N + 1][N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 1; i < N + 1; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        solution();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            sb.append(dp[S][E]).append("\n");
        }

        System.out.print(sb);
    }
}
