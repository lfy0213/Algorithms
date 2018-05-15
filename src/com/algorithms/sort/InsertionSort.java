package com.algorithms.sort;

import java.util.Random;

/**
 * 	插入排序：每步将一个待排序的记录，按其排序码大小，插到前面已经排序的文件中的适当位置，直到全部插入完为止。
 *  稳定性：稳定排序 
 *  时间复杂度： O(n)至O（n2），
 *  平均时间复杂度是O（n2）
 * @author Fuyou Loong
 *
 */
public class InsertionSort {

	public static void insertionSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int temp = a[i];
			int j;
			// 如果发现前面的数比他小，则将前面的数向后挪一个
			for (j = i; j > 0 && a[j - 1] > temp; j--) {
				a[j] = a[j - 1];
			}
			a[j] = temp;
		}
	}

	public static void main(String[] args) {
		int[] a = new int[10];
		Random random = new Random();
		System.out.println("初始数组");
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(50);
			System.out.print(" " + a[i]);
		}
		System.out.println("");
		insertionSort(a);
		System.out.println("排序后");
		for (int i = 0; i < a.length; i++) {
			System.out.print(" " + a[i]);
		}
	}
}
