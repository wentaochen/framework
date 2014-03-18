package com.framework.interfaces.web.servlet.imagecode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 *  生成随机数字或字母串，以图像方式显示，用于人工识别，使程序很难识别。 减小系统被程序自动攻击的可能性。
 *  生成的字符图形颜色由蓝色组而成，数字或字母垂直方向位置在一定范围内也是随机的，减少被程序自动识别的几率。
 *  干扰线的颜色由红、橙、黄、绿、蓝、靛、紫随机组合而成。
 *  由于数字的0，1，2易和字母的o，l,z混淆，使人眼难以识别，因此不建议生成数字和字母的混合串。
 *  生成的串字母统一用小写，串的最大长度为16。
 * 
 * 
 * @version 1.0.0
 * 
 * 修改版本: 1.0.0
 * 修改日期: 2010-8-24
 * 修改说明: 重构代码
 * 复审人 ：
 * </pre>
 */
public class RandomGraphic {

	/**
	 * 要生成的字符个数，由工厂方法得到
	 */
	private int charCount = 0;

	/**
	 * 干扰线条数
	 */
	private int lineCount = 30;

	/**
	 * 字符的高度，单位为像素
	 */
	private int wordHeight = 10;

	/**
	 * 字符的宽度，单位为像素
	 */
	private int wordWidth = 15;

	/**
	 * 字体大小，单位pt
	 */
	private int fontSize = 22;

	/**
	 * 字体
	 */
	private String fontName = "Verdana";

	/**
	 * 最大字符串个数
	 */
	private static final int MAX_CHAR_COUNT = 16;

	/**
	 * 最大干扰线个数
	 */
	private static final int MAX_LINE_COUNT = 100;

	/**
	 * 垂直方向起始位置
	 */
	private static final int INIT_YPOS = 7;

	/**
	 * 颜色数组，绘制字串时随机选择一个
	 */
	private static final Color[] CHAR_COLORS = { Color.BLUE };

	/**
	 * 颜色数组，绘制干扰线时随机选择一个
	 */
	private static final Color[] LINE_COLORS = { Color.RED, Color.ORANGE,
			Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA };

	/**
	 * 生成图像的格式常量，JPEG格式,生成为文件时扩展名为.jpg； 输出到页面时需要设置MIME type 为image/jpeg
	 */
	public final static String GRAPHIC_JPEG = "JPEG";

	/**
	 * 生成图像的格式常量，PNG格式,生成为文件时扩展名为.png； 输出到页面时需要设置MIME type 为image/png
	 */
	public final static String GRAPHIC_PNG = "PNG";

	protected RandomGraphic() {
	}

	/**
	 * 用工厂方法创建对象
	 * 
	 * @param charCount
	 *            字符个数
	 */
	protected RandomGraphic(int charCount) {
		if (charCount > 0 && charCount <= MAX_CHAR_COUNT) {
			this.charCount = charCount;
		}
	}

	/**
	 * 用工厂方法创建对象
	 * 
	 * @param charCount
	 *            字符个数
	 * @param lineCount
	 *            干扰线条数
	 */
	protected RandomGraphic(int charCount, int lineCount) {
		if (charCount > 0 && charCount <= MAX_CHAR_COUNT) {
			this.charCount = charCount;
		}
		if (lineCount > 0 && lineCount <= MAX_LINE_COUNT) {
			this.lineCount = lineCount;
		}
	}

	/**
	 * 用工厂方法创建对象
	 * 
	 * @param charCount
	 *            字符个数
	 * @param lineCount
	 *            干扰线条数
	 * @param wordHeight
	 *            字符高度
	 * @param wordWidth
	 *            字符宽度
	 * @param fontSize
	 *            字体大小
	 */
	protected RandomGraphic(int charCount, int lineCount, int wordHeight,
			int wordWidth, int fontSize) {
		if (charCount > 0 && charCount <= MAX_CHAR_COUNT) {
			this.charCount = charCount;
		}
		if (lineCount > 0 && lineCount <= MAX_LINE_COUNT) {
			this.lineCount = lineCount;
		}
		if (wordHeight > 0) {
			this.wordHeight = wordHeight;
		}
		if (wordWidth > 0) {
			this.wordWidth = wordWidth;
		}
		if (fontSize > 0) {
			this.fontSize = fontSize;
		}
	}

