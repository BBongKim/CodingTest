// 분류에는 BFS 문제라는데 그냥 수학적으로 푸는게 더 직관적인 것 같다.

// B부터 거꾸로 A를 만들면 된다.
// 마지막 자리수가 1이면 10으로 나눠주고, 짝수면 2로 나눈다.
// 만약, 마지막 자리수가 1이 아닌 홀수이면 반복문을 탈출한다.

fun solution(A: Int, B: Int): Int {
    var cur = B
    var cnt = 0

    while (cur != A) {
        if (cur == 0) return -1
        if (cur % 10 == 1) cur /= 10        // 이 부분을 조심해야된다.
        else if (cur % 2 == 0) cur /= 2
        else return -1

        cnt++
    }

    return cnt + 1
}

fun main() {
    val (A, B) = readln().split(" ").map { it.toInt() }

    println(solution(A, B))
}