// 그리디 문제
// 여벌 체육복을 가져온 학생이 도난당한 경우를 조심하면된다.
// lost와 reserve가 정렬 되었다는 보장이 없어서 정렬이 필요하다.
// 이 중 for문으로 lost와 reserve에 동시에 있을 때는 list에서 제거하는 식으로 만들었는데, set의 subtract연산을 이용하는 것이 더 깔끔하다.

import java.util.*

class Solution {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        var answer = n - lost.size
        var lostList = ArrayList<Int>(lost.toList())
        var reserveList = ArrayList<Int>(reserve.toList())
        
        lostList.sort()
        reserveList.sort()
        
        for (lostStudent in lost) {
            for (reserveStudent in reserve) {
                if (lostStudent == reserveStudent) {                
                    lostList.remove(lostStudent)
                    reserveList.remove(reserveStudent)
                    answer++
                    break
                }
            }
        }
        
        var borrowed = BooleanArray(reserveList.size) { false }
                
        for (lostStudent in lostList) {
            for (i in reserveList.indices) {
                if (borrowed[i]) continue
                
                if (lostStudent - 1 == reserveList[i] || lostStudent + 1 == reserveList[i]) {
                    borrowed[i] = true
                    answer++
                    break
                }
            }
        }
                
        return answer
    }
}