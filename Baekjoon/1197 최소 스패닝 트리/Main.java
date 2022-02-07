import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Edge> list;
    static int[] parent;

    static class Edge implements Comparable<Edge> {
        int start, end, dist;

        Edge(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist < o.dist ? -1 : 1;
        }
    }

    public static void union(int a, int b) {
        int p1 = find(a);
        int p2 = find(b);

        // p2 p1...
        if (p1 < p2) parent[p2] = p1;
        else parent[p1] = p2;
    }

    public static int find(int a) {
        if (parent[a] != a) parent[a] = find(parent[a]);

        return parent[a];
    }

    public static int solution() {
        int answer = 0;

        for(Edge e : list) {
            if (find(e.start) == find(e.end)) continue;

            union(e.start, e.end);
            answer += e.dist;
        }

        return answer;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);

        list = new ArrayList<>();

        int V, E;
        StringTokenizer input = new StringTokenizer(sc.nextLine(), " ");
        V = Integer.parseInt(input.nextToken());
        E = Integer.parseInt(input.nextToken());

        parent = new int[V + 1];

        for(int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }

        for(int i = 0 ; i < E; i++) {
            StringTokenizer input2 = new StringTokenizer(sc.nextLine(), " ");

            int s = Integer.parseInt(input2.nextToken());
            int e = Integer.parseInt(input2.nextToken());
            int d = Integer.parseInt(input2.nextToken());

            list.add(new Edge(s, e, d));
        }

        Collections.sort(list);

        System.out.println(solution());
    }
}
