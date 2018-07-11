package TMobile.pageObjects.UCC.search.impl;

import TMobile.commonComponents.BasicElements;
import TMobile.pageObjects.UCC.search.model.SearchModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class SearchPO extends BasicElements implements SearchModel {
    private static final Logger LOGGER = LogManager.getLogger();


    private SearchPO(WebDriver driver) {
        super(driver);
    }

    public static SearchPO forThis(WebDriver driver) {
        return new SearchPO(driver);
    }

}
