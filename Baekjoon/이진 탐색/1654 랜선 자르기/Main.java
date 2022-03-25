// (start + end) 할 때 int 범위 넘을 수 있는거 주의
// mid 값 0 되는 것 주의

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] list;

    public static long solution(int K) {
        long start = 0;
        long end = Arrays.stream(list).max().getAsInt();

        long max = -1;

        while (start <= end) {
            long mid = (start + end) / 2;
            if (mid == 0) mid++;

            long sum = 0;
            for (int l : list) {
                sum += l / mid;
            }

            if (sum >= K) {
                max = Math.max(mid, max);
                start = mid + 1;
            }
            else end = mid - 1;
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        list = new int[N];

        for (int i = 0; i < N; i++) list[i] = Integer.parseInt(br.readLine());

        System.out.println(solution(K));
    }
}
