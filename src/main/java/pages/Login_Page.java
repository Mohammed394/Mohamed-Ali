package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_Page extends PageBase {

	public Login_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	
	@FindBy(id = "Email")
	WebElement email;
	
	@FindBy(id = "Password")
	WebElement password;
	
	@FindBy (id = "RememberMe")
	WebElement RememberMe;

	@FindBy(xpath = "//input[@class='button-1 login-button']")
	WebElement LoginButton;
	
	@FindBy(linkText = "My account")
	WebElement result;

	public void loginUser(String emailTxt , String passwordTxt ) {
		//SetTextElement(email, emailTxt);
		//SetTextElement(password, passwordTxt);
	}
	
	public void checkRememberMe() {
		if(!RememberMe.isSelected()) 
			RememberMe.click();
	}

	public void PressLoginButton() {
		
		//clickButton(LoginButton);
	
	}

	public void ReturnResult(String resultTxt)
	{
		
		//AssertElement(result, resultTxt);
	}
}
