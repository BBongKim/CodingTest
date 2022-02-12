// 옛날에 못풀었었는데 다시 품
// 처음에 while로 풀었는데 시간초과나서 다른 분 참고했다.
// 사용할 쿠폰 - 받을 쿠폰 = 실제 쓴 쿠폰임을 아는게 중요하다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int solution(int P, int M, int F, int C) {

        int mari = M / P;
        int coupon_do = C * mari;
        int coupon_sang = coupon_do;

        int chuga_do = coupon_do / F;
        int chuga_sang = chuga_do;

        if (coupon_sang > F) chuga_sang = (coupon_sang - F) / (F - C) + 1;

        return chuga_sang - chuga_do;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()) {
                int P = Integer.parseInt(st.nextToken());
                int M = Integer.parseInt(st.nextToken());
                int F = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                sb.append(solution(P, M, F, C)).append("\n");
            }
        }
        System.out.println(sb);
    }
}
