package com.algorithms.sort;

import java.util.Random;

/**
 * 将数列分为2部分，分别对两部分进行递归归并排序;
 * 时间复杂度O（nlogn）
 * @author Fuyou Loong
 *
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] a = new int[10];
		Random random = new Random();
		System.out.println("初始数组");
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(50);
			System.out.print(" " + a[i]);
		}
		System.out.println("");
		mergeSort(a);
		System.out.println("排序后");
		for (int i = 0; i < a.length; i++) {
			System.out.print(" " + a[i]);
		}
	}

	public static void mergeSort(int[] a) {
		int[] temp = new int[a.length];
		mergeSort(a, 0, a.length - 1, temp);
	}

	/**
	 * @param a 待排序数组
	 * @param left 左边界
	 * @param right	右边界
	 */
	private static void mergeSort(int[] a, int left, int right, int[] temp) {
		if (left < right) {
			int center = (left + right) / 2;
			// 对左边的进行归并排序;
			mergeSort(a, left, center, temp);
			// 对右边的进行归并排序;
			mergeSort(a, center + 1, right, temp);
			// 合并结果集;
			merge(a, temp, left, right, center);
		}
	}

	private static void merge(int[] a, int[] temp, int left, int right, int center) {
		int leftPosi = left;
		int leftEnd = center;
		int rightPosi = center + 1;
		int rightEnd = right;
		int tempPosi = left;
		int length = right - left + 1;
		// 两个子数组中的元素逐个比较，较小者放入temp数组;
		while (leftPosi <= leftEnd && rightPosi <= rightEnd) {
			if (a[leftPosi] < a[rightPosi]) {
				temp[tempPosi++] = a[leftPosi++];
			} else {
				temp[tempPosi++] = a[rightPosi++];
			}
		}
		// 添加左边剩余的元素;
		if (leftPosi <= leftEnd) {
			temp[tempPosi++] = a[leftPosi++];
		}
		// 添加右边剩余的元素；
		if (rightPosi <= rightEnd) {
			temp[tempPosi++] = a[rightPosi++];
		}

		// 回填到原来数组;length为当前两段数组元素的总和;
		for (int i = 0; i < length; i++) {
			a[rightEnd] = temp[rightEnd];
			rightEnd--;
		}
	}
}
