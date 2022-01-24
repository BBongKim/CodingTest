import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static long solution(int[] N, int M) {
        long answer = 0;

        ArrayList<Integer> list = new ArrayList<>();
        for (int n : N) list.add(n);
        Collections.sort(list);

        answer = search(list, M);

        return answer;
    }

    public static long search(ArrayList<Integer> N, int M) {
        int start = 0;
        int end = N.get(N.size() - 1);
        int mid = (start + end) / 2;

        long max = 0;

        while (start <= end) {
            long result = 0;
            for (int n : N) {
                if (n <= mid) continue;
                result += (n - mid);
            }
            if (result >= M) max = Math.max(max, mid);
            if (result > M) start = mid + 1;
            else end = mid - 1;

            mid = (start + end) / 2;
        }
        return max;
    }


    public static void main(String[] args) throws IOException {

        //System.setIn(new FileInputStream("input.txt"));

        // 입력 값 셋팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] Ns = new int[N];
        int idx = 0;

        while (st.hasMoreTokens()) {
            Ns[idx++] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(Ns, M));
    }
}
