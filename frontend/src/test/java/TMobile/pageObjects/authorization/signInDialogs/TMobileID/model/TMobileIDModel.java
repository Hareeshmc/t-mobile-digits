package TMobile.pageObjects.authorization.signInDialogs.TMobileID.model;

public interface TMobileIDModel {
    /**
     * @param txt
     */
    void fillinEmailPhoneNumberSkype(String txt);

    /**
     *
     */
    void pressNextBtn();

    /**
     * @param txt
     */
    void fillinPassword(String txt);

    /**
     *
     */
    void pressSignInBtn();
}
