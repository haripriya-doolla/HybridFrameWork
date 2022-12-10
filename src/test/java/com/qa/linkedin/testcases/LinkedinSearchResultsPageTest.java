package com.qa.linkedin.testcases;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinFeedPage;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoginPage;
import com.qa.linkedin.pages.LinkedinSearchResultsPage;

import org.testng.annotations.BeforeClass;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LinkedinSearchResultsPageTest extends TestBase {
	private Logger log=LogManager.getLogger(LinkedinSearchResultsPageTest.class);
	LinkedinHomePage lhomePg;
	LinkedinLoginPage loginPg;
	LinkedinFeedPage lfeedPg;
	LinkedinSearchResultsPage lSrPgPg;
	String fPath=System.getProperty(null);
  @BeforeClass
  public void beforeClass() throws InterruptedException, IOException {
	  lhomePg=new LinkedinHomePage();
	  loginPg=new LinkedinLoginPage();
	  lfeedPg=new LinkedinFeedPage();
	  log.debug("validating the sign in link");
	  loginPg=lhomePg.clickOnSignInLink();
	  log.debug("performing login action to the linkedin");
	  lfeedPg=loginPg.doLogin(readPropertyValue("username"), readPropertyValue("pwd"));
	  Thread.sleep(3000);
  }
  
  @Test(priority=1)
  public void verifyLinkedinFeedPageTitle() {
	  log.debug("verifying the Linkedin Feed page title");
	  Assert.assertTrue(lfeedPg.getLinkedinFeedPageTitle().contains("Feed | LinkedIn"),"LinkedIn");
  }
  @Test(priority=2)
  public void verifyLinkedinLogo() {
	  log.debug("verifying the Linkedin logo element presence in the page");
	  Assert.assertEquals(lhomePg.isLinkedinLogoPresent(), "LinkedIn logo element is not present in home or not");
  }
  @Test(priority=3)
  public void clickSigninLinkedinLinkTest() throws InterruptedException {
	  log.debug("validating the sign in link");
	  loginPg=lhomePg.clickOnSignInLink();
  }
  
  
  @AfterClass
  public void afterClass() {
	  log.debug("come back to home page");
	  driver.navigate().back();
  }

}
