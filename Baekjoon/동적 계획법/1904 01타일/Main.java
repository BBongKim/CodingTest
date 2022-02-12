// 피보나치와 거의 동일하다.
// memory값에 15746의 나머지 값으로 넣어야 overflow가 발생 안한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] memory;

    public static int fibo(int n) {
        if(memory[n] != 0) return memory[n];

        if (n == 1) return 1;
        if (n == 2) return 2;

        memory[n] = (fibo(n-1) + fibo(n-2)) % 15746;
        return memory[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        memory = new int[N + 1];

        System.out.println(fibo(N));
    }
}
