package com.atmecs.validate_ninja_store.pages;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.atmecs.validate_ninja_store.constants.FileConstants;
import com.atmecs.validate_ninja_store.helper.JavaScriptHelper;
import com.atmecs.validate_ninja_store.helper.SeleniumHelper;
import com.atmecs.validate_ninja_store.helper.ValidaterHelper;
import com.atmecs.validate_ninja_store.helper.WaitForElement;
import com.atmecs.validate_ninja_store.logreports.LogReporter;
import com.atmecs.validate_ninja_store.utils.ExcelReader;

public class HeatClinicHomePage {
	LogReporter log = new LogReporter();
	WaitForElement waitobject = new WaitForElement();
	ExcelReader excelread = new ExcelReader();
	JavaScriptHelper javascript = new JavaScriptHelper();
	ValidaterHelper validatehelp = new ValidaterHelper();
	SeleniumHelper seleniumhelp = new SeleniumHelper();

	public void verifyHeatclinicMenu(WebDriver driver, Properties prop) throws IOException {
		String[] heatclinicdata = excelread.excelDataProviderArray(FileConstants.HEATTESTDATA_PATH, 1, "Titles");
		String[] locatordata = excelread.excelDataProviderArray(FileConstants.HEATTESTDATA_PATH, 1, "Locators");
		System.out.println(locatordata[1]);
		for (int count = 0; count < heatclinicdata.length; count++) {
			seleniumhelp.clickElement(driver, prop.getProperty(locatordata[count]));
			validatehelp.titleValidater(driver,heatclinicdata[count]);
		}
	}
	public void handlePopUp(WebDriver driver, Properties prop, String heatclinicdata) {
		seleniumhelp.sendKeys(prop.getProperty("loc.txtfield.name"), driver, heatclinicdata);
		seleniumhelp.clickElement(driver, prop.getProperty("loc.btn.color"));
		seleniumhelp.clickElement(driver, prop.getProperty("loc.btn.size"));
	}

	public void validateProduct(WebDriver driver,Properties prop,String expectedproductname) throws IOException {
		String[] productexpecteddata=excelread.excelDataProviderArray(FileConstants.HEATTESTDATA_PATH, 1,"ProdDetails"); 
		String productname=validatehelp.textOfElement(driver, prop.getProperty("loc.txt.name"));
		validatehelp.assertValidater(productname, expectedproductname);
		for(int count=1; count<=3; count++) {
			String productdetails=validatehelp.textOfElement(driver, prop.getProperty("loc.txtx.details").replace("xxx", ""+count));
			String[] productdetailsarray=productdetails.split(":");
			validatehelp.assertValidater(productdetailsarray[1],productexpecteddata[count]);
		}
	}
}
