package com.atmecs.validate_ninja_store.testscripts;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atmecs.validate_ninja_store.constants.FileConstants;
import com.atmecs.validate_ninja_store.helper.JavaScriptHelper;
import com.atmecs.validate_ninja_store.helper.SeleniumHelper;
import com.atmecs.validate_ninja_store.helper.ValidaterHelper;
import com.atmecs.validate_ninja_store.helper.WaitForElement;
import com.atmecs.validate_ninja_store.helper.WebTableHelper;
import com.atmecs.validate_ninja_store.logreports.LogReporter;
import com.atmecs.validate_ninja_store.pages.HeatClinicHomePage;
import com.atmecs.validate_ninja_store.testbase.TestBase;
import com.atmecs.validate_ninja_store.utils.ExcelReader;
import com.atmecs.validate_ninja_store.utils.PropertiesReader;

public class TestVerifyHeatClinic extends TestBase{
	WaitForElement waitobject=new WaitForElement();
	LogReporter log=new LogReporter();
	ExcelReader excelread=new ExcelReader();
	ValidaterHelper validatehelp=new ValidaterHelper();
	SeleniumHelper seleniumhelp=new SeleniumHelper();
	PropertiesReader propread=new PropertiesReader();
	JavaScriptHelper javascript=new JavaScriptHelper();
	WebTableHelper webtable=new WebTableHelper();
	HeatClinicHomePage heatclinic=new HeatClinicHomePage();
	public TestVerifyHeatClinic() {
		excelread.filePathProviderMethod(FileConstants.HEATTESTDATA_PATH);
	}
	@BeforeClass
	public void webURLLoader() {
		driver.get(prop.getProperty("heatclinicURL"));
		waitobject.waitForPageLoadTime(driver);
		driver.manage().window().maximize();
		log.logReportMessage("STEP 2: URL is loaed"+ driver.getCurrentUrl());
	}
	@Test(dataProvider="testdata",dataProviderClass=ExcelReader.class)
	public void verifyHeatClinic(String[] heatclinicdata) throws IOException, InterruptedException {
		Properties prop1=propread.keyValueLoader(FileConstants.HEATCLINICLOCATORS_PATH);
		log.logReportMessage("STEP 3: Validate all the Menu");
		heatclinic.verifyHeatclinicMenu(driver,prop1);
		log.logReportMessage("STEP 4: Mouse Over Merchandise and click Men");
		seleniumhelp.mouseOver(driver,prop1.getProperty("loc.menu.merchandise"));
		WebElement menuelement=waitobject.WaitForFluent(driver, prop1.getProperty("loc.submenu.mens"));
		Actions action=new Actions(driver);
		action.moveToElement(menuelement).click().perform();
		log.logReportMessage("STEP 5: Verify the Viewing Mens");
		waitobject.waitForElementToBeClickable(driver,prop1.getProperty("loc.txt.viewing"));
		validatehelp.assertValidater(driver.getTitle().toString(), heatclinicdata[2]);
		String viewingmenstext=validatehelp.textOfElement(driver, prop1.getProperty("loc.txt.viewing"));
		log.logReportMessage("STEP 6: Click the Buy now of Hawt Like a Habanero Shirt (Men's)");
		seleniumhelp.clickElement(driver,prop1.getProperty("loc.btn.buynow"));
		heatclinic.handlePopUp(driver, prop1, heatclinicdata[0]);
		seleniumhelp.clickElement(driver, prop1.getProperty("loc.btn.popupbuynow"));
		log.logReportMessage("STEP 7: click the Cart");
		seleniumhelp.clickElement(driver,prop1.getProperty("loc.link.cart"));
		log.logReportMessage("STEP 8: Validation of the product");
		heatclinic.validateProduct(driver, prop1,heatclinicdata[4]);
		log.logReportMessage("STEP 9: Increase the Qty count");
		WebElement element=waitobject.WaitForFluent(driver, prop1.getProperty("loc.btn.qtyincrease"));
		element.clear();
		seleniumhelp.sendKeys(prop1.getProperty("loc.btn.qtyincrease"), driver, "3");
		log.logReportMessage("STEP 10: Update the  Cart");
		seleniumhelp.clickElement(driver, prop1.getProperty("loc.btn.update"));
		
		
	}
}
