package com.jesusnut.utils;

import java.io.IOException;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.print.PrintOptions;

import com.jesusnut.config.ConfigFactory;
import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.driver.TLDriverManager;
import com.jesusnut.exception.FrameworkException;
import com.jesusnut.reports.extentreports.ExtentLogger;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility class having methods for operations on PDFs .<br>
 * Uses {@link com.google.zxing} and {@link com.google.zxing.client.j2se}
 * classes and interfaces.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

//TODO Write custom methods for operations on PDFs as per requirements using PDFUtil and Apache PDFBox.

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PDFUtils {

	public static void printPageToPDF(String urlOfPageToBeCaptured, String desiredNameOfPDFFile) {
		checkHeadlessMode();
		Ignitor.navigateToURL(urlOfPageToBeCaptured);
		Pdf pdf = ((PrintsPage) TLDriverManager.getDriver()).print(new PrintOptions());

		try {
			java.nio.file.Files.write(Paths.get(FrameworkConstants.getPrintPageToPDFOutputPath(desiredNameOfPDFFile)),
					OutputType.BYTES.convertFromBase64Png(pdf.getContent()));
			ExtentLogger.info("Created PDF from WebPage.");
		} catch (IOException e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Issue of IO operation while printing page to PDF",
					e);
		}
	}

	public static void printCurrentPageToPDF(String desiredNameOfPDFFile) {

		checkHeadlessMode();
		Pdf pdf = ((PrintsPage) TLDriverManager.getDriver()).print(new PrintOptions());

		try {
			java.nio.file.Files.write(Paths.get(FrameworkConstants.getPrintPageToPDFOutputPath(desiredNameOfPDFFile)),
					OutputType.BYTES.convertFromBase64Png(pdf.getContent()));
			ExtentLogger.info("Created PDF from WebPage.");
		} catch (IOException e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Issue of IO operation while printing page to PDF",
					e);
		}
	}

	private static void checkHeadlessMode() {

		if (!ConfigFactory.hasHeadlessRunModeStatus()) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> <b>Feature to print page to pdf is available only in HEADLESS MODE.</b>");
		}

	}

}
