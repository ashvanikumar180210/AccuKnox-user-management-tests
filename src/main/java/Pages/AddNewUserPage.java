
package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddNewUserPage {
    WebDriver driver;
    WebDriverWait wait;

    public AddNewUserPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addUser(String username, String role, String prefix, String desiredName, String status, String password, String confirmpassword) {
        // Navigate to Admin module
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("a.oxd-main-menu-item[href*='viewAdminModule']")));
        adminMenu.click();

        // Click Add button
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(@class,'oxd-button') and normalize-space()='Add']")));
        addBtn.click();

        // Fill form
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//label[text()='Username']/following::input[1]")));
        usernameInput.clear();
        usernameInput.sendKeys(username);

        WebElement roleDropdown = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//div[@class='oxd-select-text--after'])[1]")));
        roleDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@role='listbox']//span[text()='" + role + "']"))).click();

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
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@role='listbox']//span[text()='" + status + "']"))).click();

        
     // Fill Password
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//input[@type='password'])[1]")));
        passwordInput.sendKeys(password);

        // Fill Confirm Password
        WebElement confirmPasswordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//input[@type='password'])[2]")));
        confirmPasswordInput.sendKeys(confirmpassword);
        
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(@class,'oxd-button') and normalize-space()='Save']")));
        saveBtn.click();

        // Confirm success
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class,'oxd-toast') and contains(.,'Success')]")));
    }
}
