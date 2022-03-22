// 구현 문제
// 딱히 특정한 알고리즘을 필요로 하는 문제는 아니다.
// 테스트 케이스 통과를 모두 했는데, 시작하자마자 계속 틀려서 문제를 다시 읽어보니 함정(?)이 있다.
// (r, c)에서 r은 북쪽으로 떨어진, x는 서쪽으로 떨어진 좌표라 했으므로
// 입력으로 주어지는 (x, y) 에서 x는 2차원 배열의 x 좌표가 아닌 y 좌표가 되어야한다. (반대로, y는 x가 된다.)

// 테스트 케이스는 x y가 모두 같은 경우만 주어졌기 때문에, 문제 없이 작동했던 것이다.

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] cmd;

    public static void solution(int x, int y) {
        int[][] dice = new int[4][3];

        for (int c : cmd) {
            if (c == 1) {
                x++;
                if (0 > x || x >= M || 0 > y || y >= N) {
                    x--;
                    continue;
                }

                int right = dice[1][2];
                int bottom = dice[3][1];

                dice[1][2] = dice[1][1];
                dice[1][1] = dice[1][0];

                dice[1][0] = bottom;
                dice[3][1] = right;
            } else if (c == 2) {
                x--;
                if (0 > x || x >= M || 0 > y || y >= N) {
                    x++;
                    continue;
                }

                int left = dice[1][0];
                int bottom = dice[3][1];

                dice[1][0] = dice[1][1];
                dice[1][1] = dice[1][2];

                dice[1][2] = bottom;
                dice[3][1] = left;
            } else if (c == 3) {
                y--;
                if (0 > x || x >= M || 0 > y || y >= N) {
                    y++;
                    continue;
                }
                int tmp = dice[0][1];
                for (int i = 1; i <= 3; i++) {
                    dice[i - 1][1] = dice[i][1];
                }
                dice[3][1] = tmp;
            } else if (c == 4) {
                y++;
                if (0 > x || x >= M || 0 > y || y >= N) {
                    y--;
                    continue;
                }
                int tmp = dice[3][1];
                for (int i = 2; i >= 0; i--) {
                    dice[i + 1][1] = dice[i][1];
                }
                dice[0][1] = tmp;
            }

            if (map[y][x] == 0) {
                map[y][x] = dice[3][1];
            } else {
                dice[3][1] = map[y][x];
                map[y][x] = 0;
            }

            System.out.println(dice[1][1]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cmd = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int k = 0; k < M; k++) {
                map[i][k] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < K; i++) cmd[i] = Integer.parseInt(st.nextToken());

        solution(y, x);
    }
}
