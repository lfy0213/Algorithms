package com.algorithms.sort;

import java.util.Random;

/**
 * 快速排序:运用分治思想，将要排序的数分割成2部分，左边的值小于基准值，右边的值大于基准值，然后递归对左右两部分继续分割;
 * 时间复杂度:最坏时间复杂度O(n2),平均时间复杂度O(nlogn)
 * 快速排序的最好情况是每次选出的关键字接近中位数
 * 稳定性:不稳定的算法
 * @author Fuyou Loong
 *
 */
public class QuickSort {

	// 驱动程序
	public static void quickSort(int[] a) {

		quickSort2(a, 0, a.length - 1);
	}

	// 左右指针法
	private static void quickSort(int[] a, int left, int right) {

		if (left < right) {
			// 选取枢纽元，三中数值法;
			int medium = medium3(a, left, right);
			int leftPosi = left;
			int rightPosi = right - 1;
			// 开始分割
			while (leftPosi < rightPosi) {

				// 向右寻找大于枢纽元的元素;
				while (leftPosi < rightPosi && a[leftPosi] <= medium) {
					leftPosi++;
				}

				// 向左寻找小于枢纽元的元素;
				while (leftPosi < rightPosi && a[rightPosi] >= medium) {
					rightPosi--;
				}

				// 交换寻找的元素;
				if (leftPosi != rightPosi) {
					swap(a, leftPosi, rightPosi);
				}
			}
			// 将枢纽元回置;
			swap(a, leftPosi, right - 1);
			// 对左右区间进行快速排序;
			quickSort(a, left, leftPosi - 1);
			quickSort(a, leftPosi + 1, right);
		}
	}

	// 挖坑法
	private static void quickSort2(int[] a, int left, int right) {
		if (left < right) {
			int leftPosi = left;
			int rightPosi = right - 1;
			// 选取坑基准值;
			int blank = medium3(a, left, right);
			// 坑坐标;
			int blankPosi = right - 1;
			while (leftPosi < rightPosi) {
				// 向右寻找比坑基准大的值;
				while (leftPosi < rightPosi && a[leftPosi] <= blank) {
					leftPosi++;
				}
				//填坑;
				if(leftPosi!= rightPosi) {
					a[blank] = a[leftPosi];
					blankPosi = leftPosi;
				}
				//向左寻找比坑基准值小的值;
				while(leftPosi<rightPosi && a[rightPosi] >= blank) {
					rightPosi --;
				}
				//填坑;
				if(leftPosi!=rightPosi) {
					a[blank] = a[rightPosi];
					blankPosi = rightPosi;
				}
			}
			//回填最后一个坑;
			a[blankPosi] = blank;
			//对左右区间进行排序;
			quickSort2(a, left, leftPosi-1);
			quickSort2(a, leftPosi+1, right);
		}
	}

	private static int medium3(int[] a, int left, int right) {
		int mediumPosi = (left + right) / 2;
		if (a[left] > a[mediumPosi]) {
			swap(a, left, mediumPosi);
		}
		if (a[left] > a[right]) {
			swap(a, left, right);
		}
		if (a[mediumPosi] > a[right]) {
			swap(a, mediumPosi, right);
		}
		// 将枢纽元放置倒数第二个位置;
		swap(a, mediumPosi, right - 1);
		return a[right - 1];
	}

	private static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

	public static void main(String[] args) {
		int[] a = new int[10];
		Random random = new Random();
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(50);
			System.out.print(" " + a[i]);
		}
		System.out.println("");
		quickSort(a, 0, a.length - 1);
		for (int i = 0; i < a.length; i++) {
			System.out.print(" " + a[i]);
		}
	}
}
