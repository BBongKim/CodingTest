import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int fibonacci(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;

        return fibonacci(N - 1) + fibonacci(N - 2);
    }

    public static void main(String[] args) throws IOException {
        int result = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        result = fibonacci(N);

        System.out.println(result);
    }
}
