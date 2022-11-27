package com.jesusnut.enums;

/**
 * Enums to restrict the browser values supported by the framework.<br>
 * Any new browser should be included in this class.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see com.jesusnut.driver.BrowserOptionsFactory
 * @see com.jesusnut.driver.LocalDriverFactory
 * @see com.jesusnut.helper.IconHelper#getBrowserIcon()
 */

public enum BrowserType {

	CHROME("chrome"), FIREFOX("firefox"), EDGE("edge");

	private String browserName;

	BrowserType(String browserName) {
		this.browserName = browserName;
	}

	public String getBrowserName() {
		return this.browserName.toLowerCase();
	}

}
