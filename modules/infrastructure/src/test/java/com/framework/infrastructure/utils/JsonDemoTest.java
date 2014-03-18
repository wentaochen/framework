package com.framework.infrastructure.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotSame;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.framework.infrastructure.mapper.JsonMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * ��ʾJackson�Ļ���ʹ�÷�ʽ������������Feature.
 * 
 * @author calvin
 */
public class JsonDemoTest {

	private static JsonMapper mapper = JsonMapper.nonDefaultMapper();

	//// �������� ��ʾ ////

	/**
	 * ���л�����/���ϵ�Json�ַ���.
	 */
	@Test
	public void toJson() throws Exception {

		JsonMapper toJsonMapper = new JsonMapper();

		//Bean
		TestBean bean = new TestBean("A");
		String beanString = mapper.toJson(bean);
		System.out.println("Bean:" + beanString);
		assertEquals("{\"name\":\"A\"}", beanString);

		System.out.println(toJsonMapper.toJson(bean));

		//Map
		Map<String, Object> map = Maps.newLinkedHashMap();
		map.put("name", "A");
		map.put("age", 2);
		String mapString = mapper.toJson(map);
		System.out.println("Map:" + mapString);
		assertEquals("{\"name\":\"A\",\"age\":2}", mapString);

		//List<String>
		List<String> stringList = Lists.newArrayList("A", "B", "C");
		String listString = mapper.toJson(stringList);
		System.out.println("String List:" + listString);
		assertEquals("[\"A\",\"B\",\"C\"]", listString);

		//List<Bean>
		List<TestBean> beanList = Lists.newArrayList(new TestBean("A"), new TestBean("B"));
		String beanListString = mapper.toJson(beanList);
		System.out.println("Bean List:" + beanListString);
		assertEquals("[{\"name\":\"A\"},{\"name\":\"B\"}]", beanListString);

		//Bean[]
		TestBean[] beanArray = new TestBean[] { new TestBean("A"), new TestBean("B") };
		String beanArrayString = mapper.toJson(beanArray);
		System.out.println("Array List:" + beanArrayString);
		assertEquals("[{\"name\":\"A\"},{\"name\":\"B\"}]", beanArrayString);
	}

