package com.jesusnut.tests;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.jesusnut.annotations.FrameworkAnnotation;
import com.jesusnut.base.BaseTest;
import com.jesusnut.config.ConfigFactory;
import com.jesusnut.enums.Category;
import com.jesusnut.pages.LoginPage;
import com.jesusnut.pages.MyAccountsPage;
import com.jesusnut.pages.ProductInfoPage;
import com.jesusnut.pages.SearchPage;
import com.jesusnut.testdataPojo.exceltestdatamapper.ProductInfoTest_validateProductDetails_ETDM;

public class ProductInfoTest extends BaseTest {

	@FrameworkAnnotation(category = { Category.REGRESSION })
	@Test(description = "To validate that specific products e.g. 'macbook pro' as part of search result for main product e.g 'macbook'(searched on MyAccountsPage) have correct details like price, brand etc.", dataProvider = "Test Data Supplier for -> Method: validateProductDetails, TestClass: com.jesusnut.tests.ProductInfoTest", dataProviderClass = com.jesusnut.helper.DataSupplierHelper.class, groups = {
			"Regression" })
	public void validateProductDetails(ProductInfoTest_validateProductDetails_ETDM testData) {

		LoginPage loginpage = new LoginPage();
		MyAccountsPage myAccountsPage = loginpage.loginToApplication(ConfigFactory.getUserName(),
				ConfigFactory.getPassword());
		SearchPage searchPage = myAccountsPage.searchProduct(testData.getMainProduct());
		ProductInfoPage productInfoPage = searchPage
				.selectSpecificProductFromSearchResult(testData.getSpecificProductName());
		Map<String, String> actualProductDetails = productInfoPage.getProductDetails();

		Assertions.assertThat(actualProductDetails.get("Brand")).isNotBlank().isNotEmpty().isNotNull()
				.isEqualToIgnoringCase(testData.getBrand());
		Assertions.assertThat(actualProductDetails.get("Product Code")).isNotBlank().isNotEmpty().isNotNull()
				.isEqualToIgnoringCase(testData.productCode);
		Assertions.assertThat(actualProductDetails.get("Reward Points")).isNotBlank().isNotEmpty().isNotNull()
				.isEqualToIgnoringCase(testData.getRewardsPoint());
		Assertions.assertThat(actualProductDetails.get("Availability")).isNotBlank().isNotEmpty().isNotNull()
				.isEqualToIgnoringCase(testData.getAvailability());
		Assertions.assertThat(actualProductDetails.get("Price")).isNotBlank().isNotEmpty().isNotNull()
				.isEqualToIgnoringCase(testData.getPrice());
		Assertions.assertThat(actualProductDetails.get("Ex Tax")).isNotBlank().isNotEmpty().isNotNull()
				.isEqualToIgnoringCase(testData.getExtax());

	}

}
