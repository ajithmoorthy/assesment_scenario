package com.atmecs.validate_ninja_store.testscripts;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atmecs.validate_ninja_store.constants.FileConstants;
import com.atmecs.validate_ninja_store.helper.JavaScriptHelper;
import com.atmecs.validate_ninja_store.helper.SeleniumHelper;
import com.atmecs.validate_ninja_store.helper.ValidaterHelper;
import com.atmecs.validate_ninja_store.helper.WaitForElement;
import com.atmecs.validate_ninja_store.helper.WebTableHelper;
import com.atmecs.validate_ninja_store.logreports.LogReporter;
import com.atmecs.validate_ninja_store.pages.NinjaStroreHomePage;
import com.atmecs.validate_ninja_store.testbase.TestBase;
import com.atmecs.validate_ninja_store.utils.ExcelReader;
import com.atmecs.validate_ninja_store.utils.PropertiesReader;

public class TestVerifyNinjaStore extends TestBase {
	WaitForElement waitobject=new WaitForElement();
	LogReporter log=new LogReporter();
	ExcelReader excelread=new ExcelReader();
	ValidaterHelper validatehelp=new ValidaterHelper();
	SeleniumHelper seleniumhelp=new SeleniumHelper();
	PropertiesReader propread=new PropertiesReader();
	NinjaStroreHomePage ninjapage=new NinjaStroreHomePage();
	JavaScriptHelper javascript=new JavaScriptHelper();
	WebTableHelper webtable=new WebTableHelper();
	public TestVerifyNinjaStore() {
		excelread.filePathProviderMethod(FileConstants.NINJATESTDATA_PATH);
	}
	@BeforeClass
	public void webURLLoader() {
		driver.get(prop.getProperty("ninjastoreURL"));
		waitobject.waitForPageLoadTime(driver);
		driver.manage().window().maximize();
		log.logReportMessage("STEP 2: URL is loaed"+ driver.getCurrentUrl());
	}
	
	@Test(dataProvider="testdata",dataProviderClass=ExcelReader.class)
	public void verifyNinjaStore(String[] ninjatestdata) throws IOException, InterruptedException
	{
		Properties prop1=propread.keyValueLoader(FileConstants.NINJALOCATORS_PATH);
		log.logReportMessage("STEP 3: Validating the Home Page Title");
				validatehelp.titleValidater(driver,ninjatestdata[1]);
				
		log.logReportMessage("STEP 4: Changing the currency to Dollar");
				ninjapage.verifyNinjaStoreIPhone(driver,prop1, ninjatestdata);
				ninjapage.verifyNinjaStoreMacBook(driver,prop1, ninjatestdata);
				
		log.logReportMessage("STEP 12: Click the Cart   ");
				seleniumhelp.clickElement(driver,prop1.getProperty("loc.btn.cart"));
				log.logReportMessage("Successfully navigate to the cart");
				
		log.logReportMessage("STEP 13: Validate the cart Product");
				String iphonetext=validatehelp.textOfElement(driver, prop1.getProperty("loc.txt.iphone"));
				validatehelp.assertValidater(iphonetext, ninjatestdata[14]);
				String macbooktext=validatehelp.textOfElement(driver, prop1.getProperty("loc.txt.imacbook"));
				validatehelp.assertValidater(macbooktext, ninjatestdata[15]);
				
		log.logReportMessage("STEP 14: Validate the Grand Total Before");
				String grandtotal=validatehelp.textOfElement(driver, prop1.getProperty("loc.txt.grandtotal"));
				validatehelp.assertValidater(grandtotal.substring(1, grandtotal.length()),ninjatestdata[16]);
				
		log.logReportMessage("STEP 15: Remove the Product From the Cart");
				seleniumhelp.clickElement(driver, prop1.getProperty("loc.btn.remove"));
				log.logReportMessage("Product is Removed");
				
		log.logReportMessage("STEP 16: Validate the Grand Total After");
				waitobject.waitForInvisibilityOfElementLocated(driver, prop1.getProperty("loc.spinner.visibility"));
				String grandtotalafter=validatehelp.textOfElement(driver, prop1.getProperty("loc.txt.grandtotal"));
				validatehelp.assertValidater(grandtotalafter.substring(1, grandtotalafter.length()),ninjatestdata[17]);
	}
}
