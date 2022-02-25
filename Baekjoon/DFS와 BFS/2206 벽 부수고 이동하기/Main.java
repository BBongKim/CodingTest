// 못풀어서 풀이봄
// visited 배열을 한번이라도 부셨을 때와 안부셨을 때의 경로로 나누는 걸 생각하는것이 어려웠다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Pos {
        int y, x, dist;
        boolean broken;

        Pos(int y, int x, int dist, boolean broken) {
            this.y = y;
            this.x = x;
            this.dist = dist;
            this.broken = broken;
        }
    }

    static int N, M;
    static int[] x_move = {1, 0, -1, 0};
    static int[] y_move = {0, -1, 0, 1};
    static boolean[][][] visited;

    public static int solution(int[][] map) {

        Queue<Pos> queue = new LinkedList<>();

        queue.offer(new Pos(0, 0, 1, false));

        visited[0][0][0] = true;
        visited[1][0][0] = true;

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            if (cur.y == N - 1 && cur.x == M - 1) return cur.dist;

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + y_move[i];
                int nx = cur.x + x_move[i];

                if (0 > ny || ny >= N || 0 > nx || nx >= M ) continue;

                if (map[ny][nx] == 0) {
                    // 지금까지 뿌 X
                    if (!cur.broken && !visited[0][ny][nx]) {
                        visited[0][ny][nx] = true;
                        queue.offer(new Pos(ny, nx, cur.dist + 1, false));
                    } else if (cur.broken && !visited[1][ny][nx]) {
                        visited[1][ny][nx] = true;
                        queue.offer(new Pos(ny, nx, cur.dist + 1, true));
                    }
                } else if (map[ny][nx] == 1) {
                    if (!cur.broken && !visited[1][ny][nx]) {
                        visited[1][ny][nx] = true;
                        queue.offer(new Pos(ny, nx, cur.dist + 1, true));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[2][N][M];
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for (int k = 0; k < M; k++) {
                map[i][k] = s.charAt(k) - '0';
            }
        }

        System.out.println(solution(map));
    }
}
