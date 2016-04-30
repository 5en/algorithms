// Given a string of numbers, create a 7 number lottery ticket from the string.
// The 7 numbers in the lottery ticket must be from 1-59 and must be unique.
// All numbers in the string must be used in order.

//Examples:
//  “1234567” -> [[1, 2, 3, 4, 5, 6, 7]]
//  “0123456” -> none []
//  “1223434567” -> [[1 22 34 3 45 6 7] [12 2 34 3 45 6 7], …]

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LotteryTicket {
    public static void main(String[] args) {
        for (List<Integer> ticket : lotteryTicket("1234567")) {
            System.out.println(ticket);
        }

        System.out.println();

        for (List<Integer> ticket : lotteryTicket("0123456")) {
            System.out.println(ticket);
        }

        System.out.println();

        for (List<Integer> ticket : lotteryTicket("1223434567")) {
            System.out.println(ticket);
        }
    }

    // T(N) = T(N-1) + T(N-2)
    public static List<List<Integer>> lotteryTicket(String s) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> ticket = new LinkedList<Integer>();
        lotteryTicketSR(s, 0, result, ticket);

        return result;
    }

    private static void lotteryTicketSR(String s, int start, List<List<Integer>> result,
                                        List<Integer> ticket) {
        if (start == s.length()) {
            if (ticket.size() == 7) {
                result.add(new ArrayList<Integer>(ticket));
            }
            return;
        }

        if (ticket.size() >= 7) {
            return;
        }

        for (int i = start; i < s.length(); i++) {
            int num = Integer.parseInt(s.substring(start, i + 1));
            if (num <= 0 || num > 59) {
                break;
            }

            ticket.add(num);
            lotteryTicketSR(s, i + 1, result, ticket);
            ticket.remove(ticket.size() - 1);
        }
    }
}
