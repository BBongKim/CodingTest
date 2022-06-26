// 완전탐색 문제
// 그냥 직관적인 방법으로 풀면 된다.

data class Person(val idx: Int, var cnt: Int)

class Solution {
    fun solution(answers: IntArray): IntArray {
        val answer = ArrayList<Int>()
        
        val list = arrayOf(Person(1, 0), Person(2, 0), Person(3, 0))
        
        val person1 = intArrayOf(1, 2, 3, 4, 5)
        val person2 = intArrayOf(2, 1, 2, 3, 2, 4, 2, 5)
        val person3 = intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
        
        for (i in answers.indices) {
            if (person1[i % 5] == answers[i]) list[0].cnt++
            if (person2[i % 8] == answers[i]) list[1].cnt++
            if (person3[i % 10] == answers[i]) list[2].cnt++
        }
        
        list.sortWith(compareByDescending<Person> { it.cnt })
        
        val max = list[0].cnt
        
        list.forEach { if(it.cnt == max) answer.add(it.idx) }
                
        return answer.toIntArray()
    }
}