package TMobile.pageObjects.authorization.loginPageTMobileDigits.impl;

import TMobile.commonComponents.BasicElements;
import TMobile.commonComponents.WaitFor;
import TMobile.pageObjects.authorization.loginPageTMobileDigits.model.LoginPageDigitsModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPageDigitsPO extends BasicElements implements LoginPageDigitsModel {
    private static final Logger LOGGER = LogManager.getLogger();

    private By microsoftBtn = By.cssSelector("[class='btn btn-msft']");


    private LoginPageDigitsPO(WebDriver driver) {
        super(driver);
    }

    public static LoginPageDigitsPO forThis(WebDriver driver) {
        return new LoginPageDigitsPO(driver);
    }


    public Boolean checkTitle(String titleName) {
        new WaitFor(driver);
        return driver.getTitle().equals(titleName);
    }

    public String checkTitle() {
        new WaitFor(driver);
        return driver.getTitle();
    }

    @Override
    public void pressOnAuthorizationButton(String buttonName) {
        if (buttonName.equals("Microsoft")) {
            click(microsoftBtn);
        } else {
            Assert.fail("requested " + buttonName + " button does not exist");
        }
    }

    @Override
    public Boolean checkLabel(String label) {
        return null;
    }

    @Override
    public Boolean checkIfAllImagesIconLoaded() {
        return null;
    }
}
