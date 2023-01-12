// 빙산
// https://www.acmicpc.net/problem/2573

// BFS
// 주의할 점은 0의 개수를 세고 바로 빙산을 녹이면 안된다.

package Study03

object Main2 {

    data class Pos(val y: Int, val x: Int)

    val moveX = intArrayOf(1, 0, -1, 0)
    val moveY = intArrayOf(0, -1, 0, 1)

    fun solution(N: Int, M: Int, map: Array<IntArray>): Int {
        var time = 0

        while (true) {
            val piece = getIcebergPieces(N, M, map)
            if (piece >= 2) return time
            else if (piece == 0) return 0

            meltIceberg(N, M, map)
            time++
        }
    }

    private fun getIcebergPieces(N: Int, M: Int, map: Array<IntArray>): Int {
        val visited = Array(N) { Array(M) { false } }
        var piece = 0

        for (row in map.indices) {
            for (col in map[row].indices) {
                if (map[row][col] == 0) continue
                if (visited[row][col]) continue

                piece++

                val queue = ArrayDeque<Pos>()
                visited[row][col] = true
                queue.addFirst(Pos(row, col))

                while (queue.isNotEmpty()) {
                    val cur = queue.removeFirst()

                    for (i in 0 until 4) {
                        val nextY = cur.y + moveY[i]
                        val nextX = cur.x + moveX[i]

                        if (nextY >= N || nextY < 0 || nextX >= M || nextX < 0) continue
                        if (visited[nextY][nextX]) continue
                        if (map[nextY][nextX] == 0) continue

                        visited[nextY][nextX] = true
                        queue.addFirst(Pos(nextY, nextX))
                    }
                }
            }
        }

        return piece
    }

    private fun meltIceberg(N: Int, M: Int, map: Array<IntArray>) {
        val tempZeroCount = Array(N) { IntArray(M) { 0 } }

        for (row in map.indices) {
            for (col in map[row].indices) {
                if (map[row][col] == 0) continue

                var zeroCount = 0

                for (i in 0 until 4) {
                    val nextY = row + moveY[i]
                    val nextX = col + moveX[i]
                    val next = map[nextY][nextX]

                    if (next == 0) zeroCount++
                }

                tempZeroCount[row][col] = zeroCount
            }
        }

        for (row in map.indices) {
            for (col in map[row].indices) {
                if (map[row][col] == 0) continue
                if (tempZeroCount[row][col] == 0) continue

                map[row][col] -= tempZeroCount[row][col]
                map[row][col] = maxOf(map[row][col], 0)
            }
        }
    }
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }
    val map = Array(N) { IntArray(M) { 0 } }

    repeat(N) {
        val row = readln().split(" ").map { it.toInt() }.toIntArray()
        map[it] = row
    }

    println(Main2.solution(N, M, map))
}