// 백트래킹 문제
// 기본적으로 dfs로 0이 있는 자리에 모든 수를 대입해보는 식으로 진행한다.
// 하지만, 백트래킹을 위해 대입전에 스도쿠의 규칙을 만족하는지 확인을 해주어야 한다.

import java.io.*;
import java.util.*;

public class Main {
    static int[][] map = new int[9][9];
    static ArrayList<Pos> list = new ArrayList<>();
    static boolean find = false;

    static class Pos {
        int y, x;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static boolean isPossible(int y, int x, int n) {

        // 좌우
        for (int i = 0; i < 9; i++) {
            if (map[y][i] == n) return false;
        }

        // 상하
        for (int i = 0; i < 9; i++) {
            if (map[i][x] == n) return false;
        }

        // 3X3
        int s_y = y / 3 * 3;
        int s_x = x / 3 * 3;

        for (int i = s_y; i < s_y + 3; i++) {
            for (int k = s_x; k < s_x + 3; k++) {
                if (map[i][k] == n) return false;
            }
        }

        return true;
    }


    public static void dfs(int idx) {
        if (idx == list.size()) {
            find = true;
            for (int[] m : map) {
                for (int i : m) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }

            return;

            // System.exit(0) 왜 실행시간이 더 늘어날까...?
        }

        for (int i = 1; i < 10; i++) {
            Pos cur = list.get(idx);
            if (!isPossible(cur.y, cur.x, i)) continue;

            map[cur.y][cur.x] = i;
            dfs(idx + 1);
            if (find) return;
            map[cur.y][cur.x] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int k = 0; k < 9; k++) {
                map[i][k] = Integer.parseInt(st.nextToken());
                if (map[i][k] == 0) list.add(new Pos(i, k));
            }
        }

        dfs(0);
    }
}
