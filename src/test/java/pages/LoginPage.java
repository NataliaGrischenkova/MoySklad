package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage {

    private static final By EMAIL_INPUT = By.id("lable-login");
    private static final By PASSWORD_INPUT = By.id("lable-password");
    private static final By LOGIN_BUTTON = By.id("submitButton");
    private static final By ERROR_MESSAGE = By.cssSelector(".error-msg");
    private static final By REGISTRATION_LINK = By.id("reglink");
    private static final By FORGOT_PASSWORD_LINK = By.id("restlink");
    private static final By LANGUAGE_RU = By.cssSelector("div.locales a[href*='lang=ru_RU']");
    private static final By LANGUAGE_EN = By.cssSelector("div.locales a[href*='lang=en_US']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return isExist(LOGIN_BUTTON);
    }

    @Override
    public void open() {
        driver.get(BASE_URL);
        log.info("Страница открылась корректно BASE_URL={}", BASE_URL);
    }

    @Step("Ввести '{email}' в поле Login")
    public LoginPage enterEmail(String email) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        return this;
    }

    @Step("Получить value поля Login")
    public String getEmailValue() {
        return driver.findElement(EMAIL_INPUT).getAttribute("value");
    }

    @Step("Ввести '{password}' в поле Password")
    public LoginPage enterPassword(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку Login")
    public LoginPage clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }

    @Step("Получить сообщение об ошибке")
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    @Step("Открыть страницу регистрации")
    public LoginPage clickRegistration() {
        driver.findElement(REGISTRATION_LINK).click();
        return this;
    }

    @Step("Открыть страницу восстановления пароля")
    public LoginPage clickForgotPassword() {
        driver.findElement(FORGOT_PASSWORD_LINK).click();
        return this;
    }

    @Step("Переключить язык интерфейса на English")
    public LoginPage switchToEnglish() {
        driver.findElement(LANGUAGE_EN).click();
        return this;
    }

    @Step("Переключить язык интерфейса на Русский")
    public LoginPage switchToRussian() {
        driver.findElement(LANGUAGE_RU).click();
        return this;
    }
}
