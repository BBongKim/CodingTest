// 정렬 문제
// 문제의 핵심은 h번 이상 논문이 h개 이상인지만 확인하면 된다.
// 오름차순 내림차순 상관 없는데, 내림차순이 더 편하다.
// 현재 값이 현재 자리(i)보다 같거나 작으면 i가 정답이다.
// 이는 i 일때, i를 포함해서 i 앞에 citations[i]번 이상인 것이 citations[i]개 이상 있는지 확인하는 것이다.
// 아 헷갈려...  
// Ex.) 20 19 18 17 16 15 11 5 4 3

class Solution {
    fun solution(citations: IntArray): Int {
        var answer = 0
        
        // 6 5 3 1 0
        citations.sortDescending()

        for (i in citations.indices) {
            if (citations[i] <= i ) return i
        }
    
        return citations.size
    }
}