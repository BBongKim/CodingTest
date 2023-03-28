// 호석이 두 마리 치킨
// https://www.acmicpc.net/problem/21278

// 플로이드-워셜

// 처음에는 조합 + BFS로 했는데, 시간복잡도를 계산해보니 재귀 조합 시간복잡도가 2^100임

// 그래서, 단순 2중 반복문과 플로이드 워셜을 이용해
// (100^2) + N^3 의 복잡도로 구했다.

// next_permutation 조합 방식을 공부해야겠다.

package Study13

lateinit var visited: BooleanArray
lateinit var distance: Array<IntArray>

var min = Int.MAX_VALUE
var ans = List(2) { 0 }

object Main2 {
    fun solution(N: Int) {
        for (i in 1..N) {
            for (j in 1..N) {
                for (k in 1..N) {
                    distance[j][k] = minOf(distance[j][k], distance[j][i] + distance[i][k])
                }
            }
        }

        val visited = Array(N + 1) { BooleanArray(N + 1) { false } }

        for (a in 1..N) {
            for (b in 1..N) {
                if (a == b) continue
                if (visited[a][b] || visited[b][a]) continue

                visited[a][b] = true
                visited[b][a] = true
                calculate(N, listOf(a, b))
            }
        }
    }

    private fun calculate(N: Int, store: List<Int>) {
        var sum = 0

        for (i in 1..N) {
            val closest = minOf(distance[i][store[0]], distance[i][store[1]])
            sum += closest * 2
        }

        if (sum < min) {
            min = sum
            ans = store
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }
    distance = Array(N + 1) { y -> IntArray(N + 1) { x -> if (y == x) 0 else 1e9.toInt() } }
    visited = BooleanArray(N + 1) { false }

    repeat(M) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        distance[a][b] = 1
        distance[b][a] = 1
    }

    Main2.solution(N)

    println("${ans[0]} ${ans[1]} $min")
}