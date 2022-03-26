// 투 포인터 문제

// 합보다 작으면 끝 인덱스 증가 (e++), 크면 시작 인덱스 증가 (s++) 이다.
// 계산을 빠르게 하기 위해 인덱스 변경마다 누적값을 계산한다.


import java.io.*;
import java.util.*;

public class Main {
    static int[] list;

    public static long solution(int N, int S) {
        long answer = Long.MAX_VALUE;

        if (S == 0) return 1;

        int s = 0;
        int e = 0;
        long sum = list[s];
        boolean find = false;

        while (s <= e && e < N) {
            if (sum < S) {
                e++;
                if (e < N) sum += list[e];
            }
            else if (sum >= S) {
                find = true;
                answer = Math.min(answer, e - s + 1);
                sum -= list[s++];
            }
        }

        return find ? answer : 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        list = new int[N];

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) list[i] = Integer.parseInt(st.nextToken());

        System.out.println(solution(N, S));
    }
}
