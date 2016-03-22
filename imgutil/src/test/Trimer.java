package test;

import org.apache.commons.lang3.StringUtils;

public class Trimer {
	
	public static String trim(String str){
		str = StringUtils.replace(str, "_免费高速下载|百度云 网盘-分享无限制", "");
		
		str = StringUtils.replace(str, "【", " ");
		str = StringUtils.replace(str, "】", " ");
		
		return str;
	}

	public static void main(String[] args) {
		
		

	}

}
