package saucedemo.shop.allure;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.nio.charset.StandardCharsets;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import com.codeborne.selenide.Selenide;
import java.net.MalformedURLException;
import java.net.URL;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class Attachments {

        @Attachment(value = "{attachName}", type = "image/png")
        public static byte[] screenshotAs(String attachName) {
            return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
        }

        @Attachment(value = "Page source", type = "text/plain")
        public static byte[] pageSource() {
            return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
        }

        @Attachment(value = "{attachName}", type = "text/plain")
        public static String attachAsText(String attachName, String message) {
            return message;
        }

        public static void browserConsoleLogs() {
            attachAsText(
                    "Browser console logs",
                    String.join("\n", Selenide.getWebDriverLogs(BROWSER))
            );
        }

        @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
        public static String addVideo() {
            return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                    + getVideoUrl()
                    + "' type='video/mp4'></video></body></html>";
        }

        public static URL getVideoUrl() {
            String videoUrl = "https://selenoid.autotests.cloud/video/" + nameVideo() + ".mp4";

            try {
                return new URL(videoUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }
        public static String nameVideo() {
            String str = sessionId().toString();
            String number = "";
            for( int x = 0; x < 6; x++){
                number += str.charAt(x);
            }
            return number;
        }


}

