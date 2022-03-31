// 그래프 탐색 문제
// BFS로 풀었다.
// 1. BFS로 빙하 주변의 바다 갯수와 현재 덩이리 수를 같이 구한다.
// 2. 한번에 map에서 빙하를 녹인다.
// 3. 덩어리가 2이상이 될 때까지 반복한다.
// 4. 만약 덩어리 개수가 0개이면 답은 0으로 출력한다.

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int answer = 0;
    static int[] x_move = {1, 0, -1, 0};
    static int[] y_move = {0, -1, 0, 1};
    static ArrayList<Pos> glaciers = new ArrayList<>();

    static class Pos {
        int y, x, sea;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void solution() {
        // 2 덩어리 확인
        int piece = bfs();

        while (piece == 1) {
            ArrayList<Pos> tmp_list = new ArrayList<>();
            // 빙산 녹이기
            for (Pos g : glaciers) {
                int tmp = map[g.y][g.x] - g.sea;
                if (tmp > 0) {
                    map[g.y][g.x] = tmp;
                    tmp_list.add(g);
                }
                else map[g.y][g.x] = 0;
            }
            glaciers = tmp_list;
            answer++;

            piece = bfs();

            if (piece == 0) answer = 0;
        }
    }

    public static int bfs() {
        ArrayList<Pos> list = new ArrayList<>(glaciers);
        glaciers = new ArrayList<>();
        Queue<Pos> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int piece = 0;

        for (Pos p : list) {
            if (visited[p.y][p.x]) continue;

            q.offer(p);
            visited[p.y][p.x] = true;
            piece++;

            while (!q.isEmpty()) {
                Pos cur = q.poll();
                int cnt = 0;

                for (int i = 0; i < 4; i++) {
                    Pos next = new Pos(cur.y + y_move[i], cur.x + x_move[i]);

                    if (!visited[next.y][next.x] && map[next.y][next.x] != 0) {
                        q.offer(next);
                        visited[next.y][next.x] = true;
                    } else if (map[next.y][next.x] == 0) cnt++;
                }
                cur.sea = cnt;
                glaciers.add(cur);
            }
        }

        return piece;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int k = 0; k < M; k++) {
                map[i][k] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기화
        for (int y = 1; y < N - 1; y++) {
            for (int x = 1; x < M - 1; x++) {
                int sea = 0;

                if (map[y][x] != 0) {
                    for (int m = 0; m < 4; m++) {
                        if (map[y + y_move[m]][x + x_move[m]] == 0) sea++;
                    }
                    Pos tmp = new Pos(y, x);
                    tmp.sea = sea;
                    glaciers.add(tmp);
                }
            }
        }

        solution();

        System.out.println(answer);
    }
}
