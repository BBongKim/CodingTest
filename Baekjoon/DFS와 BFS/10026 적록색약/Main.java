// BFS 문제
// 색약일 경우는 R과 G를 같게 만든 map을 사용하면 된다.

import java.util.*;
import java.io.*;

public class Main {
    static int answer = 0;
    static char[][] map_a;
    static char[][] map_b;
    static boolean[][] visited;
    static int[] x_move = {1, 0, -1, 0};
    static int[] y_move = {0, -1, 0, 1};

    static class Pos {
        int y, x;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void solution(char[][] map, int N) {
        Queue<Pos> queue = new LinkedList<>();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (!visited[y][x]) {
                    queue.offer(new Pos(y, x));
                    visited[y][x] = true;
                    answer++;

                    while(!queue.isEmpty()) {
                        Pos cur = queue.poll();

                        for (int i = 0; i < 4; i++) {
                            Pos next = new Pos(cur.y + y_move[i], cur.x + x_move[i]);

                            if (next.y < 0 || next.y >= N || next.x < 0 || next.x >= N || visited[next.y][next.x]) continue;

                            if (map[next.y][next.x] == map[cur.y][cur.x]) {
                                queue.offer(next);
                                visited[next.y][next.x] = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map_a = new char[N][N];
        map_b = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int k = 0; k < N; k++) {
                map_a[i][k] = s.charAt(k);

                if (s.charAt(k) == 'G') map_b[i][k] = 'R';
                else map_b[i][k] = s.charAt(k);
            }
        }

        solution(map_a, N);
        System.out.print(answer + " ");

        answer = 0;
        visited = new boolean[N][N];

        solution(map_b, N);
        System.out.println(answer);
    }
}
