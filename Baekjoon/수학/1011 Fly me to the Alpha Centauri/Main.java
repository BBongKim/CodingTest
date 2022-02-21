// 규칙 찾는게 어려워서, 못풀고 다른분 풀이봄
// 이동 횟수가 1, 2, 3, 3, 4, 4, 5, 5, 5, 6, 6, 6, ... 이런식으로 증가한다고 찾는 것이 중요하다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static long solution(long x, long y) {
        long n = 0;
        long d = y - x;

        while (n * (n + 1) < d) n += 1;

        if (d <= Math.pow(n, 2)) return n * 2 - 1;
        else return n * 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long T = Long.parseLong(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());
            sb.append(solution(x, y)).append("\n");
        }

        System.out.println(sb);
    }
}