	/**
	 * ��Json�ַ��������л�����/����.
	 */
	@Test
	public void fromJson() throws Exception {
		//Bean
		String beanString = "{\"name\":\"A\"}";
		TestBean bean = mapper.fromJson(beanString, TestBean.class);
		System.out.println("Bean:" + bean);

		//Map
		String mapString = "{\"name\":\"A\",\"age\":2}";
		Map<String, Object> map = mapper.fromJson(mapString, HashMap.class);
		System.out.println("Map:");
		for (Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

		//List<String>
		String listString = "[\"A\",\"B\",\"C\"]";
		List<String> stringList = mapper.fromJson(listString, List.class);
		System.out.println("String List:");
		for (String element : stringList) {
			System.out.println(element);
		}

		//List<Bean>*********
		String beanListString = "[{\"name\":\"A\"},{\"name\":\"B\"}]";
		JavaType beanListType = mapper.createCollectionType(List.class, TestBean.class);
		List<TestBean> beanList = mapper.fromJson(beanListString, beanListType);
		System.out.println("Bean List:");
		for (TestBean element : beanList) {
			System.out.println(element);
		}
	}

	/**
	 * �������ֲ�ͬ��Inclusion���.
	 */
	@Test
	public void threeTypeInclusion() {
		TestBean bean = new TestBean("A");

		//��ӡȫ������
		JsonMapper normalMapper = new JsonMapper();
		assertEquals("{\"name\":\"A\",\"defaultValue\":\"hello\",\"nullValue\":null}", normalMapper.toJson(bean));

		//����ӡnullValue����
		JsonMapper nonNullMapper = JsonMapper.nonEmptyMapper();
		assertEquals("{\"name\":\"A\",\"defaultValue\":\"hello\"}", nonNullMapper.toJson(bean));

		//����ӡĬ��ֵδ�ı��nullValue��defaultValue����
		JsonMapper nonDefaultMaper = JsonMapper.nonDefaultMapper();
		assertEquals("{\"name\":\"A\"}", nonDefaultMaper.toJson(bean));
	}

	/*
	 * ��������Jaxb�ĳ���annotaion����properName��ignore��propertyOrder
	 */
	@Test
	public void jaxbStyleAnnoation() {
		TestBean2 testBean = new TestBean2(1, "foo", 18);
		//���name���������ǰ���ұ�����ΪproductName����age���Ա�ignore
		assertEquals("{\"productName\":\"foo\",\"id\":1}", mapper.toJson(testBean));
	}

	//��ת˳��
	@JsonPropertyOrder({ "name", "id" })
	public static class TestBean2 {

		public long id;

		@JsonProperty("productName")
		public String name;

		@JsonIgnore
		public int age;

		public TestBean2() {

		}

		public TestBean2(long id, String name, int age) {
			this.id = id;
			this.name = name;
			this.age = age;
		}

	}

	/**
	 * ����һ���Ѵ���Bean��JSON�ַ����eֻ����Bean�Ĳ��֌��ԣ�ֻ���w�ⲿ�ֵČ���.
	 */
	@Test
	public void updateBean() {
		String jsonString = "{\"name\":\"A\"}";

		TestBean bean = new TestBean();
		bean.setDefaultValue("Foobar");

		bean = mapper.update(jsonString, bean);

		//name����ֵ
		assertEquals("A", bean.getName());
		//DefaultValue����Json���У���Ȼ������
		assertEquals("Foobar", bean.getDefaultValue());
	}

	/**
	 * �yԇݔ��jsonp��ʽ����.
	 */
	@Test
	public void jsonp() {
		TestBean bean = new TestBean("foo");
		String jsonpString = mapper.toJsonP("callback", bean);
		assertEquals("callback({\"name\":\"foo\"})", jsonpString);
	}

	/**
	 * ��ʾ�õ�Bean, ��Ҫ��ʾ��ͬ�L���Mapper��Nullֵ����ʼ����]��׃�^�Č���ֵ��̎��.
	 */
	public static class TestBean {

		private String name;
		private String defaultValue = "hello"; //Ĭ��ֵû���޸Ĺ������ԣ����ܻ᲻���л�
		private String nullValue = null; //��ֵ�ľ��У����ܻ᲻���л�

		public TestBean() {
		}

		public TestBean(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDefaultValue() {
			return defaultValue;
		}

		public void setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
		}

		public String getNullValue() {
			return nullValue;
		}

		public void setNullValue(String nullValue) {
			this.nullValue = nullValue;
		}

		@Override
		public String toString() {
			return "TestBean [defaultValue=" + defaultValue + ", name=" + name + ", nullValue=" + nullValue + "]";
		}
	}

	////��������������ʾ////

	/**
	 * ���Զ�ö�ٵ����л�.
	 */
	@Test
	public void enumType() {
		//toJSonĬ�Jʹ��enum.name()
		assertEquals("\"One\"", mapper.toJson(TestEnum.One));
		//fromJsonʹ��enum.name()��enum.order()
		assertEquals(TestEnum.One, mapper.fromJson("\"One\"", TestEnum.class));
		assertEquals(TestEnum.One, mapper.fromJson("0", TestEnum.class));

		//ʹ��enum.toString(), ע�����ñ���������x������֮ǰ�{��.
		//����toString()ʹ��index�������ԣ���enum.name()��Լ�˿ռ䣬��enum.order()�򲻻���˳����ʱ�ı䲻ȷ�������⡣
		JsonMapper newMapper = new JsonMapper();
		newMapper.enableEnumUseToString();
		assertEquals("\"1\"", newMapper.toJson(TestEnum.One));
		assertEquals(TestEnum.One, newMapper.fromJson("\"1\"", TestEnum.class));
	}

	/**
	 * ö�e��͵���ʾBean.
	 */
	public static enum TestEnum {
		One(1), Two(2), Three(3);

		private final int index;

		TestEnum(int index) {
			this.index = index;
		}

		@Override
		public String toString() {
			return String.valueOf(index);
		}
	}

	/**
	 * ���Զ����ڵ����л�,����Ĭ����Timestamp��ʽ�洢��Ҳ������2.0��Ҳ������@JsonFormat�������ϸ�ʽ��.
	 */
	@Test
	public void dateType() {

		Date date = new Date();
		String timestampString = String.valueOf(date.getTime());
		String format = "yyyy-MM-dd HH:mm:ss";
		String formatedString = new DateTime(date).toString(format);

		DateBean dateBean = new DateBean();
		dateBean.startDate = date;
		dateBean.endDate = date;

		//to json
		String expectedJson = "{\"startDate\":" + timestampString + ",\"endDate\":\"" + formatedString + "\"}";
		assertEquals(expectedJson, mapper.toJson(dateBean));

		//from json
		Date expectedEndDate = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(formatedString).toDate();

		DateBean resultBean = mapper.fromJson(expectedJson, DateBean.class);
		assertEquals(date, resultBean.startDate);
		assertEquals(expectedEndDate, resultBean.endDate);

	}

	public static class DateBean {
		//Ĭ��timestamp�洢
		public Date startDate;
		//��annotation�е����ڸ�ʽ�洢��
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
		public Date endDate;

	}

	/**
	 * ���Դ���ն���,���ַ���,Empty�ļ���,"null"�ַ����Ľ��.
	 */
	@Test
	public void nullAndEmpty() {
		// toJson���� //

		//Null Bean
		TestBean nullBean = null;
		String nullBeanString = mapper.toJson(nullBean);
		assertEquals("null", nullBeanString);

		//Empty List
		List<String> emptyList = Lists.newArrayList();
		String emptyListString = mapper.toJson(emptyList);
		assertEquals("[]", emptyListString);

		// fromJson���� //

		//Null String for Bean
		TestBean nullBeanResult = mapper.fromJson(null, TestBean.class);
		assertNull(nullBeanResult);

		nullBeanResult = mapper.fromJson("null", TestBean.class);
		assertNull(nullBeanResult);

		//Null/Empty String for List
		List nullListResult = mapper.fromJson(null, List.class);
		assertNull(nullListResult);

		nullListResult = mapper.fromJson("null", List.class);
		assertNull(nullListResult);

		nullListResult = mapper.fromJson("[]", List.class);
		assertEquals(0, nullListResult.size());
	}

	//// �߼�Ӧ�� ////
	/**
	 * �yԇ����POJO�g��ѭ�h����.
	 */
	@Test
	public void cycleReferenceBean() {
		//��ʼ�������ϵ��parent��children�ﺬ�� child1,child2, child1/child2��parent��ָ��parent.
		CycleReferenceBean parent = new CycleReferenceBean("parent");

		CycleReferenceBean child1 = new CycleReferenceBean("child1");
		child1.setParent(parent);
		parent.getChildren().add(child1);

		CycleReferenceBean child2 = new CycleReferenceBean("child2");
		child2.setParent(parent);
		parent.getChildren().add(child2);

		//���л���, json�ַ����echildren�е�child1/child2����������parent�Č���
		String jsonString = "{\"name\":\"parent\",\"children\":[{\"name\":\"child1\"},{\"name\":\"child2\"}]}";
		assertEquals(jsonString, mapper.toJson(parent));

		//ע��˕r����Ϊ����л�child1��Ҳ������ӡparent����Ϣ���Gʧ��
		assertEquals("{\"name\":\"child1\"}", mapper.toJson(child1));

		//�������л�ʱ��Json�Ѻܴ����İ�parent����child1/child2��.
		CycleReferenceBean parentResult = mapper.fromJson(jsonString, CycleReferenceBean.class);
		assertEquals("parent", parentResult.getChildren().get(0).getParent().getName());

		//���������л�child1����ȻparentҲ�ǿ�
		CycleReferenceBean child1Result = mapper.fromJson("{\"name\":\"child1\"}", CycleReferenceBean.class);
		assertNull(child1Result.parent);
		assertEquals("child1", child1Result.getName());
	}

	/**
	 * ����POJO�g��ѭ�h���õ���ʾBean,@JsonBackReference �� @JsonManagedReference �ǹؼ�.
	 */
	public static class CycleReferenceBean {

		private String name;
		private CycleReferenceBean parent;
		private List<CycleReferenceBean> children = Lists.newArrayList();

		public CycleReferenceBean() {
		}

		public CycleReferenceBean(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		//ע��getter�csetter��Ҫ���annotation
		@JsonBackReference
		public CycleReferenceBean getParent() {
			return parent;
		}

		@JsonBackReference
		public void setParent(CycleReferenceBean parent) {
			this.parent = parent;
		}

		@JsonManagedReference
		public List<CycleReferenceBean> getChildren() {
			return children;
		}

		@JsonManagedReference
		public void setChildren(List<CycleReferenceBean> children) {
			this.children = children;
		}
	}

	/**
	 * �yԇ�ɔUչBean.
	 * ����չBean����ƻ���һЩ�Ĺ̶����Ժ���һ��Map<String,object>��ŵ���չ���ԡ�
	 * ͨ��������Щ�ǹ̶����ԣ���Щ����չ���ԣ���Ӧ�ò����ݽ����ǲ��ϱ仯�ġ�
	 * Jackson֧�ֽ��������Զ����л���ƽ�е������б�û�й̶�������Map�����Ե�����Ȼ�����ܵĽ����ڹ̶��е����Զ�������@JsonAnyGetter/Setterע�͵�Map����ȥ��
	 */
	@Test
	public void extensibleBean() {
		//һ��û�������Ǳ�������Map����ͨJSON�ַ���.
		String jsonString = "{\"name\" : \"Foobar\",\"age\" : 37,\"occupation\" : \"coder man\"}";
		ExtensibleBean extensibleBean = mapper.fromJson(jsonString, ExtensibleBean.class);
		//�̶�����
		assertEquals("Foobar", extensibleBean.getName());
		assertEquals(null, extensibleBean.getProperties().get("name"));

		//����չ����
		assertEquals("coder man", extensibleBean.getProperties().get("occupation"));
	}

	/**
	 * ��ʾ�õĿɔUչBean.@JsonAnySetter��@JsonAnyGetter�ǹؼ�.
	 */
	public static class ExtensibleBean {
		// �̶�����
		private String name;
		// ��չ����
		private final Map<String, String> properties = Maps.newHashMap();

		public ExtensibleBean() {
		}

		@JsonAnySetter
		public void add(String key, String value) {
			properties.put(key, value);
		}

		@JsonAnyGetter
		public Map<String, String> getProperties() {
			return properties;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	/**
	 * ͬһ��POJO���ڲ�ͬ�����¿�����Ҫ���л���ͬ�������飬Jackson֧��ʹ��View������.
	 */
	@Test
	public void multiViewBean() throws IOException {
		MultiViewBean multiViewBean = new MultiViewBean();
		multiViewBean.setName("Foo");
		multiViewBean.setAge(16);
		multiViewBean.setOtherValue("others");

		//public view
		ObjectWriter publicWriter = mapper.getMapper().writerWithView(Views.Public.class);
		assertEquals("{\"name\":\"Foo\",\"otherValue\":\"others\"}", publicWriter.writeValueAsString(multiViewBean));

		//internal view
		ObjectWriter internalWriter = mapper.getMapper().writerWithView(Views.Internal.class);
		assertEquals("{\"age\":16,\"otherValue\":\"others\"}", internalWriter.writeValueAsString(multiViewBean));

	}

	public static class Views {
		static class Public {
		}

		static class Internal {
		}
	}

	/**
	 * ��ʾ���л���ͬView��ͬ���Ե�Bean.
	 */
	public static class MultiViewBean {
		private String name;
		private int age;
		private String otherValue;

		@JsonView(Views.Public.class)
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@JsonView(Views.Internal.class)
		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getOtherValue() {
			return otherValue;
		}

		public void setOtherValue(String otherValue) {
			this.otherValue = otherValue;
		}
	}

	////�Զ�����Ϊ////

	/**
	 * �����Զ���ת����������о����Ը��ӡ������ǽ�Money��Long��ת.
	 */
	@Test
	public void customConverter() {

		JsonMapper newMapper = JsonMapper.nonEmptyMapper();

		SimpleModule moneyModule = new SimpleModule("MoneyModule");
		moneyModule.addSerializer(new MoneySerializer());
		moneyModule.addDeserializer(Money.class, new MoneyDeserializer());
		newMapper.getMapper().registerModule(moneyModule);

		//tojson
		User user = new User();
		user.setName("foo");
		user.setSalary(new Money(1.2));

		String jsonString = newMapper.toJson(user);

		assertEquals("{\"name\":\"foo\",\"salary\":\"1.2\"}", jsonString);

		//from
		User resultUser = newMapper.fromJson(jsonString, User.class);
		assertEquals(new Double(1.2), resultUser.getSalary().value);

	}

	public class MoneySerializer extends StdSerializer<Money> {
		public MoneySerializer() {
			super(Money.class);
		}

		@Override
		public void serialize(Money value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
				JsonProcessingException {

			jgen.writeString(value.toString());
		}
	}

	public class MoneyDeserializer extends StdDeserializer<Money> {
		public MoneyDeserializer() {
			super(Money.class);
		}

		@Override
		public Money deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
				JsonProcessingException {
			return Money.valueOf(jp.getText());
		}

	}

	public static class Money {
		private final Double value;

		public Money(Double value) {
			this.value = value;
		}

		public static Money valueOf(String value) {
			Double srcValue = Double.valueOf(value);
			return new Money(srcValue);
		}

		@Override
		public String toString() {
			return value.toString();
		}
	}

	/**
	 * ����Money���ԵĶ���.
	 */
	public static class User {
		private String name;
		private Money salary;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Money getSalary() {
			return salary;
		}

		public void setSalary(Money salary) {
			this.salary = salary;
		}

	}

	/**
	 * �����޸� ���������ԡ�
	 */
	@Test
	public void customPropertyNaming() throws JsonMappingException {

		TestBean bean = new TestBean("foo");
		bean.setDefaultValue("bar");
		JsonMapper newMapper = JsonMapper.nonEmptyMapper();
		newMapper.getMapper().setPropertyNamingStrategy(new LowerCaseNaming());
		String jsonpString = newMapper.toJson(bean);
		assertEquals("{\"name\":\"foo\",\"defaultvalue\":\"bar\"}", jsonpString);
	}

	public static class LowerCaseNaming extends PropertyNamingStrategy {
		@Override
		public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
			return defaultName.toLowerCase();
		}
	}
}