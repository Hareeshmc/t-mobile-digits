package main;

import TMobile.commonComponents.SolutionException;
import TMobile.utils.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static TMobile.frameworkConfiguration.FrameworkParameter.getBrowser;
import static TMobile.frameworkConfiguration.FrameworkParameter.getTestEnvIpAddress;
import static TMobile.frameworkConfiguration.SetSuite.getFrameworkSettings;


public class DemoSolutionTestSuite {
    private static final Logger LOGGER = LogManager.getRootLogger();
    public static WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public static void setupSuite(String serverName) {
        getFrameworkSettings();
        if (serverName.equals("Demo Server")) {
            LOGGER.info("Getting to the demo server");
            driver = Driver.create(getBrowser(), getTestEnvIpAddress(), true);
        } else {
            LOGGER.info("Getting to the " + serverName);
            driver = Driver.create(getBrowser(), serverName, true);
        }
    }

    @Parameters({"delayclose"})
    @AfterSuite(alwaysRun = true)
    public static void closeDriver(@Optional Boolean delay) {
        if (delay != null && delay) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Driver.cleanup();
    }

    @AfterMethod(alwaysRun = true)
    public void catchException(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            SolutionException.forThis(driver).fullPageScreenShot((result.getMethod().getMethodName()));
        }
    }
}
