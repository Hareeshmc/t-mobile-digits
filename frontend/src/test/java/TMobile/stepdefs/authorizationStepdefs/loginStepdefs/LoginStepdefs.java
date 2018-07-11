package TMobile.stepdefs.authorizationStepdefs.loginStepdefs;

import TMobile.pageObjects.authorization.loginPageTMobileDigits.impl.LoginPageDigitsPO;
import cucumber.api.java8.En;
import org.testng.Assert;

import static main.DemoSolutionTestSuite.driver;

public class LoginStepdefs implements En {
    public LoginStepdefs() {
        When("^I see \"([^\"]*)\" login screen$", (String pageName) -> {
            String titleName = "T-Mobile DIGITS – Login";
            if (pageName.equals("Digits")) {
                Assert.assertTrue(LoginPageDigitsPO.forThis(driver).checkTitle(titleName),
                        "Title is not as expected. Getting " + LoginPageDigitsPO.forThis(driver).checkTitle()
                                + " instead of " + titleName);
            } else {
                Assert.fail("Title for page " + pageName + " does not exist");
            }
        });


        Then("^I press \"([^\"]*)\" button$", (String nameOfTheButton) -> {
            LoginPageDigitsPO.forThis(driver).pressOnAuthorizationButton(nameOfTheButton);
        });
    }
}
