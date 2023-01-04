// 상범 빌딩
// https://www.acmicpc.net/problem/6593

// BFS 문제

// 기존 2차원 4방향 BFS가 단순하게 3차원 6방향이 되었다고 생각하면 된다.

package Study02

val moveX = intArrayOf(1, 0, -1, 0, 0, 0)
val moveY = intArrayOf(0, -1, 0, 1, 0, 0)
val moveZ = intArrayOf(0, 0, 0, 0, 1, -1)

data class Pos(val z: Int, val y: Int, val x: Int, val cost: Int)

fun solution(L: Int, R: Int, C: Int, building: Array<Array<Array<String>>>): String {
    val visited = Array(L) { Array(R) { Array(C) { false } } }
    val queue = ArrayDeque<Pos>()
    val startPos = findStartPoint(building)

    visited[startPos.z][startPos.y][startPos.x] = true
    queue.addLast(startPos)

    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()

        if (building[cur.z][cur.y][cur.x] == "E") return "Escaped in ${cur.cost} minute(s)."

        for (i in 0 until 6) {
            val nextZ = cur.z + moveZ[i]
            val nextY = cur.y + moveY[i]
            val nextX = cur.x + moveX[i]

            if (nextZ < 0 || nextZ >= L) continue
            if (nextY < 0 || nextY >= R) continue
            if (nextX < 0 || nextX >= C) continue
            if (visited[nextZ][nextY][nextX]) continue
            if (building[nextZ][nextY][nextX] == "#") continue

            visited[nextZ][nextY][nextX] = true
            queue.addLast(Pos(nextZ, nextY, nextX, cur.cost + 1))
        }
    }

    return "Trapped!"
}

fun findStartPoint(building: Array<Array<Array<String>>>): Pos {
    for (l in building.indices) {
        for (r in building[l].indices) {
            for (c in building[l][r].indices) {
                val value = building[l][r][c]

                if (value == "S") return Pos(l, r, c, 0)
            }
        }
    }

    // should not reach
    return Pos(-1, -1, -1, -1)
}

fun main() {

    while (true) {
        val (L, R, C) = readln().split(" ").map { it.toInt() }

        if (L == 0 && R == 0 && C == 0) break

        val building = Array(L) { Array(R) { Array(C) { " " } } }

        repeat(L) { l ->
            repeat(R) { r ->
                building[l][r] = readln().chunked(1).toTypedArray()
            }
            readln()
        }

        println(solution(L, R, C, building))
    }
}