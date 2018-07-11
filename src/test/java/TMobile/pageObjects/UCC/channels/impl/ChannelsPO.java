package TMobile.pageObjects.UCC.channels.impl;

import TMobile.commonComponents.BasicElements;
import TMobile.pageObjects.UCC.channels.model.ChannelsModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ChannelsPO extends BasicElements implements ChannelsModel {
    private static final Logger LOGGER = LogManager.getLogger();


    private ChannelsPO(WebDriver driver) {
        super(driver);
    }

    public static ChannelsPO forThis(WebDriver driver) {
        return new ChannelsPO(driver);
    }

}
