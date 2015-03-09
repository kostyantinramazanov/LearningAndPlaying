import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SortTrees {

	public static class Node {
		int value;
		private Node left;
		private Node right;

		public Node(int value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
		// Getters and setters are not needed for now - so they're skipped

		public int getValue() {
			return value;
		}

		public Node getLeft() {
			return left;
		}

		public Node getRight() {
			return right;
		}
		
				
	}

	public static void main(String argv[]) {
		// Initial Data
		Node firstTree = createFirstTree();
		Node secondTree = createSecondTree();
		int N = 10;

		List<Integer> result = new SortTrees().processTrees(firstTree, secondTree, N);

		//Print out result
		for (Integer i : result) {
			System.out.println(i);
		}
	}

	public List<Integer> processTrees(Node t1, Node t2, Integer amount) {
		// As far as I've understood the task, we need to find Nodes with common values.
		// There was nothing told about Nodes with common "leaves".
		// So, I'll just put the trees into two sets and then find their intersection.
		
//		Set<Integer> firstTreeElements = treeToSet(t1);
//		Set<Integer> secondTreeElements = treeToSet(t2);

		List<Integer> firstTreeElements = treeToSet(t1);
		List<Integer> secondTreeElements = treeToSet(t2);
		
		Set<Integer> commonElements = new HashSet<Integer>(firstTreeElements);
		commonElements.retainAll(secondTreeElements);
		
		List<Integer> commonValuesList = new LinkedList<Integer>(commonElements);
//		Collections.sort(commonValuesList);
		return commonValuesList.subList(
				0,
				amount < commonValuesList.size() ? amount : commonValuesList
						.size());
	}

	private List<Integer> treeToSet(Node node) {
//		Set<Integer> result = new HashSet<Integer>();
		List <Integer> result = new LinkedList<Integer>();

		if (node != null) {
			result.addAll(treeToSet(node.left));
			result.addAll(treeToSet(node.right));
			result.add(node.value);
		}
		return result;
	}

	
	// Fill Initial Trees with Dummy data
	public static Node createFirstTree() {
		Node n1 = new Node(1, null, null);
		Node n3 = new Node(3, null, null);
		Node n2 = new Node(2, n1, n3);
		Node n5 = new Node(5, null, null);
		Node n4 = new Node(4, n2, n5);

		Node n8 = new Node(8, null, null);
		Node n10 = new Node(10, null, null);
		Node n9 = new Node(9, n8, n10);

		return new Node(6, n4, n9);
	}

	public static Node createSecondTree() {
		Node n1 = new Node(1, null, null);
		Node n3 = new Node(3, null, null);
		Node n2 = new Node(2, n1, n3);

		Node n10 = new Node(10, null, null);
		Node n12 = new Node(12, null, null);
		Node n11 = new Node(11, n10, n12);
		return new Node(8, n2, n11);
	}
}
