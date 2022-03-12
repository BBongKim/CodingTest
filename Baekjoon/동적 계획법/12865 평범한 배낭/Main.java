// DP 문제
// 처음에 그냥 dfs로 했는데 바로 시간초과되서 풀이 봄
// dp[i][j]는 현재 가방 무게에서 가장 가치가 큰 값을 담고 있는다.
// dp[i][j]의 값 갱신은 Math.max(현재 가방 무게에서 앞으로 넣을 물품 무게를 뺀 곳의 dp값 + 앞으로 넣을 물품의 가치, 이 물품을 아예 넣지 않았을 경우의 dp 값)

import java.util.*;
import java.io.*;

public class Main {
    static int[][] dp;
    static int N;
    static int K;

    static class Item {
        int w, v;

        Item(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    public static void solution(Item[] list) {
        for (int i = 1; i < N + 1; i++) {
            for (int k = 1; k < K + 1; k++) {
                if (list[i - 1].w <= k) dp[i][k] = Math.max(dp[i - 1][k], list[i - 1].v + dp[i - 1][k - list[i - 1].w]);
                else dp[i][k] = dp[i-1][k];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Item[] list = new Item[N];
        dp = new int[N + 1][K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[i] = new Item(w, v);
        }

        solution(list);

        System.out.println(dp[N][K]);
    }
}
