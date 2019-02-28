package cn.jbit.myshopms.util;

import java.util.Random;

public class RandomTool {

	/**
	 * ���ָ����Χ��N�����ظ����� �ڳ�ʼ�������ظ���ѡ�������������һ�����������У�
	 * ����ѡ���鱻������������ô�ѡ����(len-1)�±��Ӧ�����滻 Ȼ���len-2�����������һ����������������
	 * 
	 * @param max
	 *            ָ����Χ���ֵ
	 * @param min
	 *            ָ����Χ��Сֵ
	 * @param n
	 *            ���������
	 * @return int[] ����������
	 */
	public static int[] random(int min, int max, int n) {
		int len = max - min + 1;
		if (max < min || n > len) {
			return null;
		}
		// ��ʼ��������Χ�Ĵ�ѡ����
		int[] source = new int[len];
		for (int i = min; i < min + len; i++) {
			source[i - min] = i;
		}

		int[] result = new int[n];
		Random rd = new Random();
		int index = 0;
		for (int i = 0; i < result.length; i++) {
			// ��ѡ����0��(len-2)���һ���±�
			index = Math.abs(rd.nextInt() % len--);
			// �������������������
			result[i] = source[index];
			// ����ѡ�����б�������������ô�ѡ����(len-1)�±��Ӧ�����滻
			source[index] = source[len];
		}
		return result;
	}

	public static long getTimeMillis() {
		long time = System.currentTimeMillis();
		return time;
	}

	public static String getTimeMillisToString() {
		return String.valueOf(getTimeMillis());
	}
}
