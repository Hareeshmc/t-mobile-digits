package TMobile.pageObjects.authorization.signInDialogs.MicrosoftSignIn.impl;

import TMobile.commonComponents.BasicElements;
import TMobile.pageObjects.authorization.signInDialogs.MicrosoftSignIn.model.MicrosoftSignInModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MicrosoftSignInPO extends BasicElements implements MicrosoftSignInModel {
    private static final Logger LOGGER = LogManager.getLogger();
    private By loginField = By.cssSelector("[name='loginfmt']");
    private By passwordField = By.cssSelector("[name='passwd']");
    private By nextBtn = By.cssSelector("[class='btn btn-block btn-primary']");
    private By submitBtn = By.cssSelector("[type='submit']");

    private MicrosoftSignInPO(WebDriver driver) {
        super(driver);
    }

    public static MicrosoftSignInPO forThis(WebDriver driver) {
        return new MicrosoftSignInPO(driver);
    }


    @Override
    public void fillinEmailPhoneNumberSkype(String txt) {
        setText(loginField, txt);
    }

    @Override
    public void pressNextBtn() {
        click(nextBtn);
    }

    @Override
    public void fillinPassword(String txt) {
        setText(passwordField, txt);
    }

    @Override
    public void pressSignInBtn() {
        click(submitBtn);
    }
}
