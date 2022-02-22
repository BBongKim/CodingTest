import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    static HashMap<Integer, Long> map = new HashMap<>();

    public static long padovan(int N) {
        if (N == 1 || N == 2 || N == 3) return 1;
        else if (N == 4 || N == 5) return 2;

        if (!map.containsKey(N)) map.put(N, padovan(N - 1) + padovan(N - 5));

        return map.get(N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(padovan(N));
        }
    }
}
