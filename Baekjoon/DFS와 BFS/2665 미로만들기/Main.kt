// 미로만들기
// https://www.acmicpc.net/problem/2665

// BFS

// DP라 생각했는데, 문제 유형에 DP가 없다. ㅎㅎ

// 풀이법
// 1. BFS로 기본적인 탐색을 진행한다.
// 2. 이때, dp 배열에 현재까지 색을 바꾼 방의 개수를 기록해둔다.
// 3. 이미 방문했던 방이더라도, dp 값을 최소화 시킬 수 있다면 또 방문한다.

package Study04

object Main {

    private val moveX = intArrayOf(1, 0, -1, 0)
    private val moveY = intArrayOf(0, -1, 0, 1)

    data class Pos(val y: Int, val x: Int)

    fun solution(N: Int, map: Array<IntArray>): Int {
        val dp = Array(N) { IntArray(N) { Int.MAX_VALUE } }
        val queue = ArrayDeque<Pos>()

        queue.addLast(Pos(0, 0))
        dp[0][0] = 0

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()

            for (i in 0 until 4) {
                val nextY = cur.y + moveY[i]
                val nextX = cur.x + moveX[i]

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue

                val temp = if (map[nextY][nextX] == 0) dp[cur.y][cur.x] + 1 else dp[cur.y][cur.x]

                if (dp[nextY][nextX] != Int.MAX_VALUE && dp[nextY][nextX] <= temp) continue

                queue.addLast(Pos(nextY, nextX))
                dp[nextY][nextX] = temp
            }
        }

        return dp[N - 1][N - 1]
    }
}

fun main() {
    val N = readln().toInt()
    val map = Array(N) { IntArray(N) { 0 } }

    repeat(N) { col ->
        val row = readln().chunked(1).map { it.toInt() }.toIntArray()
        map[col] = row
    }

    println(Main.solution(N, map))
}