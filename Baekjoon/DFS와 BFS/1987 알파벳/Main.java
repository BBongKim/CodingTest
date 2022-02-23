// 처음에 BFS로 풀었는데 테스트 케이스 몇개에서 메모리 초과 남
// 결국 풀이보고 DFS로 접근

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int answer = 1;
    static int R, C;
    static int[] x_move = {1, 0, -1, 0};
    static int[] y_move = {0, -1, 0, 1};
    static boolean[] alpha = new boolean[26];

    public static void dfs(int y, int x, int dist, char[][] map) {

        answer = Math.max(dist, answer);

        for (int i = 0; i < 4; i++) {

            int next_y = y + y_move[i];
            int next_x = x + x_move[i];

            if (0 <= next_y && next_y < R && 0 <= next_x && next_x < C && !alpha[map[next_y][next_x] - 'A']) {
                alpha[map[next_y][next_x] - 'A'] = true;
                dfs(next_y, next_x, dist + 1, map);
                alpha[map[next_y][next_x] - 'A'] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int k = 0; k < C; k++) {
                map[i][k] = s.charAt(k);
            }
        }
        alpha[map[0][0] - 'A'] = true;
        dfs(0, 0, 1, map);
        System.out.println(answer);
    }
}
