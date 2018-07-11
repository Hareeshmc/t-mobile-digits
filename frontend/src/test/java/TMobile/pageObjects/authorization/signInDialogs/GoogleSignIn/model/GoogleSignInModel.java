package TMobile.pageObjects.authorization.signInDialogs.GoogleSignIn.model;

public interface GoogleSignInModel {
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
