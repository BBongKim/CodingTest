// 가르침
// https://www.acmicpc.net/problem/1062

// 비트마스킹, 백트래킹

// 이 문제는 꼭 비트마스킹을 안써도 되지만, 연습을 위해 써봄.

// 기본적으로 DFS 백트래킹을 통해서, 알파벳을 선택한다. (이때, 집합 표현을 비트 마스킹으로 한다.)
// 그리고, K 개의 알파벳을 다 뽑았을 때마다, 단어를 읽을 수 있는지 확인하면 된다. (각 문자가 마스킹 집합에 포합되었는지 확인한다.)

package Study11

object Main4 {

    private var max = 0
    private var masking = 0

    fun solution(N: Int, K: Int, words: Array<String>): Int {
        if (K < 5) return 0

        // a n t i c
        masking = masking or (1 shl 'a' - 'a')
        masking = masking or (1 shl 'n' - 'a')
        masking = masking or (1 shl 't' - 'a')
        masking = masking or (1 shl 'i' - 'a')
        masking = masking or (1 shl 'c' - 'a')

        permutation(words, K, 0, 5)

        return max
    }

    private fun permutation(words: Array<String>, K: Int, start: Int, M: Int) {
        if (M == K) {
            var count = 0

            words@ for (word in words) {
                for (idx in 3 until word.length - 4) {
                    if (masking and (1 shl word[idx] - 'a') == 0) continue@words
                }
                count++
            }

            max = maxOf(max, count)

            return
        }

        for (i in start until 26) {
            if (masking and (1 shl i) == 0) {
                masking = masking or (1 shl i)
                permutation(words, K, i + 1, M + 1)
                masking = masking and (1 shl i).inv()
            }
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map { it.toInt() }
    val words = Array(N) { readLine() }

    println(Main4.solution(N, K, words))
}