// 풀다가 풀이법 봄
// 무조건 작은 수만 그리디하게 선택하면 함정에 빠진다.
// 따라서 이전에 R G B를 모두 선택한 경우를 구해야됨.

// i > 0 일 때, 현재 집까지의 최소 비용은 자기 자신의 색을 제외한 나머지 2개의 이전 집들 중에서 비용이 낮은 쪽과 더한 값이다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] memory;

    public static int solution(int N, int[][] map) {
        memory[0][0] = map[0][0];
        memory[0][1] = map[0][1];
        memory[0][2] = map[0][2];

        for (int i = 1; i < N; i++) {
            memory[i][0] += Math.min(memory[i - 1][1], memory[i - 1][2]) + map[i][0];
            memory[i][1] += Math.min(memory[i - 1][0], memory[i - 1][2]) + map[i][1];
            memory[i][2] += Math.min(memory[i - 1][0], memory[i - 1][1]) + map[i][2];
        }

        return Arrays.stream(memory[N - 1]).min().getAsInt();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        memory = new int[N][3];
        int[][] map = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for (int k = 0; k < 3; k++) {
                map[i][k] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(N, map));
    }
}
