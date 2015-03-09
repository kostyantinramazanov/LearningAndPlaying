import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversal {

	public static void main(String[] argv) {
		SortTrees.Node tree = SortTrees.createFirstTree();
		Queue<SortTrees.Node> firstLevel = new LinkedList<SortTrees.Node>(
				Arrays.asList(tree));
		traverseInBreadth(firstLevel);

		// traversalInDepth(tree);
		findCommonParent(tree, 3, 1);
	}

	private static void findCommonParent(SortTrees.Node tree, int i, int j) {

		List<SortTrees.Node> path1 = searchPath(tree, i);
		List<SortTrees.Node> path2 = searchPath(tree, j);

		SortTrees.Node commonNode = null;
		Iterator<SortTrees.Node> path1Iterator = path1.iterator();
		Iterator<SortTrees.Node> path2Iterator = path2.iterator();
		while (path1Iterator.hasNext() && path2Iterator.hasNext()) {
			SortTrees.Node n1 = path1Iterator.next();
			SortTrees.Node n2 = path2Iterator.next();

			if (n1.equals(n2)) {
				commonNode = n1;
			} else {
				break;
			}
		}

		System.out.println(commonNode != null ? commonNode.getValue()
				: "No common parent found");
	}

	private static List<SortTrees.Node> searchPath(SortTrees.Node node, int i) {
		List<SortTrees.Node> result = new LinkedList<SortTrees.Node>();

		if (node != null) {
			if (node.getValue() != i) {
				List<SortTrees.Node> subPath = searchPath(node.getLeft(), i);
				if (subPath.isEmpty()) {
					subPath = searchPath(node.getRight(), i);
				}
				if (!subPath.isEmpty()) {
					result.add(node);
					result.addAll(subPath);
				}
			} else {
				result.add(node);
			}
		}
		return result;

	}

	private static void traversalInDepth(SortTrees.Node node) {
		if (node != null) {
			traversalInDepth(node.getLeft());
			System.out.println(node.getValue());
			traversalInDepth(node.getRight());
		}
	}

	private static void traverseInBreadth(Queue<SortTrees.Node> level) {
		Queue<SortTrees.Node> newLevel = new LinkedList<SortTrees.Node>();
		while (!level.isEmpty()) {
			SortTrees.Node node = level.poll();
			if (node != null) {
				System.out.print(String.format("%s ", node.getValue()));
				newLevel.add(node.getLeft());
				newLevel.add(node.getRight());
			}
		}
		System.out.println();
		if (!newLevel.isEmpty()) {
			traverseInBreadth(newLevel);
		}
	}
}
