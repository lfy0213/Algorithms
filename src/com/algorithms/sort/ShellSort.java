package com.algorithms.sort;

import java.util.Random;

/**
 * 希尔排序; 插入排序的优化版;也称为增量排序, 
 * 思路:按照增量将数分为不同的组，再进行插入排序；增量不断减少，组的元素越来越多，组越少，直到增量为1;
 * 时间复杂度：最坏时间复杂度O(n2)
 * 稳定性：不稳定的算法
 * @author Fuyou Loong
 *
 */
public class ShellSort {

	public static void main(String[] args) {
		int[] a = new int[10];
		Random random = new Random();
		System.out.println("初始数组");
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(50);
			System.out.print(" " + a[i]);
		}
		System.out.println("");
		shellSort(a);
		System.out.println("排序后");
		for (int i = 0; i < a.length; i++) {
			System.out.print(" " + a[i]);
		}
	}

	private static void shellSort(int[] a) {
		// 将元素进行分组;初始增量为数组长度一半;
		for (int gap = a.length / 2; gap > 0; gap /= 2) {
			// 将分好组的数列进行插入排序;
			for (int i = gap; i < a.length; i++) {
				int j;
				int temp = a[i];
				for (j = i; j >= gap && temp < a[j - gap]; j = j - gap) {
					a[j] = a[j - gap];
				}
				a[j] = temp;
			}
		}
	}
}
