package com.algorithms.sort;

import java.util.Random;

/**
 * 堆排序：利用堆序性质，过程分为建堆和删除堆顶元素; 
 * 时间复杂度：建堆时间复杂度O(n);删除堆顶元素花费总时间O(nlogn);
 * 稳定性：不稳定排序;
 * 
 * @author Fuyou Loong {1,3,5,6,7,8,4,9,10} 
 */

public class HeapSort {

	public static void main(String[] args) {
		int[] a = new int[6];
		Random random = new Random();
		System.out.println("初始数组");
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(50);
			System.out.print(" " + a[i]);
		}
		System.out.println("");
		heapSort(a);
	}

	public static void heapSort(int[] a) {
		// 建立一个堆;从最后一个非叶子节点开始调整;
		for (int i = a.length / 2 - 1; i >= 0; i--) {
			// 调整当前元素;
			adjust(a, i, a.length);
		}
		System.out.println("建堆之后的数组");
		for (int i = 0; i < a.length; i++) {
			System.out.print(" " + a[i]);
		}
		// 删除堆顶元素，（将堆顶元素与最后一个元素交换，堆大小减1）
		for (int i = a.length - 1; i > 0; i--) {
			swap(a, 0, i);
			// 调整堆顶元素;
			adjust(a, 0, i - 1);
		}
		System.out.println("");
		System.out.println("堆排序之后的数组");
		for (int i = 0; i < a.length; i++) {
			System.out.print(" " + a[i]);
		}
	}

	// 堆调整;
	/**
	 * 递归版本
	 * @param a
	 *            堆数组
	 * @param posi
	 *            当前要调整的元素
	 * @param length
	 *            堆大小
	 */
	private static void adjust(int[] a, int posi, int length) {
		// 如果有孩子则调整;
		int child = 2 * posi + 1;
		if (child > length) {
			return;
		}
		// 先判断是否有右儿子;如果有右儿子并且比左儿子大，就将child+1;
		if (child + 1 < length && a[child] < a[child + 1]) {
			child++;
		}
		// 当前节点和儿子比较;
		if (a[posi] >= a[child]) {
			return;
		} else {
			swap(a, posi, child);
		}
		// 调整孩子;
		adjust(a, child, length);

	}

	/**
	 * 迭代版本;
	 * @param a
	 * @param posi
	 * @param length
	 */
	private static void adjust1(int[] a, int posi, int length) {
		int child = posi * 2 + 1;
		// 当还有子节点时或者比子节点都大时继续
		for (; child < length; posi = child, child = child * 2 + 1) {
			if (child + 1 < length && a[child] < a[child + 1]) {
				child++;
			}
			if (a[posi] < a[child]) {
				swap(a, posi, child);
			} else {
				break;
			}
		}
	}

	private static void swap(int[] a, int posi, int child) {
		int temp = a[posi];
		a[posi] = a[child];
		a[child] = temp;
	}

}
