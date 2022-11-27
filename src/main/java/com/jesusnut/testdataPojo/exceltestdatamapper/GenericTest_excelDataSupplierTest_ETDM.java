package com.jesusnut.testdataPojo.exceltestdatamapper;

import com.creditdatamw.zerocell.annotation.Column;
import com.creditdatamw.zerocell.converter.DoubleConverter;
import com.creditdatamw.zerocell.converter.IntegerConverter;

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
 * @see com.jesusnut.helper.DataSupplierHelper
 * @see <a href="https://github.com/sskorol/test-data-supplier">Data Supplier
 *      Library Github Link</a>
 * 
 */

@Data
@NoArgsConstructor
@Source(path = "./exceltestdata/testData.xlsx")
@Sheet(name = "Sheet1")

public final class GenericTest_excelDataSupplierTest_ETDM {

	@Column(name = "testcasename", index = 0)
	private String testCasename;

	@Column(name = "name", index = 1)
	private String name;

	@Column(name = "fathersname", index = 2)
	private String fathersName;

	@Column(name = "age", index = 3, converterClass = IntegerConverter.class)
	private int age;

	@Column(name = "salary", index = 4, converterClass = DoubleConverter.class)
	private double salary;

}
