// 배열을 이용한 풀이법
// 정렬 후, 서로 비교하다가 다른 값이 나오면 출력하면된다.
// 만약, 중간에 다른 값이 없으면 마지막 값이 답이다.

import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";     
        int idx = 0;
        
        Arrays.sort(participant);
        Arrays.sort(completion);
            
        for (int i = 0; i < completion.length; i++) {     
            if(!completion[i].equals(participant[i])) return participant[i];
        }
        
        return participant[participant.length - 1];
    }
}