	/**
	 * 用工厂方法创建对象
	 * 
	 * @param charCount
	 *            字符个数
	 * @param lineCount
	 *            干扰线条数
	 * @param wordHeight
	 *            字符高度
	 * @param wordWidth
	 *            字符宽度
	 * @param fontSize
	 *            字体大小
	 * @param fontName
	 *            字体名
	 */
	protected RandomGraphic(int charCount, int lineCount, int wordHeight,
			int wordWidth, int fontSize, String fontName) {
		if (charCount > 0 && charCount <= MAX_CHAR_COUNT) {
			this.charCount = charCount;
		}
		if (lineCount > 0 && lineCount <= MAX_LINE_COUNT) {
			this.lineCount = lineCount;
		}
		if (wordHeight > 0) {
			this.wordHeight = wordHeight;
		}
		if (wordWidth > 0) {
			this.wordWidth = wordWidth;
		}
		if (fontSize > 0) {
			this.fontSize = fontSize;
		}
		if (StringUtils.isNotBlank(fontName)) {
			this.fontName = fontName;
		}
	}

	/**
	 * 创建对象的工厂方法
	 * 
	 * @param charCount
	 *            要生成的字符个数，个数在1到16之间
	 * @return 返回RandomGraphic对象实例
	 */
	public static RandomGraphic createInstance(int charCount) {
		return new RandomGraphic(charCount);
	}

	/**
	 * 创建对象的工厂方法
	 * 
	 * @param charCount
	 *            要生成的字符个数，个数在1到16之间
	 * @param lineCount
	 *            要生成的干扰线条数
	 * @return 返回RandomGraphic对象实例
	 */
	public static RandomGraphic createInstance(int charCount, int lineCount) {
		return new RandomGraphic(charCount, lineCount);
	}

	/**
	 * 创建对象的工厂方法
	 * 
	 * @param charCount
	 *            要生成的字符个数，个数在1到16之间
	 * @param lineCount
	 *            要生成的干扰线条数
	 * @param wordHeight
	 *            字符高度
	 * @param wordWidth
	 *            字符宽度
	 * @param fontSize
	 *            字体大小
	 * @return 返回RandomGraphic对象实例
	 */
	public static RandomGraphic createInstance(int charCount, int lineCount,
			int wordHeight, int wordWidth, int fontSize) {
		return new RandomGraphic(charCount, lineCount, wordHeight, wordWidth,
				fontSize);
	}

	/**
	 * 创建对象的工厂方法
	 * 
	 * @param charCount
	 *            要生成的字符个数，个数在1到16之间
	 * @param lineCount
	 *            要生成的干扰线条数
	 * @param wordHeight
	 *            字符高度
	 * @param wordWidth
	 *            字符宽度
	 * @param fontSize
	 *            字体大小
	 * @param fontName
	 *            字体名
	 * @return 返回RandomGraphic对象实例
	 */
	public static RandomGraphic createInstance(int charCount, int lineCount,
			int wordHeight, int wordWidth, int fontSize, String fontName) {
		return new RandomGraphic(charCount, lineCount, wordHeight, wordWidth,
				fontSize, fontName);
	}

	/**
	 * 随机生成一个数字串，并以图像方式绘制，绘制结果输出到流out中
	 * 
	 * @param graphicFormat
	 *            设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
	 * @param out
	 *            图像结果输出流
	 * @return 随机生成的串的值
	 * @throws IOException
	 */
	public String drawNumber(String graphicFormat, OutputStream out)
			throws IOException {
		// 随机生成的串的值
		String charValue = RandomStringUtils.randomNumeric(charCount);

		return draw(charValue, graphicFormat, out);
	}

	/**
	 * 随机生成一个字母串，并以图像方式绘制，绘制结果输出到流out中
	 * 
	 * @param graphicFormat
	 *            设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
	 * @param out
	 *            图像结果输出流
	 * @return 随机生成的串的值
	 * @throws IOException
	 */
	public String drawAlpha(String graphicFormat, OutputStream out)
			throws IOException {
		// 随机生成的串的值
		String charValue = RandomStringUtils.randomAlphabetic(charCount)
				.toLowerCase();

		return draw(charValue, graphicFormat, out);
	}

