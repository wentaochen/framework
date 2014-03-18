package com.framework.infrastructure.test.utils;

import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Selenium������.
 * 
 * @author calvin
 */
public class SeleniumUtils {

	public static final String HTMLUNIT = "htmlunit";

	public static final String FIREFOX = "firefox";

	public static final String IE = "ie";

	public static final String REMOTE = "remote";

	private static Logger logger = LoggerFactory.getLogger(SeleniumUtils.class);

	/**
	 * ����driverName��������WebDriver�ļ�㷽��.
	 * 
	 * ���������ɷ�������װ�ڷ�Windows������, û��IE�������XWindowsʱ, ��Ҫʹ��remote
	 * dirver����Զ�̵�Windows����. drivername��remote:192.168.0.2:3000:firefox,
	 * ��ʱҪ��Զ�̷�������http://192.168.0.2:3000/wd������selnium remote����.
	 */
	public static WebDriver buildDriver(String driverName) throws Exception {
		WebDriver driver = null;

		if (HTMLUNIT.equals(driverName)) {
			driver = new HtmlUnitDriver();
			((HtmlUnitDriver) driver).setJavascriptEnabled(true);
		}

		if (FIREFOX.equals(driverName)) {
			driver = new FirefoxDriver();
		}

		if (IE.equals(driverName)) {
			driver = new InternetExplorerDriver();
		}

		if (driverName.startsWith(REMOTE)) {
			String[] params = driverName.split(":");
			Assert
					.isTrue(
							params.length == 4,
							"Remote driver is not right, accept format is \"remote:localhost:3000:firefox\", but the input is\""
									+ driverName + "\"");

			String remoteHost = params[1];
			String remotePort = params[2];
			String driverType = params[3];
			DesiredCapabilities cap = null;
			if (FIREFOX.equals(driverType)) {
				cap = DesiredCapabilities.firefox();
			}

			if (IE.equals(driverType)) {
				cap = DesiredCapabilities.internetExplorer();
			}

			driver = new RemoteWebDriver(new URL("http://" + remoteHost + ":"
					+ remotePort + "/wd"), cap);
		}

		Assert
				.notNull(driver, "No driver could be found by name:"
						+ driverName);

		return driver;
	}

	/**
	 * ����Selnium1.0�ĳ��ú���.
	 */
	public static boolean isTextPresent(WebDriver driver, String text) {
		return StringUtils.contains(driver.findElement(By.tagName("body"))
				.getText(), text);
	}

	/**
	 * ����Selnium1.0�ĳ��ú���.
	 */
	public static void type(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * ����Selnium1.0�ĳ��ú���.
	 */
	public static void uncheck(WebElement element) {
		if (element.isSelected()) {
			element.toggle();
		}
	}

	/**
	 * ����Selnium1.0�ĳ��ú���, ���д�0��ʼ.
	 */
	public static String getTable(WebElement table, int rowIndex,
			int columnIndex) {
		return table.findElement(
				By.xpath("//tr[" + (rowIndex + 1) + "]//td["
						+ (columnIndex + 1) + "]")).getText();
	}

	/**
	 * ����Selnium1.0�ĳ��ú���, timeout��λΪ����.
	 */
	public static void waitForDisplay(WebElement element, int timeout) {
		long timeoutTime = System.currentTimeMillis() + timeout;
		while (System.currentTimeMillis() < timeoutTime) {
			if (((RenderedWebElement) element).isDisplayed()) {
				return;
			}
		}
		logger.warn("waitForDisplay timeout");
	}
}
