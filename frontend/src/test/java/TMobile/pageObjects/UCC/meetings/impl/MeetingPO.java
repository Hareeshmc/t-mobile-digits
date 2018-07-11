package TMobile.pageObjects.UCC.meetings.impl;

import TMobile.commonComponents.BasicElements;
import TMobile.pageObjects.UCC.meetings.model.MeetingModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class MeetingPO extends BasicElements implements MeetingModel {
    private static final Logger LOGGER = LogManager.getLogger();


    private MeetingPO(WebDriver driver) {
        super(driver);
    }

    public static MeetingPO forThis(WebDriver driver) {
        return new MeetingPO(driver);
    }

}
