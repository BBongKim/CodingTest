// 구현 문제
// 한 줄에 경사로를 여러번 놓을 수 있는 경우를 조심하면 된다.

import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] map;
    static boolean[][] visited;

    public static int solution() {
        int answer = 0;

        // 가로
        for (int i = 0; i < N; i++) {
            int cur = map[i][0];
            boolean canGo = true;

            for (int k = 1; k < N; k++) {
                int next = map[i][k];
                if (cur == next) continue;

                if (Math.abs(next - cur) > 1) {
                    canGo = false;
                    break;
                }

                // 경사로 체크
                if (next > cur) {
                    if (!isPossibleRow(i, k - L, k - 1)) {
                        canGo = false;
                        //reset
                        Arrays.fill(visited[i], false);
                        break;
                    }
                    cur = next;
                } else {
                    if (!isPossibleRow(i, k, k + L - 1)) {
                        canGo = false;
                        //reset
                        Arrays.fill(visited[i], false);
                        break;
                    }
                    cur = next;
                }
            }

            if (canGo) answer++;
        }

        visited = new boolean[N][N];

        // 세로
        for (int i = 0; i < N; i++) {
            int cur = map[0][i];
            boolean canGo = true;

            for (int k = 1; k < N; k++) {
                int next = map[k][i];
                if (cur == next) continue;

                if (Math.abs(next - cur) > 1) {
                    canGo = false;
                    break;
                }

                // 경사로 체크
                if (next > cur) {
                    if (!isPossibleCol(i, k - L, k - 1)) {
                        canGo = false;
                        resetCol(i);
                        break;
                    }
                    cur = next;
                } else {
                    if (!isPossibleCol(i, k, k + L - 1)) {
                        canGo = false;
                        resetCol(i);
                        break;
                    }
                    cur = next;
                }
            }

            if (canGo) answer++;
        }

        return answer;
    }

    public static void resetCol(int idx) {
        for (int i = 0; i < N; i++) {
            visited[i][idx] = false;
        }
    }

    public static boolean isPossibleRow(int idx, int s, int e) {
        if (0 > s || s >= N || 0 > e || e >= N || visited[idx][s]) return false;

        int value = map[idx][s];
        visited[idx][s] = true;

        for (int i = s + 1; i <= e; i++) {
            if (visited[idx][i] || map[idx][i] != value) return false;

            visited[idx][i] = true;
        }

        return true;
    }

    public static boolean isPossibleCol(int idx, int s, int e) {
        if (0 > s || s >= N || 0 > e || e >= N || visited[s][idx]) return false;

        int value = map[s][idx];
        visited[s][idx] = true;

        for (int i = s + 1; i <= e; i++) {
            if (visited[i][idx] || map[i][idx] != value) return false;

            visited[i][idx] = true;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0 ; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int k = 0; k < N; k++) {
                map[i][k] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }
}
