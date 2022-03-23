// DFS + DP 문제
// 처음에 그냥 DFS로 풀었는데 시간초과 남
// 그래서, 그냥 DP로 접근하려고 했는데 또 안됨... 둘을 같이 써야된다.

// 처음 DP 배열을 -1로 초기화하는 이유는 경우의 수가 0일 때와 아직 탐색하지 않았을 때를 구분하기 위해서

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] x_move = {1, 0, -1, 0};
    static int[] y_move = {0, -1, 0, 1};
    static boolean[][] visited;
    static int[][] map;
    static int[][] dp;

    public static int dfs(int y, int x) {

        if (y == M - 1 && x == N - 1) return 1;

        if (dp[y][x] != -1) return dp[y][x];

        dp[y][x] = 0;

        for (int i = 0; i < 4; i++) {
            int next_x = x + x_move[i];
            int next_y = y + y_move[i];

            if (0 > next_x || next_x >= N || 0 > next_y || next_y >= M) continue;

            if (map[next_y][next_x] >= map[y][x]) continue;

            dp[y][x] += dfs(next_y, next_x);
        }

        return dp[y][x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) Arrays.fill(dp[i], -1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int k = 0; k < N; k++) {
                map[i][k] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0 ,0);

        System.out.println(dp[0][0]);
    }
}
