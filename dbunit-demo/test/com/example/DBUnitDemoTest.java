package com.example;

import java.io.File;
import java.io.FileInputStream;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

public class DBUnitDemoTest extends DBTestCase {
	
	public DBUnitDemoTest(String name){
		super(name);
		System.setProperty(
			PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
			"com.mysql.jdbc.Driver");
		System.setProperty(
			PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
			"jdbc:mysql://localhost:3306/userdb");
		System.setProperty(
			PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
			"root");
		System.setProperty(
			PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
			"");
		//System.setProperty(
		//	PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,
		//	"com.mysql.jdbc.Driver");
	}

	@Test
	public void test() throws Exception {
		assertEquals(2, getDataSet().getTable("user").getRowCount());
		ITable dataset = getDataSet().getTable("user");
		Assertion.assertEquals(dataset, dataset);
	}
	
	@Test
	public void testdb() throws Exception {
		//Fetch database data after executing your code
		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("user");
		
		//Load expected data from an XML dataset
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
			.build(new File("resources/data-snapshot-1.xml"));
		ITable expectedTable = expectedDataSet.getTable("user");
		
		//Insert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	@Test
	public void testQueryType() throws Exception{
		// Objective: match the second field of one of the rows
		// select password
		// omit primary key column for query to query snapshot comparison
		ITable queriedTable = getConnection()
				.createQueryTable("something", "select password from user where username='admin'");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
			.build(new File("resources/query-table-result.xml"));
		ITable expectedTable = expectedDataSet.getTable("user");
		
		Assertion.assertEquals(expectedTable, queriedTable);
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder()
			.build(new FileInputStream("resources/data-snapshot-1.xml"));
	}
	
	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception{
		return DatabaseOperation.CLEAN_INSERT;
	}
	
	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception{
		return DatabaseOperation.NONE;
	}

}
