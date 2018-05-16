package com.algorithms.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 桶排序：
 * 简述:简单说来就是分块儿排序。假设输入数组为均匀分布，然后将数据分为n段——称之为桶，将全部数据依次放入桶中，
 * 再通过递归将桶中数字取出再细分，或者利用其他排序算法，将局部数字排序，最后将桶按照区间大小重新串起即可.
 * 优点:如已知数据连续、数据集中在某一个区间，可以将该算法视为一个较为高效的算法。
 * 缺点:算法复杂度主要取决于step的大小（也就是桶的宽度，即区间大小），以及用于桶内排序的算法。
 * 若区间太大，桶内数据较多，则步入使用其他算法，若step过小，如1，而数据量又非常大，则非常不适合使用该算法。
 * 可改进:在一些特定情况下（如已知数据连续、数据集中在某一个区间），可以把内部排序的过程去除掉，把step设为1，也可以有效进行高效排序
 * @author FuYou
 *
 */
public class BucketSort {
	// 区间范围，暂定为20;
	public static int STEP = 3;

	public static void main(String[] args) {
		int[] a = new int[30];
		Random random = new Random();
		System.out.println("初始数组");
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(50);
			System.out.print(" " + a[i]);
		}
		System.out.println("");
		bucketSort(a);
		System.out.println("排序后");
		for (int i = 0; i < a.length; i++) {
			System.out.print(" " + a[i]);
		}
	}

	public static void bucketSort(int[] a) {
		// 找出数组中的最大最小值;
		int max = a[0];
		int min = a[0];
		for (int i = 1; i < a.length; i++) {
			if (max < a[i]) {
				max = a[i];
			}
			if (min > a[i]) {
				min = a[i];
			}
		}

		// 计算需要的桶数目;
		int BucketNum = (max - min) / STEP + 1;
		// 建立桶数组;
		List<LinkedList<Integer>> bucketList = new ArrayList<LinkedList<Integer>>();
		// 添加桶
		for (int i = 0; i < BucketNum; i++) {
			bucketList.add(new LinkedList<Integer>());
		}

		// 根据数组中元素的所在区间加入桶中;
		for (int i = 0; i < a.length; i++) {
			int index = (a[i]-min) / STEP;
			bucketList.get(index).add(a[i]);
		}
		int arrPosi = 0;
		// 遍历桶列表，将桶中元素放入原来的数组中;
		for (LinkedList<Integer> linkedList : bucketList) {
			// 遍历桶列表,将桶中元素排序,这里使用插入排序;
				insertionSort(linkedList);
			for (Integer integer : linkedList) {
				a[arrPosi++] = integer;
			}
		}
	}

	private static void insertionSort(LinkedList<Integer> linkedList) {
		for (int i = 1; i < linkedList.size(); i++) {
			int j;
			int temp = linkedList.get(i);
			for (j = i; j > 0 && linkedList.get(j - 1) > temp; j--) {
				linkedList.set(j, linkedList.get(j-1));
			}
			linkedList.set(j, temp);
		}
	}
}
