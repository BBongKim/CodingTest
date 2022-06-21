// HashMap 문제
// 중복을 고려해서, 이름마다 (현재 인원 + 1) 값을 해쉬맵에 넣어 놓는다.
// 완주자를 돌면서, 해당 이름마다 값을 1씩 감소시킨다.
// 값이 0 이상인 사람이 답이다.

import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";     
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String p : participant) {
            if (map.containsKey(p)) {
                int tmp = map.get(p);
                map.put(p, tmp + 1);
            } else {
                map.put(p, 1);
            }
        }
        
        for (String c : completion) {    
            int tmp = map.get(c);
            map.put(c, tmp - 1);        
        }
        
        for (String key : map.keySet()) {
            if (map.get(key) > 0) return key;
        }
        
        return null;
    }
}