package com.jesusnut.reports.extentreports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.jesusnut.config.ConfigFactory;
import com.jesusnut.utils.ScreenshotUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * ExtentLogger is responsible to log different event's status to Extent
 * Report.<br>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see com.jesusnut.utils.ScreenshotUtils
 * @see com.jesusnut.reports.extentreports.ExtentManager
 * @see com.jesusnut.listeners.ExtentReportListener
 * @see com.jesusnut.config.ConfigFactory
 * @see com.aventstack.extentreports.MediaEntityBuilder#createScreenCaptureFromBase64String(String)
 * @see com.aventstack.extentreports.markuputils.MarkupHelper
 *
 * 
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentLogger {

	/**
	 * Helps log a passed event to extent report.<br>
	 * Appends base64 screenshots if
	 * {@link com.jesusnut.config.ConfigFactory#hasPassedStepsScreenshots()} is set
	 * to true. <br>
	 * 
	 * @param message - message that user wants to log along with a passed event in
	 *                String format
	 * @see com.jesusnut.utils.Ignitor
	 * @see com.jesusnut.utils.JSUtils
	 */

	public static void pass(String message) {

		if (ConfigFactory.hasPassedStepsScreenshots()) {
			ExtentManager.getExtentTest().pass(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot()).build());

		}

		else {
			ExtentManager.getExtentTest().pass(message);

		}

	}

	/**
	 * Helps log a failed event along with Base64 screenshots to extent report.<br>
	 * 
	 * @param message - message that user wants to log along with a failed event in
	 *                String format
	 */

	public static void fail(String message) {
		ExtentManager.getExtentTest().fail(message, MediaEntityBuilder
				.createScreenCaptureFromBase64String(ScreenshotUtils.getFullScreenScreenShotAShotAPI()).build());

	}

	/**
	 * Helps log an information event in INDIGO color to extent report.<br>
	 * 
	 * @param message - message that user wants to log along with an information
	 *                event in String format
	 */

	public static void info(String message) {

		ExtentManager.getExtentTest().info(MarkupHelper.createLabel(message, ExtentColor.INDIGO));

	}

	/**
	 * Helps log an information event to extent report.<br>
	 * 
	 * @param throwable throwable cause for failure
	 */

	public static void info(Throwable throwable) {
		ExtentManager.getExtentTest().info(throwable);
	}

	/**
	 * Helps log a skipped event to extent report.<br>
	 * 
	 * @param message - message that user wants to log along with a skipped event in
	 *                String format
	 */

	public static void skip(String message) {
		ExtentManager.getExtentTest().skip(message);

	}

	/**
	 * Helps log any custom event to extent report.<br>
	 * 
	 * @param status a {@link com.aventstack.extentreports.Status} type status
	 * @param t      throwable cause for event
	 */

	public static void logCustomStatus(Status status, Throwable t) {
		ExtentManager.getExtentTest().log(status, t);

	}

}
