public class ReverseList {
	final static class Node {
		int value;
		Node next;

		public Node(int value, Node next) {
			super();
			this.value = value;
			this.next = next;
		}

		public int getValue() {
			return value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node list = generateList1();
		printList(list);
		// printList(reverseList(list, null));
		// Node reversed = reverseListWithLoop(list);
		// printList(reversed);
		// tortoiseAndHareLoopCheck(list);
		// largerLargerLoopCheck(list);
		findElementFromTheTail(list, 3);
	}

	private static void findElementFromTheTail(Node list, int n) {
		Node current = list;
		Node result = list;
		int distance = 0;
		while (current.getNext() != null) {
			if (++distance >= n) {
				result = result.getNext();
			}
			current = current.getNext();
		}
		System.out.print(result.getValue());
	}

	private static void tortoiseAndHareLoopCheck(Node list) {
		Node tortoise = list;
		Node hare = list;
		int counter = 0;
		while (hare.getNext() != null || hare.getNext() != tortoise) {
			counter++;
			if (counter % 2 == 0) {
				tortoise = tortoise.getNext();
			}
			hare = hare.getNext();
			if (tortoise == hare) {
				break;
			}
		}

		System.out.println(counter);
		if (tortoise == hare) {
			System.out.println("LOOP detected");
		} else {
			System.out.println("No LOOP Detected");
		}
	}

	private static void largerLargerLoopCheck(Node list) {
		Node tortoise = list;
		Node hare = list;
		int counter = 0;
		int loopSize = 2;
		while (hare.getNext() != null) {
			if (counter >= loopSize) {
				tortoise = hare;
				loopSize *= 2;
			}
			hare = hare.getNext();
			counter++;
			if (tortoise == hare) {
				break;
			}
		}

		System.out.println(counter);
		if (tortoise == hare) {
			System.out.println("LOOP detected");
		} else {
			System.out.println("No LOOP Detected");
		}
	}

	private static void printList(Node list) {
		System.out.println(list.getValue());
		if (list.getNext() != null) {
			printList(list.getNext());
		}
	}

	private static Node reverseList(Node list, Node prev) {
		if (list != null) {
			Node nextNode = list.getNext();
			list.setNext(prev);
			return reverseList(nextNode, list);
		}
		return prev;
	}

	private static Node reverseListWithLoop(Node list) {
		Node current = list;
		Node tail = null;
		Node prev = null;
		while (current != null) {
			tail = current.getNext();
			current.setNext(prev);
			prev = current;
			current = tail;
		}
		return prev;
	}

	public static Node generateList1() {
		Node node10 = new Node(10, null);
		Node node8 = new Node(8, node10);
		Node node6 = new Node(6, node8);
		Node node4 = new Node(4, node6);
		return new Node(2, node4);
	}

	public static Node generateList2() {
		Node node10 = new Node(10, null);
		Node node8 = new Node(8, node10);
		Node node6 = new Node(6, node8);
		node10.setNext(node6);
		Node node4 = new Node(4, node6);
		return new Node(2, node4);
	}

	public static Node generateList3() {
		// Node node3 = new Node(3, null);
		Node node2 = new Node(2, null);
		Node node1 = new Node(1, node2);
		node2.setNext(node1);
		return node1;
	}

}
