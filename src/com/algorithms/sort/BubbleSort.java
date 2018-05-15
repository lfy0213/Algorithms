package com.algorithms.sort;

import java.util.Random;

/**
 * 冒泡排序 稳定性：稳定排序 时间复杂度：O(n)至O(n2)，平均时间复杂度为O(n2)
 * 最好的情况：如果待排序数据序列为正序，则一趟冒泡就可完成排序，排序码的比较次数为n-1次，且没有移动，时间复杂度为O(n)。
 * 最坏的情况：如果待排序数据序列为逆序，则冒泡排序需要n-1次趟起泡，每趟进行n-i次排序码的比较和移动，即比较和移动次数均达到最大值，时间复杂度为O(n2)
 * @author Fuyou
 */
public class BubbleSort {

	public static void bubbleSort(int[] a) {
		// 定义一个标志符，如果为true,表示还需要冒泡;
		boolean flag = true;
		for (int i = 0; i < a.length && flag; i++) {
			flag = false;
			for (int j = 0; j < a.length - i - 1; j++) {
				// 如果前一个元素比后一个元素就交换;
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					flag = true;
				}
			}
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
		bubbleSort(a);
		System.out.println("排序后");
		for (int i = 0; i < a.length; i++) {
			System.out.print(" " + a[i]);
		}
	}
}
