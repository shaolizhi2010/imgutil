package imgutil.test;

import java.awt.Dimension;

import gui.ava.html.image.generator.HtmlImageGenerator;

public class Html2Img {

	public static void main(String[] args) {

		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		imageGenerator.loadHtml(
				"<div  style='font-size:16px;display:block;width:300px;border:1px solid #eee;padding:5px;'>�����ޣ�likexiu.com���������챾���������O2O�ĵ���ƽ̨������������λ�����ӡ���ά���йص�һ�С������а������ֻ������ԡ����ݡ���ͨ�����������ҵ硢�Ҿߡ��������칫�豸���ֱ��鱦��Ь�����Ρ����������ά�ޡ�����ά��ҵ�������޼ƻ����찲װ����ϴ�����졢���յ����ŷ��� </div>   ");
		//imageGenerator.loadUrl("http://likexiu.com");
		imageGenerator.setSize(new Dimension(300, 500));
		imageGenerator.saveAsImage("C:\\Users\\lizhi\\Desktop\\temp\\test.png");
		//imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");
	}

}
