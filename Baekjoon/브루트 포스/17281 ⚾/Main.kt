// ⚾
// https://www.acmicpc.net/problem/17281

// 구현, 브루트포스

// 9! = 약 36만
// 36만 x 50 = 약 1800만이기 때문에 시간내에 가능하다.

// 4번 타자가 고정이어서, 최적화를 더 진행하면 O(8! x 50)이 된다.

// 야구 게임은 규칙대로 시뮬레이션했다.

package Study10

object Main {
    var max = 0
    var score = 0

    fun solution(N: Int, records: Array<IntArray>): Int {
        val visited = BooleanArray(9) { false }
        val results = IntArray(9) { 0 }

        results[3] = 0
        dfs(records, results, visited, 0)

        return max
    }

    private fun dfs(records: Array<IntArray>, results: IntArray, visited: BooleanArray, idx: Int) {
        if (idx == 9 && results[3] == 0) {
            simulate(records, results)
            return
        }

        for (i in 0..8) {
            if (!visited[i]) {
                visited[i] = true
                results[idx] = i
                dfs(records, results, visited, idx + 1)
                visited[i] = false
            }
        }
    }

    private fun simulate(records: Array<IntArray>, hitters: IntArray) {
        score = 0
        var out = 0
        val hasBaseman = BooleanArray(4) { false }
        var nextHitterNumber = 0

        for (inning in records.indices) {
            while (out < 3) {
                when (records[inning][hitters[nextHitterNumber++]]) {
                    0 -> out++
                    1 -> moveBaseman(hasBaseman, 1)
                    2 -> moveBaseman(hasBaseman, 2)
                    3 -> moveBaseman(hasBaseman, 3)
                    4 -> moveBaseman(hasBaseman, 4)
                }
                nextHitterNumber %= 9
            }

            out = 0
            hasBaseman.fill(false)
        }

        max = maxOf(max, score)
    }

    private fun moveBaseman(hasBaseman: BooleanArray, k: Int) {
        for (i in 3 downTo 1) {
            if (!hasBaseman[i]) continue

            hasBaseman[i] = false
            val nextBase = i + k
            if (nextBase >= 4) score++
            else hasBaseman[nextBase] = true
        }

        if (k == 4) score++
        else hasBaseman[k] = true
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val records = Array(N) { readLine().split(" ").map { it.toInt() }.toIntArray() }

    println(Main.solution(N, records))
}