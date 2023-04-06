// 술래잡기
// 시뮬레이션

// 문제 자체는 어렵지 않았다.
// 근데, 술래 잡는 함수에서 start, end 범위에 실수가 있어서 조금 헤맸다 ㅎㅎ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Item {
	int y;
	int x;
	int d;
	int direction;
	boolean isDead = false;

	Item(int y, int x) {
		this.y = y;
		this.x = x;
	}

	Item(int y, int x, int d) {
		this.y = y;
		this.x = x;
		this.d = d;
	}

	Item(int y, int x, int d, int direction) {
		this.y = y;
		this.x = x;
		this.d = d;
		this.direction = direction;
	}
}

class Catcher {
	int y;
	int x;
	int d;
	int direction;
	int leftDist;
	int distance;
	int count;

	Catcher(int y, int x, int d, int direction, int leftDist, int distance, int count) {
		this.y = y;
		this.x = x;
		this.d = d;
		this.direction = direction;
		this.leftDist = leftDist;
		this.distance = distance;
		this.count = count;
	}
}

public class Main {

	static int score = 0;
	static int t = 0;

	static int n;
	static int m;
	static int h;
	static int k;
	static Catcher catcher;

	static int[] moveX = { 0, 1, 0, -1 };
	static int[] moveY = { -1, 0, 1, 0 };

	public static void moveRunners(ArrayList<Item> runners) {
		for (Item runner : runners) {
			if (runner.isDead)
				continue;

			if (isMovable(runner)) {
				int nextY = runner.y + moveY[runner.direction];
				int nextX = runner.x + moveX[runner.direction];

				if (nextY < 0 || nextY >= n || nextX < 0 || nextX >= n) {
					runner.direction = (runner.direction + 2) % 4;
					nextY = runner.y + moveY[runner.direction];
					nextX = runner.x + moveX[runner.direction];
				}

				if (nextY == catcher.y && nextX == catcher.x)
					continue;

				runner.y = nextY;
				runner.x = nextX;
			}
		}
	}

	public static boolean isMovable(Item runner) {
		if (Math.abs(runner.x - catcher.x) + Math.abs(runner.y - catcher.y) <= 3)
			return true;
		else
			return false;
	}

	public static void moveCatcher() {
		int nextY = catcher.y + moveY[catcher.direction];
		int nextX = catcher.x + moveX[catcher.direction];
		catcher.leftDist--;

		// 방향 전환 필요.
		if (catcher.leftDist == 0) {
			catcher.count++;

			// 현재 진행 방향이 시계 방향.
			if (catcher.d == 0) {
				catcher.direction = (catcher.direction + 1) % 4;

				// 같은 거리로 2번 이동했을 경우.
				if (catcher.count == 2) {
					catcher.count = 0;
					catcher.distance++;
				}

			}
			// 현재 진행 방향이 반시계 방향.
			else {
				catcher.direction = catcher.direction - 1;
				if (catcher.direction < 0)
					catcher.direction = 3;

				if (catcher.count == 2) {
					catcher.count = 0;
					catcher.distance--;
				}
			}

			catcher.leftDist = catcher.distance;
		}

		if (nextY == 0 && nextX == 0 && catcher.d == 0) {
			catcher.d = 1;
			catcher.direction = 2;
			catcher.leftDist = n - 1;
			catcher.distance = n - 1;
			catcher.count = -1;
		}

		if (nextY == n / 2 && nextX == n / 2 && catcher.d == 1) {
			catcher.d = 0;
			catcher.direction = 0;
			catcher.leftDist = 1;
			catcher.distance = 1;
			catcher.count = 0;
		}

		catcher.y = nextY;
		catcher.x = nextX;
	}

	public static void catchRunners(ArrayList<Item> runners, boolean[][] trees) {
		int startY = catcher.y;
		int startX = catcher.x;
		int endY = catcher.y + moveY[catcher.direction] * 2;
		int endX = catcher.x + moveX[catcher.direction] * 2;
		
		if (startX > endX) {
			int temp = endX;
			endX = startX;
			startX = temp;
		}
		
		if (startY > endY) {
			int temp = endY;
			endY = startY;
			startY = temp;
		}
		
		int count = 0;

		for (Item runner : runners) {
			if (runner.isDead)
				continue;

			if (trees[runner.y][runner.x])
				continue;

			if (startY <= runner.y && runner.y <= endY && startX <= runner.x && runner.x <= endX) {
				count++;
				runner.isDead = true;
			}
		}

		score += (t * count);
	}

	public static int solution(ArrayList<Item> runners, boolean[][] trees) {
		while (t++ < k) {
			moveRunners(runners);
			moveCatcher();
			catchRunners(runners, trees);
		}

		return score;
	}

	public static void main(String[] args) throws IOException {
		// 여기에 코드를 작성해주세요.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		ArrayList<Item> runners = new ArrayList();
		boolean[][] trees = new boolean[n][n];
		catcher = new Catcher(n / 2, n / 2, 0, 0, 1, 1, 0);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			if (d == 1)
				runners.add(new Item(y - 1, x - 1, d, 1));
			else
				runners.add(new Item(y - 1, x - 1, d, 2));
		}

		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			trees[y - 1][x - 1] = true;
		}

		System.out.println(solution(runners, trees));
	}
}