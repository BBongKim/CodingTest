// 양궁 대회
// https://school.programmers.co.kr/learn/courses/30/lessons/92342

// 완전탐색

// n이 최대 10이기 때문에 완탐 가능
// 기본적으로 어피치의 화살보다 1개 많은 경우를 계속 dfs로 탐색함
// 예외적으로, 0점 자리에 남은 모든 화살이 맞는 경우를 고려함 (0,0,0,0,0,0,0,0,3,4,3) 테스트 케이스 때문에

var max = 0
var apeachSum = 0
var lionSum = 0
var ansList = IntArray(11) { -1 }

fun dfs(leftArrows: Int, info: IntArray, visited: MutableList<Boolean>, scoreList: MutableList<Int>) {
    if (leftArrows == 0) {
        if (ansList.contentEquals(scoreList.toIntArray())) return

        val dx = lionSum - apeachSum

        if (dx > max) {
            ansList = scoreList.toIntArray()
            max = dx
            return
        }

        if (dx == max) {
            for (idx in 10 downTo 0) {
                if (scoreList[idx] > ansList[idx]) {
                    ansList = scoreList.toIntArray()
                    max = dx
                } else if (scoreList[idx] < ansList[idx]) break
            }
        }
        return
    }

    for (i in 0 until 11) {
        if (visited[i]) continue
        if (i != 10 && leftArrows < info[i] + 1) continue

        visited[i] = true
        lionSum += (10 - i)
        if (info[i] > 0) apeachSum -= (10 - i)

        if (i == 10) {
            scoreList[i] = leftArrows
            dfs(0, info, visited, scoreList)
            scoreList[i] = 0
        }

        scoreList[i] = info[i] + 1

        dfs(leftArrows - (info[i] + 1), info, visited, scoreList)

        scoreList[i] = 0
        if (info[i] > 0) apeachSum += (10 - i)
        visited[i] = false
        lionSum -= (10 - i)
    }
}

fun solution(n: Int, info: IntArray): IntArray {
    val visited = MutableList(11) { false }
    val scoreList = MutableList(11) { 0 }

    for (idx in info.indices) {
        if (info[idx] > 0) apeachSum += (10 - idx)
    }

    dfs(n, info, visited, scoreList)

    return if (max == 0) intArrayOf(-1)
    else ansList
}

fun main() {
    val n = 1
    val info = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    val ans = solution(n, info)

    for (a in ans) print(a)
}