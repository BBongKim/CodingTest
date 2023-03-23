// 졸려
// https://www.acmicpc.net/problem/9519

// 시뮬레이션

// 깜빡이다보면 같은 단어가 순환됨을 이용하면 된다.

// 1. 같은 단어가 나올 때까지, 계속 깜빡임을 시뮬레이션 한다.
// 2. 원래 주어진 단어에서 거꾸로 X번 가면 원래 단어가 나온다.

// 하지만, X가 최대 10억번이기 때문에 직접 순환하면 안되고, X번 거꾸로 간 위치를 한번에 구해야한다.
// 따라서, list.size - (X % list.size) 를 이용한다.

// 처음에 1번 과정이 느리게 나와서, 투 포인터 형식으로 최적화했다.

package Study12

object Main4opt {
    fun solution(X: Int, word: String): String {
        val candidates = mutableSetOf<String>()
        candidates.add(word)

        while (true) {
            val lastWord = candidates.last()
            val sb = StringBuilder()

            var left = 0
            var right = lastWord.lastIndex

            while (left <= right) {
                sb.append(lastWord[left++])
                if (left <= right) sb.append(lastWord[right--])
                else right--
            }

            if (candidates.contains(sb.toString())) break
            else candidates.add(sb.toString())
        }

        if (candidates.size == 1) return word

        val list = candidates.toList()

        return list[list.size - (X % list.size)]
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val X = readLine().toInt()
    val word = readLine()

    println(Main4opt.solution(X, word))
}