package com.kr.algorithms.trees;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: cONST
 * Date: 05.02.14
 * Time: 0:04
 * To change this template use File | Settings | File Templates.
 */
public class RBTree implements Serializable {
    private class Node {
        private int value;
        private Node left;
        private Node right;
        private boolean isRed;

        public Node(int val, boolean b) {
            this.value = val;
            isRed = b;
            this.left = null;
            this.right = null;
        }

        public void writeObject(ObjectOutputStream os) throws IOException {
            os.writeInt(value);
            if (left == null) os.writeInt(0);
            else left.writeObject(os);

            if (right == null) os.writeInt(0);
            else right.writeObject(os);
        }

        public void readObject(ObjectInputStream is) throws IOException {
            if (is.available() > 0) {
                int leftVal = is.readInt();
                if (leftVal == 0) left = null;
                else {
                    left = new Node(leftVal, false);
                    left.readObject(is);
                }
            }

            if(is.available() > 0){
                int rightVal = is.readInt();
                if (rightVal == 0) right = null;
                else {
                    right = new Node(rightVal, false);
                    right.readObject(is);
                }
            }
        }
    }

    private Node root;

    public void put(int val) {
        root = put(root, val);
        root.isRed = false;
    }

    public void delete(int val) {
        root.isRed = true;
        root = delete(root, val);
    }

    private Node put(Node root, int val) {
        Node result = root;
        if (result == null) return new Node(val, true);

        if (val < result.value) {
            result.left = put(result.left, val);
        } else if (val > result.value) {
            result.right = put(result.right, val);
        }

        if (result.right != null && result.right.isRed) result = rotateLeft(root);
        if (result.left != null && result.left.isRed && result.left.left != null && result.left.left.isRed)
            result = rotateRight(result);
        if (result.left != null && result.left.isRed && result.right != null && result.right.isRed)
            result = flipColors(result);

        return result;
    }

    private Node delete(Node root, int val) {
        Node result = root;
        if (result == null) return null;

        if (result.isRed && result.left != null && !result.left.isRed && result.right != null && !result.right.isRed) {
            result = flipColors(result);
        }

        if (val == root.value) {
            if (root.right == null) {
                return root.left;
            }

            result = root.right;
            while (result.left != null) {
                result = result.left;
            }
            result.right = delete(root.right, result.value);
            result.left = root.left;
            return result;
        }

        if (val < result.value) {
            result.left = delete(result.left, val);
        }
        if (val > root.value) {
            result.right = delete(result.right, val);
        }
        if (result.right != null && result.right.isRed) result = rotateLeft(result);
        if (result.left != null && result.left.isRed && result.left.left != null && result.left.left.isRed)
            result = rotateRight(result);
        if (result.left != null && result.left.isRed && result.right != null && result.right.isRed)
            result = flipColors(result);
        return result;
    }

    private Node rotateLeft(Node root) {
        Node result = root.right;
        root.right = result.left;
        result.left = root;
        result.isRed = root.isRed;
        root.isRed = true;
        return result;
    }

    private Node rotateRight(Node root) {
        Node result = root.left;
        root.left = result.right;
        result.right = root;
        result.isRed = root.isRed;
        root.isRed = true;
        return result;
    }

    private Node flipColors(Node root) {
        root.left.isRed = !root.left.isRed;
        root.right.isRed = !root.right.isRed;
        root.isRed = !root.isRed;
        return root;
    }

    public void dfs(Node root) {
        if (root != null) {
            dfs(root.left);
            System.out.println(root.value);
            dfs(root.right);
        }
    }

    public void bfs(Node root) {
        Queue<Node> queue = new LinkedList<Node>();

        if (root != null) {
            queue.add(root);
            do {
                root = queue.poll();
                System.out.println(root.value);
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            } while (queue.peek() != null);
        }
    }

    private final static int CHARS_PER_NODE = 3;

    public void printTree() {
        int offset = CHARS_PER_NODE;
        Node ptr = root;
        while (ptr.left != null) {
            offset = offset * 2 + CHARS_PER_NODE;
            ptr = ptr.left;
        }

        Queue<Node> thisLevel = new LinkedList<Node>();
        thisLevel.add(root);

        do {
            Queue<Node> nextLevel = new LinkedList<Node>();
            int prevOffset = offset;
            offset = (prevOffset - CHARS_PER_NODE) / 2;
            for (int i = 0; i < offset; i++, System.out.print(" ")) ;

            while (thisLevel.peek() != null) {
                Node node = thisLevel.poll();
                String val = String.format("%1$-2d", node.value).concat(node.isRed ? "r" : "b");
                System.out.print(val);
                for (int i = 0; i < prevOffset; i++, System.out.print(" ")) ;
                if (node.left != null) nextLevel.add(node.left);
                if (node.right != null) nextLevel.add(node.right);
            }
            thisLevel = nextLevel;
            System.out.println();
        } while (thisLevel.peek() != null);
    }

    private void writeObject(ObjectOutputStream os) throws IOException {
        if (root != null)
            root.writeObject(os);
        else os.writeInt(0);
    }

    private void readObject(ObjectInputStream is) throws IOException {
        if (is.available() > 0) {
            int val = is.readInt();
            if (val != 0) {
                root = new Node(val, false);
                root.readObject(is);
            }
        }
    }

    public static void main(String[] args) {
        int[] src = new int[]{1, 2, 33, 44, 55, 66, 777};
        RBTree tree = new RBTree();
        for (int i : src) {
            tree.put(i);
        }
        tree.printTree();
        int[] seqToRemove = new int[]{1};
        for (int i : seqToRemove) {
            tree.delete(i);
            tree.printTree();
        }

        try {
            new ObjectOutputStream(new FileOutputStream("C:\\zzz.txt")).writeObject(tree);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try {
            RBTree tree2 = (RBTree) new ObjectInputStream(new FileInputStream("C:\\zzz.txt")).readObject();
            tree2.printTree();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}

