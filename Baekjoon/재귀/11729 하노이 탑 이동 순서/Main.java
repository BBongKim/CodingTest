// String Builder를 사용해야 출력속도가 빠름
// N - 1 번째 원판을 via를 거쳐 start에서 to로 이동

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void hanoi(int N, int start ,int to, int via) {
        if (N == 1) {
            sb.append(start).append(" ").append(to).append("\n");
            return;
        }

        hanoi(N - 1, start, via, to);
        sb.append(start).append(" ").append(to).append("\n");
        hanoi(N - 1, via, to, start);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int start = 1;
        int via = 2;
        int to = 3;

        System.out.println((int)Math.pow(2, N) - 1);

        hanoi(N, start, to, via);
        System.out.print(sb);
    }
}
