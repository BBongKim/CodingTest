// 두 수의 합 투 포인터
// 정렬 필수
// X 보다 작다 -> start++
// X 보다 크다 -> end--
// X다 -> start++, end-- 동시에

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N];
        int cnt = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) list[i] = Integer.parseInt(st.nextToken());

        int X = Integer.parseInt(br.readLine());

        Arrays.sort(list);

        int start = 0;
        int end = N - 1;

        while (start < end) {
            if (list[start] + list[end] == X) {
                cnt++;
                end--;
                start++;
            } else if (list[start] + list[end] > X) end--;
            else if (list[start] + list[end] < X) start++;
        }

        System.out.println(cnt);
    }
}
