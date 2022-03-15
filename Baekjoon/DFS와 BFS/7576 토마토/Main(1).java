// BFS 문제
// BFS로 탐색하면서, 전날에 삽입한 위치들을 모두 큐에서 뽑았을 경우 하루가 지난걸로 친다.

// 근데, 다른 풀이 보니 그냥 map[next.y][next.x] = map[cur.y][cur.x] + 1로 만들어서 max 값을 출력하는게 더 괜찮은 방법이다.

import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int[] x_move = {1, 0, -1, 0};
    static int[] y_move = {0, -1, 0, 1};
    static int answer = -1;

    static int cnt_inserted = 0;

    static Queue<Pos> queue = new LinkedList<>();


    static class Pos {
        int y, x;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void solution(int N, int M) {
        int cnt_poll = 0;
        int cnt_tmp = 0;

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            cnt_poll++;

            for (int i = 0; i < 4; i++) {
                Pos next = new Pos(cur.y + y_move[i], cur.x + x_move[i]);

                if (next.y < 0 || next.y >= N || next.x < 0 || next.x >= M || visited[next.y][next.x]) continue;

                if (map[next.y][next.x] == 0) {
                    visited[next.y][next.x] = true;
                    map[next.y][next.x] = 1;
                    queue.offer(next);
                    cnt_tmp++;
                }
            }

            if (cnt_poll == cnt_inserted) {
                answer++;
                cnt_inserted = cnt_tmp;
                cnt_tmp = 0;
                cnt_poll = 0;
            }
        }

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
                    cnt_inserted++;
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
