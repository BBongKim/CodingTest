import java.util.LinkedList;
import java.util.Queue;

class Pos {
    int x;
    int y;

    Pos(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Solution {

    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        Queue<Pos> queue = new LinkedList<>();

        int[] d_x = new int[] {1, 0, -1, 0};
        int[] d_y = new int[] {0, -1, 0, 1};

        boolean[][] visited = new boolean[picture.length][picture[0].length];

        for (int y = 0; y < picture.length; y++) {
            for (int x = 0; x < picture[0].length; x++) {
                if (visited[y][x] || picture[y][x] == 0) continue;

                numberOfArea++;

                //Initial
                int cnt = 0;
                queue.offer(new Pos(y, x));
                visited[y][x] = true;

                while(!queue.isEmpty()) {
                    Pos cur = queue.poll();
                    cnt++;

                    for (int i = 0; i < 4; i++) {
                        Pos next = new Pos(cur.y + d_y[i], cur.x + d_x[i]);

                        if(0 <= next.y && next.y < m && 0 <= next.x && next.x < n &&
                                !visited[next.y][next.x] && picture[next.y][next.x] == picture[cur.y][cur.x] && picture[next.y][next.x] != 0) {
                            queue.offer(next);
                            visited[next.y][next.x] = true;
                        }
                    }
                }

                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
            }
        }


        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public static void main(String[] args) {

        int[][] pic = new int[][] {{1, 1, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        int[] ans;
        ans = solution(6, 4, pic);

        for(int a : ans) {
            System.out.println(a);
        }
    }
}