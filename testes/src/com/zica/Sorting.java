package com.zica;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

public class Sorting {

    public static void main(String[] args) {
        Sorting s = new Sorting();

        int[] orderedArr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] unorderedArr = {10, 1, 3, 4, 53, 6, -30, 90, 8, 15, -30, 900};
        int[] invertedArr = {1000, 40, -1};
        int[] someArr = {100, 290, 4, -1};
        int[] pairArr = {90, 1};
        int[] randomArray = new Random().ints(1000).toArray();
        int[] emptyArr = {};

        s.testAlgorithm("Insertion sort", s::insertionSort, orderedArr, unorderedArr, invertedArr,
                someArr, pairArr, randomArray, emptyArr);
        s.testAlgorithm("Selection sort", s::selectionSort, orderedArr, unorderedArr, invertedArr,
                someArr, pairArr, randomArray, emptyArr);
        s.testAlgorithm("Shell sort", s::shellSort, orderedArr, unorderedArr, invertedArr,
                someArr, pairArr, randomArray, emptyArr);

        Mergesort mergesort = new Mergesort();
        s.testAlgorithm("Mergesort", mergesort::sort, orderedArr, unorderedArr, invertedArr,
                someArr, pairArr, randomArray, emptyArr);
        s.testAlgorithm("Knuth shuffle", s::knuthShuffle, orderedArr, unorderedArr, invertedArr,
                someArr, pairArr, randomArray, emptyArr);


    }

    private void testAlgorithm(String algName, Function<int[], int[]> f, int[]... arrs) {
        System.out.println(algName);

        for (int[] arr : arrs) {
            System.out.println(Arrays.toString(f.apply(arr)));
        }

        System.out.println();
    }

    private int[] insertionSort(int[] arr) {
        int n = arr.length;
        int[] ordered = Arrays.copyOf(arr, n);

        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && ordered[j] < ordered[j - 1]; j--) {
                int aux = ordered[j - 1];
                ordered[j - 1] = ordered[j];
                ordered[j] = aux;
            }
        }
        return ordered;
    }

    private int[] selectionSort(int[] arr) {
        int n = arr.length;
        int[] ordered = Arrays.copyOf(arr, n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (ordered[i] > ordered[j]) {
                    int aux = ordered[i];
                    ordered[i] = ordered[j];
                    ordered[j] = aux;
                }
            }
        }
        return ordered;

    }

    private int[] shellSort(int[] arr) {
        int n = arr.length;
        int[] ordered = Arrays.copyOf(arr, n);

        int h = 1;
        while (h < n/3) h = 3*h + 1;

        while (h > 0) {
            // h-sort the array
            for (int i = h; i < n; i += h)
                for (int j = i - h; j >= 0 && ordered[j] > ordered[j + h]; j -= h) {
                    int aux = ordered[j + h];
                    ordered[j + h] = ordered[j];
                    ordered[j] = aux;
                }

            h = h/3;
        }

        return ordered;

    }

    private int[] knuthShuffle(int[] arr) {
        int n = arr.length;
        int[] ordered = Arrays.copyOf(arr, n);

        for (int i = 1; i < n; i++) {
            int r = new Random().nextInt(i + 1);

            int swap = ordered[i];
            ordered[i] = ordered[r];
            ordered[r] = swap;
        }

        return ordered;
    }
}
