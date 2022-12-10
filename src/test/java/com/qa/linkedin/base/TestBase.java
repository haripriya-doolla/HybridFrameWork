package com.qa.linkedin.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TestBase {
	public static WebDriver driver;
	public WebDriverWait wait;
	private static final Logger log=LogManager.getLogger(TestBase.class);
	
	
	public Properties prop=null;
	
	public String readPropertyValue(String key) throws IOException {
		log.info("create object for properties");
		prop=new Properties();
		log.debug("read the properties file");
		try {
			// src\test\java\com\qa\linkedin\config
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties");
		//	FileInputStream fis=new FileInputStream("C:\\eclipse-workspace\\HybridFramework\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties");
			log.info("load all the properties");
			prop.load(fis);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
		
	}
	@BeforeSuite
	public void setup() throws IOException {
		log.debug("launching the browser and application");
		String browserName=readPropertyValue("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			opt.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(opt);
			log.info("chromebrowser is launched");
		} else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions opt = new FirefoxOptions();
			opt.setAcceptInsecureCerts(true);
			// opt.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			// interface refvar=new implementedclass();
			driver = new FirefoxDriver(opt);
			Reporter.log("firefox browser is launched", true);
		} else if (browserName.equalsIgnoreCase("edge")) {
			EdgeOptions opt = new EdgeOptions();
			opt.setAcceptInsecureCerts(true);
			driver = new EdgeDriver(opt);
			log.info("edge browser is launched");
		}
		log.info("edge browser is launched");
		log.debug("maximize the window");
		driver.manage().window().maximize();
         log.info("add implicitwait");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		log.info("add explicitwait object", true);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		log.info("launch the application url:"+readPropertyValue("appUrl"));
		driver.get(readPropertyValue("appUrl"));
	}

	@AfterSuite
	public void tearDown() {
		log.info("close the browser");
		if(driver !=null) {
			driver.close();
		}
	
	}

}
