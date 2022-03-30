// 플로이드로 쉽게 풀었는데, 다익스트라가 훨씬 빠를거다.

import java.io.*;
import java.util.*;

public class Main {
    static int N, E;
    static int node1, node2;
    static int answer;
    static int[][] map;

    public static void solution() {
        for (int i = 1; i < N + 1; i++) {
            for (int k = 1; k < N + 1; k++) {
                for (int p = 1; p < N + 1; p++) {
                    map[k][p] = Math.min(map[k][p], map[k][i] + map[i][p]);
                }
            }
        }

        int tmp1 = map[1][node1] + map[node1][node2] + map[node2][N];
        int tmp2 = map[1][node2] + map[node2][node1] + map[node1][N];

        answer = Math.min(tmp1, tmp2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];

        for (int[] m : map) Arrays.fill(m, (int) 1e9);

        for (int i = 0; i < N + 1; i++) {
            for (int k = 0; k < N + 1; k++) {
                if (i == k) map[i][k] = 0;
            }
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a][b] = c;
            map[b][a] = c;
        }

        st = new StringTokenizer(br.readLine(), " ");
        node1 = Integer.parseInt(st.nextToken());
        node2 = Integer.parseInt(st.nextToken());

        solution();

        System.out.println(answer < 0 ? -1 : answer);
    }
}
