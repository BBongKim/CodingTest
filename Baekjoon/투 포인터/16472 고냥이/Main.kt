// 고냥이
// https://www.acmicpc.net/problem/16472

// 투 포인터

// N개의 알파벳 갯수를 유지하도록 투 포인터를 옮겨주고, 그 과정에서의 최대 길이를 구하면 된다.

// 알파벳이 N개 이하인 경우 -> right++
// 알파벳이 N개 초과한 경우 -> left++

package Study15

object Main {

    fun solution(N: Int, word: String): Int {
        var max = 2
        var length = 2
        var left = 0
        var right = 1

        val map = HashMap<Char, Int>()

        if (word.length == 1) return 1

        map[word[left]] = 1
        map[word[right]] = 1

        while (right < word.length && left < right) {
            if (map.size <= N) {
                right++
                if (right < word.length) {
                    val prev = map.getOrDefault(word[right], 0)
                    map[word[right]] = prev + 1
                    length++
                }
            } else {
                val prev = map.getOrDefault(word[left], 1)
                map[word[left]] = prev - 1

                if (map[word[left]] == 0) map.remove(word[left])

                left++
                length--
            }

            if (map.size <= N) max = maxOf(max, length)
        }

        return max
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val word = readLine()

    print(Main.solution(N, word))
}