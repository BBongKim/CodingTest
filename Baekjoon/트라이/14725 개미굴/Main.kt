// 개미굴
// https://www.acmicpc.net/problem/14725

// 트라이

// 트라이 자료구조를 이용하면 쉽게 풀 수 있었다.

// 1. 트리 구성
// 2. 트리 출력

// TreeMap을 사용하여, 단어순 정렬을 유지하였다.

package Study10

import java.util.TreeMap

class TrieNode(val key: String?) {
    val child = TreeMap<String, TrieNode>()
}

class Trie(val rootKey: String) {
    val root = TrieNode(null)

    fun insert(words: List<String>) {
        var cur = root

        words.forEach { key ->
            if (cur.child[key] == null) cur.child[key] = TrieNode(key)
            cur = cur.child[key]!!
        }
    }
}

object Main3 {
    fun solution(N: Int, info: List<List<String>>) {
        val tries = mutableListOf<Trie>()
        val idxMap = HashMap<String, Int>()

        for (words in info) {
            val rootKey = words.first()

            if (!idxMap.containsKey(rootKey)) {
                val newTrie = Trie(rootKey)
                newTrie.insert(words)

                tries.add(newTrie)
                idxMap[rootKey] = tries.size - 1
            } else {
                tries[idxMap[rootKey]!!].insert(words)
            }
        }

        tries.sortBy { it.rootKey }

        tries.forEach { printTree(it.root, -1) }

        return
    }

    private fun printTree(node: TrieNode, level: Int) {
        if (level >= 0) {
            val sb = StringBuilder()
            repeat(level) { sb.append("--") }
            sb.append(node.key)
            println(sb.toString())
        }

        for (childNode in node.child.values) {
            printTree(childNode, level + 1)
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val info = List(N) { readLine().split(" ").drop(1) }

    Main3.solution(N, info)
}