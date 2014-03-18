//package com.framework.infrastructure.utils;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.RenderingHints;
//import java.awt.Toolkit;
//import java.awt.image.BufferedImage;
//import java.awt.image.ColorModel;
//import java.awt.image.CropImageFilter;
//import java.awt.image.FilteredImageSource;
//import java.awt.image.ImageFilter;
//import java.awt.image.WritableRaster;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import javax.imageio.ImageIO;
//
//import org.apache.commons.io.FileUtils;
//
//import com.routon.h3.common.util.gif.AnimatedGifEncoder;
//import com.routon.h3.common.util.gif.GifDecoder;
//
///**
// * <pre>
// * 图像缩放、添加水印工具类，支持PNG、GIF、JPG格式的图像
// * </pre>
// */
//public class ImageHepler {
//
//	/**
//	 * 是否是GIF图像
//	 * 
//	 * @param fileName
//	 *            文件名
//	 * @return true是，false不是
//	 */
//	public static boolean isGif(String fileName) {
//		return "GIF".equals(getImageFormat(fileName));
//	}
//
//	/**
//	 * 是否是GIF图像
//	 * 
//	 * @param file
//	 *            图像文件
//	 * @return true是，false不是
//	 */
//	public static boolean isGif(File file) {
//		return "GIF".equals(getImageFormat(file));
//	}
//
//	/**
//	 * 是否是PNG图像
//	 * 
//	 * @param fileName
//	 *            文件名
//	 * @return true是，false不是
//	 */
//	public static boolean isPng(String fileName) {
//		return "PNG".equals(getImageFormat(fileName));
//	}
//
//	/**
//	 * 是否是PNG图像
//	 * 
//	 * @param file
//	 *            图像文件
//	 * @return true是，false不是
//	 */
//	public static boolean isPng(File file) {
//		return "PNG".equals(getImageFormat(file));
//	}
//
//	/**
//	 * 获取图像文件的文件格式
//	 * 
//	 * @param fileName
//	 *            文件名
//	 * @return 图像文件格式PNG, GIF, 如果非PNG, GIF图像类型那么返回unsupported
//	 */
//	public static String getImageFormat(String fileName) {
//		return getImageFormat(new File(fileName));
//	}
//
//	/**
//	 * 获取图像文件的文件格式
//	 * 
//	 * @param file
//	 *            图像文件
//	 * @return 图像文件格式PNG, GIF, 如果非PNG, GIF图像类型那么返回unsupported
//	 */
//	public static String getImageFormat(File file) {
//		FileInputStream in = null;
//		byte[] b = new byte[10];
//
//		try {
//			in = new FileInputStream(file);
//			int num = in.read(b);
//
//			if (num != 10) {
//				return "unsupported";
//			}
//		} catch (Exception e) {
//			return "unsupported";
//		} finally {
//			if (in != null) {
//				try {
//					in.close();
//				} catch (Exception ex) {
//				}
//			}
//		}
//
//		if (b[0] == (byte) 'G' && b[1] == (byte) 'I' && b[2] == (byte) 'F') {
//			return "GIF";
//		} else if (b[1] == (byte) 'P' && b[2] == (byte) 'N'
//				&& b[3] == (byte) 'G') {
//			return "PNG";
//		} else {
//			return "unsupported";
//		}
//	}
//
//	/**
//	 * 缩放Gif图像
//	 * 
//	 * @param source
//	 *            源文件地址
//	 * @param dest
//	 *            目标文件地址
//	 * @param width
//	 *            期望宽度
//	 * @param height
//	 *            期望高度
//	 * @throws Exception
//	 *             异常
//	 */
//	public static void resizeGIF(String source, String dest, int width,
//			int height) throws Exception {
//		resizeGIF(new File(source), new File(dest), width, height);
//	}
//
//	/**
//	 * 缩放Gif图像
//	 * 
//	 * @param source
//	 *            源文件
//	 * @param dest
//	 *            目标文件
//	 * @param width
//	 *            期望宽度
//	 * @param height
//	 *            期望高度
//	 * @throws Exception
//	 *             异常
//	 */
//	public static void resizeGIF(File source, File dest, int width, int height)
//			throws Exception {
//		InputStream in = null;
//		OutputStream out = null;
//
//		try {
//			// 生成一个GifDecoder
//			GifDecoder gifDecoder = new GifDecoder();
//			in = new FileInputStream(source);
//
//			if (GifDecoder.STATUS_FORMAT_ERROR == gifDecoder.read(in)) {
//				// 调用read方法读入文件流，出错时，抛出异常。
//				throw new Exception("读取文件" + source + "出错");
//			}
//
//			// 动态画面编码器
//			AnimatedGifEncoder animatedGifEncoder = new AnimatedGifEncoder();
//			out = new FileOutputStream(dest);
//			animatedGifEncoder.start(out);
//			// 设置循环显示的次数
//			animatedGifEncoder.setRepeat(gifDecoder.getLoopCount());
//			animatedGifEncoder.setQuality(19);
//
//			// 取得帧的个数
//			int frameCount = gifDecoder.getFrameCount();
//			for (int i = 0; i < frameCount; i++) {
//				// 取得帧
//				BufferedImage sourceFrame = gifDecoder.getFrame(i);
//				// 缩放
//				BufferedImage targetFrame = resizeImage(sourceFrame, width,
//						height);
//				animatedGifEncoder.setTransparent(sourceFrame.createGraphics()
//						.getColor());
//				// 设置每帧显示间隔时间
//				animatedGifEncoder.setDelay(gifDecoder.getDelay(i));
//				animatedGifEncoder.addFrame(targetFrame);
//			}
//
//			animatedGifEncoder.finish();
//		} finally {
//			if (in != null) {
//				try {
//					in.close();
//				} catch (Exception ex) {
//				}
//			}
//
//			if (out != null) {
//				try {
//					out.close();
//				} catch (Exception ex) {
//				}
//			}
//		}
//	}
//
//	/**
//	 * 缩放GIF,JPEG,PNG图像文件
//	 * 
//	 * @param source
//	 *            源文件地址
//	 * @param dest
//	 *            目标文件地址
//	 * @param width
//	 *            目标宽度
//	 * @param height
//	 *            目标高度
//	 * @throws Exception
//	 *             异常信息
//	 */
//	public static void resizeImage(String source, String dest, int width,
//			int height) throws Exception {
//		resizeImage(new File(source), new File(dest), width, height);
//	}
//
//	/**
//	 * 缩放GIF,JPEG,PNG图像文件
//	 * 
//	 * @param source
//	 *            源文件
//	 * @param dest
//	 *            目标文件
//	 * @param width
//	 *            目标宽度
//	 * @param height
//	 *            目标高度
//	 * @throws Exception
//	 *             异常信息
//	 */
//	public static void resizeImage(File source, File dest, int width, int height)
//			throws Exception {
//		BufferedImage srcImage = ImageIO.read(source);
//		int sw = srcImage.getWidth();
//		int sh = srcImage.getHeight();
//
//		if (sw <= width && sh <= height) {
//			FileUtils.copyFile(source, dest);
//
//			return;
//		}
//
//		if (isGif(source)) {
//			resizeGIF(source, dest, width, height);
//		} else {
//			String formatName = null;
//			if (isPng(source)) {
//				formatName = "png";
//			} else {
//				formatName = "jpeg";
//			}
//			BufferedImage destImage = resizeImage(srcImage, width, height);
//			ImageIO.write(destImage, formatName, dest);
//		}
//	}
//
//	/**
//	 * 实现图像的等比缩放截取，支持PNG、JPG图像
//	 * 
//	 * @param srcImage
//	 *            原始图像
//	 * @param width
//	 *            缩放后的宽度
//	 * @param height
//	 *            缩放后的高度
//	 * @return 缩放后的图像
//	 */
//	private static BufferedImage resizeImage(BufferedImage srcImage, int width,
//			int height) {
//		// 原图的大小
//		int sw = srcImage.getWidth();
//		int sh = srcImage.getHeight();
//
//		if (sw <= width && sh <= height) {
//			// 如果原图像的大小小于要缩放的图像大小，直接将要缩放的图像复制过去
//			return srcImage;
//		} else {
//			// 截取位置
//			int x = 0;
//			int y = 0;
//			// 对原图进行缩放后的大小
//			int w = sw;
//			int h = sh;
//
//			if (sh * width > height * sw) {
//				// 对高度进行截取
//				w = sw;
//				h = (sw * height) / width;
//				y = (sh - h) / 2;
//			} else {
//				// 对宽度进行截取
//				h = sh;
//				w = (sh * width) / height;
//				x = (sw - w) / 2;
//			}
//
//			// 先切割
//			ImageFilter filter = new CropImageFilter(x, y, w, h);
//			Image img = Toolkit.getDefaultToolkit().createImage(
//					new FilteredImageSource(srcImage.getSource(), filter));
//			// 再缩放
//			img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//
//			BufferedImage destImage = null;
//			int type = srcImage.getType();
//			if (type == BufferedImage.TYPE_CUSTOM) {
//				// handmade
//				ColorModel cm = srcImage.getColorModel();
//				WritableRaster raster = cm.createCompatibleWritableRaster(
//						width, height);
//				boolean alphaPremultiplied = cm.isAlphaPremultiplied();
//				destImage = new BufferedImage(cm, raster, alphaPremultiplied,
//						null);
//			} else {
//				destImage = new BufferedImage(width, height, type);
//			}
//
//			Graphics g = destImage.getGraphics();
//			g.drawImage(img, 0, 0, null);
//			g.dispose();
//
//			return destImage;
//		}
//	}
//
//	/**
//	 * 给Gif图像生成水印
//	 * 
//	 * @param source
//	 *            源文件地址
//	 * @param dest
//	 *            目标文件地址
//	 * @param text
//	 *            水印内容
//	 * @throws Exception
//	 *             异常
//	 */
//	public static void createGifWatermark(String source, String dest,
//			String text) throws Exception {
//		createGifWatermark(new File(source), new File(dest), text);
//	}
//
//	/**
//	 * 给Gif图像生成水印
//	 * 
//	 * @param source
//	 *            源文件
//	 * @param dest
//	 *            目标文件
//	 * @param text
//	 *            水印内容
//	 * @throws Exception
//	 *             异常
//	 */
//	public static void createGifWatermark(File source, File dest, String text)
//			throws Exception {
//		InputStream in = null;
//		OutputStream out = null;
//
//		try {
//			// 生成一个GifDecoder
//			GifDecoder gifDecoder = new GifDecoder();
//			in = new FileInputStream(source);
//
//			if (GifDecoder.STATUS_FORMAT_ERROR == gifDecoder.read(in)) {
//				// 调用read方法读入文件流，出错时，抛出异常。
//				throw new Exception("读取文件" + source + "出错");
//			}
//
//			// 动态画面编码器
//			AnimatedGifEncoder animatedGifEncoder = new AnimatedGifEncoder();
//			out = new FileOutputStream(dest);
//			animatedGifEncoder.start(out);
//			// 设置循环显示的次数
//			animatedGifEncoder.setRepeat(gifDecoder.getLoopCount());
//			animatedGifEncoder.setQuality(19);
//
//			// 取得帧的个数
//			int frameCount = gifDecoder.getFrameCount();
//			for (int i = 0; i < frameCount; i++) {
//				// 取得帧
//				BufferedImage sourceFrame = gifDecoder.getFrame(i);
//				// 加水印
//				BufferedImage targetFrame = createWatermark(sourceFrame, text);
//				animatedGifEncoder.setTransparent(sourceFrame.createGraphics()
//						.getColor());
//				// 设置每帧显示间隔时间
//				animatedGifEncoder.setDelay(gifDecoder.getDelay(i));
//				animatedGifEncoder.addFrame(targetFrame);
//			}
//
//			animatedGifEncoder.finish();
//		} finally {
//			if (in != null) {
//				try {
//					in.close();
//				} catch (Exception ex) {
//				}
//			}
//
//			if (out != null) {
//				try {
//					out.close();
//				} catch (Exception ex) {
//				}
//			}
//		}
//	}
//
//	/**
//	 * 给GIF,JPEG,PNG图像生成水印
//	 * 
//	 * @param source
//	 *            源文件地址
//	 * @param dest
//	 *            目标文件地址
//	 * @param text
//	 *            水印内容
//	 * @throws Exception
//	 *             异常
//	 */
//	public static void createWatermark(String source, String dest, String text)
//			throws Exception {
//		createWatermark(new File(source), new File(dest), text);
//	}
//
//	/**
//	 * 给GIF,JPEG,PNG图像生成水印
//	 * 
//	 * @param source
//	 *            源文件
//	 * @param dest
//	 *            目标文件
//	 * @param text
//	 *            水印内容
//	 * @throws Exception
//	 *             异常
//	 */
//	public static void createWatermark(File source, File dest, String text)
//			throws Exception {
//		BufferedImage image = ImageIO.read(source);
//		int sw = image.getWidth();
//		int sh = image.getHeight();
//
//		if (sw < 70 || sh < 10) {
//			FileUtils.copyFile(source, dest);
//
//			return;
//		}
//
//		if (isGif(source)) {
//			createGifWatermark(source, dest, text);
//		} else {
//			String formatName = null;
//			BufferedImage destImage = createWatermark(image, text);
//			if (isPng(source)) {
//				formatName = "png";
//			} else {
//				formatName = "jpeg";
//			}
//			ImageIO.write(destImage, formatName, dest);
//		}
//	}
//
//	/**
//	 * 给图像生成水印，支持PNG,JPG图像
//	 * 
//	 * @param source
//	 *            源文件
//	 * @param text
//	 *            水印内容
//	 * @throws Exception
//	 *             异常
//	 */
//	private static BufferedImage createWatermark(BufferedImage source,
//			String text) throws Exception {
//		int width = source.getWidth();
//		int height = source.getHeight();
//
//		int fontSizeX = width / 45;
//		int fontSizeY = height / 15;
//		int fontSize = fontSizeX < fontSizeY ? fontSizeX : fontSizeY;
//		fontSize = fontSize < 12 ? 12 : fontSize;
//
//		int chinese = 0;
//		int length = text.length();
//		for (int i = 0; i < length; i++) {
//			char chr = text.charAt(i);
//			if (chr <= 0x9FA5 && chr >= 0x4E00) {
//				chinese++;
//			}
//		}
//
//		fontSizeX = width - fontSize * ((length - chinese) * 3 + chinese * 6)
//				/ 5;
//		fontSizeY = height - fontSize;
//		fontSizeX = fontSizeX < 0 ? 0 : fontSizeX;
//		fontSizeY = fontSizeY < 0 ? 0 : fontSizeY;
//
//		// 取得图形上下文
//		Graphics2D g = source.createGraphics();
//		// 去除锯齿(当设置的字体过大的时候,会出现锯齿)
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//				RenderingHints.VALUE_ANTIALIAS_ON);
//		g.setColor(Color.red);
//		g.setFont(new Font(null, Font.HANGING_BASELINE, fontSize));
//		// 在指定坐标除添加文字
//		g.drawString(text, fontSizeX, fontSizeY);
//		g.dispose();
//
//		return source;
//	}
//
//}
