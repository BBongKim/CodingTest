// DP 문제
// 단순하게 합하면 역시나 시간초과한다.
// 그래서, 최적화할 방법을 찾았는데 Prefix Sum 방식 (이게 결국 DP)을 이용하였다.

// 풀고나서, 풀이를 보니 Prefix Sum과 비슷한데 더 효율적이게 합을 구하는 방법이 있다.

import kotlin.math.abs

lateinit var map: Array<IntArray>
lateinit var prefixSum: Array<IntArray>

fun solution(x1: Int, y1: Int, x2: Int, y2: Int): Int {
    if (x1 == x2 && y1 == y2) return map[x1 - 1][y1 - 1]

    val deltaY = abs(x2 - x1) + 1

    var startY = 0
    var startX = 0
    var endX = 0

    startY = if (x1 > x2) y2 - 1 else x1 - 1

    if (y1 > y2) {
        startX = y2
        endX = y1
    } else {
        startX = y1
        endX = y2
    }

    var sum = 0

    repeat(deltaY) {
        sum += (prefixSum[startY][endX] - prefixSum[startY][startX - 1])
        startY++
    }

    return sum
}

fun main() {
    var (N, M) = readln().split(" ").map { it.toInt() }
    map = Array(N) { IntArray(N) { 0 } }
    prefixSum = Array(N) { IntArray(N + 1) { 0 } }

    repeat(N) { i ->
        map[i] = readln().split(" ").map { it.toInt() }.toIntArray()
    }

    for (y in 0 until N) {
        var sum = 0
        var idx = 1
        for (x in 0 until N) {
            sum += map[y][x]
            prefixSum[y][idx++] = sum
        }
    }

    val sb = StringBuilder()

    repeat(M) {
        val (x1, y1, x2, y2) = readln().split(" ").map { it.toInt() }

        sb.append(solution(x1, y1, x2, y2)).append("\n")
    }



    print(sb.toString())
}