package com.kr.automatas;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cONST
 * Date: 23.11.13
 * Time: 0:33
 * To change this template use File | Settings | File Templates.
 */
public class Node {
    private String name;
    private Map<Character, String> transitions;
    private boolean aFinal;

    public Node(String name, boolean isFinal) {
        this.name = name;
        this.aFinal = isFinal;
    }

    public String getName() {
        return name;
    }

    public Map<Character, String> getTransitions() {
        return transitions;
    }

    public void setTransitions(Map<Character, String> transitions) {
        this.transitions = transitions;
    }

    public boolean isFinal() {
        return aFinal;
    }
}
