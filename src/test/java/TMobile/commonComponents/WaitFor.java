package TMobile.commonComponents;

import com.paulhammant.ngwebdriver.NgWebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;

public class WaitFor {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final long POLLING_TIME = (long) 55; // ms
    private static final long WAIT_TIME = (long) 30; // s
    private WebDriver driver;
    private Wait<WebDriver> wait;

    public WaitFor(WebDriver driver) {
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(POLLING_TIME))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        try {
            new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
            new WebDriverWait(driver, WAIT_TIME).until((ExpectedCondition<Boolean>) wd ->
                    wd != null && ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        } catch (ScriptTimeoutException e) {
            LOGGER.info("script timeout");
        }
        this.driver = driver;
    }

    public void untilTextPresent(By locator, String expectedText, Integer... longerWaitTimeInSeconds) {
        Long localWaitTime = WAIT_TIME;
        if (longerWaitTimeInSeconds.length > 0) {
            localWaitTime = (long) longerWaitTimeInSeconds[0];
        }
        try {
            LOGGER.info("Waiting for text: " + expectedText + " to be present on: " + locator);
            wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(WAIT_TIME))
                    .pollingEvery(Duration.ofMillis(POLLING_TIME))
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
            wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(locator), expectedText));
            LOGGER.info("Text " + expectedText + " on  : " + locator + " is present now");
        } catch (Exception e) {
            SolutionException.forThis(driver).fullPageScreenShot("Until_text_present_");
            Assert.fail("Expected text is not found, but found instead :");
        }
    }

    public boolean untilElementIsNotPresent(By locator, Integer... longerWaitTimeInSeconds) {
        Long localWaitTime = WAIT_TIME;
        if (longerWaitTimeInSeconds.length > 0) {
            localWaitTime = (long) longerWaitTimeInSeconds[0];
        }
        try {
            LOGGER.info("Waiting for Element : " + locator + " to be not visible anymore");
            wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(WAIT_TIME))
                    .pollingEvery(Duration.ofMillis(POLLING_TIME));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            LOGGER.info("Element : " + locator + " is not visible anymore");
        } catch (Exception e) {
            LOGGER.info("Not able to wait enough so element become not visible \n" + Arrays.toString(e.getStackTrace()));
        }
        return true;
    }

    public void ForElementNotPresent(By element) {
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(WAIT_TIME))
                .pollingEvery(Duration.ofMillis(POLLING_TIME))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(element)));
    }

    public <T> boolean ForElementBePresent(T locator, Integer... timeout) {
        long time;
        if (timeout.length > 0) {
            time = new Long(timeout[0]);
        } else {
            time = WAIT_TIME;
        }
        try {
            LOGGER.info("Searching for element: " + locator.toString());
            wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(WAIT_TIME))
                    .pollingEvery(Duration.ofMillis(POLLING_TIME))
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
            if (locator.getClass().equals(RemoteWebElement.class)) {
                wait.until(ExpectedConditions.elementToBeClickable((WebElement) locator));
            } else {
                wait.until(ExpectedConditions.elementToBeClickable((By) locator));
            }
            LOGGER.info("Element found: " + locator.toString());
        } catch (Exception e) {
            if (timeout.length > 2) {
                LOGGER.info("Element is not present: " + locator.toString());
                SolutionException.forThis(driver).fullPageScreenShot("wait_for_");
                Assert.fail("Can't find expected element" + locator + "\n" + Arrays.toString(e.getStackTrace()));
            }
        }
        return true;
    }

    public <T> boolean textNotToBePresent(By locator, String text, Integer... timeout) {
        long time;
        if (timeout.length > 0) {
            time = new Long(timeout[0]);
        } else {
            time = WAIT_TIME;
        }
        try {
            LOGGER.info("Searching for element: " + locator.toString());
            wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(WAIT_TIME))
                    .pollingEvery(Duration.ofMillis(POLLING_TIME))
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
            wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
            LOGGER.info("Text found: " + locator.toString());
        } catch (Exception e) {
            SolutionException.forThis(driver).fullPageScreenShot("Text_not_to_be_present_" + text);
            Assert.fail("Can't find expected Text on element" + locator + "\n" + Arrays.toString(e.getStackTrace()));
        }
        return true;
    }

    public <T> boolean isElementFound(T ele) {
        boolean isFound;
        try {
            if (ele.getClass().equals(RemoteWebElement.class)) {
                isFound = ((WebElement) ele).isDisplayed();
            } else {
                isFound = driver.findElement(((By) ele)).isDisplayed();
            }
        } catch (WebDriverException e) {
            isFound = false;
        }
        return isFound;
    }
}