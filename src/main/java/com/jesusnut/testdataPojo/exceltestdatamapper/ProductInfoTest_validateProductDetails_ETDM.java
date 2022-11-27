package com.jesusnut.testdataPojo.exceltestdatamapper;

import com.creditdatamw.zerocell.annotation.Column;

import io.github.sskorol.data.Sheet;
import io.github.sskorol.data.Source;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO to map excel sheet test data.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see com.jesusnut.helper.DataSupplierHelper
 * @see <a href="https://github.com/sskorol/test-data-supplier">Data Supplier
 *      Library Github Link</a>
 */

@Data
@NoArgsConstructor
@Source(path = "./exceltestdata/testData.xlsx")
@Sheet(name = "Sheet3")

public final class ProductInfoTest_validateProductDetails_ETDM {

	@Column(name = "testcasename", index = 0)
	private String testCasename;

	@Column(name = "mainproduct", index = 1)
	private String mainProduct;

	@Column(name = "specificproductname", index = 2)
	private String specificProductName;

	@Column(name = "brand", index = 3)
	private String brand;

	@Column(name = "productcode", index = 4)
	public String productCode;

	@Column(name = "rewardspoint", index = 5)
	private String rewardsPoint;

	@Column(name = "availability", index = 6)
	private String availability;

	@Column(name = "price", index = 7)
	private String price;

	@Column(name = "extax", index = 8)
	private String extax;

}
