// 완전탐색 문제
// 예전에 풀었었는데 다시 푸니까 기억이 안남...
// 그냥, 새로운 방법을 찾았다.

// 상,하 움직임은 A부터 빠른지 Z부터 빠른지 간단하게 구하면된다.
// 따라서, 이 문제의 핵심은 좌,우 움직임의 최솟값을 어떻게 구하느냐이다.
// 가장 간단한 방법은 현재 위치까지 앞에서부터 오는게 빠른지, 뒤에서부터 시작하는게 빠른지를 비교하여 계속 최솟값을 갱신하는 방법이다.
// 이렇게 비교하는 이유는 'BBBBAAAAB'와 같은 경우 때문에 뒤부터 시작하는 것을 고려한다.
// 'A'가 연속될 경우가 존재하기 때문에 A 연속 이후의 index를 구한다.
// 앞에서부터 차례대로 i까지 오는 경우는 2 * i + (N - index)
// 뒤부터 시작하는 경우는 2 * (N - index) + i 이다.

var N = 0

fun solution(name: String): Int {
    var answer = 0
    var move = Int.MAX_VALUE
    N = name.length

    for (i in 0 until N) {
        answer += minOf(name[i] - 'A', 'Z' - name[i] + 1)

        var index = i + 1
        while (index < N && name[index] == 'A') index++

        move = minOf(move, 2 * i + (N - index), 2 * (N - index) + i)
    }

    return answer + move
}

fun main() {
    println(solution("BBBBAAAAB"))
}