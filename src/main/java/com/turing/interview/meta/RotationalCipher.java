package com.turing.interview.meta;

public class RotationalCipher {
    // Add any helper functions you may need here
    String rotationalCipher(String input, int rotationFactor) {
        char[] charArr = input.toCharArray();
        for (int i = 0; i < charArr.length ; i++) {
            char value = charArr[i];
            if(isAlphanumeric(value)) {
                int modulo = 0;char base = '0';
                if (isDigit(value)) {
                    modulo = 10;base = '0';
                } else {
                    modulo = 26;base = isCapital(value) ? 'A' : 'a';
                }
                charArr[i] = (char) (base + ((value + rotationFactor - base) % modulo));
            }
        }
        return String.valueOf(charArr);
    }

    boolean isAlphanumeric(char c) {
        return (48 <= c && c <=57) || (65 <= c && c <= 90) || (97 <= c && c <= 122);
        //return Character.isDigitOrLetter(c);
    }

    boolean isCapital(char c) {
        return (65 <= c && c <= 90);
    }

    boolean isDigit(char c) {
        return (48 <= c && c <=57);
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;
    void check(String expected, String output) {
        boolean result = (expected.equals(output));
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printString(expected);
            System.out.print(" Your output: ");
            printString(output);
            System.out.println();
        }
        test_case_number++;
    }
    void printString(String str) {
        System.out.print("[\"" + str + "\"]");
    }

    public void run() {
        String input_1 = "All-convoYs-9-be:Alert1.";
        int rotationFactor_1 = 4;
        String expected_1 = "Epp-gsrzsCw-3-fi:Epivx5.";
        String output_1 = rotationalCipher(input_1, rotationFactor_1);
        check(expected_1, output_1);

        String input_2 = "abcdZXYzxy-999.@";
        int rotationFactor_2 = 200;
        String expected_2 = "stuvRPQrpq-999.@";
        String output_2 = rotationalCipher(input_2, rotationFactor_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new RotationalCipher().run();
    }
}
