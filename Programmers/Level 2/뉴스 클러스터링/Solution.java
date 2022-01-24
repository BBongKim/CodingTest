import java.util.ArrayList;

class Solution {
    public static int solution(String str1, String str2) {
        int answer = 0;

        if(str1.equalsIgnoreCase(str2)) return 65536;

        ArrayList<String> A = new ArrayList<>();
        ArrayList<String> B = new ArrayList<>();

        if (str1.length() % 2 != 0) str1 += " ";
        if (str2.length() % 2 != 0) str2 += " ";

        for(int i = 0; i < str1.length() - 1; i++) {
            String tmp = str1.substring(i, i + 2);
            if (!tmp.matches("[a-zA-Z]{2,}")) continue;
            A.add(tmp.toLowerCase());
        }

        for(int i = 0; i < str2.length() - 1; i++) {
            String tmp = str2.substring(i, i + 2);
            if (!tmp.matches("[a-zA-Z]{2,}")) continue;
            B.add(tmp.toLowerCase());
        }

        // A+B
        ArrayList<String> D = new ArrayList<>(A);
        D.addAll(B);

        // A^B
        ArrayList<String> C = new ArrayList<>();
        for(String b : B) {
            if(A.contains(b)) {
                A.remove(b);
                C.add(b);
            }
        }

        // A U B = A+B - A^B
        for(String c : C) D.remove(c);

        double j = (double) C.size() / D.size();

        answer = (int) (j * 65536);

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("E=M*C^2", "e=m*c^2"));
    }
}