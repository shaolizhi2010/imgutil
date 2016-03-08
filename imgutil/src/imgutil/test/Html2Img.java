package imgutil.test;

import java.awt.Dimension;

import gui.ava.html.image.generator.HtmlImageGenerator;

public class Html2Img {

	public static void main(String[] args) {

		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		imageGenerator.loadHtml(
				"<div  style='font-size:16px;display:block;width:300px;border:1px solid #eee;padding:5px;'>利客修（likexiu.com）致力打造本地生活服务O2O的电商平台。利客修自身定位是连接“跟维修有关的一切。”其中包括：手机、电脑、房屋、疏通、开换锁、家电、家具、汽车、办公设备、手表、珠宝、鞋包服饰、乐器等相关维修。除了维修业务，利客修计划延伸安装、清洗、改造、回收等上门服务。 </div>   ");
		//imageGenerator.loadUrl("http://likexiu.com");
		imageGenerator.setSize(new Dimension(300, 500));
		imageGenerator.saveAsImage("C:\\Users\\lizhi\\Desktop\\temp\\test.png");
		//imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");
	}

}
