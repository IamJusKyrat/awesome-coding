package com.turing.excercise.string;

import com.turing.excercise.TestResultsHelper;

import java.util.Arrays;

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";
        String sum = "";
        int addTrailingZero = 0;
        for (int i = num1.length()-1; i >= 0; i--) {
            int multiplicand = num1.charAt(i) - '0';
            StringBuilder addition = new StringBuilder();
            for(int j = 1; j <= addTrailingZero; j++) {
                addition.append("0");
            }
            addTrailingZero++;

            if (multiplicand != 0) {
                addition.append(multiply(multiplicand, num2));
                sum = add(sum, addition.toString());
            }

        }
        return reverse(sum);
    }

    public String multiply(int m, String str) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            int val = str.charAt(i) - '0';
            int mul = val * m + carry;
            sb.append(mul % 10);
            carry = mul / 10;
        }

        if (carry > 0) {
            sb.append(carry);
        }
        return sb.toString();
    }

    public String add(String sum, String addition) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = 0; i < Math.max(addition.length(), sum.length()); i++) {
            int val1 = sum.length() > i ? sum.charAt(i) - '0' : 0;
            int val2 = addition.length() > i ? addition.charAt(i) - '0': 0;
            int add = val1 + val2 + carry;
            sb.append(add % 10);
            carry = add / 10;
        }

        if (carry > 0) {
            sb.append(carry);
        }
        return sb.toString();
    }

    String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public void run() {
        String[] testcase1 = new String[] {"2", "3"};
        String[] testcase2 = new String[] {"123", "456"};
        String[] testcase3 = new String[] {"98", "9"};
        String[] testcase4 = new String[] {"408", "5"};
        TestResultsHelper.verify("1", "6", multiply(testcase1[0], testcase1[1]));
        TestResultsHelper.verify("2", "56088", multiply(testcase2[0], testcase2[1]));
        TestResultsHelper.verify("3", "882", multiply(testcase3[0], testcase3[1]));
        TestResultsHelper.verify("4", "2040", multiply(testcase4[0], testcase4[1]));
    }

    public static void main(String[] args) {
        new MultiplyStrings().run();
    }
}
