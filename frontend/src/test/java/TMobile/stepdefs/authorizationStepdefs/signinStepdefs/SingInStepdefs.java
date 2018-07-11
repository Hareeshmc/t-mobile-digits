package TMobile.stepdefs.authorizationStepdefs.signinStepdefs;

import TMobile.pageObjects.authorization.signInDialogs.MicrosoftSignIn.impl.MicrosoftSignInPO;
import cucumber.api.DataTable;
import cucumber.api.java8.En;

import static main.DemoSolutionTestSuite.driver;

public class SingInStepdefs implements En {
    public SingInStepdefs() {
        And("^I get through \"([^\"]*)\" authorization with credentials:$", (String nameOfTheService, DataTable dataTable) -> {
            if (nameOfTheService.equals("Microsoft")) {
                for (int i = 0; dataTable.raw().size() > i; i++) {
                    MicrosoftSignInPO.forThis(driver).fillinEmailPhoneNumberSkype(dataTable.raw().get(i).get(0));
                    MicrosoftSignInPO.forThis(driver).pressNextBtn();
                    MicrosoftSignInPO.forThis(driver).fillinPassword(dataTable.raw().get(i).get(1));
                    MicrosoftSignInPO.forThis(driver).pressSignInBtn();
                }
            }
        });
    }
}
