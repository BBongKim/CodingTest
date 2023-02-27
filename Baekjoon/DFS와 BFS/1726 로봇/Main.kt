// 로봇
// https://www.acmicpc.net/problem/1726

// DFS

// 각 좌표마다 취할 수 있는 행동은 3개이다.
// 1. 왼쪽으로 회전
// 2. 오른쪽으로 회전
// 3. 전진

// 각 행동을 취한 후의 좌표를 큐에 넣어주면 된다.
// 이때, 회전만 하는 경우가 있기 때문에 visited 배열은 방향을 고려한 3차원을 사용했다.

// 처음에는 k 만큼 여러칸을 한번에 이동할 수 있기 때문에, 해당 칸들을 모두 방문처리 해주어야 한다고 생각했다.
// 그런데 그렇게 할 경우, k = 1일 때 방문처리가 되어, k = 2, k = 3의 경우는 전진하지 못하는 경우가 발생한다.
// 그래서, 마지막 좌표의 방문처리만 해주었다.

package Study09

data class Pos(val y: Int, val x: Int, val d: Int, val cnt: Int)


lateinit var map: Array<IntArray>

val moveX = intArrayOf(0, 1, 0, -1, 0)
val moveY = intArrayOf(0, 0, 1, 0, -1)

object Main {
    fun solution(M: Int, N: Int, map: Array<IntArray>, points: Array<Pos>): Int {
        val visited = Array(M) { Array(N) { Array(5) { false } } }
        val queue = ArrayDeque<Pos>()
        val start = points[0]
        val end = points[1]

        visited[start.y][start.x][start.d] = true
        queue.addLast(start)

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()

            if (cur.y == end.y && cur.x == end.x && cur.d == end.d) return cur.cnt

            for (i in 0..2) {
                if (i == 0) {
                    val nextD = if (cur.d - 1 == 0) 4 else cur.d - 1
                    if (visited[cur.y][cur.x][nextD]) continue

                    visited[cur.y][cur.x][nextD] = true
                    queue.addLast(Pos(cur.y, cur.x, nextD, cur.cnt + 1))

                    continue
                }

                if (i == 1) {
                    val nextD = if (cur.d + 1 == 5) 1 else cur.d + 1
                    if (visited[cur.y][cur.x][nextD]) continue

                    visited[cur.y][cur.x][nextD] = true
                    queue.addLast(Pos(cur.y, cur.x, nextD, cur.cnt + 1))

                    continue
                }

                step@ for (k in 1..3) {
                    // 조건 검사
                    val nextY = cur.y + moveY[cur.d] * k
                    val nextX = cur.x + moveX[cur.d] * k

                    if (nextY < 0 || nextY >= M || nextX < 0 || nextX >= N) continue@step
                    if (visited[nextY][nextX][cur.d]) continue@step

                    var tempY = cur.y
                    var tempX = cur.x
                    for (r in 0 until k) {
                        tempY += moveY[cur.d]
                        tempX += moveX[cur.d]
                        if (map[tempY][tempX] == 1) continue@step
                    }

                    visited[nextY][nextX][cur.d] = true
                    queue.addLast(Pos(nextY, nextX, cur.d, cur.cnt + 1))
                }
            }
        }

        return -1
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (M, N) = readLine().split(" ").map { it.toInt() }
    map = Array(M) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    val points = Array(2) {
        val (y, x, d) = readLine().split(" ").map { it.toInt() }

        when (d) {
            2 -> Pos(y - 1, x - 1, 3, 0)
            3 -> Pos(y - 1, x - 1, 2, 0)
            else -> Pos(y - 1, x - 1, d, 0)
        }
    }

    println(Main.solution(M, N, map, points))
}