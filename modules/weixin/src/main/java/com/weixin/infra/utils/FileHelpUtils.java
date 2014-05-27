package com.weixin.infra.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.weixin.infra.config.UploadConfig;

/**
 * 操作上传的文件，并存入到服务器地址中
 * 
 * @author chenwentao
 * 
 */
public abstract class FileHelpUtils {

	public static void main(String[] args) {
		try {
			doSome();
		} catch (Exception e) {
			System.out.println("main");
		}
	}

	static void doSome() throws Exception {
		try {
			System.out.println("input");
			throw new RuntimeException();
		} catch (Exception e) {
			System.out.println("exception");
			throw new RuntimeException("runtime exception");
		} finally {
			System.out.println("finally");
		}
	}

	/**
	 * @param oriFileName
	 *            文件原始名称
	 * 
	 * @param inputStream
	 *            输入流
	 * 
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public static String copyFile(String oriFileName, InputStream inputStream)
			throws Exception {
		String fileName = FileHelpUtils.produceImageName(oriFileName);
		String filePath = UploadConfig
				.getProperty(UploadConfig.UPLOAD_SAVEPATH) + fileName;
		FileUtils.copyInputStreamToFile(inputStream, new File(filePath));

		return fileName;
		/*
		 * String newFileName = produceImageName(oriFileName); String savePath =
		 * Config.getProperty(Config.UPLOAD_SAVEPATH); OutputStream outputStream
		 * = null; try { outputStream = new FileOutputStream( new File(savePath
		 * + newFileName)); IOUtils.copy(inputStream, outputStream); } catch
		 * (Exception e) { throw e; } finally {
		 * IOUtils.closeQuietly(outputStream);
		 * IOUtils.closeQuietly(inputStream); }
		 * 
		 * return newFileName;
		 */
	}

	/**
	 * 删除服务器上的文件
	 * 
	 * @param fileName
	 */
	public static void deleteFile(String fileName) {
		FileUtils.deleteQuietly(new File(UploadConfig
				.getProperty(UploadConfig.UPLOAD_SAVEPATH) + fileName));
	}

	/**
	 * 产生新的文件名称
	 * 
	 * @param originalFileName
	 * @return
	 */
	public static String produceImageName(String originalFileName) {
		StringBuilder sb = new StringBuilder();
		sb.append(System.nanoTime());
		if (StringUtils.indexOf(originalFileName, ".") == -1) {
			return sb.toString();
		} else {
			sb.append(".");
			sb.append(StringUtils.substringAfterLast(originalFileName, "."));
			return sb.toString();
		}
	}

	/**
	 * @param file
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public static String uploadFile(MultipartFile file) throws Exception {
		return copyFile(file.getOriginalFilename(), file.getInputStream());
	}

	/**
	 * 直接给定文件的完整路径（带文件名和后缀） 如果父路径不存在，会报错
	 * 
	 * @param bytes
	 * @param filePath
	 * @throws IOException
	 */
	public static void saveFileInDisk(byte[] bytes, String filePath)
			throws IOException {
		FileCopyUtils.copy(bytes, new File(filePath));
	}

	/**
	 * 给的文件的父路径，和文件名，父路径不存在，会自动创建
	 * 
	 * @param parentPath
	 * @param bytes
	 * @param fileName
	 * @throws IOException
	 */
	public static void saveFileInDisk(String parentPath, byte[] bytes,
			String fileName) throws IOException {
		File parent = new File(parentPath);
		if (!parent.exists()) {
			parent.mkdir();
		}
		FileCopyUtils.copy(bytes, new File(parentPath + File.separator
				+ fileName));
	}
}
