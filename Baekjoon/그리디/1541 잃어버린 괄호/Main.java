// 그리디하게 덧셈을 먼저 처리한 후, 뺄셈을 처리하면 된다.
// + 이거보다 간단하게 하는법은 - 을 기준으로 String을 먼저 나눈 후, 나눠진 수들을 모두 더하고 그 수들을 모두 빼면된다...

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    public static int solution(String s) {
        int answer = 0;

        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '+') {
                int k, q;
                for (k = i - 1; k >= 0; k--) if (sb.charAt(k) == '-' || sb.charAt(k) == '+') break;
                for (q = i + 1; q < sb.length(); q++) if (sb.charAt(q) == '-' || sb.charAt(q) == '+') break;

                int tmp = Integer.parseInt(sb.substring(k + 1, i)) + Integer.parseInt(sb.substring(i + 1, q));
                sb.replace(k + 1, q, Integer.toString(tmp));
                i = 0;
            }
        }

        int[] arr = Stream.of(sb.toString().replaceAll("[-]", " ").split(" ")).mapToInt(Integer::parseInt).toArray();

        answer = arr[0];
        for (int i = 1; i < arr.length; i++) {
            answer -= arr[i];
        }

        return answer;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        System.out.println(solution(s));
    }
}
