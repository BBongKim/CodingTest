// 에라토스테네스의 체를 사용하면 실행시간이 엄청 빨라진다.
// 투 포인터 코드도 수정

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> list = new ArrayList<>();
    static boolean[] isPrime;

    public static int solution(int n) {
        if (n == 1) return 0;

        int answer = 0;

        // 소수 구하기
        getPrime(n);
        if (isPrime[n]) answer++;

        if (list.size() < 2) return answer;

        int s = 0;
        int e = 0;
        int sum = 0;
        int size = list.size();

        while (s < size) {
            if (sum > n || e == size) {
                sum -= list.get(s++);
            } else {
                sum += list.get(e++);
            }

            if (sum == n) answer++;
        }
        return answer;
    }

    public static void getPrime(int n) {
        isPrime = new boolean[n + 1];

        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int k = i * i; k <= n; k += i) isPrime[k] = false;
            }
        }

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) list.add(i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println(solution(N));
    }
}
