package com.qa.linkedin.utill;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.log4testng.Logger;

import com.qa.linkedin.base.TestBase;

public class TestUtilies extends TestBase{
private static Logger log = Logger.getLogger(TestUtilies .class);
public static String captureScreenshot(String methodName) throws IOException {
		
		String fileName=getScreenshotName(methodName);
	    String directory="target/surefire-reports/failedTestScreenshots/";
		//String directory=System.getProperty("user.dir")+"/target/surefire-reports/failedTestScreenshots/";
		new File(directory).mkdirs();
		String path=directory + fileName;
		try {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,	new File(path));
		log.debug("********************************************************************************");
		log.debug("Screenshot stored at path: "+path);
		log.debug("********************************************************************************");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	public static String getScreenshotName(String methodName) {
		Date d = new Date();
		String fileName = methodName+"-"+ d.toString().replace(":", "_").replace(" ", "_") + ".png";
		return fileName;
	}

}


