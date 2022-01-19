import java.util.HashMap;

class Solution {
    static HashMap<String, String> users = new HashMap<>();

    public static String[] solution(String[] record) {
        String[] answer;
        int cnt = 0;

        for (int i = 0; i < record.length; i++) {
            String[] r = record[i].split(" ");

            String cmd = r[0];
            String uid = r[1];
            String name;

            if(cmd.equals("Leave")) continue;
            else if(cmd.equals("Change")) cnt++;

            name = r[2];
            users.put(uid, name);
        }

        answer = new String[record.length - cnt];
        int idx = 0;

        for(int i = 0; i < record.length; i++) {
            if(record[i].charAt(0) == 'L') answer[idx++] = users.get(record[i].split(" ")[1]) + "님이 나갔습니다.";
            else if(record[i].charAt(0) == 'E') answer[idx++] = users.get(record[i].split(" ")[1]) + "님이 들어왔습니다.";
        }

        return answer;
    }


    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] answer;
        answer = solution(record);

        for (String a : answer) {
            System.out.println(a);
        }
    }
}