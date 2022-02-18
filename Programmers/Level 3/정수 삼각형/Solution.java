// 좌우로 더하고 나온 값중 최댓값을 선정하면 된다.

import java.util.Arrays;

class Solution {
    public static int solution(int[][] triangle) {
        int answer = 0;
        int[][] memory = new int[triangle.length][];

        for (int i = 0; i < triangle.length; i++) {
            memory[i] = new int[triangle[i].length];
        }

        memory[0][0] = triangle[0][0];

        for (int i = 0; i < triangle.length - 1; i++) {
            for (int k = 0; k < triangle[i].length; k++) {
                for (int p = 0; p < 2; p++) {
                    memory[i + 1][k + p] = Math.max(memory[i][k] + triangle[i + 1][k + p], memory[i + 1][k + p]);
                }
            }
        }

        answer = Arrays.stream(memory[memory.length - 1]).max().getAsInt();

        return answer;
    }

    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};

        System.out.println(solution(triangle));
    }
}