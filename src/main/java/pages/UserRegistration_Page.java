package pages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserRegistration_Page extends PageBase {

	public UserRegistration_Page(WebDriver driver) {
		super(driver);
		//driver.
	
	
	
	}
	
	
	@FindBy(id = "gender-male")
	WebElement Gender_Male;
	
	
	@FindBy(id = "FirstName")
	WebElement FirstName;
	
	
	@FindBy(id = "LastName")
	WebElement LastName;

	
	@FindBy(id = "Email")
	WebElement Email;
	
	
	@FindBy(id = "Password")
	WebElement Password;
	
	@FindBy(id = "ConfirmPassword")
	WebElement ConfirmPassword;
	
	@FindBy(id = "register-button")
	WebElement register_button;
	
	@FindBy(className = "result")
	WebElement result;
	
	
	public void RegistrationFill(String FirstNameTxt,String LastNameTxt,String EmailTxt,String PasswordTxt,String ConfirmPasswordTxt) throws InterruptedException {

		
		
		//clickButton(Gender_Male);
		
	
//		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//				.withTimeout(10,TimeUnit.SECONDS)
//				.pollingEvery(2, TimeUnit.SECONDS).
//				ignoring(NoSuchElementException.class);
//		SetTextElement(FirstName, FirstNameTxt);
//		SetTextElement(LastName, LastNameTxt);
//		SetTextElement(Email, EmailTxt);
//		SetTextElement(Password, PasswordTxt);
//		SetTextElement(ConfirmPassword, ConfirmPasswordTxt);
//
//		clickButton(register_button);
		
		
	
	}
	public void ReturnResult(String resultTxt)
	{
		
		//AssertElement(result, resultTxt);
	}
}
