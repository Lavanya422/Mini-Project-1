package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class LoginPage {
    
	@FindBy(id = "login2")
    private WebElement loginNavButton;

    @FindBy(id = "loginusername")
    private WebElement usernameField;

    @FindBy(id = "loginpassword")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginButton;

    @FindBy(id = "nameofuser")
    private WebElement loggedInUserLabel;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickLoginNavButton() {
        loginNavButton.click();
    }

    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getLoggedInUsername() {
        return loggedInUserLabel.getText();
    }

    public void login(String username, String password) {
        clickLoginNavButton();
        try {
            Thread.sleep(1000); // brief wait for modal
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public By getLoginButtonLocator() {
        return By.xpath("//button[text()='Log in']");
    }
}
