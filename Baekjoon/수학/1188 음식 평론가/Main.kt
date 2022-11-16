// 음식 평론가
// https://www.acmicpc.net/problem/1188
// 수학

// 코드는 쉬운데, 정답 봐도 증명을 못하겠음.... ㅠㅠ
// https://gona.tistory.com/27
// 이 글 보고 이해했다!

fun solution(N: Int, M: Int): Int {
    val gcd: Int = if (N >= M) getGreatestCommonDivisor(N, M)
    else getGreatestCommonDivisor(M, N)

    return M - gcd
}

fun getGreatestCommonDivisor(a: Int, b: Int): Int {
    if (b == 0) return a
    return getGreatestCommonDivisor(b, a % b)
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }

    println(solution(N, M))
}