package com.jesusnut.config;

import java.util.Objects;

import com.jesusnut.exception.FrameworkException;
import com.jesusnut.utils.EncodeDecodeUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * ConfigFactory class is responsible to check validity of data from
 * configuration files, system properties and environment variables and provides
 * those data to other Classes and test methods in this Framework.<br>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see FrameworkConfig
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigFactory {

	/**
	 * Creates a {@link org.aeonbits.owner.Config} instance from the specified
	 * interface {@link FrameworkConfig}<br>
	 * The returned instance of this method is then used to call abstract methods
	 * defined in interface {@link FrameworkConfig}
	 * 
	 * @return an instance of
	 *         {@link FrameworkConfig}/{@link org.aeonbits.owner.Config}
	 * 
	 */

	private static FrameworkConfig getConfig() {

		return org.aeonbits.owner.ConfigFactory.create(FrameworkConfig.class);

	}

	/**
	 * Checks validity of environment value in config file/system property/system
	 * env.
	 * 
	 * @throws FrameworkException if value of environment is null or blank ( if the
	 *                            string is empty or contains only white space
	 *                            codepoints).
	 * 
	 * @see FrameworkConfig#environment()
	 * @see ConfigFactory#getURL()
	 */

	private static void checkEnvironmentValue() {

		String environmentValue = getConfig().environment();

		if (((Objects.isNull(environmentValue)) || environmentValue.isBlank())) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Either user has provided [environment] key as blank or has not provided at all; through System properties/System environment/Config.properties files. \n Please provide a valid environment. Allowed values are ['qa'/'dev'/'uat']");

		}

	}

	/**
	 * Checks validity of url value (String) in config file/system property/system
	 * env and provides url value in other classes of this framework.<br>
	 * {@link ConfigFactory#checkEnvironmentValue()} is called before this method.
	 * 
	 * @return url of the AUT
	 * @throws FrameworkException if value of url is null or blank ( if the string
	 *                            is empty or contains only white space codepoints)
	 *                            OR value of environment is not supported by
	 *                            framework.
	 * 
	 * @see FrameworkConfig#url()
	 * @see com.jesusnut.driver.Driver#initDriver()
	 * @see com.jesusnut.reports.extentreports.ExtentReportHelper#setSystemInfo(com.aventstack.extentreports.ExtentReports)
	 */

	public static String getURL() {

		ConfigFactory.checkEnvironmentValue();

		String url = getConfig().url();

		if (!((Objects.isNull(url)) || url.isBlank())) {

			return url.trim();
		}

		else {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> [environment] key value provided by user is wrong. Allowed values are ['qa'/'dev'/'uat'] OR \n [url] key provided through System properties/System environment/Config.properties files is either missing/commented out or blank. Please provide a valid url ");
		}

	}

	/**
	 * Checks validity of browser value (String) in config file/system
	 * property/system env and provides browser value in other classes of this
	 * framework.<br>
	 * 
	 * @return browser on which test needs to be excuted.
	 * @throws FrameworkException if value of browser is null or blank ( if the
	 *                            string is empty or contains only white space
	 *                            codepoints).
	 * 
	 * @see FrameworkConfig#browser()
	 * @see com.jesusnut.driver.Driver#initDriver()
	 * @see com.jesusnut.reports.extentreports.ExtentReportHelper#setSystemInfo(com.aventstack.extentreports.ExtentReports)
	 * @see com.jesusnut.helper.IconHelper#getBrowserIcon()
	 */

	public static String getBrowserFromConfig() {

		String browserValueFromConfig = getConfig().browser();

		if (!((Objects.isNull(browserValueFromConfig)) || browserValueFromConfig.isBlank())) {

			return String.valueOf(browserValueFromConfig).trim().toLowerCase();
		}

		else {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> [browser] key provided through System properties/System environment/Config.properties files is either missing/commented out or blank. Please provide a valid browser");
		}

	}

	/**
	 * Checks validity of customizedReport value (boolean) in config file/system
	 * property/system env and provides customizedReport value in other classes of
	 * this framework.<br>
	 * 
	 * @return whether or not customized extent report is needed.
	 * @throws FrameworkException if value of customizedReport is null (not present)
	 *                            or any non boolean value.
	 * 
	 * @see FrameworkConfig#customizedReport()
	 * @see com.jesusnut.reports.extentreports.ExtentReportHelper#getCustomizedExtentReport(com.aventstack.extentreports.reporter.ExtentSparkReporter)
	 */

	public static boolean hasCustomizedReport() {

		boolean customizedReportValue;

		try {

			customizedReportValue = getConfig().customizedReport();

		} catch (UnsupportedOperationException | NullPointerException e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Please enter correct values [true/false] for [customizedReport] key in System properties/System environment/config.properties files. Entered value is either blank or incorrect OR \n [customizedReport] key provided through System properties/System environment/Config.properties files is either missing/commented out",
					e);

		}
		return customizedReportValue;

	}

	/**
	 * Checks validity of passedStepsScreenshots value (boolean) in config
	 * file/system property/system env and provides passedStepsScreenshots value in
	 * other classes of this framework.<br>
	 * 
	 * @return whether or not passedStepsScreenshots is needed.
	 * @throws FrameworkException if value of passedStepsScreenshots is null (not
	 *                            present) or any non boolean value.
	 * 
	 * @see FrameworkConfig#passedStepsScreenshots()
	 * @see com.jesusnut.reports.extentreports.ExtentLogger#pass(String)
	 */

	public static boolean hasPassedStepsScreenshots() {

		boolean passedStepsScreenshotsValue;

		try {

			passedStepsScreenshotsValue = getConfig().passedStepsScreenshots();

		} catch (UnsupportedOperationException | NullPointerException e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Please enter correct values [true/false] for [passedStepsScreenshots] key in System properties/System environment/config.properties files. Entered value is either blank or incorrect OR \n [passedStepsScreenshots] key provided through System properties/System environment/Config.properties files is either missing/commented out",
					e);

		}
		return passedStepsScreenshotsValue;

	}

	/**
	 * Checks validity of overrideReports value (boolean) in config file/system
	 * property/system env and provides overrideReports value in other classes of
	 * this framework.<br>
	 * 
	 * @return whether or not overriden extent report is needed.
	 * @throws FrameworkException if value of overrideReports is null (not present)
	 *                            or any non boolean value.
	 * 
	 * @see FrameworkConfig#overrideReports()
	 * @see com.jesusnut.constants.FrameworkConstants#getExtentReportPath()
	 * @see com.jesusnut.constants.FrameworkConstants#getExtentFrameworkLogoPath()
	 */

	public static boolean hasOverrideReports() {

		boolean overrideReportsValue;

		try {

			overrideReportsValue = getConfig().overrideReports();

		} catch (UnsupportedOperationException | NullPointerException e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Please enter correct values [true/false] for [overrideReports] key in System properties/System environment/config.properties files. Entered value is either blank or incorrect OR \n [overrideReports] key provided through System properties/System environment/Config.properties files is either missing/commented out",
					e);

		}
		return overrideReportsValue;

	}

	/**
	 * Checks validity of highlightElements value (boolean) in config file/system
	 * property/system env and provides highlightElements value in other classes of
	 * this framework.<br>
	 * 
	 * @return whether or not highlighting of elements (being acted upon) is needed
	 *         in screenshots.
	 * @throws FrameworkException if value of highlightElements is null (not
	 *                            present) or any non boolean value.
	 * 
	 * @see FrameworkConfig#highlightedElements()
	 * @see com.jesusnut.utils.Ignitor
	 */

	public static boolean hasHighlightElements() {

		boolean highlightElementsValue;

		try {

			highlightElementsValue = getConfig().highlightedElements();

		} catch (UnsupportedOperationException | NullPointerException e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Please enter correct values [true/false] for [highlightElements] key in System properties/System environment/config.properties files. Entered value is either blank or incorrect OR \n [highlightElements] key provided through System properties/System environment/Config.properties files is either missing/commented out",
					e);

		}
		return highlightElementsValue;

	}

	/**
	 * Checks validity of username value (String) in config file/system
	 * property/system env and provides username value in other <b>test methods</b>
	 * of this framework.<br>
	 * 
	 * @return username value
	 * @throws FrameworkException if value of username is null or blank ( if the
	 *                            string is empty or contains only white space
	 *                            codepoints).
	 * 
	 * @see FrameworkConfig#username()
	 */

	public static String getUserName() {

		String username = getConfig().username();

		if (!((Objects.isNull(username)) || (username.length() == 0))) {

			return username.trim();
		}

		else {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> [username] for the AUT provided through System properties/System environment/Config.properties files is either missing/commented out or blank. Please provide a valid username");
		}

	}

	/**
	 * Checks validity of password value (String) in config file/system
	 * property/system env and provides <b>decrypted password value </b> in other
	 * <b>test methods</b> of this framework.<br>
	 * 
	 * @return decrypted password value
	 * @throws FrameworkException if value of password is null or blank ( if the
	 *                            string is empty or contains only white space
	 *                            codepoints).
	 * 
	 * @see FrameworkConfig#password()
	 */

	public static String getPassword() {

		String encryptedPassword = getConfig().password();

		if (!((Objects.isNull(encryptedPassword)) || (encryptedPassword.length() == 0))) {

			return EncodeDecodeUtils.decrypt(encryptedPassword);

		}

		else {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> [password] for the AUT provided through System properties/System environment/Config.properties files is either missing/commented out or blank. Please provide a valid password");
		}

	}

	/**
	 * Checks validity of password value (String) in config file/system
	 * property/system env and provides <b>encrypted password value </b> in other
	 * classes of this framework.<br>
	 * 
	 * @return encrypted password value
	 * @throws FrameworkException if value of password is null or blank ( if the
	 *                            string is empty or contains only white space
	 *                            codepoints).
	 * 
	 * @see FrameworkConfig#password()
	 */

	public static String getEncryptedPasswordFromConfig() {

		String encryptedPassword = getConfig().password();

		if (!((Objects.isNull(encryptedPassword)) || (encryptedPassword.length() == 0))) {

			return encryptedPassword;
		}

		else {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> [password] for the AUT provided through System properties/System environment/Config.properties files is either missing/commented out or blank. Please provide a valid password");
		}

	}

	/**
	 * Checks validity of remote value (boolean) in config file/system
	 * property/system env and provides remote value in other classes of this
	 * framework.<br>
	 * 
	 * @return whether or not execution of test suite is needed on remote
	 *         infrastructure
	 * 
	 * @throws FrameworkException if value of remote is null (not present) or any
	 *                            non boolean value.
	 * 
	 * @see FrameworkConfig#remoteRunModeStatus()
	 * @see com.jesusnut.driver.DriverFactory#getDriver(String)
	 * @see com.jesusnut.reports.extentreports.ExtentReportHelper#setSystemInfo(com.aventstack.extentreports.ExtentReports)
	 */

	public static boolean hasRemoteRunModeStatus() {

		boolean remoteRunModeStatusValue;

		try {

			remoteRunModeStatusValue = getConfig().remoteRunModeStatus();

		} catch (UnsupportedOperationException | NullPointerException e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Please enter correct values [true/false] for [remote] key in System properties/System environment/config.properties files. Entered value is either blank or incorrect OR \n [remote] key provided through System properties/System environment/Config.properties files is either missing/commented out",
					e);

		}
		return remoteRunModeStatusValue;

	}

	/**
	 * Checks validity of remoteURL value (String) in config file/system
	 * property/system env and provides remoteURL value in other classes of this
	 * framework.<br>
	 * 
	 * @return remoteURL - for remote execution
	 * @throws FrameworkException if value of remoteURL is null or blank ( if the
	 *                            string is empty or contains only white space
	 *                            codepoints).
	 * 
	 * @see FrameworkConfig#remoteURL()
	 * @see com.jesusnut.driver.RemoteDriverFactory
	 * @see com.jesusnut.reports.extentreports.ExtentReportHelper#setSystemInfo(com.aventstack.extentreports.ExtentReports)
	 */

	public static String getRemoteURL() {

		String remoteURLValue = getConfig().remoteURL();

		if (!((Objects.isNull(remoteURLValue)) || (remoteURLValue.length() == 0))) {

			return remoteURLValue.trim();
		}

		else {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>>  [remoteURL] key provided through System properties/System environment/Config.properties files is either missing/commented out or blank. Please provide a valid remote url ");
		}

	}

	/**
	 * Checks validity of headless value (boolean) in config file/system
	 * property/system env and provides headless value in other classes of this
	 * framework.<br>
	 * 
	 * @return whether or not execution of test suite is needed in headless mode
	 * 
	 * @throws FrameworkException if value of headless is null (not present) or any
	 *                            non boolean value.
	 * 
	 * @see FrameworkConfig#headlessRunModeStatus()
	 * @see com.jesusnut.driver.BrowserOptionsFactory
	 */

	public static boolean hasHeadlessRunModeStatus() {

		boolean headlessRunModeStatusValue;

		try {

			headlessRunModeStatusValue = getConfig().headlessRunModeStatus();

		} catch (UnsupportedOperationException | NullPointerException e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Please enter correct values [true/false] for [headless] key in System properties/System environment/config.properties files. Entered value is either blank or incorrect OR \n [headless] key provided through System properties/System environment/Config.properties files is either missing/commented out",
					e);

		}
		return headlessRunModeStatusValue;

	}

	/**
	 * Checks validity of incognito value (boolean) in config file/system
	 * property/system env and provides incognito value in other classes of this
	 * framework.<br>
	 * 
	 * @return whether or not execution of test suite is needed in incognito/private
	 *         mode
	 * 
	 * @throws FrameworkException if value of incognito is null (not present) or any
	 *                            non boolean value.
	 * 
	 * @see FrameworkConfig#incognitoRunModeStatus()
	 * @see com.jesusnut.driver.BrowserOptionsFactory
	 */

	public static boolean hasIncognitoRunModeStatus() {

		boolean incognitoRunModeStatusValue;

		try {

			incognitoRunModeStatusValue = getConfig().incognitoRunModeStatus();

		} catch (UnsupportedOperationException | NullPointerException e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Please enter correct values [true/false] for [incognito] key in System properties/System environment/config.properties files. Entered value is either blank or incorrect OR \n [incognito] key provided through System properties/System environment/Config.properties files is either missing/commented out",
					e);

		}
		return incognitoRunModeStatusValue;

	}

}
