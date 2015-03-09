import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ArraySorting {

	public static final int[] ARRAY = { 11, 5, 8, 12, 7, 10, 9, 6, 2, 20, 4, 13};
	public static final int[] ARRAY1 = { 11, 5, 8, 12, 7, 10, 9};
	public static final int[] ARRAY2 = { 6, 2, 20, 4, 13, 17};

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = ARRAY;
		
//		insertionSort(array);
//		bubbleSort(array);
//		coctailSort(array);
//		shellSort(array);
//		radixSort(array);
		
//		mergeSort(array, 0, array.length-1);
//		print(array);
//		mergeSort(ARRAY1, 0, ARRAY1.length-1);
//		mergeSort(ARRAY2, 0, ARRAY2.length-1);
//		array = merge(ARRAY1, ARRAY2, 0, ARRAY1.length-1, 0, ARRAY2.length-1);
//		print(array);
		
		quickSort(array, 0, array.length-1);
		print(array);
	}


	private static void quickSort(int[] array, int left, int right) {
		if(left<right){
			int pivotPosition = pivot(array, left, right);
			quickSort(array, left, pivotPosition-1);
			quickSort(array, pivotPosition+1, right);
		}
		
	}


	private static int pivot(int[] array, int left, int right) {
		int pivotValue = array[left];
		int pivotPosition = left;
		System.out.println(array[pivotPosition]);
		for(int i=left+1; i<=right; i++){
			if(array[i] < pivotValue){
				pivotPosition++;
				swap(array, pivotPosition, i);
			}
		}
		swap(array, pivotPosition, left);
		print(array);
		return pivotPosition;
	}


	private static void mergeSort(int[] array, int left, int right) {
		int mediana = (int) ((left+right)/2);
		if(left<right){
			mergeSort(array, left, mediana);
			mergeSort(array, mediana+1, right);
			int[] result = merge(array, array, left, mediana, mediana+1, right);
			
			for(int i=0; i<result.length; i++){
				array[left+i] = result[i];
			}
		}
	}

	private static int[] merge(int[] arr1, int[] arr2, int arr1Start, int arr1End, int arr2Start, int arr2End) {
		int result[] = new int[arr1End - arr1Start + arr2End - arr2Start + 2];
		
		int i = 0;
		int rightPtr = arr2Start;
		while (arr1Start <= arr1End && rightPtr <= arr2End) {
			if (arr1[arr1Start] < arr2[rightPtr]) {
				result[i++] = arr1[arr1Start++];
			} else {
				result[i++] = arr2[rightPtr++];
			}
		}
		for (; arr1Start <=arr1End ; arr1Start++) {
			result[i++] = arr1[arr1Start];
		}
		for (; rightPtr <=arr2End; rightPtr++){
			result[i++] = arr2[rightPtr];
		}
		
		return result;
	}

	private static void radixSort(int[] array2) {
		List<Integer> array = new LinkedList<Integer>();
		for(int i : array2) array.add(i);

		int digitPosition = 1;
		List<Integer> []buckets = new List[10];
		for(int i = 0;i<buckets.length; i++){ buckets[i]=new LinkedList<Integer>();}
		
		for(int i=0; i<2; i++){
			for(Integer element : array){
				int bucketNumber = (element/digitPosition) % 10;
				buckets[bucketNumber].add(element);
			}

			//Combine arrays
			array.clear();
			for(List<Integer> bucket : buckets){
				print(bucket);
				array.addAll(bucket);
				bucket.clear();
			}
			//Shift to the next digit
			digitPosition*=10;
		}
		for(int i = 0; i<array2.length; i++){
			array2[i] = array.get(i);
		}
	}

	private static void print(List<Integer> array2) {
		for(Integer i : array2){
			System.out.print(String.format("%d ", i));
		}
		System.out.println();
	}

	public static int[] shellSort(int[] array) {
		int gap = 1;
		while (gap < array.length)
			gap = 3 * gap + 1;
		System.out.println(String.format("Gap = %d", gap));
		print(array);
		while (gap > 0) {
			gap = gap / 3;
			for (int pairSetNumber = 0; pairSetNumber < gap; pairSetNumber++) { // pairs set number 
				for (int pairSecond = gap + pairSetNumber; pairSecond < array.length; pairSecond += gap) { // walk throw pairs 0,gap; 1,gap+1
					int key = array[pairSecond];
					int pairFirst = pairSecond - gap; // first pair element
					while (pairFirst >= 0 && array[pairFirst] > key) {
						System.out.println(String.format("Gap = %d, PairsSetNumber = %d, PairFirst = %d, PairSecond = %d a[first] = %d, a[second] = %d", gap, pairSetNumber, pairFirst, pairSecond, array[pairFirst], array[pairSecond]));
						print(array);
						array[pairFirst + gap] = array[pairFirst];
						pairFirst -= gap;
					}
					array[pairFirst + gap] = key;
					// -> invariant: array[0,h,2*h..j] is sorted
				}
			}
			// ->invariant: each h-sub-array is sorted
		}
		return array;
	};
	

	/**
	 * @param array
	 */
	private static void bubbleSort(int[] array) {
		boolean isSwap = true;
		//Make N-1 passes or while all elements are not swapped
		for(int i = 0; i<array.length-1 && isSwap; i++){
			isSwap=false;
			// Make N-i passes and compare NEIGHBOUR elements
			for(int j = i+1; j<array.length; j++){
				if(array[j-1]>array[j]){
					swap(array, j, j-1);
					isSwap = true;
				}
			}
		}
	}

	/**
	 * @param array
	 */
	private static void coctailSort(int[] array) {
		int left = 1;
		int right = array.length-1;
		int last = right;
		while(left<=right){
			for(int i = right; i>=left; i--){
				System.out.println(String.format("Decrease loop: array[%d] = %d array[%d-1] = %d", i, array[i], i, array[i-1]));
				if(array[i]<array[i-1]){
					swap(array, i, i-1);
					last=i;
					System.out.println(String.format("Swap done last = %d", last));
				}
			}
			left=last+1;
			print(array);
			for(int i = left; i<=right; i++){
				System.out.println(String.format("Increase loop: array[%d] = %d array[%d-1] = %d", i, array[i], i, array[i-1]));
				if(array[i]<array[i-1]){
					swap(array, i, i-1);
					last=i;
					System.out.println(String.format("Swap done last = %d", last));
				}
			}
			right = last-1;
		}
	}
	
	static void swap(int[] array, int i, int j) {
		if (i != j) {
			array[j] ^= array[i];
			array[i] ^= array[j];
			array[j] ^= array[i];
		}
	}

	private static void insertionSort(int[] array) {
		int []workArray = array;
		// Assume, that [0] is the lowest element
		// So, the left side of an array is sorted
		for(int i = 1; i<array.length; i++){
			int location = i-1;
			int el = workArray[i];
			// Compare new element with each element from sorted side
			while(location >=0 && workArray[location]>el){
				// If we found the element, that is lowest than major sorted element,
				// then we have find it's place (location) and shift all major elements right for 1 position
				// in this case new elements position will be filled with it's preceeder.
				workArray[location+1]=workArray[location--];
			}
			// Then just place new element to it's place
			workArray[location+1]=el;
		}
	}

	static void print(int[] array) {
		print(array, 0, array.length);
	}

	
	static void print(int[] array, int left, int right) {
		for(int i = left; i<right; i++){
			System.out.print(String.format("%d ", array[i]));
		}
		System.out.println();
	}

	private static void copyUtil(int[] dest, int[] src) {
		for(int i = 0; i<src.length; i++){
			dest[i] = src[i];
		}
	}

}
