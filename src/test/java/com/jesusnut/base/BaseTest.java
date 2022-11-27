package com.jesusnut.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.jesusnut.driver.Driver;

/**
 * Acts as parent class for all the test classes in JesusNut Framework.<br>
 * 
 * <pre>
 * <b>All the test classes needs to extend this class.</b>
 * </pre>
 * 
 * For best practice, Each test method/test case (with @Test annotation) in any
 * test classes are supposed to be atomic (end to end). <br>
 * This class invokes and terminates browser session on test method/test case
 * (with @Test annotation) level- as implemented using BeforeMethod and
 * AfterMethod.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see com.jesusnut.driver
 */

public class BaseTest {

	/**
	 * <p>
	 * Invokes a new browser instance on test case/test method level and loads the
	 * respective URL.
	 * </p>
	 * 
	 */

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		Driver.initDriver();

	}

	/**
	 * <p>
	 * Terminates browser instance on test case/test method level.
	 * </p>
	 * 
	 */

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		Driver.quitDriver();
	}

}
