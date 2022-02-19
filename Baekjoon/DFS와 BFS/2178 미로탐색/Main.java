// 기본적인 DFS 문제

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Pos {
        int y, x, dist;

        Pos(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

    public static int solution(int[][] map, int N, int M) {
        int[] x_move = {1, 0, -1, 0};
        int[] y_move = {0, -1, 0, 1};
        boolean[][] visited = new boolean[N][M];
        int[][] distance = new int[N][M];

        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(0, 0, 1));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            distance[cur.y][cur.x] = cur.dist;

            for (int i = 0; i < 4; i++) {
                Pos next = new Pos(cur.y + y_move[i], cur.x + x_move[i], cur.dist + 1);

                if (0 <= next.x && next.x < M && 0 <= next.y && next.y < N
                && !visited[next.y][next.x] && map[next.y][next.x] == 1) {
                    queue.offer(next);
                    // 여기서 중복처리 안하면 메모리 초과 날 수 있음
                    visited[next.y][next.x] = true;
                }
            }
        }

        return distance[N - 1][M - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int k = 0; k < M; k++) {
                map[i][k] = tmp.charAt(k) - '0';
            }
        }

        System.out.println(solution(map, N, M));
    }
}
