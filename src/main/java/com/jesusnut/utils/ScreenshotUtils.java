package com.jesusnut.utils;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.jesusnut.driver.TLDriverManager;
import com.jesusnut.exception.FrameworkException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility Class to capture screenshots and full page screenshots.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScreenshotUtils {

	public static String getScreenshot() {
		return ((TakesScreenshot) TLDriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
	}

	public static String getFullScreenScreenShotAShotAPI() {

		byte[] imageData = null;

		try {

			imageData = Shutterbug.shootPage(TLDriverManager.getDriver(), Capture.FULL, 100, true).getBytes();

		} catch (IOException e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Issue while taking screenshot of a failed step ",
					e);
		}

		return EncodeDecodeUtils.encodeByteArrToBase64String(imageData);

	}

}
