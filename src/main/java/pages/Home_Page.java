package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home_Page extends PageBase {

	public Home_Page(WebDriver driver) {
		super(driver);
	}

	@FindBy(linkText = "Register")
	WebElement RegisterLink;
	
	@FindBy(linkText = "Log in")
	WebElement LoginLink;
	
	@FindBy(linkText = "Log out")
	WebElement LogoutLink;
	
	public void OpenRegistrationPage() {
	
		//clickButton(RegisterLink);
		
	}
	
	public void OpenLoginPage() {
		
		//clickButton(LoginLink);
		
	}
	
	public void UserLogout() {
		//clickButton(LogoutLink);
	}
	
}
