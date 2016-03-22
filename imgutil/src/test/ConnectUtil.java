package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.StringUtils;

public class ConnectUtil {
	
	public static void getTitle(){
		URL url;
		try {
			url = new URL("http://pan.baidu.com/share/link?shareid=1023176242&uk=1798788396");
		

			URLConnection connection = url.openConnection();
		
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
			String line;
			String result = "";
			while ((line = in.readLine()) != null) {
				result += line;
			}
			in.close();
			
			String title = StringUtils.substringBetween(result, "<title>", "</title>");
			
			title = Trimer.trim(title);
			
			System.out.println(title);
		
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) throws Exception {
		
		for(int i=0;i<2 ;i++){
			getTitle();
		}
		
	}
}
