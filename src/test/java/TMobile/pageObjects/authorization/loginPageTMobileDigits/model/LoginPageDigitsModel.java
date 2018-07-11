package TMobile.pageObjects.authorization.loginPageTMobileDigits.model;

public interface LoginPageDigitsModel {
    /**
     * @return
     */
    Boolean checkTitle(String titleName);

    /**
     * @param buttonName
     */
    void pressOnAuthorizationButton(String buttonName);

    /**
     * @param label
     * @return
     */
    Boolean checkLabel(String label);

    /**
     * @return
     */
    Boolean checkIfAllImagesIconLoaded();
}
