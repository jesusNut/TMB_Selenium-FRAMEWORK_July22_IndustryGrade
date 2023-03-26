package com.jesusnut.constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.jesusnut.config.ConfigFactory;
import com.jesusnut.exception.FrameworkException;
import com.jesusnut.utils.DateTimeUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * FrameworkConstants class holds constants used within the Framework.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FrameworkConstants {

	static {

//      Implementation using java.io

//		File printPageToPDFDirectory = new File("./print-page-to-pdf-output/");
//		if (!printPageToPDFDirectory.exists()) {
//			printPageToPDFDirectory.mkdir();
//		}

//      Implementation using java.nio
		Path printPageToPDFDirectory = Paths.get("./print-page-to-pdf-output/");

		if (!Files.exists(printPageToPDFDirectory)) {

			try {
				Files.createDirectory(printPageToPDFDirectory);
			} catch (IOException e) {
				throw new FrameworkException(
						"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> IO issue while operation of checking if 'printPageToPDFDirectory' exists and then creating it.");
			}
		}

	}

	// Constants related to *** TEST RUNNER ***

	private static final String WORKING_DIRECTORY_PATH = System.getProperty("user.dir");
	private static final String TESTRUNNER_EXCELFILEPATH = WORKING_DIRECTORY_PATH
			+ "/src/test/resources/testrunner/testRunner.xlsx";
	private static final String TESTRUNNER_SHEETNAME = "TestRunner";

	// Constants related to *** PDF ***

	private static final String PRINTPAGETOPDFOUTPUT_FOLDER = WORKING_DIRECTORY_PATH + "/print-page-to-pdf-output/";
	private static ThreadLocal<String> printPageToPDF_TL_FilePath = new ThreadLocal<>();

	// Constants related to *** EXTENT REPORT ***

	private static final String EXTENTREPORTOUTPUT_FOLDER = WORKING_DIRECTORY_PATH + "/extent-test-output/";
	private static String extentReportFilePath = "";
	private static final String FRAMEWORK_EXTENTREPORT_DOCUMENT_TITLE = "JesusNut Framework Result";
	private static final String FRAMEWORK_EXTENTREPORT_NAME = "JesusNut Automation Framework Results";
	private static final ViewName[] FRAMEWORK_EXTENTREPORT_VIEWING_ORDER = new ViewName[] { ViewName.DASHBOARD,
			ViewName.TEST, ViewName.CATEGORY, ViewName.AUTHOR, ViewName.DEVICE, ViewName.LOG };
	private static final String FRAMEWORK_EXTENT_LOGO_PATH = "../src/main/resources/extentCustomizationResources/TMB.png";

	// Constants related to *** ICONS ***

	public static final String BOLD_START = "<b>";
	public static final String BOLD_END = "</b>";
	public static final String ICON_AUT = "<i class=\"fa fa-heartbeat\" aria-hidden=\"true\" style='font-size:20px'></i>";
	public static final String ICON_OS = "<i class=\"fa fa-desktop\" aria-hidden=\"true\" style='font-size:20px'></i>";
	public static final String ICON_BROWSER = "<i class=\"fa fa-window-restore\" aria-hidden=\"true\" style='font-size:20px'></i>";
	public static final String ICON_EXECUTION_MODE = "<i class=\"fa fa-play-circle-o\" aria-hidden=\"true\" style='font-size:20px'></i>";
	public static final String ICON_REMOTE_URL = "<i class=\"fa fa-plug\" aria-hidden=\"true\" style='font-size:20px'></i>";

	public static final String ICON_SMILEY_PASS = "<i class=\"fa fa-graduation-cap\" aria-hidden=\"true\" style=\"font-size:20px;color:#7AE81D\"></i>";
	public static final String ICON_SMILEY_SKIP = "<i class=\"fa fa-fast-forward\" aria-hidden=\"true\" style='font-size:20px'></i>";
	public static final String ICON_SMILEY_FAIL = "<i class=\"fa fa-bug\" aria-hidden=\"true\" style=\"font-size:20px;color:#ff5722\"></i>";

	public static final String ICON_OS_WINDOWS = "<i class='fa fa-windows' ></i>";
	public static final String ICON_OS_MAC = "<i class='fa fa-apple' ></i>";
	public static final String ICON_OS_LINUX = "<i class='fa fa-linux' ></i>";
	public static final String ICON_OS_UNIDENTIFIED = "<i class=\'fa fa-binoculars'</i>";

	public static final String ICON_BROWSER_OPERA = "<i class=\"fa fa-opera\" aria-hidden=\"true\"></i>";
	public static final String ICON_BROWSER_EDGE = "<i class=\"fa fa-edge\" aria-hidden=\"true\"></i>";
	public static final String ICON_BROWSER_CHROME = "<i class=\"fa fa-chrome\" aria-hidden=\"true\"></i>";
	public static final String ICON_BROWSER_FIREFOX = "<i class=\"fa fa-firefox\" aria-hidden=\"true\"></i>";
	public static final String ICON_BROWSER_SAFARI = "<i class=\"fa fa-safari\" aria-hidden=\"true\"></i>";

	public static final String ICON_REMOTE_EXECUTION = "<i class=\"fa fa-sitemap\" aria-hidden=\"true\"></i>";
	public static final String ICON_LOCAL_EXECUTION = "<i class=\"fa fa-wrench\" aria-hidden=\"true\"></i>";

	/**
	 * Returns Extent Report path.
	 * 
	 * @return Extent Report path in String format.
	 * @see FrameworkConstants#createExtentReportPath()
	 */

	public static String getExtentReportPath() {

		if (extentReportFilePath.isEmpty()) {
			extentReportFilePath = createExtentReportPath();
		}
		return extentReportFilePath;
	}

	/**
	 * Returns output path for the page printed as pdf(printPageToPDF_TL_FilePath).
	 * 
	 * @return path for the page printed as pdf in String format.
	 * @see FrameworkConstants#createPrintPageToPDFOutputPath(String)
	 * @param desiredNameOfPDFFile desired name of pdf file that user wants
	 */

	public static String getPrintPageToPDFOutputPath(String desiredNameOfPDFFile) {

		setThreadLocaledPrintPageToPDFOutputPath(createPrintPageToPDFOutputPath(desiredNameOfPDFFile));

		return getThreadLocaledPrintPageToPDFOutputPath();
	}

	private static String getThreadLocaledPrintPageToPDFOutputPath() {
		return printPageToPDF_TL_FilePath.get();
	}

	private static void setThreadLocaledPrintPageToPDFOutputPath(String printPageToPDFOutputPath) {
		printPageToPDF_TL_FilePath.set(printPageToPDFOutputPath);
	}

	/**
	 * Returns TestRunner Excel File path.
	 * 
	 * @return TestRunner Excel File path in String format.
	 */

	public static String getTestRunnerExcelFilePath() {

		return TESTRUNNER_EXCELFILEPATH;
	}

	/**
	 * Returns TestRunner Excel Sheet Name.
	 * 
	 * @return TestRunner Excel Sheet Name in String format.
	 */

	public static String getTestRunnerSheetName() {

		return TESTRUNNER_SHEETNAME;
	}

	/**
	 * Returns Document Title for Extent Report.
	 * 
	 * @return Document Title for Extent Report in String format.
	 */

	public static String getFrameworkExtentReportDocumentTitle() {

		return FRAMEWORK_EXTENTREPORT_DOCUMENT_TITLE;
	}

	/**
	 * Returns Report name for Extent Report.
	 * 
	 * @return Report name for Extent Report in String format.
	 */

	public static String getFrameworkExtentReportName() {

		return FRAMEWORK_EXTENTREPORT_NAME;
	}

	/**
	 * Returns Viewing Order for Extent Report.<br>
	 * Viewing order shows the views in a Specified order on the left hand side of
	 * Extent Report.
	 * 
	 * @return ViewName array for Extent Report.
	 */

	public static ViewName[] getFrameworkExtentReportViewingOrder() {

		return FRAMEWORK_EXTENTREPORT_VIEWING_ORDER;
	}

	/**
	 * Creates Extent Report Path based on overrideReports property as provided by
	 * user through
	 * {@link com.jesusnut.config.ConfigFactory#hasOverrideReports()}<br>
	 * 
	 * @return Extent Report Path in String format.
	 * @see FrameworkConstants#getExtentReportPath()
	 */

	private static String createExtentReportPath() {

		if (!ConfigFactory.hasOverrideReports()) {
			String finalDateTimeFormat = DateTimeUtils.getLocalDateTime();
			return EXTENTREPORTOUTPUT_FOLDER + "/" + finalDateTimeFormat + "_" + "index.html";
		}

		else {
			return EXTENTREPORTOUTPUT_FOLDER + "/index.html";
		}

	}

	/**
	 * Creates output path for the page printed as
	 * pdf(printPageToPDF_TL_FilePath).<br>
	 * Checks './print-page-to-pdf-output/' folder path first in project directory
	 * and creates it of not already present.
	 * 
	 * @return Extent Report Path in String format.
	 * @param desiredNameOfPDFFile desired name of pdf file that user wants
	 * @see FrameworkConstants#getPrintPageToPDFOutputPath(String)
	 */

	private static String createPrintPageToPDFOutputPath(String desiredNameOfPDFFile) {

		String finalDateTimeFormat = DateTimeUtils.getLocalDateTime();

		return PRINTPAGETOPDFOUTPUT_FOLDER + "/" + finalDateTimeFormat + "_" + desiredNameOfPDFFile + ".pdf";

	}

	/**
	 * Creates a path for Logo used in the customized Extent Report based on
	 * overrideReports property as provided by user through
	 * {@link com.jesusnut.config.ConfigFactory#hasOverrideReports()}<br>
	 * 
	 * @return Path for Logo used in Customized Extent Report; in String format.
	 * @see com.jesusnut.config.ConfigFactory#hasOverrideReports()
	 * @see com.jesusnut.reports.extentreports.ExtentReportHelper
	 */

	public static String getExtentFrameworkLogoPath() {

		return "url('" + FRAMEWORK_EXTENT_LOGO_PATH + "')";

	}

}
