package com.jesusnut.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.exception.FrameworkException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * MethodInterceptorExcelHelper class feeds data from TestRunner excel file to
 * {@link com.jesusnut.listeners.MethodInterceptor}
 * 
 * <pre>
 *  TestRunner excel file contains list of all test methods in the framework.
 * User can disable/enable test methods directly from TestRunner excel file for a test run without touching the framework code.
 * <b> It is mandatory for a test method to be added in the Test Runner file and kept enabled in the TestRunner excel for it to run.(assuming that the test method is enabled in code AND present in test suite) </b>
 * <b> Test methods not included in TestRunner file will not run even if they are enabled in code AND present in test suite. </b>
 * </pre>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MethodInterceptorExcelHelper {

	/**
	 * Feeds data from TestRunner excel file to
	 * {@link com.jesusnut.listeners.MethodInterceptor}
	 * 
	 * @return data to be feeded to {@link com.jesusnut.listeners.MethodInterceptor}
	 * @throws FrameworkException if TestRunner excel file is not found or issue in
	 *                            IO operation with TestRunner excel file.
	 */

	public static List<Map<String, String>> feedDataToMethodInterceptor() {

		List<Map<String, String>> list = null;

		try (FileInputStream fis = new FileInputStream(new File(FrameworkConstants.getTestRunnerExcelFilePath()));
				Workbook workBook = new XSSFWorkbook(fis)) {

			Sheet sheet = workBook.getSheet(FrameworkConstants.getTestRunnerSheetName());

			int lastrownum = sheet.getLastRowNum();
			int lastcolnum = sheet.getRow(0).getLastCellNum();

			Map<String, String> map = null;
			list = new ArrayList<>();

			for (int i = 1; i <= lastrownum; i++) {
				map = new HashMap<>();
				for (int j = 0; j < lastcolnum; j++) {
					String key = sheet.getRow(0).getCell(j).getStringCellValue();
					String value = sheet.getRow(i).getCell(j).getStringCellValue();
					map.put(key, value);
				}
				list.add(map);
			}

		}

		catch (FileNotFoundException e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Test Runner excel file could not be found. Please check Test runner excel file path in com.jesusnut.constants.FrameworkConstants and/or validate the location of Test Runner excel file in Project",
					e);

		} catch (IOException e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Issues in input/output operation in TestRunner Excel file data.",
					e);

		}

		return list;

	}

}
