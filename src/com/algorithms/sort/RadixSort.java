package com.algorithms.sort;

import java.util.ArrayList;
import java.util.Random;

/**
 * 基数排序:要求位数相同
 * 原理：按位比较，如果为整数，就按照个,十,百等顺序依次比较,位数不相等则高位补0,例如012,123,045
 * 时间复杂度：O(k*n)  k为要趟数，即字符串的长度; n为个数
 * @author FuYou
 *
 */
public class RadixSort {

	public static void main(String[] args) {
		String[] a = new String[20];
		Random random = new Random();
		System.out.println("初始数组");
		for (int i = 0; i < a.length; i++) {
			int num = random.nextInt(20000)+10000;
			a[i] = "" + num;
			System.out.print(" " + a[i]);
		}
		System.out.println("");
		radixSort(a, 5);
		System.out.println("排序后");
		for (int i = 0; i < a.length; i++) {
			System.out.print(" " + a[i]);
		}
	}

	/**
	 * 定长字符串基数排序
	 * @param a 相同长度的字符串组成的数组;
	 * @param strLength 字符串的长度
	 */
	private static void radixSort(String[] a, int strLength) {
		// 假设字符都是ASCII码,并且长度相同;
		final int bucketNum = 256;
		// 建立一个长度为bucketNum大小的ArrayList<String>类型的数组;
		ArrayList<String>[] buckets = new ArrayList[256];
		// 添加桶;
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new ArrayList<String>();
		}

		// 从字符串最后一位开始比较,每趟基于位比较将字符串放入桶中，然后回填到数组中，清空桶中字符串;
		for (int i = strLength - 1; i >= 0; i--) {
			for (int j = 0; j < a.length; j++) {
				// 将字符串加入桶;
				buckets[a[j].charAt(i)].add(a[j]);
			}
			// 将桶中字符串依次回填到数组中;
			int index = 0;
			for (int j = 0; j < buckets.length; j++) {
				ArrayList<String> bucket = buckets[j];
				for (String string : bucket) {
					a[index++] = string;
				}
				// 清空桶中数据;
				bucket.clear();
			}
		}

	}

	/**
	 * 定长字符串的计数基数排序
	 * 避免了使用ArrayList,采用计数器的方式记录每个桶中的元素个数;
	 * @param a
	 * @param strLength
	 */
	public static void countingRadixSort(String[] a, int strLength) {
		final int bucketNum = 256;
		// 缓冲数组;
		String[] buffer = new String[a.length];

		String[] in = a;
		String[] out = buffer;

		for (int i = strLength - 1; i >= 0; i--) {
			// 建立一个计数数组;数组长度需要+1;
			int[] count = new int[bucketNum + 1];
			// 遍历字符串数组
			for (int j = 0; j < a.length; j++) {
				// 计数数组的索引为原始数组中的值+1
				// 每出现一次，就将值+1;
				count[a[j].charAt(i) + 1]++;
			}

			// 将计数数组的索引转换为排序后元素第一次出现的索引
			for (int j = 1; j <= bucketNum; j++) {
				count[j] += count[j - 1];
			}

			for (int j = 0; j < a.length; j++) {
				out[count[in[i].charAt(i)]++] = in[i];
			}

			// 交换in和out数组
			String[] temp = in;
			in = out;
			out = temp;
		}
		// 如果趟数为偶数，则最后一个out引用的是a数组，于是排序结束，否则，把buffer复制回a;
		if (strLength % 2 == 1) {
			for (int i = 0; i < a.length; i++) {
				out[i] = in[i];
			}
		}
	}

	/**
	 * @param a 不定长的字符串数组
	 * @param maxLength 字符串的可能出现的最大长度;
	 */
	public static void countingRadixSort1(String[] a, int maxLength) {
		final int bucketNum = 256;
		ArrayList<String>[] wordsByLength = new ArrayList[maxLength + 1];
		ArrayList<String>[] buckets = new ArrayList[bucketNum];

		for (int i = 0; i < wordsByLength.length; i++) {
			wordsByLength[i] = new ArrayList<>();
		}

		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new ArrayList<>();
		}

		for (int i = 0; i < a.length; i++) {
			wordsByLength[a[i].length()].add(a[i]);
		}

		int index = 0;

		for (int i = 0; i < wordsByLength.length; i++) {
			ArrayList<String> list = wordsByLength[i];
			for (String string : list) {
				a[index++] = string;
			}
		}

		int startingIndex = a.length;

		for (int i = maxLength - 1; i >= 0; i--) {
			startingIndex -= wordsByLength[i + 1].size();

			for (int j = startingIndex; j < a.length; j++) {
				buckets[a[j].charAt(i)].add(a[j]);
			}

			index = startingIndex;

			for (ArrayList<String> arrayList : buckets) {

				for (String string : arrayList) {
					a[index++] = string;
				}
				arrayList.clear();
			}
		}
	}

}
