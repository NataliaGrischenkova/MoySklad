package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By EMAIL_INPUT = By.id("lable-login");
    private static final By PASSWORD_INPUT = By.id("lable-password");
    private static final By LOGIN_BUTTON = By.id("submitButton");
    private static final By ERROR_MESSAGE = By.cssSelector(".error-msg");

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
    }

    @Step("Ввести '{username}' в поле Login")
    public void enterEmail(String email) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    @Step("Получить value поля Login")
    public String getEmailValue() {
        return driver.findElement(EMAIL_INPUT).getAttribute("value");
    }

    @Step("Ввести '{password}' в поле Password")
    public void enterPassword(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    @Step("Нажать кнопку Login")
    public void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Получить сообщение об ошибке")
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
