package test;

import org.apache.commons.lang3.StringUtils;

public class Trimer {
	
	public static String trim(String str){
		str = StringUtils.replace(str, "_��Ѹ�������|�ٶ��� ����-����������", "");
		
		str = StringUtils.replace(str, "��", " ");
		str = StringUtils.replace(str, "��", " ");
		
		return str;
	}

	public static void main(String[] args) {
		
		

	}

}
