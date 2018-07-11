package TMobile.pageObjects.authorization.signInDialogs.MicrosoftSignIn.model;

public interface MicrosoftSignInModel {
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
