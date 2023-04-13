// 사회망 서비스(SNS)
// https://www.acmicpc.net/problem/2533

// 트리, DP

// 트리에 DP를 적용하는 문제이다.
// 트리에서 DP를 적용할 때는 "dp[i] -> i 노드를 루트로 하는 서브트리의 조건을 만족하는 X 값" 으로 테이블을 생각하면 편하다.

// 이 문제에서는 dp[i][0] -> i 노드가 일반 노드일때, i 노드를 루트로 하는 서브트리에서의 최소 얼리어답터 수
// dp[i][1] -> i 노드가 얼리어답터 일때, i 노드를 루트로 하는 서브트리에서의 최소 얼리어답터 수
// 로 생각했다.

// dp[i][0] 일때는, 자식 노드가 모두 얼리어답터일 필요가 있기 때문에, dp[i][0] += dp[child][1] 이 된다.
// dp[i][1] 일때는, 자식 노드가 얼리어답터여야 할 수도 아닐 수도 있다.
// 그래서 더 최소값을 설정해주면 된다. dp[i][1] += min(dp[child][0], dp[child][1])

package Study14

object Main2 {
    lateinit var dp: Array<IntArray>
    lateinit var visited: BooleanArray

    private fun dfs(i: Int, map: List<List<Int>>) {
        dp[i][1] = 1

        for (child in map[i]) {
            if (visited[child]) continue

            visited[child] = true
            dfs(child, map)

            dp[i][0] += dp[child][1]
            dp[i][1] += minOf(dp[child][0], dp[child][1])
        }
    }

    fun solution(N: Int, map: List<List<Int>>): Int {
        dp = Array(N + 1) { IntArray(2) { 0 } }
        visited = BooleanArray(N + 1) { false }

        visited[1] = true
        dfs(1, map)

        return minOf(dp[1][0], dp[1][1])
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()

    val map = List(N + 1) { mutableListOf<Int>() }

    repeat(N - 1) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        map[a].add(b)
        map[b].add(a)       // 양방향으로 해주어야 루트 상관 없이 답을 구할 수 있음.
    }

    println(Main2.solution(N, map))
}