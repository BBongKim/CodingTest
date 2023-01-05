// 연구소 2
// https://www.acmicpc.net/problem/17141

// 백트래킹 + BFS

// 문제 자체는 어렵지 않은데, 코드가 길어졌다.
// 바이러스를 두는 모든 조합을 브루트 포스로 구한 다음에, 바이러스를 BFS를 통해 시뮬레이션 해주면 된다.

package Study02

data class Pos(val y: Int, val x: Int)

val moveX = intArrayOf(1, 0, -1, 0)
val moveY = intArrayOf(0, -1, 0, 1)

lateinit var visited: Array<Boolean>
var answer = Int.MAX_VALUE

fun solution(N: Int, M: Int, map: Array<Array<Int>>): Int {
    val virusPositions = findVirusPosition(map)
    if (virusPositions.isEmpty()) return -1
    visited = Array(virusPositions.size) { false }

    selectVirusPositions(N, map, virusPositions, 0, M)

    return if (answer == Int.MAX_VALUE) -1
    else answer
}

fun findVirusPosition(map: Array<Array<Int>>): List<Pos> {
    val list = mutableListOf<Pos>()
    for (y in map.indices) {
        for (x in map[y].indices) {
            if (map[y][x] == 2) {
                list.add(Pos(y, x))
                map[y][x] = 0
            }
        }
    }

    return list
}

fun selectVirusPositions(N: Int, map: Array<Array<Int>>, virusPositions: List<Pos>, start: Int, M: Int) {
    if (M == 0) {
        val selectedVirusPositions = mutableListOf<Pos>()
        for (i in visited.indices) {
            if (visited[i]) selectedVirusPositions.add(virusPositions[i])
        }
        findAnswer(N, map, selectedVirusPositions)
    } else {
        for (idx in start until visited.size) {
            if (!visited[idx]) {
                visited[idx] = true
                selectVirusPositions(N, map, virusPositions, idx + 1, M - 1)
                visited[idx] = false
            }
        }
    }
}

fun findAnswer(N: Int, map: Array<Array<Int>>, selectedVirusPositions: List<Pos>) {
    val queue = ArrayDeque(selectedVirusPositions)

    var tempAnswer = 0
    var count = 0

    val tempMap = Array(N) { row -> Array(N) { col -> map[row][col] } }

    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()

        for (idx in 0 until 4) {
            val nextY = cur.y + moveY[idx]
            val nextX = cur.x + moveX[idx]

            if (nextY < 0 || nextY >= N) continue
            if (nextX < 0 || nextX >= N) continue
            if (tempMap[nextY][nextX] != 0) continue
            if (isVirusPosition(selectedVirusPositions, nextY, nextX)) continue

            tempMap[nextY][nextX] = tempMap[cur.y][cur.x] + 1
            tempAnswer = maxOf(tempMap[nextY][nextX], tempAnswer)
            count++
            queue.addLast(Pos(nextY, nextX))
        }
    }

    if (isVirusFull(selectedVirusPositions.size, tempMap)) answer = minOf(tempAnswer, answer)
}

fun isVirusPosition(selectedVirusPositions: List<Pos>, nextY: Int, nextX: Int): Boolean {
    for (pos in selectedVirusPositions) {
        if (pos.y == nextY && pos.x == nextX) return true
    }
    return false
}

fun isVirusFull(M: Int, map: Array<Array<Int>>): Boolean {
    var count = 0
    for (m in map) {
        for (value in m) {
            if (value == 0) count++
        }
    }

    return count == M
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }

    val map = Array(N) { Array(N) { 0 } }

    repeat(N) { row ->
        map[row] = readln().split(" ").map { it.toInt() }.toTypedArray()
    }

    println(solution(N, M, map))
}