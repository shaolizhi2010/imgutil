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
			fontSize = targetMaxFontSize;// ���30
		}

		style.setFont_size(fontSize);

		List<String> list = DbcSbcUtils.split(str,
				(style.getWidth() - style.getPadding_left() - style.getPadding_right()) / (style.getFont_size() / 2));
		int imageHeight = (list.size()) * (fontSize + style.getLine_height()) + style.getPadding_top()
				+ style.getPadding_bottom() + style.getFont_size();
		style.setHeight(imageHeight);

		BufferedImage image = new BufferedImage(imgWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();

		Font font = new Font("������", Font.PLAIN, style.getFont_size());
		graphics.setFont(font);
		graphics.fillRect(0, 0, imgWidth, imageHeight);
		graphics.setColor(new Color(0, 0, 0));// ���ú�ɫ����,ͬ������graphics.setColor(Color.black);

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

		int imageWidth = style.getWidth();// 300;//ͼƬ�Ŀ��

		int fontSize = new BigDecimal(12 * imageup.getWidth() / 300).intValue();
		if (fontSize > 24) {
			fontSize = 24;// ���30
		}

		style.setFont_size(fontSize);

		int imageHeight = style.getHeight(); // ͼƬ�ĸ߶�

		try {
			List<String> list = DbcSbcUtils.split(
					"�����ޣ�likexiu.com���������챾���������O2O�ĵ���ƽ̨������������λ�����ӡ���ά���йص�һ�С������а������ֻ������ԡ����ݡ���ͨ�����������ҵ硢�Ҿߡ��������칫�豸���ֱ��鱦��Ь�����Ρ����������ά�ޡ�����ά��ҵ�������޼ƻ����찲װ����ϴ�����졢���յ����ŷ���",
					(style.getWidth() - style.getPadding_left() - style.getPadding_right())
							/ (style.getFont_size() / 2));

			imageHeight = (list.size() + 1) * (fontSize + style.getLine_height()) + style.getPadding_top()
					+ style.getFont_size();
			style.setHeight(imageHeight);

			BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = image.createGraphics();

			Font font = new Font("������", Font.PLAIN, style.getFont_size());
			graphics.setFont(font);
			graphics.fillRect(0, 0, imageWidth, imageHeight);
			graphics.setColor(new Color(0, 0, 0));// ���ú�ɫ����,ͬ������graphics.setColor(Color.black);

			int lineNo = 0;
			for (String str : list) {
				graphics.drawString(str, style.getPadding_left(), style.getPadding_top() + style.getFont_size()
						+ (style.getFont_size() + style.getLine_height()) * lineNo);
				lineNo++;
			}

			BufferedImage newimg = imgUtil.unionImageVertical(imageup, image);

			// graphics.drawString("��ַ:www.likexiu.com",
			// style.getInt(style.padding_left) , 36);
			ImageIO.write(newimg, "jpg", new File("C:\\Users\\lizhi\\Desktop\\temp\\abc.jpg"));// ����ͼƬ����һ
			// ImageIO,�������ɲ�ͬ��ʽ��ͼƬ������JPG,PNG,GIF.....

			graphics.dispose();// �ͷ���Դ
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// ����ͼƬ��������ʼ,ֻ֪������jpg��ʽ��ͼƬ,�������������ʽ�Ļ��ǲ�֪����ôŪ��
		/*
		 * try { FileOutputStream fos = new FileOutputStream("D:\\abc.jpg");
		 * BufferedOutputStream bos = new BufferedOutputStream(fos);
		 * JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
		 * encoder.encode(image); bos.close(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		// ����ͼƬ����������

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
		// // ������metadata�б���ļ�����
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
