// 애너그램
// https://www.acmicpc.net/problem/6443

// 백트래킹

// 처음에, HashMap을 이용하여 바로 순열로 풀었는데 자꾸 메모리 초과가 났다...
// 그래서, 알파벳 개수를 센 후에, 알파벳을 더 사용할 수 있는지를 확인하는 식으로 변경했더니 풀렸다.
// 이유가 뭔지 감이 안잡힌다... 로드 펙터 때문인가...?

package Study08

object Main {
    lateinit var answer: MutableList<String>
    lateinit var count: MutableMap<Char, Int>

    fun solution(N: Int, words: List<String>) {
        for (word in words) {
            val sb = StringBuilder()

            answer = mutableListOf()
            count = word.groupingBy { it }.eachCount().toSortedMap().toMutableMap()

            tracking(word.toCharArray(), word.length, 0, "")

            answer.forEach { sb.append(it).append("\n") }

            print(sb.toString())
        }
    }

    private fun tracking(chars: CharArray, size: Int, depth: Int, str: String) {
        if (depth == size) {
            answer.add(str)
        } else {
            for (char in count.keys) {
                if (count[char]!! > 0) {
                    count[char] = count[char]!! - 1
                    tracking(chars, size, depth + 1, str + char)
                    count[char] = count[char]!! + 1
                }
            }
        }
    }
}

fun main() {
    val N = readln().toInt()
    val words = mutableListOf<String>()

    repeat(N) {
        words.add(readln())
    }

    Main.solution(N, words)
}