package com.crmproject.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport extends TestListenerAdapter{

	public ExtentReports extent;
	public ExtentTest test;
	public ExtentHtmlReporter htmlrepo;
	@Override
	public void onStart(ITestContext testContext) {
		String time=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
	 String timestamp="Test-Reporter"+time+".html";
	 htmlrepo=new ExtentHtmlReporter(System.getProperty("user.dir")+"/time-output/"+timestamp);
	 htmlrepo.loadXMLConfig(System.getProperty("user.dir")+"/exten-config.xml");
	  extent =new ExtentReports();
	  extent.attachReporter(htmlrepo);
	  extent.setSystemInfo("HostName","LocalHost");
	  extent.setSystemInfo("Environment", "QA");
	  extent.setSystemInfo("user", "Ramu");
	  htmlrepo.config().setDocumentTitle("CRMProject_Test");//Title of the report
	  htmlrepo.config().setReportName("FuntionalReport");//name of the test
	  htmlrepo.config().setTestViewChartLocation(ChartLocation.TOP);//inthat chrat view
	  htmlrepo.config().setTheme(Theme.DARK);//color of the chart
	  
	}
	@Override
	public void onTestSuccess(ITestResult tr) {
		test=extent.createTest(tr.getName());//new entry in report
		test.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		test=extent.createTest(tr.getName());//crate entry in report
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED))	;
		String screenShot = System.getProperty("user.dir")+"\\Screenshot\\"+tr.getName()+".png";
		File f=new File(screenShot);
		if(f.exists())
		{
			try {
				test.fail("ScreenShot is below"+test.addScreencastFromPath(screenShot));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		test=extent.createTest(tr.getName());//create on entry
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
		
	}

	

	@Override
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}


}
