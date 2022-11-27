package com.jesusnut.reports.extentreports;

import com.aventstack.extentreports.ExtentTest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * ExtentManager class helps to achieve thread safety for the ExtentTest
 * instance.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see com.jesusnut.reports.extentreports.ExtentReport
 * @see com.jesusnut.reports.extentreports.ExtentLogger
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentManager {

	private static final ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();

	/**
	 * Returns the thread safe Extent Test instance fetched from ThreadLocal
	 * variable.
	 * 
	 * @return {@link com.aventstack.extentreports.ExtentTest} instance.
	 */

	static ExtentTest getExtentTest() {
		return threadLocal.get();
	}

	/**
	 * Sets the Extent Test instance to ThreadLocal variable.
	 * 
	 * @param test - {@link com.aventstack.extentreports.ExtentTest} instance that
	 *             needs to saved from Thread safety issues.
	 */
	static void setExtentTest(ExtentTest test) {
		threadLocal.set(test);
	}

	/**
	 * Sets the ThreadLocal variable having Extent Test instance with default value.
	 */

	static void unloadTest() {
		threadLocal.remove();
	}

}
