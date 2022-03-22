// 그냥 플로이드 워셜 구현 문제

import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;

    public static void solution(int N) {
        for (int i = 1; i <= N; i++) {
            for (int k = 1; k <= N; k++) {
                for (int p = 1; p <= N; p++) {
                    map[k][p] = Math.min(map[k][p], map[k][i] + map[i][p]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];

        for (int i = 0; i < N + 1; i++) Arrays.fill(map[i], (int)1e9);

        for (int i = 0; i < N + 1; i++) {
            for (int k = 0; k < N + 1; k++) {
                if (i == k) map[i][k] = 0;
            }
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a][b] = Math.min(c, map[a][b]);
        }

        solution(N);

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < N + 1; i++) {
            for (int k = 1; k < N + 1; k++) {
                if (map[i][k] == (int)1e9) sb.append(0).append(" ");
                else sb.append(map[i][k]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
