// 주차 요금 계산
// https://school.programmers.co.kr/learn/courses/30/lessons/92341#

// 구현 문제

import kotlin.math.*

fun solution(fees: IntArray, records: Array<String>): IntArray {
    val answer = ArrayList<Int>()

    val baseTime = fees[0]
    val basePrice = fees[1]
    val unitTime = fees[2]
    val unitPrice = fees[3]

    val totalMap = HashMap<String, Int>()
    val numberSet = HashSet<String>()

    // 기록 초기화
    for (inIdx in records.indices) {
        val (inTime, inNumber, cmd1) = records[inIdx].split(" ")
        if (cmd1 == "OUT") continue

        numberSet.add(inNumber)

        if (totalMap[inNumber] == null) totalMap[inNumber] = 0

        var hasOutRecord = false

        for (outIdx in inIdx + 1 until records.size) {
            val (outTime, outNumber, cmd2) = records[outIdx].split(" ")
            if (cmd2 == "IN" || inNumber != outNumber) continue

            val dt = getTimeDiff(outTime, inTime)
            totalMap[inNumber] = totalMap[inNumber]!! + dt
            hasOutRecord = true
            break
        }

        if (!hasOutRecord) {
            val dt = getTimeDiff("23:59", inTime)
            totalMap[inNumber] = totalMap[inNumber]!! + dt
        }
    }

    val numberList = numberSet.toList().sorted()

    for (num in numberList) {
        val extraCharge = ceil((totalMap[num]!! - baseTime).toFloat() / unitTime).toInt() * unitPrice

        val cost = if (extraCharge > 0) basePrice + extraCharge else basePrice
        answer.add(cost)
    }

    return answer.toIntArray()
}

private fun getTimeDiff(outTime: String, inTime: String): Int {
    val (otH, otM) = outTime.split(":").map { it.toInt() }
    val (itH, itM) = inTime.split(":").map { it.toInt() }

    return (otH - itH) * 60 + (otM - itM)
}

fun main() {
    val fees = intArrayOf(180, 5000, 10, 600)
    val records = arrayOf(
        "05:34 5961 IN",
        "06:00 0000 IN",
        "06:34 0000 OUT",
        "07:59 5961 OUT",
        "07:59 0148 IN",
        "18:59 0000 IN",
        "19:09 0148 OUT",
        "22:59 5961 IN",
        "23:00 5961 OUT"
    )

    val ans = solution(fees, records)

    for (a in ans) println(a)
}