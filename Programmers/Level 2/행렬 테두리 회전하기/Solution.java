class Solution {
    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] map = new int[rows][columns];
        int n = 1;

        //init
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < columns; k++) {
                map[i][k] = n++;
            }
        }

        int idx = 0;

        for (int[] q : queries) {
            int y1 = q[0];
            int x1 = q[1];
            int y2 = q[2];
            int x2 = q[3];
            int cur_y = y1;
            int cur_x = x1;

            int tmp = map[cur_y - 1][cur_x - 1];
            int min = tmp;
            cur_x++;

            while (cur_y != y1 || cur_x != x1) {
                int tmp2 = map[cur_y - 1][cur_x - 1];
                min = Math.min(min, tmp2);
                map[cur_y - 1][cur_x - 1] = tmp;
                tmp = tmp2;

                if (cur_x < x2 && cur_y == y1) cur_x++;
                else if (cur_y < y2 && cur_x == x2) cur_y++;
                else if (cur_x > x1 && cur_y == y2) cur_x--;
                else if (cur_y > y1 && cur_x == x1) cur_y--;
            }

            map[cur_y - 1][cur_x - 1] = tmp;
            answer[idx++] = min;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};

        for(int i : solution(6, 6, queries)) {
            System.out.println(i);
        }
    }
}