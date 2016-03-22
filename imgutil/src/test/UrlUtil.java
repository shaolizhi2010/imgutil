package test;

import org.apache.commons.lang3.StringUtils;

public class UrlUtil {

	public static String test(String str){
		
		String[] subs = str.split("[ ]");
		
		for(String sub : subs){
			if(StringUtils.contains(sub, "http://")
					||StringUtils.contains(sub, ".com")
					||StringUtils.contains(sub, ".cn")){
				
			}
		}
		
		return str;
	}
	
	public static void main(String[] args) {
		 

	}

}
