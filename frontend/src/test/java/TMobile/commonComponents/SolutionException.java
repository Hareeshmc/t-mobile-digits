package TMobile.commonComponents;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SolutionException extends Exception {
    private WebDriver driver;

    private SolutionException(WebDriver driver) {
        this.driver = driver;
    }

    public static SolutionException forThis(WebDriver driver) {
        return new SolutionException(driver);
    }

    public void fullPageScreenShot(String methodName) {
        final Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500))
                .takeScreenshot(driver);
        final BufferedImage image = screenshot.getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();

            File testTempDir = createATempDirectoryForScreenshots();
            String newImageFileName = methodName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                    ".png";

            File testTempImage = new File(testTempDir, newImageFileName);
            FileOutputStream osf;

            osf = new FileOutputStream(testTempImage);
            osf.write(imageInByte);
            osf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File createATempDirectoryForScreenshots() {
        String s = File.separator;
        String ourTestTempPathName = System.getProperty("user.dir") +
                String.format("%ssrc%sscreenshots", s, s);

        File testTempDir = new File(ourTestTempPathName);
        if (testTempDir.exists()) {
            if (!testTempDir.isDirectory()) {
                Assert.fail("Test path exists but is not a directory");
            }
        }
        return testTempDir;
    }
}