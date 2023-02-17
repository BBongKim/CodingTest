// 비슷한 단어
// https://www.acmicpc.net/problem/2179

// 정렬

// 생각보다 어려운 정렬 문제였다.
// O(N) 정도의 시간으로 풀 필요가 있었다.

// 우선, 접두사가 비슷한 단어는 정렬하면 서로 가까운 인덱스 범위로 모여지게 된다.

// 1. 이 리스트를 순회하며 2개씩 idx, idx+1의 단어의 접두사 길이를 계산한다.
// 2. 두 단어의 접두사 길이가 최대 길이를 갱신하면, 정답 후보 리스트를 비우고, 두 개의 단어를 후보 리스트에 저장한다.
// 3. 두 단어의 접두사 길이가 최대 길이와 같다면, 후보 리스트에 저장만 한다.

// 4. 정답 후보 리스트를 원래 입력 인덱스 순으로 정렬해준다.
// 5. 0번 인덱스 단어는 S가 되고, 접두사 길이가 최대 길이와 같은 후보 단어가 T이다.

package Study07

data class Word(val value: String, val idx: Int)

object Main5 {
    var max = 0
    var ans = mutableSetOf<Word>()

    fun solution(N: Int, words: List<Word>): List<String> {
        val sortedWord = words.sortedBy { it.value }

        for (idx in 0 until N - 1) {
            val cnt = cal(sortedWord[idx], sortedWord[idx + 1])

            if (max < cnt) {
                ans.clear()
                ans.add(sortedWord[idx])
                ans.add(sortedWord[idx + 1])
                max = cnt
            } else if (cnt != 0 && max == cnt) {
                ans.add(sortedWord[idx])
                ans.add(sortedWord[idx + 1])
            }
        }

        val sortedAns = ans.sortedBy { it.idx }

        var S = ""
        var T = ""

        for (i in 1 until sortedAns.size) {
            if (cal(sortedAns[0], sortedAns[i]) == max) {
                S = sortedAns[0].value
                T = sortedAns[i].value
                break
            }
        }

        return if (sortedAns.isNotEmpty()) listOf(S, T) else listOf("", "")
    }

    private fun cal(a: Word, b: Word): Int {
        val minLength = minOf(a.value.length, b.value.length)
        var cnt = 0

        for (i in 0 until minLength) {
            if (a.value[i] == b.value[i]) cnt++
            else break
        }

        return cnt
    }
}

fun main() {
    val N = readln().toInt()
    val words = mutableListOf<Word>()

    repeat(N) {
        words.add(Word(readln(), it))
    }

    val ans = Main5.solution(N, words)

    println(ans[0])
    println(ans[1])
}