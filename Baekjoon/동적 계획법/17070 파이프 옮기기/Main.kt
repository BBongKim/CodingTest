// 파이프 옮기기 1
// https://www.acmicpc.net/problem/17070

// DP
// 시간복잡도: O(N^2)

// dp[y][x][direction] = 각 방향별 경우의 수
// 각 칸에서 이동할 수 있는 칸의 경우의 수를 구하면 된다. [다음 칸 경우의 수 += 이전의 경우의 수]

val RIGHT = 0
val CROSS = 1
val DOWN = 2

fun solution(N: Int, map: Array<IntArray>): Int {
    val dp = Array(N) { Array(N) { IntArray(3) { 0 } } }

    dp[0][1][RIGHT] = 1

    for (y in 0 until N) {
        for (x in 0 until N) {
            for (prev_dir in 0 until 3) {
                if (dp[y][x][prev_dir] == 0) continue

                for (next_dir in 0 until 3) {
                    if (isPossible(N, map, y, x, prev_dir, next_dir)) {
                        when (next_dir) {
                            RIGHT -> dp[y][x + 1][next_dir] += dp[y][x][prev_dir]
                            CROSS -> dp[y + 1][x + 1][next_dir] += dp[y][x][prev_dir]
                            DOWN -> dp[y + 1][x][next_dir] += dp[y][x][prev_dir]
                        }
                    }
                }
            }
        }
    }

    return dp[N - 1][N - 1][RIGHT] + dp[N - 1][N - 1][CROSS] + dp[N - 1][N - 1][DOWN]
}

fun isPossible(N: Int, map: Array<IntArray>, y: Int, x: Int, prev_dir: Int, next_dir: Int): Boolean {
    when (prev_dir) {
        RIGHT -> if (next_dir == DOWN) return false
        DOWN -> if (next_dir == RIGHT) return false
    }

    when (next_dir) {
        RIGHT -> {
            if (x + 1 >= N) return false
            if (map[y][x + 1] == 1) return false
        }
        CROSS -> {
            if (y + 1 >= N || x + 1 >= N) return false
            if (map[y + 1][x + 1] == 1) return false
            if (map[y][x + 1] == 1) return false
            if (map[y + 1][x] == 1) return false
        }

        DOWN -> {
            if (y + 1 >= N) return false
            if (map[y + 1][x] == 1) return false
        }
    }
    return true
}

fun main() {
    val N = readln().toInt()
    val map = Array(N) { IntArray(N) { 0 } }

    repeat(N) { idx ->
        map[idx] = readln().split(" ").map { it.toInt() }.toIntArray()
    }

    println(solution(N, map))
}