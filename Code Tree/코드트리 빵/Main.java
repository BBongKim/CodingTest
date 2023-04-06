// 코드트리 빵
// 시뮬레이션, BFS


// 전체적인 문제는 하라는대로 구현하면 된다.
// 최단 거리를 구할 때마다, BFS를 돌려주었다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Pos {
	int y;
	int x;
	int cnt = 0;

	Pos(int y, int x) {
		this.y = y;
		this.x = x;
	}

	Pos(int y, int x, int cnt) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}
}

public class Main {

	static int N;
	static int M;
	static boolean[][] blocked;

	static int[] moveX = { 0, -1, 1, 0 };
	static int[] moveY = { -1, 0, 0, 1 };

	public static int bfs(Pos start, Pos target) {
		boolean[][] visited = new boolean[N][N];
		ArrayDeque<Pos> queue = new ArrayDeque();

		visited[start.y][start.x] = true;
		queue.addLast(new Pos(start.y, start.x, 0));

		while (!queue.isEmpty()) {
			Pos cur = queue.removeFirst();

			if (cur.y == target.y && cur.x == target.x)
				return cur.cnt;

			for (int i = 0; i < 4; i++) {
				int nextY = cur.y + moveY[i];
				int nextX = cur.x + moveX[i];

				if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N)
					continue;
				if (blocked[nextY][nextX])
					continue;
				if (visited[nextY][nextX])
					continue;

				visited[nextY][nextX] = true;
				queue.addLast(new Pos(nextY, nextX, cur.cnt + 1));
			}
		}

		return Integer.MAX_VALUE;
	}

	public static void move(Pos person, Pos store) {
		int min = Integer.MAX_VALUE;
		int minNextY = Integer.MAX_VALUE;
		int minNextX = Integer.MAX_VALUE;

		for (int i = 0; i < 4; i++) {
			int nextY = person.y + moveY[i];
			int nextX = person.x + moveX[i];

			if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N)
				continue;
			if (blocked[nextY][nextX])
				continue;

			int cnt = bfs(new Pos(nextY, nextX), store);

			if (cnt < min) {
				min = cnt;
				minNextY = nextY;
				minNextX = nextX;
			}
		}

		person.y = minNextY;
		person.x = minNextX;

		if (minNextY == store.y && minNextX == store.x)
			blocked[minNextY][minNextX] = true;
	}

	public static void add(Pos person, Pos store, List<Pos> camps) {
		int min = Integer.MAX_VALUE;
		Pos minCamp = null;

		for (Pos camp : camps) {
			if (blocked[camp.y][camp.x])
				continue;

			int cnt = bfs(camp, store);

			if (cnt < min) {
				min = cnt;
				minCamp = camp;
			}
		}

		person.y = minCamp.y;
		person.x = minCamp.x;
		blocked[minCamp.y][minCamp.x] = true;

	}

	public static boolean isDone(Pos[] stores) {
		for (Pos store : stores) {
			if (!blocked[store.y][store.x])
				return false;
		}
		return true;
	}

	public static int solution(int N, int M, int[][] map, Pos[] stores, ArrayList<Pos> camps) {
		blocked = new boolean[N][N];
		Pos[] people = new Pos[M];
		int t = 1;

		while (!isDone(stores)) {

			// move & check
			for (int i = 0; i < M; i++) {
				if (blocked[stores[i].y][stores[i].x])
					continue;
				if (people[i] == null)
					continue;
				move(people[i], stores[i]);
			}

			// add
			if (t <= M) {
				people[t - 1] = new Pos(0, 0);
				add(people[t - 1], stores[t - 1], camps);
			}

			t++;
		}

		return t - 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		Pos[] stores = new Pos[M];
		ArrayList<Pos> camps = new ArrayList();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1)
					camps.add(new Pos(i, j));
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			stores[i] = new Pos(y - 1, x - 1);
		}

		System.out.println(solution(N, M, map, stores, camps));
	}

}
