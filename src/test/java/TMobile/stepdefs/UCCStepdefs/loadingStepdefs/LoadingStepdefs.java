package TMobile.stepdefs.UCCStepdefs.loadingStepdefs;

import TMobile.pageObjects.UCC.loading.impl.LoadingPO;
import cucumber.api.java8.En;
import org.testng.Assert;

import static main.DemoSolutionTestSuite.driver;

public class LoadingStepdefs implements En {
    public LoadingStepdefs() {
        When("^I pick \"([^\"]*)\" option$", (String arg0) -> {
        });
        Then("^I will not get to any page at all$", () -> {

        });
        When("^I see loading screen$", () -> {
            Assert.assertTrue(LoadingPO.forThis(driver).isLoaded(), "Loading Icon is not available on the loading screen");
        });
    }
}
