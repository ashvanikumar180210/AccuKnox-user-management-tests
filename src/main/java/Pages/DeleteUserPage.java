package Pages;



import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class DeleteUserPage {
    WebDriver driver;
    WebDriverWait wait;

    public DeleteUserPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void deleteUser(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-card']//div[@role='row']")));

        // Refetch all rows fresh
        List<WebElement> freshRows = driver.findElements(By.xpath("//div[@class='oxd-table-card']//div[@role='row']"));

        for (int i = 0; i < freshRows.size(); i++) {
            WebElement row = driver.findElements(By.xpath("//div[@class='oxd-table-card']//div[@role='row']")).get(i); // refetch
            List<WebElement> columns = row.findElements(By.xpath("./div"));

            if (columns.size() > 1 && columns.get(1).getText().trim().equals(username)) {
                WebElement deleteIcon = columns.get(columns.size() - 1)
                                               .findElement(By.xpath(".//i[contains(@class,'bi-trash')]"));
                deleteIcon.click();

                WebElement confirmDeleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space()='Yes, Delete']")));
                confirmDeleteBtn.click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'oxd-toast') and contains(.,'Success')]")));
                break;
            }
        }
    }

}
