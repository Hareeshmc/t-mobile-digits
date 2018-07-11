package TMobile.pageObjects.UCC.call.impl;

import TMobile.commonComponents.BasicElements;
import TMobile.pageObjects.UCC.call.model.CallModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class CallPO extends BasicElements implements CallModel {
    private static final Logger LOGGER = LogManager.getLogger();


    private CallPO(WebDriver driver) {
        super(driver);
    }

    public static CallPO forThis(WebDriver driver) {
        return new CallPO(driver);
    }

}
