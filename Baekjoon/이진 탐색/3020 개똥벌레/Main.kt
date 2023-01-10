// 개똥벌레
// https://www.acmicpc.net/problem/3020

// 이분탐색

// 출발 높이를 이분 탐색하는 것이 아니라, 충돌하는 장애물의 개수를 이분 탐색하는 것이 핵심이다.
// 석순의 충돌 개수는 출발 높이에서 lowerBound를, 종유석의 충돌 개수는 upperBound를 이용하여 구할 수 있다.

// 석순의 경우, 출발 높이 이상의 높이를 가진 석순은 모두 충돌하며, -> lowerBound
// 종유석의 경우, 출발 높이 초과의 높이를 가진 석순은 모두 충돌한다. -> upperBound

package Study03

object Main {
    fun solution(N: Int, H: Int, upperWalls: List<Int>, downWalls: List<Int>): IntArray {
        var min = Int.MAX_VALUE
        var answerCount = 0

        for (height in 1..H) {
            val downCount = lowerBound(0, N / 2, height, downWalls)
            val upCount = upperBound(0, N / 2, H - height, upperWalls)
            val crashCount = downCount + upCount

            if (crashCount < min) {
                min = crashCount
                answerCount = 1
            } else if (crashCount == min) {
                answerCount++
            }
        }

        return intArrayOf(min, answerCount)
    }

    private fun lowerBound(left: Int, right: Int, height: Int, walls: List<Int>): Int {
        var start = left
        var end = right

        while (start < end) {
            var mid = (start + end) / 2

            if (walls[mid] >= height) {
                end = mid
            } else {
                start = mid + 1
            }
        }
        return walls.size - end
    }

    private fun upperBound(left: Int, right: Int, height: Int, walls: List<Int>): Int {
        var start = left
        var end = right

        while (start < end) {
            var mid = (start + end) / 2

            if (walls[mid] > height) {
                end = mid
            } else {
                start = mid + 1
            }
        }
        return walls.size - end
    }
}

fun main() {
    val (N, H) = readln().split(" ").map { it.toInt() }
    val upperWalls = mutableListOf<Int>()
    val downWalls = mutableListOf<Int>()

    repeat(N) { i ->
        val h = readln().toInt()

        if (i % 2 == 0) downWalls.add(h)
        else upperWalls.add(h)
    }

    val ans = Main.solution(N, H, upperWalls.sorted(), downWalls.sorted())

    println("${ans[0]} ${ans[1]}")
}