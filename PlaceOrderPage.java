package com.demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class PlaceOrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Place Order modal elements
    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement creditCardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseButton;

    // Confirmation popup elements
    @FindBy(xpath = "//div[contains(@class,'sweet-alert')]//h2")
    private WebElement confirmationMessage;

    @FindBy(xpath = "//button[text()='OK']")
    private WebElement confirmationOkButton;

    public PlaceOrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Fill in the order form and submit
     */
    public void placeOrder(String name, String country, String city, String creditCard, String month, String year) {
        wait.until(ExpectedConditions.visibilityOf(nameField));
        nameField.clear();
        nameField.sendKeys(name);

        countryField.clear();
        countryField.sendKeys(country);

        cityField.clear();
        cityField.sendKeys(city);

        creditCardField.clear();
        creditCardField.sendKeys(creditCard);

        monthField.clear();
        monthField.sendKeys(month);

        yearField.clear();
        yearField.sendKeys(year);

        purchaseButton.click();
    }

    /**
     * Verify if the order confirmation popup is visible with expected message
     */
    public boolean isOrderConfirmed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
            String text = confirmationMessage.getText();
            return text != null && text.toLowerCase().contains("thank you");
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Click OK button on confirmation popup to close it
     */
    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmationOkButton));
        confirmationOkButton.click();
    }
}
