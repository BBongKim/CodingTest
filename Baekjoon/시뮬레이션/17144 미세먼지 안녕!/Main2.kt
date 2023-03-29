// 미세먼지 안녕!
// https://www.acmicpc.net/problem/17144

// 시뮬레이션

// 예전에 풀었는데 다시 풀어봤다.
// 하라는데로 하면 되는데, 이런 값이 확산하는 문제는 보조(auxiliary) 배열을 사용하는 것이 편한 것 같다.

package Study13

lateinit var map: Array<IntArray>

object Main3 {
    val moveX = intArrayOf(1, 0, -1, 0)
    val moveY = intArrayOf(0, -1, 0, 1)

    fun solution(R: Int, C: Int, T: Int): Int {
        var machine = 0
        var time = T

        for (y in 0 until R) {
            for (x in 0 until C) {
                if (map[y][x] == -1) machine = y
            }
        }

        while (time-- > 0) {
            spread(machine, R, C)
            cleanAir(machine, R, C)
        }

        return map.sumOf { row -> row.sumOf { it } } + 2
    }

    private fun spread(machineY: Int, R: Int, C: Int) {
        val tempMap = Array(R) { IntArray(C) { 0 } }

        for (y in 0 until R) {
            for (x in 0 until C) {
                if (map[y][x] <= 0) continue

                val nextValue = map[y][x] / 5
                var cnt = 0

                for (i in 0 until 4) {
                    val nextY = y + moveY[i]
                    val nextX = x + moveX[i]

                    if (nextY < 0 || nextY >= R || nextX < 0 || nextX >= C) continue
                    if (map[nextY][nextX] == -1) continue

                    tempMap[nextY][nextX] += nextValue
                    cnt++
                }

                tempMap[y][x] += maxOf(0, map[y][x] - nextValue * cnt)

            }
        }

        tempMap[machineY - 1][0] = -1
        tempMap[machineY][0] = -1
        map = tempMap
    }

    private fun cleanAir(machineY: Int, R: Int, C: Int) {
        val top = machineY - 1

        // 1. top
        // 맨 왼쪽
        for (y in top - 1 downTo 0) {
            if (y == top - 1) {
                map[y][0] = 0
                continue
            }
            map[y + 1][0] = map[y][0]
        }
        // 맨 위쪽
        for (x in 1 until C) map[0][x - 1] = map[0][x]

        // 맨 오른쪽
        for (y in 1..top) map[y - 1][C - 1] = map[y][C - 1]

        // 맨 아래쪽
        for (x in C - 2 downTo 1) map[top][x + 1] = map[top][x]

        map[top][1] = 0

        // 2. bottom
        // 맨 왼쪽
        for (y in machineY + 1 until R) {
            if (y == machineY + 1) {
                map[y][0] = 0
                continue
            }
            map[y - 1][0] = map[y][0]
        }

        // 맨 아래쪽
        for (x in 1 until C) map[R - 1][x - 1] = map[R - 1][x]

        // 맨 오른쪽
        for (y in R - 2 downTo machineY) map[y + 1][C - 1] = map[y][C - 1]

        // 맨 위쪽
        for (x in C - 2 downTo 1) map[machineY][x + 1] = map[machineY][x]
        map[machineY][1] = 0
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (R, C, T) = readLine().split(" ").map { it.toInt() }
    map = Array(R) { readLine().split(" ").map { it.toInt() }.toIntArray() }

    println(Main3.solution(R, C, T))
}