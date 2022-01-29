/* 상하좌우 규칙만 찾으면 된다. 하다가 그냥 접근법 참고함 */
import java.util.Arrays;

class Solution {
    public static int[] solution(String[][] places) {
        int[] answer = new int[5];
        Arrays.fill(answer, 1);
        int[] move_x = {1, 0, -1, 0};
        int[] move_y = {0, -1, 0, 1};
        char[][] place = new char[5][5];

        for(int y = 0; y < 5; y++) {
            int idx_y = 0;
            for(int x = 0; x < 5; x++) {
                int idx_x = 0;
                for(char c : places[y][x].toCharArray()) {
                    place[idx_y][idx_x++] = c;
                }
                idx_y++;
            }

            for(int i = 0; i < 5; i++) {
                for(int k = 0; k < 5; k++) {
                    if(place[i][k] == 'P') {
                        for(int m = 0; m < 4; m++) {
                            int next_x = k + move_x[m];
                            int next_y = i + move_y[m];
                            if(0 <= next_x && next_x < 5 && 0 <= next_y && next_y < 5
                                    && place[next_y][next_x] == 'P') {
                                answer[y] = 0;
                                break;
                            }
                        }
                    } else if (place[i][k] == 'O') {
                        int cnt = 0;
                        for(int m = 0; m < 4; m++) {
                            int next_x = k + move_x[m];
                            int next_y = i + move_y[m];
                            if(0 <= next_x && next_x < 5 && 0 <= next_y && next_y < 5
                                    && place[next_y][next_x] == 'P') cnt++;
                            if(cnt > 1) {
                                answer[y] = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        for(int i : solution(places)) {
            System.out.println(i);
        }
    }
}