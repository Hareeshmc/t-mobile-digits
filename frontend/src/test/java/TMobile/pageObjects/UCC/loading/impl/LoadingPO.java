package TMobile.pageObjects.UCC.loading.impl;

import TMobile.commonComponents.BasicElements;
import TMobile.commonComponents.WaitFor;
import TMobile.pageObjects.UCC.loading.model.LoadingModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoadingPO extends BasicElements implements LoadingModel {
    private static final Logger LOGGER = LogManager.getLogger();
    private By logoIcon = By.cssSelector("[class='logo-dots']");

    private LoadingPO(WebDriver driver) {
        super(driver);
    }

    public static LoadingPO forThis(WebDriver driver) {
        return new LoadingPO(driver);
    }

    @Override
    public Boolean isLoaded() {
        return new WaitFor(driver).isElementFound(logoIcon);
    }

    @Override
    public Boolean isLoadedForSpecifiedPeriodOfTime() {
        return null;
    }
}
