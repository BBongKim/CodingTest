// 오큰수
// https://www.acmicpc.net/problem/17298

// 스택

// 모노톤 스택을 이용하면 된다.
// 앞으로 삽입할 숫자 x가 stack top 보다 크다면, 계속 pop을 해준다.
// 그리고, pop이 되는 수의 오큰수는 앞으로 x로 기록하면 된다.

package Study16

import java.util.*

data class Element(val idx: Int, val num: Int)

object Main2 {
    fun solution(N: Int, list: IntArray) {
        val stack = Stack<Element>()
        val answer = IntArray(N) { -1 }

        for (i in list.indices) {
            while (stack.isNotEmpty() && stack.peek().num < list[i]) {
                answer[stack.pop().idx] = list[i]
            }

            stack.push(Element(i, list[i]))
        }

        val sb = StringBuilder()
        answer.forEach { sb.append("$it ") }
        println(sb.toString())
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val list = readLine().split(" ").map { it.toInt() }.toIntArray()
    Main2.solution(N, list)
}