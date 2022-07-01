// 이분탐색 문제
// 어떤 값을 이분 탐색할지, 어떤 값을 기준 left, right를 옮길지 정하기 어려웠다.
// 다른 분 풀이를 보니, 바위 사이의 거리의 최솟값을 탐색으로 찾고, 이 최솟값을 가지기 위해 지운 바위의 개수를 가지고 left, right를 옮겼다.
// 바위를 지우는 법은 현재 돌과 이전 돌의 거리차이가 최솟값보다 작다면 지운다. 만약, 크거나 같다면 이전 돌(prev = rock)을 현재 돌로 지정한다. 
// 만약 해당 최솟값을 가지기 위해 지운 바위의 수가 n보다 크다면 최솟값 기준을 줄여야 한다. 따라서 right를 (mid - 1)로 옮긴다.
// 만약 n보다 같거나 작다면, 우선 답의 후보로 지정해두고(해당 문제는 답이 될 수 있는 가장 큰 n을 찾는 것이기 때문에 max 연산을 한다.), left를 (mid + 1)로 옮긴다.

class Solution {
    fun solution(distance: Int, rocks: IntArray, n: Int): Int {
        var answer = -1
        val rockList = rocks.toList().sorted()
        
        var left = 1
        var right = distance
       
        while (left <= right) {
             val mid = (left + right) / 2
             
             // 이전 돌의 시작은 시작점부터
             var prev = 0
             var remove = 0
             for (rock in rockList) {
                 if (rock - prev >= mid) prev = rock
                 else remove++
             }
             
             if (distance - prev < mid) remove++
             
             if (remove > n) right = mid - 1
             else {
                 answer = maxOf(answer, mid)
                 left = mid + 1
             }
        }
        
        return answer
    }
}
