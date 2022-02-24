// 메모리 초과 떠서 결국 풀이 봄..
// 전칸 밟고 오는 경우(전전칸 밟으면 안됨) VS 전전칸 밟고 오는 경우 중 최댓값을 저장하면된다.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int solution(int N, int[] stairs) {

        if (N == 1) return stairs[1];

        int[] sum = new int[N + 1];

        sum[1] = stairs[1];
        sum[2] = Math.max(stairs[2], stairs[2] + sum[1]);

        for (int i = 3; i < N + 1; i++) {
            int sum_p = sum[i - 3] + stairs[i - 1] + stairs[i];
            int sum_pp = sum[i - 2] + stairs[i];

            sum[i] = Math.max(sum_p, sum_pp);
        }


        return sum[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] stairs = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution(N, stairs));
    }
}