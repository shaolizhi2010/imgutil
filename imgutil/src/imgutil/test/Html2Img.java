package imgutil.test;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;

import com.liker.core.img.ImgUtil;

import gui.ava.html.image.generator.HtmlImageGenerator;

public class Html2Img {
	
	ImgUtil imgUtil = new ImgUtil();
	
	public BufferedImage str2img(String str, int divWidth){
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		imageGenerator.loadHtml(
				"<div  style='box-sizing:border-box;font-size:16px;font-family:\"arail\";width:"+divWidth+"px; padding:10px;'>"+str+"</div>");
		//imageGenerator.setSize(new Dimension(width, null));
		
		BufferedImage resultImg = imageGenerator.getBufferedImage();
		//resultImg= new ImgUtil().scaleImage(resultImg, width, resultImg.getHeight()*width/resultImg.getWidth());
		
		return resultImg;
	}
	
	public BufferedImage unionImgAndStr(BufferedImage imgup,String str){
		
		
		BufferedImage imgdown =  str2img(str, new BigDecimal( imgup.getWidth()*0.8 ).intValue());
		BufferedImage newimg = imgUtil.unionImageVertical(imgup, imgdown);
		
		return newimg;
	}
	
	public static void main(String[] args) {
			
		ImgUtil imgUtil = new ImgUtil();
		
		BufferedImage imgup = imgUtil.loadImageLocal("C:\\Users\\lizhi\\Desktop\\temp\\nb_02.png");//nb_02.png tooopen_sy_118120998276.jpg
		
		//System.out.println(imgup.getWidth());
		
		Html2Img h2m = new Html2Img();
	//	BufferedImage imgdown = h2m.str2img("利客修（likexiu.com）致力打造本地生活服务O2O的电商平台。利客修自身定位是连接“跟维修有关的一切。”其中包括：手机、电脑、房屋、疏通、开换锁、家电、家具、汽车、办公设备、手表、珠宝、鞋包服饰、乐器等相关维修。除了维修业务，利客修计划延伸安装、清洗、改造、回收等上门服务。", new BigDecimal( imgup.getWidth()*0.8 ).intValue());
		//h2m.html2imgFile("利客修（likexiu.com）致力打造本地生活服务O2O的电商平台。利客修自身定位是连接“跟维修有关的一切。”其中包括：手机、电脑、房屋、疏通、开换锁、家电、家具、汽车、办公设备、手表、珠宝、鞋包服饰、乐器等相关维修。除了维修业务，利客修计划延伸安装、清洗、改造、回收等上门服务。", new BigDecimal( imgup.getWidth()*0.8 ).intValue() );
		//BufferedImage imgdown = imgUtil.loadImageLocal( "C:\\Users\\lizhi\\Desktop\\temp\\temp.jpg");
		
	//	BufferedImage newimg = imgUtil.unionImageVertical(imgup, imgdown);
		
		BufferedImage newimg = h2m.unionImgAndStr(imgup, "利客修（likexiu.com）致力打造本地生活服务O2O的电商平台。利客修自身定位是连接“跟维修有关的一切。”其中包括：手机、电脑、房屋、疏通、开换锁、家电、家具、汽车、办公设备、手表、珠宝、鞋包服饰、乐器等相关维修。除了维修业务，利客修计划延伸安装、清洗、改造、回收等上门服务。");
		
		imgUtil.writeImageLocal("C:\\Users\\lizhi\\Desktop\\temp\\test.jpg", newimg);
		
		
//		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
//		imageGenerator.loadHtml(
//				"<div  style='font-size:16px;display:block;width:300px;border:1px solid #eee;padding:5px;'>利客修（likexiu.com）致力打造本地生活服务O2O的电商平台。利客修自身定位是连接“跟维修有关的一切。”其中包括：手机、电脑、房屋、疏通、开换锁、家电、家具、汽车、办公设备、手表、珠宝、鞋包服饰、乐器等相关维修。除了维修业务，利客修计划延伸安装、清洗、改造、回收等上门服务。 </div>   ");
		//imageGenerator.loadUrl("http://likexiu.com");
		//imageGenerator.setSize(new Dimension(300, 500));
		
		
		//imageGenerator.saveAsImage("C:\\Users\\lizhi\\Desktop\\temp\\test.png");
		//imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");
	}
	
}
