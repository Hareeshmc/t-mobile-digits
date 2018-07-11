package TMobile.stepdefs;

import cucumber.api.java8.En;
import main.DemoSolutionTestSuite;


public class CommonStepdefs implements En {
    public CommonStepdefs() {
        Given("^I navigate to the \"([^\"]*)\"$", DemoSolutionTestSuite::setupSuite);

        And("^I sit on the Page for \"([^\"]*)\" seconds$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
