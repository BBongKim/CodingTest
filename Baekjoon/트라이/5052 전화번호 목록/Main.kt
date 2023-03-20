// 전화번호 목록
// https://www.acmicpc.net/problem/5052

// 트라이

// 1.
// 트라이를 이용해서,
// 전화번호 삽입 도중에 이미 등록된 단어가 있으면 NO를 출력하면된다.

// 2.
// 전화번호를 짧은 순으로 먼저 정렬해야 트라이로 판단할 수 있다.

package Study12

data class TrieNode(val key: Char?, val parent: TrieNode?) {
    val child = HashMap<Char, TrieNode>()
    var isTerminated = false
}

class Trie {
    private val root = TrieNode(null, null)

    fun insert(str: String): Boolean {
        var cur = root

        for (key in str) {
            if (cur.child[key] == null) cur.child[key] = TrieNode(key, cur)
            else if (cur.child[key] != null && cur.child[key]!!.isTerminated) return false

            cur = cur.child[key]!!
        }

        cur.isTerminated = true

        return true
    }
}

object Main2 {
    fun solution(N: Int, numbers: List<String>) {
        val trie = Trie()

        var success = true

        for (number in numbers) {
            if (!trie.insert(number)) {
                success = false
                break
            }
        }

        if (success) println("YES") else println("NO")
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    var T = readLine().toInt()

    while (T-- > 0) {
        val N = readLine().toInt()
        val numbers = List(N) { readLine() }

        Main2.solution(N, numbers.sortedBy { it.length })
    }
}