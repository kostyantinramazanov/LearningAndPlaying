package com.kr.automatas;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: cONST
 * Date: 23.11.13
 * Time: 1:04
 * To change this template use File | Settings | File Templates.
 */
public class TaskSolver {
    private final Node a = new Node("A", false);
    private final Node b = new Node("B", false);
    private final Node c = new Node("C", false);
    private final Node d = new Node("D", true);

//    private final static int k = 14;

    {
        final Map<Character, String> aTransitions = new HashMap<Character, String>();
        final Map<Character, String> bTransitions = new HashMap<Character, String>();
        final Map<Character, String> cTransitions = new HashMap<Character, String>();
        final Map<Character, String> dTransitions = new HashMap<Character, String>();

        aTransitions.put('0', "B");
        aTransitions.put('1', "C");
        a.setTransitions(aTransitions);

        bTransitions.put('1', "D");
        b.setTransitions(bTransitions);

        cTransitions.put('0', "D");
        c.setTransitions(cTransitions);

        dTransitions.put('0', "A");
        dTransitions.put('1', "B");
        d.setTransitions(dTransitions);
    }

    public void execute() {
        Automata automata = new Automata(Arrays.asList(a, b, c, d), a);
//        for(int k = 2; k<7; testK(automata, k++));
        testK(automata, 11);
        testK(automata, 12);
        testK(automata, 14);
    }

    private void testK(Automata automata, int k) {
        List<String> words = createWords(k);
        int accepted = 0;
        for (String word : words) {
            try {
                if (automata.acceptWord(word)) {
                System.out.println(String.format("%s is accepted", word));
                    accepted++;
                }
            } catch (Exception e) {
//                System.out.println(String.format("%s is not accepted", word));
            }
        }

        System.out.println(String.format("Accepted words for %d-length language is %d", k, accepted));
    }

    private List<String> createWords(int k) {
        final List<String> result = new LinkedList<String>();
        for (int i = 0; i < Math.pow(2, k); i++) {
            result.add(String.format("%" + Integer.valueOf(k) + "s", Integer.toBinaryString(i)).replace(' ', '0'));
        }

        return result;
    }
}
