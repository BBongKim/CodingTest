// 두 수합의 투포인터에서 포인터를 옮기는 기준을 0으로한다.
// 답은 두 수의 합의 절댓값이 가장 작은 수이다.

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) list[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(list);

        int start = 0;
        int end = N - 1;
        int ans = Integer.MAX_VALUE;
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;

        while (start < end) {
            int sum = list[start] + list[end];

            if (Math.abs(sum) < Math.abs(ans)) {
                ans = sum;
                a = Math.min(list[start], list[end]);
                b = Math.max(list[start], list[end]);
            }

            if (sum > 0) end--;
            else if (sum < 0) start++;
            else {
                start++;
                end--;
            }
        }

        System.out.println(a + " " + b);
    }
}
