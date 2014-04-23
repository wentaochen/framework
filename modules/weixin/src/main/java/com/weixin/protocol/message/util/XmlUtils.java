package com.weixin.protocol.message.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlUtils {

	public static void main(String[] args) {
		System.out.println(XmlUtils.class.getSimpleName());
	}

	private static Logger log = Logger.getLogger(XmlUtils.class.getName());

	/**
	 * 解析request中的xml 并将数据存储到一个Map中返回
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(InputStream inputStream) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			Element root = document.getRootElement();
			List<Element> elementList = root.elements();
			for (Element e : elementList) {
				map.put(e.getName(), e.getText());
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

		return map;
	}
}
