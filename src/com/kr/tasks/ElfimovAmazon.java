package com.kr.tasks;

/**
 * Created with IntelliJ IDEA.
 * User: cONST
 * Date: 25.01.14
 * Time: 22:48
 * To change this template use File | Settings | File Templates.
 */
public class ElfimovAmazon {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 9, 9, 9, 17, 19, 23, 23, 23, 100, 100};
        int k = 1;
        ElfimovAmazon elfimovAmazon = new ElfimovAmazon();
        int res = elfimovAmazon.findSecond(arr, k);
        System.out.println(res);
        int ind = elfimovAmazon.binarySearch(arr, k);
        System.out.println(ind);

    }

    private int binarySearch(int[] arr, int k) {
        int lo = 0, hi = arr.length;
        int index = -1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (k <= arr[mid]) {
                hi = mid ;
                index = mid;
            }
            if (k > arr[mid])
                lo = mid + 1;
        }
        return index;
    }

    private int findSecond(int[] arr, int k) {
        int lo = 0, hi = arr.length;
        int index = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (k == arr[mid]) index = mid;
            if (k <= arr[mid]) hi = mid - 1;
            else if (k > arr[mid]) lo = mid + 1;
        }

        if (index != -1 && index <= arr.length - 2 && arr[index + 1] == k) return index + 1;
        return -1;
    }
}
