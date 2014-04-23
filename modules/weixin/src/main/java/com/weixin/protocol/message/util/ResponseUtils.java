package com.weixin.protocol.message.util;

import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.weixin.protocol.message.response.ResponseBaseMessage;
import com.weixin.protocol.message.response.ResponseImageMessage;
import com.weixin.protocol.message.response.ResponseMusicMessage;
import com.weixin.protocol.message.response.ResponseNewsMessage;
import com.weixin.protocol.message.response.ResponseTextMessage;
import com.weixin.protocol.message.response.ResponseVideoMessage;
import com.weixin.protocol.message.response.ResponseVoiceMessage;
import com.weixin.protocol.message.response.element.ResponseArticle;

public class ResponseUtils {

	/**
	 * 扩展xstream使其支持CDATA
	 */
	static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	 

	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessage
	 *            文本消息对象
	 * @return xml
	 */
	public static String messageToXml(ResponseTextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * 图片消息对象转换成xml
	 * 
	 * @param imageMessage
	 *            图片消息对象
	 * @return xml
	 */
	public static String messageToXml(ResponseImageMessage imageMessage) {
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}

	/**
	 * 语音消息对象转换成xml
	 * 
	 * @param voiceMessage
	 *            语音消息对象
	 * @return xml
	 */
	public static String messageToXml(ResponseVoiceMessage voiceMessage) {
		xstream.alias("xml", voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}

	/**
	 * 视频消息对象转换成xml
	 * 
	 * @param videoMessage
	 *            视频消息对象
	 * @return xml
	 */
	public static String messageToXml(ResponseVideoMessage videoMessage) {
		xstream.alias("xml", videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}

	/**
	 * 音乐消息对象转换成xml
	 * 
	 * @param musicMessage
	 *            音乐消息对象
	 * @return xml
	 */
	public static String messageToXml(ResponseMusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}

	/**
	 * 图文消息对象转换成xml
	 * 
	 * @param newsMessage
	 *            图文消息对象
	 * @return xml
	 */
	public static String messageToXml(ResponseNewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new ResponseArticle().getClass());
		return xstream.toXML(newsMessage);
	}

}
