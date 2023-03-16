// 이차원 배열과 연산
// https://www.acmicpc.net/problem/17140

// 시뮬레이션

// 문제가 원하는 동작을 그대로 구현하면 된다.
// 숫자 개수를 셀때, groupingBy.eachCount()를 사용했다.

package Study11

object Main3 {

    private var rowSize = 3
    private var colSize = 3

    fun solution(r: Int, c: Int, k: Int, A: Array<IntArray>): Int {
        val map = Array(100) { Array(100) { 0 } }

        for (y in 0 until 3) {
            for (x in 0 until 3) {
                map[y][x] = A[y][x]
            }
        }

        var time = 0


        while (time <= 100) {
            if (map[r][c] == k) return time

            if (colSize >= rowSize) doR(map)
            else doC(map)

            time++
        }

        return -1
    }

    private fun doR(map: Array<Array<Int>>) {
        var max = 0

        for (y in 0 until colSize) {
            val tempMap = map[y].groupingBy { it }.eachCount()
            val sortedPair = tempMap.toList().sortedWith(compareBy<Pair<Int, Int>> { it.second }.thenBy { it.first })

            map[y].fill(0)

            var x = 0
            for (entry in sortedPair) {
                if (entry.first == 0) continue
                map[y][x++] = entry.first
                map[y][x++] = entry.second
            }

            max = maxOf(x, max)
        }

        rowSize = max
    }

    private fun doC(map: Array<Array<Int>>) {
        var max = 0

        for (x in 0 until rowSize) {
            val tempList = mutableListOf<Int>()

            for (y in 0 until colSize) {
                tempList.add(map[y][x])
                map[y][x] = 0
            }

            val tempMap = tempList.groupingBy { it }.eachCount()
            val sortedPair = tempMap.toList().sortedWith(compareBy<Pair<Int, Int>> { it.second }.thenBy { it.first })

            var y = 0
            for (entry in sortedPair) {
                if (entry.first == 0) continue
                map[y++][x] = entry.first
                map[y++][x] = entry.second
            }

            max = maxOf(y, max)
        }

        colSize = max
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (r, c, k) = readLine().split(" ").map { it.toInt() }
    val A = Array(3) { readLine().split(" ").map { it.toInt() }.toIntArray() }

    println(Main3.solution(r - 1, c - 1, k, A))
}