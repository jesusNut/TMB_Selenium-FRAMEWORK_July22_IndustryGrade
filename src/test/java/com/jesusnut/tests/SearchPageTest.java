package com.jesusnut.tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.jesusnut.annotations.FrameworkAnnotation;
import com.jesusnut.base.BaseTest;
import com.jesusnut.config.ConfigFactory;
import com.jesusnut.enums.Category;
import com.jesusnut.pages.LoginPage;
import com.jesusnut.pages.MyAccountsPage;
import com.jesusnut.pages.SearchPage;
import com.jesusnut.testdataPojo.exceltestdatamapper.SearchPageTest_verifySearchResultCountOnSearchingProduct_ETDM;

public class SearchPageTest extends BaseTest {

	@FrameworkAnnotation(category = { Category.REGRESSION })
	@Test(description = "To check that correct number of search results are displayed on Serach Page after searching a product on MyAccountsPage", groups = {
			"Regression" }, dataProvider = "Test Data Supplier for -> Method: verifySearchResultCountOnSearchingProduct, TestClass: com.jesusnut.tests.SearchPageTest", dataProviderClass = com.jesusnut.helper.DataSupplierHelper.class)

	public void verifySearchResultCountOnSearchingProduct(
			SearchPageTest_verifySearchResultCountOnSearchingProduct_ETDM testdata) {

		LoginPage loginpage = new LoginPage();

		MyAccountsPage myAccountspage = loginpage.loginToApplication(ConfigFactory.getUserName(),
				ConfigFactory.getPassword());

		SearchPage serachPage = myAccountspage.searchProduct(testdata.getProductName());

		int actualSearchResultCount = serachPage.totalNumberOfProductsOnSerach();

		Assertions.assertThat(actualSearchResultCount).isEqualTo(testdata.getExpectedSearchresultCount());

	}

}
