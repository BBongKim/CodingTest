// 시뮬레이션 문제
// 로직 자체는 브루트포스하게 모든 경우의 수를 다 구한다.
// 따라서, DFS를 통해 탐색한다.
// 처음에 구현하는데 코드가 너무 길어지는거 같아 효율적으로 짜보고 싶어서 생각하다보니 오래걸림..
// 그렇다고 짧은 코드가 된것도 아님 ㅋㅋㅋ
// 방향 설정하는데 있어 작은 실수가 있었는데, 찾느라 시간좀 썼다 ㅠㅠ


import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static int N, M;
    static boolean[][] visited;
    static ArrayList<Pos> cctv = new ArrayList<>();
    static Stack<Pos> list = new Stack<>();
    static Stack<Integer> counter = new Stack<>();

    static class Pos {
        int type, y, x;

        Pos(int type, int y, int x) {
            this.type = type;
            this.y = y;
            this.x = x;
        }
    }

    public static void go(int degree, int cur_y, int cur_x) {
        int cnt = 0;
        visited[cur_y][cur_x] = true;

        if (degree == 0) {
            for (int x = cur_x + 1; x < M; x++) {
                if (map[cur_y][x] == 6) break;
                if (visited[cur_y][x] || map[cur_y][x] != 0) continue;

                map[cur_y][x] = -1;
                visited[cur_y][x] = true;
                list.push(new Pos(map[cur_y][x], cur_y, x));
                cnt++;
            }
        } else if (degree == 1) {
            for (int y = cur_y + 1; y < N; y++) {
                if (map[y][cur_x] == 6) break;
                if (visited[y][cur_x] || map[y][cur_x] != 0) continue;

                map[y][cur_x] = -1;
                visited[y][cur_x] = true;
                list.push(new Pos(map[y][cur_x], y, cur_x));
                cnt++;
            }
        } else if (degree == 2) {
            for (int x = cur_x - 1; x >= 0; x--) {
                if (map[cur_y][x] == 6) break;
                if (visited[cur_y][x] || map[cur_y][x] != 0) continue;

                map[cur_y][x] = -1;
                visited[cur_y][x] = true;
                list.push(new Pos(map[cur_y][x], cur_y, x));
                cnt++;
            }
        } else if (degree == 3) {
            for (int y = cur_y - 1; y >= 0; y--) {
                if (map[y][cur_x] == 6) break;
                if (visited[y][cur_x] || map[y][cur_x] != 0) continue;

                map[y][cur_x] = -1;
                visited[y][cur_x] = true;
                list.push(new Pos(map[y][cur_x], y, cur_x));
                cnt++;
            }
        }

        counter.push(cnt);
    }

    public static void erase() {
        int cnt = counter.pop();

        for (int i = 0; i < cnt; i++) {
            Pos cur = list.pop();

            map[cur.y][cur.x] = 0;
            visited[cur.y][cur.x] = false;
        }
    }

    public static void dfs(int idx) {
        if (idx == cctv.size()) {
            int cnt = 0;

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (map[y][x] == 0) cnt++;
                }
            }

            answer = Math.min(answer, cnt);
            return;
        }

        Pos cur = cctv.get(idx);

        if (cur.type == 1) {
            for (int i = 0; i < 4; i++) {
                go(i, cur.y, cur.x);
                dfs(idx + 1);
                visited[cur.y][cur.x] = false;
                erase();
            }
        } else if (cur.type == 2) {
            for (int i = 0; i < 2; i++) {
                go(i, cur.y, cur.x);
                go(i + 2, cur.y, cur.x);
                dfs(idx + 1);
                visited[cur.y][cur.x] = false;
                erase();
                erase();
            }
        } else if (cur.type == 3) {
            for (int i = 0; i < 4; i++) {
                go(i, cur.y, cur.x);
                go((i + 1) % 4, cur.y, cur.x);
                dfs(idx + 1);
                visited[cur.y][cur.x] = false;
                erase();
                erase();
            }
        } else if (cur.type == 4) {
            for (int i = 0; i < 4; i++) {
                go(i == 0 ? 3 : i - 1, cur.y, cur.x);
                go(i, cur.y, cur.x);
                go((i + 1) % 4, cur.y, cur.x);
                dfs(idx + 1);
                visited[cur.y][cur.x] = false;
                erase();
                erase();
                erase();
            }
        } else if (cur.type == 5) {
            go(0, cur.y, cur.x);
            go(1, cur.y, cur.x);
            go(2, cur.y, cur.x);
            go(3, cur.y, cur.x);
            dfs(idx + 1);
            visited[cur.y][cur.x] = false;
            erase();
            erase();
            erase();
            erase();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int k = 0; k < M; k++) {
                map[i][k] = Integer.parseInt(st.nextToken());

                if (0 < map[i][k] && map[i][k] < 6) cctv.add(new Pos(map[i][k], i, k));
            }
        }
        dfs(0);

        System.out.println(answer);
    }
}