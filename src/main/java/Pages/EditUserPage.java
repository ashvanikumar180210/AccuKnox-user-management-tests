package Pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class EditUserPage {
    WebDriver driver;
    WebDriverWait wait;

    public EditUserPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void editUser(String oldUsername, String newUsername) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-card']//div[@role='row']")));
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-card']//div[@role='row']"));

        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.xpath("./div"));
            if (columns.size() > 1 && columns.get(1).getText().trim().equals(oldUsername)) {
                WebElement actionColumn = columns.get(columns.size() - 1);
                WebElement editIcon = actionColumn.findElement(By.xpath(".//i[contains(@class,'bi-pencil-fill')]"));
                editIcon.click();
                break;
            }
        }

        // Wait for the edit form
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Username']/following::input[1]")));
        Thread.sleep(1000);
        // Use JS to set the value and trigger input event
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='';", usernameField); // clear the field
        js.executeScript("arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                usernameField, newUsername); // set and trigger change

        // Optionally, trigger blur to simulate user tabbing out
        usernameField.sendKeys(Keys.TAB);

        // Wait and click the Save button
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit']")));
        saveBtn.click();

        // Confirm success toast
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'oxd-toast') and contains(.,'Success')]")));
    }


}
