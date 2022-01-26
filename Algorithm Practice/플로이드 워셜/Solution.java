import java.util.Arrays;

public class Solution {

    public static int[][] solution(int n, int m, int[][] graph) {
        int[][] answer = new int[n][n];

        int[][] distance = new int[n + 1][n + 1];

        for(int[] d : distance) {
            Arrays.fill(d, (int) 1e9);
        }

        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= n; k++) {
                if (i == k) distance[i][k] = 0;
            }
        }

        for(int[] gra : graph) {
            distance[gra[0]][gra[1]] = gra[2];
        }

        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= n; k++) {
                for (int q = 1; q <= n; q++) {
                    distance[k][q] = Math.min(distance[k][q], distance[k][i] + distance[i][q]);
                }
            }
        }

        // 사이즈 맞게 변환
        int idx1 = 0;
        for (int i = 1; i <= n; i++) {
            int idx2 = 0;
            for (int k = 1; k <= n; k++) {
                answer[idx1][idx2++] = distance[i][k];
            }
            idx1++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 4;
        int m = 7;
        int[][] graph = {{1, 2, 4}, {1, 4, 6}, {2, 1, 3}, {2, 3, 7}, {3, 1, 5}, {3, 4, 4}, {4, 3, 2}};

        int[][] answer = solution(n, m, graph);

        for(int[] ans : answer) {
            for(int a : ans) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }
}
