// 정렬된 배열의 인덱스로 압축하면 된다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N];
        int[] sorted = new int[N];

        HashMap<Integer, Integer> map = new LinkedHashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
            sorted[i] = list[i];
        }

        Arrays.sort(sorted);

        int idx = 0;
        int prev = sorted[0];
        map.put(sorted[0], idx++);

        for (int i = 1; i < N; i++) {
            if (prev != sorted[i]) {
                map.put(sorted[i], idx++);
                prev = sorted[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(map.get(list[i])).append(" ");
        }

        System.out.println(sb);
    }
}
