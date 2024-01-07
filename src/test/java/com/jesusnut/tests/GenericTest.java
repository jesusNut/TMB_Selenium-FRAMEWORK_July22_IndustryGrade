package com.jesusnut.tests;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jesusnut.annotations.FrameworkAnnotation;
import com.jesusnut.base.BaseTest;
import com.jesusnut.config.ConfigFactory;
import com.jesusnut.enums.Category;
import com.jesusnut.testdataPojo.exceltestdatamapper.GenericTest_excelDataSupplierTest_ETDM;
import com.jesusnut.testdataPojo.jsontestdatamapper.GenericTest_jsonDataSupplierTest_JTDM;
import com.jesusnut.utils.BidiUtils;
import com.jesusnut.utils.DateTimeUtils;
import com.jesusnut.utils.EncodeDecodeUtils;
import com.jesusnut.utils.FakeDataUtils;
import com.jesusnut.utils.Ignitor;
import com.jesusnut.utils.JSUtils;
import com.jesusnut.utils.LanguageUtils;
import com.jesusnut.utils.PDFUtils;
import com.jesusnut.utils.SmartWaitUtils;

//This class and test methods in it does not belong to any Page on the AUT : https://naveenautomationlabs.com/opencart/index.php?route=account/login

public class GenericTest extends BaseTest {

