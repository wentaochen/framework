package com.framework.interfaces.web.spring.common;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

/**
 * <pre>
 * @author chenwentao
 *
 * @version 0.9
 *
 * �޸İ汾: 0.9
 * �޸�����: Apr 24, 2011
 * �޸��� :  chenwentao
 * �޸�˵��: �������
 * ������ ��
 * </pre>
 */
@Component
public class EncodingPostProcessor implements BeanPostProcessor {

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof AnnotationMethodHandlerAdapter) {
			HttpMessageConverter<?>[] convs = ((AnnotationMethodHandlerAdapter) bean)
					.getMessageConverters();
			for (HttpMessageConverter<?> conv : convs) {
				if (conv instanceof StringHttpMessageConverter) {
					((StringHttpMessageConverter) conv)
							.setSupportedMediaTypes(Arrays
									.asList(new MediaType("text", "html",
											Charset.forName("UTF-8"))));
				}
			}
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

}
