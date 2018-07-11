package TMobile.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.Assert;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static TMobile.frameworkConfiguration.FrameworkParameter.getRemoteTestEnvIpAddress;


public class Driver extends Thread {
    private static final Logger LOGGER = LogManager.getLogger();
    private static WebDriver aDriver = null;
    private static BrowserName useThisDriver = null;

    /**
     * @param browser
     * @param aURL
     * @param maximize
     * @return
     * @throws WebDriverException
     */
    public static WebDriver create(String browser, String aURL, boolean maximize) throws WebDriverException {
        setBrowser(browser);
        if (get() == null) {
            LOGGER.error("Unable to load driver for \"" + browser + "\"");
        }
        try {
            aDriver.get(aURL);
        } catch (WebDriverException e) {
            LOGGER.error(e.getMessage());
        }
        if (maximize) {
            aDriver.manage().window().maximize();
        }
        return aDriver;
    }

    /**
     * @param browser
     */
    private static void setBrowser(String browser) {
        switch (browser.toLowerCase().replace(" ", "")) {
            case "firefox":
                useThisDriver = BrowserName.FIREFOX;
                break;
            case "chrome":
                useThisDriver = BrowserName.GOOGLECHROME;
                break;
            case "remote":
                useThisDriver = BrowserName.REMOTE;
                break;
            default:
                LOGGER.error("Invalid browser driver name specified \"" + browser + "\"");
        }
        cleanup();
    }

    /**
     * @return
     */
    private static WebDriver get() {
        try {
            if (aDriver != null && aDriver.getWindowHandle() != null) {
                return aDriver;
            }
        } catch (Exception e) {
            LOGGER.error("Not able to get current Driver");
        }
        return _get();
    }

    /**
     * @return
     */
    private static WebDriver _get() {
        cleanup();

        if (useThisDriver == null) {
            setBrowser(System.getProperty("demo.webdriver", "CHROME"));
        }
        String driverDir;
        LOGGER.info("You are running : " + System.getProperty("os.name"));
        if (System.getProperty("os.name").equals("Windows 10")) {
            driverDir = "src\\test\\java\\TMobile\\utils\\";
        } else {
            driverDir = "src/test/java/TMobile/utils/";
        }

        boolean isWin = System.getProperty("os.name").contains("Windows");

        switch (useThisDriver) {
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", driverDir + "geckodriver" + (isWin ? ".exe" : ""));
                aDriver = new FirefoxDriver();
                aDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
                aDriver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
                break;

            case GOOGLECHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-web-security");
//                options.addArguments("--no-proxy-server");
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);
                ChromeOptions handlSSLErr = new ChromeOptions();
                handlSSLErr.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                handlSSLErr.setCapability(ChromeOptions.CAPABILITY, options);
                ChromeDriverService service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(driverDir + "chromedriver" + (isWin ? ".exe" : "")))
                        .usingAnyFreePort()
                        .build();
                aDriver = new ChromeDriver(service, options);

                aDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
                aDriver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
                break;

            case REMOTE:
                aDriver = determineRemoteDriver();
                assert aDriver != null;
                aDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
                aDriver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
                break;
        }

        // we want to shutdown the shared browser when the test finish
        Runtime.getRuntime().addShutdownHook(
                new Thread() {
                    public void run() {
                        Driver.cleanup();
                    }
                }
        );
        return aDriver;
    }

    /**
     * @return
     */
    private static WebDriver determineRemoteDriver() {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        try {
            return new RemoteWebDriver(new URL(getRemoteTestEnvIpAddress().trim()), capability);
        } catch (UnreachableBrowserException e) {
            Assert.fail(e.getMessage());
        } catch (MalformedURLException e) {
            LOGGER.error("Remote url invalid: " + getRemoteTestEnvIpAddress() + " (skipping)\n");
            Assert.fail("No appropriate remote driver found.");
        }
        return null;
    }

    /**
     *
     */
    public static void cleanup() {
        if (aDriver != null) {
            try {
                aDriver.quit();
                aDriver = null;
            } catch (Exception e) {
                // I don't care about errors at this point
            }
        }
    }

    /**
     *
     */
    private enum BrowserName {
        FIREFOX, GOOGLECHROME, REMOTE
    }
}