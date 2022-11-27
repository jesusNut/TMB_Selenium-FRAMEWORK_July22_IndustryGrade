package com.jesusnut.helper;

import java.lang.reflect.Method;

import com.jesusnut.testdataPojo.exceltestdatamapper.GenericTest_excelDataSupplierTest_ETDM;
import com.jesusnut.testdataPojo.exceltestdatamapper.ProductInfoTest_validateProductDetails_ETDM;
import com.jesusnut.testdataPojo.exceltestdatamapper.SearchPageTest_verifySearchResultCountOnSearchingProduct_ETDM;
import com.jesusnut.testdataPojo.jsontestdatamapper.GenericTest_jsonDataSupplierTest_JTDM;

import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.JsonReader;
import io.github.sskorol.data.TestDataReader;
import io.github.sskorol.data.XlsxReader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import one.util.streamex.StreamEx;

/**
 * DataSupplierHelper class contains Data Suppliers (analogous to Data Providers
 * in TestNG) for test methods.<br>
 * 
 * <pre>Data Suppliers can fetch data sets from excel sheets, json files, csv files
 * and yaml files through {@link com.jesusnut.testdataPojo} classes and feed data sets
 * to test methods for data - driven testing i.e.
 * running the same test method with multiple sets of data.</pre>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see com.jesusnut.testdataPojo
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataSupplierHelper {

	private static final String EXCEL_TESTDATA_SHEET = "./exceltestdata/testData.xlsx";
	private static final String JSON_TESTDATA_SHEET = "./jsontestdata/jsonTestData.json";

	@DataSupplier(name = "Test Data Supplier for -> Method: excelDataSupplierTest, TestClass: com.jesusnut.tests.GenericTest", runInParallel = true)
	public StreamEx<GenericTest_excelDataSupplierTest_ETDM> getDataForGenericTest1(Method method) {

		return TestDataReader.use(XlsxReader.class).withTarget(GenericTest_excelDataSupplierTest_ETDM.class)
				.withSource(EXCEL_TESTDATA_SHEET).read()
				.filter(testdata -> testdata.getTestCasename().equalsIgnoreCase(method.getName()));

	}

	@DataSupplier(name = "Test Data Supplier for -> Method: verifySearchResultCountOnSearchingProduct, TestClass: com.jesusnut.tests.SearchPageTest", runInParallel = true)
	public StreamEx<SearchPageTest_verifySearchResultCountOnSearchingProduct_ETDM> getDataForSearchPageTest(
			Method method) {

		return TestDataReader.use(XlsxReader.class)
				.withTarget(SearchPageTest_verifySearchResultCountOnSearchingProduct_ETDM.class)
				.withSource(EXCEL_TESTDATA_SHEET).read()
				.filter(testdata -> testdata.getTestCasename().equalsIgnoreCase(method.getName()));

	}

	@DataSupplier(name = "Test Data Supplier for -> Method: validateProductDetails, TestClass: com.jesusnut.tests.ProductInfoTest", runInParallel = true)
	public StreamEx<ProductInfoTest_validateProductDetails_ETDM> getDataForProductInfoPageTest(Method method) {

		return TestDataReader.use(XlsxReader.class).withTarget(ProductInfoTest_validateProductDetails_ETDM.class)
				.withSource(EXCEL_TESTDATA_SHEET).read()
				.filter(testdata -> testdata.getTestCasename().equalsIgnoreCase(method.getName()));

	}

	@DataSupplier(name = "Test Data Supplier for -> Method: jsonDataSupplierTest , TestClass: TestClass: com.jesusnut.tests.GenericTest", runInParallel = true)
	public StreamEx<GenericTest_jsonDataSupplierTest_JTDM> getDataForGenericTest2(Method method) {

		return TestDataReader.use(JsonReader.class).withTarget(GenericTest_jsonDataSupplierTest_JTDM.class)
				.withSource(JSON_TESTDATA_SHEET).read()
				.filter(testdata -> testdata.getTestCasename().equalsIgnoreCase(method.getName()));

	}

}