	@FrameworkAnnotation(category = { Category.SMOKE })
	@Test(description = "dummy test for JSON data supplier test", groups = "Smoke", dataProvider = "Test Data Supplier for -> Method: jsonDataSupplierTest , TestClass: TestClass: com.jesusnut.tests.GenericTest", dataProviderClass = com.jesusnut.helper.DataSupplierHelper.class, enabled = true)
	public void jsonDataSupplierTest(GenericTest_jsonDataSupplierTest_JTDM testdata) {

		Ignitor.navigateToURL("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
		System.out.println(testdata.getEmail());
		System.out.println(testdata.getPassword());
		Ignitor.doSendKeys(By.cssSelector("#input-email"), testdata.getEmail(), "Email");
		Ignitor.doSendKeys(By.cssSelector("#input-password"), EncodeDecodeUtils.decrypt(testdata.getPassword()),
				"Password");
		Ignitor.doClick(By.xpath("//input[@value='Login']"), "Login button");
		SmartWaitUtils.sleep(2);
		Assert.assertEquals(Ignitor.getURL(),
				"https://ecommerce-playground.lambdatest.io/index.php?route=account/account");

	}

	@FrameworkAnnotation(category = { Category.SMOKE })
	@Test(description = "dummy test for printing", groups = "Smoke", enabled = true)
	public void printValuesTest() throws InterruptedException {

		SmartWaitUtils.sleep(2);
		Ignitor.navigateToURL("http://omayo.blogspot.com/");
		PDFUtils.printCurrentPageToPDF("OmayoPage");
		SmartWaitUtils.sleep(2);
		JSUtils.navigateBackUsingJS();
		SmartWaitUtils.sleep(2);
		JSUtils.navigateForwardUsingJS();
		SmartWaitUtils.sleep(2);
		JSUtils.navigateToURLUsingJS("https://www.selenium.dev/");
		SmartWaitUtils.sleep(2);
		boolean displayStatus = Ignitor.verifyWebElementDisplayed(By.xpath("//h2[text()='News']"), "News");
		System.out.println("Print values test executed successfully..");
		Assertions.assertThat(displayStatus).isTrue();
		SmartWaitUtils.sleep(2);
		PDFUtils.printPageToPDF("https://www.bbc.com/", "bbcPage");

	}

	@FrameworkAnnotation(category = { Category.SMOKE })
	@Test(description = "dummy test for excel data supplier test", groups = "Smoke", dataProvider = "Test Data Supplier for -> Method: excelDataSupplierTest, TestClass: com.jesusnut.tests.GenericTest", dataProviderClass = com.jesusnut.helper.DataSupplierHelper.class, enabled = true)
	public void excelDataSupplierTest(GenericTest_excelDataSupplierTest_ETDM testdata) {

		System.out.println("I am " + testdata.getName());
		System.out.println("Father is " + testdata.getFathersName());
		System.out.println("Age is " + testdata.getAge());
		System.out.println("Salary is " + testdata.getSalary());

		// printing configs

		System.out.println(ConfigFactory.hasCustomizedReport());
		System.out.println(ConfigFactory.hasHighlightElements());
		System.out.println(ConfigFactory.hasOverrideReports());
		System.out.println(ConfigFactory.hasPassedStepsScreenshots());
		System.out.println(ConfigFactory.getPassword());
		System.out.println(ConfigFactory.getURL());
		System.out.println(ConfigFactory.getUserName());

		// printing times from com.jesusnut.utils.DateTimeUtils

		System.out.println(DateTimeUtils.getLocalDate());
		System.out.println(DateTimeUtils.getLocalTime());
		System.out.println(DateTimeUtils.getLocalDateTime());
		System.out.println(DateTimeUtils.getZonedDate("Pacific/Midway"));
		System.out.println(DateTimeUtils.getZonedTime("Pacific/Midway"));
		System.out.println(DateTimeUtils.getZonedDateTime("Pacific/Midway"));

		// checking methods from com.jesusnut.utils.LanguageUtils

		char data = 'Ý';
		System.out.println(LanguageUtils.removeAccent(data));
		String data1 = "LoreÀẽờmIpsum";
		System.out.println(LanguageUtils.removeAccent(data1).toLowerCase());
		System.out.println(LanguageUtils.convertCharset_ISO_8859_1_To_UTF8("abhishek is the name"));

		// checking methods from com.jesusnut.utils.FakeDataUtils

		System.out.println(FakeDataUtils.buildUniqueAlphaNumericTextWithSplChar("monu", "777chand"));
		System.out.println(FakeDataUtils.getUniqueAlphaNumericStringWithSplChar());
		// System.out.println(FakeDataUtils.buildUniqueText(null, null));

	}

	@FrameworkAnnotation(category = { Category.SMOKE })
	@Test(description = "dummy method for checking utility methods - 1", groups = "Smoke", enabled = true)
	public void checkUtilityMethods1() throws InterruptedException {

		// emulate device size

		BidiUtils.emulateMobileScreenResolution(390, 844, 100, true);
		Ignitor.navigateToURL("https://rahulshettyacademy.com/angularAppdemo/");
		Ignitor.waitForURLToBe("https://rahulshettyacademy.com/angularAppdemo/");

		// print pdf of the current screen at this moment- PDFUtils method check

		PDFUtils.printCurrentPageToPDF("RSPage");

		Ignitor.doClick(By.xpath("//button[@aria-label='Toggle navigation']"), "Hamburger Menu");
		Ignitor.waitForElementAttributeToContain(By.xpath("//button[@aria-label='Toggle navigation']"),
				"Hamburger expanded", "aria-expanded", "true");
		int temp_count = Ignitor.getElementsCount(By.xpath("//div[@id='navbarSupportedContent']/ul/li/a"));
		Assertions.assertThat(temp_count).isEqualTo(3);
		SmartWaitUtils.sleep(5);

		// emulate geo-location

		Ignitor.navigateToURL("https://my-location.org/");
		SmartWaitUtils.sleep(3);
		BidiUtils.emulateGeoLocation(30, 75);
		Ignitor.navigateToURL("https://my-location.org/");
		SmartWaitUtils.sleep(5);

		// auth browser

		BidiUtils.getURLAfterBrowserAuth("the-internet.herokuapp.com", "https://the-internet.herokuapp.com/basic_auth",
				"admin", "admin");
		Ignitor.navigateToURL("http://omayo.blogspot.com/");
		Ignitor.doSendKeys(By.xpath("//textarea[@id='ta1']"), "Abhishek is great", "textboxxx");

		// check chrome and edge browser permissions - geolocation allow

		Ignitor.navigateToURL("https://whatmylocation.com/");
		SmartWaitUtils.sleep(5);

		// check chrome and edge browser permissions - mic allow

		Ignitor.navigateToURL("https://mictests.com/");
		SmartWaitUtils.waitForPageLoaded();
		Ignitor.waitForElementAttributeToContain(By.xpath("//div[@id='mic-controls']"), "mic control checker", "style",
				"block");
		Ignitor.waitForElementTextToContain(By.xpath("//button[@id='mic-launcher']"), "micbutton", "Test my mic");
		Ignitor.doClick(By.xpath("//button[@id='mic-launcher']"), "micbutton");
		SmartWaitUtils.sleep(10);

		// check chrome and edge browser permissions - camera allow

		Ignitor.launchURL("https://webcamtests.com/");
		SmartWaitUtils.waitForPageLoaded();
		Ignitor.waitForElementTextToBe(By.xpath("//button[@id='webcam-launcher']"), "Test my cam button",
				"Test my cam");
		Ignitor.doClick(By.xpath("//button[@id='webcam-launcher']"), "Test my cam button");
		SmartWaitUtils.sleep(10);

	}

	@FrameworkAnnotation(category = { Category.SMOKE })
	@Test(description = "dummy method for checking utility methods - 2", groups = "Smoke", enabled = true)
	public void checkUtilityMethods2() throws InterruptedException {

		BidiUtils.getURLAfterBrowserAuth("the-internet.herokuapp.com", "https://the-internet.herokuapp.com/basic_auth",
				"admin", "admin");
		SmartWaitUtils.sleep(5);
		Ignitor.navigateToURL("http://omayo.blogspot.com/");
		Ignitor.doSendKeys(By.xpath("//textarea[@id='ta1']"), "Abhishek is great", "textboxxx");
		SmartWaitUtils.sleep(5);

		// check chrome and edge browser permissions - geolocation allow

		Ignitor.navigateToURL("https://whatmylocation.com/");
		SmartWaitUtils.sleep(9);

		// check chrome and edge browser permissions - mic allow

		Ignitor.navigateToURL("https://mictests.com/");
		SmartWaitUtils.waitForPageLoaded();
		Ignitor.waitForElementAttributeToContain(By.xpath("//div[@id='mic-controls']"), "mic control checker", "style",
				"block");
		Ignitor.waitForElementTextToContain(By.xpath("//button[@id='mic-launcher']"), "micbutton", "Test my mic");
		Ignitor.doClick(By.xpath("//button[@id='mic-launcher']"), "micbutton");
		SmartWaitUtils.sleep(10);

		// check chrome and edge browser permissions - camera allow

		Ignitor.launchURL("https://webcamtests.com/");
		SmartWaitUtils.waitForPageLoaded();
		Ignitor.waitForElementTextToBe(By.xpath("//button[@id='webcam-launcher']"), "Test my cam button",
				"Test my cam");
		Ignitor.doClick(By.xpath("//button[@id='webcam-launcher']"), "Test my cam button");
		SmartWaitUtils.sleep(10);

	}

}
