package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class SearchUserPage {
    WebDriver driver;
    WebDriverWait wait;

    public SearchUserPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToAdminModule() {
        
       
        // Wait for Admin menu to be clickable
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("a.oxd-main-menu-item[href*='viewAdminModule']")));
        adminMenu.click();
    }

    public void searchUser(String username, String role, String prefix, String desiredName, String status) {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Username']/following::input[1]")));
        usernameInput.clear();
        usernameInput.sendKeys(username);

        WebElement roleDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@class='oxd-select-text--after'])[1]")));
        roleDropdown.click();
        driver.findElement(By.xpath("//div[@role='listbox']//span[text()='" + role + "']")).click();

        WebElement empNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Type for hints...']")));
        empNameField.clear();
        empNameField.sendKeys(prefix);

        List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@role='listbox']//span")));
        for (WebElement s : suggestions) {
            if (s.getText().equalsIgnoreCase(desiredName)) {
                s.click();
                break;
            }
        }

        WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Status']/../following-sibling::div//div[contains(@class,'oxd-select-text')]")));
        statusDropdown.click();
        driver.findElement(By.xpath("//div[@role='listbox']//span[text()='" + status + "']")).click();

        driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='oxd-table-card']//div[@role='row']")));
    }
}

 
