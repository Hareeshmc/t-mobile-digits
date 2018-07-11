package main;

import TMobile.commonComponents.SolutionException;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static main.DemoSolutionTestSuite.driver;


@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "TMobile/stepdefs",
        plugin = {"pretty", "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports"},
        tags = {"@Demo"})
public class RunCucumberDemo {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "Demo", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @AfterMethod(alwaysRun = true)
    public void catchException(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            SolutionException.forThis(driver).fullPageScreenShot((result.getMethod().getMethodName()));
        }
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }
}
