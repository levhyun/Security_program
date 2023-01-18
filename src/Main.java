import java.util.Scanner;
class Security {
    static char[] englishKey = {
            '`'/* 0 */,'~'/* 1 */,'!'/* 2 */,'@'/* 3 */,
            '#'/* 4 */,'$'/* 5 */,'%'/* 6 */,'^'/* 7 */,
            '&'/* 8 */,'*'/* 9 */,'('/* 10 */,')'/* 11 */,
            '-'/* 12 */,'_'/* 13 */,'+'/* 14 */,'='/* 15 */,
            '|'/* 16 */,'['/* 17 */,']'/* 18 */,'{'/* 19 */,
            '}'/* 20 */,';'/* 21 */,':'/* 22 */,','/* 23 */,
            '.'/* 24 */,'/'/* 25 */
    };

    static char[] numberKey = {
            'q'/* 0 */,'w'/* 1 */,'e'/* 2 */, 'r'/* 3 */,
            't'/* 4 */, 'y'/* 5 */, 'u'/* 6 */,'i'/* 7 */,
            'o'/* 8 */,'p'/* 9 */
    };

    static int englishS = 97;
    static int numS = 48;

    Security() {
        System.out.println("Security 실행");
    }

    static void Information() {
        System.out.println("암호화 명령어 : Encryption()");
        System.out.println("[암호화 가능 문자]");
        System.out.println("a b c d e f g h i j k l m n o p q r s t u v w x y z\n0 1 2 3 4 5 6 7 8 9\n");
        System.out.println("복호화 명령어 : Decryption()");
        System.out.println("[복호화 가능 문자]");
        System.out.println("` ~ ! @ # $ % ^ & * ( ) - _ + = | [ ] { } ; : , . /\nq w e r t y u i o p\n");
    }

    // 암호화
    static String Encryption(String text) {
        String result = ""; // 암호화 결과값을 담을 변수

        for (int i = 0; i < text.length(); i++){
            char c = (char) text.charAt(i);
            int cNumber = (int) c;

            // 받은 문자가 a ~ z 라면 특수문자로 암호화
            if (cNumber >= 97 && cNumber <= 122) {
                cNumber = cNumber - englishS;
                // 97만큼 빼서 0 ~ 25 추출
                result += englishKey[cNumber];
            }

            // 받은 문자가 0 ~ 9 라면 영어로 암호화
            else if (cNumber >= 48 && cNumber <= 57) {
                cNumber = cNumber - numS;
                // 48만큼 빼서 0 ~ 9 추출
                result += numberKey[cNumber];
            }

            // 받은 문자가 특수 문자라면 암호화 불가
            else {
                System.out.println("암호화 불가");
                return "";
            }
        }

        return result;
    }

    // 복호화
    static String Decryption(String text){
        String result = ""; // 복호화 결과값을 담을 변수

        for(int i = 0; i < text.length(); i++){
            char c = (char) text.charAt(i);
            int cNumber = (int) c;
            int index = 0;

            // 받은 문자가 a ~ z 라면 0 ~ 9로 복호화
            if (cNumber >= 97 && cNumber <= 122) {
                for (int j = 0; j < numberKey.length; j++) {
                    if (cNumber == (int) numberKey[j]) {
                        index = j;
                        break;
                    }
                }

                index += numS;
                // 48만큼 더해서 0 ~ 9 추출
                result += (char)index;
            }

            // 받은 문자가 0 ~ 9 라면 복호화 불가
            else if (cNumber >= 48 && cNumber <= 57) {
                System.out.println("복호화 불가");
                return "";
            }

            // 받은 문자가 특수 문자라면 a ~ z로 복호화
            else {
                for (int j = 0; j < englishKey.length; j++) {
                    if (cNumber == (int) englishKey[j]) {
                        index = j;
                        break;
                    }
                }

                index += englishS;
                // 97만큼 더해서 97(a) ~ 122(z) 추출
                result += (char)index;
            }
        }

        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        Security security = new Security();

        Scanner scanner = new Scanner(System.in);
        String text;
        int select;

        while(true) {
            System.out.println("1. 암호화");
            System.out.println("2. 복호화");
            System.out.println("3. 도움말");
            System.out.println("4. 종료");
            select = scanner.nextInt();

            if (select == 1) {
                System.out.printf("암호화 할 텍스트를 입력 : ");
                text = scanner.next();
                System.out.println("[암호화 결과]");
                System.out.println(text + " -> " + security.Encryption(text) + "\n");
            }

            else if (select == 2) {
                System.out.printf("복호화 할 텍스트를 입력 : ");
                text = scanner.next();
                System.out.println("[복호화 결과]");
                System.out.println(text + " -> " + security.Decryption(text) + "\n");
            }

            else if (select == 3) {
                security.Information();
            }

            else if (select == 4) {
                break;
            }
        }

        return;
    }
}