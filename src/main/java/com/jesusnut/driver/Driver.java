/*
 * Copyright (c) 2022 Abhishek Bhardwaj-JesusNut

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package com.jesusnut.driver;

import java.util.Objects;

import org.openqa.selenium.WebDriver;

import com.jesusnut.config.ConfigFactory;
import com.jesusnut.utils.BrowserStorageUtils;
import com.jesusnut.utils.Ignitor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Driver class is responsible for invoking and closing the browsers.<br>
 * Driver class is responsible for setting the driver variable to
 * {@link TLDriverManager#setDriver(WebDriver)} which handles the thread safety
 * for the WebDriver instance.<br>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see DriverFactory
 * @see TLDriverManager
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Driver {

	/**
	 * <p>
	 * Fetches the browser value from
	 * {@link com.jesusnut.config.ConfigFactory#getBrowserFromConfig()} and creates
	 * a WebDriver instance using {@link DriverFactory#getDriver(String)}.<br>
	 * Sets the driver variable to {@link TLDriverManager#setDriver(WebDriver)} for
	 * thread safety.<br>
	 * Performs other stuffs - launching browser session with AUT URL,deleting
	 * cookies, maximizing window.<br>
	 * </p>
	 * 
	 */

	public static void initDriver() {

		if (Objects.isNull(TLDriverManager.getDriver())) {
			String browserToBeUsed = ConfigFactory.getBrowserFromConfig();
			WebDriver driver = DriverFactory.getDriver(browserToBeUsed);
			TLDriverManager.setDriver(driver);
			BrowserStorageUtils.deleteAllCookies();
			Ignitor.maximizeWindow();
			Ignitor.launchURL(ConfigFactory.getURL());
		}

	}

	/**
	 * <p>
	 * Quits the browser session after completing test case/test method.<br>
	 * Sets the threadlocal (with WebDriver instance) to default value in
	 * {@link TLDriverManager#resetDriver()} on completion of test case /test
	 * method.
	 * </p>
	 * 
	 */

	public static void quitDriver() {
		if (Objects.nonNull(TLDriverManager.getDriver())) {
			Ignitor.quitDriver();
			TLDriverManager.resetDriver();
		}

	}

}
