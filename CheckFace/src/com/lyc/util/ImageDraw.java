package com.lyc.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class ImageDraw {
	
	
	public static void main(String[] args) {
		// ScaleImage("C:/Users/zxw/Desktop/新建文件夹/IMG_4872.JPG",
		// "D:/nouse/face4.jpeg");
		//ScaleImage("D:/nouse/face6.jpeg", "D:/nouse/face7.jpeg");
		
		  // 方法一：
        //pressText("我是水印文字","D:/nouse/face4.jpeg","D:/nouse/abc_pressText.jpg","宋体",Font.BOLD,Color.white,80, 0, 0, 0.5f);//测试OK
         // 方法二：
        //pressText2("我也是水印文字", "D:/nouse/face4.jpeg","D:/nouse/abc_pressText2.jpg", "黑体", 36, Color.white, 80, 0, 0, 0.5f);//测试OK
         
         // 6-给图片添加图片水印：
       // pressImage("e:/abc2.jpg","D:/nouse/face4.jpeg","D:/nouse/abc_pressImage.jpg", 0, 0, 0.5f);//测试OK
		Polygon p[]=null;
		p[0]=new Polygon();
		p[0].addPoint(1, 1);
		p[0].addPoint(100, 1);
		p[0].addPoint(100, 100);
		p[0].addPoint(1, 100);
		//drawLine("/Users/luyuncheng/Pictures/com.tencent.ScreenCapture/123.png" , "/Users/luyuncheng/Pictures/com.tencent.ScreenCapture/qqq.jpg",p);
	}



	/**
	 * 画线
	 * 
	 * */
	
	public  void drawLine(String pathIn, String pathOut, Polygon p[],int facenum) {

		try {
			BufferedImage src = ImageIO.read(new File(pathIn));
			Image image = src.getScaledInstance(src.getWidth(), src.getHeight(),Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(src.getWidth(), src.getHeight(),BufferedImage.TYPE_INT_BGR);
			Graphics g = tag.getGraphics();
			
			g.drawImage(image, 0, 0, null);
			g.setColor(Color.YELLOW);
			//g.drawPolygon(p);
			for(int facei=0;facei<facenum;facei++)
			{
				int xpoint[]=p[facei].xpoints;
			
				int ypoint[]=p[facei].ypoints;
				for(int i=1;i<p[facei].npoints;i++)
				{
					System.out.println("x:"+xpoint[i]);
					g.drawLine(xpoint[i-1], ypoint[i-1], xpoint[i], ypoint[i]);
				}
			}
			// 释放图形的资源
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(pathOut));
			System.out.println("缩放成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("缩放图片异常");
		}
	}
	/**
	 * 缩放图片
	 * 
	 * @param pathIn
	 *            源图片路径
	 * @param pathOut
	 *            输出图片路径 缩放成505，406大小的图片
	 * */
	public static void ScaleImage(String pathIn, String pathOut) {
		try {
			BufferedImage src = ImageIO.read(new File(pathIn));
			double width = src.getWidth();
			double height = src.getHeight();
			double s1 = width / 505;
			double s2 = height / 460;
			System.out.println("之前：" + width + "/" + height);
			double ss = 0;
			if (s1 >= 1 || s2 >= 1) {
				ss = s1 > s2 ? s1 : s2;
				width = width / ss + 0.5;
				height = height / ss + 0.5;
			} else {
				ss = s1 < s2 ? s1 : s2;
				width = width * ss + 0.5;
				height = height * ss + 0.5;
			}

			System.out.println("之后：" + width + "/" + height);
			Image image = src.getScaledInstance((int) width, (int) height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage((int) width, (int) height,
					BufferedImage.TYPE_INT_BGR);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null);
			// 释放图形的资源
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(pathOut));
			System.out.println("缩放成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("缩放图片异常");
		}
	}

	/**
	 * 给图片添加文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param fontName
	 *            水印的字体名称
	 * @param fontStyle
	 *            水印的字体样式
	 * @param color
	 *            水印的字体颜色
	 * @param fontSize
	 *            水印的字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public final static void pressText(String pressText, String srcImageFile,
			String destImageFile, String fontName, int fontStyle, Color color,
			int fontSize, int x, int y, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			// 在指定坐标绘制水印文字
			g.drawString(pressText, (width - (getLength(pressText) * fontSize))
					/ 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			ImageIO.write((BufferedImage) image, "JPEG",
					new File(destImageFile));// 输出到文件流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给图片添加文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param fontName
	 *            字体名称
	 * @param fontStyle
	 *            字体样式
	 * @param color
	 *            字体颜色
	 * @param fontSize
	 *            字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public final static void pressText2(String pressText, String srcImageFile,
			String destImageFile, String fontName, int fontStyle, Color color,
			int fontSize, int x, int y, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			// 在指定坐标绘制水印文字
			g.drawString(pressText, (width - (getLength(pressText) * fontSize))
					/ 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			ImageIO.write((BufferedImage) image, "JPEG",
					new File(destImageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给图片添加图片水印
	 * 
	 * @param pressImg
	 *            水印图片
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param x
	 *            修正值。 默认在中间
	 * @param y
	 *            修正值。 默认在中间
	 * @param alpha
	 *            透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public final static void pressImage(String pressImg, String srcImageFile,
			String destImageFile, int x, int y, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			// 水印文件
			Image src_biao = ImageIO.read(new File(pressImg));
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			g.drawImage(src_biao, (wideth - wideth_biao) / 2,
					(height - height_biao) / 2, wideth_biao, height_biao, null);
			// 水印文件结束
			g.dispose();
			ImageIO.write((BufferedImage) image, "JPEG",
					new File(destImageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算text的长度（一个中文算两个字符）
	 * 
	 * @param text
	 * @return
	 */
	public final static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}
}
