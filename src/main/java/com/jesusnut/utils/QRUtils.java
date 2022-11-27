package com.jesusnut.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.exception.FrameworkException;
import com.jesusnut.reports.extentreports.ExtentLogger;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility class having methods which can be applied to read QR codes.<br>
 * Uses {@link com.google.zxing} and {@link com.google.zxing.client.j2se}
 * classes and interfaces.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QRUtils {

	public static String readQRCode(By locator, String webElementName) {

		String barcodeURL = Ignitor.getAttributeValue(locator, "src");
		try {
			URL url = new URL(barcodeURL);
			BufferedImage bufferedImage = ImageIO.read(url);
			LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
			Result result = new MultiFormatReader().decode(binaryBitmap);
			ExtentLogger.pass("Read [" + webElementName + "] QR code and the value is : ["
					+ FrameworkConstants.BOLD_START + result + FrameworkConstants.BOLD_END + "]");
			return result.getText();

		} catch (IOException | NotFoundException e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Issue while reading QR code.", e);

		}

	}

}
