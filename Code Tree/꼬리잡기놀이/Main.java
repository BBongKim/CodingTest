// 꼬리잡기놀이

// 시뮬레이션

// 몇가지 케이스 때문에 힘들었다.

// 1. 경로의 모든 길이 사람으로 꽉찬 케이스 처리.
// 2. 29 라운드 이후, round % (4 * N)을 사용시, 0 라운드가 되어버림.
// 그래서, (round - 1) % (4 * N) + 1로 처리.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Pos {
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	int y;
	int x;

	Pos(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class Team {
	Pos head;
	Pos tail;

	Team(Pos head, Pos tail) {
		this.head = head;
		this.tail = tail;
	}
}

public class Main {

	static int score = 0;
	static int N;
	static int M;
	static int K;

	static int number = 0;

	static int[][] map;

	static boolean[][] visited;

	static int[] moveX = { 1, 0, -1, 0 };
	static int[] moveY = { 0, -1, 0, 1 };

	static ArrayList<Pos> heads;
	static ArrayList<Pos> tempHeads;

	static Pos tempTail;
	static Pos tempHead;

	public static boolean isOutSide(int nextY, int nextX) {
		if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N)
			return true;
		else
			return false;
	}

	public static void moveTeams() {
		tempHeads = new ArrayList();

		for (Pos head : heads) {
			for (int i = 0; i < 4; i++) {
				int nextY = head.y + moveY[i];
				int nextX = head.x + moveX[i];

				if (isOutSide(nextY, nextX))
					continue;

				if (map[nextY][nextX] == 4 || map[nextY][nextX] == 3) {
					moveTeam(new Pos(nextY, nextX));
					break;
				}
			}
		}

		heads = tempHeads;
	}

	public static void moveTeam(Pos start) {

		int[][] tempMap = new int[N][N];

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				tempMap[y][x] = map[y][x];
			}
		}

		Pos prev = start;
		Pos cur = start;

		if (map[cur.y][cur.x] == 3) {

			// 2로 옮겨줌.
			for (int i = 0; i < 4; i++) {
				int nextY = start.y + moveY[i];
				int nextX = start.x + moveX[i];

				if (isOutSide(nextY, nextX))
					continue;
				if (map[nextY][nextX] == 0 || map[nextY][nextX] == 4 || map[nextY][nextX] == 1)
					continue;

				cur = new Pos(nextY, nextX);
				prev = cur;
				break;
			}

			while (map[cur.y][cur.x] != 1) {
				for (int i = 0; i < 4; i++) {
					int nextY = cur.y + moveY[i];
					int nextX = cur.x + moveX[i];

					if (isOutSide(nextY, nextX))
						continue;
					if (nextY == prev.y && nextX == prev.x)
						continue;
					if (map[nextY][nextX] == 0 || map[nextY][nextX] == 4 || map[nextY][nextX] == 2)
						continue;

					// 2 or 3
					if (map[nextY][nextX] == 1)
						tempHeads.add(new Pos(cur.y, cur.x));

					tempMap[cur.y][cur.x] = map[nextY][nextX];
					prev = cur;
					cur = new Pos(nextY, nextX);
					break;
				}
			}

			tempMap[cur.y][cur.x] = 2;

		} else {
			while (map[cur.y][cur.x] != 3) {
				for (int i = 0; i < 4; i++) {
					int nextY = cur.y + moveY[i];
					int nextX = cur.x + moveX[i];

					if (isOutSide(nextY, nextX))
						continue;
					if (nextY == prev.y && nextX == prev.x)
						continue;
					if (map[nextY][nextX] == 0)
						continue;
					if (map[nextY][nextX] == 4)
						continue;

					if (map[nextY][nextX] == 1)
						tempHeads.add(new Pos(cur.y, cur.x));

					tempMap[cur.y][cur.x] = map[nextY][nextX];
					prev = cur;
					cur = new Pos(nextY, nextX);
					break;
				}
			}

			tempMap[cur.y][cur.x] = 4;
		}

		map = tempMap;
	}

	public static void throwBall(int round) {
		int r = (round - 1) % (4 * N) + 1;
		
		visited = new boolean[N][N];

		if (1 <= r && r <= N)
			goRight(r - 1, 0);
		else if (N + 1 <= r && r <= 2 * N)
			goUp(N - 1, r - (N + 1));
		else if (2 * N + 1 <= r && r <= 3 * N)
			goLeft(Math.abs(r - 3 * N), N - 1);
		else if (3 * N + 1 <= r && r <= 4 * N)
			goDown(0, Math.abs(r - 4 * N));
	}

	public static void goRight(int startY, int startX) {
		for (int x = startX; x < N; x++) {
			if (map[startY][x] == 0)
				continue;
			if (map[startY][x] == 4)
				continue;

			visited[startY][x] = true;
			dfs(startY, x, 0);
			score += number * number;

			map[tempHead.y][tempHead.x] = 3;
			map[tempTail.y][tempTail.x] = 1;

			heads.removeIf(h -> h.x == tempHead.x && h.y == tempHead.y);
			heads.add(tempTail);

			break;
		}
	}

	public static void goUp(int startY, int startX) {
		for (int y = startY; y >= 0; y--) {
			if (map[y][startX] == 0)
				continue;
			if (map[y][startX] == 4)
				continue;

			visited[y][startX] = true;
			dfs(y, startX, 0);
			score += number * number;

			map[tempHead.y][tempHead.x] = 3;
			map[tempTail.y][tempTail.x] = 1;

			heads.removeIf(h -> h.x == tempHead.x && h.y == tempHead.y);
			heads.add(tempTail);

			break;
		}
	}

	public static void goLeft(int startY, int startX) {
		for (int x = startX; x >= 0; x--) {
			if (map[startY][x] == 0)
				continue;
			if (map[startY][x] == 4)
				continue;

			visited[startY][x] = true;
			dfs(startY, x, 0);
			score += number * number;

			map[tempHead.y][tempHead.x] = 3;
			map[tempTail.y][tempTail.x] = 1;

			heads.removeIf(h -> h.x == tempHead.x && h.y == tempHead.y);
			heads.add(tempTail);

			break;
		}
	}

	public static void goDown(int startY, int startX) {
		for (int y = startY; y < N; y++) {
			if (map[y][startX] == 0)
				continue;
			if (map[y][startX] == 4)
				continue;

			visited[y][startX] = true;
			dfs(y, startX, 0);
			score += number * number;

			map[tempHead.y][tempHead.x] = 3;
			map[tempTail.y][tempTail.x] = 1;

			heads.removeIf(h -> h.x == tempHead.x && h.y == tempHead.y);
			heads.add(tempTail);

			break;
		}
	}

	public static void dfs(int y, int x, int num) {
		if (map[y][x] == 3) {
			tempTail = new Pos(y, x);
		}

		if (map[y][x] == 1) {
			number = num + 1;
			tempHead = new Pos(y, x);
		}

		for (int i = 0; i < 4; i++) {
			int nextY = y + moveY[i];
			int nextX = x + moveX[i];

			if (isOutSide(nextY, nextX))
				continue;
			if (map[nextY][nextX] == 0)
				continue;
			if (map[nextY][nextX] == 4)
				continue;
			if (visited[nextY][nextX])
				continue;
			if (map[y][x] == 3 && map[nextY][nextX] == 1)
				continue;
			if (map[y][x] == 1 && map[nextY][nextX] == 3)
				continue;

			visited[nextY][nextX] = true;
			dfs(nextY, nextX, num + 1);
			visited[nextY][nextX] = false;
		}
	}

	public static void solution(int N, int M, int K) {
		int round = 0;
		while (round++ < K) {
			moveTeams();
			throwBall(round);
		}
	}

	public static void main(String[] args) throws IOException {
		// 여기에 코드를 작성해주세요.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		heads = new ArrayList();

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				if (map[y][x] == 1)
					heads.add(new Pos(y, x));
			}
		}

		solution(N, M, K);

		System.out.println(score);
	}
}