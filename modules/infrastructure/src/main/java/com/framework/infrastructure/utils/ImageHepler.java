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
// * ͼ�����š����ˮӡ�����֧࣬��PNG��GIF��JPG��ʽ��ͼ��
// * </pre>
// */
//public class ImageHepler {
//
//	/**
//	 * �Ƿ���GIFͼ��
//	 * 
//	 * @param fileName
//	 *            �ļ���
//	 * @return true�ǣ�false����
//	 */
//	public static boolean isGif(String fileName) {
//		return "GIF".equals(getImageFormat(fileName));
//	}
//
//	/**
//	 * �Ƿ���GIFͼ��
//	 * 
//	 * @param file
//	 *            ͼ���ļ�
//	 * @return true�ǣ�false����
//	 */
//	public static boolean isGif(File file) {
//		return "GIF".equals(getImageFormat(file));
//	}
//
//	/**
//	 * �Ƿ���PNGͼ��
//	 * 
//	 * @param fileName
//	 *            �ļ���
//	 * @return true�ǣ�false����
//	 */
//	public static boolean isPng(String fileName) {
//		return "PNG".equals(getImageFormat(fileName));
//	}
//
//	/**
//	 * �Ƿ���PNGͼ��
//	 * 
//	 * @param file
//	 *            ͼ���ļ�
//	 * @return true�ǣ�false����
//	 */
//	public static boolean isPng(File file) {
//		return "PNG".equals(getImageFormat(file));
//	}
//
//	/**
//	 * ��ȡͼ���ļ����ļ���ʽ
//	 * 
//	 * @param fileName
//	 *            �ļ���
//	 * @return ͼ���ļ���ʽPNG, GIF, �����PNG, GIFͼ��������ô����unsupported
//	 */
//	public static String getImageFormat(String fileName) {
//		return getImageFormat(new File(fileName));
//	}
//
//	/**
//	 * ��ȡͼ���ļ����ļ���ʽ
//	 * 
//	 * @param file
//	 *            ͼ���ļ�
//	 * @return ͼ���ļ���ʽPNG, GIF, �����PNG, GIFͼ��������ô����unsupported
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
//	 * ����Gifͼ��
//	 * 
//	 * @param source
//	 *            Դ�ļ���ַ
//	 * @param dest
//	 *            Ŀ���ļ���ַ
//	 * @param width
//	 *            �������
//	 * @param height
//	 *            �����߶�
//	 * @throws Exception
//	 *             �쳣
//	 */
//	public static void resizeGIF(String source, String dest, int width,
//			int height) throws Exception {
//		resizeGIF(new File(source), new File(dest), width, height);
//	}
//
//	/**
//	 * ����Gifͼ��
//	 * 
//	 * @param source
//	 *            Դ�ļ�
//	 * @param dest
//	 *            Ŀ���ļ�
//	 * @param width
//	 *            �������
//	 * @param height
//	 *            �����߶�
//	 * @throws Exception
//	 *             �쳣
//	 */
//	public static void resizeGIF(File source, File dest, int width, int height)
//			throws Exception {
//		InputStream in = null;
//		OutputStream out = null;
//
//		try {
//			// ����һ��GifDecoder
//			GifDecoder gifDecoder = new GifDecoder();
//			in = new FileInputStream(source);
//
//			if (GifDecoder.STATUS_FORMAT_ERROR == gifDecoder.read(in)) {
//				// ����read���������ļ���������ʱ���׳��쳣��
//				throw new Exception("��ȡ�ļ�" + source + "����");
//			}
//
//			// ��̬���������
//			AnimatedGifEncoder animatedGifEncoder = new AnimatedGifEncoder();
//			out = new FileOutputStream(dest);
//			animatedGifEncoder.start(out);
//			// ����ѭ����ʾ�Ĵ���
//			animatedGifEncoder.setRepeat(gifDecoder.getLoopCount());
//			animatedGifEncoder.setQuality(19);
//
//			// ȡ��֡�ĸ���
//			int frameCount = gifDecoder.getFrameCount();
//			for (int i = 0; i < frameCount; i++) {
//				// ȡ��֡
//				BufferedImage sourceFrame = gifDecoder.getFrame(i);
//				// ����
//				BufferedImage targetFrame = resizeImage(sourceFrame, width,
//						height);
//				animatedGifEncoder.setTransparent(sourceFrame.createGraphics()
//						.getColor());
//				// ����ÿ֡��ʾ���ʱ��
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
//	 * ����GIF,JPEG,PNGͼ���ļ�
//	 * 
//	 * @param source
//	 *            Դ�ļ���ַ
//	 * @param dest
//	 *            Ŀ���ļ���ַ
//	 * @param width
//	 *            Ŀ����
//	 * @param height
//	 *            Ŀ��߶�
//	 * @throws Exception
//	 *             �쳣��Ϣ
//	 */
//	public static void resizeImage(String source, String dest, int width,
//			int height) throws Exception {
//		resizeImage(new File(source), new File(dest), width, height);
//	}
//
//	/**
//	 * ����GIF,JPEG,PNGͼ���ļ�
//	 * 
//	 * @param source
//	 *            Դ�ļ�
//	 * @param dest
//	 *            Ŀ���ļ�
//	 * @param width
//	 *            Ŀ����
//	 * @param height
//	 *            Ŀ��߶�
//	 * @throws Exception
//	 *             �쳣��Ϣ
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
//	 * ʵ��ͼ��ĵȱ����Ž�ȡ��֧��PNG��JPGͼ��
//	 * 
//	 * @param srcImage
//	 *            ԭʼͼ��
//	 * @param width
//	 *            ���ź�Ŀ��
//	 * @param height
//	 *            ���ź�ĸ߶�
//	 * @return ���ź��ͼ��
//	 */
//	private static BufferedImage resizeImage(BufferedImage srcImage, int width,
//			int height) {
//		// ԭͼ�Ĵ�С
//		int sw = srcImage.getWidth();
//		int sh = srcImage.getHeight();
//
//		if (sw <= width && sh <= height) {
//			// ���ԭͼ��Ĵ�СС��Ҫ���ŵ�ͼ���С��ֱ�ӽ�Ҫ���ŵ�ͼ���ƹ�ȥ
//			return srcImage;
//		} else {
//			// ��ȡλ��
//			int x = 0;
//			int y = 0;
//			// ��ԭͼ�������ź�Ĵ�С
//			int w = sw;
//			int h = sh;
//
//			if (sh * width > height * sw) {
//				// �Ը߶Ƚ��н�ȡ
//				w = sw;
//				h = (sw * height) / width;
//				y = (sh - h) / 2;
//			} else {
//				// �Կ�Ƚ��н�ȡ
//				h = sh;
//				w = (sh * width) / height;
//				x = (sw - w) / 2;
//			}
//
//			// ���и�
//			ImageFilter filter = new CropImageFilter(x, y, w, h);
//			Image img = Toolkit.getDefaultToolkit().createImage(
//					new FilteredImageSource(srcImage.getSource(), filter));
//			// ������
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
//	 * ��Gifͼ������ˮӡ
//	 * 
//	 * @param source
//	 *            Դ�ļ���ַ
//	 * @param dest
//	 *            Ŀ���ļ���ַ
//	 * @param text
//	 *            ˮӡ����
//	 * @throws Exception
//	 *             �쳣
//	 */
//	public static void createGifWatermark(String source, String dest,
//			String text) throws Exception {
//		createGifWatermark(new File(source), new File(dest), text);
//	}
//
//	/**
//	 * ��Gifͼ������ˮӡ
//	 * 
//	 * @param source
//	 *            Դ�ļ�
//	 * @param dest
//	 *            Ŀ���ļ�
//	 * @param text
//	 *            ˮӡ����
//	 * @throws Exception
//	 *             �쳣
//	 */
//	public static void createGifWatermark(File source, File dest, String text)
//			throws Exception {
//		InputStream in = null;
//		OutputStream out = null;
//
//		try {
//			// ����һ��GifDecoder
//			GifDecoder gifDecoder = new GifDecoder();
//			in = new FileInputStream(source);
//
//			if (GifDecoder.STATUS_FORMAT_ERROR == gifDecoder.read(in)) {
//				// ����read���������ļ���������ʱ���׳��쳣��
//				throw new Exception("��ȡ�ļ�" + source + "����");
//			}
//
//			// ��̬���������
//			AnimatedGifEncoder animatedGifEncoder = new AnimatedGifEncoder();
//			out = new FileOutputStream(dest);
//			animatedGifEncoder.start(out);
//			// ����ѭ����ʾ�Ĵ���
//			animatedGifEncoder.setRepeat(gifDecoder.getLoopCount());
//			animatedGifEncoder.setQuality(19);
//
//			// ȡ��֡�ĸ���
//			int frameCount = gifDecoder.getFrameCount();
//			for (int i = 0; i < frameCount; i++) {
//				// ȡ��֡
//				BufferedImage sourceFrame = gifDecoder.getFrame(i);
//				// ��ˮӡ
//				BufferedImage targetFrame = createWatermark(sourceFrame, text);
//				animatedGifEncoder.setTransparent(sourceFrame.createGraphics()
//						.getColor());
//				// ����ÿ֡��ʾ���ʱ��
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
//	 * ��GIF,JPEG,PNGͼ������ˮӡ
//	 * 
//	 * @param source
//	 *            Դ�ļ���ַ
//	 * @param dest
//	 *            Ŀ���ļ���ַ
//	 * @param text
//	 *            ˮӡ����
//	 * @throws Exception
//	 *             �쳣
//	 */
//	public static void createWatermark(String source, String dest, String text)
//			throws Exception {
//		createWatermark(new File(source), new File(dest), text);
//	}
//
//	/**
//	 * ��GIF,JPEG,PNGͼ������ˮӡ
//	 * 
//	 * @param source
//	 *            Դ�ļ�
//	 * @param dest
//	 *            Ŀ���ļ�
//	 * @param text
//	 *            ˮӡ����
//	 * @throws Exception
//	 *             �쳣
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
//	 * ��ͼ������ˮӡ��֧��PNG,JPGͼ��
//	 * 
//	 * @param source
//	 *            Դ�ļ�
//	 * @param text
//	 *            ˮӡ����
//	 * @throws Exception
//	 *             �쳣
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
//		// ȡ��ͼ��������
//		Graphics2D g = source.createGraphics();
//		// ȥ�����(�����õ���������ʱ��,����־��)
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//				RenderingHints.VALUE_ANTIALIAS_ON);
//		g.setColor(Color.red);
//		g.setFont(new Font(null, Font.HANGING_BASELINE, fontSize));
//		// ��ָ��������������
//		g.drawString(text, fontSizeX, fontSizeY);
//		g.dispose();
//
//		return source;
//	}
//
//}
