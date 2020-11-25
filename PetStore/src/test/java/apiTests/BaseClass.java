package apiTests;

import java.io.File;
import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.internal.annotations.AnnotationHelper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utility.DataProvider;

public class BaseClass {
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeClass
	public void setUp() {
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/ExtentReport"+DataProvider.getCurrentTime()+".html"));
		report = new ExtentReports();
		report.attachReporter(extent);
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		logger = report.createTest(method.getName());		
	}
	@AfterMethod
	public void teardown() {
		report.flush();
	}
}
