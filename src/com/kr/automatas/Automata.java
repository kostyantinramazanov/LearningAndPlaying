package com.kr.automatas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cONST
 * Date: 23.11.13
 * Time: 0:38
 * To change this template use File | Settings | File Templates.
 */
public class Automata {

    private Node start;
    private Node currentState;
    private final Map<String, Node> nodes = new HashMap<String, Node>();

    public Automata(List<Node> nodes, Node start) {
        for (Node node : nodes) {
            this.nodes.put(node.getName(), node);
        }
        this.start = start;
        currentState = start;
    }

    public void acceptCharacter(Character a) throws Exception {
        String newNode = currentState.getTransitions().get(a);
        if (newNode != null) {
            currentState = nodes.get(newNode);
        } else {
            throw new Exception(String.format("No transition from %s by symbol %s", currentState.getName(), a));
        }
    }

    public boolean acceptWord(String word) throws Exception {
        reset();
        for(Character c : word.toCharArray()){
            acceptCharacter(c);
        }
        return currentState.isFinal();
    }

    public void reset() {
        currentState = start;
    }
}
