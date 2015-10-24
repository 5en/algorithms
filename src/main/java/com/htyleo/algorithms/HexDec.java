package com.htyleo.algorithms;

public class HexDec {
    public static void main(String[] args) {
        System.out.println(hex2dec("A")); // 10
        System.out.println(hex2dec("1a")); // 26
        System.out.println(hex2dec("ff")); // 255

        System.out.println(dec2hex(10));
        System.out.println(dec2hex(26));
        System.out.println(dec2hex(255));
    }

    public static int hex2dec(String hex) {
        int dec = 0;
        int multiple = 1;

        for (int i = hex.length() - 1; i >= 0; i--) {
            int valCh = 0;

            char ch = hex.charAt(i);
            if (ch >= '0' && ch <= '9') {
                valCh = ch - '0';
            } else if (ch >= 'a' && ch <= 'z') {
                valCh = ch - 'a' + 10;
            } else if (ch >= 'A' && ch <= 'Z') {
                valCh = ch - 'A' + 10;
            } else {
                throw new RuntimeException("invalid hex string");
            }

            dec += valCh * multiple;
            multiple *= 16;
        }

        return dec;
    }

    public static String dec2hex(int dec) {
        if (dec == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (dec != 0) {
            int remainder = dec % 16;
            dec /= 16;

            char ch = 0;
            if (remainder >= 0 && remainder <= 9) {
                ch = (char) ('0' + remainder);
            } else {
                ch = (char) ('A' + remainder - 10);
            }

            sb.insert(0, ch);
        }

        return sb.toString();
    }
}
