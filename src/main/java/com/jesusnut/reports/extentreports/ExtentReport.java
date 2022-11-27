package com.jesusnut.reports.extentreports;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.enums.Author;
import com.jesusnut.enums.Category;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Perform initialisation and termination of
 * {@link com.aventstack.extentreports.ExtentReports}.<br>
 * After creating an instance for
 * {@link com.aventstack.extentreports.ExtentTest}, it is delegated to
 * ThreadLocal variable for providing thread safety.<br>
 * Contains utlity methods for {@link com.aventstack.extentreports.ExtentTest}
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see com.aventstack.extentreports.reporter.ExtentSparkReporter
 * @see com.jesusnut.reports.extentreports.ExtentLogger
 * @see com.jesusnut.reports.extentreports.ExtentManager
 * @see com.jesusnut.listeners.ExtentReportListener
 * @see com.jesusnut.reports.extentreports.ExtentReportHelper
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentReport {

	private static ExtentReports extent;

	/**
	 * Sets the initial configuration for Extent Reporting.<br>
	 * Responsible for customizing the extent report.
	 */

	public static void initReports() {

		if (Objects.isNull(extent)) {

			extent = new ExtentReports();
			ExtentReportHelper.setSystemInfo(extent);
			ExtentSparkReporter spark = ExtentReportHelper.getExtentSparkReporterInstance();
			ExtentReportHelper.setViewingOrder(spark, FrameworkConstants.getFrameworkExtentReportViewingOrder());
			ExtentReportHelper.setDocumentTitleForExtentReport(spark,
					FrameworkConstants.getFrameworkExtentReportDocumentTitle());
			ExtentReportHelper.setReportNameForExtentReport(spark, FrameworkConstants.getFrameworkExtentReportName());
			ExtentReportHelper.getCustomizedExtentReport(spark);
			ExtentReportHelper.attachReporter(extent, spark);

		}

	}

	/**
	 * Flushing the reports ensures extent logs are reflected properly.<br>
	 * Opens the report in the default desktop browser.<br>
	 * Sets the threadlocal (with Extent Test instance) to default value in
	 * {@link ExtentManager#unloadTest()} on completion of test node.
	 */

	public static void flushReports() {

		if (Objects.nonNull(extent)) {
			extent.flush();
		}

		ExtentManager.unloadTest();

	}

	/**
	 * 
	 * Creates a test node in the extent report.<br>
	 * Delegates to {@link ExtentManager#setExtentTest(ExtentTest)} for providing
	 * thread safety.
	 * 
	 * @param testCaseName - name of test case in String format
	 * @param description  - description of test case in String format
	 */

	public static void createTest(String testCaseName, String description) {

		ExtentTest test = extent.createTest(testCaseName, description);

		ExtentManager.setExtentTest(test);

	}

	/**
	 * Logs the author details in the authors view in the extent report.
	 * 
	 * <pre>
	 *  NOTE : Only a single author can be assigned to a test case/test method in JesusNut Framework.
	 * </pre>
	 * 
	 * @param author - Author from {@link com.jesusnut.enums.Author} who authored
	 *               the test case
	 */

	public static void assignAuthor(Author author) {

		ExtentManager.getExtentTest().assignAuthor(author.toString());

	}

	/**
	 * Logs the category/categories to which a test case/test method belongs to.<br>
	 * 
	 * @param category - Categories from {@link com.jesusnut.enums.Category} to
	 *                 which a test case /test method belong.
	 */

	public static void assignCategory(Category[] category) {

		for (Category temp_category : category) {

			ExtentManager.getExtentTest().assignCategory(temp_category.toString());
		}
	}

	/**
	 * Logs the start time of execution of a test case/test method.<br>
	 * 
	 * @param time - time in long format
	 */

	public static void setStartTime(long time) {

		ExtentManager.getExtentTest().getModel().setStartTime(getTime(time));

	}

	/**
	 * Logs the end time of execution of a test case/test method.<br>
	 * 
	 * @param time - time in long format
	 */
	public static void setEndTime(long time) {

		ExtentManager.getExtentTest().getModel().setEndTime(getTime(time));
	}

	/**
	 * Returns a Date object representing this Calendar's time value.<br>
	 * 
	 * @param millis - time in long format
	 * @return java.util.Date
	 */

	private static Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}
