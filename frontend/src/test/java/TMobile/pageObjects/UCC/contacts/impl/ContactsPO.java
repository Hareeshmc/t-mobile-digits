package TMobile.pageObjects.UCC.contacts.impl;

import TMobile.commonComponents.BasicElements;
import TMobile.pageObjects.UCC.contacts.model.ContactsModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ContactsPO extends BasicElements implements ContactsModel {
    private static final Logger LOGGER = LogManager.getLogger();

    private ContactsPO(WebDriver driver) {
        super(driver);
    }

    public static ContactsPO forThis(WebDriver driver) {
        return new ContactsPO(driver);
    }

}
