// DFS + DP 문제
// 그냥 DFS로 했을 때 시간초과 난거보고 DP를 써야겠다는건 알았다.
// DFS로 더 깊게 내려갔을 때마다 갈 수 있는 최댓값을 구하면서 올라오는것이 핵심이다.

// 판다가 더 큰 곳으로만 가기 때문에 왔던 곳으로 되돌아 갈일이 없다. 그래서 visited 배열은 안써도 된다.
// 근데, 시간이 더 오래걸리네?

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dp;
    static int[] x_move = {1, 0, -1, 0};
    static int[] y_move = {0, -1, 0, 1};
    static int max = 1;

    public static int dfs(int y, int x) {

        if (dp[y][x] != -1) return dp[y][x];

        dp[y][x] = 1;

        for (int i = 0; i < 4; i++) {
            int next_x = x + x_move[i];
            int next_y = y + y_move[i];

            if (0 > next_x || next_x >= N || 0 > next_y || next_y >= N) continue;
            if (map[next_y][next_x] <= map[y][x]) continue;

            int temp = 1;

            temp += dfs(next_y, next_x);
            dp[y][x] = Math.max(temp, dp[y][x]);
            max = Math.max(dp[y][x], max);
        }

        return dp[y][x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new int[N][N];

        for (int[] d : dp) Arrays.fill(d, -1);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for (int k = 0; k < N; k++) {
                map[i][k] = Integer.parseInt(st.nextToken());
            }
        }
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                dfs(y, x);
            }
        }

        System.out.println(max);
    }
}
