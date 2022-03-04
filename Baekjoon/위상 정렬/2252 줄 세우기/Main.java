import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] degree;
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();

    public static void solution() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < degree.length; i++) {
            if (degree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int n = queue.poll();

            System.out.print(n + " ");

            for (int i : map.get(n)) {
                degree[i]--;
                if (degree[i] == 0) queue.add(i);
            }
        }

        System.out.println();
    }

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        degree = new int[N + 1];
        for (int i = 0; i < N + 1; i++) map.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");

            int n1 = Integer.parseInt(st2.nextToken());
            int n2 = Integer.parseInt(st2.nextToken());

            map.get(n1).add(n2);
            degree[n2]++;
        }

        solution();
    }
}
