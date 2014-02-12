package com.kr.tasks;

/**
 * Created with IntelliJ IDEA.
 * User: cONST
 * Date: 26.01.14
 * Time: 0:28
 * To change this template use File | Settings | File Templates.
 */
public class LinkTree {
    static class Node {
        private String name;
        private Node[] children;
        private Node brother;

        public Node(String s, Node[] children) {
            this.name = s;
            this.children = children;
            if (children == null) this.children = new Node[0];
        }
    }

    public static void main(String[] args) {
        Node n31 = new Node("31", null);
        Node n32 = new Node("32", null);
        Node n33 = new Node("33", null);
        Node n34 = new Node("34", null);
        Node n35 = new Node("35", null);
        Node n36 = new Node("36", null);

        Node n21 = new Node("21", new Node[]{n31, n32, n33});
        Node n22 = new Node("22", new Node[]{n33, n34, n35});
        Node n23 = new Node("23", new Node[]{n36});

        Node root = new Node("11", new Node[]{n21, n22, n23});

        linkLevels(root, 3);

        printLinks(root);
        printLinks(n21);
        printLinks(n31);
    }

    private static void printLinks(Node root) {
        do {
            System.out.print(root.name + "->");
            root = root.brother;
        } while (root != null);
        System.out.println();
    }

    private static void linkLevels(Node root, int h) {
        Node[] levels = new Node[h];
        dfs(root, levels, 0);
    }

    private static void dfs(Node root, Node[] levels, int level) {
        if (levels[level] != null)
            levels[level].brother = root;
        levels[level] = root;
        for (Node child : root.children) {
            dfs(child, levels, level + 1);
        }
    }
}
