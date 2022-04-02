// 소수 구하기 + 투포인터 문제
// 그냥 소수를 구하면 실행시간이 간당간당하다.

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> list = new ArrayList<>();

    public static int solution(int n) {
        if (n == 1) return 0;

        int answer = 0;

        if (isPrime(n)) answer++;

        // 소수 구하기
        for (int i = 1; i < n; i++) {
            if (isPrime(i)) list.add(i);
        }

        if (list.size() < 2) return answer;

        int s = 0;
        int e = 1;
        int sum = list.get(s) + list.get(e);

        while (s < e) {
            if (sum < n) {
                if (e + 1 < list.size()) sum += list.get(++e);
            } else if (sum > n) {
                sum -= list.get(s++);
            } else {
                sum -= list.get(s++);
                if (e + 1 < list.size()) sum += list.get(++e);
                answer++;
            }
        }
        return answer;
    }

    public static boolean isPrime(int n) {
        if (n == 1) return false;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println(solution(N));
    }
}
