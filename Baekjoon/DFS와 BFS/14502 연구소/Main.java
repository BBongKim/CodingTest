// 시간 복잡도가 크지 않아서, 조합 + BFS 방식으로 풀었다.
// 벽을 세울 수 있는 모든 경우의 수를 구한 후, 그 때 마다 바이러스가 퍼지지 않은 칸을 계산했다.

import java.util.*;
import java.io.*;

public class Main {

    public static class Pos {
        int y, x;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int[][] map;
    static Pos[] list = new Pos[65];
    static ArrayList<Pos> virus_list = new ArrayList<>();
    static boolean[] visited;
    static int answer = -1;

    static int[] x_move = {1, 0, -1, 0};
    static int[] y_move = {0, -1, 0, 1};

    static int N, M;

    public static int bfs(int[][] map) {

        Queue<Pos> queue = new LinkedList<>();

        int answer = 0;
        boolean[][] bfs_visited = new boolean[N][M];

        for (Pos p : virus_list) {
            queue.offer(p);
            bfs_visited[p.y][p.x] = true;
        }

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                Pos next = new Pos(cur.y + y_move[i], cur.x + x_move[i]);

                if (next.x < 0 || next.x >= M || next.y < 0 || next.y >= N) continue;
                if (!bfs_visited[next.y][next.x] && map[next.y][next.x] == 0) {
                    map[next.y][next.x] = 2;
                    bfs_visited[next.y][next.x] = true;
                    queue.offer(next);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < M; k++) {
                if (map[i][k] == 0) answer++;
            }
        }

        return answer;
    }

    public static void combination(int N, int start, int M) {
        if (M == 0) {
            int[][] copy_map = new int[map.length][map[0].length];
            for (int i = 0; i < map.length; i++) {
                for (int k = 0; k < map[0].length; k++) {
                    copy_map[i][k] = map[i][k];
                }
            }
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    Pos p = list[i];
                    copy_map[p.y][p.x] = 1;
                }
            }
            answer = Math.max(bfs(copy_map), answer);
        } else {
            for (int i = start; i < N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    combination(N, i + 1, M - 1);
                    visited[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int zero_cnt = 0;

        map = new int[N][M];
        int idx = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int k = 0; k < M; k++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][k] = tmp;
                if (tmp == 0) {
                    list[idx++] = new Pos(i, k);
                    zero_cnt++;
                } else if (tmp == 2) {
                    virus_list.add(new Pos(i, k));
                }
            }
        }

        visited = new boolean[zero_cnt];

        combination(zero_cnt, 0, 3);

        System.out.println(answer);
    }
}
