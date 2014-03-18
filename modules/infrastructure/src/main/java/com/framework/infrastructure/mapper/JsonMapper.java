package com.framework.infrastructure.mapper;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * �򵥷�װJackson��ʵ��JSON String<->Java Object��Mapper.
 * 
 * ��װ��ͬ��������, ʹ�ò�ͬ��builder��������ʵ��.
 * 
 */
public class JsonMapper {

	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	private final ObjectMapper mapper;

	public JsonMapper() {
		this(Include.ALWAYS);
	}

	public JsonMapper(Include include) {
		mapper = new ObjectMapper();
		//�������ʱ�������Եķ��
		mapper.setSerializationInclusion(include);
		//��������ʱ������JSON�ַ����д��ڵ�Java����ʵ��û�е�����
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	/**
	 * ����ֻ�����Null�ҷ�Empty(��List.isEmpty)�����Ե�Json�ַ�����Mapper,�������ⲿ�ӿ���ʹ��.
	 */
	public static JsonMapper nonEmptyMapper() {
		return new JsonMapper(Include.NON_EMPTY);
	}

	/**
	 * ����ֻ�����ʼֵ���ı�����Ե�Json�ַ�����Mapper, ���Լ�Ĵ洢��ʽ���������ڲ��ӿ���ʹ�á�
	 */
	public static JsonMapper nonDefaultMapper() {
		return new JsonMapper(Include.NON_DEFAULT);
	}

	/**
	 * Object������POJO��Ҳ������Collection�����顣
	 * �������ΪNull, ����"null".
	 * �������Ϊ�ռ���, ����"[]".
	 */
	public String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * �����л�POJO���Collection��List<String>.
	 * 
	 * ���JSON�ַ���ΪNull��"null"�ַ���, ����Null.
	 * ���JSON�ַ���Ϊ"[]", ���ؿռ���.
	 * 
	 * ���跴���л�����Collection��List<MyBean>, ��ʹ��fromJson(String,JavaType)
	 * @see #fromJson(String, JavaType)
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * �����л�����Collection��List<Bean>, ��ʹ�ú���createCollectionType��������,Ȼ����ñ�����.
	 * @see #createCollectionType(Class, Class...)
	 */
	public <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return (T) mapper.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * ���췺�͵�Collection Type��:
	 * ArrayList<MyBean>, �����constructCollectionType(ArrayList.class,MyBean.class)
	 * HashMap<String,MyBean>, �����(HashMap.class,String.class, MyBean.class)
	 */
	public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	/**
	 * ��JSON�eֻ����Bean�Ĳ��֌��ԕr������һ���Ѵ���Bean��ֻ���wԓ���ֵČ���.
	 */
	public <T> T update(String jsonString, T object) {
		try {
			return (T) mapper.readerForUpdating(object).readValue(jsonString);
		} catch (JsonProcessingException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		} catch (IOException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		}
		return null;
	}

	/**
	 * ݔ��JSONP��ʽ����.
	 */
	public String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(functionName, object));
	}

	/**
	 * �O���Ƿ�ʹ��Enum��toString�������x��Enum,
	 * ��False�r�rʹ��Enum��name()�������x��Enum, Ĭ�J��False.
	 * ע�Ȿ����һ��Ҫ��Mapper������, ���е��x������֮ǰ�{��.
	 */
	public void enableEnumUseToString() {
		mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
	}

	/**
	 * ȡ��Mapper����һ�������û�ʹ���������л�API.
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
}
