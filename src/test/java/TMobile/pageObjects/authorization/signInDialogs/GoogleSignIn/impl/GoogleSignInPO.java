package TMobile.pageObjects.authorization.signInDialogs.GoogleSignIn.impl;

import TMobile.commonComponents.BasicElements;
import TMobile.pageObjects.authorization.signInDialogs.GoogleSignIn.model.GoogleSignInModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class GoogleSignInPO extends BasicElements implements GoogleSignInModel {
    private static final Logger LOGGER = LogManager.getLogger();


    private GoogleSignInPO(WebDriver driver) {
        super(driver);
    }

    public static GoogleSignInPO forThis(WebDriver driver) {
        return new GoogleSignInPO(driver);
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
