package com.jesusnut.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import com.jesusnut.helper.MethodInterceptorExcelHelper;

/**
 * 
 * MethodInterceptor is a listener class impelementing
 * {@link org.testng.IMethodInterceptor}.
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

public class MethodInterceptor implements IMethodInterceptor {

	/**
	 * Before every test suite run, intercepts the test methods which are eligible
	 * for test execution i.e. present in TestRunner Excel File AND are enabled.<br>
	 * Feeds the list of eligible test methods to {@link org.testng.TestRunner} for
	 * execution in that particular test suite run.
	 * 
	 */

	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

		List<IMethodInstance> finalListOfMethodsToBeExecuted = new ArrayList<>();

		List<Map<String, String>> listOfAllTestsFromTestRunner = MethodInterceptorExcelHelper
				.feedDataToMethodInterceptor();

		for (int i = 0; i < methods.size(); i++) {

			for (int j = 0; j < listOfAllTestsFromTestRunner.size(); j++) {

				String tempMethodName = listOfAllTestsFromTestRunner.get(j).get("testcasename");

				String executionStatus = listOfAllTestsFromTestRunner.get(j).get("execute");

				if (isTestRunnerMethodEligibleForFinalList(methods, i, tempMethodName, executionStatus)) {

					finalListOfMethodsToBeExecuted.add(methods.get(i));

				}

			}

		}
		return finalListOfMethodsToBeExecuted;
	}

	/**
	 * * Checks whether or not a test method fetched from TestRunner excel file is
	 * eligible for test execution in that particular test suite run i.e. present in
	 * TestRunner Excel File AND is enabled.
	 * 
	 * @param methods         ImethodInstance
	 * @param i               index in caller method
	 * @param tempMethodName  local variable in caller method
	 * @param executionStatus local variable in caller method
	 * @return whether or not a test method is eligible for test execution i.e.
	 *         present in TestRunner Excel File AND are enabled.
	 * @see MethodInterceptor#intercept(List, ITestContext)
	 *
	 */
	private static boolean isTestRunnerMethodEligibleForFinalList(List<IMethodInstance> methods, int i,
			String tempMethodName, String executionStatus) {
		return methods.get(i).getMethod().getMethodName().equalsIgnoreCase(tempMethodName)
				&& ("yes").equalsIgnoreCase(executionStatus);
	}

}
