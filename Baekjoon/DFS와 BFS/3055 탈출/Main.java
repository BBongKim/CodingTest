// BFS 문제
// 큐를 2개를 이용하는 BFS이다.

// 너무 오래걸렸다 ㅠㅠ
// 처음에 백트래킹하느라 큐, 스택 다 써서 풀었는데 메모리 땜에 막혔다.
// 결국 어느정도 풀이법을 보았다..
// 핵심은 물을 먼저 채우고, 고슴도치를 이동시키는 것이다.

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static ArrayList<Pos> floods = new ArrayList<>();
    static int[] x_move = {1, 0, -1, 0};
    static int[] y_move = {0, -1, 0, 1};
    static Pos start, end;
    static int answer = (int) 1e10;

    static Queue<Pos> flood_queue = new LinkedList<>();
    static Queue<Pos> queue = new LinkedList<>();

    static class Pos {
        int y, x;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void solution() {

        visited[start.y][start.x] = true;
        queue.offer(new Pos(start.y, start.x));

        int cnt = 0;

        while (!queue.isEmpty()) {
            flood();
            move(++cnt);
        }
    }

    public static boolean isPossible(int y, int x) {
        return map[y][x] == '.' || map[y][x] == 'D';
    }

    public static void move(int cnt) {
        int T = queue.size();

        while (T-- > 0) {
            Pos cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                Pos next = new Pos(cur.y + y_move[i], cur.x + x_move[i]);

                if (0 > next.y || next.y >= N || 0 > next.x || next.x >= M || visited[next.y][next.x]) continue;
                if (map[next.y][next.x] == 'X' || !isPossible(next.y, next.x)) continue;

                if (map[next.y][next.x] == 'D') {
                    answer = cnt;
                    queue.clear();
                    return;
                }

                visited[next.y][next.x] = true;
                map[next.y][next.x] = 'S';
                queue.offer(next);
            }
        }
    }


    public static void flood() {
        int T = flood_queue.size();

        while (T-- > 0 && !flood_queue.isEmpty()) {
            Pos cur = flood_queue.poll();

            for (int i = 0; i < 4; i++) {
                Pos next = new Pos(cur.y + y_move[i], cur.x + x_move[i]);

                if (0 > next.y || next.y >= N || 0 > next.x || next.x >= M) continue;
                if (map[next.y][next.x] != '.') continue;

                map[next.y][next.x] = '*';
                flood_queue.offer(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int k = 0; k < M; k++) {
                map[i][k] = s.charAt(k);

                if (map[i][k] == 'D') end = new Pos(i, k);
                else if (map[i][k] == 'S') start = new Pos(i, k);
                else if (map[i][k] == '*') floods.add(new Pos(i, k));
            }
        }

        for (Pos f : floods) {
            visited[f.y][f.x] = true;
            flood_queue.offer(f);
        }

        solution();

        System.out.println(answer == (int) 1e10 ? "KAKTUS" : answer);
    }
}
