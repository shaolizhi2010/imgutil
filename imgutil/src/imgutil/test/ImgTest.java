package imgutil.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.imageio.ImageIO;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.liker.core.img.ImgUtil;

public class ImgTest {

	public static int targetFontSize = 12;
	public static int targetMaxFontSize = 24;
	public static int targetImgWidth = 300;

	public BufferedImage strToImg(String str, int imgWidth) {

		Style style = new Style();
		style.setWidth(imgWidth);

		int fontSize = new BigDecimal(targetFontSize * imgWidth / targetImgWidth).intValue();
		if (fontSize > targetMaxFontSize) {
			fontSize = targetMaxFontSize;// 最大30
		}

		style.setFont_size(fontSize);

		List<String> list = DbcSbcUtils.split(str,
				(style.getWidth() - style.getPadding_left() - style.getPadding_right()) / (style.getFont_size() / 2));
		int imageHeight = (list.size()) * (fontSize + style.getLine_height()) + style.getPadding_top()
				+ style.getPadding_bottom() + style.getFont_size();
		style.setHeight(imageHeight);

		BufferedImage image = new BufferedImage(imgWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();

		Font font = new Font("新宋体", Font.PLAIN, style.getFont_size());
		graphics.setFont(font);
		graphics.fillRect(0, 0, imgWidth, imageHeight);
		graphics.setColor(new Color(0, 0, 0));// 设置黑色字体,同样可以graphics.setColor(Color.black);

		int lineNo = 0;
		for (String substr : list) {
			graphics.drawString(substr, style.getPadding_left(), style.getPadding_top() + style.getFont_size()
					+ (style.getFont_size() + style.getLine_height()) * lineNo);
			lineNo++;
		}

		return image;
	}

	public static void main(String[] args) throws Exception {

		ImgUtil imgUtil = new ImgUtil();
		BufferedImage imageup = imgUtil.loadImageLocal("C:\\Users\\lizhi\\Desktop\\temp\\nb_02.png");

		Style style = new Style();

		style.setWidth(imageup.getWidth());

		int imageWidth = style.getWidth();// 300;//图片的宽度

		int fontSize = new BigDecimal(12 * imageup.getWidth() / 300).intValue();
		if (fontSize > 24) {
			fontSize = 24;// 最大30
		}

		style.setFont_size(fontSize);

		int imageHeight = style.getHeight(); // 图片的高度

		try {
			List<String> list = DbcSbcUtils.split(
					"利客修（likexiu.com）致力打造本地生活服务O2O的电商平台。利客修自身定位是连接“跟维修有关的一切。”其中包括：手机、电脑、房屋、疏通、开换锁、家电、家具、汽车、办公设备、手表、珠宝、鞋包服饰、乐器等相关维修。除了维修业务，利客修计划延伸安装、清洗、改造、回收等上门服务。",
					(style.getWidth() - style.getPadding_left() - style.getPadding_right())
							/ (style.getFont_size() / 2));

			imageHeight = (list.size() + 1) * (fontSize + style.getLine_height()) + style.getPadding_top()
					+ style.getFont_size();
			style.setHeight(imageHeight);

			BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = image.createGraphics();

			Font font = new Font("新宋体", Font.PLAIN, style.getFont_size());
			graphics.setFont(font);
			graphics.fillRect(0, 0, imageWidth, imageHeight);
			graphics.setColor(new Color(0, 0, 0));// 设置黑色字体,同样可以graphics.setColor(Color.black);

			int lineNo = 0;
			for (String str : list) {
				graphics.drawString(str, style.getPadding_left(), style.getPadding_top() + style.getFont_size()
						+ (style.getFont_size() + style.getLine_height()) * lineNo);
				lineNo++;
			}

			BufferedImage newimg = imgUtil.unionImageVertical(imageup, image);

			// graphics.drawString("网址:www.likexiu.com",
			// style.getInt(style.padding_left) , 36);
			ImageIO.write(newimg, "jpg", new File("C:\\Users\\lizhi\\Desktop\\temp\\abc.jpg"));// 生成图片方法一
			// ImageIO,可以生成不同格式的图片，比如JPG,PNG,GIF.....

			graphics.dispose();// 释放资源
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// 生成图片方法二开始,只知道生成jpg格式的图片,这个方法其他格式的还是不知道怎么弄。
		/*
		 * try { FileOutputStream fos = new FileOutputStream("D:\\abc.jpg");
		 * BufferedOutputStream bos = new BufferedOutputStream(fos);
		 * JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
		 * encoder.encode(image); bos.close(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		// 生成图片方法二结束

		//
		// String ACCESS_ID = "Tzm6unVbWbQz8d5X";// "G519MH7jgh3uuBUJ";
		// String ACCESS_KEY =
		// "lZdkStp7douG17yqiSF0MuteqG3B6L";//"9wdW5xOupatJiQzY4sLq8TcErg2y4N";
		//
		// // Create a new OSSClient instance
		// OSSClient client = new OSSClient("oss-cn-beijing.aliyuncs.com",
		// ACCESS_ID, ACCESS_KEY);
		//
		// ObjectMetadata objectMeta = new ObjectMetadata();
		// //objectMeta.setContentLength(targetFile.length());
		// // 可以在metadata中标记文件类型
		// objectMeta.setContentType("image/jpeg");
		//
		// //URL imgurl = new URL(
		// "http://s.cn.bing.net/th?id=OSA.Jib0x9Ma5h3grg&w=75&h=75&c=8&rs=1&pid=SatAns"
		// );
		// //DataInputStream dis = new DataInputStream(imgurl.openStream());
		//
		//
		//
		// InputStream input = new FileInputStream(new
		// File("C:/Users/lizhi/Desktop/temp/tooopen_sy_118120998276.jpg"));
		// client.putObject("likexiu", "test/tooopen_sy_118120998276.jpg",
		// input, objectMeta);
		//
		//
		// // Do some operations with the instance...
		//
		// // Shutdown the instance to release any allocated resources
		// client.shutdown();

	}
}
