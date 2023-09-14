package StepDefinitions;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.selenium.cucumber.pageobject.FilterAndVerify;
import com.selenium.cucumber.pageobject.RemoveCart;
import com.selenium.cucumber.utilities.MyUtility;
import com.selenium.cucumber.utilities.NewDriver;
import com.selenium.cucumber.pageobject.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AllTest {
	//variables
		//WebDriver object
		WebDriver driver;
		
		//POM Class Objects
		SearchProduct reg;
		AddToCart log;
		Login cp;
		FilterAndVerify filter;
		RemoveCart remove;
		
		//Utility class objects
		MyUtility utility;
		//HashMap for saving data
		HashMap<String,String> map;
		
	
	//1st
	
	
	@Given("Launch Browser")
	public void launch_browser() throws Exception {
		System.out.println("Register Test...");
		  this.utility=new MyUtility();
		  this.map= new HashMap<String, String>();
		  //getting driver on the basis of properties file
		  this.driver=NewDriver.getDriver();
			this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			  this.reg=new SearchProduct(driver);
			  this.log=new AddToCart(driver);
			  this.cp=new Login(driver);
			  this.filter=new FilterAndVerify(driver);
			  this.remove=new RemoveCart(driver);
			//Navigating to URL
			  driver.get("https://www.flipkart.com/");
			  driver.manage().window().maximize();
	}

	@And("navigate to the Flipkart URL")
	public void navigate_to_the_flipkart_url() throws Exception {
		this.driver.navigate().to("https://www.flipkart.com/");
		try {
			  this.cp.clickCross();
		  }catch (Exception e) {
			  System.out.println("PopUp not found");
//			  test.log(Status.PASS, "popup not found");		  
		  }
	}

	@When("click on the Login button")
	public void click_on_the_login_button() throws InterruptedException {
		Thread.sleep(2000);
		this.cp.clickLoginTab();
	}

	@Then("verify login page as {string}")
	public void verify_login_page_as(String d1) {
		assertEquals(this.driver.getCurrentUrl(),d1);
	}

	@And("enter valid phone number as {string} and verify result should be {string}")
	public void enter_valid_phone_number_as_and_verify_result_should_be(String d2, String exp) throws InterruptedException {
		this.cp.sendPhoneNumber(d2);
		  this.cp.clickRequestOtp();
	
		  Thread.sleep(Duration.ofSeconds(15));
		 
		  if(this.driver.getPageSource().contains("Please enter the OTP sent to")){
			  assertEquals(this.driver.getPageSource().contains("Please enter the OTP sent to"),Boolean.parseBoolean(exp));
		  }else {
			  assertEquals(this.driver.getPageSource().contains("Press"),Boolean.parseBoolean(exp));
		  }
		  Thread.sleep(1000);
		
	}

	@And("click the Login button")
	public void click_the_login_button() {

	}

	@Then("RequestOTP should be displayed")
	public void request_otp_should_be_displayed() {

		driver.manage().deleteAllCookies();
	}
	
	//2nd
	
	@Then("search for the product as {string}")
	public void search_for_the_product1(String data) {

		this.reg.sendDataToSearch(data);
	}

	@And("click on the Submit Search button")
	public void click_on_the_submit_search_button() {
		this.reg.submitSearch();

	}

	@Then("click on the iPhone")
	public void click_on_the_i_phone() throws Exception {
		this.reg.clickIphone14();
		  Thread.sleep(5000);
	}

	@And("verify the product name as {string}, price as {string}, title as {string}, and result should be {string}")
	public void verify_the_product_name_as_price_as_title_as_and_result_should_be(String d1, String d2, String d3, String exp) throws InterruptedException {
		//Handling Window
		  List<String> l1=new ArrayList<String>(driver.getWindowHandles());
		  if(l1.size()>1) {
			  driver.switchTo().window(l1.get(1));
		  }

		  assertEquals(this.driver.getPageSource().contains(d1),Boolean.parseBoolean(exp));

		  assertEquals(this.reg.getPrice().contains(d2),Boolean.parseBoolean(exp));

		 // assertEquals(this.driver.getTitle(),d3);
		  map.put(reg.getNameIphone14(), reg.getPrice());
		  System.out.println(map.get(reg.getNameIphone14()));
		  
		  Thread.sleep(1000);
		  driver.manage().deleteAllCookies();
	}

	//3rd
	
	@Then("search for the product {string}")
	public void search_for_the_product(String string) {

	}

	@And("click the Search button")
	public void click_the_search_button() {
		this.log.sendDataToSearch("mobile");
		this.log.submitSearch();

	}

	@Then("Add to Cart")
	public void add_to_cart() throws InterruptedException {
		this.log.clickmobile();

		  //Handling Window
		  List<String> l1=new ArrayList<String>(driver.getWindowHandles());
		  if(l1.size()>1) {
			  driver.switchTo().window(l1.get(1));
		  }
		  this.log.selectAddToCart();

		  Thread.sleep(3000);

	}

	@And("Product Should be added on URL as {string}, have as {string}, and expected as {string}")
	public void product_should_be_added_on_url_as_have_as_and_expected_as(String d1, String d2, String exp) throws InterruptedException {
		assertEquals(this.driver.getCurrentUrl(),d1);
		  Thread.sleep(3000);

		  assertEquals(this.log.getPrice(),d2);
		  
		  Thread.sleep(5000);
		  driver.manage().deleteAllCookies();
	}

	//4th
	
	@Then("navigate to mobile section")
	public void navigate_to_mobile_section() {

		this.filter.clickMobile();

	}
	
	@And("filter for Apple products")
	public void filter_for_apple_products() throws InterruptedException {
		this.filter.filterApple();
		  this.filter.filterNewFirst();

		  Thread.sleep(2000);
	}

	@Then("click on the first product")
	public void click_on_the_first_product() throws InterruptedException {
		this.filter.clickIphone14();
		Thread.sleep(5000);
	}

	@And("verify product name containing {string} and Title as {string}, and verified result should be {string}")
	public void verify_product_name_containing_and_title_as_and_verified_result_should_be(String d1, String d3, String exp) throws InterruptedException {
		//Handling Window
		  List<String> l1=new ArrayList<String>(driver.getWindowHandles());
		  if(l1.size()>1) {
			  driver.switchTo().window(l1.get(1));
		  }

		  assertEquals(this.driver.getPageSource().contains(d1),Boolean.parseBoolean(exp));


		  assertEquals(this.driver.getTitle().contains(d3),Boolean.parseBoolean(exp));
		  
		  Thread.sleep(1000);
		  driver.manage().deleteAllCookies();
	}

	//5th
	
	@Then("search the Product")
	public void search_the_product() throws InterruptedException {

		Thread.sleep(3000);
		this.remove.sendDataToSearch("mobile");
		this.remove.submitSearch();
	}

	@And("add the product to my cart")
	public void add_the_product_to_my_cart() throws InterruptedException {
		this.remove.clickmobile();
		//Handling Window
		  List<String> l1=new ArrayList<String>(driver.getWindowHandles());
		  if(l1.size()>1) {
			  driver.switchTo().window(l1.get(1));
		  }
		  
		  this.remove.selectAddToCart();
	}

	@Then("remove the product from my cart")
	public void remove_the_product_from_my_cart() throws InterruptedException {
		map.put(remove.getPname(), remove.getPprice());
		  if (map.containsKey(remove.getPname())) {
			   Object value = map.get(remove.getPname());
			 System.out.println("Pname : " + remove.getPname() +" price :"+ value);
			 }
		  

		  
		  JavascriptExecutor js=(JavascriptExecutor)driver;
		  js.executeScript("window.scrollBy(0,250)");
		  this.remove.clickremove();
	}

	@And("verify message as {string} and cart as {string} and result as {string}")
	public void verify_message_as_and_cart_as_and_result_as(String d1, String d2, String exp) throws InterruptedException {
		Thread.sleep(3000);
		  //this.remove.clickremovepopup();

		  assertEquals(this.remove.verifyCart(),d1);

		  assertEquals(this.remove.getloginverify(),d2);
		  Thread.sleep(1000);
		  driver.manage().deleteAllCookies();
	}

	@Then("close the Browser")
	public void close_the_browser() {
	    this.driver.quit();
	}

	
	
}
