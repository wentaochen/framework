package com.framework.infrastructure.test.groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

/**
 * ʵ��TestNG Groups����ִ���������ܵ�TestRunner.
 * 
 * ��������ڰ������ִ���ٶȽϿ�Ĳ��Է���, ����������ִ�н����Ĳ��Է���.
 * 
 * Runner��ִֻ�в������@Groups����, ����-Dtest.groups=xxx,xxx���ǺϵĲ����༰���Է���. ����������ִ��ȫ��.
 * ���ṩ�����жϵĹ��߷�����������Runner����.
 * 
 * ע��, ����ֻ������JUnit 4.4���ϰ汾.
 * 
 * @author freeman
 * @author calvin
 */
public class GroupsTestRunner extends BlockJUnit4ClassRunner {

	/** ��JVM����-D�ж���ִ�з���ı�������. */
	public static final String PROPERTY_NAME = "test.groups";

	private static List<String> groups;

	public GroupsTestRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	// --����Runner����--//

	/**
	 * ���ؼ���Class�������.
	 */
	@Override
	public void run(RunNotifier notifier) {
		if (!shouldRun(getTestClass().getJavaClass())) {
			EachTestNotifier testNotifier = new EachTestNotifier(notifier,
					getDescription());
			testNotifier.fireTestIgnored();
			return;
		}

		super.run(notifier);
	}

	/**
	 * ���ؼ��뷽���������.
	 */
	@Override
	protected void runChild(FrameworkMethod method, RunNotifier notifier) {
		if (!shouldRun(method.getMethod())) {
			Description description = describeChild(method);
			EachTestNotifier eachNotifier = new EachTestNotifier(notifier,
					description);
			eachNotifier.fireTestIgnored();
			return;
		}

		super.runChild(method, notifier);
	}

	// -- �жϲ�������/���Է����Ƿ���ϵĹ��߷��� --//

	/**
	 * �жϲ������Ƿ���Ϸ���Ҫ��. ������ٺ���һ������Groups����Ĳ��Է���ʱ����true.
	 */
	public static boolean shouldRun(Class<?> testClass) {
		Method[] methods = testClass.getMethods();
		for (Method method : methods) {
			if (method.getAnnotation(Test.class) != null
					&& method.getAnnotation(Ignore.class) == null
					&& shouldRun(method)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * �жϲ��Է����Ƿ����GroupsҪ��. ���@Groups���϶����Groups����ΪALL�򷽷�����@Groups���巵��true.
	 */
	public static boolean shouldRun(Method testMethod) {
		// ��ʼ��Groups����
		if (groups == null) {
			initGroups();
		}
		// ���groups����Ϊȫ��ִ���򷵻�true
		if (groups.contains(Groups.ALL)) {
			return true;
		}

		// ȡ�÷����ϵ�Groups annotation, �����Groupsע���ע����Ϸ���Ҫ���򷵻�true.
		Groups groupsAnnotation = testMethod.getAnnotation(Groups.class);
		if ((groupsAnnotation == null)
				|| groups.contains(groupsAnnotation.value())) {
			return true;
		}

		return false;
	}

	/**
	 * �ӻ���������ȡtest.groups����, ���group�ö��ŷָ�. eg. java -Dtest.groups=Mini,Major
	 * ����޶����򷵻�ALL.
	 */
	protected static void initGroups() {

		String groupsProperty = System.getProperty(PROPERTY_NAME);

		// �����������δ����test.groups,���Դ�property�ļ���ȡ.
		if (StringUtils.isBlank(groupsProperty)) {
			groupsProperty = Groups.ALL;
		}

		groups = Arrays.asList(groupsProperty.split(","));
	}
}
