// 제자리 멀리뛰기
// https://www.acmicpc.net/problem/6209

// 이분탐색

// 탐색 대상은 점프할 수 있는 최소거리이다.

// 타겟 거리를 최소거리로 설정하고 이동할때, 제거해야하는 돌의 개수가 M개 보다 많은지 적은지를 판단한다.

// 근데 왜, left < right .... right = mid, left = mid - 1로는 안되는건지 이해가 안된다.

package Study03

object Main {

    fun solution(end: Int, N: Int, M: Int, rock: List<Int>): Int {
        var left = 0
        var right = end

        while (left <= right) {
            var count = 0

            val mid = (left + right) / 2
            var cur = 0

            for (i in rock.indices) {
                if (rock[i] - cur < mid) {
                    count++
                } else {
                    cur = rock[i]
                }
            }

            if (count > M) {
                right = mid - 1
            } else {          
                left = mid + 1
            }
        }

        return right
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (end, N, M) = readLine().split(" ").map { it.toInt() }
    val rocks = List(N) { readLine().toInt() }

    println(Main.solution(end, N, M, rocks.sorted()))
}
