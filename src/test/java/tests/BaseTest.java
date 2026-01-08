package tests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import utils.TestListener;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

@Listeners(TestListener.class)

public class BaseTest {
    public WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    AccountPage accountPage;
    RegistrationPage registrationPage;
    RestorePasswordPage restorePasswordPage;

    @Parameters({"browser"})
    @BeforeMethod(description = "Настройка WebDriver и Page Object перед тестом")
    @Step("Настройка драйвера(браузер = '{browser}')")
    public void setUP(@Optional("chrome") String browser, ITestContext testContext) {

        if (browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless");
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.of(5, SECONDS));
        } else {
            driver = new SafariDriver();
        }

        testContext.setAttribute("driver", driver);

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        accountPage = new AccountPage(driver);
        registrationPage = new RegistrationPage(driver);
        restorePasswordPage = new RestorePasswordPage(driver);
    }

    @AfterMethod(alwaysRun = true, description = "Проверка корректного завершения теста и закрытие браузера")
    @Step("Закрыть браузер")
    public void tearDown() {
        if (driver !=null) {
            driver.quit();
        }
    }
}
