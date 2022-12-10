package com.qa.linkedin.pages;


	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	import com.qa.linkedin.utill.BasePageWebActions;
	
	
	public class LinkedinSearchResultsPage extends BasePageWebActions {

		private Logger log=LogManager.getLogger(LinkedinSearchResultsPage.class);
		//create a constructor
		public LinkedinSearchResultsPage() {
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath="//a[@class='app-aware-link'][contains(.,'See all people results')]")
		private WebElement seeAllPeopleResultsLink;
		
		@FindBy(xpath="//h2[contains(@class,'pb2 t-black--light t-14')]")
		private WebElement searchResultsText;

		
		@FindBy(xpath="//ul[contains(@class,'global-nav__primary-items')]/li[1]/a")
		private WebElement homeTab;
		
		/**
		 * fetch the current page title
		 */
		public String getLinkedinFeedPageTitle() {
			log.info("fetch the Linkedin search results page title");
			return driver.getTitle();
		}
		/**
		 * click on see All people results link
		 * @throws InterruptedException 
		 */
		public void clickOnSeeAllPeopleResultsLink() throws InterruptedException {
			log.info("click on see All People Results Link");
			if(isElementPresent(By.xpath("//div[contains(@class,'search-results')]/a[1]"))) 
			click(seeAllPeopleResultsLink);
		}
		/**
		 * click on Home tab
		 * @throws InterruptedException 
		 */
		public void clickOnHomeTab() throws InterruptedException {
			log.info("click on Home tab");
			click(homeTab);
		}
		/**
		 * fetch the search results text from results page
		 * @throws InterruptedException 
		 * @thows InterruptedException
		 */
		public String getResultsText() throws InterruptedException {
			log.info("get results count text from linkedin search results page");
			return getText(searchResultsText);
		}
		/**
		 * extract the people search results count
		 * @throws InterruptedException 
		 * @throws InterruptException
		 */
		public long getPeopleSearchResultsCount() throws InterruptedException {
			
			String txt=getResultsText();
			//split the words using space delimiter
			String[] str=txt.split("\\s");
			String countStr=null;
			if(str.length>2) {
				countStr=str[1];
				
			}else if(str.length==2) {
				countStr=str[0];
			}
			//convert the String into long format
			long count=Long.parseLong(countStr);
			return count;
		}

}
