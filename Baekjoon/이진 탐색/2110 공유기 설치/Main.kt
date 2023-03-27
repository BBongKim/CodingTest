// 공유기 설치
// https://www.acmicpc.net/problem/2110

// 이분 탐색

// 가장 인접하다는 것은 거리가 최소 거리라는 뜻.
// 결국 최소 거리 중, 최댓값을 찾는 것.

// 최소 거리를 이분 탐색을 이용해 찾는다.
// 집과 집 사이의 거리가 mid 값보다 크거나 같은지를 확인하여
// C개의 공유기를 mid 값보다 크거나 같은 거리 차이로 설치할 수 있는지를 확인한다.

// C개보다 더 많이 설치 가능 -> 탐색 거리를 늘림 -> left = mid + 1
// C개 미만 -> 탐색 거리를 줄임 -> right = mid - 1

// 탐색 거리의 최댓값을 구하기 때문에, right 값을 반환해주면 된다.

package Study13

object Main {
    fun solution(N: Int, C: Int, pos: List<Int>): Int {
        var left = 0
        var right = pos.last()

        while (left <= right) {
            val mid = (left + right) / 2

            var cnt = 1
            var cur = 0

            for (next in 1 until pos.size) {
                if (pos[next] - pos[cur] >= mid) {
                    cur = next
                    cnt++
                }
            }

            if (cnt < C) right = mid - 1
            else left = mid + 1
        }

        return right
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (N, C) = readLine().split(" ").map { it.toInt() }
    val pos = IntArray(N) { readLine().toInt() }

    println(Main.solution(N, C, pos.sorted()))
}