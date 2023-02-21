// 회사 문화 1
// https://www.acmicpc.net/problem/14267

// DP

// 단순하게 O(N^2)으로 푼다면
// 노드와 칭찬이 각각 10만개일 때, 최악의 경우 (트리가 연결 리스트와 같은 형태를 띌때) 10만^2 이다.

// 그래서, O(N)의 시간복잡도를 고려해보니 DP가 필요했다.

// 핵심은 (현재 노드의 칭찬 값) = (부모의 칭찬) + (직접 받는 칭찬) 이라는 점이다.
// 그래서 dp[i] = dp[parent[i]] + dp[i] 가 된다.

// 이 문제는 부모 노드가 항상 자신보다 작아서 괜찮긴한데,
// 만약 부모 노드가 더 큰 수가 나온다면, 부모의 칭찬 dp가 아직 계산되지 않았을 수도 있다.
// 그래서, DFS로 탐색을 진행하는 것이 필요하다.

package Study08

data class Compliment(val idx: Int, val amount: Int)

object Main33 {
    lateinit var answer: IntArray
    lateinit var visited: Array<Boolean>

    fun solution(N: Int, M: Int, parents: List<Int>, compliments: List<Compliment>) {
        answer = IntArray(N + 1) { 0 }
        visited = Array(N + 1) { false }

        compliments.forEach { answer[it.idx] += it.amount }

        for (i in 3..N) answer[i] += answer[parents[i]]

        val sb = StringBuilder()
        for (i in 1..N) sb.append("${answer[i]} ")

        println(sb.toString())
    }
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }
    val parents = readln().split(" ").map { it.toInt() }.toMutableList()
    val compliments = mutableListOf<Compliment>()

    repeat(M) {
        val (i, a) = readln().split(" ").map { it.toInt() }
        compliments.add(Compliment(i, a))
    }

    parents.add(0, 0)
    Main33.solution(N, M, parents, compliments)
}