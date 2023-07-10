// 암벽 등반
// https://www.acmicpc.net/problem/2412

// BFS

// 최단 거리를 찾기 때문에, BFS를 사용했다.

// 하지만, 단순하게 모든 구멍을 계속 순회하면, 시간초과가 나기 때문에
// y 기준 +- 2 거리에 있는 좌표만 탐색하도록 하여, 탐색시간을 줄였다.

package Study02

import kotlin.math.abs

data class Pos(var i: Int, val y: Int, val x: Int, val cnt: Int)

lateinit var visited: BooleanArray

object Main {

    fun solution(holes: List<Pos>, T: Int): Int {
        val queue = ArrayDeque<Pos>()

        holes.forEachIndexed { index, pos -> pos.i = index }

        visited[0] = true
        queue.addLast(holes.first())

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()

            if (cur.y == T) return cur.cnt

            for (i in cur.i + 1 until holes.size) {
                val next = holes[i]
                if (next.y - cur.y > 2) break
                if (abs(next.x - cur.x) > 2) continue
                if (visited[next.i]) continue

                queue.addLast(Pos(next.i, next.y, next.x, cur.cnt + 1))
                visited[next.i] = true
            }

            for (i in cur.i - 1 downTo 0) {
                val next = holes[i]
                if (next.y - cur.y < -2) break
                if (abs(next.x - cur.x) > 2) continue
                if (visited[next.i]) continue

                queue.addLast(Pos(next.i, next.y, next.x, cur.cnt + 1))
                visited[next.i] = true
            }
        }

        return -1
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (n, T) = readLine().split(" ").map { it.toInt() }

    visited = BooleanArray(n + 1) { false }

    val holes = MutableList(n) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        Pos(0, y, x, 0)
    }

    holes.add(Pos(0, 0, 0, 0))

    println(Main.solution(holes.sortedWith(compareBy<Pos> { it.y }.thenBy { it.x }), T))
}