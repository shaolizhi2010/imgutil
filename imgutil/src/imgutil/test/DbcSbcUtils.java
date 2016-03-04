package imgutil.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * ��ǡ�ȫ�����ִ�������
 * 
 * @author
 */
public class DbcSbcUtils {

	/**
	 * ��ǡ�ȫ���ַ��ж�
	 * 
	 * @param c
	 *            �ַ�
	 * @return true����ǣ� false��ȫ��
	 */
	public static boolean isDbcCase(char c) {
		// ����������ĸ���������Ͽɼ��ģ��ո����֡���ĸ�����ţ�
		if (c >= 32 && c <= 127) {
			return true;
		}
		// ���İ��Ƭ�����ͷ���
		else if (c >= 65377 && c <= 65439) {
			return true;
		}
		return false;
	}

	public static int getLength(char str) {
		int charLen = isDbcCase(str) ? 1 : 2;
		return charLen;
	}
	
	
	/**
	 * �ַ�������ȡ�ã����ְ�ǡ�ȫ�ǣ�
	 * 
	 * @param str
	 *            �ַ���
	 * @return �ַ�������
	 */
	public static int getLength(String str) {
		int len = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (isDbcCase(c)) { // ���
				len = len + 1;
			} else { // ȫ��
				len = len + 2;
			}
		}
		return len;
	}

	/**
	 * �ַ�����ȡ�����ְ�ǡ�ȫ�ǣ�
	 * 
	 * @param str
	 *            �ַ���
	 * @param limit
	 *            ����
	 * @return
	 */
	public static String left(String str, int limit) {
		if (getLength(str) <= limit) {
			return str;
		}
		char[] chars = str.toCharArray();
		int charLenSum = 0;
		String result = "";
		for (int i = 0; i < chars.length; i++) {
			int charLen = isDbcCase(chars[i]) ? 1 : 2;
			if (charLenSum + charLen > limit) {
				return result;
			}
			charLenSum += charLen;
			result += chars[i];
			if (charLenSum == limit) {
				return result;
			}
		}
		return "";
	}

	public static List<String> split(String str, int length) {

		List<String> list = new ArrayList<>();

		if (getLength(str) <= length) {
			list.add(str);
			return list;
		}

		char[] chars = str.toCharArray();
		int charLenSum = 0;
		String temp = "";

		for (char c : chars) {
			int charLen = getLength(c);
			
			if(charLenSum+ charLen > length){ //���� ����
				list.add(temp);
				temp = String.valueOf(c);
				charLenSum = charLen;
			}
			else{
				temp += c;
				charLenSum += charLen;
			}
		}
		if(StringUtils.isNotBlank(temp)){
			list.add(temp);
		}
		
		return list;
	}

	public static void main(String[] args) {

//		System.out.println(getLength("C"));
//		System.out.println(getLength("��"));
//
//		System.out.println(left("ȫ������", 10));
//		System.out.println(left("ȫ��������", 10));
//		System.out.println(left("ȫ������12", 10));
//		System.out.println(left("ȫ������123", 10));
//		System.out.println(left("ȫ��������a", 10));
//		System.out.println(left("ȫ�������ж�", 10));
		
		System.out.println( split("ȫ������һ�����������abc123", 5) );
	}
}