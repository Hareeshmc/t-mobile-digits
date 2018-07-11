package TMobile.pageObjects.authorization.signInDialogs.TMobileID.impl;

import TMobile.commonComponents.BasicElements;
import TMobile.pageObjects.authorization.signInDialogs.TMobileID.model.TMobileIDModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class TMobileIDPO extends BasicElements implements TMobileIDModel {
    private static final Logger LOGGER = LogManager.getLogger();


    private TMobileIDPO(WebDriver driver) {
        super(driver);
    }

    public static TMobileIDPO forThis(WebDriver driver) {
        return new TMobileIDPO(driver);
    }

    @Override
    public void fillinEmailPhoneNumberSkype(String txt) {

    }

    @Override
    public void pressNextBtn() {

    }

    @Override
    public void fillinPassword(String txt) {

    }

    @Override
    public void pressSignInBtn() {

    }
}
