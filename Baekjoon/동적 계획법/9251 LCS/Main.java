// 기본 LCS(subsequence) 연습
// Substring일 경우 둘이 다를 때 그냥 0값을 넣으면 된다.

import java.util.*;
import java.io.*;

public class Main {

    public static int solution(String a, String b) {
        int answer = -1;
        int[][] map = new int[a.length() + 1][b.length() + 1];

        for (int i = 1; i < a.length() + 1; i++) {
            for (int k = 1; k < b.length() + 1; k++) {
                if (a.charAt(i - 1) == b.charAt(k - 1)) map[i][k] = map[i - 1][k - 1] + 1;
                else map[i][k] = Math.max(map[i - 1][k], map[i][k - 1]);
            }
        }

        for (int i = 0; i < map.length; i++) {
            answer = Math.max(answer, Arrays.stream(map[i]).max().getAsInt());
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();

        System.out.println(solution(a, b));
    }
}
