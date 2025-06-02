package com.demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class LogoutPage {

    private WebDriver driver;

    @FindBy(id = "logout2")
    private WebElement logoutButton;

    @FindBy(id = "login2")
    private WebElement loginNavLink;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLogout() {
        logoutButton.click();
    }

    public boolean isLoginButtonVisible() {
        try {
            Thread.sleep(1000); // wait for UI to update
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return loginNavLink.isDisplayed();
    }

	public WebElement getLogoutButton() {
		// TODO Auto-generated method stub
		return null;
	}
}
