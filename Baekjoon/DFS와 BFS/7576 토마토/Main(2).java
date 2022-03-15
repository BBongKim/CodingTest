// BFS 문제

// 풀이2
// map[next.y][next.x] = map[cur.y][cur.x] + 1로 만들어서 max 값을 출력하는게 더 괜찮은 방법이라 해서, 다시 풀어봄.

import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int[] x_move = {1, 0, -1, 0};
    static int[] y_move = {0, -1, 0, 1};
    static int answer = -1;

    static Queue<Pos> queue = new LinkedList<>();


    static class Pos {
        int y, x;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void solution(int N, int M) {
        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                Pos next = new Pos(cur.y + y_move[i], cur.x + x_move[i]);

                if (next.y < 0 || next.y >= N || next.x < 0 || next.x >= M || visited[next.y][next.x]) continue;

                if (map[next.y][next.x] == 0) {
                    visited[next.y][next.x] = true;
                    map[next.y][next.x] = map[cur.y][cur.x] + 1;
                    answer = Math.max(answer, map[next.y][next.x]);
                    queue.offer(next);
                }
            }
        }

        answer--;

        boolean flag = false;

        for (int[] y : map) {
            for (int x : y) {
                if (x == 0) {
                    flag = true;
                    break;
                }
            }
        }

        if (flag) answer = -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        boolean flag = false;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < M; k++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1) {
                    queue.offer(new Pos(i, k));
                } else if (tmp == 0) flag = true;
                map[i][k] = tmp;
            }
        }

        if (!flag) {
            answer = 0;
            System.out.println(answer);
            return;
        }

        solution(N, M);
        System.out.println(answer);
    }
}
