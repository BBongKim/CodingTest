import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] map;

    public static void star(int N, int y, int x) {
        if (N == 0) return;
        if (N == 3) {
            for (int i = y; i < y + 3; i++) {
                for (int k = x; k < x + 3; k++) {
                    if (i == y + 1 && k == x + 1) {
                        map[i][k] = ' ';
                        continue;
                    }
                    map[i][k] = '*';
                }
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (i == 1 && k == 1) {
                    starEmpty(N / 3, N / 3 * i + y, N / 3 * k + x);
                    continue;
                }
                star(N / 3, N / 3 * i + y, N / 3 * k + x);
            }
        }
    }

    public static void starEmpty(int N, int y, int x) {
        for (int i = y; i < y + N; i++) {
            for (int k = x; k < x + N; k++) {
                map[i][k] = ' ';
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        star(N, 0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
