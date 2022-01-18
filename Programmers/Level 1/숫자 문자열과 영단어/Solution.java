class Solution {
    public static int solution(String s) {
        int answer = 0;
        String ans = "";
        String tmp = "";

        for (int i = 0; i < s.length(); i++) {
            switch (tmp) {
                case "zero":
                    ans += "0";
                    tmp = "";
                    break;
                case "one":
                    ans += "1";
                    tmp = "";
                    break;
                case "two":
                    ans += "2";
                    tmp = "";
                    break;
                case "three":
                    ans += "3";
                    tmp = "";
                    break;
                case "four":
                    ans += "4";
                    tmp = "";
                    break;
                case "five":
                    ans += "5";
                    tmp = "";
                    break;
                case "six":
                    ans += "6";
                    tmp = "";
                    break;
                case "seven":
                    ans += "7";
                    tmp = "";
                    break;
                case "eight":
                    ans += "8";
                    tmp = "";
                    break;
                case "nine":
                    ans += "9";
                    tmp = "";
                    break;
                default:
                    break;
            }

            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                ans += s.charAt(i);
                continue;
            }

            tmp += s.charAt(i);
        }

        switch (tmp) {
            case "zero":
                ans += "0";
                tmp = "";
                break;
            case "one":
                ans += "1";
                tmp = "";
                break;
            case "two":
                ans += "2";
                tmp = "";
                break;
            case "three":
                ans += "3";
                tmp = "";
                break;
            case "four":
                ans += "4";
                tmp = "";
                break;
            case "five":
                ans += "5";
                tmp = "";
                break;
            case "six":
                ans += "6";
                tmp = "";
                break;
            case "seven":
                ans += "7";
                tmp = "";
                break;
            case "eight":
                ans += "8";
                tmp = "";
                break;
            case "nine":
                ans += "9";
                tmp = "";
                break;
            default:
                break;
        }

        answer = Integer.parseInt(ans);
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("one4seveneight"));
    }
}