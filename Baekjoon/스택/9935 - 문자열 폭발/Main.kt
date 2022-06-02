// Stack 문제
// 처음에 Regex로 replace하는 방식을 취했는데, 메모리 초과가 났다. (어차피 N^2으로 시간초과도 났을 것)
// 그래서, 문제 유형을 보니 stack 이길레 O(N * bomb length), 최대 O(N * 36)의 시간복잡도를 가지도록 만들었다.

// 입력 길이, 실행시간 1초 기준
// ~500 -> O(N^3)
// ~1만 -> O(N^2)
// 10만~500만 -> O(N), O(NLogN)

import java.util.*

fun main() {
    val input = readln()
    val bomb = readln()
    val stack = Stack<Char>()

    for (c in input) {
        stack.push(c)

        var i = 0
        var isBomb = true
        for (idx in stack.size - bomb.length until stack.size) {
            if (idx < 0) {
                isBomb = false
                break
            }
            if (stack[idx] != bomb[i++]) {
                isBomb = false
                break
            }
        }

        if (isBomb) {
            repeat(bomb.length) {
                stack.pop()
            }
        }
    }

    val sb = StringBuilder()
    if (stack.isEmpty()) println("FRULA")
    else {
        for (s in stack) sb.append(s)
        println(sb.toString())
    }
}