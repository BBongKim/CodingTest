// 인출 시간이 짧은 사람 먼저 인출하면 된다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) list[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(list);

        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int k = 0; k <= i; k++) {
                sum += list[k];
            }
        }

        System.out.println(sum);

    }
}
