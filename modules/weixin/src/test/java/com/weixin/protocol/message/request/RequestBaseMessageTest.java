package com.weixin.protocol.message.request;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.weixin.protocol.message.response.ResponseTextMessage;
import com.weixin.protocol.message.util.XmlUtils;

public class RequestBaseMessageTest {

	/*
	 * TODO 1.抽取验证信息的通用部分; 2.分别在独立的方法加入自己需要的部分
	 * 
	 * 1.抽取验证方法的通用部分 2.分别在各自的方法完成
	 */
	@Test
	public void requestTextParse() {
		String requestXML = "<xml> <ToUserName><![CDATA[toUser]]></ToUserName> <FromUserName><![CDATA[fromUser]]></FromUserName> <CreateTime>1348831860</CreateTime> <MsgType><![CDATA[text]]></MsgType> <Content><![CDATA[this is a test陈]]></Content> <MsgId>1234567890123456</MsgId> </xml>";
		InputStream stream = new ByteArrayInputStream(
				requestXML.getBytes(Charset.forName("UTF-8")));
		Map<String, String> map = XmlUtils.parseXml(stream);
		RequestTextMessage text = new RequestTextMessage(map);

		Assert.assertEquals(text.getToUserName(), "toUser");
		Assert.assertEquals(text.getFromUserName(), "fromUser");
		Assert.assertEquals(text.getCreateTime(), 1348831860L);
		Assert.assertEquals(text.getMsgType(), "text");
		Assert.assertEquals(text.getContent(), "this is a test陈");
		Assert.assertEquals(text.getMsgId(), 1234567890123456L);
	}

	/**
	 * TODO 需要编写从string到xml的解析，然后对比节点和值的工具类
	 */
	@Test
	public void responseTextParse() {
		ResponseTextMessage rTextMessage = new ResponseTextMessage();
		rTextMessage.setFromUserName("fromUser");
		rTextMessage.setToUserName("toUser");
		rTextMessage.setCreateTime(1348831860L);
		rTextMessage.setMsgType("text");
		rTextMessage.setContent("this is a test陈");
		String xml = rTextMessage.toXml().replaceAll("\n", "");
		System.out.println(xml);

	}
}
