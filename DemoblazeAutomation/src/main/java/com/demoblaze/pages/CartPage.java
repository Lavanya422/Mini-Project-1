package com.demoblaze.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "Cart")
    private WebElement cartLink;

    @FindBy(xpath = "//a[contains(text(),'Add to cart')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//a[contains(text(),'Delete')]")
    private List<WebElement> deleteButtons;

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public void addProductToCart(String productName) {
        try {
            WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(productName)));
            product.click();

            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();

            driver.navigate().back();
        } catch (TimeoutException e) {
            System.err.println("Timeout while adding product: " + productName);
        } catch (NoSuchElementException e) {
            System.err.println("Product not found: " + productName);
        }
    }

    public int getCartItemsCount() {
        return deleteButtons.size();
    }

    public void deleteAllItems() {
        while (!deleteButtons.isEmpty()) {
            try {
                WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(0)));
                deleteBtn.click();
                // Allow DOM to update
                wait.until(ExpectedConditions.stalenessOf(deleteBtn));
                PageFactory.initElements(driver, this); // refresh list
            } catch (TimeoutException e) {
                System.err.println("Timeout waiting to delete item.");
                break;
            }
        }
    }

    public boolean isPlaceOrderButtonDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(placeOrderButton)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

	public boolean isProductAddedToCart(String string) {
		// TODO Auto-generated method stub
		return false;
	}

	public void placeOrder(String string, String string2, String string3, String string4, String string5,
			String string6) {
		// TODO Auto-generated method stub
		
	}

	public boolean isOrderConfirmed() {
		// TODO Auto-generated method stub
		return false;
	}
}