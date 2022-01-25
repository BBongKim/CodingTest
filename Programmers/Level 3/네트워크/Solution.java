import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static class Edge {
        int cur, next;

        Edge(int cur, int next) {
            this.cur = cur;
            this.next = next;
        }
    }

    public static int solution(int n, int[][] computers) {
        int answer = 0;
        int X = n;
        int Y = computers.length;
        boolean[][] visited = new boolean[Y][X];
        Queue<Edge> q = new LinkedList<>();

        for (int cur = 0; cur < Y; cur++) {
            for (int next = 0; next < X; next++) {
                if (computers[cur][next] == 0 || visited[cur][next]) continue;

                answer++;
                q.add(new Edge(cur, next));
                visited[cur][next] = true;
                visited[next][cur] = true;

                while (!q.isEmpty()) {
                    Edge e = q.poll();

                    for (int next2 = 0; next2 < X; next2++) {
                        if (!visited[e.next][next2] && computers[e.next][next2] == 1) {
                            q.add(new Edge(e.next, next2));
                            visited[e.next][next2] = true;
                            visited[next2][e.next] = true;
                        }
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution(n, computers));
    }
}