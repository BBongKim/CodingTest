// 성격 유형 검사하기 - LV1
// https://school.programmers.co.kr/learn/courses/30/lessons/118666

fun solution(survey: Array<String>, choices: IntArray): String {
    val answer = StringBuilder()

    val personality = arrayOf("RT", "CF", "JM", "AN")

    val map = HashMap<Char, Int>()

    // 초기화
    for (p in personality) {
        val leftChar = p[0]
        val rightChar = p[1]
        map[leftChar] = 0
        map[rightChar] = 0
    }

    for (idx in survey.indices) {
        val leftChar = survey[idx][0]
        val rightChar = survey[idx][1]

        when (choices[idx]) {
            1 -> map[leftChar] = map[leftChar]!! + 3
            2 -> map[leftChar] = map[leftChar]!! + 2
            3 -> map[leftChar] = map[leftChar]!! + 1
            4 -> {}
            5 -> map[rightChar] = map[rightChar]!! + 1
            6 -> map[rightChar] = map[rightChar]!! + 2
            7 -> map[rightChar] = map[rightChar]!! + 3
        }
    }

    for (p in personality) {
        val leftChar = p[0]
        val rightChar = p[1]

        if (map[leftChar]!! < map[rightChar]!!) {
            answer.append(rightChar)
        } else {
            answer.append(leftChar)
        }
    }

    return answer.toString()
}

fun main() {
    val ans = solution(arrayOf("AN", "CF", "MJ", "RT", "NA"), intArrayOf(5, 3, 2, 7, 5))
    println(ans)
}