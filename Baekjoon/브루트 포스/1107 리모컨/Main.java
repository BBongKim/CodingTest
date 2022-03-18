// 브루트포스 문제
// 단순하게 오로지 +,-로만 이동할 때와,
// abs(N으로부터 감소 또는 증가하여 바로 입력 가능한 채널 - N) + 채널 자리수 중 작은 값을 답으로 한다.

import java.io.*;
import java.util.*;

public class Main {
    public static int solution(int[] list, int N) {
        if (N == 100) return 0;

        int only_plma = Math.abs(N - 100);

        if (list.length == 10) return only_plma;

        int answer;
        int answer_de = Integer.MAX_VALUE;
        int answer_in = Integer.MAX_VALUE;

        // 감소
        int tmp = N;
        while (tmp >= 0) {
            String s = Integer.toString(tmp);
            boolean find = true;
            for (int l : list) {
                if (s.contains(Integer.toString(l))) {
                    find = false;
                    break;
                }
            }

            if (find) break;

            tmp--;
        }

        if (tmp >= 0) answer_de = Integer.toString(tmp).length() + (N - tmp);

        // 증가
        int tmp2 = N;
        while (tmp2 < Integer.MAX_VALUE) {
            answer_in = Integer.toString(tmp2).length() + (tmp2 - N);
            if (answer_in >= answer_de) break;

            String s = Integer.toString(tmp2);
            boolean find = true;
            for (int l : list) {
                if (s.contains(Integer.toString(l))) {
                    find = false;
                    break;
                }
            }

            if (find) break;

            tmp2++;
        }
        answer = Math.min(answer_de, answer_in);

        answer = Math.min(answer, only_plma);

        return answer;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] list = new int[M];

        if (M != 0) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < M; i++) list[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(list, N));
    }
}
