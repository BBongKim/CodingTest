// 램프
// https://www.acmicpc.net/problem/1034

// 애드 혹

// 답은 쉬운데, 아이디어 도출이 어려움.

// 핵심은 행의 불이 모두 켜지는 상황을 파악하기 위해, 각 행마다 0의 개수를 구하는 것이다.
// 그리고, 해당 행의 모든 불을 킬 수 있는 경우인지 조건을 검사한다.

// 1. (0의 개수 < K)
// 0의 개수가 K보다 크다면, 해당 행은 모두 1이 될 수가 없다.

// 2. (0의 개수가 짝수이면, K도 짝수) 또는 (0의 개수가 홀수이면, K도 홀수)
// 둘의 짝홀이 맞지 않으면, 모든 불을 킬 수가 없다.
// (K % 0의 개수)개의 불이 안켜짐.

// 이 둘을 모두 만족한다면, 해당 행은 모두 불을 킬 수 있으므로,
// 해당 행과 같은 패턴을 가지는 행의 개수를 구하면된다.
// 최종적으로, 이 값들 중 최대값이 정답이 된다.

package Study10

object Main2 {
    fun solution(N: Int, M: Int, K: Int, map: List<String>): Int {
        val countMap = map.groupingBy { it }.eachCount()
        var max = 0

        for (row in map) {
            val countZero = row.count { it == '0' }
            if (countZero > K) continue
            if (countZero.isOdd() && K.isEven()) continue
            if (countZero.isEven() && K.isOdd()) continue

            max = maxOf(max, countMap.getOrDefault(row, 0))
        }

        return max
    }

    private fun Int.isEven(): Boolean = this % 2 == 0
    private fun Int.isOdd(): Boolean = this % 2 == 1
}

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val map = List(N) { readLine() }
    val K = readLine().toInt()

    println(Main2.solution(N, M, K, map))
}