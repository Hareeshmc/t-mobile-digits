package TMobile.commonComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

/**
 * This is Base Page Object implementing common for all pages behavior.
 */
public class BasicElements {
    public WaitFor wait;
    protected WebDriver driver;

    public BasicElements(WebDriver driver) {
        this.driver = driver;
        wait = new WaitFor(driver);
    }

    public <T> T click(T ele, boolean... expectRefresh) {
        final By body = By.cssSelector("body");
        wait.ForElementBePresent(ele);
        if (ele.getClass().equals(WebElement.class)) {
            ((WebElement) ele).click();
        } else {
            driver.findElement(((By) ele)).click();
        }
        if (expectRefresh.length > 0 && expectRefresh[0]) {
            wait.ForElementNotPresent(body);
        }
        return ele;
    }

    public <T> void setText(T ele, String symbols) {
        wait.ForElementBePresent(ele);
        driver.findElement(((By) ele)).clear();
        driver.findElement((By) ele).sendKeys(symbols);
    }

    protected <T> String getText(T ele, boolean... isValue) {
        String textFromLabel = "";
        if (isValue.length > 0 && isValue[0]) {
            wait.ForElementBePresent(ele);
            if (ele.getClass().equals(RemoteWebElement.class)) {
                textFromLabel = ((WebElement) ele).getAttribute("value");
            } else {
                textFromLabel = driver.findElement((By) ele).getAttribute("value");
            }
        } else {
            wait.ForElementBePresent(ele);
            if (ele.getClass().equals(RemoteWebElement.class)) {
                textFromLabel = ((WebElement) ele).getText();
            } else {
                textFromLabel = driver.findElement((By) ele).getText();
            }
        }
        return textFromLabel;
    }
}