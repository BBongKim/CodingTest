// 둘레가 하나 늘어날때마다 최소 이동경로가 1씩 늘어난다.
// N이 포함되는 둘레의 이동경로 갯수를 찾으면 된다.

// 다른 풀이는 그냥 벌집개수가 6의 배수로 늘어나는 걸로 풀면됨

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int solution(int N) {
        int answer = 0;

        if (N == 1) return 1;

        int f_mul = 6;
        int e_mul = 12;
        int front = 2;
        int end = 7;
        int cnt = 2;
        boolean find = false;

        //2 ~ 7 -> 2개
        //8 ~ 19 -> 3개
        //20 ~ 37 -> 4개

        while(!find) {
            if (front <= N && N <= end) {
                answer = cnt;
                find = true;
            } else {
                front += f_mul;
                end += e_mul;
                f_mul += 6;
                e_mul += 6;
                cnt++;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(solution(N));
    }
}
