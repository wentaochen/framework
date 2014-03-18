/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.framework.infrastructure.mapper;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;

import com.framework.infrastructure.utils.Exceptions;

/**
 * ʹ��Jaxb2.0ʵ��XML<->Java Object��Mapper.
 * 
 * �ڴ���ʱ��Ҫ�趨������Ҫ���л���Root�����Class.
 * �ر�֧��Root������Collection������.
 * 
 * @author calvin
 */
public class JaxbMapper {
	//���̰߳�ȫ��Context.
	private JAXBContext jaxbContext;

	/**
	 * @param rootTypes ������Ҫ���л���Root�����Class.
	 */
	public JaxbMapper(Class<?>... rootTypes) {
		try {
			jaxbContext = JAXBContext.newInstance(rootTypes);
		} catch (JAXBException e) {
			Exceptions.unchecked(e);
		}
	}

	/**
	 * Java Object->Xml without encoding.
	 */
	public String toXml(Object root) {
		return toXml(root, null);
	}

	/**
	 * Java Object->Xml with encoding.
	 */
	public String toXml(Object root, String encoding) {
		try {
			StringWriter writer = new StringWriter();
			createMarshaller(encoding).marshal(root, writer);
			return writer.toString();
		} catch (JAXBException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * Java Collection->Xml without encoding, �ر�֧��Root Element��Collection������.
	 */
	public String toXml(Collection<?> root, String rootName) {
		return toXml(root, rootName, null);
	}

	/**
	 * Java Collection->Xml with encoding, �ر�֧��Root Element��Collection������.
	 */
	public String toXml(Collection<?> root, String rootName, String encoding) {
		try {
			CollectionWrapper wrapper = new CollectionWrapper();
			wrapper.collection = root;

			JAXBElement<CollectionWrapper> wrapperElement = new JAXBElement<CollectionWrapper>(new QName(rootName),
					CollectionWrapper.class, wrapper);

			StringWriter writer = new StringWriter();
			createMarshaller(encoding).marshal(wrapperElement, writer);

			return writer.toString();
		} catch (JAXBException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * Xml->Java Object.
	 */
	public <T> T fromXml(String xml) {
		try {
			StringReader reader = new StringReader(xml);
			return (T) createUnmarshaller().unmarshal(reader);
		} catch (JAXBException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * ����Marshaller���趨encoding(��Ϊnull).
	 * �̲߳���ȫ����Ҫÿ�δ�����pooling��
	 */
	public Marshaller createMarshaller(String encoding) {
		try {
			Marshaller marshaller = jaxbContext.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			if (StringUtils.isNotBlank(encoding)) {
				marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			}

			return marshaller;
		} catch (JAXBException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * ����UnMarshaller.
	 * �̲߳���ȫ����Ҫÿ�δ�����pooling��
	 */
	public Unmarshaller createUnmarshaller() {
		try {
			return jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * ��װRoot Element �� Collection�����.
	 */
	public static class CollectionWrapper {

		@XmlAnyElement
		protected Collection<?> collection;
	}
}
