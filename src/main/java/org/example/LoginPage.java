package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openLoginPage() {
        driver.get("https://practicetestautomation.com/practice-test-login/");
        System.out.println("Halaman login dibuka.");
    }

    public LoginPage login(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);
        System.out.println("Username dimasukkan.");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        System.out.println("Password dimasukkan.");

        WebElement loginButton = driver.findElement(By.id("submit"));
        loginButton.click();
        System.out.println("Tombol login diklik.");
        return this;
    }

    public boolean isLoggedInSuccessfully() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("practicetestautomation.com/logged-in-successfully/");
    }

    public boolean isLogoutButtonDisplayed() {
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Log out']")));
        return logoutButton.isDisplayed();
    }

    public String getVerificationText() {
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Congratulations')]")));
        return messageElement.getText();
    }
}