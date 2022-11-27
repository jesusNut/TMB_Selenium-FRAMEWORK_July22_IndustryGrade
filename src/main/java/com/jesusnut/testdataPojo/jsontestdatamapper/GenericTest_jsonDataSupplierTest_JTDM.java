package com.jesusnut.testdataPojo.jsontestdatamapper;

import com.google.gson.annotations.SerializedName;

import io.github.sskorol.data.Source;
import lombok.Getter;

/**
 * POJO to map json sheet test data.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see <a href="https://github.com/sskorol/test-data-supplier">Data Supplier
 *      Library Github Link</a>
 */
@Getter
@Source(path = "./jsontestdata/jsonTestData.json")

public class GenericTest_jsonDataSupplierTest_JTDM {

	@SerializedName("testCaseName")
	private String testCasename;

	@SerializedName("email")
	private String email;

	@SerializedName("password")
	private String password;

}
