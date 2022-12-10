package com.qa.linkedin.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoginPage;



public class LinkedinHomePageTest extends TestBase {
	private Logger log=LogManager.getLogger(LinkedinHomePageTest.class);
	LinkedinHomePage lhomePg;
	LinkedinLoginPage loginPg;
  @BeforeClass
  public void beforeClass() {
	  lhomePg=new LinkedinHomePage();
	  loginPg=new LinkedinLoginPage();
  }
  
  @Test(priority=1)
  public void verifyLinkedinHomePageTitleTest() {
	  log.debug("verifying the Linkedin home page");
	  Assert.assertEquals(lhomePg.getLinkedinHomePageTitle(), "LinkedIn: Log In or Sign Up");
  }
  @Test(priority=2)
  public void verifyLinkedinLogoTest()  {
	  log.debug("verifying the Linkedin logo element presence in the page");
	// Assert.assertEquals(lhomePg.isLinkedinLogoPresent(), "linkedinLogo is not present ");
	  Assert.assertTrue(lhomePg.isLinkedinLogoPresent(), "linkedinLogo is not present ");
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
