package com.atmecs.validate_ninja_store.pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.atmecs.validate_ninja_store.helper.JavaScriptHelper;
import com.atmecs.validate_ninja_store.helper.SeleniumHelper;
import com.atmecs.validate_ninja_store.helper.ValidaterHelper;
import com.atmecs.validate_ninja_store.helper.WaitForElement;
import com.atmecs.validate_ninja_store.logreports.LogReporter;
import com.atmecs.validate_ninja_store.utils.ExcelReader;

public class NinjaStroreHomePage {
	LogReporter log=new LogReporter();
	WaitForElement waitobject=new WaitForElement();
	ExcelReader excelread=new ExcelReader();
	JavaScriptHelper javascript=new JavaScriptHelper();
	ValidaterHelper validatehelp=new ValidaterHelper();
	SeleniumHelper seleniumhelp=new SeleniumHelper();
	public void verifyNinjaStoreIPhone(WebDriver driver,Properties prop,String[]  ninjatestdata) throws InterruptedException {
		waitobject.waitForImplicit(driver);
		log.logReportMessage("STEP 5: Searching the Product");
		seleniumhelp.sendKeys(prop.getProperty("loc.txtfield.search"), driver,  ninjatestdata[2]);
		log.logReportMessage("Product name "+ninjatestdata[2]+" is entered");
		log.logReportMessage("STEP 6: Select the Product");
		seleniumhelp.scrollPageMethod(driver,prop.getProperty("loc.imglink.iphone") );
		seleniumhelp.clickElement(driver, prop.getProperty("loc.imglink.iphone"));
		log.logReportMessage("Product is selected");
		log.logReportMessage("STEP 7: Validate the the  Product Availability");
		String availability=validatehelp.textOfElement(driver, prop.getProperty("loc.txt.prodavailability"));
		String[] availabiltyarray=availability.split(":");
		validatehelp.assertValidater(availabiltyarray[1], ninjatestdata[3]);
		log.logReportMessage("STEP 8: Validate the the  Product Price");
		String price=validatehelp.textOfElement(driver, prop.getProperty("loc.txt.prodprice"));
		price=price.substring(1,price.length());
		validatehelp.assertValidater(price, ninjatestdata[4]);
		log.logReportMessage("STEP 9: Validate the the  Product Ex Tax");
		String extax=validatehelp.textOfElement(driver, prop.getProperty("loc.txt.exprice"));
		String[] extaxarray=extax.split(":");
		extaxarray[1]=extaxarray[1].substring(2,price.length());
		validatehelp.assertValidater(extaxarray[1], ninjatestdata[5]);
		log.logReportMessage("STEP 10: Validate the the  Product Description");
		String description=validatehelp.textOfElement(driver, prop.getProperty("loc.txt.proddiscription"));
		validatehelp.assertValidater(description, ninjatestdata[6]);
		log.logReportMessage("STEP 11: Change the Quantity");
		WebElement element=waitobject.WaitForFluent(driver, prop.getProperty("loc.txtfield.qty"));
		element.clear();
		seleniumhelp.sendKeys(prop.getProperty("loc.txtfield.qty"), driver,  ninjatestdata[7]);
		log.logReportMessage("STEP 12: Click Add to Cart Button");
		javascript.javascriptClickElement(driver,prop.getProperty("loc.btn.addtocart") );
	}
	public void verifyNinjaStoreMacBook(WebDriver driver,Properties prop,String[]  ninjatestdata) throws InterruptedException {
		waitobject.waitForImplicit(driver);
		log.logReportMessage("STEP 5: Searching the Product"+ ninjatestdata[8]);
		WebElement inputelement=waitobject.WaitForFluent(driver,prop.getProperty("loc.txtfield.search"));
		inputelement.clear();
		seleniumhelp.sendKeys(prop.getProperty("loc.txtfield.search"), driver,  ninjatestdata[8]);
		log.logReportMessage("Product name "+ninjatestdata[2]+" is entered");
		log.logReportMessage("STEP 6: Select the Product");
		seleniumhelp.scrollPageMethod(driver,prop.getProperty("loc.imglink.iphone") );
		seleniumhelp.clickElement(driver, prop.getProperty("loc.imglink.iphone"));
		log.logReportMessage("Product is selected");
		log.logReportMessage("STEP 7: Validate the the  Product Availability");
		String availability=validatehelp.textOfElement(driver, prop.getProperty("loc.txt.prodavailability1"));
		String[] availabiltyarray=availability.split(":");
		validatehelp.assertValidater(availabiltyarray[1], ninjatestdata[9]);
		log.logReportMessage("STEP 8: Validate the the  Product Price");
		String price=validatehelp.textOfElement(driver, prop.getProperty("loc.txt.prodprice"));
		price=price.substring(1,price.length());
		validatehelp.assertValidater(price, ninjatestdata[10]);
		log.logReportMessage("STEP 9: Validate the the  Product Ex Tax");
		String extax=validatehelp.textOfElement(driver, prop.getProperty("loc.txt.exprice"));
		String[] extaxarray=extax.split(":");
		extaxarray[1]=extaxarray[1].substring(2,price.length());
		validatehelp.assertValidater(extaxarray[1], ninjatestdata[11]);
		log.logReportMessage("STEP 10: Validate the the  Product Description");
		String description=validatehelp.textOfElement(driver, prop.getProperty("loc.txt.proddiscription"));
		validatehelp.assertValidater(description, ninjatestdata[12]);
		log.logReportMessage("STEP 11: Change the Quantity");
		WebElement element=waitobject.WaitForFluent(driver, prop.getProperty("loc.txtfield.qty"));
		element.clear();
		seleniumhelp.sendKeys(prop.getProperty("loc.txtfield.qty"), driver,  ninjatestdata[13]);
		log.logReportMessage("STEP 12: Click Add to Cart Button");
		seleniumhelp.clickElement(driver,prop.getProperty("loc.btn.addtocart"));
	}
}

