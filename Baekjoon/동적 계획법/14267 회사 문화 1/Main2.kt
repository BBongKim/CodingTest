// 그렇게 해서도 풀어봄.

package Study08

data class Compliment(val idx: Int, val amount: Int)

object Main3 {
    lateinit var answer: IntArray
    lateinit var visited: Array<Boolean>

    fun solution(N: Int, M: Int, parents: List<Int>, compliments: List<Compliment>) {
        answer = IntArray(N + 1) { 0 }
        visited = Array(N + 1) { false }

        compliments.forEach { answer[it.idx] += it.amount }

        for (i in 3..N) {
            answer[i] += if (answer[parents[i]] == 0 && !visited[parents[i]]) {
                dfs(parents, parents[i])
            } else {
                answer[parents[i]]
            }
        }

        val sb = StringBuilder()
        for (i in 1..N) sb.append("${answer[i]} ")

        println(sb.toString())
    }

    private fun dfs(parents: List<Int>, idx: Int): Int {
        if (idx == 1) return 0

        if (answer[idx] == 0 && !visited[idx]) answer[idx] = dfs(parents, parents[idx])

        visited[idx] = true
        return answer[idx]
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
    Main3.solution(N, M, parents, compliments)
}