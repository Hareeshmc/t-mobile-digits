package TMobile.frameworkConfiguration;

public class FrameworkParameter {
    private static String testEnvIpAddress;
    private static String browser;
    private static String remoteTestEnvIpAddress;

    /**
     * @return
     */
    public static String getTestEnvIpAddress() {
        return testEnvIpAddress;
    }

    /**
     * @param testEnvIpAddress
     */
    static void setTestEnvIpAddress(String testEnvIpAddress) {
        FrameworkParameter.testEnvIpAddress = testEnvIpAddress;
    }

    /**
     * @return
     */
    public static String getBrowser() {
        return browser;
    }

    /**
     * @param browser
     */
    static void setBrowser(String browser) {
        FrameworkParameter.browser = browser;
    }

    /**
     * @return
     */
    public static String getRemoteTestEnvIpAddress() {
        return remoteTestEnvIpAddress;
    }

    /**
     * @param remoteTestEnvIpAddress
     */
    static void setRemoteTestEnvIpAddress(String remoteTestEnvIpAddress) {
        FrameworkParameter.remoteTestEnvIpAddress = remoteTestEnvIpAddress;
    }


}
