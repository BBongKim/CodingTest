// BFS 문제
// 다른 풀이보니까 훨씬 깔끔한 방법도 있었다.
// https://devowen.com/317

// 일단 나는 이렇게 풀었다.
// 1. 섬마다 번호를 붙이고 각 섬의 가장자리의 좌표를 구한다.
// 2. 각 섬의 끝 좌표에서 BFS를 통해 먼저 닿는 섬까지의 거리를 구한다.
// 3. 모두 구해서, 최소값을 찾는다. (중간에 min값보다 거리가 커지면 바로 탈출하는 등 최적화를 했다.)

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] x_move = {1, 0, -1, 0};
    static int[] y_move = {0, -1, 0, 1};

    static int min = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Pos>> list;

    static class Pos {
        int y, x;
        int dist;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }

        Pos(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

    public static int solution() {
        // 섬 분류
        numbering();

        visited = new boolean[N][N];

        for (int i = 0; i < list.size(); i++) {
            for (Pos p1 : list.get(i)) {
                bfs(i + 1, p1);
            }
        }

        return min;
    }

    public static void bfs(int island_num, Pos p1) {

        visited = new boolean[N][N];

        Queue<Pos> queue = new LinkedList<>();

        visited[p1.y][p1.x] = true;
        queue.offer(new Pos(p1.y, p1.x, 0));

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            if (cur.dist > min) return;

            for (int i = 0; i < 4; i++) {
                Pos next = new Pos(cur.y + y_move[i], cur.x + x_move[i], cur.dist + 1);

                if (0 > next.x || next.x >= N || 0 > next.y || next.y >= N) continue;
                if (visited[next.y][next.x] || map[next.y][next.x] == island_num) continue;
                if (map[next.y][next.x] > 0) {
                    min = cur.dist;
                    return;
                }

                visited[next.y][next.x] = true;
                queue.offer(next);
            }
        }
    }

    public static void numbering() {
        list = new ArrayList<>();
        Queue<Pos> queue = new LinkedList<>();
        int idx = 0;
        int island_num = 1;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (visited[y][x] || map[y][x] != 1) continue;

                list.add(new ArrayList<>());
                visited[y][x] = true;
                queue.offer(new Pos(y, x));

                while (!queue.isEmpty()) {
                    Pos cur = queue.poll();
                    map[cur.y][cur.x] = island_num;

                    boolean inserted = false;
                    for (int i = 0; i < 4; i++) {
                        Pos next = new Pos(cur.y + y_move[i], cur.x + x_move[i]);

                        if (0 > next.x || next.x >= N || 0 > next.y || next.y >= N) continue;
                        if (visited[next.y][next.x]) continue;
                        if (!inserted && map[next.y][next.x] == 0) {
                            list.get(idx).add(new Pos(cur.y, cur.x));
                            inserted = true;
                            continue;
                        }

                        if (map[next.y][next.x] == 1) {
                            visited[next.y][next.x] = true;
                            queue.offer(next);
                        }
                    }
                }
                idx++;
                island_num++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int k = 0; k < N; k++) {
                map[i][k] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }
}
