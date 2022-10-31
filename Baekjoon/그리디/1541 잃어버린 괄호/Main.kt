// 잃어버린 괄호
// https://www.acmicpc.net/problem/1541
// 그리디

// 핵심은 덧셈을 먼저하는 것
// 덧셈을 먼저하기 위해, -로 split 하고 나눠진 식들은 모두 더한다.
// 그리고, 더해진 식을 다 빼면 된다. (첫번째 수는 따로 예외 처리)

fun solution(expr: String): Int {
    var answer = Int.MAX_VALUE

    val expression = expr.split("-")

    for (subExpr in expression) {
        val sum = subExpr.split("+").sumOf { it.toInt() }
        if (answer == Int.MAX_VALUE) answer = sum
        else answer -= sum
    }

    return answer
}

fun main() {
    val answer = solution(readln())
    println(answer)
}