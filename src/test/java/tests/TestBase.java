package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.LaunchConfig;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.Attachments;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    private static final LaunchConfig config = ConfigFactory.create(LaunchConfig.class, System.getProperties());

    @BeforeAll
    @Step("Устанавливаем конфигурации перед запуском теста")
    public static void settingsTest() {
        RestAssured.baseURI = config.getBaseUrl();
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.getBrowser();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.pageLoadStrategy = config.pageLoadStrategy();
        if (config.isRemote()) {
            Configuration.remote = config.remoteUrl();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    @Step("Добавляем конфигурации для запускаемого теста")
    public void setUpBeforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    @Step("Выполняем конфигурации после окончания теста")
    public void settingsAfterTest() {
        Attachments.screenshotAs("Изображение последнего события из теста");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
        closeWebDriver();
    }
}