	/**
	 * 随机生成一个字母数字混合串，并以图像方式绘制，绘制结果输出到流out中
	 * 
	 * @param graphicFormat
	 *            设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
	 * @param out
	 *            图像结果输出流
	 * @return String 随机生成的串的值
	 * @throws IOException
	 */
	public String drawAlphaNumber(String graphicFormat, OutputStream out)
			throws IOException {
		// 随机生成的串的值
		String charValue = RandomStringUtils.randomAlphanumeric(charCount)
				.toLowerCase();

		return draw(charValue, graphicFormat, out);
	}

	/**
	 * 以图像方式绘制字符串，绘制结果输出到流out中
	 * 
	 * @param charValue
	 *            要绘制的字符串
	 * @param graphicFormat
	 *            设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
	 * @param out
	 *            图像结果输出流
	 * @return 随机生成的串的值
	 * @throws IOException
	 */
	protected String draw(String charValue, String graphicFormat,
			OutputStream out) throws IOException {
		// 计算图像的宽度和高度
		int w = (charCount + 2) * wordWidth;
		int h = wordHeight * 3;

		// 创建内存图像区
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = bi.createGraphics();

		// 设置背景色
		Color backColor = Color.WHITE;
		g.setBackground(backColor);
		g.fillRect(0, 0, w, h);

		// 随机产生lineCount条干扰线，使图象中的认证码不易被其它程序探测到
		// 生成随机类
		Random random = new Random();

		for (int i = 0; i < lineCount; i++) {
			int x = random.nextInt(w);
			int y = random.nextInt(h);
			int xl = random.nextInt(3);
			int yl = random.nextInt(3);
			Color color = LINE_COLORS[randomInt(random, 0, LINE_COLORS.length)];
			g.setColor(color);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 设置font
		g.setFont(new Font(fontName, Font.ITALIC, fontSize));

		// 绘制charValue,每个字符颜色随机
		for (int i = 0; i < charCount; i++) {
			String c = charValue.substring(i, i + 1);
			Color color = CHAR_COLORS[randomInt(random, 0, CHAR_COLORS.length)];
			g.setColor(color);
			int xpos = (i + 1) * wordWidth;
			// 垂直方向上随机
			int ypos = randomInt(random, INIT_YPOS + wordHeight, INIT_YPOS
					+ wordHeight * 2);
			g.drawString(c, xpos, ypos);
		}

		g.dispose();
		bi.flush();
		// 输出到流
		ImageIO.write(bi, graphicFormat, out);

		return charValue;
	}

	/**
	 * 返回[from,to)之间的一个随机整数
	 * 
	 * @param random
	 *            随机数生成器
	 * @param from
	 *            起始值
	 * @param to
	 *            结束值
	 * @return [from,to)之间的一个随机整数
	 */
	protected int randomInt(Random random, int from, int to) {
		// Random r = new Random();
		return from + random.nextInt(to - from);
	}

	/**
	 * 给定范围获得随机颜色
	 * 
	 * @param fc
	 *            颜色范围小值
	 * @param bc
	 *            颜色范围大值
	 * @return Color 颜色值
	 */
	public Color getRandColor(int fc, int bc) {
		Random random = new Random();

		if (fc > 255) {
			fc = 255;
		}

		if (bc > 255) {
			bc = 255;
		}

		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);

		return new Color(r, g, b);
	}

	/**
	 * 测试
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		FileOutputStream out = new FileOutputStream("d:/randomimg.png");

		try {
			System.out.println(RandomGraphic.createInstance(4, 10, 15, 20, 28)
					.drawNumber(RandomGraphic.GRAPHIC_PNG, out));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			out.close();
		}

		out = new FileOutputStream("d:/randomimg.jpg");
		try {
			System.out.println(RandomGraphic.createInstance(4, 10).drawNumber(
					RandomGraphic.GRAPHIC_JPEG, out));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			out.close();
		}
	}
}
