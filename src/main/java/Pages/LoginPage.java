package Pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Username']")));
        userField.sendKeys(username);

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passField.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        loginBtn.click();

        // wait for dashboard/admin page to appear after login
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a.oxd-main-menu-item[href*='viewAdminModule']")));
    }

}
