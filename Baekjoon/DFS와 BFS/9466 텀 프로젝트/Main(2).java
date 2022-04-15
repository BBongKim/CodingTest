// 다른분 풀이 참고해서 DFS로 풀어봤다.
// 이전에 방문한 노드면 이제 사이클인지 재검증한다.
// 만약 이전에 사이클 판정이 안난 노드이면, 사이클이 확정이다.

import java.io.*;
import java.util.*;

public class Main {
    static int[] list;
    static boolean[] visited;
    static boolean[] done;
    static int answer = 0;

    public static void dfs(int cur) {
        if (visited[cur]) return;

        int next = list[cur];
        visited[cur] = true;

        if (!visited[next]) dfs(next);

        if (!done[next]) {
            answer++;
            for (int i = next; i != cur; i = list[i]) answer++;
        }

        done[cur] = true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            list = new int[N + 1];
            visited = new boolean[N + 1];
            done = new boolean[N + 1];
            answer = 0;

            for (int i = 1; i < N + 1; i++) {
                list[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i < N + 1; i++) dfs(i);

            System.out.println(N - answer);
        }
    }
}
