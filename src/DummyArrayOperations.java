public class DummyArrayOperations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] array = ArraySorting.ARRAY;
		
		int n = 8;

		System.out.println(findNthLargest(array, n));

	}

	private static int findNthLargest(int[] array, int n) {
		findInProperWay(array, n);
		return array[n - 1];
	}

	private static void findInProperWay(int[] array, int n) {
		int pass = 0;
		for (int i = 0; i < n; i++) {
			int min = array[i];
			int minIndex = i;
			for (int j = i + 1; j < array.length; j++) {
				pass++;
				if (array[j] < min) {
					min = array[j];
					minIndex = j;
				}
			}
			if (minIndex != i) {
				ArraySorting.swap(array, i, minIndex);
			}
			ArraySorting.print(array);

		}
		System.out.println(pass);
	}

}


// final static class Dequeue{
// int size;
// Node middle;
// Node nodes[];
// Dequeue (int n){
// nodes = new Node[n];
// size = n;
// middle = nodes[n/2];
// }
//
// void tryPut(int n){
// tryPut(n, 0, size);
// }
//
// private void tryPut(int n, int i, int size2) {
// // TODO Auto-generated method stub
//
// }
// }
//
// final static class Node {
// int value;
// Node left;
// Node right;
// }

///*
// * Stupid approach
// * */
//private static void tryPut(int m, int[] buffer, int left, int right) {
//	/*
//	 * Naive approach will take up to N^2 operations
//	for (int i = size; i > start; i--) {
//		if (m > buffer[i - 1]) {
//			shift(buffer, i - 1);
//			buffer[i - 1] = m;
//			break;
//		}
//	}
//	*/
//	int med = left + (right - left) / 2;
//	if (right - left == 1){
//		if(med > 0 && m > buffer[med]){
//			shift(buffer, med);
//			buffer[med] = m;
//		} else if (m > buffer[med]){
//			buffer[med] = m;
//		}
//	} 
//	else {
//		if (m > buffer[med]) {
//			tryPut(m, buffer, med, right);
//		} else if (m < buffer[med]) {
//			tryPut(m, buffer, left, med);
//		}
//	}
//}
//
//private static void shift(int[] buffer, int fromPos) {
//	for (int i = 0; i < fromPos; i++) {
//		buffer[i] = buffer[i + 1];
//	}
//}
