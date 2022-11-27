package com.jesusnut.listeners;

//https://github.com/anshooarora/extentreports-java/issues/717
//https://stackoverflow.com/questions/44492023/how-to-generate-extent-report-if-test-case-failed-in-between

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.jesusnut.annotations.FrameworkAnnotation;
import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.reports.extentreports.ExtentLogger;
import com.jesusnut.reports.extentreports.ExtentReport;

/**
 * ExtentReportListener class is a listener class implementing
 * {@link org.testng.ITestListener}<br>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see com.jesusnut.reports.extentreports.ExtentReport
 * @see com.jesusnut.reports.extentreports.ExtentLogger
 * @see com.jesusnut.constants.FrameworkConstants
 * 
 */
public class ExtentReportListener implements ITestListener {

	/**
	 * Returns description of a test method.<br>
	 * If description is not provided explicitly in a test method
	 * like @Test(description=xxx), then test method name is considered as its test
	 * description.
	 * 
	 * @param result ITestResult
	 * @return description of a test method in String
	 */

	private static String getTestDescription(ITestResult result) {

		return result.getMethod().getDescription().isBlank() ? result.getMethod().getMethodName()
				: result.getMethod().getDescription();

	}

	/**
	 * Creates {@link com.aventstack.extentreports.ExtentTest} test node for each
	 * testNG test method, assign author, assign category and set start time of that
	 * test node on its start.<br>
	 * 
	 */

	@Override
	public void onTestStart(ITestResult result) {

		ExtentReport.createTest(result.getMethod().getMethodName(), getTestDescription(result));
		ExtentReport.assignAuthor(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).author());
		ExtentReport.assignCategory(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).category());
		ExtentReport.setStartTime(result.getStartMillis());

		ExtentLogger.info("This test case [" + result.getMethod().getMethodName() + "] is present in Test Class : ["
				+ result.getTestClass().getName() + "]");

	}

	/**
	 * Marks the test node/test case as pass and logs it in the Extent Report.<br>
	 * Appends base64 screenshots if
	 * {@link com.jesusnut.config.ConfigFactory#hasPassedStepsScreenshots()} is set
	 * to true. <br>
	 * Sets end time for the test node that has passed successfully.
	 * 
	 */

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentLogger.pass(result.getName() + " is " + FrameworkConstants.BOLD_START + "PASSED "
				+ FrameworkConstants.ICON_SMILEY_PASS + FrameworkConstants.BOLD_END);
		ExtentReport.setEndTime(result.getEndMillis());
	}

	/**
	 * Marks the test node/test case as fail, appends base64 screenshot and logs it
	 * in the Extent Report.<br>
	 * Sets end time for the test node that has failed.<br>
	 * Logs the exception (cause of failure) in the Extent Report.
	 * 
	 */

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentLogger.fail(result.getName() + " is " + FrameworkConstants.BOLD_START + "FAILED     "
				+ FrameworkConstants.ICON_SMILEY_FAIL + FrameworkConstants.BOLD_END);
		ExtentLogger.info(result.getThrowable().getLocalizedMessage());
		ExtentLogger.info(result.getThrowable());
		ExtentReport.setEndTime(result.getEndMillis());
	}

	/**
	 * Marks the test node/test case as skipped, logs it and also logs the cause of
	 * skip in the Extent Report.<br>
	 * Sets end time for the test node that has skipped.<br>
	 * Logs the exception (cause of failure) in the Extent Report.
	 * 
	 */

	@Override
	public void onTestSkipped(ITestResult result) {

		ExtentLogger.skip(result.getName() + " is " + FrameworkConstants.BOLD_START + "SKIPPED "
				+ FrameworkConstants.ICON_SMILEY_SKIP + FrameworkConstants.BOLD_END + " because of"
				+ result.getSkipCausedBy().toString());
		ExtentReport.setEndTime(result.getEndMillis());

	}

	/**
	 * Performs the basic initialization required for Extent Reports.
	 * 
	 */

	@Override
	public void onStart(ITestContext context) {

		ExtentReport.initReports();

	}

	/**
	 * Performs the basic termination steps required for Extent Reports.
	 * 
	 */

	@Override
	public void onFinish(ITestContext context) {

		ExtentReport.flushReports();

	}

}
