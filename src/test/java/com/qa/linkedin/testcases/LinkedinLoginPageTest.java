package com.qa.linkedin.testcases;


import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinFeedPage;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoginPage;

import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LinkedinLoginPageTest extends TestBase {
	private Logger log=LogManager.getLogger(LinkedinHomePageTest.class);
	LinkedinHomePage lhomePg;
	LinkedinLoginPage loginPg;
	LinkedinFeedPage lfeedPg;
  @BeforeClass
  public void beforeClass() throws InterruptedException {
	  lhomePg=new LinkedinHomePage();
	  loginPg=new LinkedinLoginPage();
	  lfeedPg=new LinkedinFeedPage();
	  log.debug("validating the sign in link");
	  loginPg=lhomePg.clickOnSignInLink();
  }
  
  @Test(priority=1)
  public void verifyLinkedinHomePageTitle() {
	  log.debug("verifying the Linkedin Login page");
	  Assert.assertEquals(loginPg.getLinkedinLoginPageTitle(), "LinkedIn Log In, Sign in| LinkedIn");
  }
  @Test(priority=2)
  public void verifyLinkedinLogo() {
	  log.debug("verify Linkedin signin header text  element presence in the page");
	  Assert.assertEquals(loginPg.isSigninHeaderTextPresent(),"LinkedIn sign in header text element");
  }
  @Test(priority=3)
  public void doLoginTest() throws InterruptedException, IOException {
	  log.debug("performing login action to the linkedin");
	 lfeedPg=loginPg.doLogin(readPropertyValue("username"),readPropertyValue("pwd"));
  }
  @Test(dependsOnMethods= {"doLoginTest"})
  public void doLogOutTest() throws InterruptedException {
	  log.debug("log out from linkedin page");
	  lfeedPg.doLogout();
  }

}
