package com.weixin.protocol.message.response;

import java.util.List;

import com.weixin.protocol.message.response.element.ResponseArticle;
import com.weixin.protocol.message.util.ResponseUtils;

/**
 * 文本消息
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class ResponseNewsMessage extends ResponseBaseMessage {
	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<ResponseArticle> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<ResponseArticle> getArticles() {
		return Articles;
	}

	public void setArticles(List<ResponseArticle> articles) {
		Articles = articles;
	}

	@Override
	public String toXml() {
		return ResponseUtils.messageToXml(this);
	}
}
