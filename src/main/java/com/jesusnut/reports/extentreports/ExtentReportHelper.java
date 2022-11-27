package com.jesusnut.reports.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.jesusnut.config.ConfigFactory;
import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.helper.IconHelper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Helper class for {@link com.jesusnut.reports.extentreports.ExtentReport} -
 * helps in initial configuration and customization of Extent Reports <br>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see com.jesusnut.constants.FrameworkConstants
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentReportHelper {

	/**
	 * Returns customized JS script for beautification of Extent Report.
	 * 
	 * @return customized JS script which is injected for beautification in
	 *         {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}.
	 */

	private static String customizedJSForSparkReport() {

		return "var els = document.getElementsByClassName(\"m-t-10 m-l-5\");\r\n" + "\r\n"
				+ "        for (var i=0; i<els.length; i++) {\r\n" + "            els[i].style.fontStyle=\"italic\"\r\n"
				+ "            els[i].style.fontSize=\"14px\"\r\n" + "\r\n" + "}\r\n" + "\r\n"
				+ "var end_time = document.querySelectorAll('span.badge-danger:not(.log)');\r\n" + "\r\n" + "\r\n"
				+ "for (var i=0; i<end_time.length; i++) {\r\n"
				+ "            end_time[i].style.backgroundColor=\"#6c757d\"\r\n" + "             \r\n" + " }\r\n"
				+ " \r\n" + " var end_time = document.querySelectorAll('span.badge-success:not(.log)');\r\n" + "\r\n"
				+ "\r\n" + "for (var i=0; i<end_time.length; i++) {\r\n"
				+ "            end_time[i].style.backgroundColor=\"#6c757d\"\r\n" + "             \r\n" + " }\r\n"
				+ "\r\n" + "var methodName = document.querySelectorAll('h5.test-status');\r\n" + "\r\n"
				+ "for (var i=0; i<methodName.length; i++) {\r\n"
				+ "            methodName[i].style.fontSize=\"18px\"\r\n"
				+ "            methodName[i].style.fontFamily=\"Arial Black\"\r\n" + "\r\n" + "             \r\n"
				+ " }\r\n" + "\r\n"
				+ "var start_time = document.querySelectorAll('span.badge-default:not(.badge-pill):not(.uri-anchor)');\r\n"
				+ "\r\n" + "\r\n" + "for (var i=0; i<start_time.length; i++) {\r\n"
				+ "            start_time[i].style.backgroundColor=\"#343a40\"\r\n" + "             \r\n" + " }\r\n"
				+ "\r\n" + "\r\n" + "document.getElementsByClassName('logo')[0].style.backgroundImage = \"url('')\"\r\n"
				+ "\r\n" + "document.getElementsByClassName('nav-logo')[0].style.backgroundImage = \""
				+ FrameworkConstants.getExtentFrameworkLogoPath() + "\"\r\n" + "\r\n"
				+ "document.getElementsByClassName('nav-logo')[0].style.width=\"70px\"\r\n" + "\r\n"
				+ "document.getElementsByClassName('nav-logo')[0].style.height=\"70px\"";

	}

	/**
	 * Sets custom JS script in
	 * {@link com.aventstack.extentreports.reporter.ExtentSparkReporter} instance
	 * attached with {@link com.aventstack.extentreports.ExtentReports} which
	 * modifies the look and feel of the Extent Report.<br>
	 * 
	 * @param spark -
	 *              {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}
	 *              instance attached with
	 *              {@link com.aventstack.extentreports.ExtentReports}
	 */

	static void getCustomizedExtentReport(ExtentSparkReporter spark) {

		if (ConfigFactory.hasCustomizedReport()) {

			spark.config().setTheme(Theme.DARK);
			spark.config().setTimelineEnabled(false);
			spark.config().setJs(ExtentReportHelper.customizedJSForSparkReport());

		}

	}

	/**
	 * Sets 'Document Title' in
	 * {@link com.aventstack.extentreports.reporter.ExtentSparkReporter} instance
	 * attached with {@link com.aventstack.extentreports.ExtentReports} which
	 * reflects in the Extent Report.<br>
	 * 
	 * 
	 * @param spark         -
	 *                      {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}
	 *                      instance attached with
	 *                      {@link com.aventstack.extentreports.ExtentReports}
	 * @param documentTitle - title supposed to be displayed in Extent Report
	 */

	static void setDocumentTitleForExtentReport(ExtentSparkReporter spark, String documentTitle) {

		spark.config().setDocumentTitle(documentTitle);

	}

	/**
	 * Sets 'Report Name' in
	 * {@link com.aventstack.extentreports.reporter.ExtentSparkReporter} instance
	 * attached with {@link com.aventstack.extentreports.ExtentReports} which
	 * reflects in the Extent Report.<br>
	 * 
	 * @param spark      -
	 *                   {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}
	 *                   instance attached with
	 *                   {@link com.aventstack.extentreports.ExtentReports}
	 * @param reportName - Report name supposed to be displayed in Extent Report
	 */

	static void setReportNameForExtentReport(ExtentSparkReporter spark, String reportName) {

		spark.config().setReportName(reportName);

	}

	/**
	 * Sets Viewing Order in
	 * {@link com.aventstack.extentreports.reporter.ExtentSparkReporter} instance
	 * attached with {@link com.aventstack.extentreports.ExtentReports} which
	 * reflects in the Extent Report.<br>
	 * 
	 * @param spark-   {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}
	 *                 instance attached with
	 *                 {@link com.aventstack.extentreports.ExtentReports}
	 * @param viewname - array of
	 *                 {@link com.aventstack.extentreports.reporter.configuration.ViewName}
	 *                 from
	 *                 {@link com.jesusnut.constants.FrameworkConstants#getFrameworkExtentReportViewingOrder()}
	 */

	static void setViewingOrder(ExtentSparkReporter spark, ViewName[] viewname) {

		spark.viewConfigurer().viewOrder().as(viewname).apply();

	}

	/**
	 * Returns {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}
	 * instance which is later attached with
	 * {@link com.aventstack.extentreports.ExtentReports} in
	 * {@link ExtentReportHelper#attachReporter(ExtentReports, ExtentSparkReporter)}
	 * 
	 * @return {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}
	 *         instance
	 */

	static ExtentSparkReporter getExtentSparkReporterInstance() {

		return new ExtentSparkReporter(FrameworkConstants.getExtentReportPath());

	}

	/**
	 * Sets Test Environment (System information) in
	 * {@link com.aventstack.extentreports.ExtentReports}<br>
	 * 
	 * @param extent - {@link com.aventstack.extentreports.ExtentReports} instance
	 */

	static void setSystemInfo(ExtentReports extent) {

		extent.setSystemInfo(FrameworkConstants.ICON_OS, IconHelper.getOSIcon() + " " + System.getProperty("os.name")
				+ " :: Version - " + System.getProperty("os.version"));
		extent.setSystemInfo("Java", System.getProperty("java.version"));
		extent.setSystemInfo(FrameworkConstants.ICON_AUT, ConfigFactory.getURL());
		extent.setSystemInfo(FrameworkConstants.ICON_BROWSER,
				ConfigFactory.getBrowserFromConfig().toUpperCase() + " " + IconHelper.getBrowserIcon());
		setRemoteURLInSystemInfo(extent);

	}

	/**
	 * Provides 'Remote URL' information in
	 * {@link ExtentReportHelper#setSystemInfo(ExtentReports)} <br>
	 * 
	 * @param extent - {@link com.aventstack.extentreports.ExtentReports} instance
	 * 
	 * @see com.jesusnut.config.ConfigFactory#hasRemoteRunModeStatus()
	 */

	private static void setRemoteURLInSystemInfo(ExtentReports extent) {

		if (ConfigFactory.hasRemoteRunModeStatus()) {

			extent.setSystemInfo(FrameworkConstants.ICON_EXECUTION_MODE,
					FrameworkConstants.ICON_REMOTE_EXECUTION + " REMOTE");

			extent.setSystemInfo(FrameworkConstants.ICON_REMOTE_URL, ConfigFactory.getRemoteURL());

		}

		else {

			extent.setSystemInfo(FrameworkConstants.ICON_EXECUTION_MODE,
					FrameworkConstants.ICON_LOCAL_EXECUTION + " LOCAL");

		}
	}

	/**
	 * 
	 * Attaches {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}
	 * instance with {@link com.aventstack.extentreports.ExtentReports}<br>
	 * 
	 * @param extent - {@link com.aventstack.extentreports.ExtentReports} instance
	 * @param spark  -
	 *               {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}
	 *               instance attached with
	 *               {@link com.aventstack.extentreports.ExtentReports}
	 */

	static void attachReporter(ExtentReports extent, ExtentSparkReporter spark) {

		extent.attachReporter(spark);
		extent.setReportUsesManualConfiguration(true);
	}

}
